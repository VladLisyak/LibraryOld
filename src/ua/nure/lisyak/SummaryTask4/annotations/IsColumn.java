package ua.nure.lisyak.SummaryTask4.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates a field that reffers to the correspondent column in 
 * {@code ResultSet} object
 * 
 * @see java.sql.ResultSet
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsColumn{

    /**
     * Name of the column. Not required parameter that should be specified 
     * only if it not the same as the name of the field. 
     * @return column name
     */
    String value() default "";

}
