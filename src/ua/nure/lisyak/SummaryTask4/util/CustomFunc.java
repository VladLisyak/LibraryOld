package ua.nure.lisyak.SummaryTask4.util;

import java.util.Collection;

import ua.nure.lisyak.SummaryTask4.entity.BasicEntity;
/**
 * Class-container for custom functions
 *
 */
public final class CustomFunc {
	/**
	 * Defines if specified entity contains on collection.
	 * @param collection Collection to look at.
	 * @param item {@link BasicEntity} entity that needs checking.
	 * @return {@code true} if entity contains in collection, {@link false} if it is not.
	 */
	 public static <E extends BasicEntity> boolean contains(Collection<E> collection, BasicEntity item) {
	        for (BasicEntity entity : collection){
	            if (entity.getId() == item.getId()) {
	                return true;
	            }
	        }
	        return false;
	    }
}
