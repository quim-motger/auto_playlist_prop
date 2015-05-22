package prop.presentation;

import javax.swing.*;
import java.util.ArrayList;

public class ListTabView extends TabView {

    public ListTabView() {
        super();

    }

    @Override
    protected JPanel createRightPanel() {
        return new JPanel();
    }

    @Override
    protected ArrayList<JButton> setActionBarButtons() {
        return new ArrayList<JButton>();
    }

}
