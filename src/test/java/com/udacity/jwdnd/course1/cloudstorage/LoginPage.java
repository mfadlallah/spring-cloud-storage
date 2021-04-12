package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriverWait wait;

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "loginSubmit")
    private WebElement loginSubmit;

    @FindBy(id = "signup-link")
    private WebElement signupLink;

    public LoginPage(WebDriver driver) {
        wait = new WebDriverWait(driver, 2);
        PageFactory.initElements(driver, this);
    }

    private void setUserName(String userName) {
        wait.until(ExpectedConditions.elementToBeClickable(inputUsername)).sendKeys(userName);
    }

    private void setPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(inputPassword)).sendKeys(password);
    }

    private void submit() {
        wait.until(ExpectedConditions.elementToBeClickable(loginSubmit)).submit();
    }

    public void goToSignUpPage() {
        wait.until(ExpectedConditions.elementToBeClickable(signupLink)).submit();
    }

    public void login(String userName,
                      String passWord) {
        setUserName(userName);
        setPassword(passWord);
        submit();
    }
}
