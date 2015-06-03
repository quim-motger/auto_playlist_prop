package prop.presentation;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
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
    private SearchSongsPanel searchSongsPanel;
    private DefaultListModel songSetModel;
    private JList songSet;
    private JTextField searchField;
    private JButton addSongButton;
    private JButton searchSongsButton;
    private JButton removeAllSongsButton;
    private JButton loadSongSet;
    private JButton saveSongSet;

    /**
     * Creates a new SongTabView
     * @param spc   the SongPController
     */
    public SongTabView(SongPController spc) {
        super();
        songPController = spc;
        addSongPanel = new AddSongPanel(songPController, this);
        searchSongsPanel = new SearchSongsPanel(songPController, this);
        initListComponents();
    }

    @Override
    /**
     * creates an emptyPanel in the rightPanel of the SongTabView
     */
    protected JPanel createRightPanel() {
        return emptyPanel;
    }

    @Override
    /**
     * Set the action bar buttons of the TabView
     */
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
                setRightPanel(emptyPanel);
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

    /**
     * creates a new ShowSongPanel
     * @param a     the title of the song
     * @param b     the artist of the song
     */
    public void setShowSongPanel(String a, String b) {
        setRightPanel(new ShowSongPanel(songPController, this, a, b));
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

    /**
     * Updates the song List
     */
    public void updateSongSetModel() {
        songSet.setPrototypeCellValue("title" + Short.MAX_VALUE);
        songSet.setModel(new AbstractListModel() {
            @Override
            public int getSize() {
                return getSongString().length;
            }

            @Override
            public Object getElementAt(int i) {
                return getSongString()[i];
            }
        });
    }

    private String[] getSongString() {
        String songs;
        String prefix = getSearchField().getText();
        if (prefix.equals("")) {
            songs = songPController.findSongs();
        }
        else songs = songPController.findSongsByName(prefix);
        return songs.split(Pattern.quote("\n"));
    }

    /**
     * creates a new ShowSongPanel
     * @param title     the title of the song
     * @param artist    the artist of the song
     */
    public void showSong(String title, String artist) {
        setShowSongPanel(title, artist);
    }

}

