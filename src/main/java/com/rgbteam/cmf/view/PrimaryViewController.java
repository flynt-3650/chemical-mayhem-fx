package com.rgbteam.cmf.view;

import java.awt.*;
import java.io.IOException;

import com.rgbteam.cmf.GeneralFlowController;

import com.rgbteam.cmf.chemistry.Element;
import javafx.css.CssParser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.scene.text.TextFlow;

public class PrimaryViewController {

    public TextFlow textFlow;
    public Text shortN;
    public Text elAtom;
    public Text elNum;
    public Text fullN;
    @FXML
    private GridPane myGridPane;

    private Button[] buttons;

    private GeneralFlowController controller = new GeneralFlowController();

    public void initialize() {

        addListener();
        addButton();

    }

    public void addListener(){
        buttons = new Button[119];

        EventHandler<MouseEvent> elementButtonListener = mouseEvent -> {
            Button source = (Button) mouseEvent.getSource();
            String shortName = source.getText();
            var e = controller.retrieveElementByShortName(shortName);
            shortN.setText(e.getShortName() + " ");
            elAtom.setText(e.getAtomicMass() + " ");
            elNum.setText(e.getNumber() + " ");
            fullN.setText(e.getFullName()  + " ");
        };
        for (int i = 0; i < 118; i++) {
            Element e = controller.retrieveElementByNumber(i + 1);
            if (e != null) {
                buttons[i] = new Button(e.getShortName());
                buttons[i].setMaxSize(1000,1000);
                //setColor(buttons[i],i);
                buttons[i].setOnMouseClicked(elementButtonListener);
            }
        }
    }

    public void addButton(){
        int i = 0;
        for(int row = 2 ; row <= 11; row ++){
            for(int  column = 1 ; column <= 18; column ++){
                if (    (row == 2 && column < 18 && column > 1) || (row == 3 && column < 13 && column > 2) || (row == 4 && column < 13 && column > 2) || (row == 9) || (row == 10 && column < 3) || (row == 11 && column < 3)) {
                    // да-да пустое условие
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
                    myGridPane.add(buttons[i], column, row);
                    i++;
                }
                if (i == buttons.length) break;
            }
        }
    }
    // эту хуйню надо доработать
    /*

    public void setColor(Button button, int i) {
        String group = controller.retrieveElementGroup(i + 1);
        Color fxColor = getColorForGroup(group);
        button.setBackground(new Background(new BackgroundFill(fxColor, null, null)));
    }

    private Color getColorForGroup(String group) {
        switch (group) {
            case "Nonmetals":
                return Color.rgb(0, 128, 0); // зеленый
            case "Noble gases":
                return Color.rgb(0, 255, 255); // голубой
            case "Alkali metals":
                return Color.rgb(255, 0, 0); // красный
            case "Alkaline earth metals":
                return Color.rgb(255, 165, 0); // оранжевый
            case "Metalloids":
                return Color.rgb(128, 128, 128); // серый
            case "Halogen":
                return Color.rgb(255, 255, 0); // желтый
            case "Post-transition metals":
                return Color.rgb(0, 0, 255); // синий
            case "Transition metals":
                return Color.rgb(255, 255, 255); // белый
            case "Lanthanoids":
                return Color.rgb(255, 0, 255); // фиолетовый
            case "Actinoids":
                return Color.rgb(255, 192, 203); // розовый
            default:
                return Color.rgb(0, 0, 0); // черный
        }
    }
    */
    @FXML
    private void switchToSecondView() throws IOException {
        GUIView.setRoot("secondary");
    }
}
