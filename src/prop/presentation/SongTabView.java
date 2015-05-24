package prop.presentation;

import prop.PropException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by quim_motger on 24/05/15.
 */
public class SongTabView extends TabView{

    private SongPController songPController;
    private JPanel emptyPanel;

    private AddSongPanel addSongPanel;
    private ShowSongPanel showSongPanel;
    private DefaultListModel songSetModel;
    private JList songSet;
    private JTextField searchField;
    private JButton addSongButton;
    private JButton removeSongButton;
    private JButton editSongButton;

    public SongTabView(SongPController spc) {
        super();
        songPController = spc;
        addSongPanel = new AddSongPanel(songPController, this);
        initListComponents();
    }

    @Override
    protected JPanel createRightPanel() {
        return emptyPanel;
    }

    @Override
    protected ArrayList<JButton> setActionBarButtons() {
        ArrayList<JButton> buttons = new ArrayList<>();

        addSongButton = new JButton("Add Song");
        addSongButton.setBorder(BorderFactory.createEmptyBorder(10, 3, 10, 3));
        addSongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setRightPanel(addSongPanel);
            }
        });
        buttons.add(addSongButton);

        removeSongButton = new JButton("Remove Song");
        removeSongButton.setBorder(BorderFactory.createEmptyBorder(10, 3, 10, 3));
        removeSongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!songSet.isSelectionEmpty()) {
                    String value = (String) songSet.getSelectedValue();
                    String[] attr = value.split(Pattern.quote(" - "));
                    try {
                        songPController.removeSong(attr[0], attr[1]);
                        setRightPanel(emptyPanel);
                        updateSongSetModel();
                    } catch (PropException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        buttons.add(removeSongButton);

        return buttons;
    }

    private void initListComponents() {
        emptyPanel = new JPanel();
        songSetModel = new DefaultListModel();
        songSet = getLeftList();
        songSet.setModel(songSetModel);
        songSet.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!songSet.isSelectionEmpty()) {
                    String value = (String) songSet.getSelectedValue();
                    String[] attr = value.split(Pattern.quote(" - "));
                    showSongPanel = new ShowSongPanel(songPController,attr[0],attr[1]);
                    setRightPanel(showSongPanel);
                }
            }
        });

        searchField = getSearchField();
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSongSetModel();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSongSetModel();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSongSetModel();
            }
        });
    }

    public void updateSongSetModel() {
        songSetModel.clear();
        String songs;
        String prefix = searchField.getText();
        if (prefix.equals("")) {
            songs = songPController.findSongs();
        }
        else songs = songPController.findSongsByName(prefix);
        String[] listSongs = songs.split(Pattern.quote("\n"));
        for (String s : listSongs) {
            songSetModel.addElement(s);
        }
    }

}

