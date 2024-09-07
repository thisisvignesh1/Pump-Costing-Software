package pageFunctions;

import locators.HomePageLocators;
import locators.LoginPageLocators;
import locators.ResetPasswordPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;
import java.io.IOException;
import java.util.List;

public class LoginPage {
    WebDriverBase webDB = new WebDriverBase();
    ReportLoger log = new ReportLoger();
    ExcelUtils excelUtils = new ExcelUtils();
    CommonPage commonPage = new CommonPage();
    boolean flag;

    /**
     * This method verifies valid login
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyValidLogin(String email, String password) {
        flag = clearLoginFields();
        if(flag) {
            flag = webDB.waitForElement(LoginPageLocators.LOGIN_HEADING, ElementType.Xpath);
            if (flag) {
                flag = enterValidEmailAndPassword(email, password);
                if (flag) {
                    flag = webDB.waitForElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
                    if (flag) {
                        flag = webDB.clickAnElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
                        if (flag) {
                            flag = verifyLandingOnHomePage();
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies landing on home page
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyLandingOnHomePage() {
        //flag = webDB.waitForElement(UsersPageLocators.USERS_TITLE, ElementType.Xpath);
        flag = webDB.waitForElement(HomePageLocators.HOME_PAGE_INFO, ElementType.Xpath);
        return flag;
    }

    /**
     * This method clears login fields
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean clearLoginFields() {
        flag = webDB.waitForElement(LoginPageLocators.EMAIL_TEXT_FIELD, ElementType.Name);
        if(flag){
            flag = webDB.clearText(LoginPageLocators.EMAIL_TEXT_FIELD, ElementType.Name);
            if(flag){
                flag = webDB.waitForElement(LoginPageLocators.PASSWORD_TEXT_FIELD, ElementType.Name);
                if(flag){
                    flag = webDB.clearText(LoginPageLocators.PASSWORD_TEXT_FIELD, ElementType.Name);
                }
            }
        }
        return flag;
    }

    /**
     * This method enters valid email and password
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean enterValidEmailAndPassword(String email, String password) {
        flag = webDB.waitForElement(LoginPageLocators.EMAIL_TEXT_FIELD, ElementType.Name);
        if (flag) {
            flag = webDB.sendTextToAnElement(LoginPageLocators.EMAIL_TEXT_FIELD, email, ElementType.Name);
            if (flag) {
                flag = webDB.waitForElement(LoginPageLocators.PASSWORD_TEXT_FIELD, ElementType.Name);
                if (flag) {
                    flag = webDB.sendTextToAnElement(LoginPageLocators.PASSWORD_TEXT_FIELD, password, ElementType.Name);
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies login page links
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyLoginPageLinks() {
        flag = webDB.waitForElement(LoginPageLocators.LOGIN_HEADING, ElementType.Xpath);
        if (flag) {
            log.logging("info", "Landed on login page");
            flag = webDB.waitForElement(LoginPageLocators.SAVE_CREDENTIALS_CHECKBOX, ElementType.Xpath);
            if (flag) {
                flag = webDB.clickAnElement(LoginPageLocators.SAVE_CREDENTIALS_CHECKBOX, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Clicked save credentials checkbox");
                    flag = webDB.waitForElement(LoginPageLocators.RESET_BUTTON, ElementType.LinkText);
                    if (flag) {
                        flag = webDB.clickAnElement(LoginPageLocators.RESET_BUTTON, ElementType.LinkText);
                        if (flag) {
                            log.logging("info", "Clicked reset button");
                            flag = webDB.waitForElement(ResetPasswordPageLocators.RESET_PASSWORD_HEADING, ElementType.Xpath);
                            if (flag) {
                                log.logging("info", "Successfully navigated to password reset page");
                                flag = webDB.waitForElement(ResetPasswordPageLocators.BACK_TO_LOGIN, ElementType.LinkText);
                                if (flag) {
                                    flag = webDB.clickAnElement(ResetPasswordPageLocators.BACK_TO_LOGIN, ElementType.LinkText);
                                    if (flag) {
                                        log.logging("info", "Clicked back to login");
                                        //
                                        commonPage.clearLocalStorage();
                                        webDB.navigateToRefresh();
                                        flag = false;
                                        //
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
//
    /**
     * This method verifies error message for invalid email ids
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws IOException
     * @throws InterruptedException
     * Here used thread sleep time to ensure entering precise value on input field
     */
    public boolean verifyErrorMessageForInvalidEmailId() throws InterruptedException, IOException {
        flag = clearLoginFields();
        if(flag) {
            flag = webDB.waitForElement(LoginPageLocators.LOGIN_HEADING, ElementType.Xpath);
            if (flag) {
                String[][] data = excelUtils.readAllDataInExcel(webDB.getDataFromProperties("invalidEmailIdsExcel"), "Sheet1");
                for (int i = 0; i < data.length; i++) {
                    for (int j = 0; j < data[i].length; j++) {
                        WebElement emailField = webDB.getDriver().findElement(By.name(LoginPageLocators.EMAIL_TEXT_FIELD));
                        Thread.sleep(1000);
                        emailField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
                        emailField.sendKeys(data[i][j]);
                        log.logging("info", "Entered invalid email id"+": " + data[i][j]);
                        WebElement passwordField = webDB.getDriver().findElement(By.name(LoginPageLocators.PASSWORD_TEXT_FIELD));
                        Thread.sleep(1000);
                        passwordField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
                        passwordField.sendKeys(LoginPageLocators.COMPANY_LOGIN_PASSWORD);
                        flag = webDB.waitForElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
                        if(flag) {
                            webDB.clickAnElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
                            flag = webDB.waitForElement(LoginPageLocators.ERROR_MESSAGE, ElementType.Xpath);
                            if(flag) {
                                String errorMsg = webDB.getTextFromElement(LoginPageLocators.ERROR_MESSAGE, ElementType.Xpath);
                                if (errorMsg.equals(LoginPageLocators.ERROR_MESSAGE_FOR_INVALID_EMAIL)) {
                                    log.logging("info", "Error message verified for value " +": " + data[i][j]);
                                }
                            }
                        }
                    }
                }
                log.logging("info", "Error message is verified for all invalid email ids");
                hardClearLoginFields();
            }
        }
        return flag;
    }

    /**
     * This method clears login fields
     * @author Vignesh - GWL
     * @throws InterruptedException
     * Used thread sleep time to ensure precisely clear fields
     */
    public void hardClearLoginFields() throws InterruptedException {
        WebElement emailField = webDB.getDriver().findElement(By.name(LoginPageLocators.EMAIL_TEXT_FIELD));
        Thread.sleep(1000);
        emailField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        WebElement passwordField = webDB.getDriver().findElement(By.name(LoginPageLocators.PASSWORD_TEXT_FIELD));
        Thread.sleep(1000);
        passwordField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
    }

    /**
     * This method enters space on front for email
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean enterSpaceOnFrontEmailAndValidPassword() {
        flag = webDB.waitForElement(LoginPageLocators.EMAIL_TEXT_FIELD, ElementType.Name);
        if (flag) {
            flag = webDB.sendTextToAnElement(LoginPageLocators.EMAIL_TEXT_FIELD, LoginPageLocators.SPACE_ON_FRONT_LOGIN_EMAIL, ElementType.Name);
            if (flag) {
                log.logging("info", "Entered email id with space as first character");
                flag = webDB.waitForElement(LoginPageLocators.PASSWORD_TEXT_FIELD, ElementType.Name);
                if (flag) {
                    flag = webDB.sendTextToAnElement(LoginPageLocators.PASSWORD_TEXT_FIELD, LoginPageLocators.COMPANY_LOGIN_PASSWORD, ElementType.Name);
                    if (flag) {
                        log.logging("info", "Entered valid password on password field");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies accepting space in front of email field
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyAcceptingSpaceInFrontEmailField() {
        flag = clearLoginFields();
        if(flag) {
            flag = webDB.waitForElement(LoginPageLocators.LOGIN_HEADING, ElementType.Xpath);
            if (flag) {
                flag = enterSpaceOnFrontEmailAndValidPassword();
                if (flag) {
                    flag = webDB.waitForElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
                    if (flag) {
                        flag = webDB.clickAnElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
                        if (flag) {
                            flag = verifyLandingOnHomePage();
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method enters space on last email and valid password
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean enterSpaceOnLastEmailSpaceAndValidPassword() {
        flag = webDB.waitForElement(LoginPageLocators.EMAIL_TEXT_FIELD, ElementType.Name);
        if (flag) {
            flag = webDB.sendTextToAnElement(LoginPageLocators.EMAIL_TEXT_FIELD, LoginPageLocators.SPACE_ON_LAST_LOGIN_EMAIL, ElementType.Name);
            if (flag) {
                log.logging("info", "Entered email id with space as last character");
                flag = webDB.waitForElement(LoginPageLocators.PASSWORD_TEXT_FIELD, ElementType.Name);
                if (flag) {
                    flag = webDB.sendTextToAnElement(LoginPageLocators.PASSWORD_TEXT_FIELD, LoginPageLocators.COMPANY_LOGIN_PASSWORD, ElementType.Name);
                    if (flag) {
                        log.logging("info", "Entered valid password on password field");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies accepting space in last email field
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyAcceptingSpaceInLastEmailField() {
        flag = clearLoginFields();
        if(flag) {
            flag = webDB.waitForElement(LoginPageLocators.LOGIN_HEADING, ElementType.Xpath);
            if (flag) {
                flag = enterSpaceOnLastEmailSpaceAndValidPassword();
                if (flag) {
                    flag = webDB.waitForElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
                    if (flag) {
                        flag = webDB.clickAnElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
                        if (flag) {
                            flag = verifyLandingOnHomePage();
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies error message for empty email
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyErrorMsgForEmptyEmail() {
        flag = webDB.waitForElement(LoginPageLocators.ERROR_MESSAGE, ElementType.Xpath);
        if(flag){
            log.logging("info", "Error message is visible");
            String errorMsg = webDB.getTextFromElement(LoginPageLocators.ERROR_MESSAGE, ElementType.Xpath);
            log.logging("info", "Error message extracted from the field is: "+errorMsg);
            if(errorMsg.equals(LoginPageLocators.ERROR_MESSAGE_FOR_EMPTY_EMAIL)){
                log.logging("info", "Error message is verified");
            }
        }
        return flag;
    }

    /**
     * This method verifies empt email and error message
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyEmptyEmailErrorMessage() throws InterruptedException {
        flag = clearLoginFields();
        if(flag) {
            flag = webDB.waitForElement(LoginPageLocators.LOGIN_HEADING, ElementType.Xpath);
            if (flag) {
                flag = webDB.waitForElement(LoginPageLocators.PASSWORD_TEXT_FIELD, ElementType.Name);
                if (flag) {
                    flag = webDB.sendTextToAnElement(LoginPageLocators.PASSWORD_TEXT_FIELD, LoginPageLocators.COMPANY_LOGIN_PASSWORD, ElementType.Name);
                    if (flag) {
                        log.logging("info", "Entered valid password on password field");
                        flag = webDB.waitForElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
                        if (flag) {
                            flag = webDB.clickAnElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
                            if (flag) {
                                flag = verifyErrorMsgForEmptyEmail();
                            }
                        }
                    }
                }
            }
        }
        hardClearLoginFields();
        return flag;
    }

    /**
     * This method verifies email and password field empty and enabled
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyEmailAndPasswordFieldEmptyAndEnabled() {
        flag = webDB.waitForElement(LoginPageLocators.EMAIL_TEXT_FIELD, ElementType.Name);
        if(flag){
            String emailValue = webDB.getAttributeFromElement(LoginPageLocators.EMAIL_TEXT_FIELD, ElementType.Name, "value");
            if(emailValue.isEmpty()){
                log.logging("info", "By default email text field is empty as expected");
                String passwordValue = webDB.getAttributeFromElement(LoginPageLocators.PASSWORD_TEXT_FIELD, ElementType.Name, "value");
                if(passwordValue.isEmpty()) {
                    log.logging("info", "By default password text field is empty as expected");
                    flag = webDB.waitForElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
                    if(flag){
                        WebElement loginButton = webDB.getDriver().findElement(By.xpath(LoginPageLocators.LOGIN_BUTTON));
                        if(loginButton.isEnabled()){
                            log.logging("info", "Login button is enabled by default as expected");
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies empty email and password field error
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyEmptyEmailPasswordError() {
        flag = webDB.waitForElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
        if(flag){
            flag = webDB.clickAnElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
            if(flag){
                log.logging("info", "Login button is clicked without email and password");
                flag = webDB.waitForElement(LoginPageLocators.ERROR_MSG_FOR_EMPTY_EMAIL, ElementType.Xpath);
                if(flag){
                    String emailError = webDB.getTextFromElement(LoginPageLocators.ERROR_MSG_FOR_EMPTY_EMAIL, ElementType.Xpath);
                    if(emailError.equals(LoginPageLocators.ERROR_MESSAGE_FOR_EMPTY_EMAIL)){
                        log.logging("info", "Error message verified for email field");
                        flag = webDB.waitForElement(LoginPageLocators.ERROR_MSG_FOR_EMPTY_PWD, ElementType.Xpath);
                        if(flag){
                            String passwordError = webDB.getTextFromElement(LoginPageLocators.ERROR_MSG_FOR_EMPTY_PWD, ElementType.Xpath);
                            if(passwordError.equals(LoginPageLocators.ERROR_MESSAGE_FOR_EMPTY_PWD)){
                                log.logging("info", "Error message verified for password field");
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
        return flag;
    }

    /**
     * This method enters space on last email and valid password
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean enterValidEmailAnd6CharsPassword() {
        flag = webDB.waitForElement(LoginPageLocators.EMAIL_TEXT_FIELD, ElementType.Name);
        if (flag) {
            flag = webDB.sendTextToAnElement(LoginPageLocators.EMAIL_TEXT_FIELD, LoginPageLocators.SPACE_ON_LAST_LOGIN_EMAIL, ElementType.Name);
            if (flag) {
                log.logging("info", "Entered valid email id");
                flag = webDB.waitForElement(LoginPageLocators.PASSWORD_TEXT_FIELD, ElementType.Name);
                if (flag) {
                    flag = webDB.sendTextToAnElement(LoginPageLocators.PASSWORD_TEXT_FIELD, LoginPageLocators.SIX_CHAR_PASSWORD, ElementType.Name);
                    if (flag) {
                        log.logging("info", "Entered password with 6 chars on password field");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies password with less than six characters
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyPasswordWithLessThanSixChars() throws InterruptedException {
        flag = clearLoginFields();
        if(flag) {
            flag = webDB.waitForElement(LoginPageLocators.LOGIN_HEADING, ElementType.Xpath);
            if (flag) {
                flag = enterValidEmailAnd6CharsPassword();
                if (flag) {
                    flag = webDB.waitForElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
                    if (flag) {
                        flag = webDB.clickAnElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
                        if (flag) {
                            flag = verifyErrorMessageForSixCharsPwd();
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies error messae for six character password
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyErrorMessageForSixCharsPwd() throws InterruptedException {
        flag = webDB.waitForElement(LoginPageLocators.INVALID_CREDENTIALS_ERROR_MSG, ElementType.Xpath);
        if(flag){
            log.logging("info", "Error message is visible");
            String errorMsg = webDB.getTextFromElement(LoginPageLocators.INVALID_CREDENTIALS_ERROR_MSG, ElementType.Xpath);
            if(errorMsg.equals(LoginPageLocators.INVALID_CREDENTIAL_ERROR)){
                log.logging("info", "Error message is verified");
            }
            hardClearLoginFields();
        }
        return flag;
    }

    /**
     * This method verifies error message for invalid passwords
     * @author Vignesh - GWL
     * @return boolean flag
     * Here used thread sleep time to ensure entering precise value on input field
     */
    public boolean verifyErrorMessageForInvalidPasswords() throws InterruptedException {
        flag = clearLoginFields();
        if (flag) {
            flag = webDB.waitForElement(LoginPageLocators.LOGIN_HEADING, ElementType.Xpath);
            if (flag) {
                List<String> passwords = commonPage.readPasswordFromCsv(webDB.getDataFromProperties("invalidPasswordCsv"));
                for (int i = 0; i < passwords.size(); i++) {
                    WebElement emailField = webDB.getDriver().findElement(By.name(LoginPageLocators.EMAIL_TEXT_FIELD));
                    Thread.sleep(1000);
                    emailField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
                    emailField.sendKeys(LoginPageLocators.COMPANY_LOGIN_EMAIL);
                    WebElement passwordField = webDB.getDriver().findElement(By.name(LoginPageLocators.PASSWORD_TEXT_FIELD));
                    Thread.sleep(1000);
                    passwordField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
                    passwordField.sendKeys(passwords.get(i));
                    log.logging("info", "Entered value: "+passwords.get(i));
                    flag = webDB.waitForElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
                    if (flag) {
                        webDB.clickAnElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
                        flag = webDB.waitForElement(LoginPageLocators.INVALID_CREDENTIALS_ERROR_MSG, ElementType.Xpath);
                        if (flag) {
                            String errorMsg = webDB.getTextFromElement(LoginPageLocators.INVALID_CREDENTIALS_ERROR_MSG, ElementType.Xpath);
                            if (errorMsg.equals(LoginPageLocators.INVALID_CREDENTIAL_ERROR)) {
                                log.logging("info", "Error message is verified for value: "+passwords.get(i));
                            }
                        }
                    }
                }
                log.logging("info", "Error message is verified for all invalid passwords");
                hardClearLoginFields();
            }
        }
        return flag;
    }

    /**
     * This method enters space on last password and valid email
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean enterSpaceOnLastPwdAndValidEmail() {
        flag = webDB.waitForElement(LoginPageLocators.EMAIL_TEXT_FIELD, ElementType.Name);
        if (flag) {
            flag = webDB.sendTextToAnElement(LoginPageLocators.EMAIL_TEXT_FIELD, LoginPageLocators.COMPANY_LOGIN_EMAIL, ElementType.Name);
            if (flag) {
                log.logging("info", "Entered valid email id");
                flag = webDB.waitForElement(LoginPageLocators.PASSWORD_TEXT_FIELD, ElementType.Name);
                if (flag) {
                    flag = webDB.sendTextToAnElement(LoginPageLocators.PASSWORD_TEXT_FIELD, LoginPageLocators.COMPANY_LOGIN_PASSWORD, ElementType.Name);
                    if (flag) {
                        log.logging("info", "Entered password with space character on last");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies accepting space in last password field
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyAcceptingSpaceInLastPwdField() {
        flag = clearLoginFields();
        if(flag) {
            flag = webDB.waitForElement(LoginPageLocators.LOGIN_HEADING, ElementType.Xpath);
            if (flag) {
                flag = enterSpaceOnLastPwdAndValidEmail();
                if (flag) {
                    flag = webDB.waitForElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
                    if (flag) {
                        flag = webDB.clickAnElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
                        if (flag) {
                            flag = verifyLandingOnHomePage();
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies error message for invalid credentials
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyErrorMessageForInvalidCreds() throws InterruptedException {
        flag = webDB.waitForElement(LoginPageLocators.INVALID_CREDENTIALS_ERROR_MSG, ElementType.Xpath);
        if(flag){
            String errorMsg = webDB.getTextFromElement(LoginPageLocators.INVALID_CREDENTIALS_ERROR_MSG, ElementType.Xpath);
            log.logging("info", "Extracted error message is: "+errorMsg);
            if(errorMsg.equals(LoginPageLocators.EMAIL_NOT_REGISTERED_ERROR)){
                log.logging("info", "Error message is verified");
            }
            hardClearLoginFields();
        }
        return flag;
    }

    /**
     * This method verifies error message with invalid email land password
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyLoginPageWithInvalidEmailPwd() throws InterruptedException {
            flag = webDB.waitForElement(LoginPageLocators.LOGIN_HEADING, ElementType.Xpath);
            if (flag) {
                flag = webDB.waitForElement(LoginPageLocators.EMAIL_TEXT_FIELD, ElementType.Name);
                if(flag){
                    flag = webDB.sendTextToAnElement(LoginPageLocators.EMAIL_TEXT_FIELD,LoginPageLocators.INVALID_EMAIL, ElementType.Name);
                    if(flag){
                        log.logging("info", "Sent invalid email on email field");
                        flag = webDB.waitForElement(LoginPageLocators.PASSWORD_TEXT_FIELD, ElementType.Name);
                        if(flag) {
                            flag = webDB.sendTextToAnElement(LoginPageLocators.PASSWORD_TEXT_FIELD, LoginPageLocators.INVALID_PASSWORD, ElementType.Name);
                            if (flag) {
                                log.logging("info", "Send invalid password on password field");
                                if (flag) {
                                    flag = webDB.waitForElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
                                    if (flag) {
                                        flag = webDB.clickAnElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
                                        if (flag) {
                                            flag = verifyErrorMessageForInvalidCreds();
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
     * This method verifies login page with empty password
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyLoginPageUpWithSqlInjection() throws InterruptedException {
            flag = webDB.waitForElement(LoginPageLocators.LOGIN_HEADING, ElementType.Xpath);
            if (flag) {
                flag = webDB.waitForElement(LoginPageLocators.EMAIL_TEXT_FIELD, ElementType.Name);
                if (flag) {
                    flag = webDB.sendTextToAnElement(LoginPageLocators.EMAIL_TEXT_FIELD, LoginPageLocators.COMPANY_LOGIN_EMAIL, ElementType.Name);
                    if (flag) {
                        log.logging("info", "Sent valid email on email field");
                        flag = webDB.waitForElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
                        if (flag) {
                            flag = webDB.clickAnElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
                            if (flag) {
                                log.logging("info", "Clicked login button without entering password");
                                flag = webDB.waitForElement(LoginPageLocators.ERROR_MESSAGE, ElementType.Xpath);
                                if(flag){
                                    String errorMsg = webDB.getTextFromElement(LoginPageLocators.ERROR_MESSAGE, ElementType.Xpath);
                                    if(errorMsg.equals(LoginPageLocators.ERROR_MESSAGE_FOR_EMPTY_PWD)){
                                        log.logging("info", "Error message is verified for empty password");
                                    }
                                    hardClearLoginFields();
                                }
                            }
                        }
                    }
                }
            }
        return flag;
    }

    /**
     * This method verifies show password button
     * @author Vignesh - GWL
     * @return boolean flag
     * Here added thread sleep time to change the attribute value
     */
    public boolean verifyShowPasswordButton() throws InterruptedException {
        String pwdType = webDB.getAttributeFromElement(LoginPageLocators.PASSWORD_TEXT_FIELD, ElementType.Name, "type");
        if(pwdType.equals("password")){
            log.logging("info", "By default password field type is password");
            String text = webDB.getTextFromElement(LoginPageLocators.PASSWORD_TEXT_FIELD, ElementType.Name);
            if(text.isEmpty()){
                log.logging("info", "Without enabling view password, password is not visible");
                flag = webDB.waitForElement(LoginPageLocators.PASSWORD_FIELD_EYE, ElementType.Xpath);
                if(flag){
                    flag = webDB.clickAnElement(LoginPageLocators.PASSWORD_FIELD_EYE, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Clicked eye logo");
                        Thread.sleep(1000);
                        String typeAfter = webDB.getAttributeFromElement(LoginPageLocators.PASSWORD_TEXT_FIELD, ElementType.Name, "type");
                        if(typeAfter.equals("text")) {
                            log.logging("info", "After enabling view password, password is visible");
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies show password option on login page
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyShowPasswordOption(String email, String password) throws InterruptedException {
        flag = clearLoginFields();
        if(flag) {
            flag = webDB.waitForElement(LoginPageLocators.LOGIN_HEADING, ElementType.Xpath);
            if (flag) {
                flag = enterValidEmailAndPassword(email, password);
                if (flag) {
                    flag = verifyShowPasswordButton();
                    if(flag) {
                        flag = webDB.waitForElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
                        if (flag) {
                            flag = webDB.clickAnElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
                            if (flag) {
                                flag = verifyLandingOnHomePage();
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies credentials are saved
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyCredentialsAreSaved() {
        flag = commonPage.verifyLogout();
        if(flag){
            flag = webDB.waitForElement(LoginPageLocators.EMAIL_TEXT_FIELD, ElementType.Name);
            if(flag){
                String email = webDB.getAttributeFromElement(LoginPageLocators.EMAIL_TEXT_FIELD, ElementType.Name, "value");
                if(email.contains("@")){
                    log.logging("info", "Credentials are saved as expected");
                    flag = webDB.waitForElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
                    if(flag){
                        flag = webDB.clickAnElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Clicked login button");
                            flag = verifyLandingOnHomePage();
                            commonPage.clearCookies();
                            commonPage.clearLocalStorage();
                        }
                    }
                }else{
                    log.logging("info", "Credentials are not saved");
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies save credentials option
     * @author Vignesh - GWL
     * @return boolean flag
     * Added thread sleep time to avoid error while clicking login
     */
    public boolean verifySaveCredentialsOption(String email, String password) throws InterruptedException {
        flag = clearLoginFields();
        if(flag) {
            flag = webDB.waitForElement(LoginPageLocators.LOGIN_HEADING, ElementType.Xpath);
            if (flag) {
                flag = enterValidEmailAndPassword(email, password);
                if (flag) {
                    flag = webDB.waitForElement(LoginPageLocators.SAVE_CREDENTIALS_CHECKBOX, ElementType.Xpath);
                    if(flag){
                        flag = webDB.clickAnElement(LoginPageLocators.SAVE_CREDENTIALS_CHECKBOX, ElementType.Xpath);
                        Thread.sleep(1000);
                        if(flag){
                            log.logging("info", "Clicked save credentials checkbox");
                            flag = webDB.waitForElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
                            if(flag){
                                flag = webDB.clickAnElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
                                if(flag){
                                    flag = verifyCredentialsAreSaved();
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
     * This method verifies save credentials disable state
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifySaveCredentialsDisableState() {
        flag = webDB.waitForElement(LoginPageLocators.SAVE_CREDENTIALS_CHECKBOX, ElementType.Xpath);
        if(flag){
            WebElement element = webDB.getDriver().findElement(By.xpath(LoginPageLocators.SAVE_CREDENTIALS_CHECKBOX));
            if(element.isSelected()){
                log.logging("fail", "Save credentials is already selected which should not be a case");
                flag = false;
            }else{
                log.logging("info", "Save credentials is not selected by default as expected");
            }
        }
        return flag;
    }

    /**
     * This method verifies unchecked save credentials
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifySaveCredentialsUnchecked() throws InterruptedException {
        flag = webDB.waitForElement(LoginPageLocators.EMAIL_TEXT_FIELD, ElementType.Name);
        if (flag) {
            String emailF = webDB.getAttributeFromElement(LoginPageLocators.EMAIL_TEXT_FIELD, ElementType.Name, "value");
            if (emailF.isEmpty()) {
                log.logging("info", "Credentials are not saved as expected");
            }else{
                log.logging("fail", "Issue on save credentials option");
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies eye icon security
     * @author Vignesh - GWL
     * @return boolean flag
     * Added thread sleep time to give enough time to change attribute value
     */
    public boolean verifyEyeIconSecurity() throws InterruptedException {
        flag = webDB.waitForElement(LoginPageLocators.PASSWORD_TEXT_FIELD, ElementType.Name);
        if(flag){
            flag = webDB.sendTextToAnElement(LoginPageLocators.PASSWORD_TEXT_FIELD,LoginPageLocators.COMPANY_LOGIN_PASSWORD, ElementType.Name);
            if(flag){
                log.logging("info", "Entered password on password field");
                String attrBefore = webDB.getAttributeFromElement(LoginPageLocators.PASSWORD_TEXT_FIELD, ElementType.Name, "type");
                if(attrBefore.equalsIgnoreCase("password")){
                    log.logging("info", "By default password is not visible");
                    flag = webDB.waitForElement(LoginPageLocators.PASSWORD_FIELD_EYE, ElementType.Xpath);
                    if(flag){
                        flag = webDB.clickAnElement(LoginPageLocators.PASSWORD_FIELD_EYE, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Clicked eye icon");
                            Thread.sleep(1000);
                            String attrAfter = webDB.getAttributeFromElement(LoginPageLocators.PASSWORD_TEXT_FIELD, ElementType.Name, "type");
                            if(attrAfter.equalsIgnoreCase("text")){
                                log.logging("info", "After clicking eye icon, password is visible");
                            }else{
                                log.logging("fail", "Issue on eye icon verification");
                                flag = false;
                            }
                        }
                    }
                }
            }
        }
        hardClearLoginFields();
        return flag;
    }

    /**
     * This method verifies access page after logout
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     * Here added sleep time to avoid error while navigation to login page
     */
    public boolean verifyAccessPageAfterLogout() throws InterruptedException {
        flag = commonPage.verifyLogout();
        if(flag){
            log.logging("info", "Trying to access protected page after logout");
            flag = webDB.navigateToUrl(webDB.getDataFromProperties("editProfileUrl"));
            if(flag){
                log.logging("info", "Sent protected page url in address bar");
                Thread.sleep(1000);
                flag = webDB.waitForElement(LoginPageLocators.LOGIN_HEADING, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Page not navigated to protected page as expected");
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies back button in browser
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyBackButtonInBrowser() {
        flag = webDB.navigateToBack();
        if(flag){
            log.logging("info", "Pressed back on the browser");
            flag = webDB.waitForElement(LoginPageLocators.LOGIN_HEADING, ElementType.Xpath);
            if(flag){
                log.logging("info", "After clicking back, still in login page as expected");
            }
        }
        return flag;
    }


}
