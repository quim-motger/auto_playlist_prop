/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prop.presentation.basicpanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author casassg
 */
public abstract class PropPanel extends JPanel {

    private JPanel buttonPanel;
    private JLabel errorText;
    private JPanel form;
    private JSeparator separator;
    private JLabel titleField;
    private JLabel fileLabel;
    private JTextField path;
    private Timer timer;

    /**
     * Creates new form PropPanel
     */
    public PropPanel() {
        initComponents();
    }
    
    private void initComponents() {

        titleField = new JLabel();
        separator = new JSeparator();
        buttonPanel = new JPanel();
        errorText = new JLabel();
        errorText.setVisible(false);
        form = createFormPanel();


        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(form, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(separator, GroupLayout.Alignment.LEADING)
                                        .addComponent(buttonPanel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(titleField)
                                                        .addComponent(errorText))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(titleField)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separator, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(form, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(errorText)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
    }

    /**
     * Creates the form Panel that will be shown in the middle of the screen
     */
    protected abstract JPanel createFormPanel();

    /**
     * Sets title text 
     * @param title String to be shown
     */
    protected void setTitleText(String title){
        titleField.setText(title);
    }
    
    /**
     * Shows dialog on the bottom in red during 3000 ms
     * @param error
     */
    protected void throwError(String error) {
        errorText.setText(error);
        errorText.setVisible(true);
        errorText.setForeground(Color.red);
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                errorText.setVisible(false);
            }
        };
        if(timer!=null)
            timer.stop();
        else {
            timer = new Timer(3000, listener);
        }
        timer.start();
    }

    /**
     * Shows dialog on the bottom in black 
     * @param info String to be shown
     */
    protected void showInfo(String info) {
        errorText.setText(info);
        errorText.setVisible(true);
        errorText.setForeground(Color.black);
        
    }

    /**
     * Adds button to the ActionBar 
     * @param button button to be added
     */
    public void addButton(JButton button) {
        buttonPanel.add(button);
        buttonPanel.add(new Box.Filler(new Dimension(10,0), new Dimension(10,0), new java.awt.Dimension(10,32767)));
    }


    /**
     * Checks if a string from a form is a valid input
     * @param parameter label of the form input
     * @param s input
     * @return true if s is valid input
     */
    protected boolean isStringValid(String parameter, String s) {
        if(s==null || s.equals("")) {
            throwError(parameter+ " is empty");
            return false;
        }
        return true;
    }
}
