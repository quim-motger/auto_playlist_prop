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
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * @author oscar.manas
 */
public class ListTabView extends TabView {

    private ListPController listPController;
    private SongTabView songTabView;
    private JTabbedPane tabbedPane;
    private JPanel emptyPanel;
    private AddList addListPanel;
    private ShowList showListPanel;
    private AddRandomList addRandomList;
    private JList listSet;
    private DefaultListModel listSetModel;
    private JTextField searchField;
    private JButton addListButton;
    private JButton removeListButton;
    private JButton removeAllButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton randomButton;
    private JButton editButton;

    public ListTabView(ListPController lpc, SongTabView stv, JTabbedPane tp) {
        super();
        listPController = lpc;
        songTabView = stv;
        tabbedPane = tp;
        initListComponents();
    }

    private void initListComponents() {
        emptyPanel = new JPanel();
        listSetModel = new DefaultListModel();
        listSet = getLeftList();
        listSet.setModel(listSetModel);
        listSet.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!listSet.isSelectionEmpty()) {
                    String value = (String) listSet.getSelectedValue();
                    showListPanel = new ShowList(value);
                    setRightPanel(showListPanel);
                }
                else
                    setRightPanel(emptyPanel);
            }
        });

        searchField = getSearchField();
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateListSetModel();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateListSetModel();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateListSetModel();
            }
        });

        addListPanel = new AddList();
    }

    @Override
    protected JPanel createRightPanel() {
        return emptyPanel;
    }

    @Override
    protected ArrayList<JButton> setActionBarButtons() {
        ArrayList<JButton> buttons = new ArrayList<JButton>();

        addListButton = new JButton("Add List");
        addListButton.setBorder(BorderFactory.createEmptyBorder(10, 3, 10, 3));
        addListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setRightPanel(addListPanel);
            }
        });
        buttons.add(addListButton);

        removeListButton = new JButton("Remove List");
        removeListButton.setBorder(BorderFactory.createEmptyBorder(10, 3, 10, 3));
        removeListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeListButtonActionPerformed(e);
            }
        });
        buttons.add(removeListButton);

        removeAllButton = new JButton("Remove All");
        removeAllButton.setBorder(BorderFactory.createEmptyBorder(10, 3, 10, 3));
        removeAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAllActionPerformed(e);
            }
        });
        buttons.add(removeAllButton);

        editButton = new JButton("Edit List");
        editButton.setBorder(BorderFactory.createEmptyBorder(10, 3, 10, 3));
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editButtonActionPerformed(e);
            }
        });
        buttons.add(editButton);

        randomButton = new JButton("Random List");
        randomButton.setBorder(BorderFactory.createEmptyBorder(10, 3, 10, 3));
        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randomButtonActionPerformed(e);
            }
        });
        buttons.add(randomButton);

        saveButton = new JButton("Save");
        saveButton.setBorder(BorderFactory.createEmptyBorder(10, 3, 10, 3));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveButtonActionPerformed(e);
            }
        });
        buttons.add(saveButton);

        loadButton = new JButton("Load");
        loadButton.setBorder(BorderFactory.createEmptyBorder(10, 3, 10, 3));
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadButtonActionPerformed(e);
            }
        });
        buttons.add(loadButton);

        return buttons;
    }

    private void removeListButtonActionPerformed(ActionEvent evt) {
        if (!listSet.isSelectionEmpty()) {
            String value = (String) listSet.getSelectedValue();
            try {
                listPController.removeList(value);
                setRightPanel(emptyPanel);
                updateListSetModel();
            }
            catch (PropException e) {
                e.printStackTrace();
            }
        }
    }

    private void removeAllActionPerformed(ActionEvent evt) {
        listPController.removeAll();
        updateListSetModel();
    }

    private void saveButtonActionPerformed(ActionEvent evt) {
        JFileChooser c = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".lists", "lists");
        c.setFileFilter(filter);
        // Demonstrate "Open" dialog:
        int rVal = c.showSaveDialog(this);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            String file = c.getSelectedFile().getName();
            String dir = c.getCurrentDirectory().toString();
            try {
                String path = dir+"/"+file+".lists";
                if(file.endsWith(".lists"))
                    path=dir+"/"+file;
                listPController.save(path);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }

    private void loadButtonActionPerformed(ActionEvent evt) {
        JFileChooser c = new JFileChooser(){
            @Override
            public void approveSelection() {
                String file = getSelectedFile().getName();
                String dir = getCurrentDirectory().toString();
                try {
                    listPController.load(dir + "/" + file);
                    updateListSetModel();
                    super.approveSelection();
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
            }
        };
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".lists", "lists");
        c.setFileFilter(filter);
        // Demonstrate "Open" dialog:
        c.showOpenDialog(this);
    }

    private void randomButtonActionPerformed(ActionEvent evt) {
        addRandomList = new AddRandomList();
        setRightPanel(addRandomList);
    }

    private void editButtonActionPerformed(ActionEvent evt) {
        if (!listSet.isSelectionEmpty()) {
            setRightPanel(new EditList());
        }
    }

    public void updateListSetModel() {
        listSetModel.clear();
        String lists[];
        String prefix = searchField.getText();
        if (prefix.equals(""))
            lists = listPController.getListSetStringArray();
        else
            lists = listPController.findLists(prefix);
        for (String s : lists) {
            listSetModel.addElement(s);
        }
    }

    public void showList(String list) {
        setRightPanel(new ShowList(list));
    }

    private class AddList extends JPanel {

        /**
         * Creates new form addListPanel
         */
        public AddList() {
            initComponents();
        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">
        protected void initComponents() {

            jLabel1 = new JLabel();
            jSeparator1 = new JSeparator();
            jLabel2 = new JLabel();

            jTextField1 = new JTextField();
            jTextField1.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (e.getKeyChar() == '|')
                        e.consume();
                }

                @Override
                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });

            addButton = new JButton();
            jLabel3 = new JLabel();

            jLabel1.setText("Add new list");

            jLabel2.setText("Title: ");

            addButton.setText("Add");
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addButtonActionPerformed(e);
                }
            });

            jLabel3.setText("Error: ");
            jLabel3.setForeground(Color.RED);
            jLabel3.setVisible(false);

            GroupLayout layout = new GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(jSeparator1)
                                            .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel1)
                                                    .addGap(0, 319, Short.MAX_VALUE))
                                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addGap(0, 0, Short.MAX_VALUE)
                                                    .addComponent(addButton))
                                            .addGroup(layout.createSequentialGroup()
                                                    .addGap(10, 10, 10)
                                                    .addComponent(jLabel2)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                            .addGroup(layout.createSequentialGroup()
                                                                    .addComponent(jLabel3)
                                                                    .addGap(0, 100, Short.MAX_VALUE))
                                                            .addComponent(jTextField1))))
                                    .addContainerGap())
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel2)
                                            .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addComponent(addButton)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 167, Short.MAX_VALUE)
                                    .addComponent(jLabel3)
                                    .addContainerGap())
            );
        }// </editor-fold>

        protected void addButtonActionPerformed(ActionEvent evt) {
            String title = jTextField1.getText();
            try {
                if (title.equals(""))
                    throw new PropException(ErrorString.MISSING_TITLE);
                listPController.addList(title);
                jTextField1.setText("");
                updateListSetModel();
                setRightPanel(addListPanel);
            }
            catch (PropException e) {
                jLabel3.setText(e.getMessage());
                jLabel3.setVisible(true);
                ActionListener listener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jLabel3.setVisible(false);
                    }
                };
                Timer timer = new Timer(3000, listener);
                timer.start();
            }
        }

        // Variables declaration - do not modify
        protected JButton addButton;
        protected JLabel jLabel1;
        private JLabel jLabel2;
        protected JLabel jLabel3;
        private JSeparator jSeparator1;
        protected JTextField jTextField1;
        // End of variables declaration
    }

    private class EditList extends AddList {

        protected void initComponents() {
            super.initComponents();
            jLabel1.setText("Edit " + (String) listSet.getSelectedValue());
            addButton.setText("Save");
            jTextField1.setText((String)listSet.getSelectedValue());
            jTextField1.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    setText(jTextField1.getText());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    setText(jTextField1.getText());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    setText(jTextField1.getText());
                }

                private void setText(String title) {
                    jLabel1.setText("Edit " + title);
                }
            });
        }

        protected void addButtonActionPerformed(ActionEvent evt) {
            String title = (String) listSet.getSelectedValue();
            int index = listSet.getSelectedIndex();
            String newTitle = jTextField1.getText();
            try {
                if (newTitle.equals(""))
                    throw new PropException(ErrorString.MISSING_TITLE);
                if (title.equals(newTitle))
                    throw new PropException(ErrorString.EDIT_LIST_SAME_TITLE);
                listPController.setListTitle(title, newTitle);
                updateListSetModel();
                showListPanel = new ShowList(newTitle);
                setRightPanel(showListPanel);
                listSet.setSelectedIndex(index);
            }
            catch (PropException e) {
                jLabel3.setText(e.getMessage());
                jLabel3.setVisible(true);
                ActionListener listener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jLabel3.setVisible(false);
                    }
                };
                Timer timer = new Timer(3000, listener);
                timer.start();
            }
        }
    }

    private class ShowList extends JPanel {

        /**
         * Creates new form ShowList
         */
        public ShowList(String title) {
            id = title;
            initComponents();
            updateListModel();
        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">
        private void initComponents() {

            jLabel1 = new JLabel();
            //jLabel2 = new JLabel();
            jSeparator1 = new JSeparator();
            jScrollPane1 = new JScrollPane();
            jList1 = new JList();
            addSongButton = new JButton();
            removeSongButton = new JButton();
            swapSongsButton = new JButton();

            jLabel1.setText("List title");

            /*jLabel2.setText("Error: ");
            jLabel2.setForeground(Color.RED);
            jLabel2.setVisible(false);*/

            listModel = new DefaultListModel();
            jList1.setModel(listModel);
            jList1.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
            jList1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        // Double-click detected
                        int songIndex = jList1.getSelectedIndex();
                        int listIndex = listSet.getSelectedIndex();
                        String[] id = listPController.getSongId(listIndex,songIndex);
                        songTabView.showSong(id[0],id[1]);
                        tabbedPane.setSelectedIndex(1);
                        listSet.clearSelection();
                    }
                }
            });
            jScrollPane1.setViewportView(jList1);

            addSongButton.setText("Add Song");
            addSongButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addSongButtonActionPerformed(e);
                }
            });

            removeSongButton.setText("Remove Song");
            removeSongButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    removeSongButtonActionPerformed(e);
                }
            });

            swapSongsButton.setText("Swap Songs");
            swapSongsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    swapSongsButtonActionPerformed(e);
                }
            });

            GroupLayout layout = new GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane1, GroupLayout.Alignment.TRAILING)
                                            .addComponent(jSeparator1)
                                            .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel1)
                                                            .addGroup(layout.createSequentialGroup()
                                                                    .addComponent(addSongButton)
                                                                    .addGap(18, 18, 18)
                                                                    .addComponent(removeSongButton)
                                                                    .addGap(18, 18, 18)
                                                                    .addComponent(swapSongsButton))
                                                            /*.addComponent(jLabel2)*/)
                                                    .addGap(0, 43, Short.MAX_VALUE)))
                                    .addContainerGap())
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(addSongButton)
                                            .addComponent(removeSongButton)
                                            .addComponent(swapSongsButton))
                                    /*.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel2)*/
                                    .addContainerGap())
            );
        }// </editor-fold>

        private void addSongButtonActionPerformed(ActionEvent evt) {
            if (!listSet.isSelectionEmpty()) {
                String value = (String) listSet.getSelectedValue();
                setRightPanel(new AddSong(value));
            }
        }

        private void removeSongButtonActionPerformed(ActionEvent evt) {
            if (!listSet.isSelectionEmpty() && !jList1.isSelectionEmpty()) {
                String value = (String) listSet.getSelectedValue();
                int index = jList1.getSelectedIndex();
                listPController.removeSong(value, index);
                updateListModel();
            }
        }

        private void swapSongsButtonActionPerformed(ActionEvent evt) {
            int selectedSongs[] = jList1.getSelectedIndices();
            if (!listSet.isSelectionEmpty() && selectedSongs.length == 2) {
                String title = (String) listSet.getSelectedValue();
                listPController.swapSongs(title, selectedSongs[0],selectedSongs[1]);
                updateListModel();
            }
        }

        public void updateListModel() {
            listModel.clear();
            String list[] = listPController.getListStringArray(id);
            jLabel1.setText(list[0]);
            for (int i = 1; i < list.length; ++i) {
                listModel.addElement(list[i]);
            }
        }

        // Variables declaration - do not modify
        private JButton addSongButton;
        private JButton removeSongButton;
        private JButton swapSongsButton;
        private JLabel jLabel1;
        //private JLabel jLabel2;
        private JList jList1;
        private JScrollPane jScrollPane1;
        private JSeparator jSeparator1;
        private DefaultListModel listModel;
        private String id;
        // End of variables declaration
    }

    private class AddSong extends JPanel {

        /**
         * Creates new form AddSong
         */
        public AddSong(String title) {
            id = title;
            initComponents();
        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">
        private void initComponents() {

            jLabel5 = new JLabel();
            jSeparator2 = new JSeparator();
            jLabel6 = new JLabel();
            jLabel7 = new JLabel();
            jLabel8 = new JLabel();
            addButton = new JButton();
            artistComboBox = new JComboBox<String>();
            titleComboBox = new JComboBox<String>();

            jLabel5.setText("Add new song to " + id);

            jLabel6.setText("Artist: ");

            jLabel7.setText("Title: ");

            jLabel8.setText("Error:");
            jLabel8.setForeground(Color.RED);
            jLabel8.setVisible(false);

            addButton.setText("Add");
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addButtonActionPerformed(e);
                }
            });

            artistComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    artistComboBoxActionPerformed(e);
                }
            });
            updateArtistComboBoxModel();

            GroupLayout layout = new GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel5)
                                                    .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                            .addComponent(jSeparator2, GroupLayout.Alignment.TRAILING)
                                                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                    .addGap(0, 0, Short.MAX_VALUE)
                                                                    .addComponent(addButton))
                                                            .addGroup(layout.createSequentialGroup()
                                                                    .addGap(10, 10, 10)
                                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                            .addComponent(jLabel7)
                                                                            .addComponent(jLabel6))
                                                                    .addGap(18, 18, 18)
                                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                            .addGroup(layout.createSequentialGroup()
                                                                                    .addComponent(jLabel8)
                                                                                    .addGap(0, 0, Short.MAX_VALUE))
                                                                            .addComponent(artistComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                            .addComponent(titleComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                                    .addContainerGap())))
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jSeparator2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel6)
                                            .addComponent(artistComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel7)
                                            .addComponent(titleComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addComponent(addButton)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                                    .addComponent(jLabel8)
                                    .addContainerGap())
            );
        }// </editor-fold>

        private void addButtonActionPerformed(ActionEvent evt) {
            try {
                String artist = String.valueOf(artistComboBox.getSelectedItem());
                String title = String.valueOf(titleComboBox.getSelectedItem());
                listPController.addSong(id, title, artist);
                showListPanel.updateListModel();
                setRightPanel(showListPanel);
            }
            catch (PropException e) {
                jLabel8.setText(e.getMessage());
                jLabel8.setVisible(true);
                ActionListener listener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jLabel8.setVisible(false);
                    }
                };
                Timer timer = new Timer(3000, listener);
                timer.start();
            }
        }

        private void artistComboBoxActionPerformed(ActionEvent evt) {
            titleComboBox.removeAllItems();
            String artist = String.valueOf(artistComboBox.getSelectedItem());
            if (!artist.equals("")) {
                try {
                    ArrayList<String> titles = listPController.getTitlesFromArtists(artist);
                    for (String s : titles)
                        titleComboBox.addItem(s);
                } catch (PropException e) {
                    jLabel8.setText(e.getMessage());
                    jLabel8.setVisible(true);
                    ActionListener listener = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jLabel8.setVisible(false);
                        }
                    };
                    Timer timer = new Timer(3000, listener);
                    timer.start();
                }
            }
        }

        public void updateArtistComboBoxModel() {
            artistComboBox.removeAllItems();
            artistComboBox.addItem("");
            for (String s : new LinkedHashSet<>(listPController.getArtists()))
                artistComboBox.addItem(s);
        }

        // Variables declaration - do not modify
        private JButton addButton;
        private JComboBox<String> artistComboBox;
        private JComboBox<String> titleComboBox;
        private JLabel jLabel5;
        private JLabel jLabel6;
        private JLabel jLabel7;
        private JLabel jLabel8;
        private JSeparator jSeparator2;
        private String id;
        // End of variables declaration                   
    }

    private class AddRandomList extends JPanel {

        /**
         * Creates new form AddRandomList
         */
        public AddRandomList() {
            initComponents();
        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
        private void initComponents() {

            jLabel1 = new JLabel();
            jSeparator1 = new JSeparator();
            jLabel2 = new JLabel();

            jTextField1 = new JTextField();
            jTextField1.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (e.getKeyChar() == '|')
                        e.consume();
                }

                @Override
                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });

            addButton = new JButton();
            jLabel3 = new JLabel();
            jSpinner1 = new JSpinner();
            jLabel4 = new JLabel();

            jLabel1.setText("Add new random List");

            jLabel2.setText("Title: ");

            numberModel = new SpinnerNumberModel(listPController.songSetSize(),0,listPController.songSetSize(),1);
            jSpinner1.setModel(numberModel);

            addButton.setText("Add");
            addButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    addButtonActionPerformed(evt);
                }
            });

            jLabel3.setText("Error: ");
            jLabel3.setForeground(Color.RED);
            jLabel3.setVisible(false);

            jLabel4.setText("Number of songs:");

            GroupLayout layout = new GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(jSeparator1)
                                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addGap(0, 0, Short.MAX_VALUE)
                                                    .addComponent(addButton))
                                            .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel1)
                                                    .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel2)
                                                            .addComponent(jLabel4))
                                                    .addGap(21, 21, 21)
                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                            .addComponent(jTextField1)
                                                            .addGroup(layout.createSequentialGroup()
                                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                            .addComponent(jLabel3)
                                                                            .addComponent(jSpinner1, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
                                                                    .addGap(0, 100, Short.MAX_VALUE)))))
                                    .addContainerGap())
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel2)
                                            .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(jSpinner1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4))
                                    .addGap(14, 14, 14)
                                    .addComponent(addButton)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                                    .addComponent(jLabel3)
                                    .addContainerGap())
            );
        }// </editor-fold>

        private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
            String title = jTextField1.getText();
            int n = (int)jSpinner1.getValue();
            try {
                if (title.equals(""))
                    throw new PropException(ErrorString.MISSING_TITLE);
                listPController.createRandomList(title, n);
                jTextField1.setText("");
                updateListSetModel();
            }
            catch (PropException e) {
                jLabel3.setText(e.getMessage());
                jLabel3.setVisible(true);
                ActionListener listener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jLabel3.setVisible(false);
                    }
                };
                Timer timer = new Timer(3000, listener);
                timer.start();
            }
        }


        // Variables declaration - do not modify                     
        private JButton addButton;
        private JLabel jLabel1;
        private JLabel jLabel2;
        private JLabel jLabel3;
        private JLabel jLabel4;
        private JSeparator jSeparator1;
        private JSpinner jSpinner1;
        private SpinnerNumberModel numberModel;
        private JTextField jTextField1;
        // End of variables declaration                   
    }

}
