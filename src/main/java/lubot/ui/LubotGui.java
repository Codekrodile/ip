package lubot.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import lubot.Lubot;

/**
 * Controller for Lubot GUI.
 * Sets up the UI components and handles user interactions.
 */
public class LubotGui {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Lubot lubot;

    /**
     * Initializes the GUI components.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        lubot = new Lubot(); // init Lubot

        // Display welcome message
        dialogContainer.getChildren().add(DialogBox.getLubotDialog(lubot.getWelcomeMessage()));
    }

    /**
     * Handles user input.
     * Displays user and Lubot messages.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = lubot.getResponse(input);

        // Add user and Lubot msg
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                DialogBox.getLubotDialog(response));

        userInput.clear();

        // Check if user wants to exit
        if (input.equalsIgnoreCase("exit")) {
            Platform.exit();
            return;
        }
    }
}

