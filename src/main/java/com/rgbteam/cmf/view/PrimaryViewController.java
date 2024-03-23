package com.rgbteam.cmf.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import com.rgbteam.cmf.GeneralFlowController;

import com.rgbteam.cmf.chemistry.Element;
import javafx.css.CssParser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import javax.swing.*;

public class PrimaryViewController {

    @FXML
    public GridPane myGridPane;

    private Button[] buttons;


    private GeneralFlowController controller = new GeneralFlowController();

    public void initialize() {

        addButton();

    }

    public void addButton(){
        buttons = new Button[119];
        int i = 0;
        for(int row = 1 ; row <= 10; row ++){
            for(int  column = 1 ; column <= 18; column ++){
                if ((row == 1 && column < 18 && column > 1) || (row == 2 && column < 13 && column > 2) || (row == 3 && column < 13 && column > 2) || (row == 8) || (row == 9 && column < 3) || (row == 10 && column < 3)) {

                } else if ((row == 6 && column == 3)) {
                    i += 15;
                } else if ((row == 7 && column == 3)) {
                    i += 15;
                } else if (row == 9 && column == 3) {
                    i -= 62;
                } else if (row == 10 && column == 3) {
                    i += 17;
                } else {
                    if (i == 118) {
                        break;
                    }
                    buttons[i] = new Button(String.valueOf(i+1));
                    buttons[i].setPrefSize(70,70);
                    myGridPane.add(buttons[i], column, row);
                    i++;
                }
                if (i == buttons.length) break;
            }
        }
    }

    @FXML
    private void switchToSecondView() throws IOException {
        GUIView.setRoot("secondary");
    }
}
