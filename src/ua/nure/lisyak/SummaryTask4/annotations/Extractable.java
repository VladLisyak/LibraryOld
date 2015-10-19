package ua.nure.lisyak.SummaryTask4.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker of the complex object that should be 
 * extracted from the DB separately.
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Extractable {

    /**
     * Prefix of the columns that should be included to the Object.
     * 
     * @return prefix of Objects columns.
     */
    String value() default "";
}
