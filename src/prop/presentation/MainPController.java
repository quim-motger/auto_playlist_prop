package prop.presentation;

import javax.swing.*;

public class MainPController {

    private UserPController userPController;
    private SongPController songPController;
    private ListPController listPController;
    private AlgorithmPController algorithmPController;
    private MainView mainView;

    public MainPController() {
        
        songPController = new SongPController();
        listPController = new ListPController(songPController.getSongController());
        userPController = new UserPController(
                listPController.getListController(),
                songPController.getSongController()
                );
        algorithmPController = new AlgorithmPController(songPController.getSongController(),userPController.getUserController(), listPController.getListController());
        
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        mainView = new MainView(userPController,songPController,listPController,algorithmPController, this);

    }

    public void initPresentation() {
        mainView.setTitle("YouTubeMix");
        mainView.setLocationRelativeTo(null);
        mainView.setVisible(true);
    }
    
    public void save(String path) throws Exception {
        songPController.save(path);
        listPController.saveAppend(path);
        userPController.saveAppend(path);
    }
    
    public void load(String path) throws Exception {
        int currentLine = songPController.load(path, 0);
        currentLine = listPController.load(path, currentLine);
        userPController.load(path, currentLine);
    }

}
