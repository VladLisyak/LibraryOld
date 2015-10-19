package ua.nure.lisyak.SummaryTask4.util;

import java.util.Arrays;
import java.util.List;
/**
 * Contains initial constants for applications inner needs.
 */
public final class Constants {
	
	/**
	 * Application configuration paths.
	 */
	public static final class ROUTES{
      public static final String DATABASE_PROPS_PATH = "database.properties";
      public static final String BUNDLE_PATH = "transls";
      public static final String RES_PATH = "/uploads/";
      public static final String UPLOAD_AUTHORS_DIRECTORY = "authors/";
 	  public static final String UPLOAD_BOOKS_DIRECTORY = "books/";
 	  public static final String UPLOAD_DIR = "B://library/";
    }
	
	/**
	 * Constants for working with requests.
	 */
	public static final class Attributes{
	  public static final String DEFAULT_LOCALE = "defaultLocale";
	  public static final String EMAIL_CONFIRMATION_PARAMETER = "uId";
	  public static final String LOCALES = "locales";
	  public static final String IMAGE = "image";
	  public static final String ID = "id";
	  public static final String DESCRIPTION = "description";
	  public static final String LANG = "lang";
	  public static final String CURRENT_LOCALE = "currLocale";
	  public static final String ANONYMOUS_USER_LOCALE = "anonLocale";
	  public static final String TRANSLATOR = "translator";
	  public static final String QUERY = "query";
	  
	  public static final String URL = "url";
	  public static final String CURRENT_USER_ROLE = "currUser";
	  public static final String ORDERED_BOOKS = "orderedBooks";
	  public static final String REGISTRATION_SUCCEED = "registrationSucceed";
	  public static final String USER = "user";
	  public static final String ADMINISTRATOR = "admin";
	  public static final String USERS = "users";
	  public static final String AUTHORS = "authors";
	  public static final String BOOK = "book";
	  public static final String ALREADY_ORDERED = "alrOrdered";
	  public static final String BOOKS = "books";
	  public static final String PROCESS_RESULT_SESSION = "resultSession";
	  public static final String PROCESS_RESULT = "result";
	  public static final String AUTHOR = "author";
	  public static final String PUBLISHER = "publisher";
	  public static final String PUBLISHERS = "publishers";
	  public static final String TITLE = "title";
	  public static final String LIBRARIAN = "librarian";
	  public static final String NAME = "name";
	}
    
	/**
	 * Services names
	 */
	public static final class Service{
      public static final String AUTHOR_SERVICE = "authorService";
      public static final String BOOK_SERVICE = "bookService";
      public static final String ORDER_SERVICE = "orderService";
      public static final String PUBLISHER_SERVICE = "publisherService";
      public static final String ITERPRETATION_SERVICE = "interpretationService";
      public static final String USER_SERVICE = "userService";
	  public static final String FILE_PROC_SERVICE = "fileProcessingService";
	}
	
	/**
	 * DAO names
	 */
	public static final class DAO{
      public static final String AUTHOR_DAO = "publisherDAO";
      public static final String BOOK_DAO = "bookDAO";
      public static final String ORDER_DAO = "orderDAO";
      public static final String PUBLISHER_DAO = "publisherDAO";
      public static final String SUBSCRIPTION_DAO = "subscriptionDAO";
      public static final String TRANSLATION_DAO = "translationDAO";
      public static final String USER_DAO = "userDAO";
	}
	
	/**
	 * Application configuration properties.
	 */
	public static final class Settings{
	 public static final int SUBSCRIPTION_DURATION = 12;
	 public static final int ORDER_DURATION = 3;
	 public static final int MAX_CHECK_OUT_PERIOD = 180;
	 public static final int BOOKS_ON_PAGE = 4;
	 public static final float FEE = 10;
	 public static final List<String> ALLOWED_IMAGE_TYPES = Arrays.asList("image/png;image/jpeg;image/gif".split(";"));
	 public static final String UPDATE_ORDERS_TASK_EXECUTION_TIME = "05:00";
	 public static final String UPLOAD_BOOKS_DIRECTORY = "books/";
	 public static final String UPLOAD_AUTHORS_DIR = "authors/";
	 public static final int FILE_SIZE_THRESHOLD = 1024*1024;
	 
	 /**
	  * Defines if image with specified type is allowed.
	  * @param contentType type uploading file.
	  * @return {@code true} if type is appropriate, {@code false} if it is not.
	  */
	 public static boolean isProperImageType(String contentType) {
	        return ALLOWED_IMAGE_TYPES.contains(contentType);
	 }
	 
	 	/**
	     * Configuration for books' images.
	     */
	 public static final class Books {
	        public static final String TEMP_DIR = "B://library/books/tmp";

	        private Books() {
	        }
	    }
	    /**
	     * Configuration for authors' images.
	     */
	    public static final class Authors {
	        public static final String TEMP_DIR = "B://library/authors/tmp";

	        private Authors() {

	        }
	    }
	}
	
	/**
	 * Validation constants.
	 * 
	 */
	public static final class Validation{
		public static final String LEN_3_TO_100 = "validator.lengthFrom3to100";
		public static final String LEN_5_TO_100 = "validator.lengthFrom5to100";
		public static final String LEN_5_TO_1000 = "validator.lengthFrom5to1000";
		public static final String LETTERS_ONLY = "validator.lettersOnly";
		public static final String INV_EMAIL = "validator.invalidEmail";
		public static final String PATT_MATCH = "validator.loginPatternMatch";
		public static final String NO_WHITESPACES = "validator.noWhitespaces";
		public static final String CANT_BE_EMPTY = "validator.cannotBeEmpty";
		public static final String PAGES_COUNT = "validator.pagesCount";
		public static final String YEAR = "validator.year";
		public static final String TOO_BIG = "validator.tooBig";
		public static final String CANT_BE_NEGATIVE = "validator.cannotBeNegative";
		
	}
	
    private Constants(){	
    } 
}
