/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prop.presentation.basicpanels;

import prop.presentation.UserPController;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

/**
 *
 * @author casassg
 */
public class UserPanel extends JPanel {

    private UserPController controller;
    private JLabel errorField;
    private JComboBox genderSelector;
    private JLabel dayLabel;
    private JLabel monthLabel;
    private JLabel nameLabel;
    private JLabel genderLabel;
    private JLabel birthdayLabel;
    private JLabel yearLabel;
    private JSeparator separator;
    private JSpinner daySpinner;
    private JSpinner monthSpinner;
    private JSpinner yearSpinner;
    private JTextField nameField;
    private JLabel titleText;
    private JPanel buttonsPanel;

    /**
     * Creates new form AddSongPanel
     */
    public UserPanel(UserPController userController) {
        controller = userController;
        initComponents();
    }

    private void initComponents() {

        titleText = new JLabel();
        separator = new JSeparator();
        nameLabel = new JLabel();
        nameField = new JTextField();
        genderLabel = new JLabel();
        birthdayLabel = new JLabel();
        genderSelector = new JComboBox();
        
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel,BoxLayout.X_AXIS));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        
        Calendar cal = Calendar.getInstance();
        SpinnerNumberModel dayModel = new SpinnerNumberModel(cal.get(Calendar.DATE),1,31,1);
        daySpinner = new JSpinner(dayModel);
        SpinnerNumberModel monthModel = new SpinnerNumberModel(cal.get(Calendar.MONTH),1,12,1);
        monthSpinner = new JSpinner(monthModel);
        
        SpinnerNumberModel yearModel = new SpinnerNumberModel(cal.get(Calendar.YEAR),1,999999999,1);
        yearSpinner = new JSpinner(yearModel);
        errorField = new JLabel();
        dayLabel = new JLabel();
        monthLabel = new JLabel();
        yearLabel = new JLabel();

        nameLabel.setFont(new Font("Noto Sans", 1, 12)); // NOI18N
        nameLabel.setText("Name");

        genderLabel.setFont(new Font("Noto Sans", 1, 12)); // NOI18N
        genderLabel.setText("Gender");

        birthdayLabel.setFont(new Font("Noto Sans", 1, 12)); // NOI18N
        birthdayLabel.setText("Birthday");

        genderSelector.setModel(new DefaultComboBoxModel(controller.getGenres()));

        errorField.setVisible(false);


        dayLabel.setFont(new Font("Noto Sans", 2, 12)); // NOI18N
        dayLabel.setText("Day");
        

        monthLabel.setFont(new Font("Noto Sans", 2, 12)); // NOI18N
        monthLabel.setText("Month");

        yearLabel.setFont(new Font("Noto Sans", 2, 12)); // NOI18N
        yearLabel.setText("Year");


        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(buttonsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(separator)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(13, 13, 13)
                                                                .addComponent(nameLabel))
                                                        .addComponent(genderLabel, GroupLayout.Alignment.TRAILING))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(nameField)
                                                        .addComponent(genderSelector, 0, 315, Short.MAX_VALUE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(12, 12, 12)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(monthLabel)
                                                                                .addComponent(yearLabel))
                                                                        .addComponent(dayLabel, GroupLayout.Alignment.TRAILING))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                                        .addComponent(monthSpinner, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                                                        .addComponent(daySpinner, GroupLayout.Alignment.LEADING)
                                                                        .addComponent(yearSpinner)))
                                                        .addComponent(titleText)
                                                        .addComponent(birthdayLabel)
                                                        .addComponent(errorField))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(titleText)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separator, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(nameField)
                                        .addComponent(nameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(genderLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(genderSelector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(birthdayLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(daySpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dayLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(monthSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(monthLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(yearSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(yearLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                                .addComponent(errorField)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
    }

    protected void throwError(String s) {
        errorField.setText(s);
        errorField.setVisible(true);
        errorField.setForeground(Color.red);
    }

    protected void setNameField(String name) {
        nameField.setText(name);
    }

    protected void setGenderSelector(String gender) {
        genderSelector.setSelectedItem(gender);
    }
    
    protected void setBirthday(int year,int month, int day) {
        daySpinner.setValue(day);
        yearSpinner.setValue(year);
        monthSpinner.setValue(month);
        
    }
    
    protected void addActionButton(JButton button) {
        buttonsPanel.add(button);
    }

    protected String getNameField() {
        return nameField.getText();
    }
    
    protected int getDaySpinner(){
        return (Integer) daySpinner.getValue();
    }

    protected int getMonthSpinner(){
        return (Integer) monthSpinner.getValue();
    }

    protected int getYearSpinner(){
        return (Integer) yearSpinner.getValue();
    }
    
    protected String getGenderSelector() {
        return (String) genderSelector.getSelectedItem();
    }
    
    private void setEdition(boolean b){
        genderSelector.setEnabled(b);
        nameField.setEnabled(b);
        yearSpinner.setEnabled(b);
        daySpinner.setEnabled(b);
        monthSpinner.setEnabled(b);
    }
    
    protected void enableEdition() {
        setEdition(true);        
    }
    
    protected void disableEdition() {
        setEdition(false);
    }
    
    protected void setTitleText(String title) {
        titleText.setText(title);
    }
}