/**
 * Typing Test program
 * Developed by Travis Le
 */

package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.util.Duration;

import java.io.IOException;

public class MainApp extends Application {
	
	//Handles the building of the UI as well as the bulk of the program's functionality
	//JavaFX applications require the start method to be overrided to run
	@Override
	public void start(Stage primaryStage) throws IOException
	{
		//Sets up the stage or the window
		primaryStage.setTitle("Typing Test");
		primaryStage.show();
		
		
		//Sets up the root node panel of the stage
		GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(15);
        grid.setVgap(25);
        grid.setPadding(new Insets(25, 25, 25, 25));
        		
        
		//starts a new typing test session
		TestSession session = new TestSession();
		
		
		//text label that displays "Time remaining (in seconds)"
		Label timeLabel = new Label("Time elapsed\n(in seconds)");
		timeLabel.setFont(Font.font(24));
		GridPane.setConstraints(timeLabel, 4, 0);
		
		
		//text label that displays the amount of time that has elapsed. Updates every second.
		Label timeElapsed = new Label(Integer.toString(session.getTime()));
		timeElapsed.setFont(Font.font(72));
		GridPane.setConstraints(timeElapsed, 4, 1);
		
		
		//converts all the words from a list of strings into a single string to be displayed by the top pane
		//each word is separated by four spaces for improved legibility for the user
		String displayText = String.join("    ", session.getWordList());

		
		//sets up the top, bottom, and right panes
		TextArea topPane = initTopPane(new TextArea(displayText));
		TextArea rightPane = initRightPane(new TextArea("Start typing sample text to begin test. Please separate each word with a single space.\n\n"));
		
		
		//timeline object that controls the timer by changing and updating it once every second. Also prints out the
		//final WPM after the user completes the test and the timeline is stopped. In order to print out the final WPM, 
		//it was necessary for the object to be created between the creation of the right pane and the bottom pane
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler() {
			public void handle(Event event) {
				session.incremTime();
				timeElapsed.setText(Integer.toString(session.getTime()));
				if (session.isComplete()) {
					timeline.stop();
					rightPane.appendText("\n\nYour final WPM is:   " + Math.round(session.calcWPM()*10000.0)/10000.0);
				}
			}
		}));
		
		TextArea botPane = initBotPane(rightPane, session, timeline, new TextArea());
		
		
		//adding the various panes and labels onto the root grid node
		grid.getChildren().add(botPane);
		grid.getChildren().add(topPane);
		grid.getChildren().add(timeLabel);
		grid.getChildren().add(timeElapsed);
		grid.getChildren().add(rightPane);
				
		Scene scene = new Scene(grid, 1080, 720);
		primaryStage.setScene(scene);
	}
	
	//setting up the top pane where the words for the typing test are displayed
	//the textarea is locked from user edits
	public TextArea initTopPane(TextArea top)
	{
        top.setWrapText(true);
        top.setEditable(false);
        top.setFont(Font.font(20));
        top.setPrefSize(880, 360);
        GridPane.setConstraints(top, 0, 0, 3, 3);
        
        return top;
	}
	
	//setting up the right pane that displays the current test session information
	//this textarea is also locked from user edits
	public TextArea initRightPane(TextArea right)
	{
		right.setWrapText(true);
		right.setFont(Font.font(24));
		right.setPrefSize(200, 500);
		right.setEditable(false);
		GridPane.setConstraints(right, 4, 2, 1, 5);
		
		return right;
	}

	//setting up the bottom pane text entry box where the user types
	//also handles updating the right pane with information about the number of words typed during the test session
	public TextArea initBotPane(TextArea right, TestSession session, Timeline timeline, TextArea bot)
	{
        bot.setWrapText(true);
        bot.setPromptText("Begin typing here");
        bot.setFont(Font.font(20));
        bot.setPrefSize(880, 360);
        GridPane.setConstraints(bot, 0, 4, 3, 3);
        
        //Adds an event handler to the text entry box that processes every key the user presses
		bot.addEventHandler(KeyEvent.KEY_PRESSED, e ->
		{
			//Once the user begins typing, the timeline object begins and the timer starts counting up
			if (bot.getText().length() == 0)
			{
				timeline.playFromStart();
			}
			
			//When the user finishes typing the same number of characters in the word list, the timeline ends,
			// the timer stops counting, and the text entry box is locked from further user entry.
			//Note that the user will need to hit an additional key after matching the display text's character
			// count to make their WPMscore appear in the right pane.
			if (bot.getText().length() >= session.getNumCharInList())
			{
				session.setComplete(true);
				bot.setEditable(false);
			}
			
			//Updates the number of words typed with every key press and displays it on the right pane
			if (session.getTime() >= 0 && bot.getText().length() < session.getNumCharInList())	
			{
				right.setText("Number of words typed:   " + session.calcWordsTyped(bot.getText().length()));
			}
		});
        
        return bot;
	}

	public static void main(String[] args)
	{
		//allows for launching the UI by building the project directly from the IDE
		launch(args);
	}

}
