package locators;

public class PumpsPageLocators {

    /** xpath */ public static final String PUMPS_SIDE_MENU = "//span[contains(text(),'Pumps')]/..//span[1]";
    /** xpath */ public static final String PUMP_CODE_HEADER = "//div[normalize-space()='Pump Code']";
    /** xpath */ public static final String PUMP_HEADING = "//h4[normalize-space()='Pumps']";
    /** xpath */ public static final String PUMP_NAME_HEADER = "//div[normalize-space()='Pump Name']";
    /** xpath */ public static final String VARIANTS_HEADER = "//div[normalize-space()='Variants']";
    /** xpath */ public static final String MOC_STANDARD_HEADER = "//div[normalize-space()='MOC Standard']";
    /** xpath */ public static final String PUMP_TYPE_HEADER = "//div[normalize-space()='Pump Type']";
    /** xpath */ public static final String PARTS_COUNT_HEADER = "//div[normalize-space()='Parts Count']";
    /** xpath */ public static final String ACTION_HEADER = "//div[contains(text(),'Action')]";
    /** xpath */ public static final String TABLE_FIRST_ROW_CHECKBOX = "//div[@role='rowgroup'][2]//div[@role='row'][1]//div[1]//div//input";
    /** xpath */ public static final String TABLE_FIRST_ROW_PUMP_CODE = "//div[@role='rowgroup'][2]//div[@role='row'][1]//div[2]//div";
    /** xpath */ public static final String TABLE_FIRST_ROW_PUMP_NAME = "//div[@role='rowgroup'][2]//div[@role='row'][1]//div[3]//div";
    /** xpath */ public static final String TABLE_FIRST_ROW_VARIANTS = "//div[@role='rowgroup'][2]//div[@role='row'][1]//div[4]//div";
    /** xpath */ public static final String TABLE_FIRST_ROW_MOC_STANDARD = "//div[@role='rowgroup'][2]//div[@role='row'][1]//div[5]//div";
    /** xpath */ public static final String TABLE_FIRST_ROW_PUMP_TYPE = "//div[@role='rowgroup'][2]//div[@role='row'][1]//div[6]//div";
    /** xpath */ public static final String TABLE_FIRST_ROW_PARTS_COUNT = "//div[@role='rowgroup'][2]//div[@role='row'][1]//div[7]//div";
    /** xpath */ public static final String TABLE_FIRST_ROW_EDIT = "//div[@role='rowgroup'][2]//div[@role='row'][1]//div[8]//a";
    /** xpath */ public static final String TABLE_FIRST_ROW_EYE = "//div[@role='rowgroup'][2]//div[@role='row'][1]//div[8]//div//button[@class='btn-icon btn-rounded btn btn-outline-secondary']";
    /** xpath */ public static final String TABLE_FIRST_DELETE = "//div[@role='rowgroup'][2]//div[@role='row'][1]//div[8]//div//button[@class='btn-icon btn-rounded btn btn-outline-danger']";
    /** xpath */ public static final String DELETE_BUTTON = "//button[contains(text(),'Delete')]";
    /** xpath */ public static final String ADD_BUTTON = "//span[contains(text(),'Add')]";
    /** xpath */ public static final String FILTER_BUTTON = "//button[contains(text(),'Filter')]";
    /** xpath */ public static final String DELETE_POPUP_TITLE = "//div[@class='modal-title h4']";
    /** xpath */ public static final String DELETE_POPUP_TEXT = "//div[@class='mt-3 modal-body']//p";
    /** xpath */ public static final String DELETE_POPUP_CANCEL = "//button[contains(text(),'Cancel')]";
    /** xpath */ public static final String DELETE_POPUP_CONFIRM = "//button[contains(text(),'Yes')]";
    /** xpath */ public static final String POPUP_CLOSE_ICON = "//*[name()='svg'][@height='25']";
    /** xpath */ public static final String FILTER_VARIANTS_DROPDOWN = "//div[contains(text(),'Select Variant')]";
    /** xpath */ public static final String FILTER_MOC_STANDARD_DROPDOWN = "//div[contains(text(),'Select MOC Standard')]";
    /** xpath */ public static final String FILTER_SEARCH_BUTTON = "//input[@value='Search']";
    /** xpath */ public static final String FILTER_RESET_BUTTON = "//input[@value='Reset']";
    /** xpath */ public static final String ADD_PUMP_TYPE_DROP = "//div[contains(text(),'Select Pump Type')]";
    /** xpath */ public static final String ADD_MOC_STANDARD_DROP = "//div[contains(text(),'Select MOC Standard')]";
    /** xpath */ public static final String ADD_VARIANT_DROP = "//div[@id='react-select-187-placeholder']";
    /** xpath */ public static final String ADD_VARIANT_VALUE_DROP = "//div[contains(text(),'Select Variant Value')]";
    /** xpath */ public static final String ADD_VARIANT = "//button[@class='btn btn-info add-new' and contains(text(),'Add Variant')]";
    /** xpath */ public static final String ADD_VARIANT_PLUS = "//i[@class='fa fa-plus add-icon']";
    /** xpath */ public static final String ADD_PART = "//button[@class='btn btn-info add-new' and contains(text(),'Add Part')]";
    /** xpath */ public static final String ADD_SELECT_PART_DROP = "//div[contains(text(),'Select Part')]";
    /** xpath */ public static final String ADD_PART_PLUS = "//i[@class='fa fa-plus add-icon add-pump']";
    /** xpath */ public static final String ADD_CANCEL_BUTTON = "//button[contains(text(),'Cancel')]";
    /** xpath */ public static final String ADD_SAVE_BUTTON = "//button[contains(text(),'Cancel')]";
    /** xpath */ public static final String PART_DETAIL_BACK = "//button[contains(text(),'Back')]";
    /** xpath */ public static final String PART_DETAIL_DELETE = "//button[contains(text(),'Delete')]";
    /** xpath */ public static final String PART_DETAIL_ADD = "//span[contains(text(),'Add')]";
    /** xpath */ public static final String PART_DETAIL_FILTER = "//a[contains(text(),'Filter')]";
    /** xpath */ public static final String PART_DETAIL_POPUP_SELECT_PART = "//div[contains(text(),'Select Part')]";
    /** xpath */ public static final String PART_DETAIL_POPUP_SAVE = "//button[contains(text(),'Save')]";
    /** xpath */ public static final String PART_DETAIL_POPUP_CANCEL = "//button[contains(text(),'Cancel')]";

}
