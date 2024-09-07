package testClass;

import org.testng.annotations.*;
import pageFunctions.*;
import utils.Mailer;
import utils.WebDriverBase;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.testng.Assert.assertEquals;

public class PartCategoriesTest {

    WebDriverBase webDB = new WebDriverBase();
    boolean flag;
    String endTime;
    String startTime;
    PartCategoriesPage partCategoriesPage = new PartCategoriesPage();
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
     * This method verifies part categories page url
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Verify the Client URL is secured ", groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},
            dataProvider = "loginData", priority = 217)
    public void VerifyPartCategoriesPageUrl(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = partCategoriesPage.verifyPartCategoriesPageUrl();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies part categories page title
     * @author Vignesh - GWL
     * @param email
     * @param password
     */
    @Test(description = "Ensure the page title is correct and reflects the content of the page.", groups = {"CompanyAdmin", "AllUsers", "SuperAdmin"},
            dataProvider = "loginData", priority = 218)
    public void VerifyPartCategoriesPageTitle(String email, String password){
        flag = loginPage.verifyValidLogin(email, password);
        if(flag) {
            flag = partCategoriesPage.verifyPartCategoriesPageViaNavigationPanel();
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

        /**
     * This method sends report via email
     * @author Vignesh - GWL
     * @throws Exception
     */
    @AfterSuite(groups = {"CompanyAdmin","SuperAdmin", "NormalUser", "AdvancedUser", "AllUsers"})
    public void SendMail() throws Exception {
		Mailer mailer = new Mailer();
		mailer = new Mailer();
		mailer.execute("Pump Costing Software");
    }


}
