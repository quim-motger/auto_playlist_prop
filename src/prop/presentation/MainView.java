package prop.presentation;

import prop.ErrorString;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    public static final String FILE_EXTENSION = ".prop";
    public static final String FILE_EXT_NAME = "prop";
    private final MainPController mainPController;
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
    private UserTabView userPanel;
    private SongTabView songPanel;
    private ListTabView listPanel;
    private JPanel AlgorithmPanel;
    private JTabbedPane TabbedPane;

    public MainView(UserPController upc, SongPController spc, ListPController lpc, AlgorithmPController apc,MainPController mpc) {
        userPController = upc;
        songPController = spc;
        listPController = lpc;
        algorithmPController = apc;
        mainPController = mpc;
        initComponents();
    }
    
    private void initComponents() {

        TabbedPane = new JTabbedPane();
        userPanel = new UserTabView(userPController,this);
        songPanel = new SongTabView(songPController);
        listPanel = new ListTabView(listPController, songPanel,TabbedPane);
        AlgorithmPanel = new AlgorithmTabView(algorithmPController,listPController,songPController,userPController);
        MenuBar = new JMenuBar();
        FileMenu = new JMenu();
        SaveMenuItem = new JMenuItem();
        LoadMenuItem = new JMenuItem();
        ExitMenuItem = new JMenuItem();
        HelpMenu = new JMenu();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));

        TabbedPane.addTab("Users", userPanel);

        TabbedPane.addTab("Songs", songPanel);

        TabbedPane.addTab("Lists", listPanel);

        TabbedPane.addTab("Algorithms", AlgorithmPanel);

        TabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = TabbedPane.getSelectedIndex();
                switch (index) {
                    case 2:
                        listPanel.updateListSetModel();
                }
            }
        });

        FileMenu.setText("File");

        SaveMenuItem.setText("Save");
        SaveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveData();
            }
        });
        FileMenu.add(SaveMenuItem);

        LoadMenuItem.setText("Load");
        LoadMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadData();
            }
        });
        FileMenu.add(LoadMenuItem);

        ExitMenuItem.setText("Exit");
        ExitMenuItem.setIconTextGap(15);
        ExitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exit();
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

    private void exit() {
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void saveData() {
        JFileChooser c = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(FILE_EXTENSION, FILE_EXT_NAME);
        c.setFileFilter(filter);
        // Demonstrate "Open" dialog:
        int rVal = c.showSaveDialog(this);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            String file = c.getSelectedFile().getName();
            String dir = c.getCurrentDirectory().toString();
            try {
                String path = dir+"/"+file+FILE_EXTENSION;
                if(file.endsWith(FILE_EXTENSION))
                    path=dir+"/"+file;
                mainPController.save(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadData() {
        JFileChooser c = new JFileChooser(){
            @Override
            public void approveSelection() {
                String file = getSelectedFile().getName();
                String dir = getCurrentDirectory().toString();
                try {
                    mainPController.load(dir + "/" + file);
                    updateAllTabs();
                    super.approveSelection();
                } catch (Exception e) {
                    e.printStackTrace();
                    if(e.getMessage().equals(ErrorString.INCORRECT_FORMAT)) {
                        JOptionPane.showMessageDialog(this, ErrorString.CORRUPT_FILE);
                    } else {
                        JOptionPane.showMessageDialog(this, ErrorString.LOAD_OTHERS);
                    }
                }
            }
        };
        FileNameExtensionFilter filter = new FileNameExtensionFilter(FILE_EXTENSION, FILE_EXT_NAME);
        c.setFileFilter(filter);
        // Demonstrate "Open" dialog:
        c.showOpenDialog(this);
    }

    private void updateAllTabs() {
        userPanel.updateList();
        listPanel.updateListSetModel();
        songPanel.updateSongSetModel();
    }

    public void showList(String list) {
        TabbedPane.setSelectedIndex(2);
        listPanel.showList(list);
    }
}

