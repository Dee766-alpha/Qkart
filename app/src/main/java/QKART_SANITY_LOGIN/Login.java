package QKART_SANITY_LOGIN;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.*;

public class Login {
    ChromeDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app/login";

    public Login(ChromeDriver driver) {
        this.driver = driver;
    }

    public void navigateToLoginPage() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    public Boolean PerformLogin(String Username, String Password) throws InterruptedException {
        // Find the Username Text Box
        WebElement username_txt_box = this.driver.findElement(By.id("username"));

        // Enter the username
        username_txt_box.sendKeys(Username);

        // Wait for user name to be entered
        Thread.sleep(1000);

        // Find the password Text Box
        WebElement password_txt_box = this.driver.findElement(By.id("password"));

        // Enter the password
        password_txt_box.sendKeys(Password);

        // Find the Login Button
        WebElement login_button = driver.findElement(By.className("button"));

        // Click the login Button
        login_button.click();

        // Wait for Login action to complete
        Thread.sleep(5000);

        return this.VerifyUserLoggedIn(Username);
    }

    public Boolean VerifyUserLoggedIn(String Username) {
        try {
            // Find the username label (present on the top right of the page)
            return username_label.getText().equals(Username);
        } catch (Exception e) {
            return false;
        }

    }

}
