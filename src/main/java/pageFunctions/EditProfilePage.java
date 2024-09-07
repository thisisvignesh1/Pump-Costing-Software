package pageFunctions;

import locators.EditProfilePageLocators;
import locators.HomePageLocators;
import locators.LoginPageLocators;
import locators.PartsPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class EditProfilePage {

    WebDriverBase webDB = new WebDriverBase();
    ReportLoger log = new ReportLoger();
    CommonPage commonPage = new CommonPage();
    LoginPage loginPage = new LoginPage();
    String firstName;
    String lastName;
    boolean flag;
    String updatedFirstName;
    String updatedLastName;
    String editedFirstName;
    String editedLastName;

    /**
     * This method verifies edit profile page redirection
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyEditProfilePageRedirection() {
        flag = webDB.waitForElement(HomePageLocators.PROFILE_DROPDOWN, ElementType.Id);
        if(flag){
            flag = webDB.clickAnElement(HomePageLocators.PROFILE_DROPDOWN, ElementType.Id);
            if(flag){
                log.logging("info", "Clicked profile dropdown");
                flag = webDB.waitForElement(HomePageLocators.PROFILE_DROPDOWN_EDIT_PROFILE, ElementType.LinkText);
                if(flag){
                    flag = webDB.clickAnElement(HomePageLocators.PROFILE_DROPDOWN_EDIT_PROFILE, ElementType.LinkText);
                    if(flag){
                        log.logging("info", "Edit profile option from dropdown");
                        flag = webDB.waitForElement(EditProfilePageLocators.EDIT_PROFILE_HEADING, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Landed on edit profile page");
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies edit profile url security
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     * Added thread sleep time to avoid issue while verifying login page
     */
    public boolean verifyEditProfileUrlSecurity() throws InterruptedException {
        flag = webDB.navigateToUrl(webDB.getDataFromProperties("editProfileUrl"));
        if(flag){
            log.logging("info", "Trying to navigate to edit profile page without login");
            Thread.sleep(1000);
            flag = webDB.waitForElement(LoginPageLocators.LOGIN_HEADING, ElementType.Xpath);
            if(flag){
                log.logging("info", "The page is still in login page as expected");
            }
        }
        return flag;
    }

    /**
     * This method verifies updated details after refresh
     * @author Vignesh - GWL
     * @return boolean flag
     * Here used thread sleep time to ensure entering precise value on input field
     */
    public boolean verifyUpdatedDetailsAfterRefresh() throws InterruptedException {
        flag = verifyEditProfilePageRedirection();
        if(flag) {
            firstName = webDB.getAttributeFromElement(EditProfilePageLocators.FIRST_NAME, ElementType.Id, "value");
            log.logging("info", "Extracted first name is: " + firstName);
            WebElement firstNameField = webDB.getDriver().findElement(By.id(EditProfilePageLocators.FIRST_NAME));
            firstNameField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            firstNameField.sendKeys(Keys.DELETE);
            if (flag) {
                log.logging("info", "Cleared first name field");
                editedFirstName = firstName + "Edited";
                for (char c : editedFirstName.toCharArray()) {
                    firstNameField.sendKeys(String.valueOf(c));
                    Thread.sleep(10);
                }
                if (flag) {
                    log.logging("info", "Modified first name value");
                    lastName = webDB.getAttributeFromElement(EditProfilePageLocators.LAST_NAME, ElementType.Id, "value");
                    log.logging("info", "Extracted last name is: " + lastName);
                    WebElement lastNameField = webDB.getDriver().findElement(By.id(EditProfilePageLocators.LAST_NAME));
                    lastNameField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
                    lastNameField.sendKeys(Keys.DELETE);
                    if (flag) {
                        log.logging("info", "Cleared last name field");
                        editedLastName = lastName + "Edited";
                        for (char c : editedLastName.toCharArray()) {
                            lastNameField.sendKeys(String.valueOf(c));
                            Thread.sleep(10);
                        }
                        if (flag) {
                            log.logging("info", "Modified last name value");
                            flag = clickUpdateAndWaitForConfirmMsg();
                            if(flag){
                                webDB.navigateToRefresh();
                                flag = verifyUpdatedFirstAndLastName(editedFirstName, editedLastName);
                                if(flag){
                                    log.logging("info", "Updated details are verified after refresh");
                                    flag = changeFirstAndLastNameToOldOne(firstName, lastName);
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
     * This method verifies edit profile field components
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyEditProfileFieldComponents() {
        flag = verifyEditProfilePageRedirection();
        if(flag){
            flag = webDB.waitForElement(EditProfilePageLocators.EMAIL_ADDRESS, ElementType.Id);
            if(flag){
                log.logging("info", "Email id field is visible");
                flag = webDB.waitForElement(EditProfilePageLocators.FIRST_NAME, ElementType.Id);
                if(flag){
                    log.logging("info", "First name field is visible");
                    flag = webDB.waitForElement(EditProfilePageLocators.LAST_NAME, ElementType.Id);
                    if(flag){
                        log.logging("info", "Last name field is visible");
                            flag = webDB.waitForElement(EditProfilePageLocators.ROLE, ElementType.Id);
                            if(flag){
                                log.logging("info", "All components are visible in the edit profile page");
                            }
                        }
                    }
                }
            }
        return flag;
    }

    /**
     * This method verifies updated first and last name
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyUpdatedFirstAndLastName(String firstName, String lastName) throws InterruptedException {
        flag = webDB.waitForElement(EditProfilePageLocators.FIRST_NAME, ElementType.Id);
        if(flag){
            updatedFirstName = webDB.getAttributeFromElement(EditProfilePageLocators.FIRST_NAME, ElementType.Id, "value");
            log.logging("info", "Updated first name extracted is: "+updatedFirstName);
            if(updatedFirstName.equalsIgnoreCase(firstName)){
                log.logging("info", "First name got updated successfully");
                updatedLastName = webDB.getAttributeFromElement(EditProfilePageLocators.LAST_NAME, ElementType.Id, "value");
                log.logging("info", "Updated last name extracted is: "+updatedLastName);
                if(updatedLastName.equalsIgnoreCase(lastName)){
                    log.logging("info", "Last name got updated successfully");
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
     * This method verifies udpated details after logout
     * @author Vignesh - GWL
     * @return boolean flag
     * Here used thread sleep time to ensure entering precise value on input field
     */
    public boolean verifyUpdatedDetailsAfterLogout(String email, String password) throws InterruptedException {
        flag = verifyEditProfilePageRedirection();
        if(flag){
            firstName = webDB.getAttributeFromElement(EditProfilePageLocators.FIRST_NAME, ElementType.Id, "value");
            log.logging("info", "Extracted first name is: "+firstName);
            WebElement firstNameField = webDB.getDriver().findElement(By.id(EditProfilePageLocators.FIRST_NAME));
            firstNameField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            firstNameField.sendKeys(Keys.DELETE);
            if(flag){
                log.logging("info", "Cleared first name field");
                editedFirstName = firstName+"Edited";
                for (char c : editedFirstName.toCharArray()) {
                    firstNameField.sendKeys(String.valueOf(c));
                    Thread.sleep(10);
                }
                if(flag){
                    log.logging("info", "Modified first name value");
                    lastName = webDB.getAttributeFromElement(EditProfilePageLocators.LAST_NAME, ElementType.Id, "value");
                    log.logging("info", "Extracted last name is: "+lastName);
                    WebElement lastNameField = webDB.getDriver().findElement(By.id(EditProfilePageLocators.LAST_NAME));
                    lastNameField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
                    lastNameField.sendKeys(Keys.DELETE);
                    if(flag) {
                        log.logging("info", "Cleared last name field");
                        editedLastName = lastName+"Edited";
                        for (char c : editedLastName.toCharArray()) {
                            lastNameField.sendKeys(String.valueOf(c));
                            Thread.sleep(10);
                        }
                        if (flag) {
                            log.logging("info", "Modified last name value");
                            flag = clickUpdateAndWaitForConfirmMsg();
                            if(flag){
                                flag = commonPage.verifyLogout();
                                if(flag) {
                                    flag = loginPage.verifyValidLogin(email, password);
                                    if(flag) {
                                        flag = verifyEditProfilePageRedirection();
                                        if(flag) {
                                            flag = verifyUpdatedFirstAndLastName(editedFirstName, editedLastName);
                                            if (flag) {
                                                flag = changeFirstAndLastNameToOldOne(firstName, lastName);
                                            }
                                        }
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
     * This method changes first and last name into old one
     * @author Vignesh - GWL
     * @return boolean flag
     * Used thread sleep time to ensure entering precise value
     */
    public boolean changeFirstAndLastNameToOldOne(String firstName, String lastName) throws InterruptedException {
        WebElement firstNameField = webDB.getDriver().findElement(By.id(EditProfilePageLocators.FIRST_NAME));
        firstNameField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        firstNameField.sendKeys(Keys.DELETE);
        if(flag) {
            log.logging("info", "Cleared first name field");
            for (char c : firstName.toCharArray()) {
                firstNameField.sendKeys(String.valueOf(c));
                Thread.sleep(10);
            }
            WebElement lastNameField = webDB.getDriver().findElement(By.id(EditProfilePageLocators.LAST_NAME));
            lastNameField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            lastNameField.sendKeys(Keys.DELETE);
            if(flag) {
                log.logging("info", "Cleared first name field");
                for (char c : lastName.toCharArray()) {
                    lastNameField.sendKeys(String.valueOf(c));
                    Thread.sleep(10);
                }
                flag = clickUpdateAndWaitForConfirmMsg();
            }
        }
        return flag;
    }

    /**
     * This method clicks update button and waits for confirmation message
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean clickUpdateAndWaitForConfirmMsg() {
        flag = webDB.moveToElement(EditProfilePageLocators.UPDATE_BUTTON, ElementType.Xpath);
        if(flag) {
            flag = webDB.javaScriptClickAnElement(EditProfilePageLocators.UPDATE_BUTTON, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Clicked update button");
                flag = webDB.waitForElement(EditProfilePageLocators.UPDATE_CONFIRMATION, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Update confirmation message is visible");
                    WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(EditProfilePageLocators.UPDATE_CONFIRMATION)));
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies username profile update
     * @author Vignesh - GWL
     * @return boolean flag
     * Used thread sleep time to ensure entering precise value
     */
    public boolean verifyUserNameProfileUpdate() throws InterruptedException {
        flag = verifyEditProfilePageRedirection();
        if(flag){
            firstName = webDB.getAttributeFromElement(EditProfilePageLocators.FIRST_NAME, ElementType.Id, "value");
            log.logging("info", "Extracted first name is: "+firstName);
            WebElement firstNameField = webDB.getDriver().findElement(By.id(EditProfilePageLocators.FIRST_NAME));
            firstNameField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            firstNameField.sendKeys(Keys.DELETE);
            if(flag){
                log.logging("info", "Cleared first name field");
                editedFirstName = firstName+"Edited";
                for (char c : editedFirstName.toCharArray()) {
                    firstNameField.sendKeys(String.valueOf(c));
                    Thread.sleep(10);
                }
                if(flag){
                    log.logging("info", "Modified first name value");
                    lastName = webDB.getAttributeFromElement(EditProfilePageLocators.LAST_NAME, ElementType.Id, "value");
                    log.logging("info", "Extracted last name is: "+lastName);
                    WebElement lastNameField = webDB.getDriver().findElement(By.id(EditProfilePageLocators.LAST_NAME));
                    lastNameField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
                    lastNameField.sendKeys(Keys.DELETE);
                    if(flag) {
                        log.logging("info", "Cleared last name field");
                        editedLastName = lastName+"Edited";
                        for (char c : editedLastName.toCharArray()) {
                            lastNameField.sendKeys(String.valueOf(c));
                            Thread.sleep(10);
                        }
                        if (flag) {
                            log.logging("info", "Modified last name value");
                            flag = clickUpdateAndWaitForConfirmMsg();
                            if(flag){
                                flag = verifyUpdatedFirstAndLastName(editedFirstName, editedLastName);
                                if(flag){
                                    flag = changeFirstAndLastNameToOldOne(firstName, lastName);
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
     * This method verifies mandatory fields mark
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyMandatoryFieldsMark() {
        flag = verifyEditProfilePageRedirection();
        if(flag){
            flag = webDB.waitForElement(EditProfilePageLocators.FIRST_NAME_ASTERISK, ElementType.Xpath);
            if(flag){
                log.logging("info", "Mandatory field mark for first name is visible");
                flag = webDB.waitForElement(EditProfilePageLocators.LAST_NAME_ASTERISK, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Mandatory field mark for last name is visible");
                    flag = webDB.waitForElement(EditProfilePageLocators.EMAIL_ASTERISK, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "All mandatory fields are marked with star");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method clears first and last name field
     * @author Vignesh - GWL
     */
    public void clearFirstAndLastNameField(){
        WebElement firstNameField = webDB.getDriver().findElement(By.id(EditProfilePageLocators.FIRST_NAME));
        firstNameField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        firstNameField.sendKeys(Keys.DELETE);
        WebElement lastNameField = webDB.getDriver().findElement(By.id(EditProfilePageLocators.LAST_NAME));
        lastNameField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        lastNameField.sendKeys(Keys.DELETE);
    }

    /**
     * This method verifies empty field error messages
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyEmptyFieldErrorMessages() {
        flag = verifyEditProfilePageRedirection();
        if(flag){
            clearFirstAndLastNameField();
            flag = webDB.moveToElement(EditProfilePageLocators.UPDATE_BUTTON, ElementType.Xpath);
            if(flag) {
                flag = webDB.javaScriptClickAnElement(EditProfilePageLocators.UPDATE_BUTTON, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Clicked update button");
                    flag = webDB.waitForElement(EditProfilePageLocators.FNAME_ERROR_MESSAGE_FOR_EMPTY_FIELD, ElementType.Xpath);
                    if(flag){
                        String firstNameError = webDB.getTextFromElement(EditProfilePageLocators.FNAME_ERROR_MESSAGE_FOR_EMPTY_FIELD, ElementType.Xpath);
                        if(firstNameError.equals(EditProfilePageLocators.FIRST_NAME_EMPTY_ERROR)) {
                            log.logging("info", "Error message is verified for first name field");
                            flag = webDB.waitForElement(EditProfilePageLocators.LNAME_ERROR_MESSAGE_FOR_EMPTY_FIELD, ElementType.Xpath);
                            if (flag) {
                                String lastNameError = webDB.getTextFromElement(EditProfilePageLocators.LNAME_ERROR_MESSAGE_FOR_EMPTY_FIELD, ElementType.Xpath);
                                if(lastNameError.equals(EditProfilePageLocators.LAST_NAME_EMPTY_ERROR)) {
                                    log.logging("info", "Error message is verified for last name field");
                                    webDB.navigateToRefresh();
                                }else{
                                    flag = false;
                                }
                            }
                        }else{
                            flag = false;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method clicks back button in edit profile page
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean clickBackButton() {
        flag = webDB.waitForElement(EditProfilePageLocators.BACK_BUTTON, ElementType.Xpath);
        if(flag){
            flag = webDB.javaScriptClickAnElement(EditProfilePageLocators.BACK_BUTTON, ElementType.Xpath);
            if(flag){
                flag = webDB.waitForElement(HomePageLocators.HOME_PAGE_INFO, ElementType.Xpath);
            }
        }
        return flag;
    }

    /**
     * This method verifies save profile with invalid data
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifySaveProfileWithInvalidData() {
        flag = verifyEditProfilePageRedirection();
        if(flag){
            WebElement firstNameField = webDB.getDriver().findElement(By.id(EditProfilePageLocators.FIRST_NAME));
            firstNameField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            firstNameField.sendKeys(Keys.DELETE);
            flag = webDB.sendTextToAnElement(EditProfilePageLocators.FIRST_NAME, "$%^", ElementType.Id);
            if(flag){
                log.logging("info", "Invalid data entered on first name field");
                flag = webDB.moveToElement(EditProfilePageLocators.UPDATE_BUTTON, ElementType.Xpath);
                if(flag) {
                    flag = webDB.javaScriptClickAnElement(EditProfilePageLocators.UPDATE_BUTTON, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Clicked update button");
                        flag = webDB.waitForElement(EditProfilePageLocators.FNAME_ERROR_MESSAGE_FOR_EMPTY_FIELD, ElementType.Xpath);
                        if(flag){
                            String errorMsg = webDB.getTextFromElement(EditProfilePageLocators.FNAME_ERROR_MESSAGE_FOR_EMPTY_FIELD, ElementType.Xpath);
                            if(errorMsg.equals(EditProfilePageLocators.FIRST_NAME_ALPHANUMERIC_ERROR)){
                                log.logging("info", "Error message is verified");
                                flag = clickBackButton();
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
     * This method verifies cancel button functionality
     * @author Vignesh - GWL
     * @return boolean flag
     * Here used thread sleep time to ensure entering precise value on input field
     */
    public boolean verifyCancelButtonFunctionality() throws InterruptedException {
        flag = verifyEditProfilePageRedirection();
        if(flag){
            clearFirstAndLastNameField();
            log.logging("info", "Cleared first and last name field");
            firstName = webDB.getAttributeFromElement(EditProfilePageLocators.FIRST_NAME, ElementType.Id, "value");
            WebElement firstNameField = webDB.getDriver().findElement(By.id(EditProfilePageLocators.FIRST_NAME));
            for (char c : firstName.toCharArray()) {
                firstNameField.sendKeys(String.valueOf(c));
                Thread.sleep(10);
            }
            log.logging("info", "Modified first name");
            flag = webDB.moveToElement(EditProfilePageLocators.BACK_BUTTON, ElementType.Xpath);
            if(flag){
                flag = clickBackButton();
                if(flag){
                    log.logging("info", "Clicked back button and verified home page navigation");
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies updated name in top right menu
     * @param firstName
     * @param lastName
     * @return boolean flag
     */
    public boolean verifyUpdatedNameInTopRightMenu(String firstName, String lastName){
        flag = webDB.waitForElement(EditProfilePageLocators.USERNAME_ON_TOP_RIGHT_CORNER, ElementType.Xpath);
        if(flag){
            String name = webDB.getTextFromElement(EditProfilePageLocators.USERNAME_ON_TOP_RIGHT_CORNER, ElementType.Xpath);
            if(name.equals(firstName+" "+lastName)) {
                log.logging("info", "Updated name reflected on top right menu as expected");
            }else{
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies edited name on top right menu
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     * Here used thread sleep time to ensure entering precise value on input field
     */
    public boolean verifyEditedNameOnTopRightMenu() throws InterruptedException {
        flag = verifyEditProfilePageRedirection();
        if(flag){
            firstName = webDB.getAttributeFromElement(EditProfilePageLocators.FIRST_NAME, ElementType.Id, "value");
            log.logging("info", "Extracted first name is: "+firstName);
            WebElement firstNameField = webDB.getDriver().findElement(By.id(EditProfilePageLocators.FIRST_NAME));
            lastName = webDB.getAttributeFromElement(EditProfilePageLocators.LAST_NAME, ElementType.Id, "value");
            log.logging("info", "Extracted last name is: "+lastName);
            WebElement lastNameField = webDB.getDriver().findElement(By.id(EditProfilePageLocators.LAST_NAME));
            clearFirstAndLastNameField();
            log.logging("info", "Cleared first and last name");
            editedFirstName = firstName+"Edited";
            for (char c : editedFirstName.toCharArray()) {
                firstNameField.sendKeys(String.valueOf(c));
                Thread.sleep(10);
            }
            log.logging("info", "Modified first name value");
            editedLastName = lastName+"Edited";
            for (char c : editedLastName.toCharArray()) {
                lastNameField.sendKeys(String.valueOf(c));
                Thread.sleep(10);
            }
            log.logging("info", "Modified last name value");
            flag = clickUpdateAndWaitForConfirmMsg();
            if(flag){
                flag = verifyUpdatedFirstAndLastName(editedFirstName, editedLastName);
                if(flag){
                    flag = verifyUpdatedNameInTopRightMenu(editedFirstName, editedLastName);
                    if(flag) {
                        flag = changeFirstAndLastNameToOldOne(firstName, lastName);
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies updating profile with revert back
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     * Here used thread sleep time to ensure entering precise value on input field
     */
    public boolean verifyUpdatingProfileWithRevertBack() throws InterruptedException {
        flag = verifyEditProfilePageRedirection();
        if(flag){
            firstName = webDB.getAttributeFromElement(EditProfilePageLocators.FIRST_NAME, ElementType.Id, "value");
            log.logging("info", "Existing first name is: "+firstName);
            lastName = webDB.getAttributeFromElement(EditProfilePageLocators.LAST_NAME, ElementType.Id, "value");
            log.logging("info", "Existing last name is: "+lastName);
            WebElement firstNameField = webDB.getDriver().findElement(By.id(EditProfilePageLocators.FIRST_NAME));
            editedFirstName = firstName+" Edited";
            for (char c : editedFirstName.toCharArray()) {
                firstNameField.sendKeys(String.valueOf(c));
                Thread.sleep(10);
            }
            log.logging("info", "Modified first name");
            flag = webDB.moveToElement(EditProfilePageLocators.BACK_BUTTON, ElementType.Xpath);
            if(flag){
                flag = clickBackButton();
                if(flag){
                    log.logging("info", "Clicked back button and navigated to home page");
                    flag = verifyUpdatedNameInTopRightMenu(firstName, lastName);
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies edit profile page in new tab
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyEditProfilePageInNewTab() {
        flag = webDB.waitForElement(HomePageLocators.PROFILE_DROPDOWN, ElementType.Id);
        if(flag){
            flag = webDB.clickAnElement(HomePageLocators.PROFILE_DROPDOWN, ElementType.Id);
            if(flag){
                log.logging("info", "Clicked profile dropdown option");
                flag = webDB.waitForElement(HomePageLocators.PROFILE_DROPDOWN_EDIT_PROFILE, ElementType.LinkText);
                if(flag){
                    Actions actions = new Actions(webDB.getDriver());
                    WebElement editProfile = webDB.getDriver().findElement(By.linkText(HomePageLocators.PROFILE_DROPDOWN_EDIT_PROFILE));
                    actions.keyDown(Keys.CONTROL).click(editProfile).keyUp(Keys.CONTROL).build().perform();
                    log.logging("info", "Opened edit profile link in new tab");
                    webDB.switchToChildWindow();
                    String expectedUrl = webDB.getDataFromProperties("editProfileUrl");
                    String actualUrl = webDB.getDriver().getCurrentUrl();
                    if(expectedUrl.equals(actualUrl)){
                        log.logging("info", "Edit profile page opened in new tab as expected");
                        webDB.switchToChildWindow();
                    }else{
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method navigates to parts page
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean navigateToPartsPage() {
        flag = webDB.waitForElement(PartsPageLocators.PARTS_SIDE_MENU, ElementType.Xpath);
        if(flag){
            flag = webDB.moveToElement(PartsPageLocators.PARTS_SIDE_MENU, ElementType.Xpath);
            if(flag){
                flag = webDB.javaScriptClickAnElement(PartsPageLocators.PARTS_SIDE_MENU, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked parts option from side menu");
                    flag = webDB.waitForElement(PartsPageLocators.PARTS_PAGE_HEADING, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Landed on parts page");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies updated name in parts page
     * @author Vignesh - GWL
     * @param firstName
     * @param lastName
     * @return
     */
    public boolean verifyUpdatedNameInPartsPage(String firstName, String lastName) {
        flag = navigateToPartsPage();
        if(flag){
            flag = webDB.waitForElement(HomePageLocators.SIDE_MENU_CLOSE_ICON, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(HomePageLocators.SIDE_MENU_CLOSE_ICON, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked close icon from side menu");
                    flag = verifyUpdatedNameInTopRightMenu(firstName, lastName);
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies modified profile in other pages
     * @return boolean flag
     * @throws InterruptedException
     * Here used thread sleep time to ensure entering precise value on input field
     */
    public boolean verifyModifiedProfileInOtherPages() throws InterruptedException {
        flag = verifyEditProfilePageRedirection();
        if(flag){
            firstName = webDB.getAttributeFromElement(EditProfilePageLocators.FIRST_NAME, ElementType.Id, "value");
            log.logging("info", "Extracted first name is: "+firstName);
            WebElement firstNameField = webDB.getDriver().findElement(By.id(EditProfilePageLocators.FIRST_NAME));
            lastName = webDB.getAttributeFromElement(EditProfilePageLocators.LAST_NAME, ElementType.Id, "value");
            log.logging("info", "Extracted last name is: "+lastName);
            WebElement lastNameField = webDB.getDriver().findElement(By.id(EditProfilePageLocators.LAST_NAME));
            clearFirstAndLastNameField();
            log.logging("info", "Cleared first and last name");
            editedFirstName = firstName+"Edited";
            for (char c : editedFirstName.toCharArray()) {
                firstNameField.sendKeys(String.valueOf(c));
                Thread.sleep(10);
            }
            log.logging("info", "Modified first name value");
            editedLastName = lastName+"Edited";
            for (char c : editedLastName.toCharArray()) {
                lastNameField.sendKeys(String.valueOf(c));
                Thread.sleep(10);
            }
            log.logging("info", "Modified last name value");
            flag = clickUpdateAndWaitForConfirmMsg();
            if(flag){
                flag = verifyUpdatedFirstAndLastName(editedFirstName, editedLastName);
                if(flag){
                    flag = verifyUpdatedNameInTopRightMenu(editedFirstName, editedLastName);
                    if(flag) {
                        //flag = verifyUpdatedNameInPartsPage(editedFirstName, editedLastName);
                        if(flag) {
                            flag = verifyEditProfilePageRedirection();
                            if(flag) {
                                flag = changeFirstAndLastNameToOldOne(firstName, lastName);
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies email address in profile edit page
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyEmailAddressInProfileEditPage() {
        flag = verifyEditProfilePageRedirection();
        if(flag) {
            flag = webDB.waitForElement(EditProfilePageLocators.EMAIL_ADDRESS, ElementType.Id);
            if (flag) {
                String email = webDB.getAttributeFromElement(EditProfilePageLocators.EMAIL_ADDRESS, ElementType.Id, "value");
                log.logging("info", "Extracted email is: " + email);
                String executionMode = System.getProperty("executionMode");
                String expectedEmail = null;
                List<String> allUserEmails = new ArrayList<>();
                switch (executionMode.toLowerCase()) {
                    case "advanceduser":
                        expectedEmail = webDB.getDataFromProperties("advancedUserUsername");
                        break;
                    case "normaluser":
                        expectedEmail = webDB.getDataFromProperties("normalUserUsername");
                        break;
                    case "companyadmin":
                        expectedEmail = webDB.getDataFromProperties("companyAdminUsername");
                        break;
                    case "superadmin":
                        expectedEmail = webDB.getDataFromProperties("superAdminUsername");
                        break;
                    case "allusers":
                        allUserEmails.add(webDB.getDataFromProperties("advancedUserUsername"));
                        allUserEmails.add(webDB.getDataFromProperties("normalUserUsername"));
                        allUserEmails.add(webDB.getDataFromProperties("companyAdminUsername"));
                        allUserEmails.add(webDB.getDataFromProperties("superAdminUsername"));
                        if (allUserEmails.contains(email)) {
                            log.logging("info", "Email matches one of the expected values in all users: " + email);
                            return true;
                        } else {
                            log.logging("info", "No matching email found in all users. Extracted email: " + email);
                            return false;
                        }
                    default:
                        log.logging("info", "Invalid execution mode: " + executionMode);
                        return false;
                }
                flag = email.equals(expectedEmail);
                if (!flag) {
                    log.logging("info", "Email does not match expected value. Expected: " + expectedEmail + ", Found: " + email);
                } else {
                    log.logging("info", "Email matches expected value: " + expectedEmail);
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies error message for special character
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyErrorOnFirstNameField() {
        flag = webDB.waitForElement(EditProfilePageLocators.FNAME_ERROR_MESSAGE_FOR_EMPTY_FIELD, ElementType.Xpath);
        if(flag){
            String errorMsg = webDB.getTextFromElement(EditProfilePageLocators.FNAME_ERROR_MESSAGE_FOR_EMPTY_FIELD, ElementType.Xpath);
            if(errorMsg.equals(EditProfilePageLocators.FIRST_NAME_ALPHANUMERIC_ERROR)){
                log.logging("info", "Error message is verified");
            }else{
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies same username and email error
     * @author Vignesh - GWL
     * @return boolean flag
     * Here used thread sleep time to ensure entering precise value on input field
     */
    public boolean verifySameUsernameAndEmailError() throws InterruptedException {
        flag = verifyEditProfilePageRedirection();
        if(flag){
            String email = webDB.getAttributeFromElement(EditProfilePageLocators.EMAIL_ADDRESS, ElementType.Id, "value");
            log.logging("info", "Extracted email from email field is: "+email);
            flag = webDB.clearTextField(EditProfilePageLocators.FIRST_NAME, ElementType.Id);
            if(flag){
                log.logging("info", "Cleared existing firstname");
                WebElement firstNameField = webDB.getDriver().findElement(By.id(EditProfilePageLocators.FIRST_NAME));
                for (char c : email.toCharArray()) {
                    firstNameField.sendKeys(String.valueOf(c));
                    Thread.sleep(10);
                }
                flag = webDB.moveToElement(EditProfilePageLocators.UPDATE_BUTTON, ElementType.Xpath);
                if(flag){
                    flag = webDB.javaScriptClickAnElement(EditProfilePageLocators.UPDATE_BUTTON, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Clicked update button");
                        flag = verifyErrorOnFirstNameField();
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies clickable links on edit profile
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyClickableLinksOnEditProfile() throws InterruptedException {
        flag = verifyEditProfilePageRedirection();
        if(flag){
            flag = clickBackButton();
            if(flag){
                log.logging("info", "Clicked back and verified navigation to home page");
                flag = verifyUserNameProfileUpdate();
            }
        }
        return flag;
    }

    /**
     * This method verifies non editable fields
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyNonEditableFields() {
        flag = verifyEditProfilePageRedirection();
        if(flag){
            String username = webDB.getAttributeFromElement(EditProfilePageLocators.FIRST_NAME, ElementType.Id, "value");
            if(username.equalsIgnoreCase(EditProfilePageLocators.SUPER_ADMIN_FIRSTNAME)){
                String roleAttribute = webDB.getAttributeFromElement(EditProfilePageLocators.ROLE, ElementType.Id, "readonly");
                flag = roleAttribute != null;
                if(flag){
                    log.logging("info", "Role field cannot be edited as expected");
                }
            }else{
                String companyNameAttribute = webDB.getAttributeFromElement(EditProfilePageLocators.COMPANY_NAME, ElementType.Id, "readonly");
                flag = companyNameAttribute != null;
                if(flag) {
                    log.logging("info", "Company name field cannot be edited as expected");
                    String roleAttribute = webDB.getAttributeFromElement(EditProfilePageLocators.ROLE, ElementType.Id, "readonly");
                    flag = roleAttribute != null;
                    if (flag) {
                        log.logging("info", "Role field cannot be edited as expected");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies logout after updated details
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyLogoutAfterUpdatedDetails() throws InterruptedException {
        flag = verifyUserNameProfileUpdate();
        if(flag){
            flag = webDB.isElementNotDisplayed(LoginPageLocators.LOGIN_HEADING, ElementType.Xpath);
            if(flag){
                log.logging("info", "After update, haven't got logged out as expected");
            }
        }
        return flag;
    }
}
