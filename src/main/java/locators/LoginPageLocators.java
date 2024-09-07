package locators;

public class LoginPageLocators {

    /** xpath */ public static final String LOGIN_HEADING = "//h3[normalize-space()='Login']";
    /** name */ public static final String EMAIL_TEXT_FIELD = "email";
    /** name */ public static final String PASSWORD_TEXT_FIELD = "password";
    /** xpath */ public static final String PASSWORD_FIELD_EYE = "//span[@class='input-group-text']";
    /** xpath */ public static final String SAVE_CREDENTIALS_CHECKBOX = "//label[normalize-space()='Save credentials']/../input";
    /** xpath */ public static final String LOGIN_BUTTON = "//button[normalize-space()='Login']";
    /** linkText */ public static final String RESET_BUTTON = "Reset";
    /** xpath */ public static final String ERROR_MESSAGE = "//small[@class='d-flex text-danger form-text']";
    /** xpath */ public static final String INVALID_CREDENTIALS_ERROR_MSG = "//div[@class='fade alert alert-danger show']";
    /** xpath */ public static final String ERROR_MSG_FOR_EMPTY_EMAIL = "//input[@name='email']/../following-sibling::small";
    /** xpath */ public static final String ERROR_MSG_FOR_EMPTY_PWD = "//input[@name='password']/../following-sibling::small";

    public static final String COMPANY_LOGIN_EMAIL = "girija4092@gmail.com";
    public static final String COMPANY_LOGIN_PASSWORD = "Admin@123";
    public static final String ERROR_MESSAGE_FOR_INVALID_EMAIL = "Please enter valid email address.";
    public static final String SPACE_ON_FRONT_LOGIN_EMAIL = " girija4092@gmail.com";
    public static final String SPACE_ON_LAST_LOGIN_EMAIL = "girija4092@gmail.com ";
    public static final String ERROR_MESSAGE_FOR_EMPTY_EMAIL = "Please enter email address.";
    public static final String ERROR_MESSAGE_FOR_EMPTY_PWD = "Please enter password.";
    public static final String INVALID_CREDENTIAL_ERROR = "Invalid login credentials";
    public static final String SPACE_ON_LAST_LOGIN_PASSWORD = "Admin@123 ";
    public static final String EMAIL_NOT_REGISTERED_ERROR = "Email is not registered with us";
    public static final String INVALID_EMAIL = "abcd@gmails.com";
    public static final String INVALID_PASSWORD = "passwordis";
    public static final String SIX_CHAR_PASSWORD = "Admin@";
}
