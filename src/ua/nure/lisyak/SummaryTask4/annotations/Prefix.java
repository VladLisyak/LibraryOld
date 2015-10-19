package ua.nure.lisyak.SummaryTask4.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Prefix that should be added to names of columns of {@code ResultSet}.
 *
 * @see java.sql.ResultSet
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Prefix {

    /**
     * @return the prefix that should be added to names of columns of {@code ResultSet}.
     */
    String value();

}
