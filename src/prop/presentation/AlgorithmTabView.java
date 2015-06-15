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
    private MainView mainView;

    public AlgorithmTabView(AlgorithmPController apc, ListPController lpc, SongPController spc, UserPController upc, MainView mv) {
        algorithmPController = apc;
        listPController = lpc;
        songPController = spc;
        userPController = upc;
        mainView = mv;
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        algorithmInputView = new AlgorithmInputView(algorithmPController, this);
        setInputPanel();
    }

    public void setInputPanel() {
        removeAll();
        add(algorithmInputView, BorderLayout.CENTER);
        repaint();
    }

    public void setOutputPanel(String title, double time) {
        removeAll();
        add(new AlgorithmOutputView(algorithmPController,listPController,this,mainView,title, time),BorderLayout.CENTER);
        revalidate();
    }

    public void execute(String title, final int algorithmIndex, final int nCom) {

        SwingWorker <Double,Void> worker = new AlgorithmWorker(title,algorithmIndex,nCom);
        try {
            worker.execute();
            setExecutingPanel();
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

   private class AlgorithmWorker extends SwingWorker<Double, Void> {

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
            double time = (getCpuTime() - start) / 1e9;
            System.out.println("Execution time: " + time + " s");
            return time;
        }

        @Override
        protected void done() {
            double time;
            try {
                time = get();
            } catch (ExecutionException e) {
                e.printStackTrace();
                setInputPanel();
                algorithmInputView.throwError(e.getCause().getMessage());
                return;
            }  catch (Exception e) {
                e.printStackTrace();
                setInputPanel();
                algorithmInputView.throwError(e.getMessage());
                return;
            }
            setOutputPanel(title,time);
        }

       private double getCpuTime( ) {
           ThreadMXBean bean = ManagementFactory.getThreadMXBean();
           return bean.isCurrentThreadCpuTimeSupported( ) ?
                   bean.getCurrentThreadCpuTime( ) : System.nanoTime();
       }
    }
}