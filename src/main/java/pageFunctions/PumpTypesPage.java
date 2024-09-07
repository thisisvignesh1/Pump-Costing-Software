package pageFunctions;

import locators.HomePageLocators;
import locators.PumpTypesLocators;
import locators.VariantsPageLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class PumpTypesPage {

    WebDriverBase webDB = new WebDriverBase();
    ReportLoger log = new ReportLoger();
    boolean flag;

    /**
     * This method verifies pump types page via navigation panel
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyPumpTypesPageViaNavigationPanel() {
        flag = webDB.waitForElement(PumpTypesLocators.PUMP_TYPES_SIDE_MENU, ElementType.LinkText);
        if(flag){
            flag = webDB.javaScriptClickAnElement(PumpTypesLocators.PUMP_TYPES_SIDE_MENU, ElementType.LinkText);
            if(flag) {
                flag = webDB.waitForElement(HomePageLocators.SIDE_MENU_CLOSE_ICON, ElementType.Xpath);
                if (flag) {
                    flag = webDB.clickAnElement(HomePageLocators.SIDE_MENU_CLOSE_ICON, ElementType.Xpath);
                    if (flag) {
                        flag = webDB.waitForElement(PumpTypesLocators.PUMP_TYPES_HEADING, ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Landed on processes page");
                        }
                    }
                }
            }
        }
        return flag;
    }
}
