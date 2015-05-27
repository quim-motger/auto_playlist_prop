package prop.presentation;

import prop.ErrorString;
import prop.PropException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by quim_motger on 24/05/15.
 */
public class SongTabView extends TabView{

    private SongPController songPController;
    private JPanel emptyPanel;

    private AddSongPanel addSongPanel;
    private SearchSongsPanel searchSongsPanel;
    private DefaultListModel songSetModel;
    private JList songSet;
    private JTextField searchField;
    private JButton addSongButton;
    private JButton searchSongsButton;
    private JButton removeAllSongsButton;
    private JButton loadSongSet;
    private JButton saveSongSet;

    public SongTabView(SongPController spc) {
        super();
        songPController = spc;
        addSongPanel = new AddSongPanel(songPController, this);
        searchSongsPanel = new SearchSongsPanel(songPController, this);
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

        searchSongsButton = new JButton("Search Songs");
        searchSongsButton.setBorder(BorderFactory.createEmptyBorder(10, 3, 10, 3));
        searchSongsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setRightPanel(searchSongsPanel);
            }
        });
        buttons.add(searchSongsButton);

        removeAllSongsButton = new JButton("Remove all Songs");
        removeAllSongsButton.setBorder(BorderFactory.createEmptyBorder(10, 3, 10, 3));
        removeAllSongsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                songPController.removeAllSongs();
                updateSongSetModel();
            }
        });
        buttons.add(removeAllSongsButton);

        saveSongSet = new JButton("Save");
        saveSongSet.setBorder(BorderFactory.createEmptyBorder(10, 3, 10, 3));
        saveSongSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                showSavePanel();
            }
        });
        buttons.add(saveSongSet);

        loadSongSet = new JButton("Load");
        loadSongSet.setBorder(BorderFactory.createEmptyBorder(10,3,10,3));
        loadSongSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                showLoadPanel();
            }
        });
        buttons.add(loadSongSet);

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
                    setShowSongPanel(attr[0],attr[1]);
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

    public void setShowSongPanel(String a, String b) {
        setRightPanel(new ShowSongPanel(songPController,this,a,b));
    }

    private void showLoadPanel() {
        JFileChooser c = new JFileChooser(){
            @Override
            public void approveSelection() {
                String file = getSelectedFile().getName();
                String dir = getCurrentDirectory().toString();
                try {
                    songPController.load(dir + "/" + file);
                    updateSongSetModel();
                    setRightPanel(emptyPanel);
                    super.approveSelection();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
            }
        };
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".songs", "songs");
        c.setFileFilter(filter);
        // Demonstrate "Open" dialog:
        c.showOpenDialog(this);
    }

    private void showSavePanel() {
        JFileChooser c = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".songs", "songs");
        c.setFileFilter(filter);
        // Demonstrate "Open" dialog:
        int rVal = c.showSaveDialog(this);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            String file = c.getSelectedFile().getName();
            String dir = c.getCurrentDirectory().toString();
            try {
                String path = dir+"/"+file+".songs";
                if(file.endsWith(".songs"))
                    path=dir+"/"+file;
                songPController.save(path);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
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

