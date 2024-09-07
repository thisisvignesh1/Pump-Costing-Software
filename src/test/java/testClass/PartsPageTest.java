package testClass;

import locators.PartsPageLocators;
import org.testng.annotations.*;
import pageFunctions.*;
import utils.WebDriverBase;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.testng.Assert.assertEquals;

public class PartsPageTest {

    WebDriverBase webDB = new WebDriverBase();
    boolean flag;
    String endTime;
    String startTime;
    PartsPage partsPage = new PartsPage();
    CommonPage commonPage = new CommonPage();
    LoginPage loginPage = new LoginPage();
    UsersPage usersPage = new UsersPage();
    MaterialsPage materialsPage = new MaterialsPage();

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
    @BeforeTest(groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"})
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
    @Test(description = "Verify the Client URL is secured ", groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"},
            dataProvider = "loginData", priority = 173)
    public void VerifyPartsPageUrlIsSecured(String email, String password) {
        flag = partsPage.verifyPartsPageUrlIsSecured();
        if(flag) {
            flag = loginPage.verifyValidLogin(email, password);
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies parts page redirection
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify that the Part menu item leads to the Part page."+"Ensure the page title is correct and reflects the content of the page."+
            "Ensure the page loads within an acceptable time frame.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 174)
    public void VerifyPartsPageRedirection(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.navigateToPartsPage();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies parts page url
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify that the Part menu item leads to the correct URL associated page.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 175)
    public void VerifyPartsPageUrl(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifyPartsPageUrl();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies links on parts page
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Ensure all links on the page are working correctly."+"Ensure navigation to other pages from the current page works correctly.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 176)
    public void VerifyLinksOnPartsPage(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifyNavigationToAddPartPage();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies text fields are editable
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Ensure all the text fields are editable.", groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"},
            dataProvider = "loginData", priority = 177)
    public void VerifyTextFieldsAreEditable(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifyTextFieldsAreEditable();
        }
        assertEquals(true, flag);
    }

    //////

    /**
     * This method verifies home page redirection
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify that the Home menu item leads to the Home page.", groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"},
            dataProvider = "loginData", priority = 178)
    public void VerifyHomePageRedirection(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifyHomePageRedirection();
        }
        assertEquals(true, flag);
    }

    /**
     * This methd verifies accessibility to master menu
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify that the Master menu item are accessible.", groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"},
            dataProvider = "loginData", priority = 179)
    public void VerifyAccessibilityToMastersMenu(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifyAccessibilityToMastersMenu();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies placeholder text in search box
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify placeholder text added on Search box", groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"},
            dataProvider = "loginData", priority = 180)
    public void VerifyPlaceholderTextInSearchBox(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifyPlaceholderTextInSearchBox();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies search button presence
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify search button is present on the field.", groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"},
            dataProvider = "loginData", priority = 181)
    public void VerifySearchButtonPresence(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifySearchButtonPresence();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies search functionality
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify search is functional and generating the correct result for correct keywords or not by adding valid search input"+"Check the total client record count displayed same on the whole page ",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 182)
    public void VerifySearchFunctionality(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifySearchFunctionality();
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
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 183)
    public void VerifySearchFunctionalityViaKeyboard(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifySearchFunctionalityViaKeyboard();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies invalid name search
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify an message display by entering invalid keywords in the search field and clicking the search button"+"Check if the Search options are visible or not for 0 search results",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 184)
    public void VerifyInvalidNameSearch(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifyInvalidNameSearch();
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
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 185)
    public void VerifyAppliedSearchAfterNavigation(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifyAppliedSearchAfterNavigation();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies reset option on search function
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Ensure the Search box has Reset option.", groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"},
            dataProvider = "loginData", priority = 186)
    public void VerifyResetOptionOnSearchFunction(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifyResetOptionOnSearchFunction();
        }
        assertEquals(true, flag);
    }

    /////

    /**
     * This method verifies presence of add button
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Ensure the Add button is present and properly aligned on the page.", groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"},
            dataProvider = "loginData", priority = 187)
    public void VerifyPresenceOfAddButton(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifyPresenceOfAddButton();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies dialogue box for add button
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Ensure the Add button opens a dialogue box to add a new item.", groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"},
            dataProvider = "loginData", priority = 188)
    public void VerifyDialogueBoxForAddButton(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifyNavigationToAddPartPage();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies creating new part item
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Ensure a new item can be added using dialogue box and save "+"Ensure the Field Inputs present in the Add dialogue box."+
            "Ensure the Field Inputs present in the Add dialogue box."+"Ensure the dropdown menu is present on the page."+"Ensure an option can be selected from the dropdown menu."+
            "Ensure the dropdown menu can dynamically load options (if applicable)."+"Verify that the field data displays the query results correctly."+
            "Verify the confirmation message after the data are saved."+"To verify newly added part is displayed in the part listing page."+
            "To verify adding a new part by filling all the required fields and that newly created part details displayed in the part listing page should correctly match with given data."+
            "Ensure that the new item is displayed in the correct order in the listing section.", groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"},
            dataProvider = "loginData", priority = 189)
    public void VerifyCreatingNewPartItem(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifyCreatingNewPartItem();
            if(flag){
                flag = partsPage.deleteCreatedPartItem(PartsPageLocators.PART_CODE);
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies input validation for add dialogue
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Ensure the Add dialogue validates input correctly "+"Ensure the Part code has the correct inputs.- Part code should be numeric, string or alphanumeric", groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"},
            dataProvider = "loginData", priority = 190)
    public void VerifyInputValidationForAddDialogue(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifyInputValidationForAddDialogue();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies empty field error message
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Ensure the field inputs cannot be left empty.", groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"},
            dataProvider = "loginData", priority = 191)
    public void VerifyEmptyFieldErrorMessage(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifyEmptyFieldErrorMessage();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies space in input field
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Ensure the field Input doesnot accept space at first Character.", groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"},
            dataProvider = "loginData", priority = 192)
    public void VerifySpaceInInputField(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifySpaceInInputField();
            if(flag){
                flag = partsPage.deleteCreatedPartItem(PartsPageLocators.PART_CODE);
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies character limit for part code
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Ensure the Part code has the correct inputs.- Varcharacter of 50 limit.", groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"},
            dataProvider = "loginData", priority = 193)
    public void VerifyCharacterLimitForPartCode(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifyCharacterLimitForPartCode();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies creating duplicate item
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Ensure that the system handles attempts to add duplicate items in part Name"+
            "Ensure that the system handles attempts to add duplicate items.", groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"},
            dataProvider = "loginData", priority = 194)
    public void VerifyCreatingDuplicateItem(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifyCreatingDuplicateItem();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies input field of purchased cost
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Ensure the Purchased Cost has the correct inputs- Decimal of 10.2 limit.", groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"},
            dataProvider = "loginData", priority = 195)
    public void VerifyInputFieldOfPurchasedCost(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifyInputFieldOfPurchasedCost();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies cancel button on add dialogue box
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Ensure the Add dialogue box has cancel option.", groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"},
            dataProvider = "loginData", priority = 196)
    public void VerifyCancelButtonOnAddDialogueBox(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifyCancelButtonOnAddDialogueBox();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies part code value in listing page
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Verify whether the Part code value in listing screen records contains alpha numeric eg:RCP-20"+"Verify whether the Part Name value in listing screen records contains alpha numeric eg:RCP 80x50-315"+
            "Verify whether the Part category value in listing screen records contains variable character of 100 alpha. eg: Impeller",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 197)
    public void VerifyPartCodeValueInListingPage(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.createNewMaterialToVerifyListingPage();
            if(flag){
                flag = partsPage.deleteCreatedPartItem("RCP-20");
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies next and previous button on pagination
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify that the “NEXT” and “PREV” button is clickable on pagination", groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"},
            dataProvider = "loginData", priority = 198)
    public void VerifyNextAndPreviousButtonOnPagination(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = partsPage.verifyNextAndPreviousButtonOnPagination();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies default pagination count
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify that the default count of the pagination is displayed on the show box", groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"},
            dataProvider = "loginData", priority = 199)
    public void VerifyDefaultPaginationCount(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = partsPage.verifyDefaultPaginationCount();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies pagination disabled state
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify that when the user is on the last page then Next button should be disabled and previous arrow disabled in first page.", groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"},
            dataProvider = "loginData", priority = 200)
    public void VerifyPaginationDisabledState(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = partsPage.navigateToPartsPage();
            if(flag){
                flag = usersPage.verifyPaginationButtonDisableState();
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies rows per page count
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "To verify on selecting 10/25/50/100 rows per page, the selected number of rows is displayed per page.", groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"},
            dataProvider = "loginData", priority = 201)
    public void VerifyRowsPerPageCount(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = materialsPage.verifyRowsPerPageValues();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies edit page redirection
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Check that the page redirects to the edit page when the user clicks on the edit icon", groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"},
            dataProvider = "loginData", priority = 202)
    public void VerifyEditPageRedirection(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = partsPage.verifyEditPageRedirection();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies master menu items in parts page
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Verify the Master Menu created data's for these value should be displayed under these drop-down listing- Part Category, Material,Material Price, Process and Rate from the respective pages. "+
            "Verify the Master Menu newly added data's for these value should be displayed under these drop-down listing- Part Category, Material,Material Price, Process and Rate from the respective pages. ",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 203)
    public void VerifyMasterMenuItemsInPartsPage(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = partsPage.verifyMasterMenuItemsInMaterialsPage();
            if(flag){
                flag = partsPage.deleteCreatedPartItem(PartsPageLocators.PART_CODE);
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies edited master menu in parts page
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Verify the Master Menu newly edited data's for these value should be displayed under these drop-down listing- Part Category, Material,Material Price, Process and Rate from the respective pages. "+
            "Verify the Master Menu newly deleted data's for these value should be displayed under these drop-down listing- Part Category, Material,Material Price, Process and Rate from the respective pages. ",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 204)
    public void VerifyEditedMasterMenuInPartsPage(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = partsPage.verifyMasterMenuItemsInMaterialsPage();
            if(flag) {
                flag = partsPage.verifyEditedMasterMenuItem();
                if (flag) {
                    flag = partsPage.deleteCreatedPartItem(PartsPageLocators.PART_CODE);
                    if(flag){
                        flag = partsPage.restoreEditedPartCategory();
                    }
                }
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies is purchased status
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify whether the Is Purchased value in listing screen records contains Yes or No.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 205)
    public void VerifyIsPurchasedStatus(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = partsPage.verifyIsPurchasedStatus();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies purchased price decimal
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify whether the Is Purchased Price in listing screen records contains decimal int of 10.2 numeric. Eg: 2000.00"+
            "Verify whether the Is Process Price in listing screen records contains decimal int of 10.2 numeric. Eg: 2000.00",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 206)
    public void VerifyPurchasedPriceDecimal(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = partsPage.verifyPurchasedPriceDecimal();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies total price decimal
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify whether the Total Price in listing screen records contains numeric. Eg: 2000.00",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 207)
    public void VerifyTotalPriceDecimal(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = partsPage.verifyTotalPriceDecimal();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies created item after refresh
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Ensure that the new item persists and is displayed after a page refresh.",
            groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},dataProvider = "loginData", priority = 208)
    public void VerifyCreatedItemAfterRefresh(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifyCreatingNewPartItem();
            if(flag){
                webDB.navigateToRefresh();
                flag = partsPage.verifyCreatedPartItem(PartsPageLocators.PART_CODE);
                if(flag) {
                    flag = partsPage.deleteCreatedPartItem(PartsPageLocators.PART_CODE);
                }
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies initial state of navigation
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Verify that the initial state of the navigation is correct.", groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"},
            dataProvider = "loginData", priority = 209)
    public void VerifyInitialStateOfNavigation(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifyInitialStateOfNavigation();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies navigation button on add part
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Navigate to next, previous and save buttons and check its functionalities.", groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"},
            dataProvider = "loginData", priority = 210)
    public void VerifyNavigationButtonsOnAddPart(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifyNavigationButtonsOnAddPart();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies is purchased button function
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Verify whether clicking isPurchased 'On - Material weight & Process fields are hidden while Purchased Date, material selection & Purchased Price are enabled. When toggle the Is Purchased 'Off' - Vice versa should happen.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 211)
    public void VerifyIsPurchasedButtonFunction(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifyIsPurchasedButtonFunction();
        }
        assertEquals(true, flag);
    }

    /////

    /**
     * This method verifies filter section fields
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "verify whether the filter field has Part code and Material in search section",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 212)
    public void VerifyFilterSectionFields(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifyFilterSectionFields();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies filter search functionality
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "verify whether user able to search with particular part code and material and check the result of the filtered value",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 213)
    public void VerifyFilterSearchFunctionality(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifyFilterSearchFunctionality();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies dropdown options for add part
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Ensure all expected options are available in the dropdown menu.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 214)
    public void VerifyDropdownOptionsForAddPart(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifyDropdownOptionsForAddPart();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies empty dropdown error
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Ensure the box validates if a required dropdown menu option is not selected.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 215)
    public void VerifyEmptyDropdownError(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifyEmptyDropdownError();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies dropdown menu via keyboard
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Ensure the dropdown menu is accessible via keyboard navigation.",
            groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"}, dataProvider = "loginData", priority = 216)
    public void VerifyDropDownMenuViaKeyboard(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = partsPage.verifyDropDownMenuViaKeyboard();
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
    @AfterTest(groups = {"CompanyAdmin", "AdvancedUser", "AllUsers"})
    public void TearDown() {
        System.out.println("Tear down");
        webDB.tearDown();
    }

}
