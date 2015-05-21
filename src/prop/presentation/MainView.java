package prop.presentation;

public class MainView extends javax.swing.JFrame {

    private UserPresentationController userPresentationController;
    private SongPresentationController songPresentationController;
    private ListPresentationController listPresentationController;
    private AlgorithmPresentationController algorithmPresentationController;

    public MainView(
            UserPresentationController upc,
            SongPresentationController spc,
            ListPresentationController lpc,
            AlgorithmPresentationController apc
    ) {
        userPresentationController = upc;
        songPresentationController = spc;
        listPresentationController = lpc;
        algorithmPresentationController = apc;
        initComponents();
    }

    public void setVisible() {

    }

    private void initComponents() {

    }

}
