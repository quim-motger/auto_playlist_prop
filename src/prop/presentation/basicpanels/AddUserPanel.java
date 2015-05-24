package prop.presentation.basicpanels;

import prop.presentation.UserPController;
import prop.presentation.UserTabView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * AddUserPanel in prop.presentation.basicpanels
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 24/05/15
 */
public class AddUserPanel  extends UserPanel {

    private final UserTabView tab;
    private UserPController controller;
    private JButton add;

    /**
     * Creates new form AddSongPanel
     *
     * @param userController
     * @param userTabView
     */
    public AddUserPanel(UserPController userController, UserTabView userTabView) {
        super(userController);
        controller = userController;
        tab = userTabView;
        
        setTitleText("Add new User");
        
        add = new JButton("Add");
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                submitAction(evt);
            }
        });
        addActionButton(add);
    }

    private void submitAction(ActionEvent evt) {
        String name = getNameField();
        if(name.equals("")){
            throwError("Missing name");
        }
        String gender = getGenderSelector();
        int day = getDaySpinner();
        int month = getMonthSpinner();
        int year = getYearSpinner();

        try {
            controller.createNewUser(name,gender,day,month,year);
        } catch (Exception e) {
            e.printStackTrace();
            throwError(e.getMessage());
        }
        tab.updateList();
        tab.showMainPanel();
    }
}
