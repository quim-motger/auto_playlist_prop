package prop;

import prop.presentation.MainPController;

public class Main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainPController mainPController = new MainPController();
                mainPController.initPresentation();
            }
        });
    }

}
