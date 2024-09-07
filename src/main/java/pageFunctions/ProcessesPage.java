package pageFunctions;

import locators.HomePageLocators;
import locators.ProcessesPageLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class ProcessesPage {

    WebDriverBase webDB = new WebDriverBase();
    ReportLoger log = new ReportLoger();
    boolean flag;

    /**
     * This method verifies processes page via navigation panel
     * @author Vignesh - GWL
     * @return boolean flag
     */
    public boolean verifyProcessesPageViaNavigationPanel() {
        flag = webDB.waitForElement(ProcessesPageLocators.PROCESSES_SIDE_MENU, ElementType.LinkText);
        if(flag){
            flag = webDB.javaScriptClickAnElement(ProcessesPageLocators.PROCESSES_SIDE_MENU, ElementType.LinkText);
            if(flag) {
                flag = webDB.waitForElement(HomePageLocators.SIDE_MENU_CLOSE_ICON, ElementType.Xpath);
                if (flag) {
                    flag = webDB.clickAnElement(HomePageLocators.SIDE_MENU_CLOSE_ICON, ElementType.Xpath);
                    if (flag) {
                        flag = webDB.waitForElement(ProcessesPageLocators.PROCESSES_HEADING, ElementType.Xpath);
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
