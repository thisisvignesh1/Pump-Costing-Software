package pageFunctions;

import locators.*;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;
import java.util.Collections;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsersPage {

    WebDriverBase webDB = new WebDriverBase();
    ReportLoger log = new ReportLoger();
    PartsPage partsPage = new PartsPage();
    EditProfilePage editProfilePage = new EditProfilePage();
    PumpsPage pumpsPage = new PumpsPage();
    ExcelUtils excelUtils = new ExcelUtils();
    boolean flag;
    String editedName;

    /**
     * This method verifies url is secured
     * @return boolean flag
     * @author Vignesh - GWL
     */
    public boolean verifyClientUrlIsSecured() {
        flag = webDB.navigateToUrl(webDB.getDataFromProperties("usersPageUrl"));
        if (flag) {
            log.logging("info", "Entering users page url");
            flag = webDB.isElementNotDisplayed(UsersPageLocators.USERS_TITLE, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Users page url is secured as expected");
            }
        }
        return flag;
    }

    /**
     * This method verifies users page navigation
     * @return boolean flag
     * @author Vignesh - GWL
     */
    public boolean verifyUsersPageNavigation() {
        flag = webDB.waitForElement(UsersPageLocators.USERS_ICON, ElementType.Xpath);
        if (flag) {
            flag = webDB.moveToElement(UsersPageLocators.USERS_ICON, ElementType.Xpath);
            if (flag) {
                flag = webDB.javaScriptClickAnElement(UsersPageLocators.USERS_ICON, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Clicked users option from side menu");
                    flag = webDB.waitForElement(UsersPageLocators.USERS_TITLE, ElementType.Xpath);
                    if (flag) {
                        flag = verifyCloseNavigationBar();
                        if(flag) {
                            log.logging("info", "Landed on users page");
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies users page url
     * @return boolean flag
     * @author Vignesh - GWL
     */
    public boolean verifyUsersPageUrl() {
        flag = verifyUsersPageNavigation();
        if (flag) {
            String url = webDB.getDriver().getCurrentUrl();
            log.logging("info", "Extracted url is: " + url);
            if (url.equals(webDB.getDataFromProperties("usersPageUrl"))) {
                log.logging("info", "Url matches with the actual url");
            } else {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies first user delete popup
     *
     * @return boolean flag
     * @author Vignesh - GWL
     */
    public boolean verifyFirstUserDeletePopupAndClose() throws InterruptedException {
        flag = webDB.waitForElement(UsersPageLocators.TABLE_FIRST_ROW_CHECKBOX, ElementType.Xpath);
        if (flag) {
            flag = webDB.javaScriptClickAnElement(UsersPageLocators.TABLE_FIRST_ROW_CHECKBOX, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Clicked table first row checkbox");
                flag = webDB.waitForElement(UsersPageLocators.DELETE_BUTTON, ElementType.Xpath);
                if (flag) {
                    flag = webDB.javaScriptClickAnElement(UsersPageLocators.DELETE_BUTTON, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Clicked delete button");
                        flag = webDB.waitForElement(UsersPageLocators.DELETE_POPUP_TITLE, ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Delete popup is visible as expected.");
                            //flag = verifyPopupClose();
                            flag = verifyDeletePopupClose();
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies add popup and close
     *
     * @return boolean flag
     * @author Vignesh - GWL
     */
    public boolean verifyAddPopupAndClose() throws InterruptedException {
        flag = webDB.waitForElement(UsersPageLocators.ADD_BUTTON, ElementType.Xpath);
        if (flag) {
            flag = webDB.clickAnElement(UsersPageLocators.ADD_BUTTON, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Clicked add button");
                Thread.sleep(2000);
                flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_EMAIL_FIELD, ElementType.Id);
                if (flag) {
                    log.logging("info", "Add user popup is visible");
                    //flag = verifyPopupClose();
                    flag = verifyDeletePopupClose();
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies popup close
     *
     * @return boolean flag
     * Here thread sleep time is used to enhance the consistency of clicking svg element close icon
     * @author Vignesh - GWL
     */
    public boolean verifyPopupClose() throws InterruptedException {
        flag = webDB.waitForElement(UsersPageLocators.POPUP_CLOSE_ICON, ElementType.Xpath);
        if (flag) {
            flag = webDB.waitForClickElement(UsersPageLocators.POPUP_CLOSE_ICON, ElementType.Xpath);
            if (flag) {
                Thread.sleep(1000);
                //flag = webDB.clickAnElement(UsersPageLocators.POPUP_CLOSE_ICON, ElementType.Xpath);
                flag = webDB.javaScriptClickAnElement(UsersPageLocators.POPUP_CLOSE_ICON, ElementType.Xpath);
                if(flag) {
                    log.logging("info", "Clicked popup close icon");
                    Thread.sleep(1000);
                    flag = webDB.isElementNotDisplayed(UsersPageLocators.POPUP_CLOSE_ICON, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Verified close popup");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies filter button
     *
     * @return boolean flag
     * @author Vignesh - GWL
     */
    public boolean verifyFilterButton() {
        flag = webDB.waitForElement(UsersPageLocators.FILTER_BUTTON, ElementType.Xpath);
        if (flag) {
            flag = webDB.clickAnElement(UsersPageLocators.FILTER_BUTTON, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Clicked filter button");
                flag = webDB.waitForElement(UsersPageLocators.FILTER_SEARCH_BUTTON, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Filter button components are visible");
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies close navigation bar
     *
     * @return boolean flag
     * @author Vignesh - GWL
     */
    public boolean verifyCloseNavigationBar() {
        flag = webDB.waitForElement(HomePageLocators.SIDE_MENU_CLOSE_ICON, ElementType.Xpath);
        if (flag) {
            flag = webDB.clickAnElement(HomePageLocators.SIDE_MENU_CLOSE_ICON, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Clicked navigation close icon");
                flag = webDB.waitForElement(UsersPageLocators.HEADER_CHECK_BOX, ElementType.Id);
                if(flag){
                    log.logging("info", "Verified close icon on navigation bar");
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies delete popup close
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyDeletePopupClose() {
        flag = webDB.waitForElement(UsersPageLocators.DELETE_CANCEL_BTN, ElementType.Xpath);
        if(flag){
            flag = webDB.clickAnElement(UsersPageLocators.DELETE_CANCEL_BTN, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked cancel button");
                WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(UsersPageLocators.DELETE_CANCEL_BTN)));
            }
        }
        return flag;
    }

    /**
     * This method verifies first row delete button
     *
     * @return boolean flag
     * @author Vignesh - GWL
     */
    public boolean verifyFirstRowDeleteButton() {
        flag = webDB.waitForElement(UsersPageLocators.TABLE_FIRST_ROW_DELETE_ICON, ElementType.Xpath);
        if (flag) {
            flag = webDB.clickAnElement(UsersPageLocators.TABLE_FIRST_ROW_DELETE_ICON, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Clicked first row delete icon");
                flag = webDB.waitForElement(UsersPageLocators.DELETE_POPUP_TITLE, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Delete popup is visible");
                    //flag = verifyPopupClose();
                    flag = verifyDeletePopupClose();
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies click edit popup cancel
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean clickEditPopupCancel() {
        flag = webDB.waitForElement(UsersPageLocators.EDIT_USER_POPUP_CANCEL_BTN, ElementType.Xpath);
        if(flag){
            flag = webDB.clickAnElement(UsersPageLocators.EDIT_USER_POPUP_CANCEL_BTN, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked edit popup cancel button");
                WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(UsersPageLocators.EDIT_USER_POPUP_CANCEL_BTN)));
            }
        }
        return flag;
    }

    /**
     * This method verifies first row edit button
     *
     * @return boolean flag
     * @author Vignesh - GWL
     */
    public boolean verifyFirstRowEditButton() {
        flag = webDB.waitForElement(UsersPageLocators.TABLE_FIRST_ROW_EDIT_ICON, ElementType.Xpath);
        if (flag) {
            flag = webDB.clickAnElement(UsersPageLocators.TABLE_FIRST_ROW_EDIT_ICON, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Clicked first row edit icon");
                flag = webDB.waitForElement(UsersPageLocators.EDIT_USER_UPDATE_BTN, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Edit popup is visible");
                    //flag = verifyPopupClose();
                    flag = clickEditPopupCancel();
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies all links on users page
     *
     * @return boolean flag
     * @author Vignesh - GWL
     */
    public boolean verifyAllLinksOnUsersPage() throws InterruptedException {
        flag = verifyUsersPageNavigation();
        if (flag) {
            flag = verifyCloseNavigationBar();
            if (flag) {
                flag = verifyFirstUserDeletePopupAndClose();
                if (flag) {
                    flag = verifyAddPopupAndClose();
                    if (flag) {
                        flag = verifyFilterButton();
                        if (flag) {
                            flag = verifyFirstRowDeleteButton();
                            if (flag) {
                                flag = verifyFirstRowEditButton();
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies users page load time
     *
     * @return boolean flag
     * @author Vignesh - GWL
     */
    public boolean verifyUsersPageLoadTime() {
        flag = webDB.waitForElement(UsersPageLocators.USERS_ICON, ElementType.Xpath);
        if (flag) {
            flag = webDB.moveToElement(UsersPageLocators.USERS_ICON, ElementType.Xpath);
            if (flag) {
                flag = webDB.javaScriptClickAnElement(UsersPageLocators.USERS_ICON, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Clicked users option from side menu");
                    flag = webDB.waitForElement(UsersPageLocators.USERS_TITLE, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Users page load time is verified");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies filter text field
     *
     * @return boolean flag
     * @autor Vignesh - GWL
     */
    public boolean verifyFilterTextField() {
        flag = webDB.waitForElement(UsersPageLocators.FILTER_BUTTON, ElementType.Xpath);
        if (flag) {
            flag = webDB.clickAnElement(UsersPageLocators.FILTER_BUTTON, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Clicked filter button");
                flag = webDB.waitForElement(UsersPageLocators.FILER_NAME_FIELD, ElementType.Id);
                if (flag) {
                    String demoName = webDB.getDataFromProperties("normalUserUsername");
                    flag = webDB.sendTextToAnElement(UsersPageLocators.FILER_NAME_FIELD, demoName, ElementType.Id);
                    if (flag) {
                        log.logging("info", "Demo name has been sent to filter name field");
                        String value = webDB.getAttributeFromElement(UsersPageLocators.FILER_NAME_FIELD, ElementType.Id, "value");
                        if (value.equals(demoName)) {
                            log.logging("info", "Entered text has been verified");
                            flag = webDB.clearText(UsersPageLocators.FILER_NAME_FIELD, ElementType.Id);
                            if (flag) {
                                String afterClear = webDB.getAttributeFromElement(UsersPageLocators.FILER_NAME_FIELD, ElementType.Id, "value");
                                if(afterClear.isEmpty()) {
                                    log.logging("info", "Cleared text from name field");
                                }else{
                                    flag = false;
                                }
                            }
                        }
                    }
                }
            }

        }
        return flag;
    }

    /**
     * This method verifies edit popup text fields
     *
     * @return boolean flag
     * @author Vignesh - GWL
     */
    public boolean verifyEditPopupTextFields() throws InterruptedException {
        Thread.sleep(2000);
        flag = webDB.clearText(UsersPageLocators.ADD_USER_POPUP_FIRST_NAME_FIELD, ElementType.Id);
        if (flag) {
            log.logging("info", "Cleared first name field");
            flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_LAST_NAME_FIELD, ElementType.Id);
            if (flag) {
                flag = webDB.clearText(UsersPageLocators.ADD_USER_POPUP_LAST_NAME_FIELD, ElementType.Id);
                if (flag) {
                    log.logging("info", "Cleared last name field");
                    flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_EMAIL_FIELD, ElementType.Id);
                    if (flag) {
                        flag = webDB.clearText(UsersPageLocators.ADD_USER_POPUP_LAST_NAME_FIELD, ElementType.Id);
                        if (flag) {
                            log.logging("info", "All text fields are editable");
                            flag = webDB.waitForElement(UsersPageLocators.EDIT_USER_POPUP_CANCEL_BTN, ElementType.Xpath);
                            if (flag) {
                                flag = webDB.clickAnElement(UsersPageLocators.EDIT_USER_POPUP_CANCEL_BTN, ElementType.Xpath);
                                if(flag){
                                    WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(UsersPageLocators.EDIT_USER_POPUP_CANCEL_BTN)));
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies text fields are editable
     *
     * @return boolean flag
     * @author Vignesh - GWL
     */
    public boolean verifyTextFieldsAreEditable() throws InterruptedException {
        flag = verifyUsersPageNavigation();
        if (flag) {
            flag = verifyFilterTextField();
            if (flag) {
                flag = webDB.waitForElement(UsersPageLocators.TABLE_FIRST_ROW_EDIT_ICON, ElementType.Xpath);
                if (flag) {
                    flag = webDB.clickAnElement(UsersPageLocators.TABLE_FIRST_ROW_EDIT_ICON, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Clicked first row edit icon");
                        flag = webDB.waitForElement(UsersPageLocators.EDIT_USER_UPDATE_BTN, ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Edit popup is visible");
                            flag = verifyEditPopupTextFields();
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies navigation to other pages
     *
     * @return boolean flag
     * @author Vignesh - GWL
     */
    public boolean verifyNavigationToOtherPages(String email) {
        flag = verifyUsersPageNavigation();
        if (flag) {
            flag = editProfilePage.verifyEditProfilePageRedirection();
            if (flag) {
                if(email.equals(webDB.getDataFromProperties("superAdminUsername"))){
                    log.logging("info", "Navigation for edit profile page is verified");
                }else{
                    flag = partsPage.navigateToPartsPage();
                    if (flag) {
                        flag = pumpsPage.navigateToPumpsPage();
                        if (flag) {
                            log.logging("info", "Navigation for other pages are verified");
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies add popup
     *
     * @return boolean flag
     * @author Vignesh - GWL
     */
    public boolean verifyAddPopup() throws InterruptedException {
        flag = webDB.waitForElement(UsersPageLocators.ADD_BUTTON, ElementType.Xpath);
        if (flag) {
            flag = webDB.clickAnElement(UsersPageLocators.ADD_BUTTON, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Clicked add button");
                Thread.sleep(2000);
                flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_EMAIL_FIELD, ElementType.Id);
                if (flag) {
                    log.logging("info", "Add user popup is visible");
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies click add button and wait for popup
     *
     * @return boolean flag
     * @author Vignesh - GWL
     */
    public boolean clickAddButtonAndWaitForPopup() {
        flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_SAVE_BTN, ElementType.Xpath);
        if (flag) {
            flag = webDB.javaScriptClickAnElement(UsersPageLocators.ADD_USER_POPUP_SAVE_BTN, ElementType.Xpath);
            if (flag) {
                flag = webDB.waitForElement(UsersPageLocators.ADD_USER_SUCCESS_POPUP, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Success popup is visible for add user");
                    WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(UsersPageLocators.ADD_USER_SUCCESS_POPUP)));
                }
            }
        }
        return flag;
    }

    /**
     * This method deletes created user
     *
     * @return boolean flag
     * @author Vignesh - GWL
     */
    public boolean deleteCreatedUser() {
        flag = webDB.waitForElement(UsersPageLocators.TABLE_FIRST_ROW_DELETE_ICON, ElementType.Xpath);
        if (flag) {
            flag = webDB.clickAnElement(UsersPageLocators.TABLE_FIRST_ROW_DELETE_ICON, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Clicked first row delete icon");
                flag = webDB.waitForElement(UsersPageLocators.DELETE_CONFIRM_BTN, ElementType.Xpath);
                if (flag) {
                    flag = webDB.clickAnElement(UsersPageLocators.DELETE_CONFIRM_BTN, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Clicked delete confirm");
                        flag = webDB.waitForElement(UsersPageLocators.DELETE_SUCCESS_POPUP_CLOSE, ElementType.Xpath);
                        if (flag) {
                            flag = webDB.clickAnElement(UsersPageLocators.DELETE_SUCCESS_POPUP_CLOSE, ElementType.Xpath);
                            if(flag){
                                log.logging("info", "Clicked popup close");
                                WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(UsersPageLocators.DELETE_SUCCESS_POPUP_CLOSE)));
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies fill user details and save
     *
     * @return boolean flag
     * @throws InterruptedException Here used thread sleep time to enhance the text entering consistency
     * @author Vignesh - GWL
     */
    public boolean verifyFillUserDetailsAndSave() throws InterruptedException {
        Thread.sleep(2000);
        flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_FIRST_NAME_FIELD, ElementType.Id);
        if (flag) {
            String demoFirstName = UsersPageLocators.DEMO_FIRST_NAME;
            WebElement firstNameField = webDB.getDriver().findElement(By.id(UsersPageLocators.ADD_USER_POPUP_FIRST_NAME_FIELD));
            for (char c : demoFirstName.toCharArray()) {
                firstNameField.sendKeys(String.valueOf(c));
                Thread.sleep(10);
            }
            log.logging("info", "Entered first name in popup");
            String demoLastName = UsersPageLocators.DEMO_LAST_NAME;
            WebElement lastNameField = webDB.getDriver().findElement(By.id(UsersPageLocators.ADD_USER_POPUP_LAST_NAME_FIELD));
            for (char c : demoLastName.toCharArray()) {
                lastNameField.sendKeys(String.valueOf(c));
                Thread.sleep(10);
            }
            log.logging("info", "Entered last name in popup");
            String demoEmail = UsersPageLocators.DEMO_EMAIL;
            WebElement emailField = webDB.getDriver().findElement(By.id(UsersPageLocators.ADD_USER_POPUP_EMAIL_FIELD));
            for (char c : demoEmail.toCharArray()) {
                emailField.sendKeys(String.valueOf(c));
                Thread.sleep(10);
            }
            log.logging("info", "Entered email in popup");
            flag = clickAddButtonAndWaitForPopup();
        }
        return flag;
    }

    /**
     * This method fills user details for super admin
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyFillUserDetailsAndSaveForSuperAdmin() throws InterruptedException {
        Thread.sleep(2000);
        flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_USER_RADIO_BTN, ElementType.Id);
        if(flag){
            flag = webDB.clickAnElement(UsersPageLocators.ADD_USER_POPUP_USER_RADIO_BTN, ElementType.Id);
            if(flag){
                log.logging("info", "Clicked user radio button");
                Actions actions = new Actions(webDB.getDriver());
                actions.sendKeys(Keys.TAB).sendKeys(Keys.chord("g")).sendKeys(Keys.ENTER).build().perform();
                flag = verifyFillUserDetailsAndSave();
            }
        }
        return flag;
    }

    /**
     * This method fills second user details for super admin
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyFillSecondUserDetailsAndSaveForSuperAdmin() throws InterruptedException {
        Thread.sleep(2000);
        flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_USER_RADIO_BTN, ElementType.Id);
        if(flag){
            flag = webDB.clickAnElement(UsersPageLocators.ADD_USER_POPUP_USER_RADIO_BTN, ElementType.Id);
            if(flag){
                log.logging("info", "Clicked user radio button");
                Actions actions = new Actions(webDB.getDriver());
                actions.sendKeys(Keys.TAB).sendKeys(Keys.chord("g")).sendKeys(Keys.ENTER).build().perform();
                flag = verifyFillSecondUserAndSaveForSuperAdmin();
            }
        }
        return flag;
    }

    /**
     * This method verifies adding new user
     *
     * @return boolean flag
     * @throws InterruptedException
     * @author Vignesh - GWL
     */
    public boolean verifyAddAndDeleteNewUser(String email) throws InterruptedException {
        flag = verifyUsersPageNavigation();
        if (flag) {
            flag = verifyAddPopup();
            if (flag) {
                if(email.equals(webDB.getDataFromProperties("superAdminUsername"))){
                    flag = verifyFillUserDetailsAndSaveForSuperAdmin();
                    if(flag){
                        flag = deleteCreatedUser();
                    }
                }else{
                    flag = verifyFillUserDetailsAndSave();
                    if (flag) {
                        flag = deleteCreatedUser();
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method enters only first and last name
     *
     * @return boolean flag
     * @throws InterruptedException Here used thread sleep time to ensure entering precise value on input field
     * @author Vignesh - GWL
     */
    public boolean enterOnlyFirstAndLastName() throws InterruptedException {
        flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_FIRST_NAME_FIELD, ElementType.Id);
        if (flag) {
            String demoFirstName = UsersPageLocators.DEMO_FIRST_NAME;
            WebElement firstNameField = webDB.getDriver().findElement(By.id(UsersPageLocators.ADD_USER_POPUP_FIRST_NAME_FIELD));
            for (char c : demoFirstName.toCharArray()) {
                firstNameField.sendKeys(String.valueOf(c));
                Thread.sleep(10);
            }
            log.logging("info", "Entered first name in popup");
            String demoLastName = UsersPageLocators.DEMO_LAST_NAME;
            WebElement lastNameField = webDB.getDriver().findElement(By.id(UsersPageLocators.ADD_USER_POPUP_LAST_NAME_FIELD));
            for (char c : demoLastName.toCharArray()) {
                lastNameField.sendKeys(String.valueOf(c));
                Thread.sleep(10);
            }
        }
        return flag;
    }

    /**
     * This method verifies error message for empty email field
     *
     * @return boolean flag
     * @author Vignesh - GWL
     */
    public boolean verifyErrorForEmptyEmailFieldAndClickCancel() throws InterruptedException {
        flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_SAVE_BTN, ElementType.Xpath);
        if (flag) {
            flag = webDB.clickAnElement(UsersPageLocators.ADD_USER_POPUP_SAVE_BTN, ElementType.Xpath);
            if (flag) {
                flag = webDB.waitForElement(UsersPageLocators.ADD_POPUP_EMPTY_EMAIL_ERROR, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Verified error for empty email field");
                    flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_CANCEL_BTN, ElementType.Xpath);
                    if (flag) {
                        flag = webDB.clickAnElement(UsersPageLocators.ADD_USER_POPUP_CANCEL_BTN, ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Clicked cancel in popup");
                            WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(UsersPageLocators.ADD_USER_POPUP_CANCEL_BTN)));
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies error message for input fields
     *
     * @return boolean flag
     * @throws InterruptedException
     * @author Vignesh - GWL
     */
    public boolean verifyErrorMessageForInputField(String email) throws InterruptedException {
        flag = verifyUsersPageNavigation();
        if (flag) {
            flag = verifyAddPopup();
            if (flag) {
                flag = enterOnlyFirstAndLastName();
                if (flag) {
                    flag = verifyErrorForEmptyEmailFieldAndClickCancel();
                    if(email.equals(webDB.getDataFromProperties("superAdminUsername"))){
                        flag = webDB.waitForElement(UsersPageLocators.EMPTY_COMPANY_ERROR, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Verified error for empty company field");
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies input fields
     *
     * @return boolean flag
     * @author Vignesh - GWL
     */
    public boolean verifyInputFields() {
        flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_FIRST_NAME_FIELD, ElementType.Id);
        if (flag) {
            log.logging("info", "First name field is visible");
            flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_LAST_NAME_FIELD, ElementType.Id);
            if (flag) {
                log.logging("info", "Last name field is visible");
                flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_EMAIL_FIELD, ElementType.Id);
                if (flag) {
                    log.logging("info", "Presence of all input fields are verified");
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies presence of input fields
     *
     * @return boolean flag
     * @author Vignesh - GWL
     */
    public boolean verifyPresenceOfInputFields() throws InterruptedException {
        flag = verifyUsersPageNavigation();
        if (flag) {
            flag = verifyAddPopup();
            if (flag) {
                flag = verifyInputFields();
                if (flag) {
                    flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_CANCEL_BTN, ElementType.Xpath);
                    if (flag) {
                        flag = webDB.javaScriptClickAnElement(UsersPageLocators.ADD_USER_POPUP_CANCEL_BTN, ElementType.Xpath);
                        WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(UsersPageLocators.ADD_USER_POPUP_CANCEL_BTN)));
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies saved user details
     *
     * @return boolean flag
     * Here used thread sleep time to ensure entering precise value on input field
     * @author Vignesh - GWL
     */
    public boolean verifySavedUserDetails() throws InterruptedException {
        flag = webDB.waitForElement(UsersPageLocators.TABLE_FIRST_ROW_EDIT_ICON, ElementType.Xpath);
        if (flag) {
            flag = webDB.clickAnElement(UsersPageLocators.TABLE_FIRST_ROW_EDIT_ICON, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Clicked first row edit icon");
                Thread.sleep(3000);
                String firstName = webDB.getAttributeFromElement(UsersPageLocators.ADD_USER_POPUP_FIRST_NAME_FIELD, ElementType.Id, "value");
                if (firstName.equals(UsersPageLocators.DEMO_FIRST_NAME)) {
                    log.logging("info", "Verified saved first name");
                    String lastName = webDB.getAttributeFromElement(UsersPageLocators.ADD_USER_POPUP_LAST_NAME_FIELD, ElementType.Id, "value");
                    if (lastName.equals(UsersPageLocators.DEMO_LAST_NAME)) {
                        log.logging("info", "Verified saved last name");
                        String email = webDB.getAttributeFromElement(UsersPageLocators.ADD_USER_POPUP_EMAIL_FIELD, ElementType.Id, "value");
                        if (email.equals(UsersPageLocators.DEMO_EMAIL)) {
                            log.logging("info", "Verified saved email");
                            flag = webDB.waitForElement(UsersPageLocators.EDIT_USER_POPUP_CANCEL_BTN, ElementType.Xpath);
                            if (flag) {
                                flag = webDB.clickAnElement(UsersPageLocators.EDIT_USER_POPUP_CANCEL_BTN, ElementType.Xpath);
                                WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(UsersPageLocators.EDIT_USER_POPUP_CANCEL_BTN)));
                            }
                        } else {
                            flag = false;
                        }
                    } else {
                        flag = false;
                    }
                } else {
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies saved field data
     *
     * @return boolean flag
     * @throws InterruptedException
     * @author Vignesh - GWL
     */
    public boolean verifySavedFieldData(String email) throws InterruptedException {
        flag = verifyUsersPageNavigation();
        if (flag) {
            flag = verifyAddPopup();
            if (flag) {
                if(email.equals(webDB.getDataFromProperties("superAdminUsername"))){
                    flag = verifyFillUserDetailsAndSaveForSuperAdmin();
                }else{
                    flag = verifyFillUserDetailsAndSave();
                }
                if (flag) {
                    flag = verifySavedUserDetails();
                    if (flag) {
                        flag = deleteCreatedUser();
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies error for maximum length name
     *
     * @return boolean flag
     * @throws InterruptedException Here used thread sleep time to ensure entering precise value on input field
     * @author Vignesh - GWL
     */
    public boolean verifyEnteringMaximumLengthNames() throws InterruptedException {
        Thread.sleep(2000);
        flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_FIRST_NAME_FIELD, ElementType.Id);
        if (flag) {
            String demoFirstName = UsersPageLocators.LENGTH_ERROR_NAME;
            WebElement firstNameField = webDB.getDriver().findElement(By.id(UsersPageLocators.ADD_USER_POPUP_FIRST_NAME_FIELD));
            for (char c : demoFirstName.toCharArray()) {
                firstNameField.sendKeys(String.valueOf(c));
                Thread.sleep(10);
            }
            log.logging("info", "Entered first name");
            String demoLastName = UsersPageLocators.LENGTH_ERROR_NAME;
            WebElement lastNameField = webDB.getDriver().findElement(By.id(UsersPageLocators.ADD_USER_POPUP_LAST_NAME_FIELD));
            for (char c : demoLastName.toCharArray()) {
                lastNameField.sendKeys(String.valueOf(c));
                Thread.sleep(10);
            }
            log.logging("info", "Entered last name");
        }
        return flag;
    }

    /**
     * This method verifies maximum length name error
     *
     * @return boolean flag
     * @author Vignesh - GWL
     */
    public boolean verifyMaximumLengthNameError() {
        flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_SAVE_BTN, ElementType.Xpath);
        if (flag) {
            flag = webDB.clickAnElement(UsersPageLocators.ADD_USER_POPUP_SAVE_BTN, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Clicked save button from popup");
                flag = webDB.waitForElement(UsersPageLocators.FIRSTNAME_FIELD_ERROR, ElementType.Xpath);
                if (flag) {
                    String fNameError = webDB.getTextFromElement(UsersPageLocators.FIRSTNAME_FIELD_ERROR, ElementType.Xpath);
                    if (fNameError.equals(UsersPageLocators.MAXIMUM_LENGTH_ERROR)) {
                        log.logging("info", "Error message is verified for first name field");
                        String lNameError = webDB.getTextFromElement(UsersPageLocators.LASTNAME_FIELD_ERROR, ElementType.Xpath);
                        if (lNameError.equals(UsersPageLocators.MAXIMUM_LENGTH_ERROR)) {
                            log.logging("info", "Error message is verified for last name field");
                        } else {
                            flag = false;
                        }
                    } else {
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies maximum length error message
     *
     * @return boolean flag
     * @throws InterruptedException
     * @author Vignesh - GWL
     */
    public boolean verifyMaximumLengthForNameField() throws InterruptedException {
        flag = verifyUsersPageNavigation();
        if (flag) {
            flag = verifyAddPopup();
            if (flag) {
                flag = verifyEnteringMaximumLengthNames();
                if (flag) {
                    flag = verifyMaximumLengthNameError();
                    if (flag) {
                        flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_CANCEL_BTN, ElementType.Xpath);
                        if (flag) {
                            flag = webDB.clickAnElement(UsersPageLocators.ADD_USER_POPUP_CANCEL_BTN, ElementType.Xpath);
                            WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(UsersPageLocators.ADD_USER_POPUP_CANCEL_BTN)));
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method enters name with space as first character and valid email
     *
     * @return boolean flag
     * @throws InterruptedException Here used thread sleep time to ensure entering precise value on input field
     * @author Vignesh - GWL
     */
    public boolean enterNameWithSpaceAsFirstCharAndValidEmail() throws InterruptedException {
        flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_FIRST_NAME_FIELD, ElementType.Id);
        if (flag) {
            String demoFirstName = UsersPageLocators.FIRSTNAME_WITH_SPACE_AT_FIRST;
            WebElement firstNameField = webDB.getDriver().findElement(By.id(UsersPageLocators.ADD_USER_POPUP_FIRST_NAME_FIELD));
            for (char c : demoFirstName.toCharArray()) {
                firstNameField.sendKeys(String.valueOf(c));
                Thread.sleep(10);
            }
            log.logging("info", "Entered first name in popup");
            String demoLastName = UsersPageLocators.LASTNAME_WITH_SPACE_AT_FIRST;
            WebElement lastNameField = webDB.getDriver().findElement(By.id(UsersPageLocators.ADD_USER_POPUP_LAST_NAME_FIELD));
            for (char c : demoLastName.toCharArray()) {
                lastNameField.sendKeys(String.valueOf(c));
                Thread.sleep(10);
            }
            log.logging("info", "Entered last name in popup");
            String demoEmail = UsersPageLocators.DEMO_EMAIL;
            WebElement emailField = webDB.getDriver().findElement(By.id(UsersPageLocators.ADD_USER_POPUP_EMAIL_FIELD));
            for (char c : demoEmail.toCharArray()) {
                emailField.sendKeys(String.valueOf(c));
                Thread.sleep(10);
            }
            log.logging("info", "Entered email in popup");
            flag = clickAddButtonAndWaitForPopup();
        }
        return flag;
    }

    /**
     * This method verifies created user details
     *
     * @return boolean flag
     * @author Vignesh - GWL
     */
    public boolean verifyCreatedUserDetailsWithSpace() {
        flag = webDB.waitForElement(UsersPageLocators.TABLE_FIRST_ROW_NAME, ElementType.Xpath);
        if (flag) {
            String name = webDB.getTextFromElement(UsersPageLocators.TABLE_FIRST_ROW_NAME, ElementType.Xpath);
            if (name.contains(UsersPageLocators.FIRSTNAME_WITH_SPACE_AT_FIRST) || name.contains(UsersPageLocators.LASTNAME_WITH_SPACE_AT_FIRST)) {
                log.logging("info", "User created successfully as expected");
            } else {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies name with space as first character
     *
     * @return boolean flag
     * @throws InterruptedException
     * @author Vignesh - GWL
     */
    public boolean verifyNameWithSpaceAsFirstCharacter(String email) throws InterruptedException {
        flag = verifyUsersPageNavigation();
        if (flag) {
            flag = verifyAddPopup();
            if (flag) {
                if(email.equals(webDB.getDataFromProperties("superAdminUsername"))){
                    Thread.sleep(2000);
                    flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_USER_RADIO_BTN, ElementType.Id);
                    if(flag){
                        flag = webDB.clickAnElement(UsersPageLocators.ADD_USER_POPUP_USER_RADIO_BTN, ElementType.Id);
                        if(flag){
                            log.logging("info", "Clicked user radio button");
                            Actions actions = new Actions(webDB.getDriver());
                            actions.sendKeys(Keys.TAB).sendKeys(Keys.chord("g")).sendKeys(Keys.ENTER).build().perform();
                        }
                    }
                }
                flag = enterNameWithSpaceAsFirstCharAndValidEmail();
                if (flag) {
                    flag = verifyCreatedUserDetailsWithSpace();
                    if (flag) {
                        flag = deleteCreatedUser();
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies saved user details after refresh
     *
     * @return boolean flag
     * @throws InterruptedException
     * @author Vignesh - GWL
     */
    public boolean verifySavedUserDetailsAfterRefresh() throws InterruptedException {
        flag = webDB.navigateToRefresh();
        if (flag) {
            log.logging("info", "Refreshed webpage");
            flag = verifySavedUserDetails();
        }
        return flag;
    }

    /**
     * This method verifies created item after refresh
     *
     * @return boolean flag
     * @throws InterruptedException
     * @author Vignesh - GWL
     */
    public boolean verifyCreatedItemAfterRefresh(String email) throws InterruptedException {
        flag = verifyUsersPageNavigation();
        if (flag) {
            flag = verifyAddPopup();
            if (flag) {
                if(email.equals(webDB.getDataFromProperties("superAdminUsername"))){
                    flag = verifyFillUserDetailsAndSaveForSuperAdmin();
                }else{
                    flag = verifyFillUserDetailsAndSave();
                }
                if (flag) {
                    flag = verifySavedUserDetailsAfterRefresh();
                    if (flag) {
                        flag = deleteCreatedUser();
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies invalid email error for creating user
     *
     * @return boolean flag
     * @throws InterruptedException
     * @author Vignesh - GWL
     */
    public boolean verifyUserInvalidEmailError() {
        flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_EMAIL_FIELD, ElementType.Id);
        if (flag) {
            String[][] data = excelUtils.readAllDataInExcel(webDB.getDataFromProperties("invalidEmailForUser"), "Sheet1");
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    WebElement emailField = webDB.getDriver().findElement(By.id(UsersPageLocators.ADD_USER_POPUP_EMAIL_FIELD));
                    emailField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
                    emailField.sendKeys(data[i][j]);
                    log.logging("info", "Entered invalid email id" + ": " + data[i][j]);
                    flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_SAVE_BTN, ElementType.Xpath);
                    if (flag) {
                        webDB.clickAnElement(UsersPageLocators.ADD_USER_POPUP_SAVE_BTN, ElementType.Xpath);
                        flag = webDB.waitForElement(UsersPageLocators.EMAIL_FIELD_ERROR, ElementType.Xpath);
                        if (flag) {
                            String errorMsg = webDB.getTextFromElement(UsersPageLocators.EMAIL_FIELD_ERROR, ElementType.Xpath);
                            if (errorMsg.equals(UsersPageLocators.ERROR_MESSAGE_FOR_INVALID_EMAIL)) {
                                log.logging("info", "Error message verified for value " + ": " + data[i][j]);
                            }
                        }
                    }
                }
            }
            log.logging("info", "Error message is verified for all invalid emails");
        }
        return flag;
    }

    /**
     * This method verifies error message for invalid email format
     *
     * @return boolean flag
     * @throws InterruptedException
     * @author Vignesh - GWL
     */
    public boolean verifyErrorForInvalidEmailFormat() throws InterruptedException {
        flag = verifyUsersPageNavigation();
        if (flag) {
            flag = verifyAddPopup();
            if (flag) {
                flag = enterOnlyFirstAndLastName();
                if (flag) {
                    flag = verifyUserInvalidEmailError();
                    if (flag) {
                        flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_CANCEL_BTN, ElementType.Xpath);
                        if (flag) {
                            flag = webDB.clickAnElement(UsersPageLocators.ADD_USER_POPUP_CANCEL_BTN, ElementType.Xpath);
                            WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(UsersPageLocators.ADD_USER_POPUP_CANCEL_BTN)));
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method enters email with space as first character
     *
     * @return boolean flag
     * @throws InterruptedException Here used thread sleep time to ensure entering precise value on input field
     * @author Vignesh - GWL
     */
    public boolean enterEmailWithSpaceAtFirst() throws InterruptedException {
        flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_EMAIL_FIELD, ElementType.Id);
        if (flag) {
            String demoEmail = UsersPageLocators.DEMO_EMAIL_WITH_SPACE_AT_FIRST;
            WebElement emailField = webDB.getDriver().findElement(By.id(UsersPageLocators.ADD_USER_POPUP_EMAIL_FIELD));
            for (char c : demoEmail.toCharArray()) {
                emailField.sendKeys(String.valueOf(c));
                Thread.sleep(10);
            }
            log.logging("info", "Entered email with space as first character");
        }
        return flag;
    }

    /**
     * This method verifies email field with space as first character
     *
     * @return boolean flag
     * @throws InterruptedException
     * @author Vignesh - GWL
     */
    public boolean verifyEmailFieldWithSpaceAsFirstChar() throws InterruptedException {
        flag = verifyUsersPageNavigation();
        if (flag) {
            flag = verifyAddPopup();
            if (flag) {
                flag = enterOnlyFirstAndLastName();
                if (flag) {
                    flag = enterEmailWithSpaceAtFirst();
                    if (flag) {
                        flag = clickAddButtonAndWaitForPopup();
                        if (flag) {
                            flag = deleteCreatedUser();
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method enters email
     *
     * @param email
     * @return boolean flag
     * @throws InterruptedException Here used thread sleep time to ensure entering precise value on input field
     * @author Vignesh - GWL
     */
    public boolean enterEmail(String email) throws InterruptedException {
        flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_EMAIL_FIELD, ElementType.Id);
        if (flag) {
            WebElement emailField = webDB.getDriver().findElement(By.id(UsersPageLocators.ADD_USER_POPUP_EMAIL_FIELD));
            for (char c : email.toCharArray()) {
                emailField.sendKeys(String.valueOf(c));
                Thread.sleep(10);
            }
            log.logging("info", "Entered email on email field");
        }
        return flag;
    }

    /**
     * This method verifies cancel option in dialogue box
     *
     * @return boolean flag
     * @throws InterruptedException
     * @author Vignesh - GWL
     */
    public boolean verifyCancelOptionInDialogueBox() throws InterruptedException {
        flag = verifyUsersPageNavigation();
        if (flag) {
            flag = verifyAddPopup();
            if (flag) {
                flag = enterOnlyFirstAndLastName();
                if (flag) {
                    flag = enterEmail(UsersPageLocators.DEMO_EMAIL);
                    if (flag) {
                        flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_CANCEL_BTN, ElementType.Xpath);
                        if (flag) {
                            flag = webDB.clickAnElement(UsersPageLocators.ADD_USER_POPUP_CANCEL_BTN, ElementType.Xpath);
                            if (flag) {
                                log.logging("info", "Clicked cancel button");
                                flag = webDB.waitForElement(UsersPageLocators.TABLE_FIRST_ROW_NAME, ElementType.Xpath);
                                if (flag) {
                                    String name = webDB.getTextFromElement(UsersPageLocators.TABLE_FIRST_ROW_NAME, ElementType.Xpath);
                                    if (name.contains(UsersPageLocators.DEMO_FIRST_NAME)) {
                                        log.logging("fail", "Cancel button haven't worked as expected");
                                        flag = false;
                                    } else {
                                        log.logging("info", "User details not saved after clicking cancel button");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies access radio button via keyboard
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyAccessRadioBtnViaKeyboard() throws InterruptedException, AWTException {
        flag = verifyUsersPageNavigation();
        if (flag) {
            flag = verifyAddPopup();
            if (flag) {
                flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_USER_RADIO_BTN, ElementType.Id);
                if(flag) {
                    WebElement userRadioButton = webDB.getDriver().findElement(By.id(UsersPageLocators.ADD_USER_POPUP_USER_RADIO_BTN));
                    Robot robot = new Robot();
                    robot.keyPress(KeyEvent.VK_TAB);
                    robot.keyRelease(KeyEvent.VK_TAB);
                    boolean isCheckedFound = false;
                    if (userRadioButton.getAttribute("checked") != null) {
                        isCheckedFound = true;
                        if (isCheckedFound) {
                            log.logging("info", "User radio button is checked after clicking tab as expected");
                            //flag = verifyPopupClose();
                            flag = verifyDeletePopupClose();
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies created user details
     *
     * @return boolean flag
     * @throws InterruptedException
     * @author Vignesh - GWL
     */
    public boolean verifyCreatedUserDetails() throws InterruptedException {
        flag = verifyUsersPageNavigation();
        if (flag) {
            flag = verifyAddPopup();
            if (flag) {
                flag = verifyFillUserDetailsAndSave();
                if (flag) {
                    flag = verifySavedUserDetails();
                    if (flag) {
                        flag = deleteCreatedUser();
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies error for duplicate item
     *
     * @return boolean flag
     * @throws InterruptedException
     * @author Vignesh - GWL
     */
    public boolean verifyErrorForDuplicateItem(String email) throws InterruptedException {
        flag = verifyAddPopup();
        if (flag) {
            if(email.equals(webDB.getDataFromProperties("superAdminUsername"))) {
                Thread.sleep(2000);
                flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_USER_RADIO_BTN, ElementType.Id);
                if (flag) {
                    flag = webDB.clickAnElement(UsersPageLocators.ADD_USER_POPUP_USER_RADIO_BTN, ElementType.Id);
                    if (flag) {
                        log.logging("info", "Clicked user radio button");
                        Actions actions = new Actions(webDB.getDriver());
                        actions.sendKeys(Keys.TAB).sendKeys(Keys.chord("g")).sendKeys(Keys.ENTER).build().perform();
                    }
                }
            }
            flag = enterOnlyFirstAndLastName();
            if (flag) {
                flag = enterEmail(UsersPageLocators.DEMO_EMAIL);
                if (flag) {
                    flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_SAVE_BTN, ElementType.Xpath);
                    if (flag) {
                        webDB.clickAnElement(UsersPageLocators.ADD_USER_POPUP_SAVE_BTN, ElementType.Xpath);
                        flag = webDB.waitForElement(UsersPageLocators.DUPLICATE_EMAIL_POPUP, ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Success popup is visible for add user");
                            WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(UsersPageLocators.DUPLICATE_EMAIL_POPUP)));
                        } else {
                            flag = webDB.waitForElement(UsersPageLocators.EMAIL_FIELD_ERROR, ElementType.Xpath);
                            if (flag) {
                                String errorMsg = webDB.getTextFromElement(UsersPageLocators.EMAIL_FIELD_ERROR, ElementType.Xpath);
                                if (errorMsg.equals(UsersPageLocators.DUPLICATE_EMAIL_ERROR)) {
                                    log.logging("info", "Error message verified for duplicate value");
                                    flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_CANCEL_BTN, ElementType.Xpath);
                                    if (flag) {
                                        flag = webDB.clickAnElement(UsersPageLocators.ADD_USER_POPUP_CANCEL_BTN, ElementType.Xpath);
                                        WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                                        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(UsersPageLocators.ADD_USER_POPUP_CANCEL_BTN)));
                                    }
                                } else {
                                    flag = false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies error on creating duplicate item
     *
     * @return boolean flag
     * @throws InterruptedException
     * @author Vignesh - GWL
     */
    public boolean verifyErrorOnCreatingDuplicateItem(String email) throws InterruptedException {
        flag = verifyUsersPageNavigation();
        if (flag) {
            flag = verifyAddPopup();
            if (flag) {
                if(email.equals(webDB.getDataFromProperties("superAdminUsername"))){
                    flag = verifyFillUserDetailsAndSaveForSuperAdmin();
                }else{
                    flag = verifyFillUserDetailsAndSave();
                }
                if (flag) {
                    flag = verifySavedUserDetails();
                    if (flag) {
                        flag = verifyErrorForDuplicateItem(email);
                        if (flag) {
                            flag = deleteCreatedUser();
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies checked status of radio button
     *
     * @return boolean flag
     * @throws InterruptedException
     * @author Vignesh - GWL
     */
    public boolean verifyCheckedStatusOfRadioBtn(String email) throws InterruptedException {
        flag = verifyUsersPageNavigation();
        if (flag) {
            flag = verifyAddPopup();
            if (flag) {
                if(email.equals(webDB.getDataFromProperties("superAdminUsername"))){
                    Thread.sleep(2000);
                    flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_USER_RADIO_BTN, ElementType.Id);
                    if(flag){
                        flag = webDB.clickAnElement(UsersPageLocators.ADD_USER_POPUP_USER_RADIO_BTN, ElementType.Id);
                    }
                }
                flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_USER_RADIO_BTN, ElementType.Id);
                if(flag) {
                    WebElement userRadioButton = webDB.getDriver().findElement(By.id(UsersPageLocators.ADD_USER_POPUP_USER_RADIO_BTN));
                    boolean isCheckedFound = false;
                    if (userRadioButton.getAttribute("checked") != null) {
                        isCheckedFound = true;
                        if (isCheckedFound) {
                            log.logging("info", "User radio button is checked by default as expected");
                            //flag = verifyPopupClose();
                            flag = verifyDeletePopupClose();
                        }else{
                            log.logging("fail", "Radio button not checked by default");
                            flag = false;
                        }
                    }else{
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies presence of delete button
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyPresenceOfDeleteButton() {
        flag = verifyUsersPageNavigation();
        if(flag){
            flag = webDB.waitForElement(UsersPageLocators.TABLE_TOTAL_ROWS, ElementType.Xpath);
            if(flag){
                List<WebElement> totalRows = webDB.getDriver().findElements(By.xpath(UsersPageLocators.TABLE_TOTAL_ROWS));
                int sizeOfRows = totalRows.size();
                log.logging("info", "Total rows on the table is: "+sizeOfRows);
                flag = webDB.waitForElement(UsersPageLocators.TABLE_TOTAL_DELETE_ICON, ElementType.Xpath);
                if(flag){
                    List<WebElement> totalDeleteIcons = webDB.getDriver().findElements(By.xpath(UsersPageLocators.TABLE_TOTAL_DELETE_ICON));
                    int sizeOfDeleteIcon = totalDeleteIcons.size();
                    log.logging("info", "Total delete icons in the table is: "+sizeOfDeleteIcon);
                    if(sizeOfRows == sizeOfDeleteIcon){
                        log.logging("info", "Each table row has a delete icon");
                    }else{
                        log.logging("fail", "Mismatch on delete icon count");
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies confirmation message on delete popup
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyConfirmationPopupOnDelete() throws InterruptedException {
        flag = verifyUsersPageNavigation();
        if(flag){
            flag = webDB.waitForElement(UsersPageLocators.TABLE_FIRST_ROW_DELETE_ICON, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(UsersPageLocators.TABLE_FIRST_ROW_DELETE_ICON, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked delete icon");
                    flag = webDB.waitForElement(UsersPageLocators.DELETE_POPUP_TITLE, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Delete popup is visible");
                        flag = webDB.waitForElement(UsersPageLocators.DELETE_POPUP_TEXT, ElementType.Xpath);
                        if(flag){
                            String deleteText = webDB.getTextFromElement(UsersPageLocators.DELETE_POPUP_TEXT, ElementType.Xpath);
                            if(deleteText.contains(UsersPageLocators.DELETE_POPUP_TEXT_MSG)){
                                log.logging("info", "Delete popup message is verified");
                                //flag = verifyPopupClose();
                                flag = verifyDeletePopupClose();
                            }else{
                                flag = false;
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies first row username
     * @author Vignesh - GWL
     * @param userName
     * @return boolean flag
     */
    public boolean verifyFirstRowUserName(String userName) {
        flag = webDB.waitForElement(UsersPageLocators.TABLE_FIRST_ROW_NAME, ElementType.Xpath);
        if(flag){
            String name = webDB.getTextFromElement(UsersPageLocators.TABLE_FIRST_ROW_NAME, ElementType.Xpath);
            if(name.contains(userName)){
                log.logging("info", "First row user name is verified");
            }else{
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies cancel option on delete popup
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyCancelOptionOnDeletePopup() {
        flag = verifyUsersPageNavigation();
        if(flag) {
            String name = webDB.getTextFromElement(UsersPageLocators.TABLE_FIRST_ROW_NAME, ElementType.Xpath);
            flag = webDB.waitForElement(UsersPageLocators.TABLE_FIRST_ROW_DELETE_ICON, ElementType.Xpath);
            if (flag) {
                flag = webDB.clickAnElement(UsersPageLocators.TABLE_FIRST_ROW_DELETE_ICON, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Clicked delete icon");
                    flag = webDB.waitForElement(UsersPageLocators.DELETE_POPUP_TITLE, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Delete popup is visible");
                        flag = webDB.waitForElement(UsersPageLocators.DELETE_CANCEL_BTN, ElementType.Xpath);
                        if(flag){
                            flag = webDB.clickAnElement(UsersPageLocators.DELETE_CANCEL_BTN, ElementType.Xpath);
                            if(flag){
                                log.logging("info", "Clicked delete popup cancel button");
                                flag = verifyFirstRowUserName(name);
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies delete popup and clicks cancel
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyDeletePopupAndClickCancel() {
        flag = webDB.waitForElement(UsersPageLocators.DELETE_BUTTON, ElementType.Xpath);
        if(flag){
            flag = webDB.clickAnElement(UsersPageLocators.DELETE_BUTTON, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked delete button");
                flag = webDB.waitForElement(UsersPageLocators.DELETE_CANCEL_BTN, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Delete popup is visible");
                    flag = webDB.clickAnElement(UsersPageLocators.DELETE_CANCEL_BTN, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Clicked delete cancel button");
                        WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(UsersPageLocators.DELETE_CANCEL_BTN)));
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies clicking first two rows checkbox
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean clickFirstTwoRowCheckbox() {
        flag = webDB.waitForElement(UsersPageLocators.TABLE_FIRST_ROW_CHECKBOX, ElementType.Xpath);
        if(flag) {
            flag = webDB.clickAnElement(UsersPageLocators.TABLE_FIRST_ROW_CHECKBOX, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Clicked first row checkbox");
                flag = webDB.waitForElement(UsersPageLocators.TABLE_SECOND_ROW_CHECKBOX, ElementType.Xpath);
                if (flag) {
                    flag = webDB.clickAnElement(UsersPageLocators.TABLE_SECOND_ROW_CHECKBOX, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Clicked second row checkbox");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies item selection box
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyItemSelectionBox() {
        flag = verifyUsersPageNavigation();
        if(flag) {
            flag = clickFirstTwoRowCheckbox();
            if (flag) {
                flag = verifyDeletePopupAndClickCancel();
            }
        }
        return flag;
    }

    /**
     * This method verifies creating second user
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyFillSecondUserDetailsAndSave() throws InterruptedException {
        flag = webDB.waitForElement(UsersPageLocators.ADD_BUTTON, ElementType.Xpath);
        if(flag) {
            flag = webDB.clickAnElement(UsersPageLocators.ADD_BUTTON, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Clicked add button");
                flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_FIRST_NAME_FIELD, ElementType.Id);
                if (flag) {
                    String demoFirstName = UsersPageLocators.DEMO_SECOND_FIRST_NAME;
                    WebElement firstNameField = webDB.getDriver().findElement(By.id(UsersPageLocators.ADD_USER_POPUP_FIRST_NAME_FIELD));
                    for (char c : demoFirstName.toCharArray()) {
                        firstNameField.sendKeys(String.valueOf(c));
                        Thread.sleep(10);
                    }
                    log.logging("info", "Entered first name in popup");
                    String demoLastName = UsersPageLocators.DEMO_SECOND_LAST_NAME;
                    WebElement lastNameField = webDB.getDriver().findElement(By.id(UsersPageLocators.ADD_USER_POPUP_LAST_NAME_FIELD));
                    for (char c : demoLastName.toCharArray()) {
                        lastNameField.sendKeys(String.valueOf(c));
                        Thread.sleep(10);
                    }
                    log.logging("info", "Entered last name in popup");
                    String demoEmail = UsersPageLocators.DEMO_SECOND_EMAIL;
                    WebElement emailField = webDB.getDriver().findElement(By.id(UsersPageLocators.ADD_USER_POPUP_EMAIL_FIELD));
                    for (char c : demoEmail.toCharArray()) {
                        emailField.sendKeys(String.valueOf(c));
                        Thread.sleep(10);
                    }
                    log.logging("info", "Entered email in popup");
                    flag = clickAddButtonAndWaitForPopup();
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies creating second user for super admin
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyFillSecondUserAndSaveForSuperAdmin() throws InterruptedException {
        flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_FIRST_NAME_FIELD, ElementType.Id);
        if (flag) {
            String demoFirstName = UsersPageLocators.DEMO_SECOND_FIRST_NAME;
            WebElement firstNameField = webDB.getDriver().findElement(By.id(UsersPageLocators.ADD_USER_POPUP_FIRST_NAME_FIELD));
            for (char c : demoFirstName.toCharArray()) {
                firstNameField.sendKeys(String.valueOf(c));
                Thread.sleep(10);
            }
            log.logging("info", "Entered first name in popup");
            String demoLastName = UsersPageLocators.DEMO_SECOND_LAST_NAME;
            WebElement lastNameField = webDB.getDriver().findElement(By.id(UsersPageLocators.ADD_USER_POPUP_LAST_NAME_FIELD));
            for (char c : demoLastName.toCharArray()) {
                lastNameField.sendKeys(String.valueOf(c));
                Thread.sleep(10);
            }
            log.logging("info", "Entered last name in popup");
            String demoEmail = UsersPageLocators.DEMO_SECOND_EMAIL;
            WebElement emailField = webDB.getDriver().findElement(By.id(UsersPageLocators.ADD_USER_POPUP_EMAIL_FIELD));
            for (char c : demoEmail.toCharArray()) {
                emailField.sendKeys(String.valueOf(c));
                Thread.sleep(10);
            }
            log.logging("info", "Entered email in popup");
            flag = clickAddButtonAndWaitForPopup();
        }
        return flag;
    }

    /**
     * This method confirms delete
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean clickDeleteButtonAndConfirm() {
        flag = webDB.waitForElement(UsersPageLocators.DELETE_BUTTON, ElementType.Xpath);
        if(flag){
            flag = webDB.clickAnElement(UsersPageLocators.DELETE_BUTTON, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked delete button");
                flag = webDB.waitForElement(UsersPageLocators.DELETE_CONFIRM_BTN, ElementType.Xpath);
                if(flag){
                    flag = webDB.clickAnElement(UsersPageLocators.DELETE_CONFIRM_BTN, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Clicked delete confirm button");
                        flag = webDB.waitForElement(UsersPageLocators.DELETE_MULTI_SUCCESS_POPUP_CLOSE, ElementType.Xpath);
                        if (flag) {
                            flag = webDB.clickAnElement(UsersPageLocators.DELETE_MULTI_SUCCESS_POPUP_CLOSE, ElementType.Xpath);
                            if(flag){
                                log.logging("info", "Clicked popup close");
                                WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(UsersPageLocators.DELETE_MULTI_SUCCESS_POPUP_CLOSE)));
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies deleting multiple records
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyDeletingMultipleRecords(String email) throws InterruptedException {
        flag = verifyUsersPageNavigation();
        if (flag) {
            flag = verifyAddPopup();
            if (flag) {
                if(email.equals(webDB.getDataFromProperties("superAdminUsername"))){
                    flag = verifyFillUserDetailsAndSaveForSuperAdmin();
                    if(flag) {
                        flag = verifyAddPopup();
                        if(flag) {
                            flag = verifyFillSecondUserDetailsAndSaveForSuperAdmin();
                        }
                    }
                }else{
                    flag = verifyFillUserDetailsAndSave();
                    if(flag){
                        flag = verifyFillSecondUserDetailsAndSave();
                    }
                }
                if (flag) {
                    flag = clickFirstTwoRowCheckbox();
                    if(flag){
                        flag = clickDeleteButtonAndConfirm();
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies select all checkbox
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifySelectAllCheckbox() {
        flag = webDB.waitForElement(UsersPageLocators.HEADER_CHECK_BOX, ElementType.Id);
        if(flag){
            flag = webDB.clickAnElement(UsersPageLocators.HEADER_CHECK_BOX, ElementType.Id);
            if(flag){
                log.logging("info", "Clicked header check box");
                flag = verifyDeletePopupAndClickCancel();
            }
        }
        return flag;
    }

    /**
     * This method verifies place holder text on filter box
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyPlaceholderTextOnFilterBox() {
        flag = webDB.waitForElement(UsersPageLocators.FILTER_BUTTON, ElementType.Xpath);
        if(flag){
            flag = webDB.clickAnElement(UsersPageLocators.FILTER_BUTTON, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked filter button");
                flag = webDB.waitForElement(UsersPageLocators.FILER_NAME_FIELD, ElementType.Id);
                if(flag){
                    String nameAttribute = webDB.getAttributeFromElement(UsersPageLocators.FILER_NAME_FIELD, ElementType.Id, "placeholder");
                    if(nameAttribute.isEmpty()){
                        log.logging("fail", "No placeholder text in filter field");
                        flag = false;
                    }else{
                        log.logging("info", "Verified placeholder text");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies search button on filter
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifySearchButtonOnFilter() {
        flag = verifyPlaceholderTextOnFilterBox();
        if(flag){
            flag = webDB.waitForElement(UsersPageLocators.FILTER_SEARCH_BUTTON, ElementType.Xpath);
            if(flag){
                log.logging("info", "Search button is visible on filter option");
            }
        }
        return flag;
    }

    /**
     * This method returns first row name
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public String returnFirstRowName() {
        flag = webDB.waitForElement(UsersPageLocators.TABLE_FIRST_ROW_NAME, ElementType.Xpath);
        String name = null;
        if (flag) {
            name = webDB.getTextFromElement(UsersPageLocators.TABLE_FIRST_ROW_NAME, ElementType.Xpath);
            if (name.isEmpty()) {
                log.logging("fail", "Name is empty");
                flag = false;
            } else {
                log.logging("info", "Extracted name is: " + name);
            }
        }
        return name;
    }

    /**
     * This method verifies filter functionality
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyFilterSearchFunctionality() throws InterruptedException {
        flag = verifyPlaceholderTextOnFilterBox();
        if(flag){
            String name = returnFirstRowName();
            flag = webDB.sendTextToAnElement(UsersPageLocators.FILER_NAME_FIELD, name, ElementType.Id);
            if(flag){
                log.logging("info", "Sent text to the name field");
                flag = webDB.waitForElement(UsersPageLocators.FILTER_SEARCH_BUTTON, ElementType.Xpath);
                if(flag){
                    flag = webDB.clickAnElement(UsersPageLocators.FILTER_SEARCH_BUTTON, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Clicked filter search button");
                        flag = webDB.waitForElement(UsersPageLocators.TABLE_TOTAL_ROWS, ElementType.Xpath);
                        if(flag){
                            List<WebElement> totalRows = webDB.getDriver().findElements(By.xpath(UsersPageLocators.TABLE_TOTAL_ROWS));
                            if(!totalRows.isEmpty()){
                                log.logging("info", "Results shown as expected");
                                String resultName = webDB.getTextFromElement(UsersPageLocators.TABLE_FIRST_ROW_NAME, ElementType.Xpath);
                                if(resultName.equals(name)){
                                    log.logging("info", "Searched content showed on result");
                                }else{
                                    flag = false;
                                }
                            }else{
                                flag = false;
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies search functionality via keyboard action
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifySearchViaKeyboardAction() {
        flag = verifyPlaceholderTextOnFilterBox();
        if(flag) {
            String name = returnFirstRowName();
            flag = webDB.waitForElement(UsersPageLocators.FILER_NAME_FIELD, ElementType.Id);
            if (flag) {
                WebElement inputField = webDB.getDriver().findElement(By.id(UsersPageLocators.FILER_NAME_FIELD));
                Actions actions = new Actions(webDB.getDriver());
                actions.sendKeys(inputField, name).sendKeys(Keys.ENTER).perform();
                flag = webDB.waitForElement(UsersPageLocators.TABLE_TOTAL_ROWS, ElementType.Xpath);
                if(flag){
                    List<WebElement> totalRows = webDB.getDriver().findElements(By.xpath(UsersPageLocators.TABLE_TOTAL_ROWS));
                    if(!totalRows.isEmpty()){
                        log.logging("info", "Results shown as expected");
                        String resultName = webDB.getTextFromElement(UsersPageLocators.TABLE_FIRST_ROW_NAME, ElementType.Xpath);
                        if(resultName.equals(name)){
                            log.logging("info", "Searched content showed on result");
                        }else{
                            flag = false;
                        }
                    }else{
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies invalid keywords on search
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyInvalidKeywordsOnSearch() {
        flag = verifyPlaceholderTextOnFilterBox();
        if(flag){
            flag = webDB.sendTextToAnElement(UsersPageLocators.FILER_NAME_FIELD, UsersPageLocators.INVALID_NAME, ElementType.Id);
            if(flag) {
                log.logging("info", "Sent text to the name field");
                flag = webDB.waitForElement(UsersPageLocators.FILTER_SEARCH_BUTTON, ElementType.Xpath);
                if (flag) {
                    flag = webDB.clickAnElement(UsersPageLocators.FILTER_SEARCH_BUTTON, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Clicked filter search button");
                        flag = webDB.waitForElement(UsersPageLocators.NO_RECORDS_ERROR, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Error message verifies for invalid name search");
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies applied filters after navigation
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyAppliedFiltersAfterNavigation() {
        flag = verifyInvalidKeywordsOnSearch();
        if(flag){
            flag = editProfilePage.verifyEditProfilePageRedirection();
            if(flag){
                flag = verifyUsersPageNavigation();
                if(flag){
                    flag = webDB.isElementNotDisplayed(UsersPageLocators.NO_RECORDS_ERROR, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Applied filters are gone after page navigation");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method extracts pagination count
     * @author Vignesh - GWL
     * @param text
     * @return boolean flag
     */
    public String extractPaginationDigit(String text) {
        Pattern pattern = Pattern.compile("\\d+$");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }

    /**
     * This method verifies total client record count
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyTotalClientRecordCount() throws InterruptedException {
        flag = verifyFilterSearchFunctionality();
        if(flag){
            flag = webDB.waitForElement(UsersPageLocators.TABLE_TOTAL_ROWS, ElementType.Xpath);
            if(flag) {
                List<WebElement> totalRows = webDB.getDriver().findElements(By.xpath(UsersPageLocators.TABLE_TOTAL_ROWS));
                int rowSize = totalRows.size();
                log.logging("info", "Total row count is: "+rowSize);
                String paginationCount = webDB.getTextFromElement(UsersPageLocators.PAGINATION_TOTAL_COUNT, ElementType.Xpath);
                String firstDigit = extractPaginationDigit(paginationCount);
                log.logging("info", "Extracted first digit count is: "+firstDigit);
                if(firstDigit.equals(Integer.toString(rowSize))) {
                    log.logging("info", "Total row count is equal to pagination count");
                }else{
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies filter reset option
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyFilterResetOption() {
        flag = verifyPlaceholderTextOnFilterBox();
        if(flag) {
            flag = webDB.waitForElement(UsersPageLocators.TABLE_TOTAL_ROWS, ElementType.Xpath);
            if(flag) {
                List<WebElement> totalRows = webDB.getDriver().findElements(By.xpath(UsersPageLocators.TABLE_TOTAL_ROWS));
                int rowSize = totalRows.size();
                log.logging("info", "Row count at beginning is: "+rowSize);
                String name = returnFirstRowName();
                flag = webDB.sendTextToAnElement(UsersPageLocators.FILER_NAME_FIELD, name, ElementType.Id);
                if (flag) {
                    log.logging("info", "Sent text to the name field");
                    flag = webDB.waitForElement(UsersPageLocators.FILTER_SEARCH_RESET_BUTTON, ElementType.Xpath);
                    if (flag) {
                        flag = webDB.clickAnElement(UsersPageLocators.FILTER_SEARCH_RESET_BUTTON, ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Clicked filter reset button");
                            flag = webDB.waitForElement(UsersPageLocators.TABLE_TOTAL_ROWS, ElementType.Xpath);
                            if(flag) {
                                List<WebElement> totalRows1 = webDB.getDriver().findElements(By.xpath(UsersPageLocators.TABLE_TOTAL_ROWS));
                                int rowSize1 = totalRows.size();
                                log.logging("info", "Row count after clicking reset is: " + rowSize1);
                                if(rowSize == rowSize1){
                                    log.logging("info", "Reset button functionality is verified");
                                }else{
                                    flag = false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies closing filter option
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyClosingFilterOption() {
        flag = verifyPlaceholderTextOnFilterBox();
        if(flag) {
            flag = webDB.sendTextToAnElement(UsersPageLocators.FILER_NAME_FIELD, UsersPageLocators.INVALID_NAME, ElementType.Id);
            if (flag) {
                log.logging("info", "Sent text to the name field");
                flag = webDB.waitForElement(UsersPageLocators.FILTER_BUTTON, ElementType.Xpath);
                if(flag){
                    flag = webDB.clickAnElement(UsersPageLocators.FILTER_BUTTON, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Clicked filter button");
                        flag = webDB.isElementNotDisplayed(UsersPageLocators.FILTER_SEARCH_BUTTON, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Search option disappeared after clicking filter button");
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies next and previous button in pagination
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyNextAndPreviousButton() {
        flag = webDB.waitForElement(UsersPageLocators.PAGINATION_NEXT_PAGE, ElementType.Id);
        if(flag){
            log.logging("info", "Next button in pagination is visible");
            flag = webDB.waitForElement(UsersPageLocators.PAGINATION_FIRST_PAGE, ElementType.Id);
            if(flag){
                log.logging("info", "First page in pagination is visible");
                flag = webDB.waitForElement(UsersPageLocators.PAGINATION_PREVIOUS_PAGE, ElementType.Id);
                if(flag){
                    flag = webDB.waitForElement(UsersPageLocators.PAGINATION_LAST_PAGE, ElementType.Id);
                    if(flag){
                        log.logging("info", "Last page in pagination is visible");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies default pagination count
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyDefaultPaginationCount() {
        flag = webDB.waitForElement(UsersPageLocators.DEFAULT_DROPDOWN, ElementType.Xpath);
        if(flag){
            WebElement dropDown = webDB.getDriver().findElement(By.xpath(UsersPageLocators.DEFAULT_DROPDOWN));
            boolean isCheckedFound = false;
            if (dropDown.getAttribute("selected") != null) {
                isCheckedFound = true;
                if (isCheckedFound) {
                    log.logging("info", "By default drop down option is 10 as expected");
                    List<WebElement> totalRows = webDB.getDriver().findElements(By.xpath(UsersPageLocators.TABLE_TOTAL_ROWS));
                    int rowSize = totalRows.size();
                    if(rowSize <= 10){
                        log.logging("info", "Total table row count is matched with default dropdown option");
                    }else{
                        flag = false;
                    }
                }
            } else {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies pagination button disable state
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyPaginationButtonDisableState() {
        List<WebElement> totalRows = webDB.getDriver().findElements(By.xpath(UsersPageLocators.TABLE_TOTAL_ROWS));
        int rowSize = totalRows.size();
        if(rowSize <= 10){
            log.logging("info", "There are less than 10 rows so button is disabled");
            flag = true;
        }else{
            flag = webDB.waitForElement(UsersPageLocators.PAGINATION_NEXT_PAGE, ElementType.Id);
            if(flag){
                flag = webDB.moveToElement(UsersPageLocators.PAGINATION_NEXT_PAGE, ElementType.Id);
                if(flag) {
                    flag = webDB.clickAnElement(UsersPageLocators.PAGINATION_NEXT_PAGE, ElementType.Id);
                    if (flag) {
                        log.logging("info", "Clicked next page button");
                        flag = webDB.waitForElement(UsersPageLocators.PAGINATION_PREVIOUS_PAGE, ElementType.Id);
                        if(flag) {
                            WebElement previousBtn = webDB.getDriver().findElement(By.id(UsersPageLocators.PAGINATION_PREVIOUS_PAGE));
                            if (previousBtn.isEnabled()) {
                                log.logging("info", "Navigated to next page and previous button is enabled");
                            } else {
                                flag = false;
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies edit page redirection
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyEditPageRedirection() throws InterruptedException {
        flag = verifyUsersPageNavigation();
        if(flag){
            flag = webDB.waitForElement(UsersPageLocators.TABLE_FIRST_ROW_EDIT_ICON, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(UsersPageLocators.TABLE_FIRST_ROW_EDIT_ICON, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked table first row edit icon");
                    flag = webDB.waitForElement(UsersPageLocators.EDIT_USER_UPDATE_BTN, ElementType.Xpath);
                    if(flag){
                        Thread.sleep(2000);
                        log.logging("info", "Edit popup is visible");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies edit first name
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public String editFirstName() throws InterruptedException {
        flag = webDB.waitForElement(UsersPageLocators.TABLE_FIRST_ROW_EDIT_ICON, ElementType.Xpath);
        if(flag) {
            flag = webDB.clickAnElement(UsersPageLocators.TABLE_FIRST_ROW_EDIT_ICON, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Clicked table first row edit icon");
                flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_FIRST_NAME_FIELD, ElementType.Id);
                if (flag) {
                    Thread.sleep(2000);
                    editedName = UsersPageLocators.DEMO_SECOND_FIRST_NAME;
                    flag = webDB.clearText(UsersPageLocators.ADD_USER_POPUP_FIRST_NAME_FIELD, ElementType.Id);
                    if (flag) {
                        log.logging("info", "Cleared first name field");
                        WebElement firstNameField = webDB.getDriver().findElement(By.id(UsersPageLocators.ADD_USER_POPUP_FIRST_NAME_FIELD));
                        for (char c : editedName.toCharArray()) {
                            firstNameField.sendKeys(String.valueOf(c));
                            Thread.sleep(10);
                        }
                        log.logging("info", "Entered first name in popup");
                        flag = webDB.waitForElement(UsersPageLocators.EDIT_USER_UPDATE_BTN, ElementType.Xpath);
                        if (flag) {
                            flag = webDB.clickAnElement(UsersPageLocators.EDIT_USER_UPDATE_BTN, ElementType.Xpath);
                            if (flag) {
                                flag = webDB.waitForElement(UsersPageLocators.EDIT_USER_SUCCESS_POPUP, ElementType.Xpath);
                                if (flag) {
                                    WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(UsersPageLocators.EDIT_USER_SUCCESS_POPUP)));
                                }
                            }
                        }
                    }
                }
            }
        }
        return editedName;
    }

    /**
     * This method verifies editing existing record
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyEditExistingRecord(String email) throws InterruptedException {
        flag = verifyUsersPageNavigation();
        if (flag) {
            flag = verifyAddPopup();
            if (flag) {
                if(email.equals(webDB.getDataFromProperties("superAdminUsername"))){
                    flag = verifyFillUserDetailsAndSaveForSuperAdmin();
                }else{
                    flag = verifyFillUserDetailsAndSave();
                }
                if (flag) {
                    String newName = editFirstName();
                    flag = webDB.waitForElement(UsersPageLocators.TABLE_FIRST_ROW_NAME, ElementType.Xpath);
                    if(flag) {
                        String name = webDB.getTextFromElement(UsersPageLocators.TABLE_FIRST_ROW_NAME, ElementType.Xpath);
                        if (name.contains(newName)) {
                            log.logging("info", "Name got successfully edited");
                            flag = deleteCreatedUser();
                        } else {
                            flag = false;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies error message for empty field and clicks cancel
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyErrorForEmptyFieldAndClickCancel() {
        flag = webDB.waitForElement(UsersPageLocators.FIRSTNAME_FIELD_ERROR, ElementType.Xpath);
        if(flag){
            String error = webDB.getTextFromElement(UsersPageLocators.FIRSTNAME_FIELD_ERROR, ElementType.Xpath);
            if(error.equals(UsersPageLocators.EMPTY_FIRST_NAME_ERROR)){
                log.logging("info", "Error message is verified for empty first name field");
                String color = webDB.getDriver().findElement(By.xpath(UsersPageLocators.FIRSTNAME_FIELD_ERROR)).getCssValue("color");
                if(color.equals(UsersPageLocators.RED_COLOR_CSS)){
                    log.logging("info", "Error message is in red color as expected");
                    flag = webDB.waitForElement(UsersPageLocators.EDIT_USER_POPUP_CANCEL_BTN, ElementType.Xpath);
                    if(flag){
                        flag = webDB.clickAnElement(UsersPageLocators.EDIT_USER_POPUP_CANCEL_BTN, ElementType.Xpath);
                        if(flag) {
                            log.logging("info", "Clicked cancel button");
                            flag = webDB.isElementNotDisplayed(UsersPageLocators.ADD_USER_POPUP_CANCEL_BTN, ElementType.Xpath);
                        }
                    }
                }else{
                    flag = false;
                }
            }else{
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies alert message for empty field
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyAlertMessageForEmptyField() throws InterruptedException {
        flag = verifyUsersPageNavigation();
        if (flag) {
            flag = webDB.waitForElement(UsersPageLocators.TABLE_FIRST_ROW_EDIT_ICON, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(UsersPageLocators.TABLE_FIRST_ROW_EDIT_ICON, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked first row edit icon");
                    flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_FIRST_NAME_FIELD, ElementType.Id);
                    if(flag){
                        Thread.sleep(3000);
                        flag = webDB.clearText(UsersPageLocators.ADD_USER_POPUP_FIRST_NAME_FIELD, ElementType.Id);
                        if(flag){
                            log.logging("info", "Cleared first name field");
                            flag = webDB.waitForElement(UsersPageLocators.EDIT_USER_UPDATE_BTN, ElementType.Xpath);
                            if(flag){
                                flag = webDB.clickAnElement(UsersPageLocators.EDIT_USER_UPDATE_BTN, ElementType.Xpath);
                                if(flag){
                                    log.logging("info", "Clicked update button with empty value");
                                    flag = verifyErrorForEmptyFieldAndClickCancel();
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies default item in edit popup
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyDefaultItemInEditPopup() throws InterruptedException {
        flag = verifyUsersPageNavigation();
        if (flag) {
            flag = webDB.waitForElement(UsersPageLocators.TABLE_FIRST_ROW_EDIT_ICON, ElementType.Xpath);
            if (flag) {
                flag = webDB.clickAnElement(UsersPageLocators.TABLE_FIRST_ROW_EDIT_ICON, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Clicked first row edit icon");
                    Thread.sleep(2000);
                    flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_FIRST_NAME_FIELD, ElementType.Id);
                    if (flag) {
                        String firstName = webDB.getAttributeFromElement(UsersPageLocators.ADD_USER_POPUP_FIRST_NAME_FIELD, ElementType.Id, "value");
                        if (!firstName.isEmpty()) {
                            log.logging("info", "Firstname field has default value");
                            flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_LAST_NAME_FIELD, ElementType.Id);
                            if (flag) {
                                String lastName = webDB.getAttributeFromElement(UsersPageLocators.ADD_USER_POPUP_LAST_NAME_FIELD, ElementType.Id, "value");
                                if (!lastName.isEmpty()) {
                                    log.logging("info", "Lastname field has default value");
                                    flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_EMAIL_FIELD, ElementType.Id);
                                    if (flag) {
                                        String email = webDB.getAttributeFromElement(UsersPageLocators.ADD_USER_POPUP_EMAIL_FIELD, ElementType.Id, "value");
                                        if (!email.isEmpty()) {
                                            log.logging("info", "Email field has default value");
                                            //flag = verifyPopupClose();
                                            flag = verifyDeletePopupClose();
                                        } else {
                                            flag = false;
                                        }
                                    } else {
                                        flag = false;
                                    }
                                } else {
                                    flag = false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies alphabetical order sorting
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyAlphabeticalSorting() throws InterruptedException {
        flag = verifyUsersPageNavigation();
        if (flag) {
            flag = webDB.waitForElement(UsersPageLocators.NAME_HEADER, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(UsersPageLocators.NAME_HEADER, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked name header sorting option");
                    Thread.sleep(2000);
                    List<WebElement> columnElements = webDB.getDriver().findElements(By.xpath(UsersPageLocators.TABLE_ALL_NAMES));
                    List<String> originalList = new ArrayList<>();
                    for (WebElement element : columnElements) {
                        originalList.add(element.getText());
                    }
                    System.out.println(originalList);
                    List<String> sortedList = new ArrayList<>(originalList);
                    Collections.sort(sortedList);
                    System.out.println(sortedList);
                    if (originalList.equals(sortedList)) {
                        log.logging("info", "The column is sorted in alphabetical order.");
                    } else {
                        log.logging("info", "The column is NOT sorted in alphabetical order.");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies drop down option in add user popup
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyDropDownOnAddUserPopup() throws InterruptedException {
        flag = verifyUsersPageNavigation();
        if(flag) {
            flag = verifyAddPopup();
            if(flag){
                flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_USER_RADIO_BTN, ElementType.Id);
                if(flag){
                    flag = webDB.clickAnElement(UsersPageLocators.ADD_USER_POPUP_USER_RADIO_BTN, ElementType.Id);
                    if(flag) {
                        flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_COMPANY_DROPDOWN, ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Company dropdown is visible");
                            //flag = verifyPopupClose();
                            flag = verifyDeletePopupClose();
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies selecting dropdown options
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifySelectingDropDownOption() throws InterruptedException {
        flag = verifyUsersPageNavigation();
        if(flag) {
            flag = verifyAddPopup();
            if (flag) {
                Thread.sleep(1000);
                flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_USER_RADIO_BTN, ElementType.Id);
                if(flag) {
                    flag = webDB.clickAnElement(UsersPageLocators.ADD_USER_POPUP_USER_RADIO_BTN, ElementType.Id);
                    if (flag) {
                        log.logging("info", "Clicked user radio button");
                        flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_COMPANY_DROPDOWN, ElementType.Xpath);
                        if (flag) {
                            WebElement element = webDB.getDriver().findElement(By.xpath(UsersPageLocators.ADD_USER_POPUP_COMPANY_DROPDOWN));
                            Actions actions = new Actions(webDB.getDriver());
                            actions.click(element).sendKeys("galaxy").sendKeys(Keys.ENTER).build().perform();
                            flag = webDB.waitForElement(UsersPageLocators.DROPDOWN_OPTION, ElementType.Xpath);
                            if (flag) {
                                String value = webDB.getTextFromElement(UsersPageLocators.DROPDOWN_OPTION, ElementType.Xpath);
                                if (value.contains("galaxy")) {
                                    log.logging("info", "Selected dropdown option is visible");
                                    //flag = verifyPopupClose();
                                    flag = verifyDeletePopupClose();
                                } else {
                                    flag = false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies error for empty dropdown
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyErrorForEmptyDropDown() throws InterruptedException {
        flag = verifyUsersPageNavigation();
        if(flag) {
            flag = verifyAddPopup();
            if (flag) {
                flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_ADVANCED_USER_RADIO_BTN, ElementType.Id);
                if(flag){
                    flag = webDB.clickAnElement(UsersPageLocators.ADD_USER_POPUP_ADVANCED_USER_RADIO_BTN, ElementType.Id);
                    if(flag){
                        log.logging("info", "Clicked advanced user radio button");
                        flag = webDB.waitForElement(UsersPageLocators.ADD_USER_POPUP_SAVE_BTN, ElementType.Xpath);
                        if(flag){
                            flag = webDB.clickAnElement(UsersPageLocators.ADD_USER_POPUP_SAVE_BTN, ElementType.Xpath);
                            if(flag){
                                log.logging("info", "Clicked save button without entering dropdown value");
                                flag = webDB.waitForElement(UsersPageLocators.EMPTY_COMPANY_ERROR, ElementType.Xpath);
                                if(flag){
                                    log.logging("info", "Error message is verified for empty company field");
                                    //flag = verifyPopupClose();
                                    flag = verifyDeletePopupClose();
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }


}
