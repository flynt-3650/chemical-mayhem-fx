package com.rgbteam.cmf.view;

import com.rgbteam.cmf.Controller;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SecondaryController {
    private Controller controller;
    @FXML
    private TextField atomicMass;

    public SecondaryController(Controller controller) {
        this.controller = controller;
    }

    @FXML
    private void switchToPrimary() throws IOException {
        GUIView.setRoot("primary");
    }

    @FXML
    public void calculateAtomicMass(ActionEvent event) {
        String rawCompound = atomicMass.getText();
        atomicMass.setText(String.valueOf(controller.calculateCompoundsAtomicMass(rawCompound)));
    }
}
