package t2qs;

import java.util.List;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;

public class t2 {
    
    private static WebDriver driver;
    
    public t2() {
    }
    
    @BeforeClass
    public static void openBrowser(){
    	System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://www.ut.edu/");
    }
    
    @Test
    public void validCredential() throws InterruptedException{
        
        /*
            1- título do site
            Titulo
        */
        assertEquals(driver.getTitle(),"The University of Tampa - A Private, Florida University");
        
        /*
            2- teste se continua "Apply now" o texto do botão
            Botão
        */
        assertEquals(driver.findElement(By.className("apply")).getText(), "APPLY NOW");
        
        /*
            3- se a #utampa continua igual
            Texto
        */
        assertEquals(driver.findElement(By.className("social-tab-text")).getText(),"#UTAMPA");
        
        /*
            4- testa se a logo não é null
            Logo
        */
        WebElement logo = driver.findElement(By.className("logo"));
        assertNotNull(logo);
        
        /*
        	5- testa quantos items de submenu existem em todas abas do menu horizontal
        	Menu
         */
        WebElement menuHM = driver.findElement(By.cssSelector("ul[class='menu horizontal main']"));
        List<WebElement> itemsLi = menuHM.findElements(By.tagName("li"));
        assertEquals(itemsLi.size(), 31);
        /*
          	6- testa se o video do background continua o mesmo
          	Link
         */
        WebElement videoLink = driver.findElement(By.cssSelector("div[class='js-video-bg jquery-background-video-wrapper']"));
        assertEquals(videoLink.getAttribute("data-video-src"),"https://s3.amazonaws.com/utassets/DaytimeSequenceFall2018720vimeo.mp4");
       
        /*
         	7- testa quantos elementos tem no carousel
         	Carousel
         */
        WebElement instaLink = driver.findElement(By.cssSelector("div[class='slick-track']"));
    	List<WebElement> itemsLiAtInstaLink = instaLink.findElements(By.tagName("article"));
        assertEquals(itemsLiAtInstaLink.size(), 3);
        
        /*
         	8- testa o numero de alunos da faculdade
         	Painel
         */
        WebElement students = driver.findElement(By.cssSelector("div[class='statistics-panel-content'] > header > h3"));
        assertEquals(students.getText(),"9,304");
        
        /*
         	MUDA PARA UMA SUBPAGINA
         */
        
        driver.navigate().to("http://www.ut.edu/admissions/");
        
        /*
         	9- testa uma busca de "students"
         	Busca
         */
        WebElement searchButton = driver.findElement(By.cssSelector("li[class='search']"));     
        searchButton.click();
        Thread.sleep(3000);
        WebElement searchBox = driver.findElement(By.cssSelector("input[id='gsc-i-id2']"));
        Thread.sleep(1000);
        searchBox.sendKeys("students");
        Thread.sleep(1000);
        searchBox.submit();
        Thread.sleep(9000);
        String busca= "students";
        assertTrue(driver.getPageSource().toLowerCase().contains(busca));
        
        /*
     	MUDA PARA UMA SUBPAGINA
         */
    
        driver.navigate().to("http://www.ut.edu/tampabayarea/");
        
        /*
        	10- testa o fullscreen do video youtube
        	iFrame
         */
        
        WebElement videoFrame = driver.findElement(By.id("fitvid0"));        
        assertNotNull(videoFrame.getAttribute("allowfullscreen"));
    }
    
    @AfterClass
    public static void closeBrowser(){
        driver.quit();
    }
}