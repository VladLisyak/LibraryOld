package ua.nure.lisyak.SummaryTask4.entity;


import ua.nure.lisyak.SummaryTask4.annotations.Extractable;

/**
 * Provides detailed information about concrete order.
 */
public class OrderDetails extends Order {

	private static final long serialVersionUID = -7854276388957175882L;

	@Extractable
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
