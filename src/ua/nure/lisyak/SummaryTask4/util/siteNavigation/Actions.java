package ua.nure.lisyak.SummaryTask4.util.siteNavigation;

/**
 * Class-container for relative URLs for all servlets.
 */
public final class Actions {
	 private static final String ADD_PATH = "/add";
	 private static final String EDIT_PATH = "/edit";
	 private static final String DELETE_PATH = "/delete";
	 
	 public static final class Common{
		  public static final String HOME = "/index";
		  public static final String PROFILE = "/profile";
		  public static final String REGISTRATION = "/registration";
		  public static final String LOGOUT = "/logout";
		  
		  public static final class Cart{
			  	public static final String CART = "/cart";
	            public static final String UPDATE_ITEM = CART + "/update";
	            public static final String DELETE_ITEM = CART + DELETE_PATH;

	            private Cart() {
	            }
		  }
		  
		  public static final class Books {

	            public static final String LIST = "/books";
	            public static final String ITEM = "/book";

	            private Books() {

	            }
	      }
		  
		  public static final class Authors {
	            public static final String ITEM = "/author";

	            private Authors() {

	            }
	        }
		  
		  private Common(){
		  }
	  }
	 
	 public static final class Admin{		 
		 private static final String USERS = "/users";
		 
		 public static final String DASHBOARD_INDEX = "/dashboard";
     /*public static final String LOGIN = DASHBOARD_INDEX + "/login";
     public static final String LOGOUT = DASHBOARD_INDEX + "/logout";*/

	        /**
	         * Librarians managing actions.
	         */
	        public static final class Librarians {
	        	private static final String LIBRARIANS = "/librarians";
	        	
	            public static final String LIBRARIANS_PATH = DASHBOARD_INDEX + USERS + LIBRARIANS;
	            public static final String ADD = LIBRARIANS_PATH + ADD_PATH;
	            public static final String EDIT = LIBRARIANS_PATH + EDIT_PATH;
	            public static final String DELETE = LIBRARIANS_PATH + DELETE_PATH;

	            private Librarians() {
	            }

	        }

	        /**
	         * Administrators managing actions.
	         */
	        public static final class Admins {
	        	private static final String ADMINISTRATORS = "/administrators";

	            public static final String ADMINISTRATORS_PATH = DASHBOARD_INDEX + USERS + ADMINISTRATORS;
	            public static final String ADD = ADMINISTRATORS_PATH + ADD_PATH;
	            public static final String EDIT = ADMINISTRATORS_PATH + EDIT_PATH;
	            public static final String DELETE = ADMINISTRATORS_PATH + DELETE_PATH;


	            private Admins() {
	            }

	        }


	        /**
	         * Users (readers) managing actions.
	         */
	        public static final class Users { 	
	            public static final String USERS_PATH = DASHBOARD_INDEX + USERS;
	            public static final String CONFIRM = DASHBOARD_INDEX + USERS + "/confirm";
	            public static final String NOT_CONFIRMED = USERS_PATH + "/not-confirmed";
	            public static final String BAN = USERS_PATH + "/ban";
	            public static final String BLACK_LIST = USERS_PATH + "/black-list";
	            public static final String UNBAN = USERS_PATH + "/unban";
	            public static final String OVERDUE = USERS_PATH + "/overdue";
	            public static final String EXTEND_OVERDUE = OVERDUE + "/extend";
	            public static final String EXTEND_ACTUAL = USERS_PATH + "/extend";

	            private Users() {
	            }

	        }

	        /**
	         * Authors managing actions.
	         */
	        public static final class Authors {
	        	private static final String AUTHORS = "/authors";

	            public static final String AUTHORS_PATH = DASHBOARD_INDEX + AUTHORS;
	            public static final String ADD = AUTHORS_PATH + ADD_PATH;
	            public static final String EDIT = AUTHORS_PATH + EDIT_PATH;
	            public static final String DELETE = AUTHORS_PATH + DELETE_PATH;

	            private Authors() {
	            }

	        }

	        /**
	         * Books managing actions.
	         */
	        public static final class Books {
	        	private static final String BOOKS = "/books";
	        	
	            public static final String BOOKS_PATH = DASHBOARD_INDEX + BOOKS;
	            public static final String ADD = BOOKS_PATH + ADD_PATH;
	            public static final String EDIT = BOOKS_PATH + EDIT_PATH;
	            public static final String DELETE = BOOKS_PATH + DELETE_PATH;

	            private Books() {
	            }

	        }

	        /**
	         * Publishers managing actions.
	         */
	        public static final class Publishers {
	        	private static final String PUBLISHERS = "/publishers";

	            public static final String PUBLISHERS_PATH = DASHBOARD_INDEX + PUBLISHERS;
	            public static final String ADD = PUBLISHERS_PATH + ADD_PATH;
	            public static final String EDIT = PUBLISHERS_PATH + EDIT_PATH;
	            public static final String DELETE = PUBLISHERS_PATH + DELETE_PATH;

	            private Publishers() {
	            }

	        }

	        private Admin(){
	        }

	    }
          
         
     
	 
	 public static final class Lib{
		private static final String ORDERS = "/orders"; 
		
	    public static final String LIBRARY_INDEX = "/library";
        public static final String ADD_ORDER = LIBRARY_INDEX + ORDERS + ADD_PATH;
        public static final String USER_BOOKS = LIBRARY_INDEX + "/user-books";
        
        public static final class ReadingRooms {

            public static final String LIST = LIBRARY_INDEX + ORDERS + "/reading-rooms";
            public static final String COMPLETE = LIST + "/complete";
           /* public static final String REPORT = LIST + "/*";*/


            private ReadingRooms() {
            }

        }
        
        public static final class Completed {

            public static final String LIST = LIBRARY_INDEX + ORDERS + "/completed";
            /*public static final String REPORT = INDEX + "/orders/completed/*";*/

            private Completed() {
            }

        }
        
        public static final class Ordered {

            public static final String LIST = LIBRARY_INDEX + ORDERS + "/ordered";
            public static final String ACCEPT = LIST + "/accept";
            /*public static final String REPORT = INDEX + "/orders/ordered/*";*/

            private Ordered() {
            }

        }
         
        public static final class CheckedOut {

            public static final String LIST = LIBRARY_INDEX + ORDERS + "/checked-out";
            /*public static final String REPORT = LIBRARY_INDEX + ORDERS + "/checked-out/*";*/
            public static final String COMPLETE = LIST + "/complete";

            private CheckedOut() {
            }

        }
        
	     private Lib(){
	     }
     }
	 

	 private Actions(){
	 }
}
