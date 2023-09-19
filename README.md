# WppoolTest
Automated testing of a WordPress plugin using Selenium 

Codebase is in the master branch.

Things to check:
1. Your chrome-driver and chrome browser should be compatible.
2. If you use your default chrome browser for testing and don't have another version of chrome browser, you need remove the line:
`options.setBinary("/home/nasif/Downloads/chrome-linux64/chrome");`
If you have another chrome version for testing like me, you add your path instead of mine.
3. The chrome-driver location for me is `/usr/local/bin/chromedriver`. You should set yours.
4. None should share login credentials. I used .env file to keep the credentials within my local machine. Git doesn't push .env file to github respositories. You should have your .env file. Intructions are given in the .env.example file.

Follow these steps to run:
1. Copy and paste this command from terminal: `git clone https://github.com/2017331080/wppoolTest.git`
2. Open the project with Eclipse.
3. Go to AutomationTesting.java file.
4. Run the code.

To be noted:
My code just toggles the enable/disable buttons. That means, if it was enabled earlier, it will be disabled and vice versa.
