package com.rgbteam.cmf.view;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {
    @FXML
    private void switchToSecondView() throws IOException {
        GUIView.setRoot("secondary");
    }
}
