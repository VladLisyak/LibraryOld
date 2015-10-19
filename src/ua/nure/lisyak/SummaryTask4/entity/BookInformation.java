package ua.nure.lisyak.SummaryTask4.entity;



import ua.nure.lisyak.SummaryTask4.annotations.IsColumn;

/**
 * Initial info about book without the book instance.
 */
public class BookInformation extends BasicEntity{

	private static final long serialVersionUID = -8628655727394705480L;

	@IsColumn
    private int amount;

    @IsColumn
    private int inStock;

    public int getAmount() {
        
    	return amount;
    }

    public void setAmount(int amount) {
        
    	this.amount = amount;
    }

    public int getInStock() {
        
    	return inStock;
    }

    public void setInStock(int inStock) {
        
    	this.inStock = inStock;
    }

}
