package ua.nure.lisyak.SummaryTask4.util;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import ua.nure.lisyak.SummaryTask4.entity.Author;
import ua.nure.lisyak.SummaryTask4.entity.Book;
/**
 * Class that describes tag for viewing book on jsp page.
 *
 */
public class CustomTag extends SimpleTagSupport {
	private Book book;
	private String lang;
	private String reff;
	

	public String getReff() {
		return reff;
	}
	public void setReff(String reff) {
		this.reff = reff;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		out.println("<div class=\"product span3 \"> <div class=\"image\">");
		out.println("<a href=\""+ reff + "/book?id="+book.getId() +"\">");
		out.println("<img src=\"/uploads/books/" + book.getImage() + "\" style = \"width:200px; height:200px;\">");
		out.println("</a> </div> <div class=\"details\"> <a href=\""+ reff + "/book?id="+book.getId() +"\" class=\"clearfix\">");
		out.println("<h4 class=\"title\">" + book.getTitle().value(lang) + "</h4>  ");
		for (Author auth : book.getAuthors()) {
			out.println("<span class=\"vendor\">" + auth.getName().value(lang) + "</span>");
		}
		
		out.print("<span class=\"price\">" + book.getPages() + "</span>");
		out.println("<span class=\"vendor\"> \"" + book.getPublisher().getTitle().value(lang) + "\"</span>");
		out.println("</a> </div> </div>");
		
	}
}

