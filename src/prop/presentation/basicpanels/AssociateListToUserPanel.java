/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prop.presentation.basicpanels;

import prop.PropException;
import prop.presentation.UserPController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author casassg
 */
public class AssociateListToUserPanel extends PropPanel {

    private final UserPController controller;
    private final String name;
    private JList allListsList;
    private JPanel allListsPanel;
    private JScrollPane allListsScrollPanel;
    private JLabel allListsTitle;
    private JButton associateButton;
    private JPanel associatedListPanel;
    private JList associatedListsList;
    private JScrollPane associatedListsScrollPanel;
    private JLabel associatedListsTitle;
    private JPanel centerPanel;
    private JButton disassociateButton;
    private Box.Filler spacer;
    private DefaultListModel<Object> associatedModel;
    private DefaultListModel<Object> allListsModel;

    public AssociateListToUserPanel(String username, UserPController userPController) {
        super();
        name = username;
        controller = userPController;
        setTitleText("Associate Lists to "+name);

        updateAllListsList();
        updateAssociatedListsList();
        setListeners();
    }

    @Override
    protected JPanel createFormPanel() {
        JPanel mainPanel = new JPanel();
        associatedModel = new DefaultListModel<>();
        allListsModel = new DefaultListModel<>();
        allListsPanel = new JPanel();
        allListsTitle = new JLabel();
        allListsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        allListsScrollPanel = new JScrollPane();
        allListsList = new JList();
        associatedListPanel = new JPanel();
        associatedListsTitle = new JLabel();
        associatedListsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        associatedListsScrollPanel = new JScrollPane();
        associatedListsList = new JList();
        centerPanel = new JPanel();
        spacer = new Box.Filler(new java.awt.Dimension(0, 60), new java.awt.Dimension(0, 60), new java.awt.Dimension(32767, 60));
        associateButton = new JButton();
        associateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        disassociateButton = new JButton();
        disassociateButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.setLayout(new java.awt.BorderLayout());

        allListsPanel.setLayout(new BoxLayout(allListsPanel, BoxLayout.PAGE_AXIS));

        allListsTitle.setHorizontalAlignment(SwingConstants.CENTER);
        allListsTitle.setText("All lists");
        allListsTitle.setVerticalAlignment(SwingConstants.TOP);
        allListsTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        allListsPanel.add(allListsTitle);
        

        allListsScrollPanel.setViewportView(allListsList);

        allListsPanel.add(allListsScrollPanel);

        mainPanel.add(allListsPanel, java.awt.BorderLayout.LINE_START);

        associatedListPanel.setLayout(new BoxLayout(associatedListPanel, BoxLayout.PAGE_AXIS));

        associatedListsTitle.setText("Associated Lists");
        associatedListsTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        associatedListPanel.add(associatedListsTitle);

        associatedListsScrollPanel.setViewportView(associatedListsList);

        associatedListPanel.add(associatedListsScrollPanel);

        mainPanel.add(associatedListPanel, java.awt.BorderLayout.EAST);

        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(spacer);

        associateButton.setText(">>");
        associateButton.setToolTipText("Associate List");
        centerPanel.add(associateButton);

        centerPanel.add(new Box.Filler(new Dimension(0,10),new Dimension(0,10),new Dimension(32767,10)));

        disassociateButton.setText("<<");
        disassociateButton.setToolTipText("Disassociate List");
        centerPanel.add(disassociateButton);

        mainPanel.add(centerPanel, java.awt.BorderLayout.CENTER);
        return mainPanel;
    }

    private void updateAssociatedListsList() {
        String[] lists;
        try {
            associatedModel.clear();
            lists = controller.getUserLists(name);
            for(String list : lists) {
                associatedModel.addElement(list);
            }
        } catch (PropException e) {
            throwError(e.getMessage());
            e.printStackTrace();
        }
        associatedListsList.setModel(associatedModel);
    }

    private void updateAllListsList() {
        allListsModel.clear();
        String[] lists = controller.getAllLists();
        for(String list : lists) {
            allListsModel.addElement(list);
        }
        allListsList.setModel(allListsModel);
        
    }

    private void setListeners() {
        associateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                associateList();
            }
        });
        
        disassociateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                disassociateList();
            }
        });
    }

    private void disassociateList() {
        String list = (String) associatedListsList.getSelectedValue();
        try {
            controller.disassociateList(name,list);
            updateAssociatedListsList();
        } catch (PropException e) {
            e.printStackTrace();
            throwError(e.getMessage());
        }
    }

    private void associateList() {
        String list = (String) allListsList.getSelectedValue();
        try {
            controller.associateList(name,list);
            updateAssociatedListsList();
        } catch (PropException e) {
            e.printStackTrace();
            throwError(e.getMessage());
        }
    }

}
