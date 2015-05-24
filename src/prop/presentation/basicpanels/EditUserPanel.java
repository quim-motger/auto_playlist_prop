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
    private UserTabView tab;
    private String name;
    private JButton cancel;
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
        
        updatePanel();
        setTitleText("Show user: "+name);
        
        edit = new JButton("Edit");
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                enableEdit();
            }
        });
        addActionButton(edit);
        
        cancelEdit = new JButton("Cancel");
        cancelEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                disableEdit();
            }
        });
        cancelEdit.setVisible(false);
        addActionButton(cancelEdit);
        
        cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tab.showMainPanel();
            }
        });
        addActionButton(cancel);
        
        disableEdit();
    }

    private void updatePanel() {
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
        disableEdition();
        edit.setVisible(true);
        cancel.setVisible(true);
        cancelEdit.setVisible(false);
        updatePanel();
    }

    private void enableEdit() {
        enableEdition();
        cancel.setVisible(false);
        cancelEdit.setVisible(true);
        edit.setVisible(false);
        setTitleText("Edit user: "+name);
    }
}
