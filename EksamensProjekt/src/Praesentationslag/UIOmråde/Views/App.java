package Praesentationslag.UIOmråde.Views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	static Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception {
    	this.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    
    public static void initOwner(Stage stage) {
    	stage.initOwner(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}