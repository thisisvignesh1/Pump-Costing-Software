package locators;

public class EditProfilePageLocators {

    /** id */ public static final String FIRST_NAME = "first_name";
    /** id */ public static final String LAST_NAME = "last_name";
    /** id */ public static final String ROLE = "role";
    /** id */ public static final String COMPANY_NAME = "company";
    /** id */ public static final String EMAIL_ADDRESS = "email";
    /** xpath */ public static final String UPDATE_BUTTON = "//form[@id='editProfileForm']//button[contains(text(),'Update')]";
    /** xpath */ public static final String BACK_BUTTON = "//form[@id='editProfileForm']//button[contains(text(),'Back')]";
    /** xpath */ public static final String EMAIL_ASTERISK = "//label[@for='email']//span";
    /** xpath */ public static final String FIRST_NAME_ASTERISK = "//label[@for='first_name']//span";
    /** xpath */ public static final String LAST_NAME_ASTERISK = "//label[@for='last_name']//span";
    /** xpath */ public static final String UPDATE_CONFIRMATION = "//div[contains(text(),'Profile updated successfully')]";
    /** xpath */ public static final String FNAME_ERROR_MESSAGE_FOR_EMPTY_FIELD = "//input[@id='first_name']/following-sibling::span[@class='error-message']";
    /** xpath */ public static final String EDIT_PROFILE_HEADING = "//h3[contains(text(),'Edit Profile')]";
    /** xpath */ public static final String LNAME_ERROR_MESSAGE_FOR_EMPTY_FIELD = "//input[@id='last_name']/following-sibling::span[@class='error-message']";
    /** xpath */ public static final String USERNAME_ON_TOP_RIGHT_CORNER = "//a[@id='dropdown-basic']//span";

    public static final String FIRST_NAME_EMPTY_ERROR = "Please enter first name.";
    public static final String LAST_NAME_EMPTY_ERROR = "Please enter last name.";
    public static final String FIRST_NAME_ALPHANUMERIC_ERROR = "Please enter only alphanumeric characters.";
    public static final String SUPER_ADMIN_FIRSTNAME = "Pranav";
}
