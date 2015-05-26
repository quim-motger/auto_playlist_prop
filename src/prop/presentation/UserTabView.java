package prop.presentation;

import prop.ErrorString;
import prop.presentation.basicelements.ActionBarButton;
import prop.presentation.basicpanels.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * UserTabView in prop.presentation
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 22/05/15
 */
public class UserTabView extends TabView {

    private final MainView mView;
    private UserPController mController;
    private DefaultListModel userListModel;
    private JList leftList;

    public UserTabView( UserPController controller, MainView mainView) {
        super();
        mController = controller;
        mView = mainView;
        
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

    public void showUserInRightPanel(String value) {
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
        
        ActionBarButton save = new ActionBarButton("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                showSavePanel();
            }
        });
        buttons.add(save);
        
        ActionBarButton load = new ActionBarButton("Load");
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                showLoadPanel();
            }
        });
        buttons.add(load);
        
        return buttons;
    }

    private void showLoadPanel() {
        JFileChooser c = new JFileChooser(){
            @Override
            public void approveSelection() {
                String file = getSelectedFile().getName();
                String dir = getCurrentDirectory().toString();
                try {
                    mController.load(dir + "/" + file);
                    updateList();
                    super.approveSelection();
                } catch (Exception e) {
                    e.printStackTrace();
                    if(e.getMessage().equals(ErrorString.INCORRECT_FORMAT)) {
                        JOptionPane.showMessageDialog(this, ErrorString.CORRUPT_FILE);
                    } else {
                        JOptionPane.showMessageDialog(this, ErrorString.LOAD_OTHERS);
                    }
                }
            }
        };
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".users", "users");
        c.setFileFilter(filter);
        // Demonstrate "Open" dialog:
        c.showOpenDialog(this);
    }

    private void showErrorPanel() {

    }

    private void showSavePanel() {
        JFileChooser c = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".users", "users");
        c.setFileFilter(filter);
        // Demonstrate "Open" dialog:
        int rVal = c.showSaveDialog(this);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            String file = c.getSelectedFile().getName();
            String dir = c.getCurrentDirectory().toString();
            try {
                String path = dir+"/"+file+".users";
                if(file.endsWith(".users"))
                    path=dir+"/"+file;
                mController.save(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void showAddUserInRightPanel() {
        UserPanel u = new AddUserPanel(mController, this);
        setRightPanel(u);
    }
    
    public void showMainPanel() {
        setRightPanel(new JPanel());
    }

    public void showAssociatedListsPanel(String name) {
        setRightPanel(new ShowAssociatedLists(mController,name,this));
    }

    public void editAssociatedLists(String name) {
        setRightPanel(new AddNewAssociatedList(name,mController,this));
    }

    public void showList(String list) {
        mView.showList(list);
    }
}
