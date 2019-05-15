package main;

import controll.controllers.Window;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Window window = new Window();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/PrimaryWindow.fxml"));
        primaryStage.setTitle("Workout Planner");
        primaryStage.setResizable(false);
      //  primaryStage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/icon.jpg"))));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setOnCloseRequest(e->{
            e.consume();
            window.confirmBox("EXIT");
        });
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
