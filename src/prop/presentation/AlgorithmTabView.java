package prop.presentation;

import javax.swing.*;
import java.awt.*;

public class AlgorithmTabView extends JPanel {

    private AlgorithmPController algorithmPController;
    private ListPController listPController;
    private AlgorithmInputView algorithmInputView;

    public AlgorithmTabView(AlgorithmPController apc, ListPController lpc) {
        algorithmPController = apc;
        listPController = lpc;
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        algorithmInputView = new AlgorithmInputView();
        setOutputPanel("");
    }

    public void setInputPanel() {
        removeAll();
        add(algorithmInputView,BorderLayout.CENTER);
    }

    public void setOutputPanel(String title) {
        removeAll();
        add(new AlgorithmOutputView(algorithmPController,listPController,this,title),BorderLayout.CENTER);
    }

}