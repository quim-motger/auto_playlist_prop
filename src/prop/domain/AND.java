package prop.domain;

import java.lang.Override;

/**
 * Relacio complexa AND
 * @author joaquim.motger
 * @version 1.0
 */

public class AND extends Complexe {

    public AND(Relacio r1, Relacio r2) {
        R1 = r1;
        R2 = r2;
    }

    @Override
    public boolean avalua(Canco c1, Canco c2) {
        return R1.avalua(c1, c2) && R2.avalua(c1,c2);
    }
}
