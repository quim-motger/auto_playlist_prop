package prop.domain;

import java.lang.Override;

/**
 * Relacio complexa AND
 * @author joaquim.motger
 * @version 1.0
 */

public class AND extends RelacioComplexa {

    public AND(Relacio R1, Relacio R2) {
        r1 = R1;
        r2 = R2;
    }

    @Override
    public boolean avalua(Canco c1, Canco c2) {
        return r1.avalua(c1, c2) && r2.avalua(c1,c2);
    }
}
