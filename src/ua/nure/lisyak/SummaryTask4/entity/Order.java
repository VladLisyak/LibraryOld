package ua.nure.lisyak.SummaryTask4.entity;


import java.sql.Date;

import ua.nure.lisyak.SummaryTask4.annotations.IsColumn;

/**
 * Order class.
 */
public class Order extends BasicEntity {

	private static final long serialVersionUID = 2495728756004116518L;

	@IsColumn
    private int type;

    @IsColumn("subscriptionId")
    private int subsId;

    @IsColumn
    private int bookId;

    @IsColumn
    private Date dueDate;

    @IsColumn
    private float fee;

    /**
     * Gets order's current type.
     *
     * @return {@link OrderType}
     */
    public OrderType getType() {
        return OrderType.getTypeById(type);
    }

    /**
     * Sets order's type.
     *
     * @param {@link OrderType}
     */
    public void setType(OrderType t) {
        this.type = t.ordinal();
    }

    public int getSubscriptionId() {
        return subsId;
    }

    public void setSubscriptionId(int subsId) {
        this.subsId = subsId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int id) {
        this.bookId = id;
    }

    /**
     * Gets date of expiration
     *
     * @return date of Orders expiration
     */
    public Date getDueDate() {
        return new Date(dueDate.getTime());
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = new Date(dueDate.getTime());
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

}
