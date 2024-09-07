package locators;

public class UsersPageLocators {

    /** xpath */ public static final String USERS_TITLE = "//h4[normalize-space()='Users']";
    /** xpath */ public static final String DELETE_BUTTON = "//button[normalize-space()='Delete']";
    /** xpath */ public static final String FILTER_BUTTON = "//a[@class='filter-icon btn btn-secondary' and contains(text(),'Filter')]";
    /** xpath */ public static final String DELETE_POPUP_TITLE = "//div[@class='modal-title h4' and contains(text(),'Confirm Delete')]";
    /** xpath */ public static final String DELETE_POPUP_TEXT = "//div[@class='modal-title h4']/../following-sibling::div//p";
    /** xpath */ public static final String DELETE_CONFIRM_BTN = "//button[normalize-space()='Yes, Delete']";
    /** xpath */ public static final String DELETE_CANCEL_BTN = "//button[normalize-space()='Cancel']";
    /** id */ public static final String ADD_USER_POPUP_ADVANCED_USER_RADIO_BTN = "advancedUserRole";
    /** id */ public static final String ADD_USER_POPUP_USER_RADIO_BTN = "userRole";
    /** xpath */ public static final String USERS_ICON = "//span[contains(text(),'Users')]/..//span[1]";
    /** id */ public static final String ADD_USER_POPUP_FIRST_NAME_FIELD = "first_name";
    /** id */ public static final String ADD_USER_POPUP_LAST_NAME_FIELD = "last_name";
    /** xpath */ public static final String ADD_BUTTON = "//span[contains(text(),'Add')]";
    /** id */ public static final String ADD_USER_POPUP_EMAIL_FIELD = "email";
    /** xpath */ public static final String POPUP_CLOSE_ICON = "//*[name()='svg'][@height='25']";
    /** xpath */ public static final String ADD_USER_POPUP_CANCEL_BTN = "//div[contains(text(),'Add User')]/../following-sibling::div[3]//button[contains(text(),'Cancel')]";
    /** xpath */ public static final String ADD_USER_POPUP_SAVE_BTN = "//div[contains(text(),'Add User')]/../following-sibling::div[3]//button[contains(text(),'Save')]";
    /** id */ public static final String FILER_NAME_FIELD = "user_name";
    /** xpath */ public static final String FILTER_SEARCH_BUTTON = "//form[@id='searchHistoryForm']//div//div[3]//input[1]";
    /** xpath */ public static final String FILTER_SEARCH_RESET_BUTTON = "//form[@id='searchHistoryForm']//div//div[3]//input[2]";
    /** id */ public static final String HEADER_CHECK_BOX = "header_checkbox";
    /** xpath */ public static final String NAME_HEADER = "//div[normalize-space()='Name']";
    /** xpath */ public static final String TABLE_TOTAL_ROWS = "//div[@role='rowgroup'][2]//div[@role='row']";
    /** xpath */ public static final String TABLE_FIRST_ROW_CHECKBOX = "//div[@role='rowgroup'][2]//div[@role='row'][1]//input";
    /** xpath */ public static final String TABLE_SECOND_ROW_CHECKBOX = "//div[@role='rowgroup'][2]//div[@role='row'][2]//input";
    /** xpath */ public static final String TABLE_FIRST_ROW_NAME = "//div[@role='rowgroup'][2]//div[@role='row'][1]//div[2]//div";
    /** xpath */ public static final String TABLE_FIRST_ROW_EDIT_ICON = "//div[@role='rowgroup'][2]//div[@role='row'][1]//div[5]//button[1]";
    /** xpath */ public static final String TABLE_FIRST_ROW_DELETE_ICON = "//div[@role='rowgroup'][2]//div[@role='row'][1]//div[5]//button[2]";
    /** xpath */ public static final String TABLE_TOTAL_DELETE_ICON = "//div[@role='rowgroup'][2]//div[@role='row']//div[5]//button[2]";
    /** xpath */ public static final String EDIT_USER_UPDATE_BTN = "//div[contains(text(),'Edit User')]/../following-sibling::div[3]//button[2]";
    /** id */ public static final String PAGINATION_FIRST_PAGE = "pagination-first-page";
    /** id */ public static final String PAGINATION_PREVIOUS_PAGE = "pagination-previous-page";
    /** id */ public static final String PAGINATION_NEXT_PAGE = "pagination-next-page";
    /** id */ public static final String PAGINATION_LAST_PAGE = "pagination-last-page";
    /** xpath */ public static final String ADD_USER_POPUP_COMPANY_DROPDOWN = "//label[@for='company']/following-sibling::div";
    /** xpath */ public static final String PAGINATION_TOTAL_COUNT = "//button[@id='pagination-first-page']/../..//span[2]";
    /** xpath */ public static final String EDIT_USER_POPUP_CANCEL_BTN = "//div[contains(text(),'Edit User')]/../following-sibling::div[3]//button[contains(text(),'Cancel')]";
    /** xpath */ public static final String ADD_USER_SUCCESS_POPUP = "//div[contains(text(),'User Created Successfully.')]";
    /** xpath */ public static final String DELETE_SUCCESS_POPUP_CLOSE = "//div[contains(text(),'User deleted successfully!')]/../following-sibling::button";
    /** xpath */ public static final String DELETE_MULTI_SUCCESS_POPUP_CLOSE = "//div[contains(text(),'Users deleted successfully!')]/../following-sibling::button";
    /** xpath */ public static final String ADD_POPUP_EMPTY_EMAIL_ERROR = "//div[@class='error-message' and contains(text(),'Please enter email address.')]";
    /** xpath */ public static final String FIRSTNAME_FIELD_ERROR = "//input[@id='first_name']/following-sibling::div[@class='error-message']";
    /** xpath */ public static final String DUPLICATE_EMAIL_POPUP = "//div[contains(text(),'Email is already registered with us.')]";
    /** xpath */ public static final String LASTNAME_FIELD_ERROR = "//input[@id='last_name']/following-sibling::div[@class='error-message']";
    /** xpath */ public static final String EMAIL_FIELD_ERROR = "//input[@id='email']/following-sibling::div[@class='error-message']";
    /** xpath */ public static final String NO_RECORDS_ERROR = "//div[@role='table']//div//div[contains(text(),'There are no records')]";
    /** xpath */ public static final String DEFAULT_DROPDOWN = "//option[@value='10']";
    /** xpath */ public static final String EDIT_USER_SUCCESS_POPUP = "//div[contains(text(),'User Details Updated Successfully.')]";
    /** xpath */ public static final String TABLE_ALL_NAMES = "//div[@role='rowgroup'][2]//div[@role='row']//div[2]//div";
    /** xpath */ public static final String DROPDOWN_OPTION = "//div[@class=' css-1dimb5e-singleValue']";
    /** xpath */ public static final String EMPTY_COMPANY_ERROR = "//div[@class='error-message' and contains(text(),'Please select company.')]";
    /** xpath */ public static final String TABLE_LAST_ROW_EDIT_ICON = "//div[@role='rowgroup'][2]//div[@role='row'][last()]//div[5]//button[1]";
    /** xpath */ public static final String CURRENCY_VALUE = "//label[@for='currency_id']/following-sibling::div//div[@class=' css-1dimb5e-singleValue']";


    public static final String DEMO_FIRST_NAME = "Test First Name";
    public static final String DEMO_LAST_NAME = "Test Last Name";
    public static final String DEMO_EMAIL = "testmail@test.com";
    public static final String DEMO_SECOND_FIRST_NAME = "Second FName";
    public static final String DEMO_SECOND_LAST_NAME = "Second LName";
    public static final String DEMO_SECOND_EMAIL = "secondtestmail@test.com";
    public static final String LENGTH_ERROR_NAME = "ThisIsTheTestNameToVerifyMaximumLength";
    public static final String MAXIMUM_LENGTH_ERROR = "Maximum length should be 15 characters.";
    public static final String FIRSTNAME_WITH_SPACE_AT_FIRST = " FNameWithSpace";
    public static final String LASTNAME_WITH_SPACE_AT_FIRST = " LNameWithSpace";
    public static final String ERROR_MESSAGE_FOR_INVALID_EMAIL = "Please enter valid email address.";
    public static final String DEMO_EMAIL_WITH_SPACE_AT_FIRST = "testmail@test.com ";
    public static final String DUPLICATE_EMAIL_ERROR = "Email Address already exists.";
    public static final String DELETE_POPUP_TEXT_MSG = "Are you sure you want to delete the user";
    public static final String INVALID_NAME = "&*()";
    public static final String EMPTY_FIRST_NAME_ERROR = "Please enter first name.";
    public static final String RED_COLOR_CSS = "rgba(255, 0, 0, 1)";
}