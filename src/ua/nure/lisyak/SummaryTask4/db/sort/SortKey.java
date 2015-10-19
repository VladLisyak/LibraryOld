package ua.nure.lisyak.SummaryTask4.db.sort;

/**
 * Entity for storing sort key.
 */
public interface SortKey {

    /**
     * Gets the sort key based on {@code locale}
     * @param locale code of current locale
     * @return the value of the parameter
     */
    String getSort(String locale);

}
