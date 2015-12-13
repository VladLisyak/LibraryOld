package ua.nure.lisyak.SummaryTask4.entity;

import ua.nure.lisyak.SummaryTask4.annotations.IsColumn;

public class Queue extends BasicEntity{
	
	@IsColumn
	private int userId;
	@IsColumn
	private int bookId;
	
	public Queue(Integer bookId, Integer userId) {
		this.userId = userId;
		this.bookId = bookId;
	}
	
	public Queue(){
		
	}
	public int getUserId() {
		return userId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	private static final long serialVersionUID = 4811435353682679238L;
	
	

}
