package ua.nure.lisyak.SummaryTask4.entity;


import ua.nure.lisyak.SummaryTask4.annotations.Extractable;
import ua.nure.lisyak.SummaryTask4.annotations.Prefix;

/**
 * Class for Publisher entity.
 */
@Prefix("publisher")
public class Publisher extends BasicEntity {

	private static final long serialVersionUID = -2724268316285758585L;

	@Extractable()
    private Interpretation title;

    public Interpretation getTitle() {
        return title;
    }

    public void setTitle(Interpretation title) {
        this.title = title;
    }
}
