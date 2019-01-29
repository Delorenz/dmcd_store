package iutmetz.wmce.dmcd_store_dev.interfaces;

import java.util.ArrayList;

public interface ActiviteEnAttenteFindAll extends ActiviteEnAttente {

    public void notifyRetourRequeteFindAll(String code, ArrayList liste);
}
