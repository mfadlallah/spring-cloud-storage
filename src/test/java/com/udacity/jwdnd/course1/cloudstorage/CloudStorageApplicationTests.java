package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

    @LocalServerPort
    private int port;

    private WebDriver driver;
    private String password = "1234";

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    private void signup(String userName, String passWord) {
        driver.get("http://localhost:" + this.port + "/signup");

        // Signup steps
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup("Mohamed", "Fadl Allah", userName, passWord);
    }

    private void login(String userName, String passWord) {
        driver.get("http://localhost:" + this.port + "/login");

        // Login Steps (Success)
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userName, passWord);
    }

    @Test
    public void getLoginPage() {
        driver.get("http://localhost:" + this.port + "/login");
        Assertions.assertEquals("Login", driver.getTitle());
    }

    // Write a test that verifies that an unauthorized user can only access the login and signup pages.
    @Test
    public void testUnauthorizedUser_can_only_access_login_signup_pages() {
        driver.get("http://localhost:" + this.port + "/home");
        Assertions.assertEquals("Login", driver.getTitle());

        driver.get("http://localhost:" + this.port + "/signup");
        Assertions.assertEquals("Sign Up", driver.getTitle());

        driver.get("http://localhost:" + this.port + "/login");
        Assertions.assertEquals("Login", driver.getTitle());
    }

    /*	Write a test that signs up a new user, logs in, verifies that the home page is accessible,
        logs out, and verifies that the home page is no longer accessible. */
    @Test
    public void testSignUp_Login_Logout_And_HomePage_Not_accessible_After_Logout() {
        signup("Ahmed", password);

        login("Ahmed", password);

        Assertions.assertEquals("Home", driver.getTitle());

        // Logout Steps
        HomePage homePage = new HomePage(driver);
        homePage.logoutSubmit();

        // UnAuthorized Open Home
        driver.get("http://localhost:" + this.port + "/home");

        Assertions.assertEquals("Login", driver.getTitle());
    }

    //	Write a test that creates a note, and verifies it is displayed.
    @Test
    public void test_that_creates_note_and_verifies_it_is_displayed() {
        signup("Hussain", password);

        login("Hussain", password);

        HomePage homePage = new HomePage(driver);
        homePage.openNoteTab();
        driver.switchTo().activeElement();

        homePage.openAddEditNoteDialog();
        driver.switchTo().activeElement();

        // Add Note.
        String noteTitle = "New Note";
        NoteDialog noteDialog = new NoteDialog(driver);
        noteDialog.addEditNote(noteTitle, "New Note Description");

        Assertions.assertEquals(noteTitle, homePage.geNoteTitle());
    }

    //	Write a test that edits a note, and changes are displayed.
    @Test
    public void test_that_edits_note_and_verifies_changes_are_displayed() {
        signup("Islam", password);

        login("Islam", password);

        String noteTitle = "New Note";
        HomePage homePage = new HomePage(driver);
        homePage.openNoteTab();
        driver.switchTo().activeElement();

        homePage.openAddEditNoteDialog();
        driver.switchTo().activeElement();

        // Add Note.
        NoteDialog noteDialog = new NoteDialog(driver);
        noteDialog.addEditNote(noteTitle, "New Note Description");

        // Edit Note
        homePage.editNote();
        String editNoteTitleText = "Old Note Edited";
        noteDialog.addEditNote(editNoteTitleText, "New Note Description");

        Assertions.assertEquals(editNoteTitleText, homePage.geNoteTitle());
    }

    //	Write a test that deletes a note, and verifies it is not displayed.
    @Test
    public void test_that_deletes_note_and_verifies_it_is_not_displayed() {
        signup("Hamza", password);

        login("Hamza", password);

        String noteTitle = "New Note Will Be Deleted";
        HomePage homePage = new HomePage(driver);
        homePage.openNoteTab();
        driver.switchTo().activeElement();

        homePage.openAddEditNoteDialog();
        driver.switchTo().activeElement();

        // Add Note.
        NoteDialog noteDialog = new NoteDialog(driver);
        noteDialog.addEditNote(noteTitle, "New Note Description");

        // Delete Note.
        homePage.deleteNote();

        Assertions.assertEquals("", homePage.geNoteTitle());
    }

    /* Write a test that creates a set of credentials,
    verifies that they are displayed, and verifies that the displayed password is encrypted. */
    @Test
    public void test_creates_credentials_verifies_that_displayed_with_password_encrypted() {
        signup("Zakaria", password);

        login("Zakaria", password);

        HomePage homePage = new HomePage(driver);
        homePage.openCredentialsTab();
        driver.switchTo().activeElement();

        homePage.openAddEditCredentialDialog();
        driver.switchTo().activeElement();

        // Add Credentials.
        String passwordOne = "1234";
        String passwordTwo = "1234";
        CredentialDialog credentialDialog = new CredentialDialog(driver);
        credentialDialog.addEditCredential("WWW.Facebook.com", "Heba", passwordOne);
        credentialDialog.addEditCredential("WWW.Google.com", "Fadlool", passwordTwo);

        String encryptedPasswordOne = homePage.getCredentialEncryptedPassword(0);
        String encryptedPasswordTwo = homePage.getCredentialEncryptedPassword(1);

        Assertions.assertNotEquals("", encryptedPasswordOne);
        Assertions.assertNotEquals("", encryptedPasswordTwo);
        Assertions.assertNotEquals(passwordOne, encryptedPasswordOne);
        Assertions.assertNotEquals(passwordTwo, encryptedPasswordTwo);
    }


    /* Write a test that views an existing set of credentials,
     verifies that the viewable password is unencrypted,
      edits the credentials, and verifies that the changes are displayed. */
    @Test
    public void test_view_existing_credentials_verifies_that_viewable_password_unencrypted_and_edit_successfully() {
        signup("Mama", password);

        login("Mama", password);

        HomePage homePage = new HomePage(driver);
        homePage.openCredentialsTab();
        driver.switchTo().activeElement();

        homePage.openAddEditCredentialDialog();
        driver.switchTo().activeElement();

        // Add Credentials.
        String passwordOne = "1234";
        String passwordTwo = "1234";
        CredentialDialog credentialDialog = new CredentialDialog(driver);
        credentialDialog.addEditCredential("WWW.Facebook.com", "Heba", passwordOne);
        credentialDialog.addEditCredential("WWW.Google.com", "Fadlool", passwordTwo);

        // View Credential.
        homePage.editCredential();

        String viwedPasswordOne = credentialDialog.getViewablePassword();
        Assertions.assertEquals(viwedPasswordOne, passwordOne);

        // Edit Credential.
        String newUserName = "Heba11";
        credentialDialog.addEditCredential("WWW.Facebook.com", newUserName, passwordOne);

        String editedUserName = homePage.getCredentialUsername(0);
        Assertions.assertEquals(editedUserName, newUserName);
    }

    /*  Write a test that deletes an existing set of credentials
    and verifies that the credentials are no longer displayed.*/
    @Test
    public void test_delete_existing_credentials_verifies_that_not_displayed() {
        signup("Mariam", password);

        login("Mariam", password);

        HomePage homePage = new HomePage(driver);
        homePage.openCredentialsTab();
        driver.switchTo().activeElement();

        homePage.openAddEditCredentialDialog();
        driver.switchTo().activeElement();

        // Add Credentials.
        String passwordOne = "1234";
        String passwordTwo = "1234";
        CredentialDialog credentialDialog = new CredentialDialog(driver);
        credentialDialog.addEditCredential("WWW.Facebook.com", "Heba", passwordOne);
        credentialDialog.addEditCredential("WWW.Google.com", "Fadlool", passwordTwo);

        Assertions.assertNotEquals("", homePage.getCredentialUsername(0));
        Assertions.assertNotEquals("", homePage.getCredentialUsername(1));

        // delete credential one.
        homePage.deleteCredential();

        Assertions.assertEquals("", homePage.getCredentialUsername(1));

        // delete credential two.
        homePage.deleteCredential();

        Assertions.assertEquals("", homePage.getCredentialUsername(0));
    }
}
