package prop.presentation.generator;

import prop.ErrorString;
import prop.domain.Pair;
import prop.presentation.UserPController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * AddUser in prop.presentation_v2.generator
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 14/06/15
 */
public class UserForm extends FormGen {

    private final UserPController controller;
    private JComboBox genderSelector;
    private JTextField nameField;
    private JSpinner daySpinner;
    private JSpinner monthSpinner;
    private JSpinner yearSpinner;

    public UserForm(UserPController userController) {
        controller = userController;
        updatePanel();
        nameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                checkIllegal();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                checkIllegal();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                checkIllegal();
            }
        });

    }

    protected boolean checkIllegal() {
        if (nameField.getText().contains("|")) {
            throwError(ErrorString.ILLEGAL_CHARACTER);
            return true;
        }
        return false;
    }

    protected void updatePanel() {
        genderSelector.setModel(new DefaultComboBoxModel(controller.getGenres()));
    }

    @Override
    protected ArrayList<Pair<String, JComponent>> setFields() {
        /**TEXT FIELD**/
        ArrayList<Pair<String, JComponent>> fields = new ArrayList<>();
        nameField = new JTextField();
        genderSelector = new JComboBox();

        /**BIRTHDAY FIELDS**/
        daySpinner = new JSpinner();
        monthSpinner = new JSpinner();
        yearSpinner = new JSpinner();

        /**SETUP BIRTHDAY MODELS**/
        Calendar cal = Calendar.getInstance();
        SpinnerNumberModel dayModel = new SpinnerNumberModel(cal.get(Calendar.DATE), 1, 31, 1);
        daySpinner = new JSpinner(dayModel);
        SpinnerNumberModel monthModel = new SpinnerNumberModel(cal.get(Calendar.MONTH), 1, 12, 1);
        monthSpinner = new JSpinner(monthModel);
        SpinnerNumberModel yearModel = new SpinnerNumberModel(cal.get(Calendar.YEAR), 1, 999999999, 1);
        yearSpinner = new JSpinner(yearModel);

        /**Adding to UI**/
        fields.add(new Pair<String, JComponent>("Name", nameField));
        fields.add(new Pair<String, JComponent>("Gender", genderSelector));
        fields.add(new Pair<String, JComponent>("Birthday", new JLabel()));
        fields.add(new Pair<String, JComponent>("Day", daySpinner));
        fields.add(new Pair<String, JComponent>("Month", monthSpinner));
        fields.add(new Pair<String, JComponent>("Year", yearSpinner));
        return fields;
    }

    /**
     * Set birthday date
     *
     * @param year
     * @param month
     * @param day
     */
    protected void setBirthday(int year, int month, int day) {
        daySpinner.setValue(day);
        yearSpinner.setValue(year);
        monthSpinner.setValue(month);

    }

    /**
     * get name
     *
     * @return
     */
    protected String getNameField() {
        return nameField.getText();
    }

    /**
     * Sets name
     *
     * @param name
     */
    protected void setNameField(String name) {
        nameField.setText(name);
    }

    /**
     * get day
     *
     * @return
     */
    protected int getDaySpinner() {
        return (Integer) daySpinner.getValue();
    }

    /**
     * get month
     *
     * @return
     */
    protected int getMonthSpinner() {
        return (Integer) monthSpinner.getValue();
    }

    protected int getYearSpinner() {
        return (Integer) yearSpinner.getValue();
    }

    /**
     * get gender
     *
     * @return
     */
    protected String getGenderSelector() {
        return (String) genderSelector.getSelectedItem();
    }

    /**
     * set gender
     *
     * @param gender
     */
    protected void setGenderSelector(String gender) {
        genderSelector.setSelectedItem(gender);
    }

    /**
     * enable field edition
     *
     * @param enable if true -> edition is enabled
     */
    protected void enableFieldEdition(boolean enable) {
        genderSelector.setEditable(enable);
        genderSelector.setEnabled(enable);
        nameField.setEditable(enable);
        yearSpinner.setEnabled(enable);
        daySpinner.setEnabled(enable);
        monthSpinner.setEnabled(enable);
    }
}
