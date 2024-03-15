package com.rgbteam.cmf.view;

import java.io.IOException;

import com.rgbteam.cmf.AppFlowController;

import javafx.fxml.FXML;

public class PrimaryController {
    private AppFlowController controller = new AppFlowController();

    @FXML
    private void switchToSecondView() throws IOException {
        GUIView.setRoot("secondary");
    }
}
