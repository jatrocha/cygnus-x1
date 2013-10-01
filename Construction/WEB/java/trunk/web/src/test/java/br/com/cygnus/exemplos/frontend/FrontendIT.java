package br.com.cygnus.exemplos.frontend;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FrontendIT {

   private static ChromeDriverService service;
   private WebDriver browser;

   // @BeforeClass
   // public static void createAndStartService() {
   // service = new ChromeDriverService.Builder().usingChromeDriverExecutable(new File("path/to/my/chromedriver")).usingAnyFreePort().build();
   // service.start();
   // }
   //
   // @AfterClass
   // public static void createAndStopService() {
   // service.stop();
   // }

   @Before
   public void setup() {

      this.browser = new FirefoxDriver();
   }

   @Test
   public void startTest() {
      this.browser.get("http://localhost:8081/examples/");

      this.browser.findElement(By.id("id")).click();

      // Will throw exception if elements not found
      this.browser.findElement(By.id("id")).sendKeys("011");

      this.browser.findElement(By.id("titulo")).sendKeys("T�tulo");

      this.browser.findElement(By.id("autor")).sendKeys("Autor");

      this.browser.findElement(By.id("genero")).sendKeys("G�nero");

      this.browser.findElement(By.id("btnSubmit")).click();
      // this.browser.findElement(By.id("account")).click();

      // assertEquals("John", this.browser.findElement(By.id("firstName")).getAttribute("value"));

   }

   @After
   public void tearDown() {
      this.browser.quit();
   }
}
