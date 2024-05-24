package com.rgbteam.cmf.view;

import com.rgbteam.cmf.GeneralFlowController;
import com.rgbteam.cmf.chemistry.Element;
import com.rgbteam.cmf.chemistry.InvalidCompoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Map;


public class SecondaryViewController {
    private final GeneralFlowController controller = new GeneralFlowController();

    @FXML
    private TextField compoundQuery;

    @FXML
    private Label labelCompound;

    @FXML
    private Label atomicMass;

    @FXML
    private Label oxidationState;
    @FXML
    private Label compoundClass;


    @FXML
    private void switchToPrimary() throws IOException {
        GUIView.setRoot("primary");
    }


    private void showAtomicMass() throws InvalidCompoundException {
        double ans = controller.calculateCompoundsAtomicMass(compoundQuery.getText());

        if (ans == 0.0) {
            labelCompound.setText("Error");
            return;
        }
        atomicMass.setText(String.format("%.4f", ans));
    }


    private void showOxidStates() {
        Map<Element, int[]> elementsToOxidStates = controller.findCompoundsOxidationStates(compoundQuery.getText());

        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<Element, int[]> entry : elementsToOxidStates.entrySet()) {
            Element element = entry.getKey();
            int[] oxidationStates = entry.getValue();

            stringBuilder.append(element.getShortName()).append(": ");
            for (int oxidationState : oxidationStates) {
                if (oxidationState != oxidationStates[oxidationStates.length - 1]) {
                    stringBuilder.append(oxidationState).append(", ");
                } else {
                    stringBuilder.append(oxidationState + " ");
                }
                
            }
        }
        
        oxidationState.setText(stringBuilder.toString());
    }


    private void showClass() {
        String classPair = controller.retrieveClassOfCompound(compoundQuery.getText());
        compoundClass.setText(classPair);
    }


    @FXML
    public void showCompoundInformation() throws InvalidCompoundException {
        labelCompound.setText(compoundQuery.getText());
        showAtomicMass();
        showOxidStates();
        showClass();
    }


    @FXML
    public void changeTheme() {
    }
    @FXML
    public void showInfoAboutCalculations() {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Information about calculations");
        infoAlert.setHeaderText("How to use:");
        Text infoText = new Text("Enter a compound or element, click on the calculate button and get information about your element or connection\n" +
        "Authors: Repilov. I.I., Bondarenko E.A., Oleg");
    

        infoText.setWrappingWidth(300);
        infoText.setFill(Paint.valueOf("BLACK"));

    ScrollPane scrollPane = new ScrollPane(infoText);
    scrollPane.setPrefViewportWidth(300);
    scrollPane.setPrefViewportHeight(50);

    infoAlert.getDialogPane().setContent(scrollPane);

    infoAlert.showAndWait();
    }
}
