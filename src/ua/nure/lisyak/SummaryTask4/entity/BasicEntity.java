package ua.nure.lisyak.SummaryTask4.entity;

import java.io.Serializable;

import ua.nure.lisyak.SummaryTask4.annotations.IsColumn;

/**
 * Base class for all entities.
 */
public abstract class BasicEntity implements Serializable {
	
	private static final long serialVersionUID = -7882238380753737241L;

	@IsColumn
    private int id;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BasicEntity other = (BasicEntity) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
