package QKART_SANITY_LOGIN.Module1;

import java.util.List;
import javax.xml.xpath.XPath;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResult {
    WebElement parentElement;

    public SearchResult(WebElement SearchResultElement) {
        this.parentElement = SearchResultElement;
    }

    /*
     * Return title of the parentElement denoting the card content section of a
     * search result
     */
    public String getTitleofResult() {
        String titleOfSearchResult = "";
        // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
        // Find the element containing the title (product name) of the search result and
        // assign the extract title text to titleOfSearchResult
        WebElement title_name =parentElement.findElement(By.xpath("//*[@id='root']/div/div/div[3]/div/div[2]/div/div/div[1]/p[1]"));
        titleOfSearchResult = title_name.getText();
        return titleOfSearchResult;

    }

    /*
     * Return Boolean denoting if the open size chart operation was successful
     */
    public Boolean openSizechart() {
        try {

            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            // Find the link of size chart in the parentElement and click on it
            WebElement sizeChartWebElement =parentElement.findElement( By.xpath("//button[text()='Size chart']"));
            sizeChartWebElement.click();
            Thread.sleep(2000);
            return true;
            
        } catch (Exception e) {
            System.out.println("Exception while opening Size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the close size chart operation was successful
     */
    public Boolean closeSizeChart(WebDriver driver) {
        try {
            Thread.sleep(2000);
            Actions action = new Actions(driver);

            // Clicking on "ESC" key closes the size chart modal
            action.sendKeys(Keys.ESCAPE);
            action.perform();
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            System.out.println("Exception while closing the size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean based on if the size chart exists
     */
    public Boolean verifySizeChartExists() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            /*
             * Check if the size chart element exists. If it exists, check if the text of
             * the element is "SIZE CHART". If the text "SIZE CHART" matches for the
             * element, set status = true , else set to false
             */

             WebElement sizeChartWebElement = parentElement.findElement( By.xpath("//button[text()='Size chart']"));
             if(sizeChartWebElement.isDisplayed()){
                if(sizeChartWebElement.getText().equals("SIZE CHART")){
                    status=true;}
                    else{
                        status=false;
                    }
                }
             
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    /*
     * Return Boolean if the table headers and body of the size chart matches the
     * expected values
     */
    public Boolean validateSizeChartContents(List<String> expectedTableHeaders, List<List<String>> expectedTableBody,
            WebDriver driver) {
        Boolean status = true;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            /*
             * Locate the table element when the size chart modal is open
             * 
             * Validate that the contents of expectedTableHeaders is present as the table
             * header in the same order
             * 
             * Validate that the contents of expectedTableBody are present in the table body
             * in the same order
             */

             List<WebElement> tableHeaders = driver
             .findElements(By.xpath("/html/body/div[2]/div[3]/div/div/table/thead/tr/th"));
             for(int i=0;i<expectedTableHeaders.size();i++){
                WebElement headerElement =tableHeaders.get(i);
                String headerValue = expectedTableHeaders.get(i);
                if(!headerElement.getText().equals(headerValue)){
                    status=false;
                }
             }
             for(int i = 0;i<expectedTableBody.size();i++){
                int tr = i+ 1;
                List<String> table_Rows =expectedTableBody.get(i);
                for(int j =0;j < table_Rows.size();j++){
                    int td =j + 1;
                    WebElement tableValue = driver.findElement((By.xpath("/html/body/div[2]/div[3]/div/div/table/tbody/tr["+tr+"]/td["+td+"]")));
                    String actualValue =tableValue.getText();
                    String expectedValue = table_Rows.get(j);
                    if(!actualValue.equals(expectedValue)){
                        status= false;
                    }
                }
             }


            return status;

        } catch (Exception e) {
            System.out.println("Error while validating chart contents");
            return false;
        }
    }

    /*
     * Return Boolean based on if the Size drop down exists
     */
    public Boolean verifyExistenceofSizeDropdown(WebDriver driver) {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            // If the size dropdown exists and is displayed return true, else return false
            WebElement sizeDropDown = parentElement.findElement(By.xpath("//select[@id='uncontrolled-native']"));
            if(sizeDropDown.isDisplayed()){
                status= true;
            }
            else{
                status = false;
            }
            
            return status;
        } catch (Exception e) {
            return status;
        }
    }
}