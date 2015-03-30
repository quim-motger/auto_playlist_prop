package prop.domain;

/**
 * Created by casassg on 30/03/15.
 *
 * @author casassg
 * @version 1.0
 */
public class NOT extends Complexe {
    
    public NOT(Relacio relacio) {
        R1 = relacio;
    }

    @Override
    public boolean avalua(Canco c1, Canco c2) {
        return !R1.avalua(c1, c2);
    }
}
