package prop.presentation;

public class MainPresentationController {

    private UserPresentationController userPresentationController;
    private SongPresentationController songPresentationController;
    private ListPresentationController listPresentationController;
    private AlgorithmPresentationController algorithmPresentationController;
    private MainView mainView;

    public MainPresentationController() {
        userPresentationController = new UserPresentationController();
        songPresentationController = new SongPresentationController();
        listPresentationController = new ListPresentationController();
        algorithmPresentationController = new AlgorithmPresentationController();
        mainView = new MainView(
                userPresentationController,
                songPresentationController,
                listPresentationController,
                algorithmPresentationController
        );
    }

    public void initPresentation() {
        mainView.setVisible();
    }

}
