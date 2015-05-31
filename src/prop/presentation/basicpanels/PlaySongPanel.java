package prop.presentation.basicpanels;

import prop.PropException;
import prop.presentation.UserPController;
import prop.presentation.UserTabView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


/**
 * AddPlaybackPanel in prop.presentation.basicpanels
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 29/05/15
 */
public class PlaySongPanel extends PropPanel {

    private final UserPController controller;
    private final JButton back;
    private final UserTabView tab;
    private String name;
    private JButton create;
    private JLabel artistLabel;
    private JLabel titleLabel;
    private JLabel dateLabel;
    private JSpinner dateSpinner;
    private JComboBox artistDropDown;
    private JComboBox titleDropDown;
    private DefaultComboBoxModel<Object> modelTitle;


    public PlaySongPanel(String userName, UserPController userPController, UserTabView userTabView) {
        super();
        tab = userTabView;
        modelTitle = new DefaultComboBoxModel<>();
        artistDropDown.setModel(modelTitle);
        controller = userPController;
        name = userName;
        setTitleText("Play song for "+name);
        
        create = new JButton("Create");
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addNewPlayback();
            }
        });

        back = new JButton("< Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tab.showPlays(name);
            }
        });
        
        artistDropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateTitles();
            }
        });
        
        addButton(back);
        addButton(create);
        updateArtists();
    }

    private void updateTitles() {
        titleDropDown.removeAllItems();
        String artist = (String) artistDropDown.getSelectedItem();
        try {
            ArrayList<String> titles = controller.getArtistSongs(artist);
            DefaultComboBoxModel titleModel = new DefaultComboBoxModel(titles.toArray());
            titleDropDown.setModel(titleModel);
        } catch (PropException e) {
            e.printStackTrace();
            throwError(e.getMessage());
        }
    }

    private void updateArtists() {
        artistDropDown.removeAllItems();
        DefaultComboBoxModel artistModel = (DefaultComboBoxModel) artistDropDown.getModel();
        Set<String> artists = new LinkedHashSet<>(controller.getArtists());
        for(String artist : artists) {
            artistModel.addElement(artist);
        }
        
        updateTitles();
        showInfo("Select artist to see song titles of the artist");
    }

    private void addNewPlayback() {
        String artist = (String) artistDropDown.getSelectedItem();
        String title = (String) titleDropDown.getSelectedItem();
        
        

        Date d = (Date) dateSpinner.getValue();
        if (isStringValid("Title", title) && isStringValid("Artist", artist)) {
            try {
                controller.playNewSong(title, artist, name, d);
            } catch (Exception e) {
                e.printStackTrace();
                throwError(e.getMessage());
            }
            tab.showPlays(name);
        }
    }


    @Override
    protected JPanel createFormPanel() {
        JPanel panel = new JPanel();
        artistLabel = new JLabel();
        titleLabel = new JLabel();
        dateLabel = new JLabel();
        dateSpinner = new JSpinner();
        artistDropDown = new JComboBox();
        titleDropDown = new JComboBox();
        

        artistLabel.setText("Artist:");

        titleLabel.setText("Title:");

        dateLabel.setText("Day:");

        dateSpinner.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE));
        
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(artistLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(artistDropDown, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(titleLabel)
                                                        .addComponent(dateLabel))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(dateSpinner, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                                                        .addComponent(titleDropDown, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(artistLabel)
                                        .addComponent(artistDropDown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(titleLabel)
                                        .addComponent(titleDropDown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(dateSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dateLabel))
                                .addContainerGap(194, Short.MAX_VALUE))
        );
        return panel;
    }
}
