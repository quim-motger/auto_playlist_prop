package prop.domain;

/**
 * Created by casassg on 30/03/15.
 *
 * @author casassg
 * @version 1.0
 */
public class OR extends RelacioComplexa {
    
    public OR(Relacio relacio1, Relacio relacio2) {
        r1 = relacio1;
        r2 = relacio2;
    }

    @Override
    public boolean avalua(Canco c1, Canco c2) {
        return r1.avalua(c1, c2) && r2.avalua(c1, c2);
    }
}
