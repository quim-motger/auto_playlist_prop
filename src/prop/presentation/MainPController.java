package prop.presentation;

import prop.PropException;

import javax.swing.*;

public class MainPController {

    private UserPController userPController;
    private SongPController songPController;
    private ListPController listPController;
    private AlgorithmPController algorithmPController;
    private MainView mainView;

    public MainPController() {
        
        songPController = new SongPController();
        // Only for testing purposes  *******************************************************************
        try {
            songPController.addSong("title0", "artist0", "album0", "2000", "0", "0", "000");
            songPController.addSong("title1", "artist1", "album1", "2001", "1", "1", "111");
            songPController.addSong("title2", "artist2", "album2", "2002", "2", "2", "222");
            songPController.addSong("title3", "artist3", "album3", "2003", "3", "3", "333");
        }
        catch (PropException e) {
            System.out.println(e.getMessage());
        }
        // **********************************************************************************************
        listPController = new ListPController(songPController.getSongController());
        userPController = new UserPController(
                listPController.getListController(),
                songPController.getSongController()
                );
        algorithmPController = new AlgorithmPController();

        /* Set the Windows look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Windows is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
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
        //</editor-fold>

        mainView = new MainView(userPController,songPController,listPController,algorithmPController);

    }

    public void initPresentation() {
        mainView.setTitle("YouTubeMix");
        mainView.setLocationRelativeTo(null);
        mainView.setVisible(true);
    }

}
