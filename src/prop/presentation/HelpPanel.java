package prop.presentation;

import prop.presentation.basicpanels.PropPanel;

import javax.swing.*;

/**
 * HelpPanel in prop.presentation.basicelements
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 03/06/15
 */
public class HelpPanel extends PropPanel{
    
    public HelpPanel() {
        super();
        setTitleText("Help Text");
    }
    
    @Override
    protected JPanel createFormPanel() {
        JPanel mainPanel = new JPanel();
        JScrollPane scroll = new JScrollPane();
        JTextArea textArea = new JTextArea();

        textArea.setColumns(20);
        textArea.setRows(5);
        scroll.setViewportView(textArea);
        
        textArea.setText(HELP_TXT);

        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scroll, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scroll, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                                .addContainerGap())
        );
        return mainPanel;
    }

    private static final String HELP_TXT = "YouTubeMix\n" +
            "\n" +
            "How to save users, songs and lists:\n" +
            "To save everything go to File->Save. The state of the program will be saved in .prop format. \n" +
            "To save only Users go to Users tab and click \"Save\" at the top bar. It will be saved in .users format.\n" +
            "To save only Songs go to Songs tab and click \"Save\" at the top bar. It will be saved in .songs format.\n" +
            "To save only Lists go to Lists tab and click \"Save\" at the top bar. It will be saved in .lists format.\n" +
            "\n" +
            "Users tab:\n" +
            "\n" +
            "How to create a user:\n" +
            "Click \"Add User\" at the top bar and fill the form. Note that the username must be unique. \n" +
            "\n" +
            "How to associate lists to a user:\n" +
            "Select a user of the list in the left and click \"Associated Lists\". Then click \"edit\" and select the lists to associate or disassociate.\n" +
            "\n" +
            "How to play a song:\n" +
            "Select a user of the list in the left and click \"Played\". Then click \"Play song\" and select the song to play.\n" +
            "\n" +
            "Songs tab:\n" +
            "\n" +
            "How to add a new song:\n" +
            "Click \"Add Song\" at the top bar and fill the form. Note that the combination Title - Artist must be unique. \n" +
            "\n" +
            "How to search songs:\n" +
            "Click \"Search Songs\" at the top bar. Click the checkboxes of the fields you want the program to consider when performing the search. Fiill the selected fields and click \"Search\".\n" +
            "\n" +
            "Lists tab:\n" +
            "How to change the order of songs in a list:\n" +
            "Select a list. CTRL+Click two songs of the list and click \"Swap Songs\".\n" +
            "\n" +
            "Algorithms tab:\n" +
            "To generate a list first you have to enter the relations. \n" +
            "\n" +
            "The graph represents all the songs and the selected community (in green).";
}
