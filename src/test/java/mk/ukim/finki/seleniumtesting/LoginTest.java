package mk.ukim.finki.seleniumtesting;
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
            loginPage.login("userincorrectpassword@gmail.com", "wrong_password_test");
            String errorMessage = loginPage.getErrorMessage();
            assertEquals(errorMessage, "Usuário ou senha inválido.\n" +
                    "Tente novamente ou verifique suas informações!");
        }
    @Test//T2
    public void shouldtransferbetweenaccounts() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.registrar("firstaccount@gmail.com", "Conta1", "123456", "123456");
        String confirmMessage = loginPage.getconfirmMessage();
        assertNotNull(confirmMessage);
        loginPage.closeRegistrar();
        loginPage.login("firstaccount@gmail.com", "123456");
        String textAccountNumber = loginPage.gettextAccountNumber();
        String textdigit = loginPage.gettextdigit();
        loginPage.closehomepage();
        loginPage.registrar("secondaccount@gmail.com", "Conta2", "123456", "123456");
        loginPage.closeRegistrar();
        loginPage.login("secondaccount@gmail.com", "123456");
        loginPage.transferir(textAccountNumber, textdigit, "100", "Transferência entre contas");
        assertEquals(loginPage.getsaldoDisponivel(), "R$ 900,00");
        loginPage.closehomepage();
        loginPage.login("firstaccount@gmail.com", "123456");
        assertEquals(loginPage.getsaldoDisponivel(), "R$ 1.100,00");
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
