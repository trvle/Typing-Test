README - Typing Test


Function
--------
This is a Java application that acts as a typing test, developed by Travis Le. The test measures how fast you type by having you type a given set of words, calculating and displaying your speed in words per minute (WPM) based on the time it took for you to finish. This program does not currently display mistakes or words the user has spelled incorrectly.


Build information and requirements
----------------------------------
This program requires at least Java SE 8 build 1.8.0_161 or JRE 8u161 with JavaFX 8 and was developed using Eclipse Java EE IDE version Oxygen.2 Release (4.7.2) on Windows 10.

A .jar file containing the project has been created from within Eclipse by right-clicking the "Typing Test" project, clicking "Export...", and exporting as a runnable jar.

Running this jar will require the Java versions as stated above. To run the file, open it with "Java(TM) Platform SE binary." If you do not have that already set as the default program to open .jar files, you may do so by right-clicking the .jar file and selecting "Open with..." and selecting "Java(TM) Platform SE binary".

If you wish to run the program from within Eclipse, there is a known issue with Eclipse where JavaFX causes an Access Restriction error. To build the project from within Eclipse and to resolve this error, it is necessary to install e(fx)clipse. This can be done through Eclipse directly:
	
	1. Under the Help menu, click on "Install New Software"
	2. On the "Work with:" dropdown menu, select on "Oxygen - http://download.eclipse.org/releases/oxygen"
    3. Type in the filter box "e(fx)clipse" and select "e(fx)elipse - IDE"
    4. Click next and follow the prompts until intstallation is complete.



Running the program
-------------------
Double-click the "TypingTest.jar" file, assuming one has "Java(TM) Platform SE binary" as the default program to open .jar files. See the build information and requirements section for more details. Launching the .jar will open up a window containing the UI of the Typing Test program.

If you wish to run from within Eclipse, load the project by performing the following steps:

	1. Under Files, select "Open Projects from File System"
	2. Navigate to where you downloaded the project folder and select the "Typing Test" project folder.
	3. After the project is loaded, build and run the MainApp.java and TestSession.java files in the "application" package.

	Note: Please make sure the "words.txt" text file is in the root of the Typing Test directory, the same directory as the .settings, bin, and src directories so the TestSession class can read from it.

The UI will then appear. The words you'll type will be displayed at the top pane, and in the bottom pane is a text entry box where you will type the words. As soon as you begin typing, the timer on the right will begin counting up, and the right pane will change to display the number of words you have typed so far, in real-time.

When you have finished typing the words, the test will conclude and your WPM will be displayed in the right pane underneath the total number of words you've typed.


Known issues
------------
When the user finishes typing the same number of characters there are in the words, the right pane does not automatically display the final WPM. It requires an additional key press to display the final WPM. For example, the total number of characters in the top pane (length of each individual word + a space between every word) is exactly 672 characters, so the text entry box will require entering 643 characters (though the last character is not displayed) for the final WPM to appear.

There is a type safety warning in the timeline method. I did not resolve this warning but it does not appear to affect the program in an observable way so I left the code as is.


References
---------------
List of words taken and modified from a list of 5000 most commonly used words: https://www.wordfrequency.info/free.asp
Formula for calculating WPM from: https://www.speedtypingonline.com/typing-equations