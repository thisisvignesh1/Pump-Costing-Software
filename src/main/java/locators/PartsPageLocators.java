package locators;

public class PartsPageLocators {

    /** xpath */ public static final String ADD_BUTTON = "//h4[normalize-space()='Parts']/../../../following-sibling::div[2]//span[contains(text(),'Add')]";
    /** xpath */ public static final String PARTS_SIDE_MENU = "//span[contains(text(),'Parts')]/..//span[1]";
    /** xpath */ public static final String PARTS_PAGE_HEADING = "//h4[normalize-space()='Parts']";
    /** xpath */ public static final String ADD_PAGE_TITLE = "//h3[@class='box-title']";
    /** id */ public static final String ADD_PAGE_PART_CODE = "part_code";
    /** id */ public static final String ADD_PAGE_PART_NAME = "part_name";
    /** id */ public static final String ADD_PAGE_MATERIAL_WEIGHT = "materialweight";
    /** xpath */ public static final String HOME_PAGE_LOGO = "//h4[normalize-space()='Parts']/../../..//a";
    /** xpath */ public static final String FILTER_BUTTON = "//button[@class='filter-icon btn btn-secondary']";
    /** id */ public static final String PART_NAME_FIELD = "part_name";
    /** xpath */ public static final String SEARCH_BUTTON = "//h4[normalize-space()='Parts']/../../../following-sibling::div[2]//input[@value='Search']";
    /** xpath */ public static final String RESET_BUTTON = "//h4[normalize-space()='Parts']/../../../following-sibling::div[2]//input[@value='Reset']";
    /** xpath */ public static final String TABLE_FIRST_ROW_PART_NAME = "//div[@role='rowgroup'][2]//div[@role='row'][1]//div[3]//div";
    /** xpath */ public static final String NO_RECORDS_INFO = "//div[contains(text(),'There are no records to display')]";
    /** xpath */ public static final String PART_CATEGORY_DROPDOWN = "//label[@for='partcategory']/following-sibling::div//div[contains(text(),'Part Category')]";
    /** xpath */ public static final String MATERIAL_DROPDOWN = "//label[@for='material']/following-sibling::div//div[contains(text(),'Material')]";
    /** xpath */ public static final String SELECTED_PART_CATEGORY = "//div[contains(text(),'P001')]";
    /** xpath */ public static final String SELECTED_MATERIAL_CATEGORY = "//div[contains(text(),'Alloys')]";
    /** xpath */ public static final String ADD_PART_NEXT_BUTTON = "//button[normalize-space()='Next']";
    /** id */ public static final String LUMP_SUM_PRICE = "processes.0.lumsumPrice";
    /** id */ public static final String PROCESS_DROPDOWN = "//div[contains(text(),'Select Process')]";
    /** xpath */ public static final String SAVE_BUTTON = "//button[normalize-space()='Save']";
    /** xpath */ public static final String PART_CREATED_SUCCESS = "//div[@role='alert']//div[2]";
    /** xpath */ public static final String TABLE_FIRST_ROW_PART_CODE = "//div[@role='rowgroup'][2]//div[@role='row'][1]//div[2]//div";
    /** xpath */ public static final String TABLE_FIRST_ROW_PART_CATEGORY = "//div[@role='rowgroup'][2]//div[@role='row'][1]//div[4]//div";
    /** xpath */ public static final String TABLE_FIRST_ROW_IS_PURCHASED = "//div[@role='rowgroup'][2]//div[@role='row'][1]//div[5]//div";
    /** xpath */ public static final String TABLE_FIRST_ROW_PROCESS_PRICE = "//div[@role='rowgroup'][2]//div[@role='row'][1]//div[8]//div";
    /** xpath */ public static final String TABLE_FIRST_ROW_TOTAL_PRICE = "//div[@role='rowgroup'][2]//div[@role='row'][1]//div[9]//div";
    /** xpath */ public static final String DELETE_BUTTON = "//h4[normalize-space()='Parts']/../../../following-sibling::div[2]//button[contains(text(),'Delete')]";
    /** xpath */ public static final String DELETE_POPUP_CONFIRM = "//div[@class='modal-title h4']/../following-sibling::div[2]//button[2]";
    /** xpath */ public static final String DELETE_POPUP_CANCEL = "//div[@class='modal-title h4']/../following-sibling::div[2]//button[1]";
    /** xpath */ public static final String PART_CODE_ERROR_MESSAGE = "//input[@id='part_code']/following-sibling::div";
    /** xpath */ public static final String PART_NAME_ERROR_MESSAGE = "//input[@id='part_name']/following-sibling::div";
    /** xpath */ public static final String IS_PURCHASED_TOGGLE = "//label[@for='is_purchased']";
    /** id */ public static final String PURCHASED_PRICE_FIELD = "purchased_price";
    /** xpath */ public static final String CANCEL_BUTTON = "//button[normalize-space()='Cancel']";
    /** id */ public static final String PAGINATION_FIRST_PAGE = "pagination-first-page";
    /** id */ public static final String PAGINATION_PREVIOUS_PAGE = "pagination-previous-page";
    /** id */ public static final String PAGINATION_NEXT_PAGE = "pagination-next-page";
    /** xpath */ public static final String DEFAULT_DROPDOWN = "//option[@value='10']";
    /** id */ public static final String PAGINATION_LAST_PAGE = "pagination-last-page";
    /** xpath */ public static final String TABLE_FIRST_ROW_EDIT_BUTTON = "//div[@role='rowgroup'][2]//div[@role='row'][1]//div[10]//a";
    /** xpath */ public static final String TABLE_FIRST_ROW_DELETE_BUTTON = "//div[@role='rowgroup'][2]//div[@role='row'][1]//div[10]//button[2]";
    /** xpath */ public static final String TABLE_FIRS_ROW_EYE_ICON = "//div[@role='rowgroup'][2]//div[@role='row'][1]//div[10]//button[@class='btn-icon btn-rounded btn btn-outline-secondary']";
    /** xpath */ public static final String EDIT_PART_HEADING = "//h3[@class='box-title']";
    /** xpath */ public static final String POPUP_CLOSE_ICON = "//*[name()='svg'][@height='25']";
    /** xpath */ public static final String FILTER_PART_CATEGORY_DROPDOWN = "//div[contains(text(),'Select Part Category')]";
    /** xpath */ public static final String FILTER_IS_PURCHASED_TOGGLE = "//label[@for='is_purchased']";
    /** xpath */ public static final String FILTER_SEARCH_BTN = "//input[@value='Search']";
    /** xpath */ public static final String FILTER_RESET_BTN = "//input[@value='Reset']";
    /** xpath */ public static final String PART_HISTORY_TITLE = "//h3[contains(text(),'View Part Price History')]";
    /** xpath */ public static final String PREVIOUS_BUTTON = "//button[contains(text(),'Previous')]";
    /** id */ public static final String MATERIAL_PRICE_FIELD = "materialprice";
    /** id */ public static final String DATE_PICKER = "datepicker";
    /** xpath */ public static final String TABLE_TOTAL_ROWS = "//div[@role='rowgroup'][2]//div[@role='row']";
    /** xpath */ public static final String PART_CATEGORY_OPTIONS = "//div[@role='listbox']//div";
    /** xpath */ public static final String PART_CATEGORY_ERROR = "//div[@id='partcategory']/following-sibling::div";
    /** xpath */ public static final String SELECTED_PART_CATEGORYY = "//div[contains(text(),'P002')]";




    public static final String ADD_PART_HEADER = "Add Part";
    public static final String PART_NAME_PLACEHOLDER = "Part Name";
    public static final String DUMMY_NAME = "DummyName";
    public static final String PART_CODE = "P98890";
    public static final String PART_NAME = "Test Part Name";
    public static final String PART_NAME_WITH_SPACE = " Test Part Name";
    public static final String INVALID_NAME = "&*#%^#&";
    public static final String INVALID_CODE_ERROR = "Please enter valid part code.";
    public static final String INVALID_NAME_ERROR = "Please enter valid part name.";
    public static final String EMPTY_CODE_ERROR = "Please enter part code.";
    public static final String EMPTY_NAME_ERROR = "Please enter part name.";
    public static final String LENGTHY_NAME = "This is the test name to check the limit of the name field";
    public static final String LENGTHY_NAME_ERROR = "Maximum length should be 50 characters.";
    public static final String DUPLICATE_CODE_ERROR = "Part Code already exists.";
    public static final String EMPTY_PART_CATEGORY_ERROR = "Please select part category.";
}
