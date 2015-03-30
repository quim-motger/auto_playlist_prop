package prop.domain;

import java.lang.Override;

/**
 * Relacio complexa AND
 * @author joaquim.motger
 * @version 1.0
 */

public class AND extends RelacioComplexa {

    public AND(Relacio r1, Relacio r2) {
        this.r1 = r1;
        this.r2 = r2;
    }

    @Override
    public boolean avalua(Canco c1, Canco c2) {
        return r1.avalua(c1, c2) && r2.avalua(c1,c2);
    }
}
