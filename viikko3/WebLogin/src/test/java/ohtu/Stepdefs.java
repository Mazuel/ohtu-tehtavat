package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
	// WebDriver driver = new ChromeDriver();
	WebDriver driver = new HtmlUnitDriver();
	String baseUrl = "http://localhost:4567";

	@Given("login is selected")
	public void loginIsSelected() {
		driver.get(baseUrl);
		WebElement element = driver.findElement(By.linkText("login"));
		element.click();
	}
	
	@Given("command new user is selected")
	public void newUserSelected() {
		driver.get(baseUrl);
		WebElement element = driver.findElement(By.linkText("register new user"));
		element.click();
	}
	
	@Given("user with username {string} with password {string} is successfully created")
	public void successfullyCreatedUser(String username, String password) {
		newUserSelected();
		register(username, password, password);
	}
	
	@Given("user with username {string} and password {string} is tried to be created")
	public void unsuccessfullyCreatedUser(String username, String password) {
		newUserSelected();
		register(username, password, password);
	}

	@When("correct username {string} and password {string} are given")
	public void correctUsernameAndPasswordAreGiven(String username, String password) {
		logInWith(username, password);
	}

	@Then("user is logged in")
	public void userIsLoggedIn() {
		pageHasContent("Ohtu Application main page");
	}

	@When("valid username {string} and short password {string} are given")
	public void validUsernameAndShortPassword(String username, String password) {
		register(username, password, password);
	}

	@When("invalid username {string} and password {string} are entered")
	public void registerWithInvalidUsernameAndValidPassword(String username, String password) {
		register(username, password, password);
	}
	
	@When("login invalid username {string} and password {string} are entered")
	public void loginWithInvalidUsernameAndPassword(String username, String password) {
		logInWith(username, password);
	}

	@When("valid username {string} and password {string} and unmatching password confirmation {string} are given")
	public void validUsernameWithUnmatchinPasswords(String username, String password, String passwordConfirmation) {
		register(username, password, passwordConfirmation);
	}

	@When("correct username {string} and incorrect password {string} are given")
	public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
		logInWith(username, password);
	}

	@When("incorrect username {string} and password {string} are given")
	public void incorrectUsernameAndPasswordAreGiven(String username, String password) {
		logInWith(username, password);
	}

	@When("a valid username {string} and password {string} and matching password confirmation are entered")
	public void validUsernameAndMatchingPasswords(String username, String password) {
		register(username, password, password);
	}

	@Then("user is not logged in and error message is given")
	public void userIsNotLoggedInAndErrorMessageIsGiven() {
		pageHasContent("invalid username or password");
		pageHasContent("Give your credentials to login");
	}

	@Then("system will respond {string}")
	public void systemWillRespond(String pageContent) throws Throwable {
		assertTrue(driver.getPageSource().contains(pageContent));
	}

	@Then("user is not created and error {string} is reported")
	public void userNotCreatedAndErrorReported(String error) {
		pageHasContent(error);
	}

	@Then("a new user is created")
	public void newUserCreated() {
		pageHasContent("Welcome to Ohtu Application!");
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	/* helper methods */

	private void pageHasContent(String content) {
		assertTrue(driver.getPageSource().contains(content));
	}

	private void logInWith(String username, String password) {
		assertTrue(driver.getPageSource().contains("Give your credentials to login"));
		WebElement element = driver.findElement(By.name("username"));
		element.sendKeys(username);
		element = driver.findElement(By.name("password"));
		element.sendKeys(password);
		element = driver.findElement(By.name("login"));
		element.submit();
	}

	private void register(String username, String password, String confirmationPassword) {
		WebElement element = driver.findElement(By.name("username"));
		element.sendKeys(username);
		element = driver.findElement(By.name("password"));
		element.sendKeys(password);
		element = driver.findElement(By.name("passwordConfirmation"));
		element.sendKeys(confirmationPassword);
		element = driver.findElement(By.name("signup"));
		element.submit();
	}
	
}
