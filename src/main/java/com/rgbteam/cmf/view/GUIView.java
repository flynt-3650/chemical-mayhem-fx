package com.rgbteam.cmf.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.rgbteam.cmf.Controller;

/**
 * JavaFX App
 */
public class GUIView extends Application {

    private static Scene scene;
    private static Controller controller; 

    @Override
    public void start(Stage stage) throws IOException {
        controller = new Controller(); 
        scene = new Scene(loadFXML("primary"), 1280, 720);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUIView.class.getResource("/com/rgbteam/cmf/" + fxml + ".fxml"));
        Parent root;
    
        if (fxml.equals("secondary")) {
            SecondaryController secondaryController = new SecondaryController(controller);
            fxmlLoader.setController(secondaryController);
        }
        root = fxmlLoader.load();
        return root;
    }
    

    public static void main(String[] args) {
        launch();
    }

}
