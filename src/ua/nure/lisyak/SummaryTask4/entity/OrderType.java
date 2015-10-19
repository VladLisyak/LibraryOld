package ua.nure.lisyak.SummaryTask4.entity;


/**
 * Defines the type of order.
 *
 */
public enum OrderType {

    /**
     * Book is ordered(Order confirmed).
     */
    ORDERED,
    /**
     * Order waits for confirmation.
     */
    CHECKED_OUT,
    /**
     * Book is returned to the library.
     */
    COMPLETED,
    /**
     * Book is issued to the reading room.
     */
    READING_ROOM;

    /**
     * Defines {@code OrderType} by its ordinal
     * @param ordinal {@code int} value of concrete {@link OrderType}
     * @return {@code OrderType} object
     * @throws IllegalArgumentException if the {@code ordinal} is out of range
     */
    public static OrderType getTypeById(int ordinal) {
        OrderType[] roles = values();
        if (ordinal < 0 || ordinal >= roles.length) {
            throw new IllegalArgumentException("Invalid type id");
        }
        return roles[ordinal];
    }

}
