package prop.presentation;

import prop.domain.AlgorithmController;
import prop.domain.RelationController;

public class AlgorithmPresentationController {

    private AlgorithmController algorithmController;
    private RelationController relationController;

    public AlgorithmPresentationController() {
        algorithmController = new AlgorithmController();
        relationController = new RelationController();
    }

}
