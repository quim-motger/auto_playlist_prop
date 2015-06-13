package prop.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author oscar.manas
 */
public class AlgorithmOutputView extends JPanel {

    private AlgorithmPController algorithmPController;
    private ListPController listPController;
    private AlgorithmTabView algorithmTabView;
    private JScrollPane rightScrollPanel;
    private GraphPanel graphPanel;
    private JTextArea textPanel;
    private JScrollPane leftListView;
    private JToolBar actionBar;
    private JLabel listTitle;
    private JList leftList;
    private JButton newInputButton;
    private JButton graphButton;
    private JButton logButton;
    private DefaultListModel listModel;
    private JButton executionButton;
    private GraphPanel.ExecutionPanel executionPanel;

    /**
     * Creates new form MainPanel
     */
    public AlgorithmOutputView(AlgorithmPController apc, ListPController lpc, AlgorithmTabView atv, String title, double time) {
        algorithmPController = apc;
        listPController = lpc;
        algorithmTabView = atv;
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        initComponents();
        updateListModel(title);
        listTitle.setText(title);
        
        JLabel timeLabel = new JLabel("Time = "+time+" s");
        actionBar.add(Box.createHorizontalGlue());
        actionBar.add(timeLabel);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        leftListView = new JScrollPane();
        leftList = new JList();
        listModel = new DefaultListModel();
        leftList.setModel(listModel);
        leftList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        leftList.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        leftListView.setViewportView(leftList);
        leftListView.setPreferredSize(new Dimension(195, 200));

        graphPanel = new GraphPanel(algorithmPController);

        executionPanel = new GraphPanel.ExecutionPanel(algorithmPController);

        textPanel = new JTextArea();
        textPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        textPanel.setEditable(false);
        textPanel.setLineWrap(true);
        textPanel.setWrapStyleWord(true);
        textPanel.setText(algorithmPController.getLog());

        rightScrollPanel = new JScrollPane();
        rightScrollPanel.setViewportView(graphPanel);

        actionBar = new JToolBar();
        actionBar.setFloatable(false);
        actionBar.setRollover(true);

        listTitle = new JLabel();
        listTitle.setText("title");
        listTitle.setPreferredSize(new Dimension(194, 200));
        actionBar.add(Box.createHorizontalStrut(3));
        actionBar.add(listTitle);
        actionBar.add(Box.createHorizontalStrut(6));

        newInputButton = new JButton("New input");
        newInputButton.setBorder(BorderFactory.createEmptyBorder(10, 3, 10, 3));
        newInputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newInputButtonActionPerformed(e);
            }
        });
        actionBar.add(newInputButton);
        actionBar.add(Box.createHorizontalStrut(5));

        graphButton = new JButton("Graph");
        graphButton.setBorder(BorderFactory.createEmptyBorder(10, 3, 10, 3));
        graphButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphButtonActionPerformed(e);
            }
        });
        graphButton.setEnabled(false);
        actionBar.add(graphButton);
        actionBar.add(Box.createHorizontalStrut(5));

        logButton = new JButton("Log");
        logButton.setBorder(BorderFactory.createEmptyBorder(10, 3, 10, 3));
        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logButtonActionPerformed(e);
            }
        });
        actionBar.add(logButton);
        actionBar.add(Box.createHorizontalStrut(5));

        executionButton = new JButton("Step-by-step");
        executionButton.setBorder(BorderFactory.createEmptyBorder(10, 3, 10, 3));
        executionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executionButtonActionPerformed(e);
            }
        });
        actionBar.add(executionButton);
        actionBar.add(Box.createHorizontalStrut(5));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(leftListView, leftListView.getPreferredSize().width, leftListView.getPreferredSize().width, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rightScrollPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(actionBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(actionBar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(leftListView, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                                        .addComponent(rightScrollPanel)))
        );
    }// </editor-fold>

    private void graphButtonActionPerformed(ActionEvent evt) {
        rightScrollPanel.setViewportView(graphPanel);
        graphButton.setEnabled(false);
        logButton.setEnabled(true);
        executionButton.setEnabled(true);
    }

    private void logButtonActionPerformed(ActionEvent evt) {
        rightScrollPanel.setViewportView(textPanel);
        logButton.setEnabled(false);
        graphButton.setEnabled(true);
        executionButton.setEnabled(true);
    }

    private void executionButtonActionPerformed(ActionEvent evt) {
        rightScrollPanel.setViewportView(executionPanel);
        executionButton.setEnabled(false);
        logButton.setEnabled(true);
        graphButton.setEnabled(true);
    }

    private void newInputButtonActionPerformed(ActionEvent evt) {
        algorithmTabView.setInputPanel();
    }

    /**
     * Update the JList which contains the list of songs.
     * @param title
     */
    public void updateListModel(String title) {
        listModel.clear();
        String list[] = listPController.getListStringArray(title);
        for (int i = 1; i < list.length; ++i) {
            listModel.addElement(list[i]);
        }
    }

}
