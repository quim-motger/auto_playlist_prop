package prop.presentation;

import prop.presentation.basicpanels.LoadingPanel;

import javax.swing.*;
import java.awt.*;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

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
        algorithmInputView = new AlgorithmInputView(songPController,userPController, algorithmPController, listPController, this);
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

    public void execute(String title, int algorithmIndex, int nCom) {
        try {
            setExecutingPanel();
            double start = getCpuTime();
            algorithmPController.execute(title, algorithmIndex, nCom, listPController.getListController(), algorithmPController.getRelationController());
            double time = (getCpuTime() - start) / 1e9;
            System.out.println("Execution time: " + time + " s");
            setOutputPanel(title);
        }
        catch (Exception e) {
            e.printStackTrace();
            setInputPanel();
            algorithmInputView.throwError(e.getMessage());
        }
    }

    private void setExecutingPanel() {
        removeAll();
        add(new LoadingPanel("Executing Algorithm"));
    }

    private double getCpuTime( ) {
        /*ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported( ) ?
                bean.getCurrentThreadCpuTime( ) : 0L;*/
        return System.nanoTime();
    }
}