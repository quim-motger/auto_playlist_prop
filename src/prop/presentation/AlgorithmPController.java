package prop.presentation;

import prop.domain.AlgorithmController;
import prop.domain.RelationController;

public class AlgorithmPController {

    private AlgorithmController algorithmController;
    private RelationController relationController;

    public AlgorithmPController() {
        algorithmController = new AlgorithmController();
        relationController = new RelationController();
    }

}
