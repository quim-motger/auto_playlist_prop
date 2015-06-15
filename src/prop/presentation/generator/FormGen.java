package prop.presentation.generator;

import prop.domain.Pair;
import prop.presentation.basicpanels.PropPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * FormGen in prop.presentation_v2.generator
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 11/06/15
 */
public abstract class FormGen extends PropPanel {

    private FormUtility formUtil;

    public FormGen() {
        ArrayList<Pair<String, JComponent>> fields = setFields();
        for (Pair<String, JComponent> field : fields) {
            addField(field.first(), field.second());
        }
        endOfForm();
    }

    protected abstract ArrayList<Pair<String, JComponent>> setFields();

    @Override
    protected JPanel createFormPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        formUtil = new FormUtility(mainPanel);
        return mainPanel;
    }

    private void addField(String label, JComponent field) {
        formUtil.addLabel(label);
        formUtil.addLastField(field);
    }

    private void endOfForm() {
        formUtil.addFiller();

    }

}
