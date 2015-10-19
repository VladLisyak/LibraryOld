package ua.nure.lisyak.SummaryTask4.entity;

import java.sql.Date;

import ua.nure.lisyak.SummaryTask4.annotations.IsColumn;

/**
 * Class for Subscription entity.
 */
public class Subscription extends BasicEntity {

	private static final long serialVersionUID = -8675241891049179202L;

	@IsColumn
    private int userId;
	
    @IsColumn
    private Date expirationDate;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int id) {
        this.userId = id;
    }

    /**
     * Gets subscription's expiration date.
     *
     * @return {@link Date} of expiration of subscription
     */
    public Date getExpirationDate() {
        return new Date(expirationDate.getTime());
    }

    /**
     * Sets subscription's expiration date.
     *
     * @param expDate new subs expiration date
     */
    public void setExpirationDate(Date expDate) {
        this.expirationDate = new Date(expDate.getTime());
    }

}
