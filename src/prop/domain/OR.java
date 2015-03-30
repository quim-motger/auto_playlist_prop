package prop.domain;

/**
 * Created by casassg on 30/03/15.
 *
 * @author casassg
 * @version 1.0
 */
public class OR extends Complexe{
    
    public OR(Relacio relacio1, Relacio relacio2) {
        R1 = relacio1;
        R2 = relacio2;
    }

    @Override
    public boolean avalua(Canco c1, Canco c2) {
        return R1.avalua(c1, c2) && R2.avalua(c1, c2);
    }
}
