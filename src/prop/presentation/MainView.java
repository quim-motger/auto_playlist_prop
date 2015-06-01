package prop.presentation;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainView extends JFrame {

    public static final String USERS_FILE = "users.users";
    private UserPController userPController;
    private SongPController songPController;
    private ListPController listPController;
    private AlgorithmPController algorithmPController;

    private JMenu FileMenu;
    private JMenu HelpMenu;
    private JMenuBar MenuBar;
    private JMenuItem ExitMenuItem;
    private JMenuItem SaveMenuItem;
    private JMenuItem LoadMenuItem;
    private UserTabView UserPanel;
    private SongTabView SongPanel;
    private ListTabView ListPanel;
    private JPanel AlgorithmPanel;
    private JTabbedPane TabbedPane;

    public MainView(UserPController upc, SongPController spc, ListPController lpc, AlgorithmPController apc) {
        userPController = upc;
        songPController = spc;
        listPController = lpc;
        algorithmPController = apc;
        initComponents();
    }
    
    private void initComponents() {

        TabbedPane = new JTabbedPane();
        UserPanel = new UserTabView(userPController,this);
        SongPanel = new SongTabView(songPController);
        ListPanel = new ListTabView(listPController,SongPanel,TabbedPane);
        AlgorithmPanel = new AlgorithmTabView(algorithmPController,listPController,songPController,userPController);
        MenuBar = new JMenuBar();
        FileMenu = new JMenu();
        SaveMenuItem = new JMenuItem();
        LoadMenuItem = new JMenuItem();
        ExitMenuItem = new JMenuItem();
        HelpMenu = new JMenu();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));

        TabbedPane.addTab("Users", UserPanel);

        TabbedPane.addTab("Songs", SongPanel);

        TabbedPane.addTab("Lists", ListPanel);

        TabbedPane.addTab("Algorithms", AlgorithmPanel);

        TabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = TabbedPane.getSelectedIndex();
                switch (index) {
                    case 2:
                        ListPanel.updateListSetModel();
                }
            }
        });

        FileMenu.setText("File");

        SaveMenuItem.setText("Save");
        SaveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveData(evt);
            }
        });
        FileMenu.add(SaveMenuItem);

        LoadMenuItem.setText("Load");
        LoadMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        FileMenu.add(LoadMenuItem);

        ExitMenuItem.setText("Exit");
        ExitMenuItem.setIconTextGap(15);
        ExitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onCloseOptionSelected(evt);
            }
        });
        FileMenu.add(ExitMenuItem);

        MenuBar.add(FileMenu);

        HelpMenu.setText("Help");
        HelpMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        MenuBar.add(HelpMenu);

        setJMenuBar(MenuBar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(TabbedPane)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(TabbedPane)
        );

        pack();
    }

    private void onCloseOptionSelected(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void saveData(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        JFileChooser c = new JFileChooser();
       // c.set
        
        // Demonstrate "Open" dialog:
        int rVal = c.showSaveDialog(this);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            
            String dir = c.getCurrentDirectory().toString();
            try {
                String path = dir+"/"+ USERS_FILE;
                userPController.save(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*private boolean correctFolder(JFileChooser c) {
        String directory = c.getCurrentDirectory().toString();
        File folder = new File(directory);
        File[] dir_contents =  folder.listFiles();
        //String temp = file + ".MOD";
        boolean check = new File(temp).exists();
    }*/

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    public void showList(String list) {
        TabbedPane.setSelectedIndex(2);
        ListPanel.showList(list);
    }
}

