package com.rgbteam.cmf.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class GUIView extends Application {

    private static Scene scene; 

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 1280, 720);
        String css = this.getClass().getResource("/com/rgbteam/cmf/StyleCollectionView.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUIView.class.getResource("/com/rgbteam/cmf/" + fxml + ".fxml"));
        Parent root = fxmlLoader.load();
    
        return root;
    }
    

    public static void main(String[] args) {
        launch();
    }
}
