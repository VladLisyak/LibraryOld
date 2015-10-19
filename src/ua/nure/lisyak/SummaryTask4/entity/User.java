package ua.nure.lisyak.SummaryTask4.entity;

import ua.nure.lisyak.SummaryTask4.annotations.IsColumn;

/**
 * Class describing User entity.
 */
public class User extends BasicEntity {

	private static final long serialVersionUID = 6798337064016938919L;

	@IsColumn
    private String firstName;

    @IsColumn
    private String lastName;
    
    @IsColumn
    private int role;

    @IsColumn
    private String email;

    @IsColumn
    private String login;

    @IsColumn
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    /**
     *  Gets user {@link Role}.
     *
     * @return user {@link Role}
     */
    public Role getRole() {
    	return Role.getRoleById(role);
    }
    
    /**
     * Sets user {@link Role}.
     *
     * @param new user's {@link Role} 
     */
    public void setRole(Role role) {
    	this.role = role.ordinal();
    	
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
