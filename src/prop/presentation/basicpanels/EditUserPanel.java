package prop.presentation.basicpanels;

import prop.PropException;
import prop.presentation.UserPController;
import prop.presentation.UserTabView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

/**
 * EditUserPanel in prop.presentation.basicpanels
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 24/05/15
 */
public class EditUserPanel extends UserPanel {
    private final JButton delete;
    private final JButton listEdit;
    private JButton save;
    private UserTabView tab;
    private String name;
    private JButton edit;
    private JButton cancelEdit;
    private UserPController controller;

    /**
     * Creates new form AddSongPanel
     *
     * @param userController
     * @param userTabView
     */
    public EditUserPanel(UserPController userController, UserTabView userTabView,String userName) {
        super(userController);
        controller = userController;
        name = userName;
        tab = userTabView;
        
        updateUserData();
        setTitleText("User detail: "+name);
        
        edit = new JButton("Edit");
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                enableEdit();
            }
        });
        addActionButton(edit);
        
        save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                saveEdition();
            }
        });
        addActionButton(save);

        delete = new JButton("Delete");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                deleteUser();
            }
        });
        addActionButton(delete);

        listEdit = new JButton("Associated Lists");
        listEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tab.showAssociatedListsPanel(name);
            }
        });
        addActionButton(listEdit);

        cancelEdit = new JButton("Cancel");
        cancelEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                disableEdit();
            }
        });
        addActionButton(cancelEdit);
        
        disableEdit();
    }

    private void deleteUser() {
        try {
            controller.deleteUser(name);
            tab.updateList();
            tab.showMainPanel();
        } catch (Exception e) {
            e.printStackTrace();
            throwError(e.getMessage());
        }
    }

    private void saveEdition() {
        String newName = getNameField();
        if(newName.equals("")){
            throwError("Missing name");
        }
        String gender = getGenderSelector();
        int day = getDaySpinner();
        int month = getMonthSpinner();
        int year = getYearSpinner();

        try {
            controller.updateUser(name, newName, gender, day, month, year);
            tab.updateList();
            tab.showMainPanel();
        } catch (Exception e) {
            e.printStackTrace();
            throwError(e.getMessage());
        }
        
    }

    private void updateUserData() {
        String user = "";
        try {
            user = controller.getUser(name);
        } catch (PropException e) {
            e.printStackTrace();
            throwError(e.getMessage());
        }
        String tokens[] = user.split(" ");
        setNameField(tokens[1]);
        setGenderSelector(tokens[2]);
        Date date = new Date(Long.valueOf(tokens[3]));
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        setBirthday(
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DATE)
        );
    }

    private void disableEdit() {
        setEnableEdit(false);
        setTitleText("User detail: "+name);
        updateUserData();
    }

    private void enableEdit() {
        setEnableEdit(true);
        setTitleText("Edit user: "+name);
    }
    
    private void setEnableEdit(boolean enable){
        enableFieldEdition(enable);
        cancelEdit.setVisible(enable);
        edit.setVisible(!enable);
        save.setVisible(enable);
    }
}
