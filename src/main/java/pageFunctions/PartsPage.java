package pageFunctions;

import com.beust.ah.A;
import jakarta.mail.Part;
import locators.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ReportLoger;
import utils.WebDriverBase;
import java.util.List;
import utils.WebDriverBase.ElementType;

import java.time.Duration;
import java.util.IdentityHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartsPage {

    WebDriverBase webDB = new WebDriverBase();
    ReportLoger log = new ReportLoger();
    PumpsPage pumpsPage = new PumpsPage();
    boolean flag;
    String extractedMaterialName;
    String materialName;
    String extractedPartCategoryName;
    String partCategoryName;
    String extractedProcessCode;
    String processCode;

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
                    flag = webDB.waitForElement(HomePageLocators.SIDE_MENU_CLOSE_ICON, ElementType.Xpath);
                    if(flag){
                        flag = webDB.clickAnElement(HomePageLocators.SIDE_MENU_CLOSE_ICON, ElementType.Xpath);
                        if(flag){
                            flag = webDB.waitForElement(PartsPageLocators.PARTS_PAGE_HEADING, ElementType.Xpath);
                            if(flag){
                                log.logging("info", "Landed on parts page");
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies parts page url security
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyPartsPageUrlIsSecured() {
        flag = webDB.navigateToUrl(webDB.getDataFromProperties("partsPageUrl"));
        if (flag) {
            log.logging("info", "Entering parts page url");
            flag = webDB.isElementNotDisplayed(PartsPageLocators.PARTS_PAGE_HEADING, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Parts page url is secured as expected");
            }
        }
        return flag;
    }

    /**
     * This method verifies parts page url
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyPartsPageUrl() {
        flag = navigateToPartsPage();
        if(flag){
            String url = webDB.getDriver().getCurrentUrl();
            if(url.equals(webDB.getDataFromProperties("partsPageUrl"))){
                log.logging("info", "Parts page url is verified");
            }else{
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies navigation to add part page
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyNavigationToAddPartPage() throws InterruptedException {
        flag = navigateToPartsPage();
        if(flag){
            flag = webDB.waitForElement(PartsPageLocators.ADD_BUTTON, ElementType.Xpath);
            if(flag){
                flag = webDB.javaScriptClickAnElement(PartsPageLocators.ADD_BUTTON, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked add button");
                    flag = webDB.waitForElement(PartsPageLocators.ADD_PAGE_TITLE, ElementType.Xpath);
                    if(flag){
                        String header = webDB.getTextFromElement(PartsPageLocators.ADD_PAGE_TITLE, ElementType.Xpath);
                        if(header.equals(PartsPageLocators.ADD_PART_HEADER)){
                            log.logging("info", "Navigated to add parts page");
                            Thread.sleep(2000);
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
     * This method verifies text fields are editable
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyTextFieldsAreEditable() throws InterruptedException {
        flag = verifyNavigationToAddPartPage();
        if(flag){
            flag = webDB.waitForElement(PartsPageLocators.ADD_PAGE_PART_CODE, ElementType.Id);
            if(flag) {
                flag = webDB.sendTextToAnElement(PartsPageLocators.ADD_PAGE_PART_CODE, "ASD", ElementType.Id);
                if (flag) {
                    String value = webDB.getAttributeFromElement(PartsPageLocators.ADD_PAGE_PART_CODE, ElementType.Id, "value");
                    if(value.equals("ASD")){
                        flag = webDB.clearText(PartsPageLocators.ADD_PAGE_PART_CODE, ElementType.Id);
                        if(flag){
                            log.logging("info", "Part code field is editable");
                            flag = webDB.waitForElement(PartsPageLocators.ADD_PAGE_PART_NAME, ElementType.Id);
                            if(flag) {
                                flag = webDB.sendTextToAnElement(PartsPageLocators.ADD_PAGE_PART_NAME, "ASD", ElementType.Id);
                                if (flag) {
                                    String value1 = webDB.getAttributeFromElement(PartsPageLocators.ADD_PAGE_PART_NAME, ElementType.Id, "value");
                                    if(value1.equals("ASD")){
                                        flag = webDB.clearText(PartsPageLocators.ADD_PAGE_PART_NAME, ElementType.Id);
                                        if(flag){
                                            log.logging("info", "Part name field is editable");
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
     * This method verifies home page redirection
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyHomePageRedirection() {
        flag = navigateToPartsPage();
        if(flag){
            flag = webDB.waitForElement(PartsPageLocators.HOME_PAGE_LOGO, ElementType.Xpath);
            if(flag){
                WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(PartsPageLocators.HOME_PAGE_LOGO)));
                flag = webDB.javaScriptClickAnElement(PartsPageLocators.HOME_PAGE_LOGO, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked home page logo");
                    flag = webDB.waitForElement(UsersPageLocators.USERS_TITLE, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Landed on users page");
                    }else{
                        flag = webDB.waitForElement(PumpsPageLocators.PUMP_HEADING, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Landed on pumps page");
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies accessibility to master page
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyAccessibilityToMastersMenu() {
        flag = navigateToPartsPage();
        if(flag){
            flag = webDB.waitForElement(HomePageLocators.MASTER_SIDE_MENU, ElementType.Xpath);
            if(flag) {
                flag = webDB.moveToElement(HomePageLocators.MASTER_SIDE_MENU, ElementType.Xpath);
                if (flag) {
                    flag = webDB.javaScriptClickAnElement(HomePageLocators.MASTER_SIDE_MENU, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Clicked master option from side menu");
                        flag = webDB.waitForElement(HomePageLocators.MATERIALS_SIDE_MENU, ElementType.LinkText);
                        if(flag){
                            flag = webDB.javaScriptClickAnElement(HomePageLocators.MATERIALS_SIDE_MENU, ElementType.LinkText);
                            if(flag){
                                log.logging("info", "Clicked materials side menu");
                                flag = webDB.waitForElement(HomePageLocators.MATERIALS_PAGE_HEADING, ElementType.Xpath);
                                if(flag){
                                    log.logging("info", "Landed on materials page");
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
     * This method verifies placeholder text in search box
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyPlaceholderTextInSearchBox() {
        flag = navigateToPartsPage();
        if(flag){
            flag = webDB.waitForElement(PartsPageLocators.FILTER_BUTTON, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(PartsPageLocators.FILTER_BUTTON, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked filter button");
                    flag = webDB.waitForElement(PartsPageLocators.PART_NAME_FIELD, ElementType.Id);
                    if(flag){
                        String placeholder = webDB.getAttributeFromElement(PartsPageLocators.PART_NAME_FIELD, ElementType.Id, "placeholder");
                        if(placeholder.equals(PartsPageLocators.PART_NAME_PLACEHOLDER)){
                            log.logging("info", "Place holder is verified");
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
     * This method verifies search button presence
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifySearchButtonPresence() {
        flag = navigateToPartsPage();
        if(flag){
            flag = webDB.waitForElement(PartsPageLocators.FILTER_BUTTON, ElementType.Xpath);
            if(flag) {
                flag = webDB.clickAnElement(PartsPageLocators.FILTER_BUTTON, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Clicked filter button");
                    flag = webDB.waitForElement(PartsPageLocators.SEARCH_BUTTON, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Search button is visible");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies search functionality
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifySearchFunctionality() {
        flag = verifySearchButtonPresence();
        if(flag){
            flag = webDB.waitForElement(PartsPageLocators.TABLE_FIRST_ROW_PART_NAME, ElementType.Xpath);
            if(flag) {
                String partName = webDB.getTextFromElement(PartsPageLocators.TABLE_FIRST_ROW_PART_NAME, ElementType.Xpath);
                flag = webDB.sendTextToAnElement(PartsPageLocators.PART_NAME_FIELD, partName, ElementType.Id);
                if (flag) {
                    log.logging("info", "Sent part name in the field");
                    flag = webDB.waitForElement(PartsPageLocators.SEARCH_BUTTON, ElementType.Xpath);
                    if (flag) {
                        flag = webDB.clickAnElement(PartsPageLocators.SEARCH_BUTTON, ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Clicked search button");
                            flag = webDB.waitForElement(PartsPageLocators.TABLE_FIRST_ROW_PART_NAME, ElementType.Xpath);
                            if (flag) {
                                List<WebElement> totalRows = webDB.getDriver().findElements(By.xpath(UsersPageLocators.TABLE_TOTAL_ROWS));
                                int rowSize = totalRows.size();
                                if (rowSize == 1) {
                                    log.logging("info", "There is only one row is visible");
                                    String name = webDB.getTextFromElement(PartsPageLocators.TABLE_FIRST_ROW_PART_NAME, ElementType.Xpath);
                                    if (name.equals(partName)) {
                                        log.logging("info", "Search functionality is verified");
                                    } else {
                                        flag = false;
                                    }
                                } else {
                                    flag = verifySearchFunctionalityForMultipleRows(partName);
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
     * This method verifies search functionality for multiple rows
     *
     * @author Vignesh - GWL
     * @param name
     * @return boolean flag
     */
    public boolean verifySearchFunctionalityForMultipleRows(String name){
        List<WebElement> elements = webDB.getDriver().findElements(By.xpath(UsersPageLocators.TABLE_TOTAL_ROWS));
        boolean isPartFound = false;
        for (WebElement element : elements) {
            String text = element.getText();
            if (name.equals(text)) {
                isPartFound = true;
                break;
            }
        }
        if (isPartFound) {
            log.logging("info", "All row has the searched name content");
        } else {
            log.logging("info", "Issue on search functionality");
            flag = false;
        }
        return flag;
    }

    /**
     * This method verifies search functionality via keyboard
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifySearchFunctionalityViaKeyboard() {
        flag = verifySearchButtonPresence();
        if(flag) {
            flag = webDB.waitForElement(PartsPageLocators.TABLE_FIRST_ROW_PART_NAME, ElementType.Xpath);
            if (flag) {
                String partName = webDB.getTextFromElement(PartsPageLocators.TABLE_FIRST_ROW_PART_NAME, ElementType.Xpath);
                flag = webDB.sendTextToAnElement(PartsPageLocators.PART_NAME_FIELD, partName, ElementType.Id);
                if (flag) {
                    log.logging("info", "Sent part name in the field");
                    Actions action = new Actions(webDB.getDriver());
                    action.sendKeys(Keys.ENTER).build().perform();
                    flag = webDB.waitForElement(PartsPageLocators.TABLE_FIRST_ROW_PART_NAME, ElementType.Xpath);
                    if (flag) {
                        List<WebElement> totalRows = webDB.getDriver().findElements(By.xpath(UsersPageLocators.TABLE_TOTAL_ROWS));
                        int rowSize = totalRows.size();
                        if (rowSize == 1) {
                            log.logging("info", "There is only one row is visible");
                            String name = webDB.getTextFromElement(PartsPageLocators.TABLE_FIRST_ROW_PART_NAME, ElementType.Xpath);
                            if (name.equals(partName)) {
                                log.logging("info", "Search functionality is verified");
                            } else {
                                flag = false;
                            }
                        } else {
                            flag = verifySearchFunctionalityForMultipleRows(partName);
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies invalid name search error
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyInvalidNameSearch() {
        flag = verifySearchButtonPresence();
        if(flag) {
            flag = webDB.waitForElement(PartsPageLocators.PART_NAME_FIELD, ElementType.Id);
            if(flag){
                flag = webDB.sendTextToAnElement(PartsPageLocators.PART_NAME_FIELD, PartsPageLocators.DUMMY_NAME, ElementType.Id);
                if(flag){
                    log.logging("info", "Entered invalid name");
                    flag = webDB.waitForElement(PartsPageLocators.SEARCH_BUTTON, ElementType.Xpath);
                    if (flag) {
                        flag = webDB.clickAnElement(PartsPageLocators.SEARCH_BUTTON, ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Clicked search button");
                            flag = webDB.waitForElement(PartsPageLocators.NO_RECORDS_INFO, ElementType.Xpath);
                            if(flag){
                                log.logging("info", "No records info is verified");
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies applied search after navigation
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyAppliedSearchAfterNavigation() {
        flag = verifySearchFunctionality();
        if(flag){
            flag = pumpsPage.navigateToPumpsPage();
            if(flag){
                flag = navigateToPartsPage();
                if(flag){
                    flag = webDB.isElementNotDisplayed(PartsPageLocators.SEARCH_BUTTON, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Filter has been removed");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies reset option on search function
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyResetOptionOnSearchFunction() {
        flag = verifySearchButtonPresence();
        if(flag) {
            flag = webDB.waitForElement(UsersPageLocators.TABLE_TOTAL_ROWS, ElementType.Xpath);
            if(flag) {
                List<WebElement> totalRows = webDB.getDriver().findElements(By.xpath(UsersPageLocators.TABLE_TOTAL_ROWS));
                int rowSize = totalRows.size();
                flag = webDB.sendTextToAnElement(PartsPageLocators.PART_NAME_FIELD, PartsPageLocators.DUMMY_NAME, ElementType.Id);
                if (flag) {
                    log.logging("info", "Entered name on part field");
                    flag = webDB.waitForElement(PartsPageLocators.RESET_BUTTON, ElementType.Xpath);
                    if (flag) {
                        flag = webDB.clickAnElement(PartsPageLocators.RESET_BUTTON, ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Clicked reset button");
                            flag = webDB.waitForElement(UsersPageLocators.TABLE_TOTAL_ROWS, ElementType.Xpath);
                            if (flag) {
                                List<WebElement> totalRowsAfter = webDB.getDriver().findElements(By.xpath(UsersPageLocators.TABLE_TOTAL_ROWS));
                                int rowSizeAfter = totalRowsAfter.size();
                                if (rowSizeAfter == rowSize) {
                                    log.logging("info", "Reset functionality verified");
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
     * This method verifies presence of add button
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyPresenceOfAddButton() {
        flag = navigateToPartsPage();
        if(flag){
            flag = webDB.waitForElement(PartsPageLocators.ADD_BUTTON, ElementType.Xpath);
            if(flag){
                log.logging("info", "Add button is visible");
            }
        }
        return flag;
    }

    /**
     * This method sends part code value
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean sendPartCodeValue(String partCode) throws InterruptedException {
        flag = webDB.waitForElement(PartsPageLocators.ADD_PAGE_PART_CODE, ElementType.Id);
        if(flag){
//            WebElement partCodeField = webDB.getDriver().findElement(By.id(PartsPageLocators.ADD_PAGE_PART_CODE));
//            for (char c : partCode.toCharArray()) {
//                partCodeField.sendKeys(String.valueOf(c));
//                Thread.sleep(10);
//            }
            flag = webDB.sendTextToAnElement(PartsPageLocators.ADD_PAGE_PART_CODE,partCode, ElementType.Id);
            if(flag){
                log.logging("info", "Sent part code");
                String value = webDB.getAttributeFromElement(PartsPageLocators.ADD_PAGE_PART_CODE, ElementType.Id, "value");
                if(value.equals(partCode)){
                    log.logging("info", "Verified sent part code");
                }else{
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * This method sends part code value
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean sendPartNameValue(String partName) {
        flag = webDB.waitForElement(PartsPageLocators.ADD_PAGE_PART_NAME, ElementType.Id);
        if(flag){
            flag = webDB.sendTextToAnElement(PartsPageLocators.ADD_PAGE_PART_NAME,partName, ElementType.Id);
            if(flag){
                log.logging("info", "Sent part code");
                String value = webDB.getAttributeFromElement(PartsPageLocators.ADD_PAGE_PART_NAME, ElementType.Id, "value");
                if(value.equals(partName)){
                    log.logging("info", "Verified sent part name");
                }else{
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * This method selects part category
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean selectPartCategory() {
        flag = webDB.waitForElement(PartsPageLocators.PART_CATEGORY_DROPDOWN, ElementType.Xpath);
        if(flag){
            WebElement element = webDB.getDriver().findElement(By.xpath(PartsPageLocators.PART_CATEGORY_DROPDOWN));
            Actions action = new Actions(webDB.getDriver());
            action.click(element).sendKeys("P001").sendKeys(Keys.ENTER).build().perform();
            flag = webDB.waitForElement(PartsPageLocators.SELECTED_PART_CATEGORY, ElementType.Xpath);
            if(flag){
                log.logging("info", "Selected part category");
            }
        }
        return flag;
    }

    /**
     * This method selects material
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean selectMaterialDropdown() {
        flag = webDB.waitForElement(PartsPageLocators.MATERIAL_DROPDOWN, ElementType.Xpath);
        if(flag){
            WebElement element = webDB.getDriver().findElement(By.xpath(PartsPageLocators.MATERIAL_DROPDOWN));
            Actions action = new Actions(webDB.getDriver());
            action.click(element).sendKeys("Alloys").sendKeys(Keys.ENTER).build().perform();
            flag = webDB.waitForElement(PartsPageLocators.SELECTED_MATERIAL_CATEGORY, ElementType.Xpath);
            if(flag){
                log.logging("info", "Selected material category");
            }
        }
        return flag;
    }

    /**
     * This method sends material weight
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean sendMaterialWeight() {
        flag = webDB.waitForElement(PartsPageLocators.ADD_PAGE_MATERIAL_WEIGHT, ElementType.Id);
        if(flag){
            flag = webDB.sendTextToAnElement(PartsPageLocators.ADD_PAGE_MATERIAL_WEIGHT,"10", ElementType.Id);
            if(flag){
                log.logging("info", "Sent material weight");
                String attr = webDB.getAttributeFromElement(PartsPageLocators.ADD_PAGE_MATERIAL_WEIGHT, ElementType.Id, "value");
                if(attr.equals("10")){
                    log.logging("info", "Material weight verified");
                }else{
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * This method fills basic details
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean fillBasicDetails() throws InterruptedException {
        flag = sendPartCodeValue(PartsPageLocators.PART_CODE);
        if(flag){
            flag = sendPartNameValue(PartsPageLocators.PART_NAME);
            if(flag){
                flag = selectPartCategory();
                if(flag){
                    Thread.sleep(2000);
                    flag = selectMaterialDropdown();
                    if(flag){
                        flag = sendMaterialWeight();
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method clicks add part next button
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean clickAddPartNextButton() {
        flag = webDB.waitForElement(PartsPageLocators.ADD_PART_NEXT_BUTTON, ElementType.Xpath);
        if(flag){
            flag = webDB.clickAnElement(PartsPageLocators.ADD_PART_NEXT_BUTTON, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked next button");
            }
        }
        return flag;
    }

    /**
     * This method fills process details
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean fillProcessDetails() throws InterruptedException {
        flag = webDB.waitForElement(PartsPageLocators.LUMP_SUM_PRICE, ElementType.Id);
        if(flag){
            flag = webDB.clickAnElement(PartsPageLocators.LUMP_SUM_PRICE, ElementType.Id);
            Actions actions = new Actions(webDB.getDriver());
            actions.sendKeys("25").build().perform();
            //flag = webDB.sendTextToAnElement(PartsPageLocators.LUMP_SUM_PRICE,"25", ElementType.Id);
            if(flag){
                log.logging("info", "Sent lump sum price");
                Thread.sleep(2000);
                flag = webDB.waitForElement(PartsPageLocators.PROCESS_DROPDOWN, ElementType.Xpath);
                if(flag){
                    WebElement element = webDB.getDriver().findElement(By.xpath(PartsPageLocators.PROCESS_DROPDOWN));
                    Actions action = new Actions(webDB.getDriver());
                    action.click(element).build().perform();
                    action.sendKeys("PR").sendKeys(Keys.ENTER).build().perform();
                    flag = webDB.waitForElement(PartsPageLocators.ADD_PART_NEXT_BUTTON, ElementType.Xpath);
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies part created success message
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyPartCreatedSuccessMsg() {
        flag = webDB.waitForElement(PartsPageLocators.PART_CREATED_SUCCESS, ElementType.Xpath);
        if(flag){
            WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PartsPageLocators.PART_CREATED_SUCCESS)));
        }
        return flag;
    }

    /**
     * This method verifies created part item
     * @author Vignesh - GWL
     * @param partCode
     * @return boolean flag
     */
    public boolean verifyCreatedPartItem(String partCode){
        flag = webDB.waitForElement(PartsPageLocators.TABLE_FIRST_ROW_PART_CODE, ElementType.Xpath);
        if(flag){
            String code = webDB.getTextFromElement(PartsPageLocators.TABLE_FIRST_ROW_PART_CODE, ElementType.Xpath);
            if(code.equals(partCode)){
                log.logging("info", "Created item is verified");
            }else{
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies created part item
     * @author Vignesh - GWL
     * @param partName
     * @return boolean flag
     */
    public boolean verifyCreatedPartName(String partName){
        flag = webDB.waitForElement(PartsPageLocators.TABLE_FIRST_ROW_PART_NAME, ElementType.Xpath);
        if(flag){
            String code = webDB.getTextFromElement(PartsPageLocators.TABLE_FIRST_ROW_PART_NAME, ElementType.Xpath);
            if(code.equals(partName)){
                log.logging("info", "Created item is verified");
            }else{
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method deletes created part item
     * @author Vignesh - GWL
     * @param partCode
     * @return boolean flag
     */
    public boolean deleteCreatedPartItem(String partCode){
        flag = webDB.waitForElement("//div[contains(text(),'"+partCode+"')]/../..//input", ElementType.Xpath);
        if(flag){
            flag = webDB.javaScriptClickAnElement("//div[contains(text(),'"+partCode+"')]/../..//input", ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked desired checkbox");
                flag = webDB.waitForElement(PartsPageLocators.DELETE_BUTTON, ElementType.Xpath);
                if(flag){
                    flag = webDB.javaScriptClickAnElement(PartsPageLocators.DELETE_BUTTON, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Clicked delete button");
                        flag = webDB.waitForElement(PartsPageLocators.DELETE_POPUP_CONFIRM, ElementType.Xpath);
                        if(flag){
                            flag = webDB.clickAnElement(PartsPageLocators.DELETE_POPUP_CONFIRM, ElementType.Xpath);
                            if(flag){
                                log.logging("info", "Clicked delete popup confirm");
                                flag = webDB.waitForElement(PartsPageLocators.PART_CREATED_SUCCESS, ElementType.Xpath);
                                if(flag){
                                    WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PartsPageLocators.PART_CREATED_SUCCESS)));
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
     * This method verifies creating new part item
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyCreatingNewPartItem() throws InterruptedException {
        flag = verifyNavigationToAddPartPage();
        if(flag){
            flag = fillBasicDetails();
            if(flag){
                flag = clickAddPartNextButton();
                if(flag){
                    flag = fillProcessDetails();
                    if(flag){
                        Thread.sleep(2000);
                        flag = clickAddPartNextButton();
                        if(flag){
                            flag = webDB.waitForElement(PartsPageLocators.SAVE_BUTTON, ElementType.Xpath);
                            if(flag){
                                flag = webDB.clickAnElement(PartsPageLocators.SAVE_BUTTON, ElementType.Xpath);
                                if(flag){
                                    log.logging("info", "Clicked save button");
                                    flag = verifyPartCreatedSuccessMsg();
                                    if(flag){
                                        flag = verifyCreatedPartItem(PartsPageLocators.PART_CODE);
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
     * This method verifies input validation for add dialogue
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyInputValidationForAddDialogue() throws InterruptedException {
        flag = verifyNavigationToAddPartPage();
        if(flag) {
            flag = sendPartCodeValue(PartsPageLocators.INVALID_NAME);
            if (flag) {
                flag = sendPartNameValue(PartsPageLocators.INVALID_NAME);
                if (flag) {
                    webDB.moveToElement(PartsPageLocators.ADD_PART_NEXT_BUTTON, ElementType.Xpath);
                    flag = webDB.waitForElement(PartsPageLocators.ADD_PART_NEXT_BUTTON, ElementType.Xpath);
                    if(flag) {
                        flag = webDB.javaScriptClickAnElement(PartsPageLocators.ADD_PART_NEXT_BUTTON, ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Clicked next button");
                            if (flag) {
                                flag = webDB.waitForElement(PartsPageLocators.PART_CODE_ERROR_MESSAGE, ElementType.Xpath);
                                if (flag) {
                                    String codeError = webDB.getTextFromElement(PartsPageLocators.PART_CODE_ERROR_MESSAGE, ElementType.Xpath);
                                    if (codeError.equals(PartsPageLocators.INVALID_CODE_ERROR)) {
                                        log.logging("info", "Invalid code error verified");
                                        flag = webDB.waitForElement(PartsPageLocators.PART_NAME_ERROR_MESSAGE, ElementType.Xpath);
                                        if (flag) {
                                            String nameError = webDB.getTextFromElement(PartsPageLocators.PART_NAME_ERROR_MESSAGE, ElementType.Xpath);
                                            if (nameError.equals(PartsPageLocators.INVALID_NAME_ERROR)) {
                                                log.logging("info", "Invalid name error verified");
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
            }
        }
        return flag;
    }

    /**
     * This method verifies empty field error message
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyEmptyFieldErrorMessage() throws InterruptedException {
        flag = verifyNavigationToAddPartPage();
        if(flag) {
            webDB.moveToElement(PartsPageLocators.ADD_PART_NEXT_BUTTON, ElementType.Xpath);
            flag = webDB.waitForElement(PartsPageLocators.ADD_PART_NEXT_BUTTON, ElementType.Xpath);
            if(flag) {
                flag = webDB.javaScriptClickAnElement(PartsPageLocators.ADD_PART_NEXT_BUTTON, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Clicked next button");
                    flag = webDB.waitForElement(PartsPageLocators.PART_CODE_ERROR_MESSAGE, ElementType.Xpath);
                    if (flag) {
                        String codeError = webDB.getTextFromElement(PartsPageLocators.PART_CODE_ERROR_MESSAGE, ElementType.Xpath);
                        if (codeError.equals(PartsPageLocators.EMPTY_CODE_ERROR)) {
                            log.logging("info", "Empty code error verified");
                            flag = webDB.waitForElement(PartsPageLocators.PART_NAME_ERROR_MESSAGE, ElementType.Xpath);
                            if (flag) {
                                String nameError = webDB.getTextFromElement(PartsPageLocators.PART_NAME_ERROR_MESSAGE, ElementType.Xpath);
                                if (nameError.equals(PartsPageLocators.EMPTY_NAME_ERROR)) {
                                    log.logging("info", "Empty name error verified");
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
        return flag;
    }

    /**
     * This method verifies space in input field
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifySpaceInInputField() throws InterruptedException {
        flag = verifyNavigationToAddPartPage();
        if(flag){
            flag = sendPartCodeValue(PartsPageLocators.PART_CODE);
            if(flag) {
                flag = sendPartNameValue(PartsPageLocators.PART_NAME_WITH_SPACE);
                if (flag) {
                    flag = selectPartCategory();
                    if (flag) {
                        Thread.sleep(2000);
                        flag = selectMaterialDropdown();
                        if (flag) {
                            flag = sendMaterialWeight();
                            if (flag) {
                                flag = clickAddPartNextButton();
                                if (flag) {
                                    flag = fillProcessDetails();
                                    if (flag) {
                                        Thread.sleep(2000);
                                        flag = clickAddPartNextButton();
                                        if (flag) {
                                            flag = webDB.waitForElement(PartsPageLocators.SAVE_BUTTON, ElementType.Xpath);
                                            if (flag) {
                                                flag = webDB.clickAnElement(PartsPageLocators.SAVE_BUTTON, ElementType.Xpath);
                                                if (flag) {
                                                    log.logging("info", "Clicked save button");
                                                    flag = verifyPartCreatedSuccessMsg();
                                                    if (flag) {
                                                        flag = verifyCreatedPartName(PartsPageLocators.PART_NAME);
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
            }
        }
        return flag;
    }

    /**
     * This method verifies character limit for part code
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyCharacterLimitForPartCode() throws InterruptedException {
        flag = verifyNavigationToAddPartPage();
        if(flag) {
            flag = sendPartNameValue(PartsPageLocators.LENGTHY_NAME);
            if (flag) {
                webDB.moveToElement(PartsPageLocators.ADD_PART_NEXT_BUTTON, ElementType.Xpath);
                flag = webDB.waitForElement(PartsPageLocators.ADD_PART_NEXT_BUTTON, ElementType.Xpath);
                if(flag) {
                    flag = webDB.javaScriptClickAnElement(PartsPageLocators.ADD_PART_NEXT_BUTTON, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Clicked next button");
                        if (flag) {
                            flag = webDB.waitForElement(PartsPageLocators.PART_NAME_ERROR_MESSAGE, ElementType.Xpath);
                            if (flag) {
                                String nameError = webDB.getTextFromElement(PartsPageLocators.PART_NAME_ERROR_MESSAGE, ElementType.Xpath);
                                if (nameError.equals(PartsPageLocators.LENGTHY_NAME_ERROR)) {
                                    log.logging("info", "Lengthy name error verified");
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
     * This method verifies creating duplicate item
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyCreatingDuplicateItem() throws InterruptedException {
        flag = navigateToPartsPage();
        if(flag) {
            flag = webDB.waitForElement(PartsPageLocators.TABLE_FIRST_ROW_PART_CODE, ElementType.Xpath);
            if(flag){
                String partCode = webDB.getTextFromElement(PartsPageLocators.TABLE_FIRST_ROW_PART_CODE, ElementType.Xpath);
                flag = webDB.waitForElement(PartsPageLocators.ADD_BUTTON, ElementType.Xpath);
                if(flag){
                    flag = webDB.clickAnElement(PartsPageLocators.ADD_BUTTON, ElementType.Xpath);
                    if(flag) {
                        log.logging("info", "Clicked add button");
                        Thread.sleep(2000);
                        flag = webDB.waitForElement(PartsPageLocators.ADD_PAGE_PART_CODE, ElementType.Id);
                        if (flag) {
                            flag = webDB.sendTextToAnElement(PartsPageLocators.ADD_PAGE_PART_CODE, partCode, ElementType.Id);
                            if (flag) {
                                log.logging("info", "Sent part code");
                                webDB.moveToElement(PartsPageLocators.ADD_PART_NEXT_BUTTON, ElementType.Xpath);
                                flag = clickAddPartNextButton();
                                if (flag) {
                                    flag = webDB.waitForElement(PartsPageLocators.PART_CODE_ERROR_MESSAGE, ElementType.Xpath);
                                    if (flag) {
                                        String error = webDB.getTextFromElement(PartsPageLocators.PART_CODE_ERROR_MESSAGE, ElementType.Xpath);
                                        if (error.equals(PartsPageLocators.DUPLICATE_CODE_ERROR)) {
                                            log.logging("info", "Verified error for duplicate value");
                                        } else {
                                            flag = false;
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
     * This method verifies input field of purchased cose
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyInputFieldOfPurchasedCost() throws InterruptedException {
        flag = verifyNavigationToAddPartPage();
        if(flag) {
            flag = webDB.waitForElement(PartsPageLocators.IS_PURCHASED_TOGGLE, ElementType.Xpath);
            if(flag){
                WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(PartsPageLocators.IS_PURCHASED_TOGGLE)));
                flag = webDB.clickAnElement(PartsPageLocators.IS_PURCHASED_TOGGLE, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked toggle button");
                    flag = webDB.waitForElement(PartsPageLocators.PURCHASED_PRICE_FIELD, ElementType.Id);
                    if(flag){
                        flag = webDB.sendTextToAnElement(PartsPageLocators.PURCHASED_PRICE_FIELD,"1", ElementType.Id);
                        if(flag){
                            log.logging("info", "Sent price");
                            webDB.clickAnElement(PartsPageLocators.ADD_PAGE_PART_CODE, ElementType.Id);
                            String text = webDB.getAttributeFromElement(PartsPageLocators.PURCHASED_PRICE_FIELD, ElementType.Id, "value");
                            if(text.equals("$1.00")){
                                log.logging("info", "Verified input field");
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
     * This method verifies cancel button on add dialogue box
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyCancelButtonOnAddDialogueBox() throws InterruptedException {
        flag = verifyNavigationToAddPartPage();
        if(flag) {
            flag = webDB.waitForElement(PartsPageLocators.CANCEL_BUTTON, ElementType.Xpath);
            if(flag){
                webDB.moveToElement(PartsPageLocators.CANCEL_BUTTON, ElementType.Xpath);
                flag = webDB.clickAnElement(PartsPageLocators.CANCEL_BUTTON, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked cancel button");
                    flag = webDB.waitForElement(PartsPageLocators.PARTS_PAGE_HEADING, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Navigated back to parts page as expected");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method fills basic details
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean fillBasicDetailsForListingPage() throws InterruptedException {
        flag = sendPartCodeValue("RCP-20");
        if(flag){
            flag = sendPartNameValue("RCP 80x50-315");
            if(flag){
                flag = selectPartCategory();
                if(flag){
                    Thread.sleep(2000);
                    flag = selectMaterialDropdown();
                    if(flag){
                        flag = sendMaterialWeight();
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method creates new material to verify listing page
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean createNewMaterialToVerifyListingPage() throws InterruptedException {
        flag = verifyNavigationToAddPartPage();
        if(flag){
            flag = fillBasicDetailsForListingPage();
            if(flag){
                flag = clickAddPartNextButton();
                if(flag){
                    flag = fillProcessDetails();
                    if(flag) {
                        Thread.sleep(2000);
                        flag = clickAddPartNextButton();
                        if (flag) {
                            flag = webDB.waitForElement(PartsPageLocators.SAVE_BUTTON, ElementType.Xpath);
                            if (flag) {
                                flag = webDB.clickAnElement(PartsPageLocators.SAVE_BUTTON, ElementType.Xpath);
                                if (flag) {
                                    log.logging("info", "Clicked save button");
                                    flag = verifyPartCreatedSuccessMsg();
                                    if (flag) {
                                        flag = verifyCreatedPartItem("RCP-20");
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
     * This method verifies next and previous button on pagination
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyNextAndPreviousButtonOnPagination() {
        flag = navigateToPartsPage();
        if(flag) {
            flag = webDB.waitForElement(PartsPageLocators.PAGINATION_PREVIOUS_PAGE, ElementType.Id);
            if(flag){
                flag = webDB.waitForElement(PartsPageLocators.PAGINATION_NEXT_PAGE, ElementType.Id);
                if(flag){
                    log.logging("info", "Next and previous pagination button is visible");
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
        flag = navigateToPartsPage();
        if(flag) {
            flag = webDB.waitForElement(PartsPageLocators.DEFAULT_DROPDOWN, ElementType.Xpath);
            if(flag){
                String count = webDB.getTextFromElement(PartsPageLocators.DEFAULT_DROPDOWN, ElementType.Xpath);
                if(count.equals("10")) {
                    log.logging("info", "Default pagination count is 10");
                }else{
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies edit page redirection
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyEditPageRedirection() {
        flag = navigateToPartsPage();
        if(flag) {
            flag = webDB.waitForElement(PartsPageLocators.TABLE_FIRST_ROW_EDIT_BUTTON, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(PartsPageLocators.TABLE_FIRST_ROW_EDIT_BUTTON, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked first row edit button");
                    flag = webDB.waitForElement(PartsPageLocators.EDIT_PART_HEADING, ElementType.Xpath);
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies materials page url security
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyMaterialsPageUrlIsSecured() {
        flag = webDB.navigateToUrl(webDB.getDataFromProperties("materialsPageUrl"));
        if (flag) {
            log.logging("info", "Entering materials page url");
            flag = webDB.waitForElement(MaterialsPageLocators.MATERIALS_HEADING, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Navigated to material page as expected");
            }
        }
        return flag;
    }

    /**
     * This method gets first row material name
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public String getFirstRowMaterialName() {
        flag = webDB.waitForElement(MaterialsPageLocators.TABLE_FIRST_ROW_MATERIAL_NAME, ElementType.Xpath);
        String materialName = null;
        if (flag) {
            materialName = webDB.getTextFromElement(MaterialsPageLocators.TABLE_FIRST_ROW_MATERIAL_NAME, ElementType.Xpath);
            log.logging("info", "Extracted material name is: "+materialName);
        }
        return materialName;
    }

    /**
     * This method get material name from material page
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public String getMaterialNameFromMaterialPage() {
        flag = verifyMaterialsPageUrlIsSecured();
        if(flag){
            materialName = getFirstRowMaterialName();
        }
        return materialName;
    }

    /**
     * This method verifies part categories page url
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyPartCategoriesPageUrl() {
        flag = webDB.navigateToUrl(webDB.getDataFromProperties("partCategoriesPageUrl"));
            if(flag){
                flag = webDB.waitForElement(PartCategoriesLocators.PART_CATEGORIES_HEADING, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Navigated to part categories page");
                }
            }
        return flag;
    }

    /**
     * This method gets first row part category name
     * @author Vignesh - GWL
     * @return boolan flag
     */
    public String getFirstRowPartCategoryName() {
        flag = webDB.waitForElement(PartCategoriesLocators.TABLE_FIRST_ROW_PART_CATEGORY, ElementType.Xpath);
        String partCategoryName = null;
        if (flag) {
            partCategoryName = webDB.getTextFromElement(PartCategoriesLocators.TABLE_FIRST_ROW_PART_CATEGORY, ElementType.Xpath);
            log.logging("info", "Extracted partCategory name is: "+partCategoryName);
        }
        return partCategoryName;
    }

    /**
     * This method gets part category name
     * @author Vignesh - GWL
     * @return string part category name
     */
    public String getPartCategoryName() {
        flag = verifyPartCategoriesPageUrl();
        if(flag){
            partCategoryName = getFirstRowPartCategoryName();
        }
        return partCategoryName;
    }

    /**
     * This method verifies part categories page url
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyProcessPageUrl() {
        flag = webDB.navigateToUrl(webDB.getDataFromProperties("processPageUrl"));
        if(flag){
            flag = webDB.waitForElement(ProcessesPageLocators.PROCESSES_HEADING, ElementType.Xpath);
            if(flag){
                log.logging("info", "Navigated to process page");
            }
        }
        return flag;
    }

    /**
     * This method gets first row part process code
     * @author Vignesh - GWL
     * @return string process code
     */
    public String getFirstRowProcessCode() {
        flag = webDB.waitForElement(ProcessesPageLocators.TABLE_FIRST_ROW_PROCESS_CODE, ElementType.Xpath);
        String processCode = null;
        if (flag) {
            processCode = webDB.getTextFromElement(ProcessesPageLocators.TABLE_FIRST_ROW_PROCESS_CODE, ElementType.Xpath);
            log.logging("info", "Extracted process code is: "+processCode);
        }
        return processCode;
    }

    /**
     * This method gets process code from process page
     * @author Vignesh - GWL
     * @return string process code
     */
    public String getProcessCodeFromProcessPage() {
        flag = verifyProcessPageUrl();
        if(flag){
            processCode = getFirstRowProcessCode();
        }
        return processCode;
    }

    /**
     * This method selects part category
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean selectPartCategoryForMaster(String partCategory) {
        flag = webDB.waitForElement(PartsPageLocators.PART_CATEGORY_DROPDOWN, ElementType.Xpath);
        if(flag){
            WebElement element = webDB.getDriver().findElement(By.xpath(PartsPageLocators.PART_CATEGORY_DROPDOWN));
            Actions action = new Actions(webDB.getDriver());
            action.click(element).sendKeys(partCategory).sendKeys(Keys.ENTER).build().perform();
            flag = webDB.waitForElement("//div[contains(text(),'"+partCategory+"')]", ElementType.Xpath);
            if(flag){
                log.logging("info", "Selected part category");
            }
        }
        return flag;
    }

    /**
     * This method selects material
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean selectMaterialDropdownForMaster(String materialName) {
        flag = webDB.waitForElement(PartsPageLocators.MATERIAL_DROPDOWN, ElementType.Xpath);
        if(flag){
            WebElement element = webDB.getDriver().findElement(By.xpath(PartsPageLocators.MATERIAL_DROPDOWN));
            Actions action = new Actions(webDB.getDriver());
            action.click(element).sendKeys(materialName).sendKeys(Keys.ENTER).build().perform();
            flag = webDB.waitForElement("//div[contains(text(),'"+materialName+"')]", ElementType.Xpath);
            if(flag){
                log.logging("info", "Selected material category");
            }
        }
        return flag;
    }

    /**
     * This method fills basic details for master
     * @author Vignesh - GWL
     * @param partCategory
     * @param MaterialName
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean fillBasicDetailsForMaster(String partCategory, String MaterialName) throws InterruptedException {
        flag = sendPartCodeValue(PartsPageLocators.PART_CODE);
        if(flag){
            flag = sendPartNameValue(PartsPageLocators.PART_NAME);
            if(flag){
                flag = selectPartCategoryForMaster(partCategory);
                if(flag){
                    Thread.sleep(2000);
                    flag = selectMaterialDropdownForMaster(MaterialName);
                    if(flag){
                        flag = sendMaterialWeight();
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method fills process details
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean fillProcessDetailsForMaster(String processCode) throws InterruptedException {
        flag = webDB.waitForElement(PartsPageLocators.LUMP_SUM_PRICE, ElementType.Id);
        if(flag){
            flag = webDB.clickAnElement(PartsPageLocators.LUMP_SUM_PRICE, ElementType.Id);
            Actions actions = new Actions(webDB.getDriver());
            actions.sendKeys("25").build().perform();
            if(flag){
                log.logging("info", "Sent lump sum price");
                Thread.sleep(2000);
                flag = webDB.waitForElement(PartsPageLocators.PROCESS_DROPDOWN, ElementType.Xpath);
                if(flag){
                    WebElement element = webDB.getDriver().findElement(By.xpath(PartsPageLocators.PROCESS_DROPDOWN));
                    Actions action = new Actions(webDB.getDriver());
                    action.click(element).build().perform();
                    action.sendKeys(processCode).sendKeys(Keys.ENTER).build().perform();
                    flag = webDB.waitForElement(PartsPageLocators.ADD_PART_NEXT_BUTTON, ElementType.Xpath);
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies master menu items in material page
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyMasterMenuItemsInMaterialsPage() throws InterruptedException {
        extractedMaterialName = getMaterialNameFromMaterialPage();
        extractedPartCategoryName = getPartCategoryName();
        extractedProcessCode = getProcessCodeFromProcessPage();
        flag = navigateToPartsPage();
        if(flag){
            flag = verifyNavigationToAddPartPage();
            if(flag){
                flag = fillBasicDetailsForMaster(partCategoryName, materialName);
                if(flag){
                    flag = clickAddPartNextButton();
                    if(flag){
                        flag = fillProcessDetailsForMaster(processCode);
                        if(flag) {
                            Thread.sleep(2000);
                            flag = clickAddPartNextButton();
                            if (flag) {
                                flag = webDB.waitForElement(PartsPageLocators.SAVE_BUTTON, ElementType.Xpath);
                                if (flag) {
                                    flag = webDB.clickAnElement(PartsPageLocators.SAVE_BUTTON, ElementType.Xpath);
                                    if (flag) {
                                        log.logging("info", "Clicked save button");
                                        flag = verifyPartCreatedSuccessMsg();
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
     * This method verifies edited master menu item
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyEditedMasterMenuItem() throws InterruptedException {
        String partCategory = getPartCategoryName();
        flag = webDB.waitForElement("//div[contains(text(),'"+partCategory+"')]/../following-sibling::div[2]//button[1]", ElementType.Xpath);
        if(flag){
            flag = webDB.clickAnElement("//div[contains(text(),'"+partCategory+"')]/../following-sibling::div[2]//button[1]", ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked edit icon");
                flag = webDB.waitForElement(PartCategoriesLocators.PART_CATEGORY_NAME_FIELD, ElementType.Id);
                if(flag){
                    Thread.sleep(1000);
                    WebElement inputElement = webDB.getDriver().findElement(By.id(PartCategoriesLocators.PART_CATEGORY_NAME_FIELD));
                    JavascriptExecutor jsExecutor = (JavascriptExecutor) webDB.getDriver();
                    jsExecutor.executeScript("arguments[0].value = ' ';", inputElement);
                    if(flag){
                        log.logging("info", "Cleared part category name field");
                        Thread.sleep(1000);
                        flag = webDB.sendTextToAnElement(PartCategoriesLocators.PART_CATEGORY_NAME_FIELD, partCategory+"Edited", ElementType.Id);
                        if(flag){
                            flag = webDB.waitForElement(PartCategoriesLocators.EDIT_PART_CATEGORY_UPDATE, ElementType.Xpath);
                            if(flag){
                                flag = webDB.clickAnElement(PartCategoriesLocators.EDIT_PART_CATEGORY_UPDATE, ElementType.Xpath);
                                if(flag){
                                    log.logging("info", "Clicked update button");
                                    flag = webDB.waitForElement(PartCategoriesLocators.EDIT_PART_UPDATE_MESSAGE, ElementType.Xpath);
                                    WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PartCategoriesLocators.EDIT_PART_UPDATE_MESSAGE)));
                                    flag = navigateToPartsPage();
                                    if(flag){
                                        flag = webDB.waitForElement(PartsPageLocators.TABLE_FIRST_ROW_PART_CATEGORY, ElementType.Xpath);
                                        if(flag){
                                            String text = webDB.getTextFromElement(PartsPageLocators.TABLE_FIRST_ROW_PART_CATEGORY, ElementType.Xpath);
                                            if(text.equals(partCategory+"Edited")){
                                                log.logging("info", "Updated part category reflected properly");
                                            }else{
                                                log.logging("info", "Issue while updating");
                                                flag = false;
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
     * This method restore edited part category
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean restoreEditedPartCategory() throws InterruptedException {
        String name = getPartCategoryName();
        if(name.contains("Edited")){
            String output = name.replace("Edited", " ");
            Thread.sleep(1000);
            WebElement inputElement = webDB.getDriver().findElement(By.id(PartCategoriesLocators.PART_CATEGORY_NAME_FIELD));
            JavascriptExecutor jsExecutor = (JavascriptExecutor) webDB.getDriver();
            jsExecutor.executeScript("arguments[0].value = ' ';", inputElement);
            if(flag) {
                log.logging("info", "Cleared part category name field");
                flag = webDB.sendTextToAnElement(PartCategoriesLocators.PART_CATEGORY_NAME_FIELD, output, ElementType.Id);
                if(flag){
                    flag = webDB.waitForElement(PartCategoriesLocators.EDIT_PART_CATEGORY_UPDATE, ElementType.Xpath);
                    if(flag) {
                        flag = webDB.clickAnElement(PartCategoriesLocators.EDIT_PART_CATEGORY_UPDATE, ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Clicked update button");
                            flag = webDB.waitForElement(PartCategoriesLocators.EDIT_PART_UPDATE_MESSAGE, ElementType.Xpath);
                            WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PartCategoriesLocators.EDIT_PART_UPDATE_MESSAGE)));
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies is purchased status
     * @author Vignesh -GWL
     * @return boolean flag
     */
    public boolean verifyIsPurchasedStatus() {
        flag = navigateToPartsPage();
        if(flag){
            flag = webDB.waitForElement(PartsPageLocators.TABLE_FIRST_ROW_IS_PURCHASED, ElementType.Xpath);
            if(flag){
                String text = webDB.getTextFromElement(PartsPageLocators.TABLE_FIRST_ROW_IS_PURCHASED, ElementType.Xpath);
                if(text.equals("No") || text.equals("Yes")){
                    log.logging("info", "List item contains the purchased status as yes or no");
                }else{
                    log.logging("info", "No purchased status is found");
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies valid currency format
     * @author Vignesh - GWL
     * @param text
     * @return boolean flag
     */
    public static boolean isValidCurrencyFormat(String text) {
        String regex = "^\\$\\s\\d{1,3}(,\\d{3})*\\.\\d{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

    /**
     * This method verifies purchased price decimal
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyPurchasedPriceDecimal() {
        flag = navigateToPartsPage();
        if(flag){
            flag = webDB.waitForElement(PartsPageLocators.TABLE_FIRST_ROW_PROCESS_PRICE, ElementType.Xpath);
            if(flag){
                String text = webDB.getTextFromElement(PartsPageLocators.TABLE_FIRST_ROW_PROCESS_PRICE, ElementType.Xpath);
                log.logging("info", "Extracted text is: "+text);
                flag = isValidCurrencyFormat(text);
                if(flag){
                    log.logging("info", "Extracted text is in expected format");
                }else{
                    flag = true;
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies total price decimal format
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyTotalPriceDecimal() {
        flag = navigateToPartsPage();
        if(flag){
            flag = webDB.waitForElement(PartsPageLocators.TABLE_FIRST_ROW_TOTAL_PRICE, ElementType.Xpath);
            if(flag){
                String text = webDB.getTextFromElement(PartsPageLocators.TABLE_FIRST_ROW_TOTAL_PRICE, ElementType.Xpath);
                log.logging("info", "Extracted text is: "+text);
                flag = isValidCurrencyFormat(text);
                if(flag){
                    log.logging("info", "Extracted text is in expected format");
                }else{
                    flag = true;
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies initial state of navigation
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyInitialStateOfNavigation() throws InterruptedException {
        flag = verifyNavigationToAddPartPage();
        if(flag){
            flag = webDB.waitForElement(PartsPageLocators.PAGINATION_NEXT_PAGE, ElementType.Id);
            if(flag){
                log.logging("info", "Next button is verified");
                flag = webDB.waitForElement(PartsPageLocators.CANCEL_BUTTON, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Cancel button is verified");
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies previous button
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyPreviousButton() {
        flag = webDB.waitForElement(PartsPageLocators.PREVIOUS_BUTTON, ElementType.Xpath);
        if(flag){
            flag = webDB.clickAnElement(PartsPageLocators.PREVIOUS_BUTTON, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked previous button");
                flag = webDB.waitForElement(PartsPageLocators.ADD_PAGE_TITLE, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Verified previous button");
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies navigaton button on add part
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyNavigationButtonsOnAddPart() throws InterruptedException {
        flag = verifyNavigationToAddPartPage();
        if(flag) {
            flag = fillBasicDetails();
            if (flag) {
                flag = webDB.waitForElement(PartsPageLocators.CANCEL_BUTTON, ElementType.Xpath);
                if(flag) {
                    flag = clickAddPartNextButton();
                    if (flag) {
                        flag = verifyPreviousButton();
                        if (flag) {
                            flag = clickAddPartNextButton();
                            Thread.sleep(2000);
                            flag = clickAddPartNextButton();
                            if (flag) {
                                flag = webDB.waitForElement(PartsPageLocators.SAVE_BUTTON, ElementType.Xpath);
                                if (flag) {
                                    log.logging("info", "Verified save button");
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
     * This method verifies is purchased button function
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyIsPurchasedButtonFunction() throws InterruptedException {
        flag = verifyNavigationToAddPartPage();
        if(flag){
            flag = webDB.waitForElement(PartsPageLocators.IS_PURCHASED_TOGGLE, ElementType.Xpath);
            if(flag){
                flag = webDB.isElementDisplayed(PartsPageLocators.ADD_PAGE_MATERIAL_WEIGHT, ElementType.Id);
                if(flag){
                    log.logging("info", "Material weight field is visible");
                    flag = webDB.isElementDisplayed(PartsPageLocators.MATERIAL_PRICE_FIELD, ElementType.Id);
                    if(flag){
                        log.logging("info", "Material price field is visible");
                        flag = webDB.clickAnElement(PartsPageLocators.IS_PURCHASED_TOGGLE, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Clicked is purchased toggle");
                            flag = webDB.waitForElement(PartsPageLocators.PURCHASED_PRICE_FIELD, ElementType.Id);
                            if(flag){
                                log.logging("info", "Purchased price field is visible");
                                flag = webDB.waitForElement(PartsPageLocators.DATE_PICKER, ElementType.Id);
                                if(flag){
                                    log.logging("info", "Calender is visible");
                                    flag = webDB.isElementNotDisplayed(PartsPageLocators.MATERIAL_PRICE_FIELD, ElementType.Id);
                                    if(flag){
                                        flag = webDB.isElementNotDisplayed(PartsPageLocators.ADD_PAGE_MATERIAL_WEIGHT, ElementType.Id);
                                        if(flag){
                                            log.logging("info", "Both material price and weight field got disappeared");
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
     * This method verifies filter section fields
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyFilterSectionFields() {
        flag = navigateToPartsPage();
        if(flag){
            flag = webDB.waitForElement(PartsPageLocators.FILTER_BUTTON, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(PartsPageLocators.FILTER_BUTTON, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked filter button");
                    flag = webDB.waitForElement(PartsPageLocators.FILTER_PART_CATEGORY_DROPDOWN, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Part category dropdown is visible");
                        flag = webDB.waitForElement(PartsPageLocators.IS_PURCHASED_TOGGLE, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Is purchased toggle is visible");
                            flag = webDB.waitForElement(PartsPageLocators.ADD_PAGE_PART_NAME, ElementType.Id);
                            if(flag){
                                log.logging("info", "Part name field is visible");
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies filter search functionality
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyFilterSearchFunctionality() {
        flag = navigateToPartsPage();
        if(flag){
            flag = webDB.waitForElement(PartsPageLocators.TABLE_TOTAL_ROWS, ElementType.Xpath);
            if(flag){
                String partName = webDB.getTextFromElement(PartsPageLocators.TABLE_FIRST_ROW_PART_NAME, ElementType.Xpath);
                log.logging("info", "Extracted part name is: "+partName);
                flag = webDB.waitForElement(PartsPageLocators.FILTER_BUTTON, ElementType.Xpath);
                if(flag){
                    flag = webDB.clickAnElement(PartsPageLocators.FILTER_BUTTON, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Clicked filter button");
                        flag = webDB.waitForElement(PartsPageLocators.PART_NAME_FIELD, ElementType.Id);
                        if(flag){
                            flag = webDB.sendTextToAnElement(PartsPageLocators.PART_NAME_FIELD, partName, ElementType.Id);
                            if(flag){
                                log.logging("info", "Sent part name in field");
                                flag = webDB.waitForElement(PartsPageLocators.FILTER_SEARCH_BTN, ElementType.Xpath);
                                if(flag){
                                    flag = webDB.clickAnElement(PartsPageLocators.FILTER_SEARCH_BTN, ElementType.Xpath);
                                    if(flag){
                                        log.logging("info", "Clicked filter search button");
                                        flag = webDB.waitForElement(PartsPageLocators.TABLE_TOTAL_ROWS, ElementType.Xpath);
                                        if(flag){
                                            List<WebElement> totalRows1 = webDB.getDriver().findElements(By.xpath(PartsPageLocators.TABLE_TOTAL_ROWS));
                                            int rowSize = totalRows1.size();
                                            if(rowSize == 1){
                                                log.logging("info", "One data shown as expected");
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
            }
        }
        return flag;
    }

    /**
     * This method verifies dropdown options for add part
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyDropdownOptionsForAddPart() throws InterruptedException {
        flag = verifyNavigationToAddPartPage();
        if(flag){
            Thread.sleep(2000);
            flag = webDB.waitForElement(PartsPageLocators.PART_CATEGORY_DROPDOWN, ElementType.Xpath);
            if(flag){
                WebElement dropdown = webDB.getDriver().findElement(By.xpath(PartsPageLocators.PART_CATEGORY_DROPDOWN));
                Actions actions = new Actions(webDB.getDriver());
                actions.click(dropdown).build().perform();
                if(flag){
                    log.logging("info", "Clicked part category dropdown");
                    flag = webDB.waitForElement(PartsPageLocators.PART_CATEGORY_OPTIONS, ElementType.Xpath);
                    if(flag){
                        List<WebElement> elements = webDB.getDriver().findElements(By.xpath(PartsPageLocators.PART_CATEGORY_OPTIONS));
                        for (WebElement element : elements) {
                            String elementText = element.getText();
                            if (elementText.contains("P")) {
                                System.out.println("Element text contains 'P': " + elementText);
                            } else {
                                System.out.println("Element text does NOT contain 'P': " + elementText);
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies empty dropdown error
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyEmptyDropdownError() throws InterruptedException {
        flag = verifyNavigationToAddPartPage();
        if(flag){
            flag = webDB.waitForElement(PartsPageLocators.ADD_PART_NEXT_BUTTON, ElementType.Xpath);
            if(flag){
                webDB.moveToElement(PartsPageLocators.ADD_PART_NEXT_BUTTON, ElementType.Xpath);
                flag = webDB.clickAnElement(PartsPageLocators.ADD_PART_NEXT_BUTTON, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked next button");
                    flag = webDB.waitForElement(PartsPageLocators.PART_CATEGORY_ERROR, ElementType.Xpath);
                    if(flag){
                        String error = webDB.getTextFromElement(PartsPageLocators.PART_CATEGORY_ERROR, ElementType.Xpath);
                        if(error.equals(PartsPageLocators.EMPTY_PART_CATEGORY_ERROR)){
                            log.logging("info", "Verified error for part category dropdown");
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
     * This method verifies dropdown menu via keyboard
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyDropDownMenuViaKeyboard() throws InterruptedException {
        flag = verifyNavigationToAddPartPage();
        if(flag){
            flag = webDB.waitForElement(PartsPageLocators.ADD_PAGE_PART_CODE, ElementType.Id);
            if(flag){
                flag = webDB.clickAnElement(PartsPageLocators.ADD_PAGE_PART_CODE, ElementType.Id);
                if(flag){
                    log.logging("info", "Clicked part code");
                    Actions action = new Actions(webDB.getDriver());
                    action.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys("P002").build().perform();
                    action.sendKeys(Keys.ENTER).build().perform();
                    flag = webDB.waitForElement(PartsPageLocators.SELECTED_PART_CATEGORYY, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Selected via keyboard is verified");
                    }
                }
            }
        }
        return flag;
    }

}
