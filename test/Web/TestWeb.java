package Web;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestWeb {
  private static WebDriver driver;
  private static String baseUrl;
  private boolean acceptNextAlert = true;
  private static StringBuffer verificationErrors = new StringBuffer();
    
  
  
  @BeforeClass
  public static void setUp() throws Exception {
    
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/index.jsp";
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
  }

  @Test
  public void testLoginEmailVacio() throws Exception{
    driver.get(baseUrl);      
    driver.findElement(By.id("f_email")).clear();
    driver.findElement(By.id("f_contrasena")).clear();
    driver.findElement(By.name("submit")).click();
    assertEquals("Escribe tu correo", driver.findElement(By.id("errorLogin")).getText());
  }
  
  @Test
  public void testLoginEmailIncorrecto() throws Exception{
    driver.get(baseUrl);
    driver.findElement(By.id("f_email")).clear();
    driver.findElement(By.id("f_email")).sendKeys("test");
    driver.findElement(By.id("f_contrasena")).clear();
    driver.findElement(By.id("f_contrasena")).sendKeys("test");
    driver.findElement(By.name("submit")).click();
    assertEquals("El usuario test no existe", driver.findElement(By.id("errorLogin")).getText());
  }
  
  @Test
  public void testLoginContrasenaVacia() throws Exception{
    driver.get(baseUrl);
    driver.findElement(By.id("f_email")).clear();
    driver.findElement(By.id("f_email")).sendKeys("test@test.com");
    driver.findElement(By.id("f_contrasena")).clear();
    driver.findElement(By.name("submit")).click();
    assertEquals("Escribe tu contraseña", driver.findElement(By.id("errorLogin")).getText());
  }
  
  @Test
  public void testLoginContrasenaIncorrecta() throws Exception{
    driver.get(baseUrl);
    driver.findElement(By.id("f_email")).clear();
    driver.findElement(By.id("f_email")).sendKeys("admin@personal.com");
    driver.findElement(By.id("f_contrasena")).clear();
    driver.findElement(By.id("f_contrasena")).sendKeys("test");
    driver.findElement(By.name("submit")).click();
    assertEquals("La contraseña es incorrecta", driver.findElement(By.id("errorLogin")).getText());
  }
  
  @Test
  public void testLogin() throws Exception {
    driver.get(baseUrl);
    driver.findElement(By.id("f_email")).clear();
    driver.findElement(By.id("f_email")).sendKeys("admin@personal.com");
    driver.findElement(By.id("f_contrasena")).clear();
    driver.findElement(By.id("f_contrasena")).sendKeys("123456");
    driver.findElement(By.name("submit")).click();
    assertEquals("Usuario: admin@personal.com", driver.findElement(By.cssSelector("#loginname > p")).getText());
    driver.findElement(By.name("close")).click();    
  }
  
  @Test
  public void testRegistro() throws Exception {
    driver.get(baseUrl);    
    driver.findElement(By.name("registro")).click();
    driver.findElement(By.id("r_nombre")).clear();
    driver.findElement(By.id("r_nombre")).sendKeys("TestNombre");
    driver.findElement(By.id("r_apellidos")).clear();
    driver.findElement(By.id("r_apellidos")).sendKeys("TestApellidos");
    driver.findElement(By.id("r_email")).clear();
    driver.findElement(By.id("r_email")).sendKeys("test@test.com");
    driver.findElement(By.id("r_contrasena")).clear();
    driver.findElement(By.id("r_contrasena")).sendKeys("testpass");
    driver.findElement(By.id("r_contrasenarep")).clear();
    driver.findElement(By.id("r_contrasenarep")).sendKeys("testpass");
    driver.findElement(By.id("r_telefono")).clear();
    driver.findElement(By.id("r_telefono")).sendKeys("555555555");
    driver.findElement(By.id("r_cv")).clear();
    driver.findElement(By.id("r_cv")).sendKeys("Esto es un currículo de test.");
    driver.findElement(By.xpath("//input[@value='Enviar']")).click();
    assertEquals("Usuario: test@test.com", driver.findElement(By.cssSelector("#loginname > p")).getText());
    driver.findElement(By.name("close")).click();
  }

  @AfterClass
  public static void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alert.getText();
    } finally {
      acceptNextAlert = true;
    }
  }
}