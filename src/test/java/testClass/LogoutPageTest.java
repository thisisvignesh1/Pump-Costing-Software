package testClass;

import org.testng.annotations.*;
import pageFunctions.CommonPage;
import pageFunctions.LoginPage;
import utils.WebDriverBase;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.testng.Assert.assertEquals;

public class LogoutPageTest {

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
        }else if("SuperAdmin".equalsIgnoreCase(user)) {
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
    //@BeforeTest(groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"})
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
     * This method verifies valid logout
     * @author Vignesh - GWL
     */
    @Test(description = "Verify that the user is successfully logged out and redirected to the login page.",
            groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 21)
    public void VerifyValidLogout(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = commonPage.verifyLogout();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies navigate back after logout
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "Test if the user can still access restricted pages or perform actions after logging out.",
            groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 22)
    public void VerifyNavigateBackAfterLogout(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = commonPage.verifyNavigateBackAfterLogout();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies logout from different pages
     * @author Vignesh - GWL
     */
    @Test(description = "Attempt to logout from various pages within the application (e.g., dashboard, profile, settings).",
            groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 23)
    public void VerifyLogoutFromDifferentPages(String email, String password) {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = commonPage.verifyLogoutFromProfilePage();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies access to page after logout
     * @author Vignesh - GWL
     * @throws InterruptedException
     */
    @Test(description = "After logging out, try to access a protected page by typing the URL directly.",
            groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 24)
    public void VerifyAccessPageAfterLogout(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = loginPage.verifyAccessPageAfterLogout();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies logout precedence
     * @author Vignesh - GWL
     */
    @Test(description = "Click the logout button while simultaneously navigating to another page.",
            groups = {"CompanyAdmin","SuperAdmin","AdvancedUser","NormalUser", "AllUsers"}, dataProvider = "loginData", priority = 25)
    public void VerifyLogoutPrecedence(String email, String password) throws InterruptedException {
        flag = loginPage.verifyValidLogin(email, password);
        if(flag){
            flag = commonPage.verifyLogoutPrecedence();
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
