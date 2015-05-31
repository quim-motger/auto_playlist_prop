package prop.presentation.basicpanels;

import prop.PropException;
import prop.presentation.UserPController;
import prop.presentation.UserTabView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ShowAssociatedLists in prop.presentation.basicpanels
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 25/05/15
 */
public class ShowAssociatedLists extends PropPanel {
    private final UserPController controller;
    private final String name;
    private final UserTabView tab;
    private final JButton showList;
    private final JButton dissasociate;
    private JScrollPane listWrapper;
    private JList listOfLists;
    private DefaultListModel<String> listOfListsModel;

    public ShowAssociatedLists(UserPController userPController, String userName, UserTabView userTabView) {
        super();
        name = userName;
        controller = userPController;
        tab = userTabView;
        updateListOfLists();
        setTitleText("Associated Lists from "+name);
        
        listOfLists.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JButton back = new JButton("< Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tab.showUserInRightPanel(name);
            }
        });
        addButton(back);
        
        JButton associate = new JButton("Edit");
        associate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tab.editAssociatedLists(name);
            }
        });
        addButton(associate);
        
        showList = new JButton("Show List");
        showList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String list = (String) listOfLists.getSelectedValue();
                tab.showList(list);
            }
        });
        addButton(showList);
        showList.setVisible(false);
        
        dissasociate = new JButton("Disassociate List");
        dissasociate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String list = (String) listOfLists.getSelectedValue();
                dissasociateList(list);
            }
        });
        //addButton(dissasociate);
        dissasociate.setVisible(false);
        
        listOfLists.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                startEditMode();
            }
        });
    }

    private void dissasociateList(String list) {
        try {
            controller.disassociateList(name,list);
            updateListOfLists();
            showInfo("Disassociated List");
            stopEditMode();
        } catch (PropException e) {
            e.printStackTrace();
            throwError(e.getMessage());
        }
    }

    private void startEditMode() {
        showList.setVisible(true);
        dissasociate.setVisible(true);
    }

    @Override
    protected JPanel createFormPanel() {
        JPanel panel = new JPanel();
        listWrapper = new JScrollPane();
        listOfLists = new JList();
        listOfListsModel = new DefaultListModel<>();
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

    private void updateListOfLists() {
        listOfListsModel.clear();
        try {
            String[] lists = controller.getUserLists(name);
            for(String list : lists) {
                listOfListsModel.addElement(list);
            }
        } catch (PropException e) {
            e.printStackTrace();
            throwError(e.getMessage());
        }
        
        listOfLists.setModel(listOfListsModel);

    }

    private void stopEditMode() {
        showList.setVisible(false);
        dissasociate.setVisible(false);
    }


}
