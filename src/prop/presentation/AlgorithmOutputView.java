package prop.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlgorithmOutputView extends JPanel {

    private AlgorithmPController algorithmPController;
    private ListPController listPController;
    private JScrollPane rightScrollPanel;
    private GraphPanel graphPanel;
    private JTextArea textPanel;
    private JScrollPane leftListView;
    private JToolBar actionBar;
    private JLabel listTitle;
    private JList leftList;
    private JButton button;
    private DefaultListModel listModel;
    private Boolean isGraphVisible;

    /**
     * Creates new form MainPanel
     */
    public AlgorithmOutputView(AlgorithmPController apc, ListPController lpc, String title) {
        algorithmPController = apc;
        listPController = lpc;
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        initComponents();
        updateListModel(title);
    }

    private void initComponents() {
        leftListView = new JScrollPane();
        leftList = new JList();
        listModel = new DefaultListModel();
        leftList.setModel(listModel);
        leftList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        leftList.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        leftListView.setViewportView(leftList);
        leftListView.setPreferredSize(new Dimension(195, 200));

        graphPanel = new GraphPanel();
        textPanel = new JTextArea();
        textPanel.setEditable(false);

        rightScrollPanel = new JScrollPane();
        rightScrollPanel.setViewportView(graphPanel);
        isGraphVisible = true;

        actionBar = new JToolBar();
        actionBar.setFloatable(false);
        actionBar.setRollover(true);

        listTitle = new JLabel();
        listTitle.setText("List title");
        listTitle.setMaximumSize(new Dimension(194, 200));
        actionBar.add(Box.createHorizontalStrut(3));
        actionBar.add(listTitle);
        actionBar.add(Box.createHorizontalStrut(6));

        button = new JButton("Algorithm Log");
        button.setBorder(BorderFactory.createEmptyBorder(10, 3, 10, 3));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonActionPerformed(e);
            }
        });
        actionBar.add(button);

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
    }

    private void buttonActionPerformed(ActionEvent evt) {
        if (isGraphVisible) {
            button.setText("Graph");
            rightScrollPanel.setViewportView(textPanel);
            isGraphVisible = false;
        }
        else {
            button.setText("Algorithm Log");
            rightScrollPanel.setViewportView(graphPanel);
            isGraphVisible = true;
        }
    }

    public void updateListModel(String title) {
        listModel.clear();
        /*String list[] = listPController.getListStringArray(title);
        listTitle.setText(list[0]);*/
        String list[] = {"title","aaa","bbb","ccc","ddd"};
        for (int i = 1; i < list.length; ++i) {
            listModel.addElement(list[i]);
        }
    }

}
