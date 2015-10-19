package ua.nure.lisyak.SummaryTask4.util.siteNavigation;
/**
 * Class-container for relative paths to jsp pages of project.
 */
public final class PagesPaths {
	 
	private static final String PREFIX = "/WEB-INF/views/";
	
	public static final class Main {
        private static final String MAIN = PREFIX + "main/";
        
        public static final String LOGIN = MAIN + "login.jsp";
        public static final String PROFILE = MAIN + "profile.jsp";
        public static final String REGISTRATION = MAIN + "registration.jsp";
        public static final String REGISTRATION_SUCCEED = MAIN + "registrationSucceed.jsp";
        
        public static final class Books {
        	private static final String BOOKS_INDEX = MAIN + "books/";
        	
            public static final String LIST = BOOKS_INDEX + "list.jsp";
            public static final String ITEM = BOOKS_INDEX + "book.jsp";
            public static final String CART = BOOKS_INDEX + "cart.jsp";

            private Books() {
            }

        }
        
        public static final class Authors {

            public static final String ITEM = MAIN + "authors/author.jsp";

            private Authors() {
            }

        }
	}
	
	public static final class Lib {
		private static final String MANAGING = "managing/";
		private static final String ORDERS = "orders/";
		private static final String ORDERS_MANAGING_PATH = MANAGING + ORDERS;
		private static final String LIBRARY = PREFIX + "library/";
		
		public static final String INDEX = LIBRARY + "index.jsp";
		public static final String ADD_ORDER = LIBRARY + MANAGING + "add.jsp";
		public static final String USER_BOOKS = LIBRARY + MANAGING + "userBooks.jsp";
		
		public static final class ReadingRooms {
			private static final String READING_ROOMS = "readingRooms/";

            public static final String LIST = LIBRARY + ORDERS_MANAGING_PATH + READING_ROOMS + "list.jsp";
            public static final String COMPLETE = LIBRARY + MANAGING + ORDERS + READING_ROOMS + "complete.jsp";

            private ReadingRooms() {
            }

        }
		
		public static final class Completed {

            public static final String LIST = LIBRARY + ORDERS_MANAGING_PATH + "completed/list.jsp";

            private Completed() {
            }

        }
		
		public static final class Ordered {

            public static final String LIST = LIBRARY + ORDERS_MANAGING_PATH + "ordered/list.jsp";
            public static final String ACCEPT = LIBRARY + ORDERS_MANAGING_PATH + "ordered/accept.jsp";

            private Ordered() {
            }

        }
		
		 public static final class CheckedOut {
	
	            public static final String LIST = LIBRARY + ORDERS_MANAGING_PATH + "checkedOut/list.jsp";
	            public static final String COMPLETE = LIBRARY + ORDERS_MANAGING_PATH + "/checkedOut/complete.jsp";
	
	            private CheckedOut() {
	            }
	
	     }
	}
	
	public static final class Dashboard {
        private static final String DASHBOARD = PREFIX + "dashboard/managing/";
        public static final String INDEX =PREFIX+"dashboard/index.jsp";

        public static final class Librarians {
        	private static final String LIBRARIANS_PATH = DASHBOARD + "librarians/";
        	
            public static final String LIST = LIBRARIANS_PATH + "list.jsp";
            public static final String ADD = LIBRARIANS_PATH + "add.jsp";
            public static final String EDIT = LIBRARIANS_PATH + "edit.jsp";
            public static final String DELETE = LIBRARIANS_PATH + "delete.jsp";

            private Librarians() {
            }
        }


        public static final class Admins {
        	private static final String ADMINS_PATH = DASHBOARD + "administrators/";

            public static final String LIST = ADMINS_PATH + "list.jsp";
            public static final String ADD = ADMINS_PATH + "add.jsp";
            public static final String EDIT = ADMINS_PATH + "edit.jsp";
            public static final String DELETE = ADMINS_PATH + "delete.jsp";

            private Admins() {
            }
        }


        public static final class Users {
        	private static final String USERS_PATH = DASHBOARD + "users/";
        	
            public static final String CONFIRM = USERS_PATH + "confirm.jsp";
            public static final String NOT_CONFIRMED = USERS_PATH + "notConfirmed.jsp";
            public static final String LIST = USERS_PATH + "list.jsp";
            public static final String BAN = USERS_PATH + "ban.jsp";
            public static final String UNBAN = USERS_PATH + "unban.jsp";
            public static final String BLACK_LIST = USERS_PATH + "blackList.jsp";
            public static final String OVERDUE_LIST = USERS_PATH + "overdue.jsp";
            public static final String EXTEND = USERS_PATH + "extend.jsp";

            private Users() {
            }
        }

        
        public static final class Authors {
        	private static final String ORDERS_PATH = DASHBOARD + "authors/";

            public static final String LIST = ORDERS_PATH + "list.jsp";
            public static final String ADD = ORDERS_PATH + "add.jsp";
            public static final String EDIT = ORDERS_PATH + "edit.jsp";
            public static final String DELETE = ORDERS_PATH + "delete.jsp";

            private Authors() {
            }

        }

       
        public static final class Books {

            private static final String BOOKS = DASHBOARD + "books/";

            public static final String LIST = BOOKS + "list.jsp";
            public static final String ADD = BOOKS + "add.jsp";
            public static final String EDIT = BOOKS + "edit.jsp";
            public static final String DELETE = BOOKS + "delete.jsp";

            private Books() {

            }

        }

       
        public static final class Publishers {

            private static final String PUBLISHERS = DASHBOARD + "publishers/";

            public static final String ADD = PUBLISHERS + "add.jsp";
            public static final String DELETE = PUBLISHERS + "delete.jsp";
            public static final String EDIT = PUBLISHERS + "edit.jsp";
            public static final String LIST = PUBLISHERS + "list.jsp";

            private Publishers() {

            }
        }

        private Dashboard() {

        }

    }

}
