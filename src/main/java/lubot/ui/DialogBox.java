package lubot.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Custom chat bubble to display messages from user and Lubot.
 */
public class DialogBox extends HBox {
    private Label text;

    /**
     * Constructs a DialogBox with a message and an image.
     *
     * @param message The message to display.
     */
    private DialogBox(String message) {
        text = new Label(message);
        text.setWrapText(true);
        this.getChildren().add(text);
    }

    /**
     * Creates a dialog box for user.
     *
     * @param message The user's message.
     * @return A formatted DialogBox for the user.
     */
    public static DialogBox getUserDialog(String message) {
        DialogBox db = new DialogBox(message);
        db.setAlignment(Pos.TOP_RIGHT);
        return db;
    }

    /**
     * Creates a dialog box for Lubot's messages.
     *
     * @param message Lubot's response message.
     * @return A formatted DialogBox for Lubot.
     */
    public static DialogBox getLubotDialog(String message) {
        DialogBox db = new DialogBox(message);
        db.setAlignment(Pos.TOP_LEFT);
        return db;
    }
}

