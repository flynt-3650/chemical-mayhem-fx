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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextFlow;
import javafx.event.EventHandler;


public class PrimaryViewController {

    @FXML
    public GridPane myGridPane;
    public TextFlow textFlow;

    private Button[] buttons;


    private GeneralFlowController controller = new GeneralFlowController();

    public void initialize() {

        addListener();
        addButton();

    }

    public void addListener(){
        buttons = new Button[119];
        EventHandler<MouseEvent> elementButtonListener = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Button source = (Button) mouseEvent.getSource();
                String shortName = source.getText();
                var e = controller.retrieveElementByShortName(shortName);

            }
        };
        for (int i = 0; i < 118; i++) {
            Element e = controller.retrieveElementByNumber(i + 1);
            if (e != null) {
                buttons[i] = new Button(e.getShortName());
                buttons[i].setOnMouseClicked(elementButtonListener);
            }
        }
    }

    public void addButton(){

        int i = 0;
        for(int row = 2 ; row <= 11; row ++){
            for(int  column = 1 ; column <= 18; column ++){
                if ((row == 2 && column < 18 && column > 1) || (row == 3 && column < 13 && column > 2) || (row == 4 && column < 13 && column > 2) || (row == 9) || (row == 10 && column < 3) || (row == 11 && column < 3)) {

                } else if ((row == 7 && column == 3)) {
                    i += 15;
                } else if ((row == 8 && column == 3)) {
                    i += 15;
                } else if (row == 10 && column == 3) {
                    i -= 62;
                } else if (row == 11 && column == 3) {
                    i += 17;
                } else {
                    if (i == 118) {
                        break;
                    }
                    buttons[i] = new Button(String.valueOf(i+1));
                    buttons[i].setMaxSize(100,100);
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
