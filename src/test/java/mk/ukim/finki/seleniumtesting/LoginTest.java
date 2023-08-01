package mk.ukim.finki.seleniumtesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginTest {
    private WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = getDriver();
    }


    @Test
    public void shouldOpen() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
    }

    @Test//T1
    public void canNotLoginWithInvalidPassword() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("gabdimitrievski111@gmail.com", "wrong_password_test");
        String errorMessage = loginPage.getErrorMessage();
        assertEquals(errorMessage, "Usuário ou senha inválido.\n" +
                "Tente novamente ou verifique suas informações!");
    }

    @Test//T2
    public void shouldregisterfirstAccount() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.registrar("thinaregina@gmail.com", "Conta1", "123456", "123456");
        String confirmMessage = loginPage.getconfirmMessage();
        assertNotNull(confirmMessage);
    }

    @Test//T3
    public void shouldregistersecondAccount() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.registrar("regina.becker.cunha@gmail.com", "Conta2", "123456", "123456");
        String confirmMessage = loginPage.getconfirmMessage();
        assertNotNull(confirmMessage);
    }

    @Test//T3
    public void shouldLoginfirstAccount() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("thinaregina@gmail.com", "123456");
        assertTrue(new HomePage(driver).isLoaded());
    }

    @Test//T3
    public void shouldLoginsecondAccount() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("regina.becker.cunha@gmail.com", "123456");
        assertTrue(new HomePage(driver).isLoaded());
    }

    @Test//T4
    public void shouldtransfer() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("thinaregina@gmail.com", "123456");
        String textAccountNumber = loginPage.gettextAccountNumber();
        String textdigit = loginPage.gettextdigit();
        driver.findElement(By.id("btnExit")).click();
        loginPage.login("regina.becker.cunha@gmail.com", "123456");
        loginPage.transferir("textAccountNumber", "textdigit", "100", "Transferência com sucesso");
    }

    @Test//T5
    public void shouldsaldodisponivelfirstAccount() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("thinaregina@gmail.com", "123456");
        String saldodisponivel = loginPage.getsaldoDisponivel();
        assertEquals(saldodisponivel, "R$ 1.100,00");
    }

    @Test//T6
    public void shouldsaldodisponivelsecondAccount2() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("regina.becker.cunha@gmail.com", "123456");
        String saldodisponivel = loginPage.getsaldoDisponivel();
        assertEquals(saldodisponivel, "R$ 900,00");
    }

    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Windows\\chromedriver.exe");
        return new ChromeDriver();
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }
}
