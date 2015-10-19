package ua.nure.lisyak.SummaryTask4.entity;

import ua.nure.lisyak.SummaryTask4.annotations.Extractable;

/**
 * Class for Reader entity. Extends {@link User} but contains 
 * additional field - {@link Date} of subscription expiration
 */
public class Reader extends User {

	private static final long serialVersionUID = -3767271679939510404L;
	
	@Extractable(value = "subscription")
    private Subscription subs;

    public Subscription getSubscription() {
        return subs;
    }

    public void setSubscription(Subscription subs) {
        this.subs = subs;
    }
}
