package com.rgbteam.cmf.view;

import com.rgbteam.cmf.AppFlowController;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SecondaryController {
    private AppFlowController controller = new AppFlowController();

    @FXML
    private TextField atomicMass;

    // public SecondaryController(Controller controller) {
    //     this.controller = controller;
    // }

    @FXML
    private void switchToPrimary() throws IOException {
        GUIView.setRoot("primary");
    }

    @FXML
    public void calculateAtomicMass(ActionEvent event) {
        atomicMass.setText(String.format("%.4f" , controller.calculateCompoundsAtomicMass(atomicMass.getText())));
    }
}
