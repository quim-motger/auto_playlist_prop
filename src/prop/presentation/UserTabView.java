package prop.presentation;

import prop.presentation.basicelements.ActionBarButton;
import prop.presentation.basicpanels.AddUserPanel;
import prop.presentation.basicpanels.EditUserPanel;
import prop.presentation.basicpanels.UserPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * UserTabView in prop.presentation
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 22/05/15
 */
public class UserTabView extends TabView {

    private UserPController mController;
    private DefaultListModel userListModel;
    private JList leftList;

    public UserTabView( UserPController controller) {
        super();
        mController = controller;
        
        initUserTabComponents();
    }

    private void initUserTabComponents() {
        userListModel = new DefaultListModel();
        leftList = getLeftList();
        leftList.setModel(userListModel);
        leftList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                if (!leftList.isSelectionEmpty()){
                    String value = (String) leftList.getSelectedValue();
                    showUserInRightPanel(value);
                }
            }
        });
        
        getSearchField().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateList();
            }
        });
        getSearchField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                updateList();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                updateList();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                updateList();
            }
        });
        
        
        updateList();
    }

    private void showUserInRightPanel(String value) {
        setRightPanel(new EditUserPanel(mController,this,value));
    }

    public void updateList() {
        userListModel.clear();
        ArrayList<String> users;
        String search = getSearchField().getText();
        
        if(search.equals("")) {
            users = mController.getUsers();
        } else {
            users = mController.getMatch(search);
        }
        
        for(String user : users) {
            userListModel.addElement(user);
        }
    }

    @Override
    protected JPanel createRightPanel() {
        return new JPanel();
    }

    @Override
    protected ArrayList<JButton> setActionBarButtons() {
        ArrayList<JButton> buttons = new ArrayList<>();
        
        ActionBarButton addUser = new ActionBarButton("Add User");
        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                showAddUserInRightPanel();
            }
        });
        buttons.add(addUser);
        
        return buttons;
    }

    private void showAddUserInRightPanel() {
        UserPanel u = new AddUserPanel(mController, this);
        setRightPanel(u);
    }
    
    public void showMainPanel() {
        setRightPanel(new JPanel());
    }

}
