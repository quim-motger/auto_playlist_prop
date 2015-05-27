package prop.presentation.basicpanels;

import prop.PropException;
import prop.presentation.UserPController;
import prop.presentation.UserTabView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ShowPlayedSongs in prop.presentation.basicpanels
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 26/05/15
 */
public class ShowPlayedSongs extends PropPanel {


    private final UserTabView tab;
    private final JButton back;
    private final String name;
    private final UserPController controller;
    private JScrollPane listWrapper;
    private DefaultTableModel listOfListsModel;
    private JTable listOfLists;
    
    public ShowPlayedSongs(UserTabView userTabView, String userName, UserPController userPController) {
        super();
        tab = userTabView;
        name = userName;
        controller = userPController;
        
        updatePanel();
        
        back = new JButton("< Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tab.showUserInRightPanel(name);
            }
        });
        addButton(back);
        
        
    }

    private void updatePanel() {

        try {
            listOfListsModel.setRowCount(0);
            String[] plays = controller.getUserPlays(name);
            String[] titles = new String[plays.length];
            String[] artists = new String[plays.length];
            String[] date = new String[plays.length];
            for(int i=0; i<plays.length; ++i){
                String[] tokens = plays[i].split(" ");
                titles[i] = tokens[1];
                artists[i] = tokens[2];
                date[i] = tokens[5] + "/";
                date[i] += tokens[4] + "/";
                date[i] += tokens[3];
                date[i] += " ";
                date[i] += tokens[6]+":";
                date[i] += tokens[7]+":";
                date[i] += tokens[8];
            }
            listOfListsModel.addColumn("Title",titles);
            listOfListsModel.addColumn("Artist",artists);
            listOfListsModel.addColumn("Date",date);

        } catch (PropException e) {
            e.printStackTrace();
            throwError(e.getMessage());
        }
        
    }

    @Override
    protected JPanel createFormPanel() {
        JPanel panel = new JPanel();
        listWrapper = new JScrollPane();
        listOfLists = new JTable();
        listOfListsModel = new DefaultTableModel();
        listWrapper.setViewportView(listOfLists);

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(listWrapper, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(listWrapper, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                                .addContainerGap())
        );
        return panel;
    }
}
