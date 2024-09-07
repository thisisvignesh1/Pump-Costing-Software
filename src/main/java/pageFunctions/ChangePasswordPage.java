package pageFunctions;

import locators.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

import java.time.Duration;
import java.util.List;

public class ChangePasswordPage {

    WebDriverBase webDB = new WebDriverBase();
    ReportLoger log = new ReportLoger();
    LoginPage loginPage = new LoginPage();
    CommonPage commonPage = new CommonPage();
    EditProfilePage editProfilePage = new EditProfilePage();
    ExcelUtils excelUtils = new ExcelUtils();
    boolean flag;

    /**
     * This method verifies change password page redirection
     * @gokul - GWL
     * @return boolean flag
     */
    public boolean verifyChangePasswordPageRedirection() {
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
                        flag = webDB.waitForElement(ChangePasswordPageLocators.CHANGE_PASSWORD_HEADING, ElementType.Xpath);
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
     * This method verifies change password url security
     * @author Gokul - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyChangePasswordUrlSecurity() throws InterruptedException {
        flag = webDB.navigateToUrl(webDB.getDataFromProperties("editProfileUrl"));
        if(flag){
            log.logging("info", "Trying to navigate to change password page without login");
            Thread.sleep(1000);
            flag = webDB.waitForElement(LoginPageLocators.LOGIN_HEADING, ElementType.Xpath);
            if(flag){
                log.logging("info", "The page is still in login page as expected");
            }
        }
        return flag;
    }

    /**
     * This method verifies all fields have placeholder
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyAllFieldsHavePlaceholder() {
        flag = verifyChangePasswordPageRedirection();
        if(flag){
            flag = webDB.waitForElement(ChangePasswordPageLocators.CURRENT_PASSWORD_FIELD, ElementType.Id);
            if(flag){
                String currentPwd = webDB.getAttributeFromElement(ChangePasswordPageLocators.CURRENT_PASSWORD_FIELD, ElementType.Id, "placeholder");
                if(!currentPwd.isEmpty()){
                    log.logging("info", "Current password field has placeholder");
                    flag = webDB.waitForElement(ChangePasswordPageLocators.NEW_PASSWORD_FIELD, ElementType.Id);
                    if(flag) {
                        String newPwd = webDB.getAttributeFromElement(ChangePasswordPageLocators.NEW_PASSWORD_FIELD, ElementType.Id, "placeholder");
                        if (!newPwd.isEmpty()) {
                            log.logging("info", "New password field has placeholder");
                            flag = webDB.waitForElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_FIELD, ElementType.Id);
                            if(flag) {
                                String confirmPwd = webDB.getAttributeFromElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_FIELD, ElementType.Id, "placeholder");
                                if (!confirmPwd.isEmpty()) {
                                    log.logging("info", "Confirm password field has placeholder");
                                }else{
                                    flag = false;
                                }
                            }
                        }else{
                            flag = false;
                        }
                    }
                }else{
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies error message for length new password
     * @auhor Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyErrorForLengthyNewPassword() throws InterruptedException {
        Thread.sleep(1000);
        flag = webDB.waitForElement(ChangePasswordPageLocators.NEW_PASSWORD_ERROR, ElementType.Xpath);
        if(flag){
            String error = webDB.getTextFromElement(ChangePasswordPageLocators.NEW_PASSWORD_ERROR, ElementType.Xpath);
            if(error.equals(ChangePasswordPageLocators.MAXIMUM_LENGTH_NEW_PWD)){
                log.logging("info", "Error message verified for maximum length new password");
            }else{
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies error message for length confirm password
     * @auhor Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyErrorForLengthyConfirmPassword() {
        flag = webDB.waitForElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_ERROR, ElementType.Xpath);
        if(flag){
            String error = webDB.getTextFromElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_ERROR, ElementType.Xpath);
            if(error.equals(ChangePasswordPageLocators.MAXIMUM_LENGTH_NEW_PWD)){
                log.logging("info", "Error message verified for maximum length confirm password");
            }else{
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method sends max limit new password and clicks update
     * @author Gokul - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean sendMaxLimitNewPasswordAndClickUpdate() throws InterruptedException {
        flag = webDB.waitForElement(ChangePasswordPageLocators.NEW_PASSWORD_FIELD, ElementType.Id);
        if(flag) {
            flag = webDB.sendTextToAnElement(ChangePasswordPageLocators.NEW_PASSWORD_FIELD, ChangePasswordPageLocators.LENGTH_LIMIT_PASSWORD, ElementType.Id);
            if (flag) {
                log.logging("info", "New lengthy password entered on field");
                flag = webDB.moveToElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                if (flag) {
                    flag = webDB.clickAnElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Clicked update button");
                        Thread.sleep(1000);
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies new password maximum character limit
     * @author Gokul - GWL
     * @param currentPassword
     * @return boolean flag
     */
    public boolean verifyNewPasswordMaxCharacterLimit(String currentPassword) throws InterruptedException {
        flag = verifyChangePasswordPageRedirection();
        if(flag){
            flag = webDB.waitForElement(ChangePasswordPageLocators.CURRENT_PASSWORD_FIELD, ElementType.Id);
            if(flag){
                flag = webDB.sendTextToAnElement(ChangePasswordPageLocators.CURRENT_PASSWORD_FIELD, currentPassword, ElementType.Id);
                if(flag){
                    log.logging("info", "Current password entered on field");
                    flag = sendMaxLimitNewPasswordAndClickUpdate();
                    if(flag){
                        flag = verifyErrorForLengthyNewPassword();
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method sends minimum length new password and clicks update
     * @author Gokul - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean sendMinLimitNewPasswordAndClickUpdate() throws InterruptedException {
        flag = webDB.waitForElement(ChangePasswordPageLocators.NEW_PASSWORD_FIELD, ElementType.Id);
        if(flag) {
            flag = webDB.sendTextToAnElement(ChangePasswordPageLocators.NEW_PASSWORD_FIELD, ChangePasswordPageLocators.MINIMUM_LENGTH_NEW_PWD, ElementType.Id);
            if (flag) {
                log.logging("info", "New minimum lengthy password entered on field");
                flag = webDB.moveToElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                if (flag) {
                    flag = webDB.clickAnElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Clicked update button");
                        Thread.sleep(1000);
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies error for short new password
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyErrorForShortNewPassword() {
        flag = webDB.waitForElement(ChangePasswordPageLocators.NEW_PASSWORD_ERROR, ElementType.Xpath);
        if(flag){
            String error = webDB.getTextFromElement(ChangePasswordPageLocators.NEW_PASSWORD_ERROR, ElementType.Xpath);
            if(error.equals(ChangePasswordPageLocators.MINIMUM_LENGTH_NEW_PWD_ERROR)){
                log.logging("info", "Error message verified for minimum length new password");
            }else{
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies new password minimum character limit
     * @param password
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyNewPasswordMinCharacterLimit(String password) throws InterruptedException {
        flag = verifyChangePasswordPageRedirection();
        if(flag) {
            flag = webDB.waitForElement(ChangePasswordPageLocators.CURRENT_PASSWORD_FIELD, ElementType.Id);
            if (flag) {
                flag = webDB.sendTextToAnElement(ChangePasswordPageLocators.CURRENT_PASSWORD_FIELD, password, ElementType.Id);
                if (flag) {
                    log.logging("info", "Current password entered on field");
                    flag = sendMinLimitNewPasswordAndClickUpdate();
                    if(flag){
                        flag = verifyErrorForShortNewPassword();
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies error messae for empty fields
     * @author Gokul - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean VerifyErrorMessageForEmptyFields() throws InterruptedException {
        flag = verifyChangePasswordPageRedirection();
        if(flag) {
            flag = webDB.moveToElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                if(flag){
                    Thread.sleep(1000);
                    log.logging("info", "Clicked update button with empty fields");
                    flag = webDB.waitForElement(ChangePasswordPageLocators.CURRENT_PASSWORD_ERROR, ElementType.Xpath);
                    if(flag){
                        String currentError = webDB.getTextFromElement(ChangePasswordPageLocators.CURRENT_PASSWORD_ERROR, ElementType.Xpath);
                        if(currentError.equals(ChangePasswordPageLocators.CURRENT_PWD_EMPTY_ERROR)){
                            log.logging("info", "Error message verified for current password field");
                            flag = webDB.waitForElement(ChangePasswordPageLocators.NEW_PASSWORD_ERROR, ElementType.Xpath);
                            if(flag){
                                String newError = webDB.getTextFromElement(ChangePasswordPageLocators.NEW_PASSWORD_ERROR, ElementType.Xpath);
                                if(newError.equals(ChangePasswordPageLocators.NEW_PWD_EMPTY_ERROR)){
                                    log.logging("info", "Error message verified for new password field");
                                    flag = webDB.waitForElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_ERROR, ElementType.Xpath);
                                    if(flag){
                                        String confirmError = webDB.getTextFromElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_ERROR, ElementType.Xpath);
                                        if(confirmError.equals(ChangePasswordPageLocators.CONFIRM_PWD_EMPTY_ERROR)){
                                            log.logging("info", "Error message verified for confirm password field");
                                        }else{
                                            flag = false;
                                        }
                                    }
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
     * This method checks error message for invalid current password
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean checkErrorMessageForInvalidCurrentPwd() {
        flag = webDB.waitForElement(ChangePasswordPageLocators.CURRENT_PASSWORD_ERROR, ElementType.Xpath);
        if(flag){
            String error = webDB.getTextFromElement(ChangePasswordPageLocators.CURRENT_PASSWORD_ERROR, ElementType.Xpath);
            if(error.equals(ChangePasswordPageLocators.CURRENT_INVALID_PWD)){
                log.logging("info", "Error message is verified");
                String color = webDB.getDriver().findElement(By.xpath(ChangePasswordPageLocators.CURRENT_PASSWORD_ERROR)).getCssValue("color");
                if(color.equals(UsersPageLocators.RED_COLOR_CSS)) {
                    log.logging("info", "Error message is in red color as expected");
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
     * This method verifies invalid new password error
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyInvalidCurrentPasswordError() {
        flag = verifyChangePasswordPageRedirection();
        if(flag){
            flag = enterValueOnCurrentPwdField(ChangePasswordPageLocators.INVALID_PASSWORD);
            if(flag){
                flag = webDB.moveToElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                if(flag){
                    flag = webDB.clickAnElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Clicked update button");
                        flag = checkErrorMessageForInvalidCurrentPwd();
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method clicks update and wait for logout
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean clickUpdateAndWaitForLogout() {
        flag = webDB.moveToElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
        if(flag){
            flag = webDB.clickAnElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked update button");
                flag = webDB.waitForElement(ChangePasswordPageLocators.UPDATE_PASSWORD_SUCCESS_MESSAGE, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Success message is verified verified for update password");
                    WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(ChangePasswordPageLocators.UPDATE_PASSWORD_SUCCESS_MESSAGE)));
                    flag = webDB.waitForElement(LoginPageLocators.LOGIN_HEADING, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Logged out after changing password");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies change password to default
     * @author Gokul - GWL
     * @param password
     * @return boolean flag
     */
    public boolean changePasswordToDefault(String password){
        flag = verifyChangePasswordPageRedirection();
        if(flag) {
            flag = enterValueOnCurrentPwdField(ChangePasswordPageLocators.RANDOM_PASSWORD);
            if(flag){
                flag = enterValueOnNewPwdField(password);
                if(flag){
                    flag = enterValueOnConfirmPwdField(password);
                    if(flag){
                        flag = clickUpdateAndWaitForLogout();
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies change password functionalit
     * @author Gokul - GWL
     * @param email
     * @param password
     * @return
     */
    public boolean verifyChangePasswordFunctionality(String email, String password) {
        String currentEmail = email;
        String currentPassword = password;
        flag = verifyChangePasswordPageRedirection();
        if(flag){
            flag = webDB.waitForElement(ChangePasswordPageLocators.CURRENT_PASSWORD_FIELD, ElementType.Id);
            if(flag){
                flag = webDB.sendTextToAnElement(ChangePasswordPageLocators.CURRENT_PASSWORD_FIELD, password, ElementType.Id);
                if(flag){
                    log.logging("info", "Entered current password");
                    flag = webDB.waitForElement(ChangePasswordPageLocators.NEW_PASSWORD_FIELD, ElementType.Id);
                    if(flag){
                        flag = webDB.sendTextToAnElement(ChangePasswordPageLocators.NEW_PASSWORD_FIELD,ChangePasswordPageLocators.RANDOM_PASSWORD, ElementType.Id);
                        if(flag){
                            log.logging("info", "Entered new password");
                            flag = webDB.waitForElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_FIELD, ElementType.Id);
                            if(flag) {
                                flag = webDB.sendTextToAnElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_FIELD, ChangePasswordPageLocators.RANDOM_PASSWORD, ElementType.Id);
                                if (flag) {
                                    log.logging("info", "Entered confirm password");
                                    flag = clickUpdateAndWaitForLogout();
                                    if(flag) {
                                        flag = loginPage.verifyValidLogin(currentEmail, ChangePasswordPageLocators.RANDOM_PASSWORD);
                                        if(flag){
                                            log.logging("info", "Successfully logged in with new credential");
                                            flag = changePasswordToDefault(currentPassword);
                                            if(flag){
                                                flag = loginPage.verifyValidLogin(currentEmail, currentPassword);
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
     * This method verifies error for invalid current password
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyErrorForInvalidCurrentPassword() {
        flag = webDB.waitForElement(ChangePasswordPageLocators.CURRENT_PASSWORD_ERROR, ElementType.Xpath);
        if(flag){
            String error = webDB.getTextFromElement(ChangePasswordPageLocators.CURRENT_PASSWORD_ERROR, ElementType.Xpath);
            if(error.equals(ChangePasswordPageLocators.CURRENT_INVALID_PWD)){
                log.logging("info", "Error message verified for current password");
            }else{
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies change password with invalid old password
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyChangePwdWithInvalidOldPwd() {
        flag = verifyChangePasswordPageRedirection();
        if(flag) {
            flag = enterValueOnCurrentPwdField(ChangePasswordPageLocators.INVALID_PASSWORD);
            if(flag){
                flag = enterValueOnNewPwdField(ChangePasswordPageLocators.RANDOM_PASSWORD);
                if(flag){
                    flag = enterValueOnConfirmPwdField(ChangePasswordPageLocators.RANDOM_PASSWORD);
                    if(flag){
                        flag = webDB.moveToElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                        if(flag) {
                            flag = webDB.clickAnElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                            if (flag) {
                                log.logging("info", "Clicked update button");
                                flag = verifyErrorForInvalidCurrentPassword();
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method enters value on current password field
     * @author Gokul - GWL
     * @param currentPwd
     * @return boolean flag
     */
    public boolean enterValueOnCurrentPwdField(String currentPwd){
        flag = webDB.waitForElement(ChangePasswordPageLocators.CURRENT_PASSWORD_FIELD, ElementType.Id);
        if(flag){
            flag = webDB.sendTextToAnElement(ChangePasswordPageLocators.CURRENT_PASSWORD_FIELD,currentPwd, ElementType.Id);
            if(flag){
                log.logging("info", "Text passed on current password field");
            }
        }
        return flag;
    }

    /**
     * This method enters value on new password field
     * @author Gokul - GWL
     * @param newPwd
     * @return boolean flag
     */
    public boolean enterValueOnNewPwdField(String newPwd){
        flag = webDB.waitForElement(ChangePasswordPageLocators.NEW_PASSWORD_FIELD, ElementType.Id);
        if(flag){
            flag = webDB.sendTextToAnElement(ChangePasswordPageLocators.NEW_PASSWORD_FIELD,newPwd, ElementType.Id);
            if(flag){
                log.logging("info", "Text passed on new password field");
            }
        }
        return flag;
    }

/**
 * This method enters value on confirm password field
 * @author Gokul - GWL
 * @param confirmPwd
 * @return boolean flag
 */
public boolean enterValueOnConfirmPwdField(String confirmPwd){
    flag = webDB.waitForElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_FIELD, ElementType.Id);
    if(flag){
        flag = webDB.sendTextToAnElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_FIELD,confirmPwd, ElementType.Id);
        if(flag){
            log.logging("info", "Text passed on confirm password field");
        }
    }
    return flag;
}

    /**
     * This method verifies error for confirm password field
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyErrorForConfirmPwdField() {
        flag = webDB.waitForElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_ERROR, ElementType.Xpath);
        if(flag){
            String error = webDB.getTextFromElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_ERROR, ElementType.Xpath);
            if(error.equals(ChangePasswordPageLocators.CONFIRM_PWD_EMPTY_ERROR)){
                log.logging("info", "Error message is verified for confirm password field");
            }else{
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies change password with empty confirm password field
     * @author Gokul - GWL
     * @param password
     * @return boolean flag
     */
    public boolean verifyChangePwdWithEmptyConfirmPwd(String password) {
        flag = verifyChangePasswordPageRedirection();
        if(flag) {
            flag = enterValueOnCurrentPwdField(password);
            if(flag){
                flag = enterValueOnNewPwdField(ChangePasswordPageLocators.RANDOM_PASSWORD);
                if(flag){
                    flag = webDB.moveToElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                    if(flag) {
                        flag = webDB.clickAnElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Clicked update button");
                            flag = verifyErrorForConfirmPwdField();
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies error for empty new password field
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyErrorForEmptyNewPwdField() {
        flag = webDB.waitForElement(ChangePasswordPageLocators.NEW_PASSWORD_ERROR, ElementType.Xpath);
        if(flag){
            String error = webDB.getTextFromElement(ChangePasswordPageLocators.NEW_PASSWORD_ERROR, ElementType.Xpath);
            if(error.equals(ChangePasswordPageLocators.NEW_PWD_EMPTY_ERROR)){
                log.logging("info", "Error message is verified for new password field");
            }else{
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies change password with empty new password
     * @author Gokul - GWL
     * @param password
     * @return boolean flag
     */
    public boolean verifyChangePwdWithEmptyNewPwd(String password){
        flag = verifyChangePasswordPageRedirection();
        if(flag) {
            flag = enterValueOnCurrentPwdField(password);
            if (flag) {
                flag = enterValueOnConfirmPwdField(ChangePasswordPageLocators.RANDOM_PASSWORD);
                if (flag) {
                    flag = webDB.moveToElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                    if(flag) {
                        flag = webDB.clickAnElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Clicked update button");
                            flag = verifyErrorForEmptyNewPwdField();
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies error for empty confirm password field
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyErrorForEmptyConfirmField() {
        flag = webDB.waitForElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_ERROR, ElementType.Xpath);
        if(flag){
            String error = webDB.getTextFromElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_ERROR, ElementType.Xpath);
            if(error.equals(ChangePasswordPageLocators.CONFIRM_PWD_EMPTY_ERROR)){
                log.logging("info", "Error message is verified for confirm password field");
            }else{
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies error for empty new and confirm password
     * @author Gokul - GWL
     * @param password
     * @return boolean flag
     */
    public boolean verifyErrorForEmptyNewAndConfirmPwd(String password) {
        flag = verifyChangePasswordPageRedirection();
        if(flag) {
            flag = enterValueOnCurrentPwdField(password);
            if (flag) {
                flag = webDB.moveToElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                if(flag) {
                    flag = webDB.clickAnElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Clicked update button");
                        flag = verifyErrorForEmptyNewPwdField();
                        if(flag){
                            flag = verifyErrorForEmptyConfirmField();
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies error for space in new and confirm password
     * @author Gokul - GWL
     * @param password
     * @return boolean flag
     */
    public boolean verifyErrorForSpaceInNewAndConfirmPwd(String password) {
        flag = verifyChangePasswordPageRedirection();
        if(flag) {
            flag = enterValueOnCurrentPwdField(password);
            if (flag) {
                flag = enterValueOnNewPwdField(" ");
                if(flag){
                    flag = enterValueOnConfirmPwdField(" ");
                    if(flag){
                        flag = webDB.moveToElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                        if(flag) {
                            flag = webDB.clickAnElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                            if (flag) {
                                log.logging("info", "Clicked update button");
                                flag = verifyErrorForShortNewPassword();
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies error for new and confirm password length
     * @author Gokul - GWL
     * @param password
     * @return boolean flag
     */
    public boolean verifyErrorForNewAndConfirmPwdLength(String password) throws InterruptedException {
        flag = verifyChangePasswordPageRedirection();
        if(flag) {
            flag = enterValueOnCurrentPwdField(password);
            if (flag) {
                flag = enterValueOnNewPwdField(ChangePasswordPageLocators.LENGTH_LIMIT_PASSWORD);
                if(flag){
                    flag = enterValueOnConfirmPwdField(ChangePasswordPageLocators.LENGTH_LIMIT_PASSWORD);
                    if(flag){
                        flag = webDB.moveToElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                        if(flag) {
                            flag = webDB.clickAnElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                            if (flag) {
                                log.logging("info", "Clicked update button");
                                flag = verifyErrorForLengthyNewPassword();
//                                if(flag){
//                                    flag = verifyErrorForLengthyConfirmPassword();
//                                }
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies star symol on mandatory fields
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyStarSymbolOnMandatoryFields() {
        flag = verifyChangePasswordPageRedirection();
        if(flag) {
            flag = webDB.waitForElement(ChangePasswordPageLocators.CURRENT_PASSWORD_ASTERISK, ElementType.Xpath);
            if(flag){
                log.logging("info", "Mandatory sign is visible for current password field");
                flag = webDB.waitForElement(ChangePasswordPageLocators.NEW_PASSWORD_ASTERISK, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Mandatory sign is visible for new password field");
                    flag = webDB.waitForElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_ASTERISK, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Mandatory sign is visible for confirm password field");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies change password button is clickable
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyChangePasswordButtonIsClickable() {
        flag = verifyChangePasswordPageRedirection();
        if(flag) {
            flag = webDB.moveToElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
            if(flag){
                WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ChangePasswordPageLocators.UPDATE_BUTTON)));
            }
        }
        return flag;
    }

    /**
     * This method verifies error message for not entering mixed password
     * @author Gokul - GWL
     * @param password
     * @return boolean flag
     */
    public boolean verifyErrorMessageForNotEnteringMixedPassword(String password) {
        flag = verifyChangePasswordPageRedirection();
        if(flag) {
            flag = enterValueOnCurrentPwdField(password);
            if (flag) {
                flag = enterValueOnNewPwdField(ChangePasswordPageLocators.PLAIN_PASSWORD);
                if (flag) {
                    flag = enterValueOnConfirmPwdField(ChangePasswordPageLocators.PLAIN_PASSWORD);
                    if (flag) {
                        flag = webDB.moveToElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                        if (flag) {
                            flag = webDB.clickAnElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                            if (flag) {
                                log.logging("info", "Clicked update button");
                                flag = webDB.waitForElement(ChangePasswordPageLocators.NEW_PASSWORD_ERROR, ElementType.Xpath);
                                if(flag){
                                    String error = webDB.getTextFromElement(ChangePasswordPageLocators.NEW_PASSWORD_ERROR, ElementType.Xpath);
                                    if(error.equals(ChangePasswordPageLocators.INVALID_NEW_PASSWORD_ERROR)){
                                        log.logging("info", "Error message is verified for plain password");
                                    }else{
                                        flag = false;
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
     * This method verifies login with old credentials
     * @author Gokul - GWL
     * @param email
     * @param password
     * @return boolean flag
     */
    public boolean tryLoginWithOldCredentials(String email, String password) {
        flag = webDB.waitForElement(LoginPageLocators.LOGIN_HEADING, ElementType.Xpath);
        if (flag) {
            flag = loginPage.enterValidEmailAndPassword(email, password);
            if (flag) {
                flag = webDB.waitForElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
                if (flag) {
                    flag = webDB.clickAnElement(LoginPageLocators.LOGIN_BUTTON, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Clicked login with old credentials");
                        flag = webDB.waitForElement(LoginPageLocators.INVALID_CREDENTIALS_ERROR_MSG, ElementType.Xpath);
                        if(flag){
                            String error = webDB.getTextFromElement(LoginPageLocators.INVALID_CREDENTIALS_ERROR_MSG, ElementType.Xpath);
                            if(error.equals(LoginPageLocators.INVALID_CREDENTIAL_ERROR)){
                                log.logging("info", "Issue verified while login with old credentials");
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
     * This method verifies issue while login with old credentials
     * @param email
     * @param password
     * @return boolean flag
     */
    public boolean verifyIssueWhileLoginWithOldCredentials(String email, String password) {
        String currentEmail = email;
        String currentPassword = password;
        flag = verifyChangePasswordPageRedirection();
        if(flag){
            flag = enterValueOnCurrentPwdField(password);
            if(flag){
                flag = enterValueOnNewPwdField(ChangePasswordPageLocators.RANDOM_PASSWORD);
                if(flag){
                    flag = enterValueOnConfirmPwdField(ChangePasswordPageLocators.RANDOM_PASSWORD);
                    if(flag) {
                        flag = clickUpdateAndWaitForLogout();
                        if (flag) {
                            flag = tryLoginWithOldCredentials(email, password);
                            if (flag) {
                                flag = loginPage.verifyValidLogin(currentEmail, ChangePasswordPageLocators.RANDOM_PASSWORD);
                                if (flag) {
                                    log.logging("info", "Successfully logged in with new credential");
                                    flag = changePasswordToDefault(currentPassword);
                                    if (flag) {
                                        flag = loginPage.verifyValidLogin(currentEmail, currentPassword);
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
     * This method verifies password mismatch error
     * @author Gokul - GWL
     * @param password
     * @return boolean flag
     */
    public boolean verifyPasswordMismatchError(String password) throws InterruptedException {
        flag = verifyChangePasswordPageRedirection();
        if(flag) {
            flag = enterValueOnCurrentPwdField(password);
            if (flag) {
                flag = enterValueOnNewPwdField(ChangePasswordPageLocators.RANDOM_PASSWORD);
                if (flag) {
                    flag = enterValueOnConfirmPwdField(ChangePasswordPageLocators.RANDOM_PASSWORD+ChangePasswordPageLocators.MINIMUM_LENGTH_NEW_PWD);
                    if (flag) {
                        flag = webDB.moveToElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                        if (flag) {
                            flag = webDB.clickAnElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                            if (flag) {
                                log.logging("info", "Clicked update button");
                                Thread.sleep(1000);
                                flag = webDB.waitForElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_ERROR, ElementType.Xpath);
                                if(flag){
                                    String error = webDB.getTextFromElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_ERROR, ElementType.Xpath);
                                    if(error.equals(ChangePasswordPageLocators.PASSWORD_MISMATCH_ERROR)){
                                        log.logging("info", "Error message is verified for mismatch password");
                                    }else{
                                        flag = false;
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
     * This method verifies password field accepting different values
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyPasswordFieldAcceptance() {
        flag = verifyChangePasswordPageRedirection();
        if(flag){
            String[][] data = excelUtils.readAllDataInExcel(webDB.getDataFromProperties("passwordFieldCheck"), "Sheet1");
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    WebElement newPasswordField = webDB.getDriver().findElement(By.id(ChangePasswordPageLocators.NEW_PASSWORD_FIELD));
                    newPasswordField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
                    newPasswordField.sendKeys(data[i][j]);
                    log.logging("info", "Entered" + ": " + data[i][j]);
                    flag = webDB.isElementNotDisplayed(ChangePasswordPageLocators.NEW_PASSWORD_ERROR, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "No error message on new password field for: "+data[i][j]);
                        WebElement confirmPasswordField = webDB.getDriver().findElement(By.id(ChangePasswordPageLocators.CONFIRM_PASSWORD_FIELD));
                        confirmPasswordField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
                        confirmPasswordField.sendKeys(data[i][j]);
                        log.logging("info", "Entered" + ": " + data[i][j]);
                        flag = webDB.isElementNotDisplayed(ChangePasswordPageLocators.CONFIRM_PASSWORD_ERROR, ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "No error message on confirm password field for: " + data[i][j]);
                        }
                    }
                }
            }
            log.logging("info", "Password fields are checked with multiple values");
        }
        return flag;
    }
}
