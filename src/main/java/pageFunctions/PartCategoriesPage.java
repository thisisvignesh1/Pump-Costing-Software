package pageFunctions;

import locators.HomePageLocators;
import locators.MaterialsPageLocators;
import locators.PartCategoriesLocators;
import locators.UsersPageLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class PartCategoriesPage {

    WebDriverBase webDB = new WebDriverBase();
    ReportLoger log = new ReportLoger();
    boolean flag;

    /**
     * This method verifies part categories page via navigation panel
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyPartCategoriesPageViaNavigationPanel() {
        flag = webDB.waitForElement(PartCategoriesLocators.PART_CATEGORIES_SIDE_MENU, ElementType.LinkText);
        if(flag){
            flag = webDB.javaScriptClickAnElement(PartCategoriesLocators.PART_CATEGORIES_SIDE_MENU, ElementType.LinkText);
            if(flag) {
                flag = webDB.waitForElement(HomePageLocators.SIDE_MENU_CLOSE_ICON, ElementType.Xpath);
                if (flag) {
                    flag = webDB.clickAnElement(HomePageLocators.SIDE_MENU_CLOSE_ICON, ElementType.Xpath);
                    if (flag) {
                        flag = webDB.waitForElement(PartCategoriesLocators.PART_CATEGORIES_HEADING, ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Landed on part categories page");
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies part categories page url
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyPartCategoriesPageUrl() {
        flag = webDB.waitForElement(UsersPageLocators.USERS_TITLE, ElementType.Xpath);
        if(flag){
            flag = webDB.navigateToUrl(webDB.getDataFromProperties("partCategoriesPageUrl"));
            if(flag){
                flag = webDB.waitForElement(PartCategoriesLocators.PART_CATEGORIES_HEADING, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Navigated to part categories page");
                }
            }
        }
        return flag;
    }

}
