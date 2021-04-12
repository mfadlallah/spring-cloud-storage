package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupPage {

    private WebDriverWait wait;

    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "signupSubmit")
    private WebElement signupSubmit;

    @FindBy(id = "login-link")
    private WebElement loginLink;

    @FindBy(linkText = "Back to Login")
    private WebElement backToLogin;


    public SignupPage(WebDriver driver) {
        wait = new WebDriverWait(driver, 2);
        PageFactory.initElements(driver, this);
    }

    public void setFirstName(String firstName) {
        wait.until(ExpectedConditions.elementToBeClickable(inputFirstName)).sendKeys(firstName);
    }

    public void setLasttName(String lasttName) {
        wait.until(ExpectedConditions.elementToBeClickable(inputLastName)).sendKeys(lasttName);
    }

    public void setUserName(String userName) {
        wait.until(ExpectedConditions.elementToBeClickable(inputUsername)).sendKeys(userName);
    }

    public void setPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(inputPassword)).sendKeys(password);
    }

    public void submit() {
        wait.until(ExpectedConditions.elementToBeClickable(signupSubmit)).submit();
    }

    public void clickLoginInSuccessMessage() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    public void backToLoginClick() {
        wait.until(ExpectedConditions.elementToBeClickable(backToLogin)).click();
    }

    public void signup(String firstName,
                       String lasttName,
                       String userName,
                       String password) {

        setUserName(userName);
        setPassword(password);
        setFirstName(firstName);
        setLasttName(lasttName);

        signupSubmit.submit();
    }
}
