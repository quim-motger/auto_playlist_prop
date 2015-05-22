package prop.presentation;

import javax.swing.*;
import java.util.ArrayList;

/**
 * @author casassg
 */
public abstract class TabView extends JPanel {

    private JPanel rightPanel;
    private JScrollPane leftListView;
    private JToolBar actionBar;

    /**
     * Creates new form MainPanel
     */
    public TabView() {
        initComponents();
    }

    private void initComponents() {
        leftListView = new JScrollPane();
        actionBar = new JToolBar();


        leftListView.setViewportView(createLeftList());

        rightPanel = createRightPanel();

        actionBar.setRollover(true);

        JTextField searchField = createSearchField();

        actionBar.add(searchField);

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

    protected abstract JTextField createSearchField();

    protected abstract JPanel createRightPanel();

    protected abstract ArrayList<JButton> setActionBarButtons();

    public abstract JList createLeftList();


}
