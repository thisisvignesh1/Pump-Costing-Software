package testClass;

import org.testng.annotations.*;
import pageFunctions.CommonPage;
import pageFunctions.EditProfilePage;
import pageFunctions.LoginPage;
import utils.WebDriverBase;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.testng.Assert.assertEquals;

public class EditProfilePageTest {

    WebDriverBase webDB = new WebDriverBase();
    boolean flag;
    String endTime;
    String startTime;
    EditProfilePage editProfilePage = new EditProfilePage();
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
        } else if ("AdvancedUser".equalsIgnoreCase(user)) {
            return new Object[][]{
                    {webDB.getDataFromProperties("advancedUserUsername"), webDB.getDataFromProperties("advancedUserPassword")}
            };
        } else if ("NormalUser".equalsIgnoreCase(user)) {
            return new Object[][]{
                    {webDB.getDataFromProperties("normalUserUsername"), webDB.getDataFromProperties("normalUserPassword")}
            };
        } else if ("AllUsers".equalsIgnoreCase(user)) {
            return new Object[][]{
                    {webDB.getDataFromProperties("companyAdminUsername"), webDB.getDataFromProperties("companyAdminPassword")},
                    {webDB.getDataFromProperties("superAdminUsername"), webDB.getDataFromProperties("superAdminPassword")},
                    {webDB.getDataFromProperties("advancedUserUsername"), webDB.getDataFromProperties("advancedUserPassword")},
                    {webDB.getDataFromProperties("normalUserUsername"), webDB.getDataFromProperties("normalUserPassword")}
            };
        }
        return new Object[][]{};
    }

    /**
     * This method opens browser and enter url
     * @author Vignesh - GWL
     */
    //@BeforeTest(groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"})
    public void BrowserOpen() {
        webDB.Setup(System.getProperty("platformName"));
        startTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
        System.out.println("Test case started at" + " " + startTime);
    }

    /**
     * This method enters login page url
     * @author Vignesh - GWL
     */
    @BeforeMethod(groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"})
    public void before(){
        webDB.enterURL(webDB.getDataFromProperties("loginPageUrl"));
    }

    /**
     * This method verifies edit profile page redirection
     * @author Vignesh - GWL
     */
    @Test(description = "Verify the edit profile page is redirected properly when edit Profile button is clicked from dashboard page",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 26)
    public void VerifyEditProfilePageRedirection(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = editProfilePage.verifyEditProfilePageRedirection();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies edit profile page security
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify the edit profile URL is secured ",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 27)
    public void VerifyEditProfileUrlSecurityWithoutLogin(String email, String password) throws InterruptedException {
        flag = editProfilePage.verifyEditProfileUrlSecurity();
        if(flag){
            flag = loginPage.verifyValidLogin(email, password);
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies edit profile field componenets
     * @author Vignesh - GWL
     */
    @Test(description = "Check that basic profile requirement like - Name , Email address and Role. Showing or not",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 28)
    public void VerifyEditProfileFieldComponents(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = editProfilePage.verifyEditProfileFieldComponents();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies username profile updation
     * @author Vignesh - GWL
     */
    @Test(description = "Check that user able to update profile details like Profile photo , Name",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 29)
    public void VerifyUserNameProfileUpdate(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = editProfilePage.verifyUserNameProfileUpdate();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies updated details after refresh
     * @author Vignesh - GWL
     */
    @Test(description = "Check that updated details showing or not when user refresh the page",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 30)
    public void VerifyUpdatedDetailsAfterRefresh(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = editProfilePage.verifyUpdatedDetailsAfterRefresh();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies updated details after logout
     * @throws InterruptedException
     */
    @Test(description = "Verify that updated profile data should persist after re-login into an application",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 31)
    public void VerifyUpdatedDetailsAfterLogout(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = editProfilePage.verifyUpdatedDetailsAfterLogout(email, password);
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies mandatory fields mark
     * @author Vignesh - GWL
     */
    @Test(description = "Verify that all mandatory fields should be displayed in the red “*” mark",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 32)
    public void VerifyMandatoryFieldsMark(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = editProfilePage.verifyMandatoryFieldsMark();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies empty field error messages
     * @author Vignesh - GWL
     */
    @Test(description = "Verify that all validation messages should be displayed properly or not",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 33)
    public void VerifyEmptyFieldErrorMessages(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = editProfilePage.verifyEmptyFieldErrorMessages();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies save profile with invalid data
     * @author Vignesh - GWL
     */
    @Test(description = "Verify that the user should not be able to save a profile page with invalid data",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 34)
    public void VerifySaveProfileWithInvalidData(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = editProfilePage.verifySaveProfileWithInvalidData();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies cancel button functionality
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Check the cancel button functionality", dataProvider = "loginData",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, priority = 35)
    public void VerifyCancelButtonFunctionality(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = editProfilePage.verifyCancelButtonFunctionality();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies edited name on top right menu
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Check the Edited name is updated properly on the Top right corner in menu ",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 36)
    public void VerifyEditedNameOnTopRightMenu(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = editProfilePage.verifyEditedNameOnTopRightMenu();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies updating profile with revert back
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "After updating the profile for several time do the revert back or cancel options works without modifying any value.",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 37)
    public void VerifyUpdatingProfileWithRevertBack(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = editProfilePage.verifyUpdatingProfileWithRevertBack();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies edit profile page in new tab
     * @author Vignesh - GWL
     */
    @Test(description = "Check whether the edit profile can be opened in new tab as separate screen and that should be secured.",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 38)
    public void VerifyEditProfilePageInNewTab(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = editProfilePage.verifyEditProfilePageInNewTab();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies modified profile in other pages
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify whether the profile info is displaying all pages when changed",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 39)
    public void VerifyModifiedProfileInOtherPages(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = editProfilePage.verifyModifiedProfileInOtherPages();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies email address in profile edit page
     * @author Vignesh - GWL
     */
    @Test(description = "Verify whether the email address given while registration appears here in the profile page.(automated generation)",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"},dataProvider = "loginData", priority = 40)
    public void VerifyEmailAddressInProfileEditPage(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = editProfilePage.verifyEmailAddressInProfileEditPage();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies same username and email error
     * @author Vignesh - GWL
     */
    @Test(description = "What happens if both name and Email address given are same.",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 41)
    public void VerifySameUsernameAndEmailError(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = editProfilePage.verifySameUsernameAndEmailError();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies clickable links on edit profile
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Need to verify all the clickable links are working or not",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 42)
    public void VerifyClickableLinksOnEditProfile(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = editProfilePage.verifyClickableLinksOnEditProfile();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies success message on update
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "After successful update - do we get the success notification with auto hide and to which page it is redirecting",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 43)
    public void VerifySuccessMessageOnUpdate(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = editProfilePage.verifyUserNameProfileUpdate();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies non-editable fields
     * @author Vignesh - GWL
     */
    @Test(description = "Check if the user can edit the non-editable fields.",
            groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser","AllUsers"}, dataProvider = "loginData", priority = 44)
    public void VerifyNonEditableFields(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = editProfilePage.verifyNonEditableFields();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies updated details after saved
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Check that updated details showing or not after saved.",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 45)
    public void VerifyUpdatedDetailsAfterSaved(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = editProfilePage.verifyUserNameProfileUpdate();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies logout after updated details
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Check whether logout after that updated details.",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 46)
    public void VerifyLogoutAfterUpdatedDetails(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = editProfilePage.verifyLogoutAfterUpdatedDetails();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies confirmation message after update
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify the confirmation message after the Profile info has been updated.",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 47)
    public void VerifyConfirmationMessageAfterUpdate(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = editProfilePage.verifyUserNameProfileUpdate();
        }
        assertEquals(true, flag);
    }

    /**
     * This is after method verification
     * @author Vignesh - GWL
     */
    @AfterMethod(groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"})
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
    //@AfterTest(groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"})
    public void TearDown() {
        System.out.println("Tear down");
        webDB.tearDown();
    }
}
