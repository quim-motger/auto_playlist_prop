package prop.presentation.basicpanels;

import prop.ErrorString;
import prop.presentation.UserPController;
import prop.presentation.UserTabView;
import prop.presentation.generator.UserForm;

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
public class AddUserPanel extends UserForm {

    private final UserTabView tab;
    private UserPController controller;
    private JButton add;

    /**
     * Creates new form AddUserPanel
     */
    public AddUserPanel(UserPController userController, UserTabView userTabView) {
        super(userController);
        controller = userController;
        tab = userTabView;
        
        updatePanel();
        
        setTitleText("Add new User");
        
        add = new JButton("Add");
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                submitAction(evt);
            }
        });
        addButton(add);
        
        
        
        enableFieldEdition(true);
    }

    private void submitAction(ActionEvent evt) {
        String name = getNameField();
        if(name.equals("")){
            throwError(ErrorString.MISSING_NAME);
            return;
        }
        if (checkIllegal()) {
            throwError(ErrorString.NAME_INCORRECT_FORMAT);
            return;
        }
        String gender = getGenderSelector();
        int day = getDaySpinner();
        int month = getMonthSpinner();
        int year = getYearSpinner();

        try {
            controller.createNewUser(name,gender,day,month,year);
            tab.updateList();
            tab.showAddUserInRightPanel();
        } catch (Exception e) {
            e.printStackTrace();
            throwError(e.getMessage());
        }
        
    }
}
