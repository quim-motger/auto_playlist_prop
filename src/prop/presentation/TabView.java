package prop.presentation;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author casassg
 */
public abstract class TabView extends JPanel {

    private JScrollPane rightScrollPanel;
    private JPanel rightPanel;
    private JScrollPane leftListView;
    private JToolBar actionBar;
    private JTextField searchField;
    private JList leftList;

    /**
     * Creates new form MainPanel
     */
    public TabView() {
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        initComponents();
    }

    public JList getLeftList() {
        return leftList;
    }

    protected void setLeftList(JList mLeftList) {
        leftList = mLeftList;
    }

    protected void setRightPanel(JPanel rightPanel) {
        rightScrollPanel.setViewportView(rightPanel);
    }

    private void initComponents() {
        leftListView = new JScrollPane();
        leftList = new JList();
        leftList.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        leftListView.setViewportView(leftList);
        leftListView.setPreferredSize(new Dimension(195, 200));

        rightScrollPanel = new JScrollPane();
        JPanel rightPanel = createRightPanel();
        rightScrollPanel.setViewportView(rightPanel);

        actionBar = new JToolBar();
        actionBar.setFloatable(false);
        actionBar.setRollover(true);

        searchField = new JTextField();
        searchField.setMaximumSize(new Dimension(150, 200));
        actionBar.add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.setBorder(BorderFactory.createEmptyBorder(10,3,10,3));
        actionBar.add(Box.createHorizontalStrut(4));
        actionBar.add(searchButton);
        actionBar.add(Box.createHorizontalStrut(1));
        actionBar.addSeparator();
        actionBar.add(Box.createHorizontalStrut(3));

        ArrayList<JButton> buttons = setActionBarButtons();
        for (JButton button : buttons) {
            actionBar.add(button);
            actionBar.add(Box.createHorizontalStrut(5));
        }

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(leftListView, GroupLayout.DEFAULT_SIZE, leftListView.getPreferredSize().width, GroupLayout.PREFERRED_SIZE)
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

    protected abstract JPanel createRightPanel();

    protected abstract ArrayList<JButton> setActionBarButtons();

}
