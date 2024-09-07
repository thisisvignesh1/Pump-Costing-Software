package testClass;

import locators.MaterialsPageLocators;
import org.testng.annotations.*;
import pageFunctions.*;
import utils.Mailer;
import utils.WebDriverBase;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.testng.Assert.assertEquals;

public class MaterialsPageTest {

    WebDriverBase webDB = new WebDriverBase();
    boolean flag;
    String endTime;
    String startTime;
    MaterialsPage materialsPage = new MaterialsPage();
    CommonPage commonPage = new CommonPage();
    LoginPage loginPage = new LoginPage();
    UsersPage usersPage = new UsersPage();

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
        } else if ("AdvancedUser".equalsIgnoreCase(user)) {
            return new Object[][]{
                    {webDB.getDataFromProperties("advancedUserUsername"), webDB.getDataFromProperties("advancedUserPassword")}
            };
        } else if ("AllUsers".equalsIgnoreCase(user)) {
            return new Object[][]{
                    {webDB.getDataFromProperties("companyAdminUsername"), webDB.getDataFromProperties("companyAdminPassword")},
                    {webDB.getDataFromProperties("advancedUserUsername"), webDB.getDataFromProperties("advancedUserPassword")}
            };
        }
        return new Object[][]{};
    }

    /**
     * This method opens browser and enter url
     * @author Vignesh - GWL
     */
    //@BeforeTest(groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"})
    public void BrowserOpen() {
        webDB.Setup(System.getProperty("platformName"));
        startTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
        System.out.println("Test case started at" + " " + startTime);
    }

    /**
     * This method enters login page url
     * @author Vignesh - GWL
     */
    @BeforeMethod(groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"})
    public void before(){
        webDB.enterURL(webDB.getDataFromProperties("loginPageUrl"));
    }

    /**
     * This method verifies change password page redirection
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify the Client URL is secured ",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 119)
    public void VerifyMaterialsPageSecurity(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyMaterialsPageUrlIsSecured();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies materials page via navigation panel
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify that the Materials menu item leads to the Materials page."+"Ensure the page title is correct and reflects the content of the page."+
            "Ensure the page loads within an acceptable time frame.", groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 120)
    public void VerifyMaterialsPageViaNavigationPanel(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyMaterialsPageViaNavigationPanel();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies url of materials page
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify that the Materials menu item leads to the correct URL associated page.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 121)
    public void VerifyUrlOfMaterialsPage(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyUrlOfMaterialsPage();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies all links on materials page
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Ensure all links on the page are working correctly."+"Ensure this Delete dialogue box has cancel option.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 122)
    public void VerifyAllLinksOnMaterialsPage(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyAllLinksOnMaterialsPage();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies navigation to other pages
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Ensure navigation to other pages from the current page works correctly.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 123)
    public void VerifyNavigationToOtherPages(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyNavigationToOtherPages();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies text fields are editable
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Ensure all the text fields are editable.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 124)
    public void VerifyTextFieldsAreEditable(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyTextFieldsAreEditable();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies home page redirection
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify that the Home menu item leads to the Home page.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 125)
    public void VerifyHomePageRedirection(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyHomePageRedirection(email);
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies Master manu items accessibility
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify that the Master menu item are accessible.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 126)
    public void VerifyMasterMenuItemsAccessibility(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.VerifyMasterMenuItemsAccessibility();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies placeholder text on search
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify placeholder text added on Search box",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 127)
    public void VerifyPlaceHolderTextOnSearch(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyPlaceHolderTextOnSearch();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies search functionality
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify search is functional and generating the correct result for correct keywords or not by adding valid search input",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 128)
    public void VerifySearchFunctionality(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifySearchFunctionality();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies search functionality via keyboard
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify search working by adding keyword and pressing the Enter key from the keyboard",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 129)
    public void VerifySearchFunctionalityViaKeyboard(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifySearchFunctionalityViaKeyboard();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies copy paste functionality
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify an message display by entering invalid keywords in the search field and clicking the search button"+"Check if the Search options are visible or not for 0 search results",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 130)
    public void VerifyInvalidSearchFunctionality(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.VerifyInvalidSearchFunctionality();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies applied search after navigation
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Ensure applied Searchs persist after navigating away and returning to the page.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 131)
    public void VerifyAppliedSearchAfterNavigation(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyAppliedSearchAfterNavigation();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies total client record on pagination
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Check the total client record count displayed same on the whole page ",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 132)
    public void VerifyTotalClientRecord(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyTotalClientRecord();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies reset option on search
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Ensure the Search box has Reset option."+"Clear the filter field and check all the records are listed ", groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"},
            dataProvider = "loginData", priority = 133)
    public void VerifyResetOptionOnSearch(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyResetOptionOnSearch();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies reset option functionality
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Ensure the Search box has cancel the filter operation and closes the filter field without applying any changes.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 134)
    public void VerifyResetOptionFunctionality(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyResetOptionFunctionality();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies add item dialog box
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Ensure the Add button opens a dialogue box to add a new item.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 135)
    public void VerifyAddItemDialogBox(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyAddItemDialogBox();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies add new material
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Ensure a new item can be added using dialogue box and save"+"Ensure the Field Inputs present in the Add dialogue box."+
            "Verify the confirmation message after the data are saved."+"To verify newly added Materials is displayed in the Materials listing page."+
            "To verify adding a new Material by filling all the required fields and that newly created Material details displayed in the Materials listing page should correctly match with given data."+
            "Ensure an item can be deleted."+"Verify the confirmation message after the data are deleted.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 136)
    public void VerifyAddingNewMaterial(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyAddingNewMaterial();
            if(flag){
                flag = materialsPage.deleteCreatedMaterial(MaterialsPageLocators.MATERIAL_CODE);
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies empty field errors
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Ensure the Add dialogue validates input correctly"+"Ensure the field inputs cannot be left empty.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 137)
    public void VerifyEmptyFieldError(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyEmptyFieldError();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies created data in fields
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Verify that the field data displays the query results correctly.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 138)
    public void VerifyCreatedDataInFields(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyCreatedDataInFields();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies space as first character for material
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Ensure the field Input doesnot accept space at first Character.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 139)
    public void VerifySpaceAsFirstCharacterForMaterial(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifySpaceAsFirstCharacterForMaterial();
            if(flag){
                flag = materialsPage.deleteCreatedMaterial(MaterialsPageLocators.MATERIAL_CODE);
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies character limit error
     * @author Gokul - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Ensure the Materials Name has the correct inputs- Varcharacter of 100 limit."+"Ensure the Materials Code has the correct inputs- Varcharacter of 50 limit.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 140)
    public void VerifyCharacterLimitError(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyCharacterLimitError();
            if(flag){
                flag = materialsPage.clickAddMaterialCancelButton();
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies cancel option on add dialogue box
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Ensure the Add dialogue box has cancel option.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 141)
    public void VerifyCancelOptionOnAddDialogueBox(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyCancelOptionOnAddDialogueBox();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies created item after page refresh
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Ensure that the new item persists and is displayed after a page refresh.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 142)
    public void VerifyCreatedItemAfterPageRefresh(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyAddingNewMaterial();
            if(flag){
                flag = materialsPage.verifyCreatedItemAfterPageRefresh();
                if(flag) {
                    flag = materialsPage.deleteCreatedMaterial(MaterialsPageLocators.MATERIAL_CODE);
                }
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies error for duplicate mateiral item
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Ensure that the system handles attempts to add duplicate items.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 143)
    public void VerifyErrorForDuplicateMaterialItem(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyErrorForDuplicateMaterialItem();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies uom dropdown options
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Verify whether UOM dropdown is present with the specified values - kg, litre and hours"+
            "verify whether user can able to select the specified values - kg, litre and hours "+"verify whether the field change as per the speicific values selected and it is listed on the listing screen",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 144)
    public void VerifyUOMDropdownOptions(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyUOMDropdownOptions();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies presence of delete button on table
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Ensure the Delete button is present for each item in the list.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 145)
    public void VerifyPresenceOfDeleteButtonOnTable(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyPresenceOfDeleteButtonOnTable();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies delete via keyboard action
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Check whether the user can delete the record by clicking on the delete button present on the keyboard",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 146)
    public void VerifyDeleteViaKeyboardAction(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyDeleteViaKeyboardAction();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies delete popup visibility
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify whether the confirmation pop-up appears when we click the Delete Icon."+"Verify whether the confirmation pop-up appears when we click the Delete Icon.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 147)
    public void VerifyDeletePopupVisibility(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyDeletePopupVisibility();
        }
        assertEquals(true, flag);
    }

    /////

    /**
     * This method verifies cancel option on delete dialogue box
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Ensure the Delete dialogue box has cancel option.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 148)
    public void VerifyCancelOptionOnDeleteDialogueBox(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyCancelOptionOnDeleteDialogueBox();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies selecting multiple items and delete
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Ensure whether more than one item can be selected in the given lists."+"Ensure the item can be deleted if more than one records are selected."+
            "Verify clicking on select all is able to delete all records list.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 149)
    public void VerifySelectingMultipleItemsAndDelete(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifySelectingMultipleItemsAndDelete();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies pagination buttons are clickable
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify that the “NEXT” and “PREV” button is clickable on pagination",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 150)
    public void VerifyPaginationButtonsAreClickable(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyMaterialsPageUrlIsSecured();
            if (flag) {
                flag = usersPage.verifyNextAndPreviousButton();
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies default pagination count
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify that the default count of the pagination is displayed on the show box",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 151)
    public void VerifyDefaultCountOnPagination(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyMaterialsPageUrlIsSecured();
            if (flag) {
                flag = usersPage.verifyDefaultPaginationCount();
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies disabled state of pagination
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify that when the user is on the last page then Next button should be disabled and previous arrow disabled in first page.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 152)
    public void VerifyDisabledStateOfPagination(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyMaterialsPageUrlIsSecured();
            if (flag) {
                flag = usersPage.verifyPaginationButtonDisableState();
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies edit created material
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Check that the page redirects to the edit page when the user clicks on the edit icon"+"Check the confirmation message When the user updates the record successfully"+
            "Check that user is able to edit the record by entering the valid data",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 153)
    public void VerifyEditCreatedMaterial(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyEditCreatedMaterial();
            if(flag){
                materialsPage.deleteCreatedMaterial(MaterialsPageLocators.SECOND_MATERIAL_CODE);
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies alert message for empty field
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Check the alert message for all the mandatory fields, when the user leaves the field blank"+"Verify the validation messages are displayed in red color",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 154)
    public void VerifyAlterMessageForEmptyField(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyAlterMessageForEmptyField();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies default info on edit box
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Verify that the default info of the selected item is displayed on the Edit box",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 155)
    public void VerifyDefaultInfoOnEditBox(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyDefaultInfoOnEditBox();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies navigation to price history page for first item
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify that the view icon is visible when the page loads."+"Verify that clicking the view icon displays the details correctly."+
            "Verify that the view icon loads within an acceptable time frame.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 156)
    public void VerifyViewIconButton(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyNavigationToPriceHistoryPageForFirstItem();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies view icon accessibility
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify that the view icon is accessible (e.g., has appropriate aria-label).",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 157)
    public void VerifyViewIconAccessibility(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyViewIconAccessibility();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies multiple view icons
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify the behavior when there are multiple view icons on the page."+"Verifying the updated information displayed in this page",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 158)
    public void VerifyMultipleViewIcons(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyMultipleViewIcons();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies add button on material price page
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Verifying the Add button in this page"+"Verifying particular materials price history details are displayed inside the view page."+
            "Verifying the Delete button in this page"+"Verify the confirmation message after the data are bulk deleted.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 159)
    public void VerifyAddButtonOnMaterialPricePage(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyAddButtonOnMaterialPricePage();
            if(flag){
                flag = materialsPage.deleteCreatedMaterialPrice();
            }
        }
        assertEquals(true, flag);
    }

    ///

    /**
     * This method verifies edit price history page
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Verify user able to edit and update the date and price in the material price history page.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 160)
    public void VerifyEditPriceHistoryPage(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyEditPriceHistoryPage();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies search functionality in listing page
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Verify search is functional in pump listing page is generating the correct result for correct keywords or not by adding valid search input",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 161)
    public void VerifySearchFunctionalityInListingPage(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifySearchFunctionalityInListingPage();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies back button functionality
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verifying the Back button in this page", groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"},
            dataProvider = "loginData", priority = 162)
    public void VerifyBackButtonFunctionality(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyBackButtonFunctionality();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies alphabetical order sorting
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Check if sorting is case-sensitive or case-insensitive."+"Ensure labels are sorted lexicographically, considering numeric portions correctly."+
            "Sorting should consider special characters, which typically come before alphanumeric characters."+"check the Standard alphabetical sorting for the tabular column"+
            "Verify behavior when all labels are identical."+"Ensure numeric strings are sorted correctly."+"Verify sorting behavior with a single label."+"Ensure that spaces at the beginning of labels are considered."+
            "Ensure sorting works for reverse ordered input.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 163)
    public void VerifyAlphabeticalSorting(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyAlphabeticalSorting();
            if(flag){
                flag = materialsPage.verifyAlphabeticalSortingForMaterialName();
                if(flag){
                    flag = materialsPage.verifyAlphabeticalSortingForUOM();
                    if(flag){
                        flag = materialsPage.verifyAlphabeticalSortingForLatestPrice();
                    }
                }
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies duplicate date validation
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Need to display Duplicate date Validation message if UOM is not selected in view pricing history"+"Two validation message appears for same error as well as Duplicate validation message appears for unique value.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 164)
    public void VerifyDuplicateDateValidation(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyDuplicateDateValidation();
            if(flag){
                flag = materialsPage.deleteCreatedMaterialPrice();
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies rows per page values
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "To verify on selecting 10/25/50/100 rows per page, the selected number of rows is displayed per page.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 165)
    public void VerifyRowsPerPageValues(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyRowsPerPageValues();
        }
        assertEquals(true, flag);
    }

    /////

    /**
     * This method verifies material page redirection after deleting record
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Page redirects to Material page if we delete item in View Pricing history which has more than one records.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 166)
    public void VerifyMaterialPageRedirectionAfterDelete(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyMaterialPageRedirectionAfterDelete();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies capitalization in date format
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Need capitalization in date format for add new item in View price history"+"The Date format entered manually accepts in appropriate date with less than 4 digits in year - material pricing page.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 167)
    public void VerifyCapitalizationInDateFormat(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyCapitalizationInDateFormat();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies invalid material name and code
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Material name and code can accepts only numbers as well as special characters without validation message."+"Content change for Price date validation message & calendar display should be feasible to click.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 168)
    public void VerifyInvalidMaterialNameAndCode(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyInvalidMaterialNameAndCode();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies price amount format
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Price amount should display in correct format in Edit material Menu.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 169)
    public void VerifyPriceAmountFormat(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyPriceAmountFormat();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies page entries count
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Need Rows per page entries in Material page as well as in view Pricing history",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 170)
    public void VerifyPageEntriesCount(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyMaterialPageEntriesCount();
            if(flag){
                flag = materialsPage.verifyMaterialPricePageEntriesCount();
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies length value error
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Validation message should appear after lengthy values entered and disappear after deleting the lengthy values in Materials page."+"Space in the validation message for Maximum character entries.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 171)
    public void VerifyLengthValueError(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyLengthValueError();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies company currency on user
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Admin changing the company currency, it is not updated to company and its users. ",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"}, priority = 172)
    public void VerifyCompanyCurrencyOnUser() throws InterruptedException {
        flag = loginPage.verifyValidLogin(webDB.getDataFromProperties("companyAdminUsername"), webDB.getDataFromProperties("companyAdminPassword"));
        if(flag) {
            flag = materialsPage.verifyCompanyCurrencyOnUser();
        }
        assertEquals(true, flag);
    }

    /**
     * This is after method verification
     * @author Vignesh - GWL
     */
    @AfterMethod(groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"})
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
    //@AfterTest(groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"})
    public void TearDown() {
        System.out.println("Tear down");
        webDB.tearDown();
    }

}