package prop.presentation.basicpanels;

import prop.PropException;
import prop.presentation.UserPController;
import prop.presentation.UserTabView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * AddNewAssociatedList in prop.presentation.basicpanels
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 26/05/15
 */
public class AddNewAssociatedList extends PropPanel {
    private final UserPController controller;
    private final String name;
    private final UserTabView tab;
    private final JButton associate;
    private JScrollPane listWrapper;
    private JList listOfLists;
    private DefaultListModel<String> listOfListsModel;
    
    public AddNewAssociatedList (String userName, UserPController userPController, UserTabView userTabView) {
        super();
        controller = userPController;
        name = userName;
        tab = userTabView;
        setTitleText("Associate new lists to " + name);
        updatePanel();

        JButton back = new JButton("< Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tab.showAssociatedListsPanel(name);
            }
        });
        addButton(back);
        
        associate = new JButton("Associate Lists");
        associate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                List<String> lists = listOfLists.getSelectedValuesList();
                associateLists(lists);
            }
        });
        addButton(associate);
        associate.setVisible(false);
        
        listOfLists.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                startEditMode();
            }
        });
    }

    private void startEditMode() {
        associate.setVisible(true);
    }

    private void associateLists(List<String> lists) {
        try {
            for (String list : lists) {
                controller.associateList(name, list);
            }
            showInfo("Lists associated");
            associate.setVisible(false);
        } catch (PropException e) {
            e.printStackTrace();
            throwError(e.getMessage());
        }
        updatePanel();
    }

    @Override
    protected JPanel createFormPanel() {
        JPanel panel = new JPanel();
        listWrapper = new JScrollPane();
        listOfLists = new JList();
        listOfLists.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listOfListsModel = new DefaultListModel<>();
        listOfLists.setModel(listOfListsModel);
        listWrapper.setViewportView(listOfLists);

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(listWrapper, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(listWrapper, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                                .addContainerGap())
        );
        return panel;
    }
    
    private void updatePanel() {
        try {
            listOfListsModel.clear();
            String[] lists = controller.getNotUserLists(name);
            for(String list : lists) {
                listOfListsModel.addElement(list);
            }
        } catch (PropException e) {
            e.printStackTrace();
            throwError(e.getMessage());
        }
    }
}
