package prop.presentation;

import javax.swing.*;
import java.util.ArrayList;

/**
 * @author casassg
 */
public abstract class TabView extends JPanel {

    private JPanel rightPanel;
    private JScrollPane leftListView;
    private JList leftList;
    private JToolBar actionBar;
    private JTextField searchField;

    /**
     * Creates new form MainPanel
     */
    public TabView() {
        initComponents();
    }

    public void setRightPanel(JPanel jPanel) {
        rightPanel = jPanel;
    }

    public JList getLeftList() {
        return leftList;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public void initComponents() {
        leftListView = new JScrollPane();
        leftList = new JList();
        leftListView.setViewportView(leftList);

        actionBar = new JToolBar();
        actionBar.setRollover(true);
        searchField = new JTextField();
        actionBar.add(searchField);

        rightPanel = createRightPanel();

        ArrayList<JButton> buttons = setActionBarButtons();
        for (JButton button : buttons) {
            actionBar.add(button);
        }

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(leftListView, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(rightPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(actionBar, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(leftListView, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(actionBar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rightPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    protected abstract JPanel createRightPanel();

    protected abstract ArrayList<JButton> setActionBarButtons();

}
