package ua.nure.lisyak.SummaryTask4.db.sort;

/**
 * A sort order entity that generates locale-dependent order key.
 */
public class TranslSortKey extends AbstractSortKey {

    /**
     * Creates a new {@code TranslSortKey} object.
     *
     * @param table table name
     */
    public TranslSortKey(String table) {
        super(table, null);
    }

    @Override
    public String getSort(String locale) {
        return getTab() + "_" + locale;
    }

}
