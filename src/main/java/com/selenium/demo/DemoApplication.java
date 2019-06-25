package com.selenium.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@SpringBootApplication
@RestController
public class DemoApplication {


    @Autowired
    private ResourceLoader resourceLoader;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @PostConstruct
    private void chargeDrivers() throws IOException {
        System.setProperty("webdriver.gecko.driver", resourceLoader.getResource("classpath:firefox/geckodriver.exe").getURI().getPath());
        System.setProperty("webdriver.chrome.driver", resourceLoader.getResource("classpath:chrome/chromedriver.exe").getURI().getPath());
    }

    @GetMapping("firefox")
    private void firefox() {
        WebDriver firefoxDriver = new FirefoxDriver();
        firefoxDriver.get("https://www.google.com/");
        firefoxDriver.findElement(By.className("gLFyf")).sendKeys("Hola Mundo desde Firefox");
    }

    @GetMapping("chrome")
    private void chrome() {
        WebDriver chromeDriver = new ChromeDriver();
        chromeDriver.get("https://www.google.com/");
        chromeDriver.findElement(By.className("gLFyf")).sendKeys("Hola Mundo Desde chrome");
    }

}
