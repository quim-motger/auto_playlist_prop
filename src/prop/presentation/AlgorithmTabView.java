package prop.presentation;

import prop.presentation.basicpanels.LoadingPanel;

import javax.swing.*;
import java.awt.*;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.ExecutionException;

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

    public void setOutputPanel(String title, double time) {
        removeAll();
        add(new AlgorithmOutputView(algorithmPController,listPController,this,title, time),BorderLayout.CENTER);
    }

    public void execute(String title, final int algorithmIndex, final int nCom) {
        setExecutingPanel();
        
        SwingWorker <Double,Object> worker = new AlgorithmWorker(title,algorithmIndex,nCom);
        try {
            worker.execute();
        } catch (Exception e) {
            e.printStackTrace();
            setInputPanel();
            algorithmInputView.throwError(e.getMessage());
        }
    }

    private void setExecutingPanel() {
        removeAll();
        add(new LoadingPanel("Executing Algorithm"));
    }

   class AlgorithmWorker extends SwingWorker<Double, Object> {

       private final int nCom;
       private final int algorithmIndex;
       private final String title;

       public AlgorithmWorker(String listTitle, int algIndex, int nCommunities) {
           title = listTitle;
           algorithmIndex = algIndex;
           nCom = nCommunities;
       }
       
       
        @Override
        protected Double doInBackground() throws Exception {
            double start = getCpuTime();
            algorithmPController.execute(title, algorithmIndex, nCom);
            return  (getCpuTime() - start) / 1e9;
        }

        @Override
        protected void done() {
            try {
                setOutputPanel(title,get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

       private double getCpuTime( ) {
           return System.nanoTime();
       }
    }
}