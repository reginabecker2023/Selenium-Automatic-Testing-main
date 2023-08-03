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
        //Botão Registrar
        driver.findElement(By.className("ihdmxA")).click();
        Thread.sleep(5000);
        //Elemento da tela E-mail
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[2]/input")))
                .isEnabled();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[2]/input")))
                .isDisplayed();
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[2]/input"))
                .sendKeys(user);
        Thread.sleep(5000);
        //Elemento da tela Nome
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")))
                .isDisplayed();
        driver.findElement(By.name("name")).sendKeys(person);
        Thread.sleep(5000);
        //Elemento da tela Senha
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[4]/div/input")))
                .isDisplayed();
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[4]/div/input"))
                .sendKeys(pass);
        Thread.sleep(5000);
        //Elemento da tela Confirmação senha
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passwordConfirmation")))
                .isDisplayed();
        driver.findElement(By.name("passwordConfirmation")).sendKeys(passconfirm);
        Thread.sleep(5000);
        //Elemento da tela Criar conta com Saldo ?
        driver.findElement(By.className("dTSgXK")).click();
        Thread.sleep(5000);
        //Botão Cadastrar
        driver.findElement(By.className("CMabB")).click();
        Thread.sleep(5000);
    }

    public void closeRegistrar() throws InterruptedException {
        driver.findElement(By.id("btnCloseModal")).click();
        Thread.sleep(5000);
    }

    public void closehomepage() throws InterruptedException {
        driver.findElement(By.id("btnExit")).click();
        Thread.sleep(5000);
    }

    public void login(String user, String password) throws InterruptedException {
        //Elemento da tela E-mail
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys(user);
        Thread.sleep(5000);
        //Elemento da tela Senha
        driver.findElement(By.name("password")).sendKeys(password);
        Thread.sleep(5000);
        //Botão Acessar
        driver.findElement(By.className("otUnI")).click();
        Thread.sleep(5000);
    }

    public void transferir(String accountNumber, String digit, String transferValue, String description) throws InterruptedException {
        //Botão da tela Transferência
        driver.findElement(By.id("btn-TRANSFERÊNCIA")).click();
        Thread.sleep(5000);
        //Elemento da tela Número da conta
        driver.findElement(By.name("accountNumber")).clear();
        driver.findElement(By.name("accountNumber")).sendKeys(accountNumber);
        Thread.sleep(5000);
        //Elemento da tela Digito da conta
        driver.findElement(By.name("digit")).clear();
        driver.findElement(By.name("digit")).sendKeys(digit);
        Thread.sleep(5000);
        //Elemento da tela Valor da Transferência
        driver.findElement(By.name("transferValue")).clear();
        driver.findElement(By.name("transferValue")).sendKeys(transferValue);
        Thread.sleep(5000);
        //Elemento da tela Descrição
        driver.findElement(By.name("description")).clear();
        driver.findElement(By.name("description")).sendKeys(description);
        Thread.sleep(5000);
        //Botão Transferir agora
        driver.findElement(By.className("CMabB")).click();
        Thread.sleep(5000);
        //Botão Fechar da modal
        driver.findElement(By.id("btnCloseModal")).click();
        Thread.sleep(5000);
        //Botão Voltar do site
        driver.findElement(By.id("btnBack")).click();
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
