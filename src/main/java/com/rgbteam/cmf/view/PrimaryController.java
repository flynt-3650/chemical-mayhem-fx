package com.rgbteam.cmf.view;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        GUIView.setRoot("/com/rgbteam/cmf/secondary.fxml");
    }
}
