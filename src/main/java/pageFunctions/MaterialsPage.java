package pageFunctions;

import io.reactivex.rxjava3.internal.operators.mixed.MaterializeSingleObserver;
import locators.*;
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
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MaterialsPage {

    WebDriverBase webDB = new WebDriverBase();
    ReportLoger log = new ReportLoger();
    PartsPage partsPage = new PartsPage();
    PumpsPage pumpsPage = new PumpsPage();
    PartCategoriesPage partCategoriesPage = new PartCategoriesPage();
    ProcessesPage processesPage = new ProcessesPage();
    VariantsPage variantsPage = new VariantsPage();
    PumpTypesPage pumpTypesPage = new PumpTypesPage();
    CommonPage commonPage = new CommonPage();
    LoginPage loginPage = new LoginPage();
    boolean flag;

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
     * This method verifies materials page via navigation panel
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyMaterialsPageViaNavigationPanel() {
        flag = webDB.waitForElement(MaterialsPageLocators.MASTER_SIDE_MENU, ElementType.Xpath);
        if(flag){
            flag = webDB.moveToElement(MaterialsPageLocators.MASTER_SIDE_MENU, ElementType.Xpath);
            if(flag){
                flag = webDB.javaScriptClickAnElement(MaterialsPageLocators.MASTER_SIDE_MENU, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked master option from navigation panel");
                    flag = webDB.waitForElement(MaterialsPageLocators.MATERIALS_SIDE_MENU, ElementType.LinkText);
                    if(flag){
                        flag = webDB.javaScriptClickAnElement(MaterialsPageLocators.MATERIALS_SIDE_MENU, ElementType.LinkText);
                        if(flag) {
                            flag = webDB.waitForElement(HomePageLocators.SIDE_MENU_CLOSE_ICON, ElementType.Xpath);
                            if (flag) {
                                flag = webDB.clickAnElement(HomePageLocators.SIDE_MENU_CLOSE_ICON, ElementType.Xpath);
                                if (flag) {
                                    flag = webDB.waitForElement(MaterialsPageLocators.MATERIALS_HEADING, ElementType.Xpath);
                                    if (flag) {
                                        log.logging("info", "Landed on materials page");
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
     * This method verifies url of materials page
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyUrlOfMaterialsPage() {
        flag = verifyMaterialsPageViaNavigationPanel();
        if(flag){
            String url = webDB.getDriver().getCurrentUrl();
            if(url.equals(webDB.getDataFromProperties("materialsPageUrl"))){
                log.logging("info", "Url of materials page is verified");
            }else{
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method clicks first row check box
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean clickFirstRowCheckbox() {
        flag = webDB.waitForElement(UsersPageLocators.TABLE_FIRST_ROW_CHECKBOX, ElementType.Xpath);
        if(flag){
            flag = webDB.javaScriptClickAnElement(UsersPageLocators.TABLE_FIRST_ROW_CHECKBOX, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked first row checkbox");
            }
        }
        return flag;
    }

    /**
     * This method clicks delete button and verifies popup
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean clickDeleteButtonAndVerifyPopup() {
        flag = webDB.waitForElement(MaterialsPageLocators.DELETE_BUTTON, ElementType.Xpath);
        if(flag){
            flag = webDB.clickAnElement(MaterialsPageLocators.DELETE_BUTTON, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked delete button");
                flag = webDB.waitForElement(MaterialsPageLocators.DELETE_POPUP, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Delete popup is visible");
                }
            }
        }
        return flag;
    }

    /**
     * This method clicks add button and wait for popup
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean clickAddButtonAndWaitForPopup() {
        flag = webDB.waitForClickElement(MaterialsPageLocators.ADD_BUTTON, ElementType.Xpath);
        if(flag){
            flag = webDB.clickAnElement(MaterialsPageLocators.ADD_BUTTON, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked add button");
                flag = webDB.waitForElement(MaterialsPageLocators.ADD_POPUP, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Add popup is verified");
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies filter button and verifies search button
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean clickFilterButtonAndVerifySearchButton() {
        flag = webDB.waitForElement(MaterialsPageLocators.FILTER_BUTTON, ElementType.Xpath);
        if(flag){
            flag = webDB.clickAnElement(MaterialsPageLocators.FILTER_BUTTON, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked filter button");
                flag = webDB.waitForElement(MaterialsPageLocators.FILTER_SEARCH_BUTTON, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Search option is visible");
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies first row price history button
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyFirstRowPriceHistoryButton() {
        flag = webDB.waitForElement(MaterialsPageLocators.TABLE_FIRST_ROW_PRICE_HISTORY, ElementType.Xpath);
        if(flag){
            flag = webDB.clickAnElement(MaterialsPageLocators.TABLE_FIRST_ROW_PRICE_HISTORY, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked table first row price history button");
                flag = webDB.waitForElement(MaterialsPageLocators.PRICE_HISTORY_HEADING, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Price history button is verified");
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies all links on mateirals page
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyAllLinksOnMaterialsPage() throws InterruptedException {
        flag = verifyMaterialsPageUrlIsSecured();
        if(flag){
            flag = clickFirstRowCheckbox();
            if(flag){
                flag = clickDeleteButtonAndVerifyPopup();
                if(flag){
                    flag = webDB.waitForClickElement(MaterialsPageLocators.DELETE_POPUP_CANCEL, ElementType.Xpath);
                    if(flag) {
                        flag = webDB.clickAnElement(MaterialsPageLocators.DELETE_POPUP_CANCEL, ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Clicked delete cancel button");
                            flag = clickAddButtonAndWaitForPopup();
                            if (flag) {
                                Thread.sleep(3000);
                                flag = webDB.clickAnElement(MaterialsPageLocators.ADD_POPUP_CANCEL, ElementType.Xpath);
                                if (flag) {
                                    log.logging("info", "Clicked add popup cancel button");
                                    flag = clickFilterButtonAndVerifySearchButton();
                                    if (flag) {
                                        flag = verifyFirstRowPriceHistoryButton();
                                        if (flag) {
                                            log.logging("info", "All links are verified in materials page");
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
     * This method verifies navigation to other pages
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyNavigationToOtherPages() {
        flag = verifyMaterialsPageUrlIsSecured();
        if(flag){
            flag = pumpsPage.navigateToPumpsPage();
            if(flag){
                flag = partsPage.navigateToPartsPage();
            }
        }
        return flag;
    }

    /**
     * This method verifies filter search edit feature
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyFilterSearchIsEditable() {
        flag = webDB.waitForElement(MaterialsPageLocators.FILTER_BUTTON, ElementType.Xpath);
        if(flag){
            flag = webDB.clickAnElement(MaterialsPageLocators.FILTER_BUTTON, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked filter search button");
                flag = webDB.waitForElement(MaterialsPageLocators.FILTER_SEARCH_FIELD, ElementType.Id);
                if(flag){
                    flag = webDB.sendTextToAnElement(MaterialsPageLocators.FILTER_SEARCH_FIELD, PartsPageLocators.DUMMY_NAME, ElementType.Id);
                    if(flag){
                        String valueBefore = webDB.getAttributeFromElement(MaterialsPageLocators.FILTER_SEARCH_FIELD, ElementType.Id,"value");
                        if(!valueBefore.isEmpty()){
                            log.logging("info", "Sent text to filter search field");
                            flag = webDB.clearText(MaterialsPageLocators.FILTER_SEARCH_FIELD, ElementType.Id);
                            if(flag){
                                String value = webDB.getAttributeFromElement(MaterialsPageLocators.FILTER_SEARCH_FIELD, ElementType.Id,"value");
                                if(value.isEmpty()){
                                    log.logging("info", "Cleared field");
                                }else {
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
     * This method clicks add material cancel button
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean clickAddMaterialCancelButton() {
        flag = webDB.waitForElement(MaterialsPageLocators.ADD_POPUP_CANCEL, ElementType.Xpath);
        if(flag){
            flag = webDB.javaScriptClickAnElement(MaterialsPageLocators.ADD_POPUP_CANCEL, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked cancel button");
                WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(MaterialsPageLocators.ADD_POPUP_CANCEL)));
            }
        }
        return flag;
    }

    /**
     * This method verifies material name field is editable
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyMaterialNameFieldIsEditable() {
        flag = webDB.sendTextToAnElement(MaterialsPageLocators.ADD_MATERIAL_NAME_FIELD, PartsPageLocators.DUMMY_NAME, ElementType.Id);
        if(flag){
            String name = webDB.getAttributeFromElement(MaterialsPageLocators.ADD_MATERIAL_NAME_FIELD, ElementType.Id, "value");
            if(!name.isEmpty()){
                log.logging("info", "Entered text on material name field");
                flag = webDB.clearText(MaterialsPageLocators.ADD_MATERIAL_NAME_FIELD, ElementType.Id);
                if(flag){
                    String nameAfter = webDB.getAttributeFromElement(MaterialsPageLocators.ADD_MATERIAL_NAME_FIELD, ElementType.Id, "value");
                    if(nameAfter.isEmpty()){
                        log.logging("info", "Cleared text on material name field");
                    }else{
                        flag = false;
                    }
                }
            }else{
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies text fields are editable
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyAddTextFieldsAreEditable() throws InterruptedException {
        flag = clickAddButtonAndWaitForPopup();
        if(flag){
            Thread.sleep(2000);
            flag = webDB.sendTextToAnElement(MaterialsPageLocators.ADD_MATERIAL_CODE_FIELD, PartsPageLocators.DUMMY_NAME, ElementType.Id);
            if(flag){
                String code = webDB.getAttributeFromElement(MaterialsPageLocators.ADD_MATERIAL_CODE_FIELD, ElementType.Id, "value");
                if(!code.isEmpty()){
                    log.logging("info", "Entered text on material code field");
                    flag = webDB.clearText(MaterialsPageLocators.ADD_MATERIAL_CODE_FIELD, ElementType.Id);
                    if(flag){
                        String codeAfter = webDB.getAttributeFromElement(MaterialsPageLocators.ADD_MATERIAL_CODE_FIELD, ElementType.Id, "value");
                        if(codeAfter.isEmpty()){
                            log.logging("info", "Cleared text on material code field");
                            flag = verifyMaterialNameFieldIsEditable();
                            if(flag) {
                                flag = clickAddMaterialCancelButton();
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
     * This method verifies text fields are editable
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyTextFieldsAreEditable() throws InterruptedException {
        flag = verifyMaterialsPageUrlIsSecured();
        if(flag){
            flag = verifyFilterSearchIsEditable();
            if(flag){
                flag = verifyAddTextFieldsAreEditable();
            }
        }
        return flag;
    }

    /**
     * This method verifies home page redirection
     * @author Vignesh - GWL
     * @param email
     * @return boolean flag
     */
    public boolean verifyHomePageRedirection(String email) {
        flag = verifyMaterialsPageUrlIsSecured();
        if(flag){
            flag = webDB.waitForElement(MaterialsPageLocators.HOME_PAGE_LOGO, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(MaterialsPageLocators.HOME_PAGE_LOGO, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked home page logo");
                    if(email.equals(webDB.getDataFromProperties("companyAdminUsername"))){
                        flag = webDB.waitForElement(UsersPageLocators.USERS_TITLE, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Landed on home page");
                        }
                    }else{
                        flag = webDB.waitForElement(PumpsPageLocators.PUMP_HEADING, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Landed on home page");
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies master menu items accessibility
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean VerifyMasterMenuItemsAccessibility() {
        flag = verifyMaterialsPageViaNavigationPanel();
        if(flag){
            flag = partCategoriesPage.verifyPartCategoriesPageViaNavigationPanel();
            if(flag){
                flag = processesPage.verifyProcessesPageViaNavigationPanel();
                if(flag){
                    flag = variantsPage.verifyVariantsPageViaNavigationPanel();
                    if(flag){
                        flag = pumpTypesPage.verifyPumpTypesPageViaNavigationPanel();
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies placeholder text on search
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyPlaceHolderTextOnSearch() {
        flag = verifyMaterialsPageViaNavigationPanel();
        if(flag) {
            flag = clickFilterButtonAndVerifySearchButton();
            if (flag) {
                String placeHolder = webDB.getAttributeFromElement(MaterialsPageLocators.FILTER_SEARCH_FIELD, ElementType.Id, "placeholder");
                if (!placeHolder.isEmpty()) {
                    log.logging("info", "Search field has placeholder tex");
                } else {
                    flag = false;
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
        flag = verifyMaterialsPageViaNavigationPanel();
        if(flag) {
            flag = clickFilterButtonAndVerifySearchButton();
            if (flag) {
                flag = webDB.waitForElement(MaterialsPageLocators.TABLE_FIRST_ROW_MATERIAL_CODE, ElementType.Xpath);
                if(flag) {
                    String materialCode = webDB.getTextFromElement(MaterialsPageLocators.TABLE_FIRST_ROW_MATERIAL_CODE, ElementType.Xpath);
                    log.logging("info", "Extracted material code is: " + materialCode);
                    flag = webDB.sendTextToAnElement(MaterialsPageLocators.FILTER_SEARCH_FIELD, materialCode, ElementType.Id);
                    if (flag) {
                        log.logging("info", "Sent text on the field");
                        flag = webDB.waitForElement(MaterialsPageLocators.FILTER_SEARCH_BUTTON, ElementType.Xpath);
                        if (flag) {
                            flag = webDB.clickAnElement(MaterialsPageLocators.FILTER_SEARCH_BUTTON, ElementType.Xpath);
                            if (flag) {
                                log.logging("info", "Clicked filter search button");
                                flag = webDB.waitForElement(MaterialsPageLocators.TABLE_FIRST_ROW_MATERIAL_CODE, ElementType.Xpath);
                                if (flag) {
                                    List<WebElement> totalRows = webDB.getDriver().findElements(By.xpath(UsersPageLocators.TABLE_TOTAL_ROWS));
                                    if (totalRows.size() == 1) {
                                        log.logging("info", "One result is shown as expected");
                                        String result = webDB.getTextFromElement(MaterialsPageLocators.TABLE_FIRST_ROW_MATERIAL_CODE, ElementType.Xpath);
                                        if (result.equals(materialCode)) {
                                            log.logging("info", "Result is verified");
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
        return flag;
    }

    /**
     * This method verifies search functionality via keyboard
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifySearchFunctionalityViaKeyboard(){
        flag = verifyMaterialsPageViaNavigationPanel();
        if(flag) {
            flag = clickFilterButtonAndVerifySearchButton();
            if (flag) {
                String materialCode = webDB.getTextFromElement(MaterialsPageLocators.TABLE_FIRST_ROW_MATERIAL_CODE, ElementType.Xpath);
                log.logging("info", "Extracted material code is: "+materialCode);
                flag = webDB.sendTextToAnElement(MaterialsPageLocators.FILTER_SEARCH_FIELD, materialCode, ElementType.Id);
                if(flag){
                    log.logging("info", "Sent text on the field");
                    Actions actions = new Actions(webDB.getDriver());
                    actions.sendKeys(Keys.ENTER).build().perform();
                    flag = webDB.waitForElement(MaterialsPageLocators.TABLE_FIRST_ROW_MATERIAL_CODE, ElementType.Xpath);
                    if(flag){
                        List<WebElement> totalRows = webDB.getDriver().findElements(By.xpath(UsersPageLocators.TABLE_TOTAL_ROWS));
                        if(totalRows.size() == 1){
                            log.logging("info", "One result is shown as expected");
                            String result = webDB.getTextFromElement(MaterialsPageLocators.TABLE_FIRST_ROW_MATERIAL_CODE, ElementType.Xpath);
                            if(result.equals(materialCode)){
                                log.logging("info", "Result is verified");
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
        return flag;
    }

    /**
     * This method verifies invalid search functionality
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean VerifyInvalidSearchFunctionality(){
        flag = verifyMaterialsPageViaNavigationPanel();
        if(flag) {
            flag = clickFilterButtonAndVerifySearchButton();
            if (flag) {
                flag = webDB.sendTextToAnElement(MaterialsPageLocators.FILTER_SEARCH_FIELD, PartsPageLocators.DUMMY_NAME, ElementType.Id);
                if(flag){
                    log.logging("info", "Invalid text sent on field");
                    flag = webDB.clickAnElement(MaterialsPageLocators.FILTER_SEARCH_BUTTON, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Clicked search button");
                        flag = webDB.waitForElement(MaterialsPageLocators.EMPTY_RECORD, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Empty record for invalid search is verified");
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
    public boolean verifyAppliedSearchAfterNavigation(){
        flag = verifySearchFunctionality();
        if(flag){
            flag = partCategoriesPage.verifyPartCategoriesPageViaNavigationPanel();
            if(flag){
                flag = verifyMaterialsPageUrlIsSecured();
                if(flag){
                    flag = webDB.waitForElement(UsersPageLocators.TABLE_TOTAL_ROWS, ElementType.Xpath);
                    if(flag) {
                        List<WebElement> totalRows = webDB.getDriver().findElements(By.xpath(UsersPageLocators.TABLE_TOTAL_ROWS));
                        if (totalRows.size() > 1) {
                            log.logging("info", "Filter removed as expected after navigation");
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
     * This method verifies total client record on pagination
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyTotalClientRecord() {
        flag = verifySearchFunctionality();
        if(flag){
            flag = webDB.waitForElement(UsersPageLocators.PAGINATION_TOTAL_COUNT, ElementType.Xpath);
            if(flag){
                String paginationCount = webDB.getTextFromElement(UsersPageLocators.PAGINATION_TOTAL_COUNT, ElementType.Xpath);
                if(paginationCount.equals(MaterialsPageLocators.SEARCHED_PAGINATION_COUNT)){
                    log.logging("info", "Record count matches with pagination");
                }else{
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies reset option on search
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyResetOptionOnSearch() {
        flag = verifyMaterialsPageUrlIsSecured();
        if(flag){
            flag = webDB.waitForElement(MaterialsPageLocators.FILTER_BUTTON, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(MaterialsPageLocators.FILTER_BUTTON, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked filter button");
                    flag = webDB.waitForElement(MaterialsPageLocators.RESET_BUTTON, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Reset button is visible");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies reset option functionality
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyResetOptionFunctionality() {
        flag = verifyResetOptionOnSearch();
        if(flag){
            flag = webDB.waitForElement(MaterialsPageLocators.TABLE_FIRST_ROW_MATERIAL_CODE, ElementType.Xpath);
            if(flag) {
                List<WebElement> totalRowsBefore = webDB.getDriver().findElements(By.xpath(UsersPageLocators.TABLE_TOTAL_ROWS));
                String materialCode = webDB.getTextFromElement(MaterialsPageLocators.TABLE_FIRST_ROW_MATERIAL_CODE, ElementType.Xpath);
                log.logging("info", "Extracted material code is: " + materialCode);
                flag = webDB.sendTextToAnElement(MaterialsPageLocators.FILTER_SEARCH_FIELD, materialCode, ElementType.Id);
                if (flag) {
                    log.logging("info", "Sent text on the field");
                    flag = webDB.waitForElement(MaterialsPageLocators.RESET_BUTTON, ElementType.Xpath);
                    if (flag) {
                        flag = webDB.clickAnElement(MaterialsPageLocators.RESET_BUTTON, ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Clicked reset button");
                            flag = webDB.waitForElement(MaterialsPageLocators.TABLE_FIRST_ROW_MATERIAL_CODE, ElementType.Xpath);
                            if(flag) {
                                List<WebElement> totalRowsAfter = webDB.getDriver().findElements(By.xpath(UsersPageLocators.TABLE_TOTAL_ROWS));
                                if(totalRowsAfter.size() == totalRowsBefore.size()){
                                    log.logging("info", "Verified reset search functionality");
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
     * This method verifies add item dialog box
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyAddItemDialogBox() {
        flag = verifyMaterialsPageUrlIsSecured();
        if(flag) {
            flag = clickAddButtonAndWaitForPopup();
            if (flag) {
                flag = clickAddMaterialCancelButton();
            }
        }
        return flag;
    }

    /**
     * This method sends material code value
     * @author Vignesh - GWL
     * @param materialCode
     * @return boolean flag
     */
    public boolean sendMaterialCodeValue(String materialCode) {
        flag = webDB.waitForElement(MaterialsPageLocators.ADD_MATERIAL_CODE_FIELD, ElementType.Id);
        if(flag){
            flag = webDB.sendTextToAnElement(MaterialsPageLocators.ADD_MATERIAL_CODE_FIELD, materialCode, ElementType.Id);
            if(flag){
                log.logging("info", "Sent material code on material code field");
            }
        }
        return flag;
    }

    /**
     * This method sends material name value
     * @author Vignesh - GWL
     * @param materialName
     * @return boolean flag
     */
    public boolean sendMaterialNameValue(String materialName) {
        flag = webDB.waitForElement(MaterialsPageLocators.ADD_MATERIAL_NAME_FIELD, ElementType.Id);
        if(flag){
            flag = webDB.sendTextToAnElement(MaterialsPageLocators.ADD_MATERIAL_NAME_FIELD, materialName, ElementType.Id);
            if(flag){
                log.logging("info", "Sent material name on material name field");
            }
        }
        return flag;
    }

    /**
     * This method verifies material price value
     * @author Vignesh - GWL
     * @param materialPrice
     * @return boolean flag
     */
    public boolean sendMaterialPriceValue(String materialPrice){
        flag = webDB.waitForElement(MaterialsPageLocators.ADD_MATERIAL_PRICE, ElementType.Id);
        if(flag){
            flag = webDB.sendTextToAnElement(MaterialsPageLocators.ADD_MATERIAL_PRICE, materialPrice, ElementType.Id);
            if(flag){
                log.logging("info", "Sent material price on material price field");
            }
        }
        return flag;
    }

    /**
     * This method clicks save button
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean clickSaveButton() {
        flag = webDB.waitForElement(MaterialsPageLocators.ADD_POPUP_SAVE, ElementType.Xpath);
        if(flag){
            flag = webDB.clickAnElement(MaterialsPageLocators.ADD_POPUP_SAVE, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked save button");
                flag = webDB.waitForElement(MaterialsPageLocators.MATERIAL_CREATED_MESSAGE, ElementType.Xpath);
                if(flag) {
                    WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(MaterialsPageLocators.MATERIAL_CREATED_MESSAGE)));
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies created material
     * @author Vignesh - GWL
     * @param materialCode
     * @return boolean flag
     */
    public boolean verifyCreatedMaterial(String materialCode){
        flag = webDB.waitForElement(MaterialsPageLocators.TABLE_FIRST_ROW_MATERIAL_CODE, ElementType.Xpath);
        if(flag){
            String mlCode = webDB.getTextFromElement(MaterialsPageLocators.TABLE_FIRST_ROW_MATERIAL_CODE, ElementType.Xpath);
            if(mlCode.equals(materialCode)){
                log.logging("info", "Material created successfully");
            }else{
                log.logging("fail", "Wrong material code");
                flag = false;
            }

        }
        return flag;
    }

    /**
     * This method deletes created material
     * @author Vignesh - GWL
     * @param materialCode
     * @return boolean flag
     */
    public boolean deleteCreatedMaterial(String materialCode){
        flag = webDB.waitForElement("//div[contains(text(), '"+materialCode+"')]/../following-sibling::div[4]//button[3]", ElementType.Xpath);
        if(flag){
            flag = webDB.clickAnElement("//div[contains(text(), '"+materialCode+"')]/../following-sibling::div[4]//button[3]", ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked delete button");
                flag = webDB.waitForElement(MaterialsPageLocators.DELETE_POPUP_CONFIRM, ElementType.Xpath);
                if(flag){
                    flag = webDB.clickAnElement(MaterialsPageLocators.DELETE_POPUP_CONFIRM, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Clicked delete popup confirm");
                        flag = webDB.waitForElement(MaterialsPageLocators.MATERIAL_DELETED_MESSAGE, ElementType.Xpath);
                        if(flag){
                            WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(MaterialsPageLocators.MATERIAL_DELETED_MESSAGE)));
                            flag = webDB.isElementNotDisplayed("//div[contains(text(), '"+materialCode+"')]/../following-sibling::div[4]//button[3]", ElementType.Xpath);
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies adding new material
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyAddingNewMaterial() throws InterruptedException {
        flag = verifyMaterialsPageUrlIsSecured();
        if(flag) {
            flag = clickAddButtonAndWaitForPopup();
            if (flag) {
                Thread.sleep(3000);
                flag = sendMaterialCodeValue(MaterialsPageLocators.MATERIAL_CODE);
                if(flag){
                    flag = sendMaterialNameValue(MaterialsPageLocators.MATERIAL_NAME);
                    if(flag){
                        WebElement element = webDB.getDriver().findElement(By.xpath(MaterialsPageLocators.SELECT_UOM_DROPDOWN));
                        Actions actions = new Actions(webDB.getDriver());
                        actions.click(element).sendKeys(Keys.chord("k")).sendKeys(Keys.ENTER).build().perform();
                        flag = sendMaterialPriceValue(MaterialsPageLocators.MATERIAL_PRICE);
                        if(flag){
                            flag = clickSaveButton();
                            if(flag){
                                flag = verifyCreatedMaterial(MaterialsPageLocators.MATERIAL_CODE);
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies material code error
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyMaterialCodeError(){
        flag = webDB.waitForElement(MaterialsPageLocators.MATERIAL_CODE_ERROR, ElementType.Xpath);
        if(flag){
            String error = webDB.getTextFromElement(MaterialsPageLocators.MATERIAL_CODE_ERROR, ElementType.Xpath);
            if(error.equals(MaterialsPageLocators.MATERIAL_CODE_ERROR_MSG)){
                log.logging("info", "Error message verified for material code field");
                WebElement cssValue = webDB.getDriver().findElement(By.xpath(MaterialsPageLocators.MATERIAL_CODE_ERROR));
                String color = cssValue.getCssValue("color");
                if(color.equals(MaterialsPageLocators.RED_COLOR_VALUE)){
                    log.logging("info", "Color of error message is verified");
                }
            }else{
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies material name error
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyMaterialNameError(){
        flag = webDB.waitForElement(MaterialsPageLocators.MATERIAL_NAME_ERROR, ElementType.Xpath);
        if(flag){
            String error = webDB.getTextFromElement(MaterialsPageLocators.MATERIAL_NAME_ERROR, ElementType.Xpath);
            if(error.equals(MaterialsPageLocators.MATERIAL_NAME_ERROR_MSG)){
                log.logging("info", "Error message verified for material name field");
            }else{
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies uom error
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyUomError(){
        flag = webDB.waitForElement(MaterialsPageLocators.UOM_DROPDOWN_ERROR, ElementType.Xpath);
        if(flag){
            String error = webDB.getTextFromElement(MaterialsPageLocators.UOM_DROPDOWN_ERROR, ElementType.Xpath);
            if(error.equals(MaterialsPageLocators.UOM_ERROR_MSG)){
                log.logging("info", "Error message verified for uom field");
            }else{
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies material price error
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyMaterialPriceError(){
        flag = webDB.waitForElement(MaterialsPageLocators.MATERIAL_PRICE_ERROR, ElementType.Xpath);
        if(flag){
            String error = webDB.getTextFromElement(MaterialsPageLocators.MATERIAL_PRICE_ERROR, ElementType.Xpath);
            if(error.equals(MaterialsPageLocators.MATERIAL_PRICE_ERROR_MSG)){
                log.logging("info", "Error message verified for material price field");
            }else{
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies empty field error
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyEmptyFieldError() throws InterruptedException {
        flag = verifyMaterialsPageUrlIsSecured();
        if(flag) {
            flag = clickAddButtonAndWaitForPopup();
            if (flag) {
                Thread.sleep(2000);
                flag = webDB.waitForElement(MaterialsPageLocators.ADD_POPUP_SAVE, ElementType.Xpath);
                if(flag) {
                    flag = webDB.clickAnElement(MaterialsPageLocators.ADD_POPUP_SAVE, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Clicked save button with an empty field");
                        flag = verifyMaterialCodeError();
                        if(flag){
                            flag = verifyMaterialNameError();
                            if(flag){
                                flag = verifyUomError();
                                if(flag){
                                    flag = verifyMaterialPriceError();
                                    if(flag){
                                        flag = clickAddMaterialCancelButton();
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
     * This method verifies created data in fields
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyCreatedDataInFields() throws InterruptedException {
        flag = verifyAddingNewMaterial();
        if(flag) {
            flag = webDB.waitForElement(MaterialsPageLocators.TABLE_FIRST_ROW_EDIT_ICON, ElementType.Xpath);
            if (flag) {
                flag = webDB.clickAnElement(MaterialsPageLocators.TABLE_FIRST_ROW_EDIT_ICON, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Clicked first row edit icon");
                    Thread.sleep(2000);
                    flag = webDB.waitForElement(MaterialsPageLocators.ADD_MATERIAL_CODE_FIELD, ElementType.Id);
                    if (flag) {
                        String materialCode = webDB.getAttributeFromElement(MaterialsPageLocators.ADD_MATERIAL_CODE_FIELD, ElementType.Id, "value");
                        if (materialCode.equals(MaterialsPageLocators.MATERIAL_CODE)) {
                            log.logging("info", "Material code is verified");
                            flag = webDB.waitForElement(MaterialsPageLocators.ADD_MATERIAL_NAME_FIELD, ElementType.Id);
                            if (flag) {
                                String materialName = webDB.getAttributeFromElement(MaterialsPageLocators.ADD_MATERIAL_NAME_FIELD, ElementType.Id, "value");
                                if (materialName.equals(MaterialsPageLocators.MATERIAL_NAME)) {
                                    log.logging("info", "Material name is verified");
                                    flag = webDB.waitForElement(MaterialsPageLocators.ADD_MATERIAL_PRICE, ElementType.Id);
                                    if (flag) {
                                        String materialPrice = webDB.getAttributeFromElement(MaterialsPageLocators.ADD_MATERIAL_PRICE, ElementType.Id, "value");
                                        if (materialPrice.contains(MaterialsPageLocators.MATERIAL_PRICE)) {
                                            log.logging("info", "Material price is verified");
                                            flag = clickAddMaterialCancelButton();
                                            if(flag){
                                                flag = deleteCreatedMaterial(MaterialsPageLocators.MATERIAL_CODE);
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
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies space as first character for material
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifySpaceAsFirstCharacterForMaterial() throws InterruptedException {
        flag = verifyMaterialsPageUrlIsSecured();
        if(flag) {
            flag = clickAddButtonAndWaitForPopup();
            if (flag) {
                Thread.sleep(2000);
                flag = sendMaterialCodeValue(MaterialsPageLocators.MATERIAL_CODE);
                if(flag){
                    flag = sendMaterialNameValue(" "+MaterialsPageLocators.MATERIAL_NAME);
                    if(flag){
                        WebElement element = webDB.getDriver().findElement(By.xpath(MaterialsPageLocators.SELECT_UOM_DROPDOWN));
                        Actions actions = new Actions(webDB.getDriver());
                        actions.click(element).sendKeys(Keys.chord("k")).sendKeys(Keys.ENTER).build().perform();
                        flag = sendMaterialPriceValue(MaterialsPageLocators.MATERIAL_PRICE);
                        if(flag){
                            flag = clickSaveButton();
                            if(flag){
                                flag = verifyCreatedMaterial(MaterialsPageLocators.MATERIAL_CODE);
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies character limit error
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyCharacterLimitError() throws InterruptedException {
        flag = verifyMaterialsPageUrlIsSecured();
        if(flag) {
            flag = clickAddButtonAndWaitForPopup();
            if (flag) {
                Thread.sleep(2000);
                flag = sendMaterialCodeValue(MaterialsPageLocators.LIMIT_CHECK);
                if (flag) {
                    flag = sendMaterialNameValue(MaterialsPageLocators.LIMIT_CHECK);
                    if (flag) {
                        flag = webDB.waitForElement(MaterialsPageLocators.ADD_POPUP_SAVE, ElementType.Xpath);
                        if(flag) {
                            flag = webDB.clickAnElement(MaterialsPageLocators.ADD_POPUP_SAVE, ElementType.Xpath);
                            if (flag) {
                                log.logging("info", "Clicked save button");
                                flag = webDB.waitForElement(MaterialsPageLocators.MATERIAL_CODE_ERROR, ElementType.Xpath);
                                if(flag){
                                    String codeError = webDB.getTextFromElement(MaterialsPageLocators.MATERIAL_CODE_ERROR, ElementType.Xpath);
                                    if(codeError.equals(MaterialsPageLocators.MAX_LENGTH_ERROR)){
                                        log.logging("info", "Verified error for material code field");
                                        flag = webDB.waitForElement(MaterialsPageLocators.MATERIAL_NAME_ERROR, ElementType.Xpath);
                                        if(flag){
                                            String nameError = webDB.getTextFromElement(MaterialsPageLocators.MATERIAL_NAME_ERROR, ElementType.Xpath);
                                            if(nameError.equals(MaterialsPageLocators.MAX_LENGTH_ERROR)){
                                                log.logging("info", "Verified error for material name field");
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
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies cancel option on add dialogue box
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyCancelOptionOnAddDialogueBox() throws InterruptedException {
        flag = verifyMaterialsPageUrlIsSecured();
        if(flag) {
            flag = webDB.waitForElement(MaterialsPageLocators.TABLE_FIRST_ROW_MATERIAL_CODE, ElementType.Xpath);
            if (flag) {
                String materialCode = webDB.getTextFromElement(MaterialsPageLocators.TABLE_FIRST_ROW_MATERIAL_CODE, ElementType.Xpath);
                flag = clickAddButtonAndWaitForPopup();
                if (flag) {
                    Thread.sleep(2000);
                    flag = clickAddMaterialCancelButton();
                    if (flag) {
                        String materialCodeAfter = webDB.getTextFromElement(MaterialsPageLocators.TABLE_FIRST_ROW_MATERIAL_CODE, ElementType.Xpath);
                        if (materialCodeAfter.equals(materialCode)) {
                            log.logging("info", "Item not saved after clicked cancel as expected");
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
     * This method verifies created item after page refresh
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyCreatedItemAfterPageRefresh() {
        flag = webDB.navigateToRefresh();
        if(flag){
            log.logging("info", "Clicked refresh");
            flag = verifyCreatedMaterial(MaterialsPageLocators.MATERIAL_CODE);
        }
        return flag;
    }

    /**
     * This method verifies error for duplicate item
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyErrorForDuplicateItem() {
        flag = webDB.waitForElement(MaterialsPageLocators.MATERIAL_CODE_ERROR, ElementType.Xpath);
        if(flag) {
            String materialCodeError = webDB.getTextFromElement(MaterialsPageLocators.MATERIAL_CODE_ERROR, ElementType.Xpath);
            if (materialCodeError.equals(MaterialsPageLocators.DUPLICATE_MATERIAL_CODE_ERROR)) {
                log.logging("info", "Error message is verified for material code");
                flag = webDB.waitForElement(MaterialsPageLocators.MATERIAL_NAME_ERROR, ElementType.Xpath);
                if (flag) {
                    String materialNameError = webDB.getTextFromElement(MaterialsPageLocators.MATERIAL_NAME_ERROR, ElementType.Xpath);
                    if (materialNameError.equals(MaterialsPageLocators.DUPLICATE_MATERIAL_NAME_ERROR)) {
                        log.logging("info", "Error message is verified for material name");
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
     * This method verifies error for duplicate material item
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyErrorForDuplicateMaterialItem() throws InterruptedException {
        flag = verifyAddingNewMaterial();
        if(flag){
            flag = clickAddButtonAndWaitForPopup();
            if (flag) {
                Thread.sleep(2000);
                flag = sendMaterialCodeValue(MaterialsPageLocators.MATERIAL_CODE);
                if (flag) {
                    flag = sendMaterialNameValue(MaterialsPageLocators.MATERIAL_NAME);
                    if (flag) {
                        WebElement element = webDB.getDriver().findElement(By.xpath(MaterialsPageLocators.SELECT_UOM_DROPDOWN));
                        Actions actions = new Actions(webDB.getDriver());
                        actions.click(element).sendKeys(Keys.chord("k")).sendKeys(Keys.ENTER).build().perform();
                        flag = sendMaterialPriceValue(MaterialsPageLocators.MATERIAL_PRICE);
                        if (flag) {
                            flag = webDB.waitForElement(MaterialsPageLocators.ADD_POPUP_SAVE, ElementType.Xpath);
                            if (flag) {
                                flag = webDB.clickAnElement(MaterialsPageLocators.ADD_POPUP_SAVE, ElementType.Xpath);
                                if (flag) {
                                    log.logging("info", "Clicked save button");
                                    flag = verifyErrorForDuplicateItem();
                                    if(flag) {
                                        flag = clickAddMaterialCancelButton();
                                        if (flag) {
                                            flag = deleteCreatedMaterial(MaterialsPageLocators.MATERIAL_CODE);
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
     * This method verifies uom dropdown options
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyUOMDropdownOptions() throws InterruptedException {
        flag = verifyMaterialsPageUrlIsSecured();
        if(flag){
            flag = clickAddButtonAndWaitForPopup();
            if (flag) {
                Thread.sleep(2000);
                flag = sendMaterialCodeValue(MaterialsPageLocators.MATERIAL_CODE);
                if (flag) {
                    flag = sendMaterialNameValue(MaterialsPageLocators.MATERIAL_NAME);
                    if (flag) {
                        WebElement element = webDB.getDriver().findElement(By.xpath(MaterialsPageLocators.SELECT_UOM_DROPDOWN));
                        Actions actions = new Actions(webDB.getDriver());
                        actions.click(element).perform();
                        String[] options = {"kilo", "Liters", "Milli", "Inches", "Hours", "Pound"};
                        boolean flag = false;
                        for (String option : options) {
                            actions.sendKeys(Keys.chord(option)).perform();
                            Thread.sleep(500);
                            actions.sendKeys(Keys.ENTER).perform();
                            Thread.sleep(2000);
                            flag = webDB.waitForElement(MaterialsPageLocators.UOM_DROPDOWN_SELECTED_OPTION, ElementType.Xpath);
                            if (flag) {
                                String selectedOption = webDB.getTextFromElement(MaterialsPageLocators.UOM_DROPDOWN_SELECTED_OPTION, ElementType.Xpath);
                                List<String> validOptions = MaterialsPageLocators.VALID_UOM_OPTIONS;
                                if (validOptions.contains(selectedOption)) {
                                    log.logging("info", "Selected option '" + selectedOption + "' is valid.");
                                } else {
                                    log.logging("fail", "Selected option '" + selectedOption + "' is not in the valid options list.");
                                    break;
                                }
                            }
                            actions.click(element).perform();
                        }
                    }
                    flag = clickAddMaterialCancelButton();
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies presence of delete on table rows
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyPresenceOfDeleteButtonOnTable() {
        flag = verifyMaterialsPageUrlIsSecured();
        if(flag){
            flag = webDB.waitForElement(UsersPageLocators.TABLE_TOTAL_ROWS, ElementType.Xpath);
            if(flag){
                List<WebElement> totalRows = webDB.getDriver().findElements(By.xpath(UsersPageLocators.TABLE_TOTAL_ROWS));
                flag = webDB.waitForElement(MaterialsPageLocators.TABLE_TOTAL_DELETE_ICON, ElementType.Xpath);
                if(flag){
                    List<WebElement> totalDeleteIcon = webDB.getDriver().findElements(By.xpath(MaterialsPageLocators.TABLE_TOTAL_DELETE_ICON));
                    if(totalRows.size() == totalDeleteIcon.size()){
                        log.logging("info", "All rows has delete icon");
                    }else{
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies delete via keyboard action
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyDeleteViaKeyboardAction() {
        flag = verifyMaterialsPageUrlIsSecured();
        if(flag){
            flag = webDB.waitForElement(UsersPageLocators.TABLE_FIRST_ROW_CHECKBOX, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(UsersPageLocators.TABLE_FIRST_ROW_CHECKBOX, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked first row checkbox");
                    Actions actions = new Actions(webDB.getDriver());
                    actions.sendKeys(Keys.DELETE).build().perform();
                    flag = webDB.isElementNotDisplayed(MaterialsPageLocators.DELETE_POPUP, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Delete popup is not visible as expected");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies delete popup visibility
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyDeletePopupVisibility() {
        flag = verifyMaterialsPageUrlIsSecured();
        if(flag){
            flag = webDB.waitForElement(MaterialsPageLocators.TABLE_FIRST_ROW_DELETE_ICON, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(MaterialsPageLocators.TABLE_FIRST_ROW_DELETE_ICON, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked first row delete icon");
                    flag = webDB.waitForElement(MaterialsPageLocators.DELETE_POPUP_CANCEL, ElementType.Xpath);
                    if(flag){
                        String text = webDB.getTextFromElement(MaterialsPageLocators.DELETE_POPUP_TEXT, ElementType.Xpath);
                        if(text.contains(MaterialsPageLocators.DELETE_POPUP_TEXT_MSG)){
                            log.logging("info", "Verified delete popup text");
                            flag = webDB.clickAnElement(MaterialsPageLocators.DELETE_POPUP_CANCEL, ElementType.Xpath);
                            if(flag){
                                log.logging("info", "Clicked delete popup cancel");
                                WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(MaterialsPageLocators.DELETE_POPUP_CANCEL)));
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
     * This method verifies cancel option on delete dialogue box
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyCancelOptionOnDeleteDialogueBox() {
        flag = verifyMaterialsPageUrlIsSecured();
        if(flag){
            flag = webDB.waitForElement(MaterialsPageLocators.TABLE_FIRST_ROW_DELETE_ICON, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(MaterialsPageLocators.TABLE_FIRST_ROW_DELETE_ICON, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked first row delete icon");
                    flag = webDB.waitForElement(MaterialsPageLocators.DELETE_POPUP_CANCEL, ElementType.Xpath);
                    if(flag){
                        flag = webDB.clickAnElement(MaterialsPageLocators.DELETE_POPUP_CANCEL, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Clicked popup cancel button");
                            WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(MaterialsPageLocators.DELETE_POPUP_CANCEL)));
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies adding second material
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyAddingSecondMaterial() throws InterruptedException {
        flag = verifyMaterialsPageUrlIsSecured();
        if(flag) {
            flag = clickAddButtonAndWaitForPopup();
            if (flag) {
                Thread.sleep(2000);
                flag = sendMaterialCodeValue(MaterialsPageLocators.SECOND_MATERIAL_CODE);
                if(flag){
                    flag = sendMaterialNameValue(MaterialsPageLocators.SECOND_MATERIAL_NAME);
                    if(flag){
                        WebElement element = webDB.getDriver().findElement(By.xpath(MaterialsPageLocators.SELECT_UOM_DROPDOWN));
                        Actions actions = new Actions(webDB.getDriver());
                        actions.click(element).sendKeys(Keys.chord("k")).sendKeys(Keys.ENTER).build().perform();
                        flag = sendMaterialPriceValue(MaterialsPageLocators.MATERIAL_PRICE);
                        if(flag){
                            flag = clickSaveButton();
                            if(flag){
                                flag = verifyCreatedMaterial(MaterialsPageLocators.SECOND_MATERIAL_CODE);
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method clicks delete and confirm
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean clickDeleteAndConfirm() {
        flag = webDB.waitForElement(MaterialsPageLocators.DELETE_POPUP_CONFIRM, ElementType.Xpath);
        if(flag){
            flag = webDB.clickAnElement(MaterialsPageLocators.DELETE_POPUP_CONFIRM, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked delete popup confirm");
                flag = webDB.waitForElement(MaterialsPageLocators.ALERT_POPUP, ElementType.Xpath);
                if(flag){
                    WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(MaterialsPageLocators.ALERT_POPUP)));
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies selecting multiple items
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifySelectingMultipleItemsAndDelete() throws InterruptedException {
        flag = verifyAddingNewMaterial();
        if(flag){
            flag = verifyAddingSecondMaterial();
            if(flag){
                flag = webDB.waitForElement(UsersPageLocators.TABLE_FIRST_ROW_CHECKBOX, ElementType.Xpath);
                if(flag){
                    flag = webDB.clickAnElement(UsersPageLocators.TABLE_FIRST_ROW_CHECKBOX, ElementType.Xpath);
                    if(flag) {
                        log.logging("info", "Clicked first row checkbox");
                        flag = webDB.waitForElement(UsersPageLocators.TABLE_SECOND_ROW_CHECKBOX, ElementType.Xpath);
                        if (flag) {
                            flag = webDB.clickAnElement(UsersPageLocators.TABLE_SECOND_ROW_CHECKBOX, ElementType.Xpath);
                            if (flag) {
                                log.logging("info", "Clicked second row checkbox");
                                flag = clickDeleteButtonAndVerifyPopup();
                                if(flag){
                                    flag = clickDeleteAndConfirm();
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
     * This method clicks first row edit and verify popup
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean clickFirstRowEditAndVerifyPopup() {
        flag = webDB.waitForElement(MaterialsPageLocators.TABLE_FIRST_ROW_EDIT_ICON, ElementType.Xpath);
        if(flag){
            flag = webDB.clickAnElement(MaterialsPageLocators.TABLE_FIRST_ROW_EDIT_ICON, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked first row edit icon");
                flag = webDB.waitForElement(MaterialsPageLocators.ADD_MATERIAL_CODE_FIELD, ElementType.Id);
                if(flag){
                    log.logging("info", "Edit material popup is visible");
                }
            }
        }
        return flag;
    }

    /**
     * This method edits material code
     * @author Vignesh - GWL
     * @param materialCode
     * @return boolean flag
     */
    public boolean editMaterialCode(String materialCode) throws InterruptedException {
        Thread.sleep(2000);
        flag = webDB.waitForElement(MaterialsPageLocators.ADD_MATERIAL_CODE_FIELD, ElementType.Id);
        if(flag){
            flag = webDB.clearText(MaterialsPageLocators.ADD_MATERIAL_CODE_FIELD, ElementType.Id);
            if(flag){
                log.logging("info", "Cleared material code field");
                flag = webDB.sendTextToAnElement(MaterialsPageLocators.ADD_MATERIAL_CODE_FIELD, materialCode, ElementType.Id);
                if(flag){
                    String value = webDB.getAttributeFromElement(MaterialsPageLocators.ADD_MATERIAL_CODE_FIELD, ElementType.Id, "value");
                    if(value.equals(materialCode)){
                        log.logging("info", "Sent edited material code");
                    }else{
                        log.logging("fail", "Issue on editing material code");
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies click update button
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean clickUpdateButton() {
        flag = webDB.waitForElement(MaterialsPageLocators.EDIT_POPUP_UPDATE_BTN, ElementType.Xpath);
        if(flag){
            flag = webDB.clickAnElement(MaterialsPageLocators.EDIT_POPUP_UPDATE_BTN, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked update button");
                flag = webDB.waitForElement(MaterialsPageLocators.ALERT_POPUP, ElementType.Xpath);
                if(flag){
                    String alert = webDB.getTextFromElement(MaterialsPageLocators.ALERT_POPUP, ElementType.Xpath);
                    if(alert.equals(MaterialsPageLocators.UPDATE_MESSAGE)){
                        log.logging("info", "Update message is verified");
                        WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(MaterialsPageLocators.ALERT_POPUP)));
                    }else if(alert.equals(MaterialsPageLocators.PRICE_DETAILS_UPDATE_MESSAGE)){
                        log.logging("info", "Update message is verified");
                        WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(MaterialsPageLocators.ALERT_POPUP)));
                    }else{
                        log.logging("info", "Issue on verifying message");
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies edit created material
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyEditCreatedMaterial() throws InterruptedException {
        flag = verifyMaterialsPageUrlIsSecured();
        if(flag) {
            flag = verifyAddingNewMaterial();
            if(flag){
                flag = clickFirstRowEditAndVerifyPopup();
                if(flag){
                    flag = editMaterialCode(MaterialsPageLocators.SECOND_MATERIAL_CODE);
                    if(flag){
                        flag = clickUpdateButton();
                        if(flag){
                            flag = verifyCreatedMaterial(MaterialsPageLocators.SECOND_MATERIAL_CODE);
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies alert message for empty field
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyAlterMessageForEmptyField() throws InterruptedException {
        flag = verifyMaterialsPageUrlIsSecured();
        if(flag) {
            flag = clickFirstRowEditAndVerifyPopup();
            if (flag) {
                Thread.sleep(2000);
                flag = webDB.waitForElement(MaterialsPageLocators.ADD_MATERIAL_CODE_FIELD, ElementType.Id);
                if (flag) {
                    flag = webDB.clearText(MaterialsPageLocators.ADD_MATERIAL_CODE_FIELD, ElementType.Id);
                    if (flag) {
                        log.logging("info", "Cleared material code field");
                        flag = webDB.waitForElement(MaterialsPageLocators.EDIT_POPUP_UPDATE_BTN, ElementType.Xpath);
                        if (flag) {
                            flag = webDB.clickAnElement(MaterialsPageLocators.EDIT_POPUP_UPDATE_BTN, ElementType.Xpath);
                            if (flag) {
                                log.logging("info", "Clicked update button");
                                flag = verifyMaterialCodeError();
                                if (flag) {
                                    flag = clickAddMaterialCancelButton();
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
     * This method gets first row material code
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public String getFirstRowMaterialCode() {
        flag = webDB.waitForElement(MaterialsPageLocators.TABLE_FIRST_ROW_MATERIAL_CODE, ElementType.Xpath);
        String materialCode = null;
        if (flag) {
            materialCode = webDB.getTextFromElement(MaterialsPageLocators.TABLE_FIRST_ROW_MATERIAL_CODE, ElementType.Xpath);
            log.logging("info", "Extracted material code is: "+materialCode);
        }
        return materialCode;
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
     * This method verifies default info on edit box
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyDefaultInfoOnEditBox() throws InterruptedException {
        flag = verifyMaterialsPageUrlIsSecured();
        if(flag) {
            String materialCode = getFirstRowMaterialCode();
            String materialName = getFirstRowMaterialName();
            flag = clickFirstRowEditAndVerifyPopup();
            if (flag) {
                Thread.sleep(2000);
                flag = webDB.waitForElement(MaterialsPageLocators.ADD_MATERIAL_CODE_FIELD, ElementType.Id);
                if (flag) {
                    String materialCde = webDB.getAttributeFromElement(MaterialsPageLocators.ADD_MATERIAL_CODE_FIELD, ElementType.Id, "value");
                    if (materialCde.equals(materialCode)) {
                        log.logging("info", "Verified material code");
                        flag = webDB.waitForElement(MaterialsPageLocators.ADD_MATERIAL_NAME_FIELD, ElementType.Id);
                        if (flag) {
                            String materialNme = webDB.getAttributeFromElement(MaterialsPageLocators.ADD_MATERIAL_NAME_FIELD, ElementType.Id, "value");
                            if (materialNme.equals(materialName)) {
                                log.logging("info", "Verified material name");
                                flag = clickAddMaterialCancelButton();
                            }else {
                                flag = false;
                            }
                        }
                    }else {
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies navigation to price history page for first item
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyNavigationToPriceHistoryPageForFirstItem() {
        flag = verifyMaterialsPageUrlIsSecured();
        if(flag) {
            flag = webDB.waitForElement(MaterialsPageLocators.TABLE_FIRST_ROW_PRICE_HISTORY, ElementType.Xpath);
            if (flag) {
                flag = webDB.clickAnElement(MaterialsPageLocators.TABLE_FIRST_ROW_PRICE_HISTORY, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Clicked view icon");
                    flag = webDB.waitForElement(MaterialsPageLocators.PRICE_HISTORY_HEADING, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Navigated to price history page.");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies view icon accessibility
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyViewIconAccessibility() {
        flag = verifyMaterialsPageUrlIsSecured();
        if(flag) {
            flag = webDB.waitForElement(MaterialsPageLocators.TABLE_FIRST_ROW_PRICE_HISTORY, ElementType.Xpath);
            if (flag) {
                String attr = webDB.getAttributeFromElement(MaterialsPageLocators.TABLE_FIRST_ROW_PRICE_HISTORY, ElementType.Xpath, "type");
                if(attr.equals("button")){
                    log.logging("info", "View button is accessible as expected");
                }else{
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies price history in page
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyMultipleViewIcons() {
        flag = verifyMaterialsPageUrlIsSecured();
        if(flag) {
            flag = webDB.waitForElement(MaterialsPageLocators.TABLE_FIRST_ROW_PRICE, ElementType.Xpath);
            if (flag) {
                String price = webDB.getTextFromElement(MaterialsPageLocators.TABLE_FIRST_ROW_PRICE, ElementType.Xpath);
                flag = webDB.waitForElement(MaterialsPageLocators.TABLE_FIRST_ROW_PRICE_HISTORY, ElementType.Xpath);
                if (flag) {
                    flag = webDB.clickAnElement(MaterialsPageLocators.TABLE_FIRST_ROW_PRICE_HISTORY, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Clicked eye icon");
                        flag = webDB.waitForElement(MaterialsPageLocators.PRICE_HISTORY_HEADING, ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Landed on price history page");
                            flag = webDB.waitForElement(MaterialsPageLocators.MATERIAL_PRICE_PAGE_MAT_PRICE, ElementType.Xpath);
                            if(flag){
                                String text = webDB.getTextFromElement(MaterialsPageLocators.MATERIAL_PRICE_PAGE_MAT_PRICE, ElementType.Xpath);
                                if(text.equals(price)){
                                    log.logging("info", "Material price verified on price history page too");
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
     * This method clicks material price page add button
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean clickMaterialPricePageAddBtn() {
        flag = webDB.waitForElement(MaterialsPageLocators.MATERIAL_PRICE_PAGE_ADD_BTN, ElementType.Xpath);
        if(flag) {
            flag = webDB.clickAnElement(MaterialsPageLocators.MATERIAL_PRICE_PAGE_ADD_BTN, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Clicked add button");
            }
        }
        return flag;
    }

    /**
     * This method sends current date
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean sendCurrentDate() {
        flag = webDB.waitForElement(MaterialsPageLocators.ADD_MATERIAL_PRICE_DATE, ElementType.Id);
        if (flag) {
            flag = webDB.clickAnElement(MaterialsPageLocators.ADD_MATERIAL_PRICE_DATE, ElementType.Id);
            if (flag) {
                log.logging("info", "Clicked price date");
                flag = webDB.waitForElement(MaterialsPageLocators.CURRENT_DATE, ElementType.Xpath);
                if (flag) {
                    flag = webDB.clickAnElement(MaterialsPageLocators.CURRENT_DATE, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Sent material price date");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method selects dropdown option
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean selectDropDownOption() {
        flag = webDB.waitForElement(MaterialsPageLocators.SELECT_UOM_DROPDOWN, ElementType.Xpath);
        if (flag) {
            flag = webDB.clickAnElement(MaterialsPageLocators.SELECT_UOM_DROPDOWN, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Clicked select uom dropdown");
                Actions actions = new Actions(webDB.getDriver());
                actions.sendKeys("kilo").sendKeys(Keys.ENTER).build().perform();
            }
        }
        return flag;
    }

    /**
     * This method nds material price
     * @author Vignesh - GWL
     * @param price
     * @return boolean flag
     */
    public boolean sendMaterialPrice(String price) {
        flag = webDB.waitForElement(MaterialsPageLocators.ADD_MATERIAL_PRICE, ElementType.Id);
        if (flag) {
            flag = webDB.sendTextToAnElement(MaterialsPageLocators.ADD_MATERIAL_PRICE, price, ElementType.Id);
            if (flag) {
                log.logging("info", "Sent material price");
            }
        }
        return flag;
    }

    /**
     * This method clicks save for material price popup
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean clickSaveForMaterialPricePopup() {
        flag = webDB.waitForElement(MaterialsPageLocators.ADD_POPUP_SAVE, ElementType.Xpath);
        if(flag){
            flag = webDB.clickAnElement(MaterialsPageLocators.ADD_POPUP_SAVE, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked popup save button");
                flag = webDB.waitForElement(MaterialsPageLocators.ALERT_POPUP, ElementType.Xpath);
                if(flag) {
                    WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(MaterialsPageLocators.ALERT_POPUP)));
                }
            }
        }
        return flag;
    }

    /**
     * This method deletes created material price
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean deleteCreatedMaterialPrice() {
        flag = webDB.waitForElement(MaterialsPageLocators.MATERIAL_PRICE_TABLE_FIRST_ROW_DELETE, ElementType.Xpath);
        if(flag){
            flag = webDB.clickAnElement(MaterialsPageLocators.MATERIAL_PRICE_TABLE_FIRST_ROW_DELETE, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked table first row delete icon");
                flag = clickDeleteAndConfirm();
            }
        }
        return flag;
    }

    /**
     * This method verifies add button on material price page
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyAddButtonOnMaterialPricePage() throws InterruptedException {
        flag = verifyNavigationToPriceHistoryPageForFirstItem();
        if(flag) {
            flag = clickMaterialPricePageAddBtn();
            if(flag) {
                Thread.sleep(2000);
                flag = sendCurrentDate();
                if (flag) {
                    flag = selectDropDownOption();
                    if (flag) {
                        flag = sendMaterialPrice("10");
                        if (flag) {
                            flag = clickSaveForMaterialPricePopup();
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies edited price history item
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyEditPriceHistoryItem() throws InterruptedException {
        flag = webDB.waitForElement(MaterialsPageLocators.MATERIAL_PRICE_TABLE_FIRST_ROW_EDIT, ElementType.Xpath);
        if(flag){
            flag = webDB.clickAnElement(MaterialsPageLocators.MATERIAL_PRICE_TABLE_FIRST_ROW_EDIT, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked first row edit icon");
                Thread.sleep(2000);
                flag = webDB.waitForElement(MaterialsPageLocators.ADD_MATERIAL_PRICE, ElementType.Id);
                if(flag){
                    flag = webDB.clearText(MaterialsPageLocators.ADD_MATERIAL_PRICE, ElementType.Id);
                    if(flag){
                        log.logging("info", "Cleared text");
                        flag = sendMaterialPrice("100");
                        if(flag){
                            flag = clickUpdateButton();
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies edit price history page
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyEditPriceHistoryPage() throws InterruptedException {
        flag = verifyNavigationToPriceHistoryPageForFirstItem();
        if(flag) {
            flag = clickMaterialPricePageAddBtn();
            if (flag) {
                Thread.sleep(2000);
                flag = sendCurrentDate();
                if (flag) {
                    flag = selectDropDownOption();
                    if (flag) {
                        flag = sendMaterialPrice("10");
                        if (flag) {
                            flag = clickSaveForMaterialPricePopup();
                            if (flag) {
                                flag = verifyEditPriceHistoryItem();
                                if(flag){
                                    flag = deleteCreatedMaterialPrice();
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
     * This method verifies search functionality in listing page
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifySearchFunctionalityInListingPage() throws InterruptedException {
        flag = verifyAddButtonOnMaterialPricePage();
        if(flag) {
            flag = webDB.waitForElement(MaterialsPageLocators.MATERIAL_PRICE_FILTER_BUTTON, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(MaterialsPageLocators.MATERIAL_PRICE_FILTER_BUTTON, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked filter button");
                    String firstRowDate = webDB.getTextFromElement(MaterialsPageLocators.MATERIAL_PRICE_TABLE_FIRST_ROW_DATE, ElementType.Xpath);
                    log.logging("info", "Date is: "+firstRowDate);
                    flag = webDB.waitForElement(MaterialsPageLocators.MATERIAL_PRICE_FILTER_SEARCH_FIELD, ElementType.Id);
                    if(flag){
                        flag = webDB.sendTextToAnElement(MaterialsPageLocators.MATERIAL_PRICE_FILTER_SEARCH_FIELD,firstRowDate, ElementType.Id);
                        if(flag){
                            log.logging("info", "Sent text");
                            flag = webDB.waitForElement(MaterialsPageLocators.MATERIAL_PRICE_FILTER_SEARCH_BTN, ElementType.Xpath);
                            if(flag){
                                flag = webDB.clickAnElement(MaterialsPageLocators.MATERIAL_PRICE_FILTER_SEARCH_BTN, ElementType.Xpath);
                                if(flag){
                                    flag = webDB.waitForElement(UsersPageLocators.TABLE_TOTAL_ROWS, ElementType.Xpath);
                                    if(flag){
                                        List<WebElement> totalRows = webDB.getDriver().findElements(By.xpath(UsersPageLocators.TABLE_TOTAL_ROWS));
                                        if(totalRows.size() == 1){
                                            log.logging("info", "Shows only one record as expected");
                                            flag = webDB.waitForElement(MaterialsPageLocators.MATERIAL_PRICE_TABLE_FIRST_ROW_DATE, ElementType.Xpath);
                                            if(flag){
                                                String date = webDB.getTextFromElement(MaterialsPageLocators.MATERIAL_PRICE_TABLE_FIRST_ROW_DATE, ElementType.Xpath);
                                                if(date.equals(firstRowDate)){
                                                    log.logging("info", "Search functionality verified");
                                                    webDB.moveToElement(MaterialsPageLocators.MATERIAL_PRICE_TABLE_FIRST_ROW_DELETE, ElementType.Xpath);
                                                    flag = deleteCreatedMaterialPrice();
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
        }
        return flag;
    }

    /**
     * This method verifies back button functionality
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyBackButtonFunctionality() {
        flag = verifyNavigationToPriceHistoryPageForFirstItem();
        if(flag) {
            flag = webDB.waitForElement(MaterialsPageLocators.BACK_BUTTON, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(MaterialsPageLocators.BACK_BUTTON, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked back button");
                    flag = webDB.waitForElement(MaterialsPageLocators.MATERIALS_HEADING, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Navigated back to material page");
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
        flag = verifyMaterialsPageUrlIsSecured();
        if (flag) {
            flag = webDB.waitForElement(MaterialsPageLocators.MATERIAL_CODE_HEADER, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(MaterialsPageLocators.MATERIAL_CODE_HEADER, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked material code header sorting option");
                    Thread.sleep(2000);
                    List<WebElement> columnElements = webDB.getDriver().findElements(By.xpath(MaterialsPageLocators.TABLE_ALL_MATERIAL_CODE));
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
     * This method verifies alphabetical order sorting
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyAlphabeticalSortingForMaterialName() throws InterruptedException {
        flag = verifyMaterialsPageUrlIsSecured();
        if (flag) {
            flag = webDB.waitForElement(MaterialsPageLocators.MATERIAL_NAME_HEADER, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(MaterialsPageLocators.MATERIAL_NAME_HEADER, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked material code header sorting option");
                    Thread.sleep(2000);
                    List<WebElement> columnElements = webDB.getDriver().findElements(By.xpath(MaterialsPageLocators.TABLE_ALL_MATERIAL_NAME));
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
     * This method verifies alphabetical order sorting
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyAlphabeticalSortingForUOM() throws InterruptedException {
        flag = verifyMaterialsPageUrlIsSecured();
        if (flag) {
            flag = webDB.waitForElement(MaterialsPageLocators.UOM_HEADER, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(MaterialsPageLocators.UOM_HEADER, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked material code header sorting option");
                    Thread.sleep(2000);
                    List<WebElement> columnElements = webDB.getDriver().findElements(By.xpath(MaterialsPageLocators.TABLE_ALL_UOM));
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
     * This method verifies duplicate date validation
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyDuplicateDateValidation() throws InterruptedException {
        flag = verifyAddButtonOnMaterialPricePage();
        if(flag){
            flag = clickMaterialPricePageAddBtn();
            if(flag) {
                Thread.sleep(2000);
                flag = sendCurrentDate();
                if (flag) {
                    flag = selectDropDownOption();
                    if (flag) {
                        flag = sendMaterialPrice("10");
                        if(flag){
                            flag = webDB.waitForElement(MaterialsPageLocators.MATERIAL_PRICE_DATE_ERROR, ElementType.Xpath);
                            if(flag){
                                String error = webDB.getTextFromElement(MaterialsPageLocators.MATERIAL_PRICE_DATE_ERROR, ElementType.Xpath);
                                if(error.equals(MaterialsPageLocators.DUPLICATE_DATE_ERROR)){
                                    log.logging("info", "Error message is verified");
                                    flag = clickAddMaterialCancelButton();
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
     * This method verifies rows per page count
     * @author Vignesh - GWL
     * @param value
     * @return boolean flag
     */
    public boolean verifyRowsPerPageCount(String value){
        flag = webDB.waitForElement(MaterialsPageLocators.MATERIALS_ROWS_PER_PAGE, ElementType.Xpath);
        if(flag){
            flag = webDB.moveToElement(MaterialsPageLocators.MATERIALS_ROWS_PER_PAGE, ElementType.Xpath);
            if(flag) {
                flag = webDB.selectDropDownByValue(MaterialsPageLocators.MATERIALS_ROWS_PER_PAGE, value, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Selected first value from dropdown");
                    flag = webDB.waitForElement(MaterialsPageLocators.MATERIALS_ROWS_PER_PAGE, ElementType.Xpath);
                    if (flag) {
                        String attr = webDB.getAttributeFromElement("//option[@value='"+value+"']", ElementType.Xpath, "selected");
                        if(attr != null){
                            log.logging("info", "The element is selected as expected");
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
     * This method verifies rows per page values
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyRowsPerPageValues() {
        flag = verifyMaterialsPageUrlIsSecured();
        if(flag){
            flag = verifyRowsPerPageCount("25");
            if(flag){
                flag = verifyRowsPerPageCount("50");
                if(flag){
                    flag = verifyRowsPerPageCount("100");
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
    public boolean verifyAlphabeticalSortingForLatestPrice() throws InterruptedException {
        flag = verifyMaterialsPageUrlIsSecured();
        if (flag) {
            flag = webDB.waitForElement(MaterialsPageLocators.LATEST_PRICE_HEADER, ElementType.Xpath);
            if (flag) {
                flag = webDB.clickAnElement(MaterialsPageLocators.LATEST_PRICE_HEADER, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Clicked latest price header sorting option");
                    Thread.sleep(2000);
                    List<WebElement> columnElements = webDB.getDriver().findElements(By.xpath(MaterialsPageLocators.TABLE_ALL_LATEST_PRICE));
                    List<String> originalList = new ArrayList<>();
                    for (WebElement element : columnElements) {
                        originalList.add(element.getText().replace("₹", "").trim());
                    }
                    List<Double> originalNumberList = originalList.stream()
                            .map(price -> Double.parseDouble(price.replace(",", "")))
                            .collect(Collectors.toList());
                    List<Double> sortedNumberList = new ArrayList<>(originalNumberList);
                    Collections.sort(sortedNumberList);
                    List<String> sortedList = sortedNumberList.stream()
                            .map(price -> String.format("₹ %.2f", price))
                            .collect(Collectors.toList());
                    if (originalNumberList.equals(sortedNumberList)) {
                        log.logging("info", "The column is sorted in ascending order based on the latest price.");
                    } else {
                        log.logging("info", "The column is NOT sorted in ascending order based on the latest price.");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies material page redirection after delete
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyMaterialPageRedirectionAfterDelete() throws InterruptedException {
        flag = verifyAddButtonOnMaterialPricePage();
        if(flag){
            flag = deleteCreatedMaterialPrice();
            if(flag){
                flag = webDB.waitForElement(MaterialsPageLocators.PRICE_HISTORY_HEADING, ElementType.Xpath);
                if(flag){
                    log.logging("info", "After deleting record, still in price history page as expected");
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies capitalization in date format
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyCapitalizationInDateFormat() throws InterruptedException {
        flag = verifyNavigationToPriceHistoryPageForFirstItem();
        if(flag) {
            flag = clickMaterialPricePageAddBtn();
            if (flag) {
                Thread.sleep(2000);
                flag = webDB.waitForElement(MaterialsPageLocators.ADD_MATERIAL_PRICE_DATE, ElementType.Id);
                if(flag){
                    String attr = webDB.getAttributeFromElement(MaterialsPageLocators.ADD_MATERIAL_PRICE_DATE, ElementType.Id, "placeholder");
                    if(attr.equals(MaterialsPageLocators.DATE_PICKER_FORMAT)){
                        log.logging("info", "Capitalization of date format is verified");
                        flag = clickAddMaterialCancelButton();
                    }else{
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies invalid material name and code
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyInvalidMaterialNameAndCode() throws InterruptedException {
        flag = verifyMaterialsPageUrlIsSecured();
        if(flag) {
            flag = clickAddButtonAndWaitForPopup();
            if (flag) {
                Thread.sleep(2000);
                flag = sendMaterialCodeValue(MaterialsPageLocators.INVALID_MATERIAL_NAME);
                if (flag) {
                    flag = sendMaterialNameValue(MaterialsPageLocators.INVALID_MATERIAL_CODE);
                    if (flag) {
                        flag = selectDropDownOption();
                        if (flag) {
                            flag = sendMaterialPrice("10");
                            if (flag) {
                                flag = webDB.waitForElement(MaterialsPageLocators.ADD_POPUP_SAVE, ElementType.Xpath);
                                if (flag) {
                                    flag = webDB.clickAnElement(MaterialsPageLocators.ADD_POPUP_SAVE, ElementType.Xpath);
                                    if (flag) {
                                        log.logging("info", "Clicked save button");
                                        flag = webDB.waitForElement(MaterialsPageLocators.MATERIAL_NAME_ERROR, ElementType.Xpath);
                                        if (flag) {
                                            String nameError = webDB.getTextFromElement(MaterialsPageLocators.MATERIAL_NAME_ERROR, ElementType.Xpath);
                                            if (nameError.equals(MaterialsPageLocators.INVALID_MATERIAL_NAME_ERROR)) {
                                                log.logging("info", "Invalid name error verified");
                                                flag = webDB.waitForElement(MaterialsPageLocators.MATERIAL_NAME_ERROR, ElementType.Xpath);
                                                if (flag) {
                                                    String materialError = webDB.getTextFromElement(MaterialsPageLocators.MATERIAL_CODE_ERROR, ElementType.Xpath);
                                                    if (materialError.equals(MaterialsPageLocators.INVALID_MATERIAL_CODE_ERROR)) {
                                                        log.logging("info", "Invalid CODE error verified");
                                                        flag = clickAddMaterialCancelButton();
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
            }
        }
        return flag;
    }

    /**
     * This method verifies regex pattern
     * @author Vignesh - GWL
     * @param price
     * @return boolean flag
     */
    public boolean isValidCurrencyFormat(String price) {
        String regex = "₹\\s?([0-9]{1,3}(,[0-9]{3})*|[0-9]+)\\.[0-9]{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(price);
        return matcher.matches();
    }

    /**
     * This method verifies price amount format
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyPriceAmountFormat() {
        flag = verifyMaterialsPageUrlIsSecured();
        if(flag){
            flag = webDB.waitForElement(MaterialsPageLocators.TABLE_FIRST_ROW_PRICE, ElementType.Xpath);
            if(flag){
                String price = webDB.getTextFromElement(MaterialsPageLocators.TABLE_FIRST_ROW_PRICE, ElementType.Xpath);
                flag = isValidCurrencyFormat(price);
                if(flag){
                    log.logging("info", "Price amount format is verified");
                }
            }
        }
        return flag;
    }

    /**
     * This method extracts last number
     * @author Vignesh - GWL
     * @param input
     * @return boolean flag
     */
    public String extractLastNumber(String input) {
        String regex = "\\d+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    /**
     * This method verifies page entries count
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyMaterialPageEntriesCount() throws InterruptedException {
        flag = verifyMaterialsPageUrlIsSecured();
        if(flag){
            flag = webDB.waitForElement(MaterialsPageLocators.TOTAL_ITEMS_COUNT, ElementType.Xpath);
            if(flag) {
                String rowsCount = webDB.getTextFromElement(MaterialsPageLocators.TOTAL_ITEMS_COUNT, ElementType.Xpath);
                String beforeExtractedNumber = extractLastNumber(rowsCount);
                log.logging("info", "Items count before creation is: " + beforeExtractedNumber);
                flag = verifyAddingNewMaterial();
                if (flag) {
                    flag = webDB.waitForElement(MaterialsPageLocators.TOTAL_ITEMS_COUNT, ElementType.Xpath);
                    if (flag) {
                        String rowsCountAfter = webDB.getTextFromElement(MaterialsPageLocators.TOTAL_ITEMS_COUNT, ElementType.Xpath);
                        String afterExtractedNumber = extractLastNumber(rowsCountAfter);
                        log.logging("info", "Items count after creation is: " + afterExtractedNumber);
                        if ((Integer.parseInt(beforeExtractedNumber) == Integer.parseInt(afterExtractedNumber) - 1)) {
                            log.logging("info", "Count got increased in material page as expected");
                            flag = deleteCreatedMaterial(MaterialsPageLocators.MATERIAL_CODE);
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
     * This method verifies material price page entries count
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyMaterialPricePageEntriesCount() throws InterruptedException {
        flag = verifyNavigationToPriceHistoryPageForFirstItem();
        if(flag) {
            flag = webDB.waitForElement(MaterialsPageLocators.TOTAL_ITEMS_COUNT, ElementType.Xpath);
            if(flag) {
                String rowsCount = webDB.getTextFromElement(MaterialsPageLocators.TOTAL_ITEMS_COUNT, ElementType.Xpath);
                String beforeExtractedNumber = extractLastNumber(rowsCount);
                log.logging("info", "Items count before creation is: " + beforeExtractedNumber);
                flag = verifyAddButtonOnMaterialPricePage();
                if (flag) {
                    flag = webDB.waitForElement(MaterialsPageLocators.TOTAL_ITEMS_COUNT, ElementType.Xpath);
                    if (flag) {
                        String rowsCountAfter = webDB.getTextFromElement(MaterialsPageLocators.TOTAL_ITEMS_COUNT, ElementType.Xpath);
                        String afterExtractedNumber = extractLastNumber(rowsCountAfter);
                        log.logging("info", "Items count after creation is: " + afterExtractedNumber);
                        if ((Integer.parseInt(beforeExtractedNumber) == Integer.parseInt(afterExtractedNumber) - 1)) {
                            log.logging("info", "Count got increased in material page as expected");
                            flag = deleteCreatedMaterialPrice();
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
     * This method verifies length value error
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyLengthValueError() throws InterruptedException {
        flag = verifyCharacterLimitError();
        if(flag){
            flag = webDB.clearText(MaterialsPageLocators.ADD_MATERIAL_CODE_FIELD, ElementType.Id);
            if(flag){
                flag = webDB.sendTextToAnElement(MaterialsPageLocators.ADD_MATERIAL_CODE_FIELD,MaterialsPageLocators.MATERIAL_CODE, ElementType.Id);
                if(flag) {
                    log.logging("info", "Cleared material code field");
                    flag = webDB.clearText(MaterialsPageLocators.ADD_MATERIAL_NAME_FIELD, ElementType.Id);
                    if (flag) {
                        log.logging("info", "Cleared material name field");
                        flag = webDB.sendTextToAnElement(MaterialsPageLocators.ADD_MATERIAL_NAME_FIELD,MaterialsPageLocators.MATERIAL_NAME, ElementType.Id);
                        if(flag){
                            flag = webDB.isElementNotDisplayed(MaterialsPageLocators.MATERIAL_CODE_ERROR, ElementType.Xpath);
                            if(flag){
                                flag = webDB.isElementNotDisplayed(MaterialsPageLocators.MATERIAL_NAME_ERROR, ElementType.Xpath);
                                if(flag){
                                    log.logging("info", "Error message cleared after entering valid names");
                                    flag = clickAddMaterialCancelButton();
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
     * This method extracts currency symbol
     * @author Vignesh - GWL
     * @param input
     * @return boolean flag
     */
    public static String extractCurrencySymbol(String input) {
        String regex = "^[^\\d\\s\\w]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    /**
     * This method changes currency option
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean changeCurrencyOption() {
        flag = webDB.waitForElement(UsersPageLocators.CURRENCY_VALUE, ElementType.Xpath);
        if(flag){
            String currency = webDB.getTextFromElement(UsersPageLocators.CURRENCY_VALUE, ElementType.Xpath);
            log.logging("info", "Currency is: "+currency);
            WebElement element =  webDB.getDriver().findElement(By.xpath(UsersPageLocators.CURRENCY_VALUE));
            Actions actions = new Actions(webDB.getDriver());
            if(currency.equals("$ (USD)")){
                log.logging("info", "Changing currency frm USD");
                actions.click(element).sendKeys("INR").sendKeys(Keys.ENTER).build().perform();
            }else{
                log.logging("info", "Changing currency frm INR");
                actions.click(element).sendKeys("USD").sendKeys(Keys.ENTER).build().perform();
            }
            flag = webDB.waitForElement(UsersPageLocators.EDIT_USER_UPDATE_BTN, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(UsersPageLocators.EDIT_USER_UPDATE_BTN, ElementType.Xpath);
                if(flag){
                    flag = webDB.waitForElement(UsersPageLocators.EDIT_USER_SUCCESS_POPUP, ElementType.Xpath);
                    if(flag){
                        WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(10));
                        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(UsersPageLocators.EDIT_USER_SUCCESS_POPUP)));
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method changes currency of company admin
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean changeCurrencyOfCompanyAdmin() throws InterruptedException {
        flag = commonPage.verifyLogout();
        if(flag) {
            flag = loginPage.verifyValidLogin(webDB.getDataFromProperties("superAdminUsername"), webDB.getDataFromProperties("superAdminPassword"));
            if(flag) {
                flag = webDB.waitForElement(UsersPageLocators.PAGINATION_LAST_PAGE, ElementType.Id);
                if(flag) {
                    flag = webDB.moveToElement(UsersPageLocators.PAGINATION_LAST_PAGE, ElementType.Id);
                    if(flag){
                        flag = webDB.clickAnElement(UsersPageLocators.PAGINATION_LAST_PAGE, ElementType.Id);
                        if(flag){
                            log.logging("info", "Clicked pagination last icon");
                            flag = webDB.waitForElement(UsersPageLocators.TABLE_LAST_ROW_EDIT_ICON, ElementType.Xpath);
                            if(flag){
                                flag = webDB.clickAnElement(UsersPageLocators.TABLE_LAST_ROW_EDIT_ICON, ElementType.Xpath);
                                if(flag){
                                    Thread.sleep(3000);
                                    log.logging("info", "Clicked table last row edit icon");
                                    flag = changeCurrencyOption();
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
     * This method verifies selecting dropdown options
     * @author Vignesh - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyCompanyCurrencyOnUser() throws InterruptedException {
        flag = verifyMaterialsPageUrlIsSecured();
        if(flag){
            flag = webDB.waitForElement(MaterialsPageLocators.TABLE_FIRST_ROW_PRICE, ElementType.Xpath);
            if(flag){
                String currentCurrency = webDB.getTextFromElement(MaterialsPageLocators.TABLE_FIRST_ROW_PRICE, ElementType.Xpath);
                String extractedCurrency = extractCurrencySymbol(currentCurrency);
                log.logging("info", "Current currency is: "+extractedCurrency);
                flag = changeCurrencyOfCompanyAdmin();
                if(flag){
                    flag = commonPage.verifyLogout();
                    if(flag){
                        flag = loginPage.verifyValidLogin(webDB.getDataFromProperties("companyAdminUsername"), webDB.getDataFromProperties("companyAdminPassword"));
                        if(flag){
                            flag = verifyMaterialsPageUrlIsSecured();
                            if(flag) {
                                flag = webDB.waitForElement(MaterialsPageLocators.TABLE_FIRST_ROW_PRICE, ElementType.Xpath);
                                if (flag) {
                                    String afterCurrency = webDB.getTextFromElement(MaterialsPageLocators.TABLE_FIRST_ROW_PRICE, ElementType.Xpath);
                                    String extractedAfterCurrency = extractCurrencySymbol(afterCurrency);
                                    log.logging("info", "After edited currency is: " + extractedAfterCurrency);
                                    if(!extractedCurrency.equals(extractedAfterCurrency)){
                                        log.logging("info", "Currency chanegs verified");
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

}
