package prop.presentation;

public class MainView extends javax.swing.JFrame {

    private UserPController userPController;
    private SongPController songPController;
    private ListPController listPController;
    private AlgorithmPController algorithmPController;

    public MainView(
            UserPController upc,
            SongPController spc,
            ListPController lpc,
            AlgorithmPController apc
    ) {
        userPController = upc;
        songPController = spc;
        listPController = lpc;
        algorithmPController = apc;
        initComponents();
    }

    public void setVisible() {

    }

    private void initComponents() {

    }

}
