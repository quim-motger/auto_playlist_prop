package prop.presentation.basicelements;

import javax.swing.*;

/**
 * ActionBarButton in prop.presentation.basicelements
 *Button with borders designed for the actionBar
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 24/05/15
 */
public class ActionBarButton extends JButton {
    
    public ActionBarButton(String value) {
        super(value);
        setBorder(BorderFactory.createEmptyBorder(10, 3, 10, 3));
    }
}
