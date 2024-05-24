package com.rgbteam.cmf.view;

import com.rgbteam.cmf.GeneralFlowController;
import com.rgbteam.cmf.chemistry.Element;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.IOException;

public class PrimaryViewController {
    private final GeneralFlowController controller = new GeneralFlowController();
    @FXML
    private Text shortName;
    @FXML
    private Text elementNumber;
    @FXML
    private Text fullName;
    @FXML
    private Button buttonNM;
    @FXML
    private Button buttonNG;
    @FXML
    private Button buttonAM;
    @FXML
    private Button buttonAE;
    @FXML
    private Button buttonM;
    @FXML
    private Button buttonH;
    @FXML
    private Button buttonPT;
    @FXML
    private Button buttonTM;
    @FXML
    private Button buttonL;
    @FXML
    private Button buttonA;
    @FXML
    private Text atomicMass;
    @FXML
    private Text molarMass;
    @FXML
    private Text groupElement;
    @FXML
    private Text protonAmount;
    @FXML
    private Text neutronAmount;
    @FXML
    private Text electronAmount;
    @FXML
    private Label lableElement;
    @FXML
    private Label lableInfo;
    @FXML
    private GridPane myGridPane;
    private Button[] buttons;

    public void initialize() {
        addListeners();
        showButtons();
        groupButtonsConst();
    }


    public void showButtons() {
        int i = 0;
        for (int row = 2; row <= 11; row++) {
            for (int column = 1; column <= 18; column++) {
                if ((row == 2 && column < 18 && column > 1) || (row == 3 && column < 13 && column > 2)
                        || (row == 4 && column < 13 && column > 2) || (row == 9) || (row == 10 && column < 3)
                        || (row == 11 && column < 3)) {
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

    public void addListeners() {
        buttons = new Button[119];
        EventHandler<MouseEvent> elementButtonListener = mouseEvent -> {
            Button source = (Button) mouseEvent.getSource();
            infoWindows(source);
        };
        for (int i = 0; i < buttons.length; i++) {
            Element e = controller.retrieveElementByNumber(i + 1);
            if (e != null) {
                buttons[i] = new Button(e.getShortName());
                buttons[i].setOnMouseClicked(elementButtonListener);
                setButtonsStyle(i);
            }
        }

    }

    public void setButtonsStyle(int i){
        buttons[i].setFont(Font.font("Arial", 16));
        buttons[i].setMaxSize(1000, 1000);
        setColor(buttons[i],i);
    }

    public void setColor(Button button, int i) {
        if (button == null) {
            return;
        }
        String group = controller.retrieveElementGroup(i + 1);
        Color fxColor = getColorForGroupLight(group, button); // светлая тема
        button.setBackground(new Background(new BackgroundFill(fxColor, new CornerRadii(10), Insets.EMPTY)));
        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: derive(-fx-base, -20%); -fx-background-radius: 10;"));
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: " +
                fxColor.toString().substring(2, 10) + "; -fx-background-radius: 10;"));
        button.setOnMousePressed(event -> button.setStyle("-fx-background-color: derive(-fx-base, -30%); -fx-background-radius: 10;"));
        button.setOnMouseReleased(event -> button.setStyle("-fx-background-color: derive(-fx-base, -20%); -fx-background-radius: 10;"));
    }



    // светлая тема (окраска кнопки и цвет текста)
    private Color getColorForGroupLight(String group, Button button) {
        Color fxColor;
        switch (group) {
            case "nonmetal" -> {
                fxColor = Color.web("#e6f0ff", 1.0);// цвет кнопки
                button.setTextFill(Color.web("#0060f0", 1.0));//цвет текста
                button.setBorder(new Border(new BorderStroke(Color.web("#0060f0"), BorderStrokeStyle.SOLID,
                        new CornerRadii(10), BorderWidths.DEFAULT)));
            }
            case "noble gases" -> {
                fxColor = Color.web("#ffebee", 1.0);
                button.setTextFill(Color.web("#cd1d5e", 1.0));
                button.setBorder(new Border(new BorderStroke(Color.web("#cd1d5e"), BorderStrokeStyle.SOLID,
                        new CornerRadii(10), BorderWidths.DEFAULT)));
            }
            case "alkali metals" -> {
                fxColor = Color.web("#dbf8ff", 1.0);
                button.setTextFill(Color.web("#00758c", 1.0));
                button.setBorder(new Border(new BorderStroke(Color.web("#00758c"), BorderStrokeStyle.SOLID,
                        new CornerRadii(10), BorderWidths.DEFAULT)));
            }
            case "alkaline earth metals" -> {
                fxColor = Color.web("#ffebeb", 1.0);
                button.setTextFill(Color.web("#d60024", 1.0));
                button.setBorder(new Border(new BorderStroke(Color.web("#d60024"), BorderStrokeStyle.SOLID,
                        new CornerRadii(10), BorderWidths.DEFAULT)));
            }
            case "metalloids" -> {
                fxColor = Color.web("#fef9e6", 1.0);
                button.setTextFill(Color.web("#945700", 1.0));
                button.setBorder(new Border(new BorderStroke(Color.web("#945700"), BorderStrokeStyle.SOLID,
                        new CornerRadii(10), BorderWidths.DEFAULT)));
            }
            case "halogen" -> {
                fxColor = Color.web("#e9e9ec", 1.0);
                button.setTextFill(Color.web("#3f374f", 1.0));
                button.setBorder(new Border(new BorderStroke(Color.web("#3f374f"), BorderStrokeStyle.SOLID,
                        new CornerRadii(10), BorderWidths.DEFAULT)));
            }
            case "transition metals" -> {
                fxColor = Color.web("#f5ecfd", 1.0);
                button.setTextFill(Color.web("#6232ec", 1.0));
                button.setBorder(new Border(new BorderStroke(Color.web("#6232ec"), BorderStrokeStyle.SOLID,
                        new CornerRadii(10), BorderWidths.DEFAULT)));
            }
            case "post-transition metals" -> {
                fxColor = Color.web("#dcfaeb", 1.0);
                button.setTextFill(Color.web("#103b11", 1.0));
                button.setBorder(new Border(new BorderStroke(Color.web("#103b11"), BorderStrokeStyle.SOLID,
                        new CornerRadii(10), BorderWidths.DEFAULT)));
            }
            case "lanthanoids" -> {
                fxColor = Color.web("#e6f5ff", 1.0);
                button.setTextFill(Color.web("#4c738d", 1.0));
                button.setBorder(new Border(new BorderStroke(Color.web("#4c738d"), BorderStrokeStyle.SOLID,
                        new CornerRadii(10), BorderWidths.DEFAULT)));
            }
            case "actinoids" -> {
                fxColor = Color.web("#ffeadb", 1.0);
                button.setTextFill(Color.web("#c73200", 1.0));
                button.setBorder(new Border(new BorderStroke(Color.web("#c73200"), BorderStrokeStyle.SOLID,
                        new CornerRadii(10), BorderWidths.DEFAULT)));
            }
            default -> fxColor = Color.web("#ffffff", 1.0);
        }
        return fxColor;
    }

    public void groupButtonsConst(){
        Button[] buttonsGroup = new Button[10];
        buttonsGroup[0] = buttonNM;
        buttonsGroup[1] = buttonNG;
        buttonsGroup[2] = buttonAM;
        buttonsGroup[3] = buttonAE;
        buttonsGroup[4] = buttonM;
        buttonsGroup[5] = buttonH;
        buttonsGroup[6] = buttonPT;
        buttonsGroup[7] = buttonTM;
        buttonsGroup[8] = buttonL;
        buttonsGroup[9] = buttonA;
        setColorGroup(buttonsGroup);
    }

    public void setColorGroup(Button[] buttonsGroup){
        for (Button button : buttonsGroup) {
            String groupColour = button.getText();
            Color fxColor = getColorForGroupLight(groupColour, button);
            button.setBackground(new Background(new BackgroundFill(fxColor, new CornerRadii(10), Insets.EMPTY)));
            setActionButtonsGroup(button , fxColor);
        }
    }


    public void setActionButtonsGroup(Button button , Color fxColor){
            button.setOnMousePressed(event -> {
                button.setStyle("-fx-background-color: derive(-fx-base, -30%); -fx-background-radius: 10;");
                Button source = (Button) event.getSource();
                String groupName = source.getText();
                setColorForGroup(groupName);
            });
            button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: derive(-fx-base, -20%); -fx-background-radius: 10;"));
            button.setOnMouseExited(event -> button.setStyle("-fx-background-color: " +
                    fxColor.toString().substring(2, 10) + "; -fx-background-radius: 10;"));
            button.setOnMouseReleased(event -> {
                button.setStyle("-fx-background-color: derive(-fx-base, -20%); -fx-background-radius: 10;");
                for (int i = 0; i < buttons.length; i ++) {
                    setColor(buttons[i], i);
                }
            });
    }

    public void setColorForGroup(String groupName) {
        for (int i = 0; i < buttons.length; i++) {
            Button button = buttons[i];
            if (button == null) {
                continue;
            }
            String elementGroup = controller.retrieveElementGroup(i + 1);
            if (!groupName.equals(elementGroup)) {
                button.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"),
                        new CornerRadii(10), Insets.EMPTY)));
                button.setTextFill(Color.web("#ffffff", 1.0));
            }
        }
    }

    public void infoWindows(Button source){
        String shortName = source.getText();
        var e = controller.retrieveElementByShortName(shortName);
        this.shortName.setText(e.getShortName());
        int number = e.getNumber();
        this.shortName.setFill(buttons[number-1].getTextFill());
        elementNumber.setText(String.valueOf(e.getNumber()));
        elementNumber.setFill(buttons[number-1].getTextFill());
        fullName.setText(e.getFullName());
        fullName.setFill(buttons[number-1].getTextFill());
        atomicMass.setText("  Atomic Mass: " + e.getAtomicMass());
        atomicMass.setFill(buttons[number-1].getTextFill());
        molarMass.setText("  Molar Mass: " + String.format("%.4f", e.getMolarMass()));
        molarMass.setFill(buttons[number-1].getTextFill());
        groupElement.setText(" Group: " + e.getElementGroup());
        groupElement.setFill(buttons[number-1].getTextFill());
        protonAmount.setText(" Protons: " + e.getProtonAmount());
        protonAmount.setFill(buttons[number-1].getTextFill());
        neutronAmount.setText(" Neutrons: " + e.getNeutronAmount());
        neutronAmount.setFill(buttons[number-1].getTextFill());
        electronAmount.setText(" Electrons: " + e.getElectronAmount());
        electronAmount.setFill(buttons[number-1].getTextFill());
        lableInfo.setBackground(new Background(new BackgroundFill(getColorForGroupLight(e.getElementGroup(),
                buttons[number - 1]), new CornerRadii(10), Insets.EMPTY)));
        lableInfo.setBorder(buttons[number-1].getBorder());

        lableElement.setBackground(new Background(new BackgroundFill(getColorForGroupLight(e.getElementGroup(),
                buttons[number - 1]), new CornerRadii(10), Insets.EMPTY)));
        lableElement.setBorder(buttons[number-1].getBorder());
    }




/* //темная
    private Color getColorForGroupDark(String group, Button button){
        Color fxColor;
        switch (group) {
            case "nonmetal":
                fxColor = Color.web("#ff4444", 1.0);
                button.setTextFill(Color.WHITE);
                break;
            case "alkali metals":
                fxColor = Color.web("#6c3b01", 1.0);
                button.setTextFill(Color.WHITE);
                break;
            case "alkaline earth metals":
                fxColor = Color.web("#6c3b01", 1.0);
                button.setTextFill(Color.WHITE);
                break;
            case "transition metals":
                fxColor = Color.web("#711019", 1.0);
                button.setTextFill(Color.WHITE);
                break;
            case "lanthanoids":
                fxColor = Color.web("#402c17", 1.0);
                button.setTextFill(Color.WHITE);
                break;
            case "actinoids":
                fxColor = Color.web("#732e4c", 1.0);
                button.setTextFill(Color.WHITE);
                break;
            case "post-transition metals":
                fxColor = Color.web("#003666", 1.0);
                button.setTextFill(Color.WHITE);
                break;
            case "metalloids":
                fxColor = Color.web("#015146", 1.0);
                button.setTextFill(Color.WHITE);
                break;
            case "noble gases":
                fxColor = Color.web("#3a2151", 1.0);
                button.setTextFill(Color.WHITE);
                break;
            default:
                fxColor = Color.web("#e9e9ec", 1.0);
                button.setTextFill(Color.WHITE);
                break;
        }
        return fxColor;
    }
*/

@FXML
public void showInfoAboutPeriodicTable() {
    Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
    infoAlert.setTitle("Information about periodic table");
    infoAlert.setHeaderText("How to use");

    Text infoText = new Text("In this periodic table you can get acquainted with basic information about the element, as well as see groups of elements\n" +
            "Authors: Repilov. I.I., Bondarenko E.A., Oleg");
    infoText.setWrappingWidth(300);
    infoText.setFill(Paint.valueOf("BLACK"));

    ScrollPane scrollPane = new ScrollPane(infoText);
    scrollPane.setPrefViewportWidth(300);
    scrollPane.setPrefViewportHeight(50);

    infoAlert.getDialogPane().setContent(scrollPane);

    infoAlert.showAndWait();
}


    @FXML
    private void switchToSecondView() throws IOException {
        GUIView.setRoot("secondary");
    }
}
