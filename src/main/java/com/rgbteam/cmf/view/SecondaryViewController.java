package com.rgbteam.cmf.view;

import com.rgbteam.cmf.GeneralFlowController;
import com.rgbteam.cmf.chemistry.InvalidCompoundException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SecondaryViewController {
    private final GeneralFlowController controller = new GeneralFlowController();

    @FXML
    private TextField compoundQuery;

    @FXML
    private Label labelCompound;
    
    @FXML
    private Label atomicMass;

    @FXML
    private void switchToPrimary() throws IOException {
        GUIView.setRoot("primary");
    }

    private void calculateAtomicMass() throws InvalidCompoundException {
        double ans = controller.calculateCompoundsAtomicMass(compoundQuery.getText());

        if (ans == 0.0) {
            labelCompound.setText("Error");
            return;
        }
        atomicMass.setText(String.format("%.4f", ans));
    }

    @FXML
    public void showCompoundInformation() throws InvalidCompoundException {
        labelCompound.setText(compoundQuery.getText());
        calculateAtomicMass();
    }
}
