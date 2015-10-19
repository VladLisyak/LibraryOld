package ua.nure.lisyak.SummaryTask4.db.parser;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.lisyak.SummaryTask4.annotations.*;
import ua.nure.lisyak.SummaryTask4.exception.ParserException;

/**
 * Parses an object from {@link ResultSet}
 *
 * @param <T> type of object to extract
 * @see ResultSet
 * @see ua.nure.lisyak.SummaryTask4.annotations.Extractable
 * @see ua.nure.lisyak.SummaryTask4.annotations.IsColumn
 * @see ua.nure.lisyak.SummaryTask4.annotations.Prefix
 */
public class Parser<T> {

    private static final Logger LOGGER = Logger.getLogger(Parser.class);
    private Class<T> objClass;
    private List<Field> fieldsList = new ArrayList<>();

    /**
     * Creates a new parser
     *
     * @param objClass Parent class for objects that can be unmarshallized by this parser.
     */
    public Parser(Class<T> objClass) {
        this.objClass = objClass;
    }

    /**
     * Parses an object
     *
     * @param resSet {@link ResultSet} to get the object from
     * @return parsed object of specified type
     * @throws SQLException if unable to perform parsing
     */
    public T parse(ResultSet resSet) throws SQLException {
        try {
            Class<T> clazz = objClass;
            T instance = clazz.newInstance();
            Prefix prefixAnnotation = clazz.getAnnotation(Prefix.class);
            String prefixVal = null;
            if (prefixAnnotation != null) {
                prefixVal = prefixAnnotation.value();
            }
            for (Class<? super T> c = clazz.getSuperclass(); c != Object.class; c = c.getSuperclass()) {
                fillFields(c, resSet, instance, prefixVal);
            }
            fillFields(clazz, resSet, instance, prefixVal);
            fillListFields(instance, resSet);
            return instance;
        } catch (IllegalAccessException e) {
            LOGGER.warn("Unable to set field.", e);
            throw new ParserException("Cannot set field", e);
        } catch (InstantiationException e) {
            LOGGER.warn("Unable create object.", e);
            throw new ParserException("Unable creating object", e);
        }
    }

    private T extractOne(ResultSet resSet, String prefix)
            throws SQLException, IllegalAccessException, InstantiationException {
        Class<T> clazz = objClass;
        T instance = clazz.newInstance();
        for (Class<? super T> c = clazz.getSuperclass(); c != Object.class; c = c.getSuperclass()) {
            fillFields(c, resSet, instance, prefix);
        }
        fillFields(clazz, resSet, instance, prefix);
        return instance;
    }

    private void fillFields(Class clazz, ResultSet resSet, T instance, String prefix)
            throws SQLException, IllegalAccessException, InstantiationException {
        if (clazz!=null){
	    	for (Field field : clazz.getDeclaredFields()) {
	            field.setAccessible(true);
	            IsColumn column = field.getAnnotation(IsColumn.class);
	            if (column == null) {
	                extract(field, resSet, instance, prefix);
	                continue;
	            }
	            String name = column.value();
	            if (name == null || name.isEmpty()) {
	                name = field.getName();
	            }
	            if (prefix != null && !prefix.isEmpty()) {
	                name = prefix + "_" + name;
	            }
	            Object object = resSet.getObject(name, field.getType());
	            field.set(instance, object);
	        }
    	}
    }

    private void extract(Field field, ResultSet resSet, Object instance, String prefix)
            throws SQLException, IllegalAccessException, InstantiationException {
        Extractable ectractAnn = field.getAnnotation(Extractable.class);
        if (ectractAnn != null) {
            if (field.getGenericType() instanceof ParameterizedType) {
                fieldsList.add(field);
                return;
            }

            Parser<?> parser = new Parser<>(field.getType());
            String objPrefix = ectractAnn.value();
            if (objPrefix != null && !objPrefix.isEmpty()) {
                field.set(instance, parser.extractOne(resSet, objPrefix));
            } else {
                String fieldPrefix = prefix == null || prefix.isEmpty() ? "" : prefix + "_";
                field.set(instance, parser.extractOne(resSet, fieldPrefix + field.getName()));
            }
        }
    }

    /**
     * Fills field with a list of extracted objects.
     *
     * @param instance  outer parsed object
     * @param resSet result set to extract from
     * @throws IllegalAccessException
     * @throws SQLException
     */
    private void fillListFields(Object instance, ResultSet resSet)
            throws SQLException, IllegalAccessException {
        for (Field field : fieldsList) {
            Type fieldType = field.getGenericType();
            ParameterizedType concreteType = (ParameterizedType) fieldType;
            Class<?> listType = (Class) concreteType.getActualTypeArguments()[0];
            List<Object> objList = new ArrayList<>();
            int prev = resSet.getInt(1);
            for (int curr = prev; prev == curr; curr = resSet.getInt(1)) {
                Parser<?> parser = new Parser<>(listType);
                Object obj = parser.parse(resSet);
                objList.add(obj);
                if (!resSet.next()) {
                    break;
                }
            }
            resSet.previous();
            field.set(instance, objList);
        }
        fieldsList = new ArrayList<>();
    }
}

