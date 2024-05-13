package com.rgbteam.cmf.view;

import com.rgbteam.cmf.GeneralFlowController;
import com.rgbteam.cmf.chemistry.Element;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.paint.Color;

import java.io.IOException;

public class PrimaryViewController {
    private final GeneralFlowController controller = new GeneralFlowController();
    public TextFlow textFlow;
    public Text elAtom;
    public Text shortName;
    public Text elementNumber;
    public Text fullName;
    public Button buttonNM;
    public Button buttonNG;
    public Button buttonAM;
    public Button buttonAE;
    public Button buttonM;
    public Button buttonH;
    public Button buttonPT;
    public Button buttonTM;
    public Button buttonL;
    public Button buttonA;
    @FXML
    private GridPane myGridPane;
    private Button[] buttons;

    public void initialize() {
        addListeners();
        showButtons();
        GroupButtonsConst();
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
            String shortName = source.getText();
            var e = controller.retrieveElementByShortName(shortName);
            this.shortName.setText(e.getShortName() + " ");
            elAtom.setText(e.getAtomicMass() + " ");
            elementNumber.setText(e.getNumber() + " ");
            fullName.setText(e.getFullName() + " ");
        };
        for (int i = 0; i < buttons.length; i++) {
            Element e = controller.retrieveElementByNumber(i + 1);
            if (e != null) {
                buttons[i] = new Button(e.getShortName());
                buttons[i].setOnMouseClicked(elementButtonListener);
                SetButtonsStyle(i);
            }
        }

    }

    public void SetButtonsStyle(int i){
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
        button.setBackground(new Background(new BackgroundFill(fxColor, new CornerRadii(0), Insets.EMPTY)));
        button.setOnMouseEntered(event -> {
            button.setStyle("-fx-background-color: derive(-fx-base, -20%); -fx-background-radius: 0;");
            button.getStyleClass().add("round-button");
        });
        button.setOnMouseExited(event -> {
            button.setStyle("-fx-background-color: " + fxColor.toString().substring(2, 10) + "; -fx-background-radius: 0;");
            button.getStyleClass().add("round-button");
        });
        button.setOnMousePressed(event -> {
            button.setStyle("-fx-background-color: derive(-fx-base, -30%); -fx-background-radius: 0;");
            button.getStyleClass().add("round-button");
        });
        button.setOnMouseReleased(event -> {
            button.setStyle("-fx-background-color: derive(-fx-base, -20%); -fx-background-radius: 0;");
            button.getStyleClass().add("round-button");
        });
    }



    // светлая тема (окраска кнопки и цвет текста)
    private Color getColorForGroupLight(String group, Button button) {
        Color fxColor;
        switch (group) {
            case "nonmetal" -> {
                fxColor = Color.web("#e6f0ff", 1.0);// цвет кнопки
                button.setTextFill(Color.web("#0060f0", 1.0));//цвет текста
            }
            case "noble gases" -> {
                fxColor = Color.web("#ffebee", 1.0);
                button.setTextFill(Color.web("#cd1d5e", 1.0));
            }
            case "alkali metals" -> {
                fxColor = Color.web("#dbf8ff", 1.0);
                button.setTextFill(Color.web("#00758c", 1.0));
            }
            case "alkaline earth metals" -> {
                fxColor = Color.web("#ffebeb", 1.0);
                button.setTextFill(Color.web("#d60024", 1.0));
            }
            case "metalloids" -> {
                fxColor = Color.web("#fef9e6", 1.0);
                button.setTextFill(Color.web("#945700", 1.0));
            }
            case "halogen" -> {
                fxColor = Color.web("#e9e9ec", 1.0);
                button.setTextFill(Color.web("#3f374f", 1.0));
            }
            case "transition metals" -> {
                fxColor = Color.web("#f5ecfd", 1.0);
                button.setTextFill(Color.web("#6232ec", 1.0));
            }
            case "post-transition metals" -> {
                fxColor = Color.web("#dcfaeb", 1.0);
                button.setTextFill(Color.web("#103b11", 1.0));
            }
            case "lanthanoids" -> {
                fxColor = Color.web("#e6f5ff", 1.0);
                button.setTextFill(Color.web("#4c738d", 1.0));
            }
            case "actinoids" -> {
                fxColor = Color.web("#ffeadb", 1.0);
                button.setTextFill(Color.web("#c73200", 1.0));
            }
            default -> fxColor = Color.web("#ffffff", 1.0);
        }
        return fxColor;
    }

    public void GroupButtonsConst(){
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
        SetColourGroup(buttonsGroup);
    }

    public void SetColourGroup(Button[] buttonsGroup){
        for (Button button : buttonsGroup) {
            String groupColour = button.getText();
            Color fxColor = getColorForGroupLight(groupColour, button);
            button.setBackground(new Background(new BackgroundFill(fxColor, new CornerRadii(0), Insets.EMPTY)));
            setActionButtonsGroup(button , fxColor);
        }
    }


    public void setActionButtonsGroup(Button button , Color fxColor){
            button.setOnMousePressed(event -> {
                button.setStyle("-fx-background-color: derive(-fx-base, -30%); -fx-background-radius: 0;");
                button.getStyleClass().add("round-button");
                Button source = (Button) event.getSource();
                String groupName = source.getText();
                SetColourForGroup(groupName);
            });
            button.setOnMouseEntered(event -> {
                button.setStyle("-fx-background-color: derive(-fx-base, -20%); -fx-background-radius: 0;");
                button.getStyleClass().add("round-button");
            });
            button.setOnMouseExited(event -> {
                button.setStyle("-fx-background-color: " + fxColor.toString().substring(2, 10) + "; -fx-background-radius: 0;");
                button.getStyleClass().add("round-button");
            });
            button.setOnMouseReleased(event -> {
                button.setStyle("-fx-background-color: derive(-fx-base, -20%); -fx-background-radius: 0;");
                button.getStyleClass().add("round-button");
                for (int i = 0; i < buttons.length; i ++) {
                    setColor(buttons[i], i);
                }
            });
    }

    public void SetColourForGroup(String groupName) {
        for (int i = 0; i < buttons.length; i++) {
            Button button = buttons[i];
            if (button == null) {
                continue;
            }
            String elementGroup = controller.retrieveElementGroup(i + 1);
            if (groupName.equals(elementGroup)) {
                Color fxColor = getColorForGroupLight(elementGroup, button);
                button.setBackground(new Background(new BackgroundFill(fxColor, new CornerRadii(0), Insets.EMPTY)));
            } else {
                button.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(0), Insets.EMPTY)));
            }
        }
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
    private void switchToSecondView() throws IOException {
        GUIView.setRoot("secondary");
    }
}
