import java.util.ArrayList;
import java.util.HashMap;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.TextAlignment;

public class BaccaratGame extends Application {

	ArrayList<Card> playerHand = new ArrayList<Card>();
	ArrayList<Card> bankerHand = new ArrayList<Card>();
	BaccaratDealer theDealer = new BaccaratDealer();
	BaccaratGameLogic gameLogic = new BaccaratGameLogic();
	String betChoice = "";
	double currentBet = 0;
	double totalWinnings = 0;

	public double evaluateWinnings()
	{
		String winner = gameLogic.whoWon(playerHand, bankerHand);
		double winnings = 0;

		if (betChoice == winner)
		{
			if (betChoice == "Player")
			{
				winnings = winnings + currentBet;
			}
			else if (betChoice == "Draw")
			{
				winnings = winnings + (currentBet * 8);
			}
			else if (betChoice == "Banker")
			{
				winnings = winnings + (currentBet * 0.95);
			}
		}
		else if (betChoice != winner)
		{
			winnings = winnings - currentBet;
		}

		return winnings;
	}
	public static void main(String[] args) 
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		primaryStage.setTitle("Baccarat");

	    BorderPane root = new BorderPane();
		BorderPane root2 = new BorderPane();
		BorderPane root3 = new BorderPane();

		Scene startScene = new Scene(root, 700,700);
		Scene wagerScene = new Scene(root2, 700,700);
		Scene playScene = new Scene(root3, 700,700);

		Menu options = new Menu("Options");
		MenuBar startBar = new MenuBar(options);
		MenuItem freshStart = new MenuItem("Fresh Start");
		MenuItem exit = new MenuItem("Exit");
		options.getItems().add(freshStart);
		options.getItems().add(exit);

		exit.setOnAction(e -> Platform.exit());

		Button play = new Button("Play");
		play.setPrefSize(125, 50);
		play.setFont(new Font(20));
		Text title = new Text("Baccarat");
		title.setFont(new Font(100));
		VBox startSceneElements = new VBox(150, title, play);

		primaryStage.setScene(startScene);

		root.setTop(startBar);
		root.setCenter(startSceneElements);
		BorderPane.setMargin(startSceneElements, new Insets(50, 50, 50, 150));
		VBox.setMargin(play, new Insets(0, 0, 0, 120));
		root.setStyle("-fx-background-color: forestgreen");

		play.setOnAction(e -> primaryStage.setScene(wagerScene));

		MenuBar wagerBar = new MenuBar(options);
		root2.setTop(wagerBar);
		root2.setStyle("-fx-background-color: forestgreen");

		Button playerBet = new Button("Player");
		Button drawBet = new Button("Draw");
		Button bankerBet = new Button("Banker");
		HBox wagerButtons = new HBox(50, playerBet, drawBet, bankerBet);
		playerBet.setPrefSize(125, 50);
		playerBet.setFont(new Font(20));
		drawBet.setPrefSize(125, 50);
		drawBet.setFont(new Font(20));
		bankerBet.setPrefSize(125, 50);
		bankerBet.setFont(new Font(20));
		playerBet.setDisable(true);
		drawBet.setDisable(true);
		bankerBet.setDisable(true);

		Text wagerTitleText = new Text("Wager");
		wagerTitleText.setFont(new Font(80));
		Text wagerPromptText = new Text("Please enter your wager in dollars ($), press ENTER, click on the button that reflects what you are betting on, and then click Play.");
		wagerPromptText.setFont(new Font(20));
		wagerPromptText.setWrappingWidth(600);
		wagerPromptText.setTextAlignment(TextAlignment.CENTER);
		TextField wagerTextField = new TextField();
		wagerTextField.setMaxSize(600, 30);
		Button wagerPlayButton = new Button("Play");
		wagerPlayButton.setDisable(true);
		wagerPlayButton.setPrefSize(150, 60);
		wagerPlayButton.setFont(new Font(25));

		VBox wagerSceneElements = new VBox(50, wagerTitleText, wagerPromptText, wagerTextField, wagerButtons, wagerPlayButton);
		root2.setCenter(wagerSceneElements);
		VBox.setMargin(wagerTitleText, new Insets(50, 0, 0, 220));
		VBox.setMargin(wagerPromptText, new Insets(0, 0, 0, 50));
		VBox.setMargin(wagerTextField, new Insets(0, 0, 0, 50));
		VBox.setMargin(wagerButtons, new Insets(0, 0, 0, 105));
		VBox.setMargin(wagerPlayButton, new Insets(0, 0, 0, 267));

		wagerTextField.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e)
			{
				double wagerAmount = Double.parseDouble(wagerTextField.getText());
				currentBet = wagerAmount;
				wagerTextField.setDisable(true);
				wagerTextField.clear();
				playerBet.setDisable(false);
				drawBet.setDisable(false);
				bankerBet.setDisable(false);
			}
		});

		playerBet.setOnAction(e -> 
		{
			betChoice = "Player";
			playerBet.setDisable(true);
			drawBet.setDisable(true);
			bankerBet.setDisable(true);
			wagerPlayButton.setDisable(false);
		});

		drawBet.setOnAction(e -> 
		{
			betChoice = "Draw";
			playerBet.setDisable(true);
			drawBet.setDisable(true);
			bankerBet.setDisable(true);
			wagerPlayButton.setDisable(false);
		});

		bankerBet.setOnAction(e -> 
		{
			betChoice = "Banker";
			playerBet.setDisable(true);
			drawBet.setDisable(true);
			bankerBet.setDisable(true);
			wagerPlayButton.setDisable(false);
		});

		betChoice = "Player";

		freshStart.setOnAction(e -> 
		{
			primaryStage.setScene(startScene);
			wagerTextField.setDisable(false);
			wagerTextField.clear();
			playerBet.setDisable(true);
			drawBet.setDisable(true);
			bankerBet.setDisable(true);
			wagerPlayButton.setDisable(true);
			currentBet = 0;
			totalWinnings = 0;
			playerHand.clear();
			bankerHand.clear();
			theDealer.shuffleDeck();
		});

		wagerPlayButton.setOnAction(e -> 
		{
			primaryStage.setScene(playScene);
			wagerPlayButton.setDisable(true);

			theDealer.shuffleDeck();
			playerHand = theDealer.dealHand();
			bankerHand = theDealer.dealHand();
		});

		MenuBar playBar = new MenuBar(options);
		root3.setTop(playBar);
		root3.setStyle("-fx-background-color: forestgreen");

		theDealer.shuffleDeck();
		playerHand = theDealer.dealHand();
		bankerHand = theDealer.dealHand();

		if (gameLogic.evaluatePlayerDraw(playerHand))
		{
			playerHand.add(theDealer.drawOne());
		}

		Image c1 = new Image(bankerHand.get(0).fileName);
		ImageView card1 = new ImageView(c1);
		card1.setFitWidth(100);
		card1.setFitHeight(145);
		card1.setPreserveRatio(true);

		Image c2 = new Image(bankerHand.get(1).fileName);
		ImageView card2 = new ImageView(c2);
		card2.setFitWidth(100);
		card2.setFitHeight(145);
		card2.setPreserveRatio(true);

		Image c3 = null;
		ImageView card3 = new ImageView(c3);

		Image c4 = new Image(playerHand.get(0).fileName);
		ImageView card4 = new ImageView(c4);
		card4.setFitWidth(100);
		card4.setFitHeight(145);
		card4.setPreserveRatio(true);

		Image c5 = new Image(playerHand.get(1).fileName);
		ImageView card5 = new ImageView(c5);
		card5.setFitWidth(100);
		card5.setFitHeight(145);
		card5.setPreserveRatio(true);

		Image c6 = null;
		ImageView card6 = new ImageView(c6);

		Button playButton = new Button("Play Again");
		playButton.setDisable(true);
		playButton.setPrefSize(125, 50);
		playButton.setFont(new Font(20));

		Text currentWinningsText = new Text("Current Winnings: $" + totalWinnings);
		currentWinningsText.setFont(new Font(20));
		currentWinningsText.setWrappingWidth(100);

		if (playerHand.size() == 2)
		{
			if (gameLogic.evaluateBankerDraw(bankerHand, null))
			{
				bankerHand.add(theDealer.drawOne());
				c3 = new Image(bankerHand.get(2).fileName);
				card3 = new ImageView(c3);
				card3.setFitWidth(100);
				card3.setFitHeight(145);
				card3.setPreserveRatio(true);
			}
		}
		else if (playerHand.size() == 3)
		{
			c6 = new Image(playerHand.get(2).fileName);
			card6 = new ImageView(c6);
			card6.setFitWidth(100);
			card6.setFitHeight(145);
			card6.setPreserveRatio(true);

			if (gameLogic.evaluateBankerDraw(bankerHand, playerHand.get(2)))
			{
				bankerHand.add(theDealer.drawOne());
				c3 = new Image(bankerHand.get(2).fileName);
				card3 = new ImageView(c3);
				card3.setFitWidth(100);
				card3.setFitHeight(145);
				card3.setPreserveRatio(true);
			}
		}

		boolean win = false;

		if (betChoice == gameLogic.whoWon(playerHand, bankerHand))
		{
			win = true;
		}

		Text gameResults = new Text();

		if (win)
		{
			gameResults = new Text("Player Total: " + gameLogic.handTotal(playerHand) + " Banker Total: " + gameLogic.handTotal(bankerHand) + "\n" + 
									gameLogic.whoWon(playerHand, bankerHand) + " wins.\nCongrats, you bet " + betChoice + "! You win!");
			playButton.setDisable(false);
		}
		else
		{
			gameResults = new Text("Player Total: " + gameLogic.handTotal(playerHand) + " Banker Total: " + gameLogic.handTotal(bankerHand) + "\n" + 
									gameLogic.whoWon(playerHand, bankerHand) + " wins.\nSorry, you bet " + betChoice + "! You lost your bet!");
			playButton.setDisable(false);
		}

		playButton.setOnAction(e -> 
		{
			primaryStage.setScene(wagerScene);
			wagerTextField.setDisable(false);
			totalWinnings = totalWinnings + evaluateWinnings();
			playerHand.clear();
			bankerHand.clear();
			theDealer.shuffleDeck();
		});
		
		gameResults.setFont(new Font(20));
		HBox.setMargin(gameResults, new Insets(0, 0, 0, 170));

		Text bankerValue = new Text("Banker Total: " + gameLogic.handTotal(bankerHand));
		bankerValue.setFont(new Font(20));
		bankerValue.setTextAlignment(TextAlignment.CENTER);

		Text playerValue = new Text("Player Total: " + gameLogic.handTotal(playerHand));
		playerValue.setFont(new Font(20));
		playerValue.setTextAlignment(TextAlignment.CENTER);

		VBox.setMargin(currentWinningsText, new Insets(0, 0, 0, 610));
		HBox bankerCards = new HBox(10, card1, card2, card3);
		VBox.setMargin(bankerCards, new Insets(0, 0, 0, 185));
		VBox.setMargin(bankerValue, new Insets(0, 0, 0, 280));
		HBox.setMargin(playButton, new Insets(0, 0, 0, 35));
		HBox centerElements = new HBox(10, gameResults, playButton);
		VBox.setMargin(playerValue, new Insets(0, 0, 0, 280));

		HBox playerCards = new HBox(10, card4, card5, card6);
		VBox.setMargin(playerCards, new Insets(0, 0, 0, 185));
		VBox playSceneElements = new VBox(20, currentWinningsText, bankerCards, bankerValue, centerElements, playerValue, playerCards);
		root3.setCenter(playSceneElements);

		primaryStage.show();
	}

}