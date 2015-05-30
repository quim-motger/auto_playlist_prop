package prop.presentation;

import javax.swing.*;
import java.awt.*;

public class AlgorithmTabView extends JPanel {

    private AlgorithmPController algorithmPController;
    private ListPController listPController;
    private SongPController songPController;
    private UserPController userPController;
    private AlgorithmInputView algorithmInputView;

    public AlgorithmTabView(AlgorithmPController apc, ListPController lpc, SongPController spc, UserPController upc) {
        algorithmPController = apc;
        listPController = lpc;
        songPController = spc;
        userPController = upc;
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        algorithmInputView = new AlgorithmInputView(songPController,userPController,this);
        setInputPanel();
    }

    public void setInputPanel() {
        removeAll();
        add(algorithmInputView, BorderLayout.CENTER);
        repaint();
    }

    public void setOutputPanel(String title) {
        removeAll();
        add(new AlgorithmOutputView(algorithmPController,listPController,this,title),BorderLayout.CENTER);
    }

}