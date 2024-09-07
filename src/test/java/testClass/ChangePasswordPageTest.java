package testClass;

import org.testng.annotations.*;
import pageFunctions.ChangePasswordPage;
import pageFunctions.CommonPage;
import pageFunctions.LoginPage;
import utils.Mailer;
import utils.WebDriverBase;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.testng.Assert.assertEquals;

public class ChangePasswordPageTest {

    WebDriverBase webDB = new WebDriverBase();
    boolean flag;
    String endTime;
    String startTime;
    ChangePasswordPage changePasswordPage = new ChangePasswordPage();
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
     * This method verifies change password page redirection
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verifying URL on the address bar", groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"},
            dataProvider = "loginData", priority = 99)
    public void VerifyChangePasswordPageRedirection(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = changePasswordPage.verifyChangePasswordPageRedirection();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies change password url security
     * @author Vignesh  GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Verify the Change password URL is secured ", groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"},
            dataProvider = "loginData", priority = 100)
    public void VerifyChangePasswordUrlSecurity(String email, String password) throws InterruptedException {
        flag = changePasswordPage.verifyChangePasswordUrlSecurity();
        if(flag){
            flag = loginPage.verifyValidLogin(email, password);
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies placeholder for all fields
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify all the fields in the change password page contain a placeholder.", groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"},
            dataProvider = "loginData", priority = 101)
    public void VerifyAllFieldsHavePlaceholder(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = changePasswordPage.verifyAllFieldsHavePlaceholder();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies new password maximum character limit
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify New password has maximum character limit", groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"},
            dataProvider = "loginData", priority = 102)
    public void VerifyNewPasswordMaxCharacterLimit(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = changePasswordPage.verifyNewPasswordMaxCharacterLimit(password);
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies new password minimum character limit
     * @author Vignesh - GWL
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Verify New password has a minimum character limit"+"Verifying the error message getting displayed",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 103)
    public void VerifyNewPasswordMinCharacterLimit(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = changePasswordPage.verifyNewPasswordMinCharacterLimit(password);
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies error message for empty fields
     * @param email
     * @param password
     * @throws InterruptedException
     */
    @Test(description = "Verifying the error message getting displayed for leaving the mandatory fields empty "+"Verifying change password functionality by leaving all three field such as old password, confirm password and new password field blank",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 104)
    public void VerifyErrorMessageForEmptyFields(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = changePasswordPage.VerifyErrorMessageForEmptyFields();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies invalid current password error
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verifying the color of error message for invalid current password.",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 105)
    public void VerifyInvalidCurrentPasswordError(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = changePasswordPage.verifyInvalidCurrentPasswordError();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies change password functionality
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verifying change password functionality by entering valid old password and matching new password and confirm password"+
            "Enter the mixed password (Alphabet, Number, Special symbol) to check the functionality."+"Check whether the confirmation message displays after the password has been changed successfully."+
            "Login after resetting the password and see if you can successfully log in."+"Verify the confirmation message after the password has been changed.",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 106)
    public void VerifyChangePasswordFunctionality(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = changePasswordPage.verifyChangePasswordFunctionality(email, password);
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies change password with invalid old password
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verifying change password functionality by entering an invalid old password and matching the new password and confirming password",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 107)
    public void VerifyChangePwdWithInvalidOldPwd(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = changePasswordPage.verifyChangePwdWithInvalidOldPwd();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies change password with empty confirm password field
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verifying change password functionality by entering valid old password and new password and leaving blank the confirm password field",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 108)
    public void VerifyChangePwdWithEmptyConfirmPwd(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = changePasswordPage.verifyChangePwdWithEmptyConfirmPwd(password);
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies change password with empty new password
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verifying change password functionality by entering valid old password and confirm password and leaving blank the new password field",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 109)
    public void VerifyChangePwdWithEmptyNewPwd(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = changePasswordPage.verifyChangePwdWithEmptyNewPwd(password);
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies error for empty new and confirm password field
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verifying change password functionality by entering valid old passport and leaving both confirm password and new password field blank"+
            "Check the alert message when the user enters the wrong password.",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 110)
    public void VerifyErrorForEmptyNewAndConfirmPwd(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = changePasswordPage.verifyErrorForEmptyNewAndConfirmPwd(password);
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies error for space in new and confirm password
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verifying change password functionality by entering valid old password and entering only space in both confirm password and new password field.",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 111)
    public void VerifyErrorForSpaceInNewAndConfirmPwd(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = changePasswordPage.verifyErrorForSpaceInNewAndConfirmPwd(password);
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies error for new and confirm password length
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verifying change password functionality by entering more characters in new and confirm password field",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 112)
    public void VerifyErrorForNewAndConfirmPwdLength(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = changePasswordPage.verifyErrorForNewAndConfirmPwdLength(password);
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies star symbol for mandatory fields
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify the mandatory password fields are marked with a red star “*” symbol",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 113)
    public void VerifyStarSymbolOnMandatoryFields(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = changePasswordPage.verifyStarSymbolOnMandatoryFields();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies change password button is clickable
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Check whether the change password button is Clickable or not.",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 114)
    public void VerifyChangePasswordButtonIsClickable(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = changePasswordPage.verifyChangePasswordButtonIsClickable();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies error message for not entering mixed password
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Check the alert message for the mixed password when the user clicks on the change button without entering the mixed password if it is mandatory.",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 115)
    public void VerifyErrorMessageForNotEnteringMixedPassword(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = changePasswordPage.verifyErrorMessageForNotEnteringMixedPassword(password);
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies issue while login with old credentials
     * @param email
     * @param password
     */
    @Test(description = "Verify the User is not able to Login to application with old password once new password is updated",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 116)
    public void VerifyIssueWhileLoginWithOldCredentials(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = changePasswordPage.verifyIssueWhileLoginWithOldCredentials(email, password);
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies password mismatch error
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verifying change password functionality by entering an valid old password and different new password and different confirming password",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 117)
    public void VerifyPasswordMismatchError(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = changePasswordPage.verifyPasswordMismatchError(password);
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies password field accepts alphabet
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify Password field accepts alphabetic characters."+"Verify Password field accepts numeric characters."+
            "Verify Password field accepts Special characters."+"Verify password is accepted in Uppercase."+"Verify password is accepted in lowercase.",
            groups = {"CompanyAdmin","SuperAdmin", "AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 118)
    public void VerifyPasswordFieldAcceptance(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = changePasswordPage.verifyPasswordFieldAcceptance();
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
