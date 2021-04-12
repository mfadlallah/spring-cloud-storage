package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CredentialDialog {


    @FindBy(id = "credential-url")
    private WebElement urlInput;

    @FindBy(id = "credential-username")
    private WebElement usernameInput;

    @FindBy(id = "credential-password")
    private WebElement passwordInput;

    @FindBy(id = "saveCredentialButton")
    private WebElement saveCredentialButton;

    private WebDriver driver;

    private JavascriptExecutor javascriptExecutor;

    private WebDriverWait wait;

    public CredentialDialog(WebDriver driver) {
        this.driver = driver;
        this.javascriptExecutor = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 4);
    }

    private void setUserName(String username) {
        javascriptExecutor.executeScript("arguments[0].value='';", usernameInput);
        javascriptExecutor.executeScript("arguments[0].value='" + username + "';", usernameInput);
    }

    private void setUrl(String url) {
        javascriptExecutor.executeScript("arguments[0].value='';", urlInput);
        javascriptExecutor.executeScript("arguments[0].value='" + url + "';", urlInput);
    }

    private void setPassword(String password) {
        javascriptExecutor.executeScript("arguments[0].value='';", passwordInput);
        javascriptExecutor.executeScript("arguments[0].value='" + password + "';", passwordInput);
    }

    private void dialogSubmit() {
        javascriptExecutor.executeScript("arguments[0].click();", saveCredentialButton);
    }

    public void addEditCredential(String url,
                                  String username,
                                  String password) {
        setUserName(username);
        setUrl(url);
        setPassword(password);
        dialogSubmit();
    }

    public String getViewablePassword() {

        return javascriptExecutor.executeScript("return arguments[0].value;", passwordInput).toString();
    }
}
