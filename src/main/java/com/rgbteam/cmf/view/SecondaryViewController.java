package com.rgbteam.cmf.view;

import com.rgbteam.cmf.GeneralFlowController;
import com.rgbteam.cmf.chemistry.Compound;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SecondaryViewController {
    private GeneralFlowController controller = new GeneralFlowController();

    @FXML
    private TextField compoundQuery;

    @FXML
    private Label labelCompound;
    
    @FXML
    private Label atomitMass;

    @FXML
    private void switchToPrimary() throws IOException {
        GUIView.setRoot("primary");
    }
    
    private void calculateAtomicMass(ActionEvent event) {
        Compound compound = new Compound(compoundQuery.getText());
        System.out.println(compound);
        double ans = controller.calculateCompoundsAtomicMass(compoundQuery.getText());

        if (ans == 0.0) {
            labelCompound.setText("Error");
            return;
        }
        atomitMass.setText(String.format("%.4f", ans));
    }

    @FXML
    public void showCompoundInformation() {
        labelCompound.setText(compoundQuery.getText());
        calculateAtomicMass(null);
    }
}
