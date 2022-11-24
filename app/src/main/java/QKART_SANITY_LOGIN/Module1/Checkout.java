package QKART_SANITY_LOGIN.Module1;

import java.util.List;
import javax.lang.model.util.ElementScanner6;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Checkout {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app/checkout";

    public Checkout(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToCheckout() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    /*
     * Return Boolean denoting the status of adding a new address
     */
    public Boolean addNewAddress(String addresString) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Click on the "Add new address" button, enter the addressString in the address
             * text box and click on the "ADD" button to save the address
             */
            Thread.sleep(3000);
            WebElement addNewAddressElement =
            driver.findElement(By.xpath("//button[@id='add-new-btn']"));
            addNewAddressElement.click();
            Thread.sleep(1000);
            WebElement addressElement =driver.findElement(By.xpath("//textarea[@placeholder='Enter your complete address']"));
            addressElement.sendKeys(addresString);
            WebElement addButton =driver.findElement(By.xpath("//button[text()='Add']"));
            addButton.click();

            return true;
        } catch (Exception e) {
            System.out.println("Exception occurred while entering address: " + e.getMessage());
            return false;

        }
    }

    /*
     * Return Boolean denoting the status of selecting an available address
     */
    public Boolean selectAddress(String addressToSelect) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Iterate through all the address boxes to find the address box with matching
             * text, addressToSelect and click on it
             */
            List<WebElement> addElements =driver
            .findElements(By.xpath("//input[@name='address']/parent::span/parent::div/p"));
            for(WebElement addressElement : addElements){
                if(addressElement.getText().equals(addressToSelect)){
                    addressElement.click();
                    break;
                }
                else{
                    System.out.println("Unable to find the given address");
           
                }
            }
                //System.out.println("Unable to find the given address");
            return false;
                
            
            
            
        } catch (Exception e) {
            System.out.println("Exception Occurred while selecting the given address: " + e.getMessage());
            return false;
        }

    }

    /*
     * Return Boolean denoting the status of place order action
     */
    public Boolean placeOrder() {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            // Find the "PLACE ORDER" button and click on it

            Thread.sleep((2000));
            WebElement  placeOrderButton=
            driver.findElement(By.xpath("//button[text()='PLACE ORDER']"));
            placeOrderButton.click();
            return true;
        }
            //return false;

         catch (Exception e) {
            System.out.println("Exception while clicking on PLACE ORDER: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the insufficient balance message is displayed
     */
    public Boolean verifyInsufficientBalanceMessage() {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 08: MILESTONE 7
            WebElement inmessage = driver.findElement(By.id("notistack-snackbar"));
            if(inmessage.getText().equals("You do not have enough balance in your wallet for this purchase")){
                return true;
            }else{
                return false;
            }
            
            
        }catch (Exception e) {
            System.out.println("Exception while verifying insufficient balance message: " + e.getMessage());
            return false;
        }
    }
}
