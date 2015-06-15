package prop.presentation;

import prop.PropException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    private MainView mainView;
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
    private JPanel leftPanel;
    private JButton generateListButton;
    private String title;

    /**
     * Creates new form MainPanel
     */
    public AlgorithmOutputView(AlgorithmPController apc, ListPController lpc, AlgorithmTabView atv, MainView mv, String _title, double time) {
        algorithmPController = apc;
        listPController = lpc;
        algorithmTabView = atv;
        mainView = mv;
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        initComponents();
        updateListModel();
        title = _title;
        
        JLabel timeLabel = new JLabel("Time = "+time+" s");
        actionBar.add(Box.createHorizontalGlue());
        actionBar.add(timeLabel);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        leftPanel = new JPanel(new BorderLayout());
        leftListView = new JScrollPane();
        leftList = new JList();
        listModel = new DefaultListModel();
        leftList.setModel(listModel);
        leftList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        leftList.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        leftListView.setViewportView(leftList);
        leftPanel.setPreferredSize(new Dimension(195, 200));
        leftListView.setPreferredSize(new Dimension(195, 200));
        leftPanel.add(leftListView, BorderLayout.CENTER);

        graphPanel = new GraphPanel(algorithmPController);

        leftList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                int comm[] = leftList.getSelectedIndices();
                graphPanel.makeItBigger(comm);
            }
        });
        
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
        listTitle.setText("Communities:");
        listTitle.setPreferredSize(new Dimension(194, 200));
        actionBar.add(Box.createHorizontalStrut(3));
        actionBar.add(listTitle);
        actionBar.add(Box.createHorizontalStrut(6));

        generateListButton = new JButton("Generate list");
        generateListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateListButtonActionPerformed(e);
            }
        });
        leftPanel.add(generateListButton, BorderLayout.SOUTH);

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
                                .addComponent(leftPanel, leftPanel.getPreferredSize().width, leftPanel.getPreferredSize().width, GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(leftPanel, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
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

    private void generateListButtonActionPerformed(ActionEvent evt) {
        try {
            algorithmPController.generateList(title,leftList.getSelectedIndices(),listPController.getListController());
        } catch (PropException e) {
            e.printStackTrace();
        }
        mainView.showList(title);
        algorithmTabView.setInputPanel();
    }

    /**
     * Update the JList which contains the list of songs.
     */
    public void updateListModel() {
        listModel.clear();
        for (int i = 0; i < algorithmPController.getNCommunities(); ++i)
            listModel.addElement("Community " + i);
    }

}
