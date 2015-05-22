package prop.presentation;

import javax.swing.*;
import java.util.ArrayList;

/**
 * UserTabView in prop.presentation
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 22/05/15
 */
public class UserTabView extends TabView {

    public UserTabView() {
        super();
        super.getLeftList().setModel(new AbstractListModel() {
            String[] test = {
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                    "a",
                    "a",
                    "a",
                    "a",
                    "a",
                    "a",
                    "a",
            };

            @Override
            public int getSize() {
                return test.length;
            }

            @Override
            public Object getElementAt(int i) {
                return test[i];
            }
        });
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
