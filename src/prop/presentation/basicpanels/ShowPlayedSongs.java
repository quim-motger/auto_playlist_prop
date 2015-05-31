package prop.presentation.basicpanels;

import prop.PropException;
import prop.presentation.UserPController;
import prop.presentation.UserTabView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

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
    private final JButton newPlay;
    private JScrollPane listWrapper;
    private DefaultListModel<String> listOfListsModel;
    private JList listOfLists;
    
    public ShowPlayedSongs(UserTabView userTabView, String userName, UserPController userPController) {
        super();
        tab = userTabView;
        name = userName;
        controller = userPController;
        
        updatePanel();

        setTitleText(name+"'s playbacks");
        
        back = new JButton("< Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tab.showUserInRightPanel(name);
            }
        });
        addButton(back);

        newPlay = new JButton("Play song");
        newPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tab.showAddPlayback(name);
            }
        });
        addButton(newPlay);

        
    }

    private void updatePanel() {

        try {
            listOfListsModel.clear();
            String[] plays = controller.getUserPlays(name);
            for(String play : plays){
                String[] tokens = play.split(Pattern.quote("|"));

                String ret = tokens[1];
                ret += "-" + tokens[2];
                ret += " " + tokens[5] + "/";
                ret += tokens[4] + "/";
                ret += tokens[3];
                ret += " ";
                ret += tokens[6]+":";
                ret += tokens[7]+":";
                ret += tokens[8];
                listOfListsModel.addElement(ret);
            }
            listOfLists.setModel(listOfListsModel);

        } catch (PropException e) {
            e.printStackTrace();
            throwError(e.getMessage());
        }
        
    }

    @Override
    protected JPanel createFormPanel() {
        JPanel panel = new JPanel();
        listWrapper = new JScrollPane();
        listOfLists = new JList();
        listOfListsModel = new DefaultListModel<String>();
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
