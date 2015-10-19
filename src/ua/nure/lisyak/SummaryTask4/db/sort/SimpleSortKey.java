package ua.nure.lisyak.SummaryTask4.db.sort;

/**
 * A sort order key for values that aren't dependent of locale.
 */
public class SimpleSortKey extends AbstractSortKey {

    /**
     * Instantiates a new {@code SimpleSortKey} object.
     *
     * @param table  name of table as part of key
     * @param column name of column to be used in key
     */
    public SimpleSortKey(String table, String column) {
        super(table, column);
    }

    @Override
    public String getSort(String locale) {
        return getTab() + "_" + getCol();
    }

}
