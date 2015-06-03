/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prop.presentation.basicpanels;

import prop.ErrorString;
import prop.PropException;
import prop.presentation.UserPController;
import prop.presentation.UserTabView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


/**
 *
 * @author casassg
 */
public class AssociateListToUserPanel extends PropPanel {

    private final UserPController controller;
    private final String name;
    private final UserTabView tab;
    private JScrollPane associatedListsScroll;
    private JList associatedLists;
    private JPanel centerYPanel;
    private Box.Filler leftFiller;
    private JPanel centerXFiller;
    private JButton associateList;
    private JButton disassociateList;
    private Box.Filler rightFiller;
    private JScrollPane restListsScroll;
    private JList restList;
    private JLabel associatedListsLabel;
    private JLabel restListLabel;
    private DefaultListModel<String> associatedModel;
    private DefaultListModel<String> restModel;


    /**
     * Creates a new Associative Panel for list of the specified user
     */
    public AssociateListToUserPanel(String username, UserPController userPController, UserTabView userTabView) {
        super();
        name = username;
        controller = userPController;
        setTitleText("Lists Associated "+name);
        tab = userTabView;

        updateRestList();
        updateAssociatedListsList();
        setListeners();
        
        JButton back = new JButton("< Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tab.showAssociatedListsPanel(name);
            }
        });
        
        
        addButton(back);
        
        associatedListsLabel.setText(name+"'s lists");
    }

    @Override
    protected JPanel createFormPanel() {
        JPanel mainPanel = new JPanel();
        associatedListsScroll = new JScrollPane();
        associatedLists = new JList();
        centerYPanel = new JPanel();
        leftFiller = new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(32767, 0));
        centerXFiller = new JPanel();
        associateList = new JButton();
        disassociateList = new JButton();
        rightFiller = new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(32767, 0));
        restListsScroll = new JScrollPane();
        restList = new JList();
        associatedListsLabel = new JLabel();
        restListLabel = new JLabel();
        associatedModel = new DefaultListModel<>();
        restModel = new DefaultListModel<>();

        associatedListsScroll.setViewportView(associatedLists);

        centerYPanel.setLayout(new BoxLayout(centerYPanel, BoxLayout.LINE_AXIS));
        centerYPanel.add(leftFiller);

        centerXFiller.setLayout(new BoxLayout(centerXFiller, BoxLayout.Y_AXIS));

        associateList.setText("<<");
        centerXFiller.add(associateList);

        disassociateList.setText(">>");
        centerXFiller.add(disassociateList);

        centerYPanel.add(centerXFiller);
        centerYPanel.add(rightFiller);
        
        restListsScroll.setViewportView(restList);

        restListLabel.setText("Rest of the lists");

        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(associatedListsLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(associatedListsScroll))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(centerYPanel, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(restListLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(restListsScroll))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(centerYPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(associatedListsLabel)
                                                        .addComponent(restListLabel))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(restListsScroll, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                                                        .addComponent(associatedListsScroll))))
                                .addContainerGap())
        );
        return mainPanel;
    }
    
    private void updateLists() {
        updateAssociatedListsList();
        updateRestList();
    }

    private void updateAssociatedListsList() {
        try {
            associatedModel.clear();
            String[] lists = controller.getUserLists(name);
            for(String list : lists) {
                associatedModel.addElement(list);
            }
        } catch (PropException e) {
            throwError(e.getMessage());
            e.printStackTrace();
        }
        associatedLists.setModel(associatedModel);
    }

    private void updateRestList() {
        restModel.clear();
        String[] lists = new String[0];
        try {
            lists = controller.getNotUserLists(name);
            for(String list : lists) {
                restModel.addElement(list);
            }
            restList.setModel(restModel);
        } catch (PropException e) {
            e.printStackTrace();
            throwError(e.getMessage());
        }
    }

    private void setListeners() {
        associateList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(restList.isSelectionEmpty()) {
                    throwError(ErrorString.NO_ITEM_SELECTED);
                    return;
                }
                List<String> lists = restList.getSelectedValuesList();
                associateLists(lists);
                updateLists();
            }
        });
        
        disassociateList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(associatedLists.isSelectionEmpty()) {
                    throwError(ErrorString.NO_ITEM_SELECTED);
                    return;
                }
                List<String> lists = associatedLists.getSelectedValuesList();
                disassociateLists(lists);
                updateLists();
            }
        });
    }

    private void disassociateLists(List<String> lists) {
        for (String list : lists) {
            disassociateList(list);
        }
    }

    private void disassociateList(String list) {
        try {
            controller.disassociateList(name,list);
        } catch (PropException e) {
            throwError(e.getMessage());
        }
    }

    private void associateList(String list) {
        try {
            controller.associateList(name,list);
        } catch (PropException e) {
            throwError(e.getMessage());
        }
    }

    private void associateLists(List<String> lists) {
        for(String list: lists) {
            associateList(list);
        }
    }
    
    

}
