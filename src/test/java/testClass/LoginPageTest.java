package testClass;

import org.testng.annotations.*;
import pageFunctions.CommonPage;
import pageFunctions.LoginPage;
import utils.WebDriverBase;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.testng.Assert.assertEquals;

public class LoginPageTest {

    WebDriverBase webDB = new WebDriverBase();
    boolean flag;
    String endTime;
    String startTime;
    LoginPage loginPage = new LoginPage();
    CommonPage commonPage = new CommonPage();

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
    @BeforeTest(groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"})
    public void BrowserOpen() {
        webDB.Setup(System.getProperty("platformName"));
        startTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
        System.out.println("Test case started at" + " " + startTime);
    }

    /**
     * This method enters login page url
     * @author Vignesh - GWL
     */
    @BeforeMethod(groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"})
    public void before(){
        webDB.enterURL(webDB.getDataFromProperties("loginPageUrl"));
    }

    /**
     * This method verifies valid login
     * @author Vignesh - GWL
     */
    @Test(description = "Verifying Open Forum URL: https://pump_costing.galaxyweblinks.com/signin",
            groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 1)
    public void VerifySuccessfulLogin(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = commonPage.verifyLogout();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies all links in the login page
     * @author Vignesh - GWL
     */
    @Test(description = "Verifying all the Clickable links on Login page",
            groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"}, priority = 2)
    public void VerifyLoginPageLinks() {
        flag = loginPage.verifyLoginPageLinks();
        assertEquals(true, flag);
    }

    /**
     * This method verifies email and password field is empty and login btn is enabled
     * @author Vignesh - GWL
     */
    @Test(description = "Verifying Login page up >> Email address and password text boxes are empty and \"Signin\" Button should be enabled",
            groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"}, priority = 3)
    public void VerifyFieldsEmptyAndEnabled() {
        flag = loginPage.verifyEmailAndPasswordFieldEmptyAndEnabled();
        assertEquals(true, flag);
    }

    /**
     * This method verifies login page to dashboard navigation
     * @author Vignesh - GWL
     */
    @Test(description = "Verifying Login page up with valid email id and password "+"Verify the Password field with valid password. "+
            "Verify Password field accepts Special characters. "+"Verifying Login page up, Sign in links are hidden after success login "+
            "Verify that when the user login into the system without selecting Save credentials checkbox.",
            groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 4)
    public void VerifyDashboardNavigation(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = commonPage.verifyLogout();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies different invalid email error message
     * @author Vignesh - GWL
     * @throws IOException
     * @throws InterruptedException
     */
    @Test(description = "Verify with an email id with IP address as its Domain. "+"Verify an invalid email id with combination of Special Characters. "+
            "Verify with an invalid Email id without (@) Symbol . "+"Verify with an invalid Email id with more number of (.) character. "+"Verify that Email Field accepts only alphabetic characters. "+
            "Verify that Email Field accepts numeric characters. "+"Verify that Email Field accepts special characters. "+"Verify that Email Field accepts space as its middle character."+
            "Verify that the email field accepts Email address "+"Ensure the system rejects duplicate email addresses during sign-in.",
            groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"}, priority = 5)
    public void VerifyInvalidEmailError() throws IOException, InterruptedException {
        flag = loginPage.verifyErrorMessageForInvalidEmailId();
        assertEquals(true, flag);
    }

    /**
     * This method verifies space as first character in email field
     * @author Vignesh - GWL
     */
    @Test(description = "Verify Email Field accepts space as its first character.",
            groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"}, priority = 6)
    public void VerifySpaceFirstInEmailField() {
        flag = loginPage.verifyAcceptingSpaceInFrontEmailField();
        if(flag){
            commonPage.verifyLogout();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies space as last character in email field
     * @author Vignesh - GWL
     */
    @Test(description = "Verify Email Field accepts space as its last character.",
            groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"}, priority = 7)
    public void VerifySpaceLastInEmailField() {
        flag = loginPage.verifyAcceptingSpaceInLastEmailField();
        if(flag){
            commonPage.verifyLogout();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies empty email field error message
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify Email field when it is submitted when no value is entered.",
            groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"}, priority = 8)
    public void VerifyEmptyEmailError() throws InterruptedException {
        flag = loginPage.verifyEmptyEmailErrorMessage();
        assertEquals(true, flag);
    }

    /**
     * This method verifies empty email and password error
     * @author Vignesh - GWL
     */
    @Test(description = "Verify Password field when it is submitted when no value is entered. "+"Verifying Login page up without Entering any inputs",
            groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"}, priority = 9)
    public void VerifyEmptyEmailAndPwdError() {
        flag = loginPage.verifyEmptyEmailPasswordError();
        assertEquals(true, flag);
    }

    /**
     * This method verifies password with less than six characters
     * @author Vignesh - GWL
     */
    @Test(description = "Verifying the Password field with less then 6 characters",
            groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"}, priority = 10)
    public void VerifyPwdLessThanSixChars() throws InterruptedException {
        flag = loginPage.verifyPasswordWithLessThanSixChars();
        assertEquals(true, flag);
    }

    /**
     * This method verifies error message for invalid passwords
     * @author Vignesh - GWL
     */
    @Test(description = "Verify Password field with Space as middle character. "+"Verify Password field accepts password in Uppercase. "+
            "Verify Password field accepts password in lowercase. "+"Verify the Password field with invalid password. "+"Verify Password field accepts alphabetic characters. "+
            "Verify Password field accepts numeric character. "+"Verify Password field with Space as first character.",
            groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"}, priority = 11)
    public void VerifyErrorForInvalidPwd() throws InterruptedException {
        flag = loginPage.verifyErrorMessageForInvalidPasswords();
        assertEquals(true, flag);
    }

    /**
     * This method verifies space in last character in password
     * @author Vignesh - GWL
     */
    @Test(description = "Verify Password field with Space as last character.",
            groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"}, priority = 12)
    public void VerifySpaceAsLastPwdCharacter() {
        flag = loginPage.verifyAcceptingSpaceInLastPwdField();
        if(flag){
            commonPage.verifyLogout();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies show password option
     * @author Vignesh - GWL
     */
    @Test(description = "Verify on clicking the show password option near the password field.",
            groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 13)
    public void VerifyShowPwdOption(String email, String password) throws InterruptedException {
        flag = loginPage.verifyShowPasswordOption(email, password);
        if(flag){
            commonPage.verifyLogout();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies login page with invalid email and password
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verifying the Login page up with invalid Email address and password "+"Verifying login functionality with unregistered email id and Password",
            groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"}, priority = 14)
    public void VerifyLoginWithInvalidEmailPwd() throws InterruptedException {
        flag = loginPage.verifyLoginPageWithInvalidEmailPwd();
        assertEquals(true, flag);
    }

    /**
     * This method verifies error message for empty password
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verifying Login page up with SQL injection",
            groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"}, priority = 15)
    public void VerifyLoginWithSqlInject() throws InterruptedException {
        flag = loginPage.verifyLoginPageUpWithSqlInjection();
        assertEquals(true, flag);
    }

    /**
     * This method verifies save credentials option
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify \" Save credentials\" password has been autosaved and appear default in next login",
            groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 16)
    public void VerifySaveCredentialsOption(String email, String password) throws InterruptedException {
        flag = loginPage.verifySaveCredentialsOption(email, password);
        if(flag){
            commonPage.verifyLogout();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies save credentials disable state
     * @author Vignesh - GWL
     */
    @Test(description = "Verify the Save credentials field is disabled",
            groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"}, priority = 17)
    public void VerifySaveCredentialsDisable() {
        flag = loginPage.verifySaveCredentialsDisableState();
        assertEquals(true, flag);
    }

    /**
     * This method verifies save credentials unchecked
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify whether the user visits again and unchecked the Save credentials checkbox.",
            groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"}, priority = 18)
    public void VerifySaveCredentialsUnchecked() throws InterruptedException {
        flag = loginPage.verifySaveCredentialsUnchecked();
        assertEquals(true, flag);
    }

    /**
     * This method verifies eye icon security
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify the Password is hidden again for security reasons once viewed the eye icon.",
            groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"}, priority = 19)
    public void VerifyEyeIconSecurity() throws InterruptedException {
        flag = loginPage.verifyEyeIconSecurity();
        assertEquals(true, flag);
    }

    /**
     * This method verifies back button in browser
     * @author Vignesh - GWL
     */
    @Test(description = "Verifying the Back button in browser",
            groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 20)
    public void VerifyBackButtonInBrowser(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = commonPage.verifyLogout();
            if (flag) {
                flag = loginPage.verifyBackButtonInBrowser();
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This is after method verification
     * @author Vignesh - GWL
     */
    @AfterMethod(groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"})
    public void VerifyAfterMethod() {
        commonPage.clearCookies();
        webDB.navigateToRefresh();
        endTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    /**
     * This method closes the browser
     * @author Vignesh - GWL
     */
    //@AfterTest(groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"})
    public void TearDown() {
        System.out.println("Tear down");
        webDB.tearDown();
    }
}
