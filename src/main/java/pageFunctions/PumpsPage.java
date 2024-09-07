package pageFunctions;

import locators.PartsPageLocators;
import locators.PumpsPageLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class PumpsPage {

    WebDriverBase webDB = new WebDriverBase();
    ReportLoger log = new ReportLoger();
    boolean flag;

    /**
     * This method navigates to pumps page
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean navigateToPumpsPage() {
        flag = webDB.waitForElement(PumpsPageLocators.PUMPS_SIDE_MENU, ElementType.Xpath);
        if(flag){
            flag = webDB.moveToElement(PumpsPageLocators.PUMPS_SIDE_MENU, ElementType.Xpath);
            if(flag){
                flag = webDB.javaScriptClickAnElement(PumpsPageLocators.PUMPS_SIDE_MENU, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked pumps option from side menu");
                    flag = webDB.waitForElement(PumpsPageLocators.PUMP_HEADING, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Landed on pumps page");
                    }
                }
            }
        }
        return flag;
    }

}
