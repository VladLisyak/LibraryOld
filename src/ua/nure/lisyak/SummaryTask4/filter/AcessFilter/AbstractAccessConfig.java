package ua.nure.lisyak.SummaryTask4.filter.AcessFilter;

import ua.nure.lisyak.SummaryTask4.entity.User;
import ua.nure.lisyak.SummaryTask4.entity.Role;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;

/**
 * Implements basic functionality of {@link AccessConfiguration} interface.
 */
public abstract class AbstractAccessConfig implements AccessConfiguration {

    private Role userRole;
    private static final String redirect = PagesPaths.Lib.INDEX;

    protected AbstractAccessConfig(Role userRole, String redirect) {
        this.userRole = userRole;
    }
    
    public String getRedirect() {
        return redirect;
    }
    
    public boolean userRoleMatch(User user) {
        if (user == null) {
            return false;
        }
            if (user != null && user.getRole() == userRole) {
                return true;
            }
        
        return false;
    }

}
