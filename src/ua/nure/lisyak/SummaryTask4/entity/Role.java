package ua.nure.lisyak.SummaryTask4.entity;

/**
 * Defines users roles.
 *
 */
public enum Role {

	/**
	 * Newly created account that needs confirmation
	 */
	NEEDS_CONFIRMATION,
	/**
	 * A banned account.
	 */
	BANNED,
	/**
	 * A reader whose subscription is overdue
	 */
	OVERDUE,
    /**
     * Registered and confirmed account.
     */
    USER,
    /**
     * Librarians account.
     */
    LIBRARIAN,
    /**
     * Administrators account.
     */
    ADMINISTRATOR;

    /**
     * Defines {@link Role} by its ordinal
     * @param roleId role ordinal
     * @return {@link Role} object
     * @throws IllegalArgumentException if id is out of bound
     */
    public static Role getRoleById(int roleId) {
        Role[] roles = values();
        if (roleId < 0 || roleId >= roles.length) {
            throw new IllegalArgumentException("Specified id is out of range");
        }
        return roles[roleId];
    }

}
