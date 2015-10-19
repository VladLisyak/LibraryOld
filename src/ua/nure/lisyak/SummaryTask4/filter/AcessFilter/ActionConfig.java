package ua.nure.lisyak.SummaryTask4.filter.AcessFilter;

import org.apache.log4j.Logger;

import ua.nure.lisyak.SummaryTask4.entity.Role;
import ua.nure.lisyak.SummaryTask4.entity.User;


/**
 * Defines if the current user can access the specified action.
 */
public class ActionConfig extends AbstractAccessConfig {
	private static final Logger LOGGER = Logger.getLogger(ActionConfig.class);
    private String[] actions;

     /**
     * Creates a new instance of Action Configuration.
     *
     * @param role allowed user {@link Role}
     * @param redirect redirection path
     * @param actions actions that must be under control of this config
     */
    public ActionConfig(Role userRole, String redirect, String... actions) {
        super(userRole, redirect);
        this.actions = actions;
    }
    
    @Override
   	public String isAllowed(User usr, String path) {
   		if (userRoleMatch(usr)){
   			return null;
   		}
   		return getRedirect();
   	}

	@Override
	public boolean belongs(String s) {
		for (String string : actions) {
			if(string.equals(s)){
				LOGGER.debug(string+" "+ s);
				return true;
			}
		}
		return false;
	}

}
