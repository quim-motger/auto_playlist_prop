package prop.presentation;

public class MainPController {

    private UserPController userPController;
    private SongPController songPController;
    private ListPController listPController;
    private AlgorithmPController algorithmPController;
    private MainView mainView;

    public MainPController() {
        userPController = new UserPController();
        songPController = new SongPController();
        listPController = new ListPController();
        algorithmPController = new AlgorithmPController();
        mainView = new MainView(
                userPController,
                songPController,
                listPController,
                algorithmPController
        );
    }

    public void initPresentation() {
        mainView.setVisible();
    }

}
