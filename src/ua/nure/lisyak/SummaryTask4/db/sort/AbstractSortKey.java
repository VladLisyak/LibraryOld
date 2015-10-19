package ua.nure.lisyak.SummaryTask4.db.sort;

/**
 * An abstract sort entity. Provides base constructor for sort definitions.
 */
public abstract class AbstractSortKey implements SortKey{
    private String table;
    private String column;

    protected AbstractSortKey(String tabName, String colName) {
        this.table = tabName;
        this.column = colName;
    }


    protected String getTab() {
        return table;
    }

    protected String getCol() {
        return column;
    }

    /**
     * Gets order by value
     * @param locale code of current locale
     * @return sort order depending of type
     */
    public abstract String getSort(String locale);
}
