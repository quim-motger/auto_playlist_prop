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

public class ListTabView extends TabView {

    private ListPController listPController;
    private JPanel emptyPanel;
    private AddList addListPanel;
    private AddSong addSongPanel;
    private ShowList showListPanel;
    private JList listSet;
    private DefaultListModel listSetModel;
    private JTextField searchField;
    private JButton searchButton;
    private JButton addListButton;
    private JButton removeListButton;
    private JButton addSongButton;

    public ListTabView(ListPController lpc) {
        super();
        listPController = lpc;
        initListComponents();
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
            public void actionPerformed(ActionEvent evt) {
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
        });
        buttons.add(removeListButton);

        addSongButton = new JButton("Add Song");
        addSongButton.setBorder(BorderFactory.createEmptyBorder(10, 3, 10, 3));
        addSongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = (String) listSet.getSelectedValue();
                if (!listSet.isSelectionEmpty()) {
                    addSongPanel = new AddSong(value);
                    setRightPanel(addSongPanel);
                }

            }
        });
        buttons.add(addSongButton);

        return buttons;
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

        searchButton = getSearchButton();
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateListSetModel();
            }
        });

        addListPanel = new AddList();
    }

    private void updateListSetModel() {
        listSetModel.clear();
        ArrayList<String> lists;
        String prefix = searchField.getText();
        if (prefix.equals(""))
            lists = listPController.getListSetStringArray();
        else
            lists = listPController.findLists(prefix);
        for (String s : lists) {
            listSetModel.addElement(s);
        }
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
        private void initComponents() {

            jLabel1 = new JLabel();
            jSeparator1 = new JSeparator();
            jLabel2 = new JLabel();
            jTextField1 = new JTextField();
            jButton1 = new JButton();
            jLabel3 = new javax.swing.JLabel();

            jLabel1.setText("Add new list");

            jLabel2.setText("Title: ");

            jButton1.setText("Add");
            jButton1.addActionListener(new ActionListener() {
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
                                                    .addComponent(jButton1))
                                            .addGroup(layout.createSequentialGroup()
                                                    .addGap(10, 10, 10)
                                                    .addComponent(jLabel2)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(layout.createSequentialGroup()
                                                                    .addComponent(jLabel3)
                                                                    .addGap(0, 200, Short.MAX_VALUE))
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
                                    .addComponent(jButton1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 167, Short.MAX_VALUE)
                                    .addComponent(jLabel3)
                                    .addContainerGap())
            );
        }// </editor-fold>

        private void addButtonActionPerformed(ActionEvent evt) {
            String title = jTextField1.getText();
            try {
                listPController.addList(title);
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
        private JButton jButton1;
        private JLabel jLabel1;
        private JLabel jLabel2;
        private JLabel jLabel3;
        private JSeparator jSeparator1;
        private JTextField jTextField1;
        // End of variables declaration
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
            jSeparator1 = new JSeparator();
            jScrollPane1 = new JScrollPane();
            jList1 = new JList();

            jLabel1.setText("List title");

            listModel = new DefaultListModel();
            jList1.setModel(listModel);
            jScrollPane1.setViewportView(jList1);

            GroupLayout layout = new GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addComponent(jScrollPane1)
                                            .addComponent(jSeparator1, GroupLayout.Alignment.LEADING)
                                            .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addComponent(jLabel1)
                                                    .addGap(0, 343, Short.MAX_VALUE)))
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
                                    .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                                    .addContainerGap())
            );
        }// </editor-fold>

        public void updateListModel() {
            listModel.clear();
            ArrayList<String> list = listPController.getListStringArray(id);
            jLabel1.setText(list.get(0));
            for (int i = 1; i < list.size(); ++i) {
                listModel.addElement(list.get(i));
            }
        }

        // Variables declaration - do not modify
        private JLabel jLabel1;
        private JList jList1;
        private JScrollPane jScrollPane1;
        private JSeparator jSeparator1;
        private DefaultListModel listModel;
        private String id;
        // End of variables declaration                   
    }

    public class AddSong extends JPanel {

        /**
         * Creates new form AddSong2
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
            jTextField3 = new JTextField();
            jLabel7 = new JLabel();
            jTextField4 = new JTextField();
            jLabel8 = new JLabel();
            jButton2 = new JButton();

            jLabel5.setText("Add new song to " + id);

            jLabel6.setText("Title: ");

            jLabel7.setText("Artist: ");

            jLabel8.setText("Error:");
            jLabel8.setForeground(Color.RED);
            jLabel8.setVisible(false);

            jButton2.setText("Add");
            jButton2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addButtonActionPerformed(e);
                }
            });

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
                                                                    .addComponent(jButton2))
                                                            .addGroup(layout.createSequentialGroup()
                                                                    .addGap(10, 10, 10)
                                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                            .addComponent(jLabel7)
                                                                            .addComponent(jLabel6))
                                                                    .addGap(18, 18, 18)
                                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                            .addGroup(layout.createSequentialGroup()
                                                                                    .addComponent(jLabel8)
                                                                                    .addGap(0, 200, Short.MAX_VALUE))
                                                                            .addComponent(jTextField4)
                                                                            .addComponent(jTextField3))))
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
                                            .addComponent(jTextField4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel7)
                                            .addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton2)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                                    .addComponent(jLabel8)
                                    .addContainerGap())
            );
        }// </editor-fold>

        private void addButtonActionPerformed(ActionEvent evt) {
            try {
                if (id == null) throw new NullPointerException();
                String title = jTextField4.getText();
                String artist = jTextField3.getText();
                listPController.addSong(id, title, artist);
                showListPanel.updateListModel();
                setRightPanel(showListPanel);
                jTextField3.setText("");
                jTextField4.setText("");
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

        // Variables declaration - do not modify
        private JButton jButton2;
        private JLabel jLabel5;
        private JLabel jLabel6;
        private JLabel jLabel7;
        private JLabel jLabel8;
        private JSeparator jSeparator2;
        private JTextField jTextField3;
        private JTextField jTextField4;
        private String id;
        // End of variables declaration                   
    }

}
