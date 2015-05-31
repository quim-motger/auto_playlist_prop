package prop.presentation.basicpanels;

import javax.swing.*;

/**
 * LoadingPanel in prop.presentation.basicpanels
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 31/05/15
 */
public class LoadingPanel extends PropPanel{
    
    private JProgressBar progressBar;
    
    public LoadingPanel(String title) {
        super();
        setTitleText(title);
    }

    @Override
    protected JPanel createFormPanel() {
        JPanel mainPanel = new JPanel();
        progressBar = new JProgressBar();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        progressBar.setIndeterminate(true);
        mainPanel.add(progressBar);
        return mainPanel;
    }
}
