import java.time.Duration;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutomationTesting {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized"); // Maximize the browser window
		options.setBinary("/home/nasif/Downloads/chrome-linux64/chrome");
		WebDriver driver = new ChromeDriver(options);

		try {
			// 1. Log in to WordPress site
			driver.get("https://ukmededucation.com/wp-admin");
			Thread.sleep(2000);
			WebElement usernameInput = driver.findElement(By.id("user_login"));
			WebElement passwordInput = driver.findElement(By.id("user_pass"));
			WebElement loginButton = driver.findElement(By.id("wp-submit"));
			Dotenv dotenv = Dotenv.load();
			usernameInput.sendKeys(dotenv.get("USERNAMEI"));
			passwordInput.sendKeys(dotenv.get("PASSWORD"));
			loginButton.click();
			Thread.sleep(2000);
			try {
				// 2. Check whether the “WP Dark Mode” Plugin is Active or not.
				WebElement wpMenu = driver.findElement(By.xpath("//div[@class='wp-menu-name' and text()='WP Dark Mode']"));
				
				// 3. If Active, navigate to the WP Dark Mode & continue. Otherwise, Install the Plugin and Activate it.
				wpMenu.click();
			} catch (NoSuchElementException err) {
				System.out.println("The plugin was not found. Installing it !!");
				
				driver.get("https://ukmededucation.com/wp-admin/plugin-install.php");
				
				WebElement pluginSearchInput = driver.findElement(By.id("search-plugins"));
				pluginSearchInput.sendKeys("WP Dark Mode");
				
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				
				WebElement installDarkModePlugin = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[data-name='WP Dark Mode – Best Dark Mode Plugin for WordPress with Social Sharing 4.2.2']")));
				installDarkModePlugin.click();
				
				WebElement activateDarkModePlugin = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[aria-label='Activate WP Dark Mode &#8211; Best Dark Mode Plugin for WordPress with Social Sharing']")));
				activateDarkModePlugin.click();
				Thread.sleep(2000);
			}
			// 4. Enable Backend Darkmode from Settings -> General Settings.
			WebElement backendDarkMode = driver.findElement(By.cssSelector("label[for='wppool-wp_dark_mode_general[enable_backend]']"));
			backendDarkMode.click();
			WebElement save = driver.findElement(By.id("save_settings"));
			save.click();
			
			// 5. Validate whether the Darkmode is working or not on the Admin Dashboard.
			Thread.sleep(3000);
			
			// 6. Navigate to the WP Dark Mode.
			WebElement wpDMplugin = driver.findElement(By.xpath("//div[@class='wp-menu-name' and text()='WP Dark Mode']"));
			wpDMplugin.click();
			Thread.sleep(3000);
			
			// 7. From Settings -> Switch Settings - Change the “Floating Switch Style” from the default selections (Selecting the third one).
			WebElement switchSettings = driver.findElement(By.xpath("//span[text()='Switch Settings']"));
			switchSettings.click();
			Thread.sleep(3000);
			
			WebElement floatingSwitchStyle = driver.findElement(By.cssSelector("img.image-choose-img[src='https://ukmededucation.com/wp-content/plugins/wp-dark-mode/assets/images/button-presets/3.png']"));
			floatingSwitchStyle.click();
			Thread.sleep(3000);
			
			// 8. From Settings -> Switch Settings - Select Custom Switch size & Scale it to 220.
			WebElement customSwitch = driver.findElement(By.xpath("//span[text()='Custom']"));
			customSwitch.click();
			Thread.sleep(3000);
			WebElement slider = driver.findElement(By.xpath("//*[@id=\"wp_dark_mode_switch\"]/form/table/tbody/tr[4]/td/div/div[1]"));
			Actions sliderAction = new Actions(driver);
			sliderAction.clickAndHold(slider).moveByOffset(150, 0).release().perform();
			Thread.sleep(3000);
			
			// 9. From Settings -> Switch Settings - Change the Floating Switch Position (Left Bottom).
			WebElement floatingSwitchPosition = driver.findElement(By.id("wp_dark_mode_switch[switcher_position]"));
			Select select = new Select(floatingSwitchPosition);
			select.selectByVisibleText("Left Bottom");
			WebElement saveButton = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/div[4]/div[2]/div[4]/form/div/p/input"));
			saveButton.click();
			Thread.sleep(2000);
			
			// 10. Disable Keyboard Shortcut from the Accessibility Settings.
			WebElement accessibilitySettings = driver.findElement(By.id("wp_dark_mode_accessibility-tab"));
			accessibilitySettings.click();
			Thread.sleep(2000);
			WebElement keyboardShortcut = driver.findElement(By.cssSelector("label[for='wppool-wp_dark_mode_accessibility[keyboard_shortcut]']"));
			keyboardShortcut.click();
			Thread.sleep(2000);
			saveButton = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/div[4]/div[2]/div[8]/form/div/p/input"));
			saveButton.click();
			Thread.sleep(2000);
			
			// 11. From Settings -> Animation - Enable “Darkmode Toggle Animation” & change the “Animation Effect” from the default selections (I have selected Flip).
			WebElement animationSettings = driver.findElement(By.xpath("//span[text()='Animation']"));
			animationSettings.click();
			Thread.sleep(2000);
			WebElement toggleAnimation = driver.findElement(By.cssSelector("label[for='wppool-wp_dark_mode_animation[toggle_animation]']"));
			toggleAnimation.click();
			WebElement animationEffect = driver.findElement(By.id("wp_dark_mode_animation[animation]"));
			select = new Select(animationEffect);
			select.selectByVisibleText("Flip");
			Thread.sleep(2000);
			saveButton = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/div[4]/div[2]/div[13]/form/div/p/input"));
			saveButton.click();
			Thread.sleep(2000);
			
			// 12.  Validate whether the Darkmode is working or not from the Frontend.
			driver.get("https://ukmededucation.com");
			Thread.sleep(4000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}
}
