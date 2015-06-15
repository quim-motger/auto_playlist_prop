package prop.presentation.generator;

/**
 * FormUtility in prop.presentation_v2.generator
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 14/06/15
 */

import javax.swing.*;
import java.awt.*;

/**
 * Simple utility class for creating forms that have a column
 * of labels and a column of fields. All of the labels have the
 * same width, determined by the width of the widest label
 * component.
 * <p/>
 * Philip Isenhour - 060628 - http://javatechniques.com/
 */
public class FormUtility {
    /**
     * Grid bag constraints for fields and labels
     */
    private GridBagConstraints lastConstraints = null;
    private GridBagConstraints middleConstraints = null;
    private GridBagConstraints labelConstraints = null;
    private Container mParent;

    public FormUtility(Container parent) {
        mParent = parent;

        // Set up the constraints for the "last" field in each
        // row first, then copy and modify those constraints.


        // weightx is 1.0 for fields, 0.0 for labels
        // gridwidth is REMAINDER for fields, 1 for labels
        lastConstraints = new GridBagConstraints();

        // Stretch components horizdrivontally (but not vertically)
        lastConstraints.fill = GridBagConstraints.HORIZONTAL;

        // Components that are too short or narrow for their space
        // Should be pinned to the northwest (upper left) corner
        lastConstraints.anchor = GridBagConstraints.NORTHWEST;

        // Give the "last" component as much space as possible
        lastConstraints.weightx = 1.0;

        // Give the "last" component the remainder of the row
        lastConstraints.gridwidth = GridBagConstraints.REMAINDER;

        // Add a little padding
        lastConstraints.insets = new Insets(5, 5, 5, 5);

        // Now for the "middle" field components
        middleConstraints =
                (GridBagConstraints) lastConstraints.clone();

        // These still get as much space as possible, but do
        // not close out a row
        middleConstraints.gridwidth = GridBagConstraints.RELATIVE;

        // And finally the "label" constrains, typically to be
        // used for the first component on each row
        labelConstraints =
                (GridBagConstraints) lastConstraints.clone();

        // Give these as little space as necessary
        labelConstraints.weightx = 0.0;
        labelConstraints.gridwidth = 1;
    }

    /**
     * Adds a field component. Any component may be used. The
     * component will be stretched to take the remainder of
     * the current row.
     */
    public void addLastField(Component c) {
        GridBagLayout gbl = (GridBagLayout) mParent.getLayout();
        gbl.setConstraints(c, lastConstraints);
        mParent.add(c);
    }

    /**
     * Adds an arbitrary label component, starting a new row
     * if appropriate. The width of the component will be set
     * to the minimum width of the widest component on the
     * form.
     */
    public void addLabel(Component c) {
        GridBagLayout gbl = (GridBagLayout) mParent.getLayout();
        gbl.setConstraints(c, labelConstraints);
        mParent.add(c);
    }

    /**
     * Adds a JLabel with the given string to the label column
     */
    public JLabel addLabel(String s) {
        JLabel c = new JLabel(s);
        addLabel(c);
        return c;
    }

    /**
     * Adds a "middle" field component. Any component may be
     * used. The component will be stretched to take all of
     * the space between the label and the "last" field. All
     * "middle" fields in the layout will be the same width.
     */
    public void addMiddleField(Component c) {
        GridBagLayout gbl = (GridBagLayout) mParent.getLayout();
        gbl.setConstraints(c, middleConstraints);
        mParent.add(c);
    }

    public void addFiller() {
        GridBagConstraints gbcFiller = new GridBagConstraints();
        gbcFiller.weighty = 1.0;
        gbcFiller.fill = GridBagConstraints.VERTICAL;
        mParent.add(Box.createGlue(), gbcFiller);
    }

}
