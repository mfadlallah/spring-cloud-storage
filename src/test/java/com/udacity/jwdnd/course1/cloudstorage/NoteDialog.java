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

public class NoteDialog {

    private WebDriverWait wait;

    @FindBy(id = "note-title")
    private WebElement noteTitleInput;

    @FindBy(id = "note-description")
    private WebElement noteDescriptionInput;

    @FindBy(id = "saveChangesButton")
    private WebElement saveChangesButton;

    private WebDriver driver;

    private JavascriptExecutor javascriptExecutor;

    public NoteDialog(WebDriver driver) {
        this.driver = driver;
        this.javascriptExecutor = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 4);
    }

    private void setNoteTitle(String noteTitle) {
        javascriptExecutor.executeScript("arguments[0].value='';", noteTitleInput);
        javascriptExecutor.executeScript("arguments[0].value='"+noteTitle+"';", noteTitleInput);
    }

    private void setNoteDescription(String noteDescription) {
        javascriptExecutor.executeScript("arguments[0].value='';", noteDescriptionInput);
        javascriptExecutor.executeScript("arguments[0].value='"+noteDescription+"';", noteDescriptionInput);
    }

    private void dialogSubmit() {
        javascriptExecutor.executeScript("arguments[0].click();", saveChangesButton);
    }

    public void addEditNote(String noteTitle,
                            String noteDescription) {

        setNoteTitle(noteTitle);
        setNoteDescription(noteDescription);
        dialogSubmit();
    }

}
