package prop.presentation;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;

public class ListTabView extends TabView {

    public ListTabView() {
        super();
        initListComponents();
    }

    @Override
    protected JPanel createRightPanel() {
        return new JPanel();
    }

    @Override
    protected ArrayList<JButton> setActionBarButtons() {
        return new ArrayList<JButton>();
    }

    private void initListComponents() {
        getLeftList().setModel(new AbstractListModel() {
            String[] elements = {"A","B","C","D"};
            @Override
            public int getSize() {
                return elements.length;
            }

            @Override
            public Object getElementAt(int index) {
                return elements[index];
            }
        });
    }

}
