package com.rgbteam.cmf.view;

import com.rgbteam.cmf.AppFlowController;
import com.rgbteam.cmf.chemistry.Compound;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SecondaryController {
    private AppFlowController controller = new AppFlowController();

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

    
    
    public void calculateAtomicMass(ActionEvent event) {
        Compound compound = new Compound(compoundQuery.getText());
        System.out.println(compound);
        atomitMass.setText(String.format("%.4f" , controller.calculateCompoundsAtomicMass(compoundQuery.getText())));
        
    }

    @FXML
    public void showCompoundInformation() {
        labelCompound.setText(compoundQuery.getText());
        calculateAtomicMass(null);
    }
}
