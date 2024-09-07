package locators;

public class ChangePasswordPageLocators {

    /** xpath */ public static final String CHANGE_PASSWORD_HEADING = "//h3[normalize-space()='Change Password']";
    /** xpath */ public static final String CURRENT_PASSWORD_ASTERISK = "//label[@for='current_password']//span";
    /** id */ public static final String CURRENT_PASSWORD_FIELD = "current_password";
    /** xpath */ public static final String NEW_PASSWORD_ASTERISK = "//label[@for='new_password']//span";
    /** id */ public static final String NEW_PASSWORD_FIELD = "new_password";
    /** xpath */ public static final String CONFIRM_PASSWORD_ASTERISK = "//label[@for='confirm_password']//span";
    /** id */ public static final String CONFIRM_PASSWORD_FIELD = "confirm_password";
    /** xpath */ public static final String UPDATE_BUTTON = "//form[@id='changePasswordForm']//button[contains(text(),'Update')]";
    /** xpath */ public static final String NEW_PASSWORD_ERROR = "//input[@id='new_password']/../following-sibling::span";
    /** xpath */ public static final String CURRENT_PASSWORD_ERROR = "//input[@id='current_password']/../following-sibling::span";
    /** xpath */ public static final String CONFIRM_PASSWORD_ERROR = "//input[@id='confirm_password']/../following-sibling::span";
    /** xpath */ public static final String UPDATE_PASSWORD_SUCCESS_MESSAGE = "//div[@role='alert']//div[contains(text(),'Password changed successfully.')]";

    public static final String LENGTH_LIMIT_PASSWORD = "ThisIsTheTestPassword";
    public static final String MAXIMUM_LENGTH_NEW_PWD = "Maximum length should be 12 characters.";
    public static final String MINIMUM_LENGTH_NEW_PWD = "Test";
    public static final String MINIMUM_LENGTH_NEW_PWD_ERROR = "Minimum length should be 9 characters.";
    public static final String CURRENT_PWD_EMPTY_ERROR = "Please enter current password.";
    public static final String NEW_PWD_EMPTY_ERROR = "Please enter new password.";
    public static final String CONFIRM_PWD_EMPTY_ERROR = "Please enter confirm password.";
    public static final String PLAIN_PASSWORD = "thisistest";
    public static final String INVALID_NEW_PASSWORD_ERROR = "Password must contain at least 1 uppercase letter, 1 lowercase letter, 1 digit, and 1 special character";
    public static final String INVALID_PASSWORD = "Wrongpwd@123";
    public static final String CURRENT_INVALID_PWD = "Current password is incorrect.";
    public static final String RANDOM_PASSWORD = "Random@123";
    public static final String PASSWORD_MISMATCH_ERROR = "Passwords do not match.";
}
