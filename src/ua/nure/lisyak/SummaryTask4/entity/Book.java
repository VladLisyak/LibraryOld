package ua.nure.lisyak.SummaryTask4.entity;

import java.util.List;


import ua.nure.lisyak.SummaryTask4.annotations.IsColumn;
import ua.nure.lisyak.SummaryTask4.annotations.Extractable;
import ua.nure.lisyak.SummaryTask4.annotations.Prefix;

/**
 * Class for Book entity.
 */
@Prefix("book")
public class Book extends BookInformation {
	
	private static final long serialVersionUID = -7225571802614310209L;

	@IsColumn
    private int year;

    @IsColumn
    private int pages;

    @IsColumn
    private String image;

    @Extractable
    private Interpretation title;

    @Extractable
    private Interpretation description;

    @Extractable(value = "publisher")
    private Publisher publisher;

    @Extractable
    private List<Author> authors;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public Interpretation getTitle() {
        return title;
    }

    public void setTitle(Interpretation title) {
        this.title = title;
    }

    public int getPages() {
    	return pages;
    }
    
    public void setPages(int pages) {
    	this.pages = pages;
    }

    public List<Author> getAuthors() {
    	return authors;
    }
    
    public void setAuthors(List<Author> authors) {
    	this.authors = authors;
    }

    public Interpretation getDescription() {
        return description;
    }

    public void setDescription(Interpretation description) {
        this.description = description;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String img) {
        this.image = img;
    }
}
