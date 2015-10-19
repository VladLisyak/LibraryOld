package ua.nure.lisyak.SummaryTask4.entity;

import java.util.List;

import ua.nure.lisyak.SummaryTask4.annotations.IsColumn;
import ua.nure.lisyak.SummaryTask4.annotations.Extractable;

/**
 * Entity for {@link Reader} that has ordered books
 */
public class BookUser extends Reader {

	private static final long serialVersionUID = 2143287808901295688L;

	/**
	 * Total sum of fee for overdued books
	 */
	@IsColumn
    private Integer feeSum;

    @Extractable("books")
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> booksList) {
        this.books = booksList;
    }

    public Integer getFeeSum() {
        return feeSum;
    }

    public void setFeeSum(Integer feeSum) {
        this.feeSum = feeSum;
    }
}
