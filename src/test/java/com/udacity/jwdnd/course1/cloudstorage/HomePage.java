package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {
    private WebDriverWait wait;

    @FindBy(id = "logoutButton")
    private WebElement logoutDiv;

    // Notes Web Elements
    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id = "editNote")
    private WebElement editNoteLink;

    @FindBy(id = "deleteNote")
    private WebElement deleteNoteLink;

    @FindBy(id = "addNewNote")
    private WebElement addNewNoteButton;

    @FindBy(id = "notesTable")
    private WebElement notesTable;

    // Credential Web Elements
    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(id = "editCredential")
    private WebElement editCredentialLink;

    @FindBy(id = "deleteCredential")
    private WebElement deleteCredentialLink;

    @FindBy(id = "addNewCredential")
    private WebElement addNewCredentialButton;

    @FindBy(id = "credentialTable")
    private WebElement credentialsTable;


    private WebDriver driver;
    private JavascriptExecutor javascriptExecutor;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.javascriptExecutor = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, 4);
        PageFactory.initElements(driver, this);

    }

    public void logoutSubmit() {
        javascriptExecutor.executeScript("arguments[0].click();", logoutDiv);
    }

    public void openNoteTab() {
        javascriptExecutor.executeScript("arguments[0].click();", notesTab);
    }

    public void deleteNote() {
        javascriptExecutor.executeScript("arguments[0].click();", deleteNoteLink);
    }

    public void editNote() {
        javascriptExecutor.executeScript("arguments[0].click();", editNoteLink);
    }

    public void openAddEditNoteDialog() {
        javascriptExecutor.executeScript("arguments[0].click();", addNewNoteButton);

    }

    public void openCredentialsTab() {
        javascriptExecutor.executeScript("arguments[0].click();", credentialsTab);
    }

    public void deleteCredential() {
        javascriptExecutor.executeScript("arguments[0].click();", deleteCredentialLink);
    }

    public void editCredential() {
        javascriptExecutor.executeScript("arguments[0].click();", editCredentialLink);
    }

    public void openAddEditCredentialDialog() {
        javascriptExecutor.executeScript("arguments[0].click();", addNewCredentialButton);

    }

    public String getCredentialUsername(int rowIndex) {
        List<WebElement> noteRows = credentialsTable.findElements(By.xpath("id('credentialTable')/tbody/tr"));
        try {
            List<WebElement> td_collection = noteRows.get(rowIndex).findElements(By.xpath("td"));
            return (String) javascriptExecutor
                    .executeScript(
                            "return arguments[0].innerHTML",
                            td_collection.get(2).findElement(By.id("credentialUsername"))
                    );
        } catch (IndexOutOfBoundsException exception) {
            exception.printStackTrace();
        }

        return "";
    }

    public String getCredentialEncryptedPassword(int rowIndex) {
        List<WebElement> noteRows = credentialsTable.findElements(By.xpath("id('credentialTable')/tbody/tr"));
        try {
            List<WebElement> td_collection = noteRows.get(rowIndex).findElements(By.xpath("td"));
            return (String) javascriptExecutor
                    .executeScript(
                            "return arguments[0].innerHTML",
                            td_collection.get(3).findElement(By.id("credentialPassword"))
                    );
        } catch (IndexOutOfBoundsException exception) {
            exception.printStackTrace();
        }

        return "";
    }

    public String geNoteTitle() {
        List<WebElement> noteRows = notesTable.findElements(By.xpath("id('notesTable')/tbody/tr"));
        try {
            List<WebElement> td_collection = noteRows.get(0).findElements(By.xpath("td"));
            return (String) javascriptExecutor
                    .executeScript(
                            "return arguments[0].innerHTML",
                            td_collection.get(1).findElement(By.id("noteTitle"))
                    );
        } catch (IndexOutOfBoundsException exception) {
            exception.printStackTrace();
        }

        return "";
    }


}
