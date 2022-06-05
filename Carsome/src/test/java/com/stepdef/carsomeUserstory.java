package com.stepdef;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class carsomeUserstory {
	WebDriver driver;

	@Given("^user is on carsome homepage$")
	public void user_is_on_carsome_homepage() throws Throwable {
		System.setProperty("webdriver.chrome.driver", ".//Drivers//chromedriver.exe");
		ChromeOptions options = new ChromeOptions();

		options.addArguments("disable-notifications");
		driver = new ChromeDriver(options);
		driver.get("https://www.carsome.my/");
		driver.manage().window().maximize();
		Thread.sleep(3000);

	}

	@Then("^click on Buy Car and select Perodua from the dropdown menu$")
	public void click_on_Buy_Car_and_select_Perodua_from_the_dropdown_menu() throws Throwable {
		Actions actions = new Actions(driver);
		WebElement buycar = driver.findElement(By.xpath("//span[text()='Buy Car']"));
		actions.moveToElement(buycar).perform();
		driver.findElement(By.xpath("//div[@class='menu-box-brand'][normalize-space()='Perodua']")).click();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

	}

	@And("^Verify Perodua Axia cars available to buy$")
	public void verify_Perodua_Axia_cars_available_to_buy() throws Throwable {
		int ActualCountOfCars = driver
				.findElements(By.xpath("//a[contains(@href,'/buy-car/perodua/') and @class='mod-card__head']")).size();
		int ExpectedCountOfCars = 18;
		Assert.assertEquals(ActualCountOfCars, ExpectedCountOfCars);
	}

	@Given("^Recommended dropdown and change it to Lowest Price$")
	public void recommended_dropdown_and_change_it_to_Lowest_Price() throws Throwable {
		driver.findElement(By.xpath("//div[text()='Recommended']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(text(),'Lowest Price')]")).click();
		Thread.sleep(3000);
	}

	@And("^Verify that the price of the first car is lower than the second car$")
	public void verify_that_the_price_of_the_first_car_is_lower_than_the_second_car() throws Throwable {
		List<WebElement> priceList = driver.findElements(By.xpath(
				"//a[contains(@href,'/buy-car/perodua/') and @class='mod-card__title']/following-sibling::div[3]/div[@class='mod-card__price__total']//strong"));
		String FirstCarPrice = priceList.get(0).getText().replace(",", ""); // here we are comparing only 2 values so
																			// mentioned like this.
		String SecondCarPrice = priceList.get(1).getText().replace(",", "");
		System.out.println(FirstCarPrice);
		System.out.println(SecondCarPrice);
		if (Integer.parseInt(FirstCarPrice) < Integer.parseInt(SecondCarPrice)) {
			System.out.print("First car value is Lower than second car price and Passed!!");
		} else {
			System.out.print("First car value is greter than second car price and is failed!!");
		}
		driver.close();
	}


}
