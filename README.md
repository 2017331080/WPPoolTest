# WppoolTest
Automated testing of a WordPress plugin using Selenium 

Codebase is in the master branch.
There is a short (2.5 minutes) demonstration video (mp4) in the main branch.

Project Requirements:
1. Your chrome-driver and chrome browser should be compatible.
2. If you use your default chrome browser for testing and don't have another version of chrome browser, you need remove the line:
`options.setBinary("/home/nasif/Downloads/chrome-linux64/chrome");`
If you have another chrome version for testing like me, you should add your path replacing mine.
3. The chrome-driver location for me is `/usr/local/bin/chromedriver` (OS - Ubuntu). You should set yours.
4. None should share login credentials on a public github repository. I used a `.env` file to keep my credentials within my local machine. Git doesn't push .env file to github respositories. You should create your `.env` file. Instructions are given in the `.env.example` file.

Follow these steps to run the project:
1. Copy and paste this command from terminal: `git clone https://github.com/2017331080/wppoolTest.git`
2. Open the project with Eclipse.
3. Go to `AutomationTesting.java` file.
4. Run the code.

To be noted:
My code is yet to be updated. It just toggles the enable/disable buttons instead of strictly enabling or strictly disabling them. That means, if it was enabled earlier, it will be disabled. Likewise, if it was disabled earlier, it will be enabled. Just what toggling means.

If you have any other issues, add it from the Issues section.
