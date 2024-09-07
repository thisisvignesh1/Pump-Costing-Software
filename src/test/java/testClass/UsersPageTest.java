package testClass;

import org.testng.annotations.*;
import pageFunctions.CommonPage;
import pageFunctions.LoginPage;
import pageFunctions.UsersPage;
import utils.Mailer;
import utils.WebDriverBase;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.testng.Assert.assertEquals;

public class UsersPageTest {

    WebDriverBase webDB = new WebDriverBase();
    boolean flag;
    String endTime;
    String startTime;
    UsersPage usersPage = new UsersPage();
    CommonPage commonPage = new CommonPage();
    LoginPage loginPage = new LoginPage();

    /**
     * This is the data provider method returns login objects
     * @author Vignesh - GWL
     * @return object
     */
    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        String user = System.getProperty("executionMode");
        if ("CompanyAdmin".equalsIgnoreCase(user)) {
            return new Object[][]{
                    {webDB.getDataFromProperties("companyAdminUsername"), webDB.getDataFromProperties("companyAdminPassword")}
            };
        } else if("SuperAdmin".equalsIgnoreCase(user)) {
            return new Object[][]{
                    {webDB.getDataFromProperties("superAdminUsername"), webDB.getDataFromProperties("superAdminPassword")}
            };
        } else if ("AllUsers".equalsIgnoreCase(user)) {
            return new Object[][]{
                    {webDB.getDataFromProperties("companyAdminUsername"), webDB.getDataFromProperties("companyAdminPassword")},
                    {webDB.getDataFromProperties("superAdminUsername"), webDB.getDataFromProperties("superAdminPassword")}
            };
        }
        return new Object[][]{};
    }

    /**
     * This method opens browser and enter url
     *
     * @author Vignesh - GWL
     */
    //@BeforeTest(groups = {"CompanyAdmin","SuperAdmin", "AllUsers"})
    public void BrowserOpen() {
        webDB.Setup(System.getProperty("platformName"));
        startTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
        System.out.println("Test case started at" + " " + startTime);
    }

    /**
     * This method enters login page url
     *
     * @author Vignesh - GWL
     */
    @BeforeMethod(groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"})
    public void before() {
        webDB.enterURL(webDB.getDataFromProperties("loginPageUrl"));
        //loginPage.verifyValidLogin(webDB.getDataFromProperties("companyAdminUsername"), webDB.getDataFromProperties("companyAdminPassword"));
    }

    /**
     * This method verifies client url is secured
     *
     * @author Vignesh - GWL
     */
    @Test(description = "Verify the Client URL is secured ", groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 48)
    public void VerifyClientUrlIsSecuredWithoutLogin(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyClientUrlIsSecured();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies users page navigation
     *
     * @author Vignesh - GWl
     */
    @Test(description = "Verify that the User menu item leads to the User page.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 49)
    public void VerifyUsersPageNavigation(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyUsersPageNavigation();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies users page url
     *
     * @author Vignesh - GWL
     */
    @Test(description = "Verify that the User menu item leads to the correct URL associated page.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 50)
    public void VerifyUsersPageUrl(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyUsersPageUrl();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies all links on users page
     *
     * @author Vignesh - GWL
     */
    @Test(description = "Ensure all links on the page are working correctly.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 51)
    public void VerifyUsersPageComponents(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyAllLinksOnUsersPage();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies users page load time
     *
     * @author Vignesh - GWL
     */
    @Test(description = "Ensure the page loads within an acceptable time frame.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 52)
    public void VerifyUsersPageLoadTime(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyUsersPageLoadTime();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies text fields are editable
     *
     * @author Vignesh - GWL
     */
    @Test(description = "Ensure all the text fields are editable.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 53)
    public void VerifyTextFieldsAreEditable(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyTextFieldsAreEditable();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies navigation to other pages
     *
     * @author Vignesh - GWL
     */
    @Test(description = "Ensure navigation to other pages from the current page works correctly.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 54)
    public void VerifyNavigationToOtherPages(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyNavigationToOtherPages(email);
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies dynamic content details
     *
     * @author Vignesh - GWL
     */
    @Test(description = "Ensure any dynamic content (e.g., user-specific data, real-time updates) is displayed correctly.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 55)
    public void VerifyDynamicContentDetails(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyUsersPageLoadTime();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies adding new user
     *
     * @throws InterruptedException
     * @author Vignesh - GWL
     */
    @Test(description = "Ensure the Add button is present and properly aligned on the page. " + "Ensure the Add button opens a dialogue box to add a new item. " +
            "Ensure a new item can be added using dialogue box and saved", groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 56)
    public void VerifyAddAndDeleteNewUser(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyAddAndDeleteNewUser(email);
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies error message on input field
     *
     * @author Vignesh - GWL
     */
    @Test(description = "Ensure the Add dialogue validates input correctly.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 57)
    public void VerifyErrorMessageForInputField(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyErrorMessageForInputField(email);
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies presence of input fields
     *
     * @author Vignesh - GWL
     */
    @Test(description = "Ensure the Field Inputs present in the Add User dialogue box.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 58)
    public void VerifyPresenceOfInputFields(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyPresenceOfInputFields();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies empty field error message
     *
     * @throws InterruptedException
     * @author Vignesh - GWL
     */
    @Test(description = "Ensure the field inputs cannot be left empty.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 59)
    public void VerifyEmptyInputFieldError(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyErrorMessageForInputField(email);
        }
        assertEquals(true,flag);
    }

    /**
     * This method verifies saved field data
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that the field data displays the query results correctly.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 60)
    public void VerifySavedFieldData(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifySavedFieldData(email);
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies maximum length error for name field
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Ensure the Name field enforces a maximum length",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 61)
    public void VerifyMaximumLengthForNameField(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyMaximumLengthForNameField();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies name with space as first character
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Ensure the field Input does not accept space at first Character.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 62)
    public void VerifyNameWithSpaceAsFirstCharacter(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyNameWithSpaceAsFirstCharacter(email);
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies error message for invalid email format
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Ensure the Email Address field validates the email format. "+"Verify with an email id with IP address as its Domain. "+
            "Verify an invalid email id with combination of Special Characters. "+"Verify with an invalid Email id without (@) Symbol ."+
            "Verify with an invalid Email id with more number of (.) character. "+"Verify that Email Field accepts only alphabetic characters."+
            "Verify that Email Field accepts numeric characters."+"Verify that Email Field accepts special characters."+
            "Verify that Email Field accepts space as its middle character."+"Verify Email Field accepts space as its first character."+
            "Verify Email Field accepts space as its last character.", groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 63)
    public void VerifyErrorForInvalidEmailFormat(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyErrorForInvalidEmailFormat();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies cancel option from add popup
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Ensure the Add dialogue box has cancel option.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 64)
    public void VerifyCancelOptionInDialogueBox(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyCancelOptionInDialogueBox();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies confirmation message after data saved
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify the confirmation message after the data are saved."+"To verify newly added user is displayed in the user listing page.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 65)
    public void VerifyConfirmationMessageAfterDataSaved(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyAddAndDeleteNewUser(email);
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies created user details
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "To verify adding a new user by filling all the required fields and that newly created user details displayed in the user listing page should correctly match with given data.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 66)
    public void VerifyCreatedUserDetails(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyAddAndDeleteNewUser(email);
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies created item after refresh
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Ensure that the new item persists and is displayed after a page refresh.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 67)
    public void VerifyCreatedItemAfterRefresh(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyCreatedItemAfterRefresh(email);
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies error on creating duplicate item
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Ensure that the system handles attempts to add duplicate items.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 68)
    public void VerifyErrorOnCreatingDuplicateItem(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyErrorOnCreatingDuplicateItem(email);
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies checked status of radio button
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Ensure only one radio button can be selected at a time."+"Ensure the correct radio button is selected by default (if applicable)."+
            "Ensure the dialogue box has a default selection as User radio button.",dataProvider = "loginData", groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"}, priority = 69)
    public void VerifyCheckedStatusOfRadioBtn(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyCheckedStatusOfRadioBtn(email);
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies access radio button via keyboard
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Ensure radio buttons are accessible via keyboard navigation.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 70)
    public void VerifyAccessRadioBtnViaKeyboard(String email, String password) throws InterruptedException, AWTException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyAccessRadioBtnViaKeyboard();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies presence of delete button
     * @author Vignesh - GWL
     */
    @Test(description = "Ensure the Delete button is present for each item in the list.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 71)
    public void VerifyPresenceOfDeleteButton(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyPresenceOfDeleteButton();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies deletion of selected item
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Ensure an item can be deleted."+"Verify the confirmation message after the data are deleted."+"Verify whether the confirmation pop-up appears when we click the Delete Icon.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 72)
    public void VerifySelectedItemCanBeDeleted(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyAddAndDeleteNewUser(email);
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies confirmation message on delete popup
     * @author Vignesh - GWL
     */
    @Test(description = "Verify whether the confirmation pop-up appears when we click the Delete Icon.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 73)
    public void VerifyConfirmationPopupOnDelete(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyConfirmationPopupOnDelete();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies cancel option on delete popup
     * @author Vignesh - GWL
     */
    @Test(description = "Ensure the Delete dialogue box has cancel option."+"Ensure this Delete dialogue box has cancel option.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 74)
    public void VerifyCancelOptionOnDeletePopup(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyCancelOptionOnDeletePopup();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies item selection box
     * @author Vignesh - GWL
     */
    @Test(description = "Ensure whether more than one item can be selected in the given lists.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 75)
    public void VerifyItemSelectionBox(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyItemSelectionBox();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies deleting multiple records
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Ensure the item can be deleted if more than one records are selected."+"Verify the confirmation message after the data are bulk deleted.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 76)
    public void VerifyDeletingMultipleRecords(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyDeletingMultipleRecords(email);
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies select all checkbox
     * @author Vignesh - GWL
     */
    @Test(description = "Verify clicking on select all is able to delete all records list.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 77)
    public void VerifySelectAllCheckbox(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifySelectAllCheckbox();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies placeholder text on filter box
     * @author Vignesh - GWL
     */
    @Test(description = "Verify placeholder text added on Filter box",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 78)
    public void VerifyPlaceholderTextOnFilterBox(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyPlaceholderTextOnFilterBox();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies search button on filter
     * @author Vignesh - GWL
     */
    @Test(description = "Verify search button is present on the field.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 79)
    public void VerifySearchButtonOnFilter(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifySearchButtonOnFilter();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies filter search functionality
     * @author Vignesh - GWL
     */
    @Test(description = "Verify search is functional and generating the correct result for correct keywords or not by adding valid search input",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 80)
    public void VerifyFilterSearchFunctionality(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyFilterSearchFunctionality();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies search via keyboard action
     * @author Vignesh - GWL
     */
    @Test(description = "Verify search working by adding keyword and pressing the Enter key from the keyboard",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 81)
    public void VerifySearchViaKeyboardAction(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifySearchViaKeyboardAction();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies invalid keyword on search
     * @author Vignesh - GWL
     */
    @Test(description = "Verify an message display by entering invalid keywords in the search field and clicking the search button"+
            "Check if the filter options are visible or not for 0 search results",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 82)
    public void VerifyInvalidKeywordsOnSearch(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyInvalidKeywordsOnSearch();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies applied filters after navigation
     * @author Vignesh - GWL
     */
    @Test(description = "Ensure applied filters should not persist after navigating away and returning to the page.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 83)
    public void VerifyAppliedFiltersAfterNavigation(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyAppliedFiltersAfterNavigation();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies total client record count
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Check the total client record count displayed same on the whole page ",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 84)
    public void VerifyTotalClientRecordCount(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyTotalClientRecordCount();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies filter reset option
     * @author Vignesh - GWL
     */
    @Test(description = "Ensure the filter box has Reset option.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 85)
    public void VerifyFilterResetOption(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyFilterResetOption();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies closing filter option
     * @author Vignesh - GWL
     */
    @Test(description = "Ensure the Search box has cancel the filter operation and closes the filter field without applying any changes.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 86)
    public void VerifyClosingFilterOption(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyClosingFilterOption();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies next and previous button
     * @author Vignesh - GWL
     */
    @Test(description = "Verify that the “NEXT” and “PREV” button is clickable on pagination",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 87)
    public void VerifyNextAndPreviousButton(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyNextAndPreviousButton();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies default pagination count
     * @author Vignesh - GWL
     */
    @Test(description = "Verify that the default count of the pagination is displayed on the show box",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 88)
    public void VerifyDefaultPaginationCount(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyUsersPageNavigation();
            if(flag) {
                flag = usersPage.verifyDefaultPaginationCount();
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies pagination button disable state
     * @author Vignesh - GWL
     */
    @Test(description = "Verify that when the user is on the last page then Next button should be disabled and previous arrow disabled in first page.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 89)
    public void VerifyPaginationButtonDisableState(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyUsersPageNavigation();
            if(flag) {
                flag = usersPage.verifyPaginationButtonDisableState();
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies edit page redirection
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Check that the page redirects to the edit page when the user clicks on the edit icon",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 90)
    public void VerifyEditPageRedirection(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyEditPageRedirection();
            if (flag) {
                //flag = usersPage.verifyPopupClose();
                flag = usersPage.verifyDeletePopupClose();
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies editing existing record
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Check that user is able to edit the record by entering the valid data including changing the radion buttons."+
            "Check the confirmation message When the user updates the record successfully",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 91)
    public void VerifyEditExistingRecord(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyEditExistingRecord(email);
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies alert message for empty field
     * @author Vignesh - GWL
     */
    @Test(description = "Check the alert message for all the mandatory fields, when the user leaves the field blank"+
            "Verify the validation messages are displayed in red color ",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 92)
    public void VerifyAlertMessageForEmptyField(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyAlertMessageForEmptyField();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies default item in edit popup
     * @author Vignesh - GWL
     */
    @Test(description = "Verify that the default info of the selected item is displayed on the Edit box",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 93)
    public void VerifyDefaultItemInEditPopup(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyDefaultItemInEditPopup();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies clear filter record
     * @author Vignesh - GWL
     */
    @Test(description = "Clear the filter field and check all the records are listed ",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 94)
    public void VerifyClearFilterRecord(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyFilterResetOption();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies alphabetical order sorting
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "check the Standard alphabetical sorting for the tabular column",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 95)
    public void VerifyAlphabeticalSorting(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = usersPage.verifyAlphabeticalSorting();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies dropdown on add user popup
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Ensure the dropdown menu is present on the page.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"}, priority = 96)
    public void VerifyDropDownOnAddUserPopup() throws InterruptedException {
        flag = loginPage.verifyValidLogin(webDB.getDataFromProperties("superAdminUsername"), webDB.getDataFromProperties("superAdminPassword"));
        if(flag) {
            flag = usersPage.verifyDropDownOnAddUserPopup();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies dropdown options
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Ensure an option can be selected from the dropdown menu."+"Ensure the dropdown menu is accessible via keyboard navigation.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"}, priority = 97)
    public void VerifySelectingDropDownOption() throws InterruptedException {
        flag = loginPage.verifyValidLogin(webDB.getDataFromProperties("superAdminUsername"), webDB.getDataFromProperties("superAdminPassword"));
        if(flag) {
            flag = usersPage.verifySelectingDropDownOption();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies error message for empty dropdown
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Ensure the box validates if a required dropdown menu option is not selected.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"}, priority = 98)
    public void VerifyErrorForEmptyDropDown() throws InterruptedException {
        flag = loginPage.verifyValidLogin(webDB.getDataFromProperties("superAdminUsername"), webDB.getDataFromProperties("superAdminPassword"));
        if(flag) {
            flag = usersPage.verifyErrorForEmptyDropDown();
        }
        assertEquals(true, flag);
    }

    /**
     * This is after method verification
     * @author Vignesh - GWL
     */
    @AfterMethod(groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"})
    public void VerifyAfterMethod() {
        commonPage.closeAllChildWindows();
        commonPage.verifyLogout();
        commonPage.clearCookies();
        webDB.navigateToRefresh();
        endTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    /**
     * This method closes the browser
     * @author Vignesh - GWL
     */
    //@AfterTest(groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"})
    public void TearDown() {
        System.out.println("Tear down");
        webDB.tearDown();
    }
}
