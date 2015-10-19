package ua.nure.lisyak.SummaryTask4.entity;


import ua.nure.lisyak.SummaryTask4.annotations.IsColumn;
import ua.nure.lisyak.SummaryTask4.annotations.Extractable;
import ua.nure.lisyak.SummaryTask4.annotations.Prefix;

/**
 * Class for Author entity.
 */
@Prefix("author")
public class Author extends BasicEntity {

	private static final long serialVersionUID = 7205103418753081835L;

	@Extractable
    private Interpretation name;

    @Extractable
    private Interpretation description;

    @IsColumn
    private String image;

    public Interpretation getName() {
        return name;
    }

    public void setName(Interpretation name) {
        this.name = name;
    }

    public Interpretation getDescription() {
        return description;
    }

    public void setDescription(Interpretation description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String img) {
        this.image = img;
    }
}
