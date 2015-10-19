package ua.nure.lisyak.SummaryTask4.filter.AcessFilter;

import ua.nure.lisyak.SummaryTask4.entity.User;


/**
 * Defines the paths that user with specified {@link Role} can access to.
 *
 * @see ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions
 */
public interface AccessConfiguration {

   /**
     * Defines if the specified path belongs to 
     * paths under control of this configuration
     * 
     * @param path the path to be checked
     * @return {@code true} if current config contains such path, {@code false} otherwise
     */
	public boolean belongs(String s);
	

    /**
     * Defines if specified path is allowed for current user {@link Role}
     * @param usr current {@link User}
     * @param path path to be tested
     * @return redirection path if current is not allowed. 
     */
    public String isAllowed(User usr, String path);
	
    /**
     * Gets action, where the user will be redirected to in case of denied access.
     *
     * @return action, where the user will be redirected to in case of denied access
     */
    String getRedirect();
    
     /**
     * Defines if current User's {@link Role} meets requirements of config. 
     * @param user user whose Role to be tested 
     * @return {@code true} if current User's {@link Role} meets 
     * requirements of config, {@code false} otherwise.
     */
    public boolean userRoleMatch(User user);

}

