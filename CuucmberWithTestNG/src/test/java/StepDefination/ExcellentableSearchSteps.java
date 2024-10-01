package StepDefination;

import java.time.Duration;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jdk.internal.org.jline.utils.Log;

public class ExcellentableSearchSteps {
	WebDriver driver = null;
	@Before
	public void initDriver() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	@After
	public void teardown() {
		driver.quit();
	}
	@Given("User is on Excellentable Page")
	public void user_is_on_excellentable_page() {
		driver.get("https://www.excellentable.com/help/");
	}

	@When("User enters {string} in the search box")
	public void user_enters_in_the_search_box(String string) {
		new WebDriverWait(driver,Duration.ofSeconds(20)).until(new ExpectedCondition<Boolean>() {
	           public Boolean apply(WebDriver d) {
	               return d.findElement(By.xpath("//input[@class='vp-search-input__input']")).isEnabled();
	           }
	       });
		driver.findElement(By.xpath("//input[@class='vp-search-input__input']")).sendKeys(string);
	}

	@Then("User should see search suggestions containing {string}")
	public void user_should_see_search_suggestions_containing(String string) throws InterruptedException {
		Thread.sleep(5000);
		new WebDriverWait(driver,Duration.ofSeconds(20)).until(new ExpectedCondition<Boolean>() {
	           public Boolean apply(WebDriver d) {
	               return d.findElement(By.xpath("//ul[contains(@id,'suggestion')]")).isDisplayed();
	           }
	       });
	    List<WebElement> suggestions = driver.findElements(By.xpath("//ul[contains(@id,'suggestion')]/li/a/span"));
	    for(WebElement element:suggestions) {
	    	System.out.println(element.getText());
	    	Assert.assertTrue(element.getText().contains(string));
	    }
	}

	@When("User clicks on the first suggestion")
	public void user_clicks_on_the_first_suggestion() {
	    driver.findElement(By.xpath("(//ul[contains(@id,'suggestion')]/li/a)[1]")).click();
	}

	@Then("User should navigated to the {string} page")
	public void user_should_navigated_to_the_comments_page(String string) {
		new WebDriverWait(driver,Duration.ofSeconds(20)).until(new ExpectedCondition<Boolean>() {
	           public Boolean apply(WebDriver d) {
	               return d.findElement(By.xpath("//div[@id='article-inner-content']")).isDisplayed();
	           }
	       });
		Assert.assertTrue(driver.getTitle().equalsIgnoreCase(string));
	}
}