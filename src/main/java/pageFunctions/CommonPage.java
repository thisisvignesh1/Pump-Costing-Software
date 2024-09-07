package pageFunctions;

import locators.EditProfilePageLocators;
import locators.HomePageLocators;
import locators.LoginPageLocators;
import com.opencsv.exceptions.CsvValidationException;
import org.openqa.selenium.JavascriptExecutor;
import utils.ReportLoger;
import utils.WebDriverBase;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import utils.WebDriverBase.ElementType;

public class CommonPage {

    WebDriverBase webDB = new WebDriverBase();
    ReportLoger log = new ReportLoger();
    boolean flag;
    String parentWindow;
    Set<String> windows;
    String childWindow;

    /**
     * This method clears local storage
     * @author Gokul - GWL
     */
    public void clearLocalStorage() {
        JavascriptExecutor jse = (JavascriptExecutor) webDB.getDriver();
        jse.executeScript("window.localStorage.clear();");
        System.out.println("local storage cleared");
        webDB.getDriver().navigate().refresh();
    }

    /**
     * This method clears cookies
     * @author Gokul - GWL
     */
    public void clearCookies(){
        webDB.getDriver().manage().deleteCookieNamed("cookie");
        webDB.getDriver().navigate().refresh();
    }

    /**
     * This method verifies logout
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyLogout() {
//        flag = webDB.isElementDisplayed("//div[@role='alert']/following-sibling::button", ElementType.Xpath);
//        if(flag){
//            flag = webDB.clickAnElement("//div[@role='alert']/following-sibling::button", ElementType.Xpath);
//        }
        flag = webDB.waitForElement(HomePageLocators.PROFILE_DROPDOWN, WebDriverBase.ElementType.Id);
        if(flag){
            flag = webDB.moveToElement(HomePageLocators.PROFILE_DROPDOWN, WebDriverBase.ElementType.Id);
            if(flag) {
                flag = webDB.clickAnElement(HomePageLocators.PROFILE_DROPDOWN, WebDriverBase.ElementType.Id);
                if (flag) {
                    flag = webDB.waitForElement(HomePageLocators.PROFILE_DROPDOWN_LOGOUT, ElementType.LinkText);
                    if (flag) {
                        flag = webDB.clickAnElement(HomePageLocators.PROFILE_DROPDOWN_LOGOUT, WebDriverBase.ElementType.LinkText);
                        if (flag) {
                            flag = webDB.waitForElement(LoginPageLocators.LOGIN_HEADING, WebDriverBase.ElementType.Xpath);
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method closes all child windows
     * @author Gokul - GWL
     */
    public void closeAllChildWindows() {
        parentWindow = webDB.getDriver().getWindowHandle();
        windows = webDB.getDriver().getWindowHandles();
        Iterator<String> iterator = windows.iterator();
        while (iterator.hasNext()) {
            childWindow = iterator.next();
            if (!parentWindow.equals(childWindow)) {
                webDB.getDriver().switchTo().window(childWindow);
                webDB.getDriver().close();

            }
        }
        webDB.getDriver().switchTo().window(parentWindow);
    }

    /**
     * This method reads password from csv file
     * @author Gokul - GWL
     * @param filePath
     * @return list of password
     */
    public static List<String> readPasswordFromCsv(String filePath) {
        List<String> passwords = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                passwords.add(values[0]);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return passwords;
    }

    /**
     * This method verifies navigate back after logout
     * @author Gokul - GWL
     * @return boolean flag
     * @throws InterruptedException
     * Used thread sleep time to ensure smooth navigation
     */
    public boolean verifyNavigateBackAfterLogout() throws InterruptedException {
        flag = verifyLogout();
        if(flag){
            Thread.sleep(1000);
            webDB.getDriver().navigate().back();
            flag = webDB.waitForElement(LoginPageLocators.LOGIN_HEADING, ElementType.Xpath);
            if(flag){
                log.logging("info", "After clicking back, the page still is in login page");
            }else{
                log.logging("fail", "Issue on navigating back");
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies logout from profile page
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyLogoutFromProfilePage() {
        flag = webDB.waitForElement(HomePageLocators.PROFILE_DROPDOWN, ElementType.Id);
        if(flag){
            flag = webDB.clickAnElement(HomePageLocators.PROFILE_DROPDOWN, ElementType.Id);
            if(flag){
                flag = webDB.waitForElement(HomePageLocators.PROFILE_DROPDOWN_EDIT_PROFILE, ElementType.LinkText);
                if(flag){
                    flag = webDB.clickAnElement(HomePageLocators.PROFILE_DROPDOWN_EDIT_PROFILE, ElementType.LinkText);
                    if(flag) {
                        log.logging("info", "Clicked edit profile option");
                        flag = webDB.waitForElement(EditProfilePageLocators.EDIT_PROFILE_HEADING, ElementType.Xpath);
                        if(flag) {
                            log.logging("info", "Landed on profile edit page");
                            flag = verifyLogout();
                            if(flag){
                                log.logging("info", "Logged out from profile page");
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies logout precedence
     * @author Gokul - GWL
     * @return boolean flag
     * Added thread sleep time to avoid issue while verification
     */
    public boolean verifyLogoutPrecedence() throws InterruptedException {
        flag = webDB.waitForElement(HomePageLocators.PROFILE_DROPDOWN, ElementType.Id);
        if(flag){
            flag = webDB.clickAnElement(HomePageLocators.PROFILE_DROPDOWN, ElementType.Id);
            if(flag){
                log.logging("info", "Clicked profile dropdown");
                flag = webDB.waitForElement(HomePageLocators.PROFILE_DROPDOWN_LOGOUT, ElementType.LinkText);
                if(flag){
                    flag = webDB.clickAnElement(HomePageLocators.PROFILE_DROPDOWN_LOGOUT, ElementType.LinkText);
                    if(flag){
                        log.logging("info", "Clicked logout button");
                        flag = webDB.navigateToUrl("https://costing.pumpsearch.com/users/editprofile");
                        if(flag){
                            log.logging("info", "Concurrently navigating to edit profile page");
                            Thread.sleep(1000);
                            flag = webDB.waitForElement(LoginPageLocators.LOGIN_HEADING, ElementType.Xpath);
                            if(flag){
                                log.logging("info", "Logout option took precedence over navigating to another page");
                            }else{
                                log.logging("fail", "logout haven't took precedence");
                                flag = false;
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }
}
