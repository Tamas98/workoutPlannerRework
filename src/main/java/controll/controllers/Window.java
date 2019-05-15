package controll.controllers;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Window {

    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Creates a new window with the given title from the given fxml.
     * @param fxmlPath The path of the fxml file
     * @param title The title of the new window
     * @param height The height of the window we want to create
     * @param width The width of the window we want to create
     * @return The created window
     */
    public Stage createWindow(String fxmlPath,String title,int width,int height){
        Stage stage = new Stage();
        Parent root = new Parent() {
        };

        try {
            root = FXMLLoader.load(getClass().getResource(fxmlPath));
        } catch (IOException e) {
            log.error("A problem occured while creating a window");
            e.printStackTrace();
        }

        stage.setTitle(title);
        stage.setScene(new Scene(root, width, height));

        log.info("A new window is created");
        return stage;
    }

    /**
     * Pop box to confirm choices like exit,delete, etc..
     * @param title The title of the window
     */
    public void confirmBox(String title){
        Stage stage = new Stage();
        stage.setTitle(title);

        Label label = new Label("Are you sure?");
        label.setId("label");

        Button yesButton = new Button("Yes");
        yesButton.setId("button");
        yesButton.setOnAction(e-> {
            stage.close();
            Platform.exit();
        });

        Button noButton = new Button("No");
        noButton.setId("button");
        noButton.setOnAction(e -> stage.close());

        HBox buttons = new HBox(yesButton,noButton);
        buttons.setPadding(new Insets(10,10,10,10));
        buttons.setSpacing(20);
        buttons.setAlignment(Pos.CENTER);

        VBox layout = new VBox(label,buttons);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);
        layout.setPadding(new Insets(10,10,10,10));

        Scene scene = new Scene(layout,200,150);
        scene.getStylesheets().add(getClass().getResource("/Styles/BoxStyle.css").toExternalForm());

        stage.setResizable(false);
        stage.setScene(scene);
        stage.showAndWait();

        log.info("Successfully popped a confirm box");
    }

    /**
     * Pops an informational box about errors,problems etc.
     * @param message The message to the user
     * @param title The title of the window
     */
    public void popBox(String message,String title){
        Stage stage = new Stage();
        stage.setTitle(title);

        Label label = new Label(message);
        label.setId("label");

        Button okButton = new Button("OK");
        okButton.setOnAction(e -> stage.close());
        okButton.setId("button");

        VBox layout = new VBox(label,okButton);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);
        layout.setPadding(new Insets(10,10,10,10));

        Scene scene = new Scene(layout,200,150);
        scene.getStylesheets().add(getClass().getResource("/Styles/BoxStyle.css").toExternalForm());

        stage.setResizable(false);
        stage.setScene(scene);
        stage.showAndWait();

        log.info("Successfully popped a box with message: " + message);
    }
}
