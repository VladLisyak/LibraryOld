package ua.nure.lisyak.SummaryTask4.filter.AcessFilter;

import org.apache.log4j.Logger;

import ua.nure.lisyak.SummaryTask4.entity.User;
import ua.nure.lisyak.SummaryTask4.entity.Role;


/**
 * Defines if the current user can access the specified module (set of actions with same prefix path).
 */
public class ModuleConfig extends AbstractAccessConfig {
	private static final Logger LOGGER = Logger.getLogger(ModuleConfig.class);
    private String basePath;
    private String[] exclude;

     /**
     * Creates a new module access configuration.
     *
     * @param userRole allowed user role
     * @param basePath path of the URL the path must start with to be under config control
     * @param redirect redirect action if access denied
     */
    public ModuleConfig(Role role, String basePath, String redirect, String... exclude) {
        super(role, redirect);
        this.basePath = basePath;
        this.exclude = exclude;
    }
  

	@Override
	public boolean belongs(String path) {
		boolean belongs = false;
		for (String string : exclude) {
			belongs = belongs || string.equals(path) || string.startsWith(path);
			if (belongs){
				break;
			}
			LOGGER.debug(belongs+" "+ path+ " "+ string);
		}
		
		return !basePath.isEmpty() && ((basePath.equals(path) || path.startsWith(basePath)) || belongs);
	}
	
	@Override
	public String isAllowed(User usr, String path) {
		 for (String s : exclude) {
	            if (s.equals(path)) {
	                return null;
	            }
	     }	 
		 if(userRoleMatch(usr)){
			 return null;
		 }	 
	   return getRedirect();
	}


}

