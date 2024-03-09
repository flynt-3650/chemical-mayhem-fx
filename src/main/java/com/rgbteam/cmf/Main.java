package com.rgbteam.cmf;

import com.rgbteam.cmf.view.CLIView;
import com.rgbteam.cmf.view.GUIView;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("cli")) {
            CLIView.main(args);
        } else {
            GUIView.main(args);
        }
    }
}
