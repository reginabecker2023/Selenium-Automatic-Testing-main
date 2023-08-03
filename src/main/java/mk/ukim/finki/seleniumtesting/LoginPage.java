package mk.ukim.finki.seleniumtesting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.Arrays;
public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public void open() {
        driver.get("https://bugbank.netlify.app/");
    }
    public boolean isLoaded() throws InterruptedException {
        Thread.sleep(5000);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")))
                .isDisplayed();
    }

    public void registrar(String user, String person, String pass, String passconfirm) throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[1]/form/div[3]/button[2]"))
                .click();
        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[2]/input")))
                .isEnabled();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[2]/input")))
                .isDisplayed();
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[2]/input"))
                .sendKeys(user);
        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")))
                .isDisplayed();
        driver.findElement(By.name("name")).sendKeys(person);
        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[4]/div/input")))
                .isDisplayed();
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[4]/div/input"))
                .sendKeys(pass);
        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passwordConfirmation")))
                .isDisplayed();
        driver.findElement(By.name("passwordConfirmation")).sendKeys(passconfirm);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"toggleAddBalance\"]"))
                .click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/button"))
                .click();
        Thread.sleep(5000);
    }

    public void closeRegistrar() throws InterruptedException {
        driver.findElement(By.id("btnCloseModal"))
                .click();
        Thread.sleep(5000);
    }

    public void closehomepage() throws InterruptedException {
        driver.findElement(By.id("btnExit"))
                .click();
        Thread.sleep(5000);
    }

    public void login(String user, String password) throws InterruptedException {
        driver.findElement(By.name("email"))
                .clear();
        driver.findElement(By.name("email"))
                .sendKeys(user);
        Thread.sleep(5000);
        driver.findElement(By.name("password"))
                .sendKeys(password);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[1]/form/div[3]/button[1]"))
                .click();
        Thread.sleep(5000);
    }

    public void transferir(String accountNumber, String digit, String transferValue, String description) throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"btn-TRANSFERÊNCIA\"]/span/img"))
                .click();
        Thread.sleep(5000);
        driver.findElement(By.name("accountNumber"))
                .clear();
        driver.findElement(By.name("accountNumber"))
                .sendKeys(accountNumber);
        Thread.sleep(5000);
        driver.findElement(By.name("digit"))
                .clear();
        driver.findElement(By.name("digit"))
                .sendKeys(digit);
        Thread.sleep(5000);
        driver.findElement(By.name("transferValue"))
                .clear();
        driver.findElement(By.name("transferValue"))
                .sendKeys(transferValue);
        Thread.sleep(5000);
        driver.findElement(By.name("description"))
                .clear();
        driver.findElement(By.name("description"))
                .sendKeys(description);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/form/button"))
                .click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"btnCloseModal\"]"))
                .click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"btnBack\"]"))
                .click();
        Thread.sleep(5000);
    }

    public String getsaldoDisponivel() {
        WebElement saldoDisponivel = driver.findElement(By.xpath("//*[@id=\"textBalance\"]/span"));
        return saldoDisponivel.getText();
    }
    public String gettextAccountNumber() {
        String accountNumber = driver.findElements(By.xpath("//*[@id=\"textAccountNumber\"]/span"))
                .get(0).getText();
        String[] result = accountNumber.split("[-.;,:!?]");
        String conta =  Arrays.stream(result).findFirst().get();
        return conta;
    }
    public String gettextdigit() {
        int tamanhoaccountNumber = (driver.findElements(By.xpath("//*[@id=\"textAccountNumber\"]/span"))
                .get(0).getText().length());
        String digit = driver.findElements(By.xpath("//*[@id=\"textAccountNumber\"]/span"))
                .get(0).getText().substring(tamanhoaccountNumber-1);
        return digit;
    }
    public String getconfirmMessage() {
        WebElement confirmPage = driver.findElement(By.id("modalText"));
        return confirmPage.getText();
    }
    public String getErrorMessage() {
        WebElement errorPage = driver.findElement(By.id("modalText"));
        return errorPage.getText();
    }
}
