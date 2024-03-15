package com.rgbteam.cmf.view;

import java.io.IOException;

import com.rgbteam.cmf.GeneralFlowController;

import javafx.fxml.FXML;

public class PrimaryViewController {
    private GeneralFlowController controller = new GeneralFlowController();

    @FXML
    private void switchToSecondView() throws IOException {
        GUIView.setRoot("secondary");
    }
}
