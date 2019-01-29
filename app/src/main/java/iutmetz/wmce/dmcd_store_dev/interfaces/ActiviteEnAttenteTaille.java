package iutmetz.wmce.dmcd_store_dev.interfaces;

import java.util.ArrayList;

public interface ActiviteEnAttenteTaille extends ActiviteEnAttente {

    public void notifyRetourRequeteFindTaille(String code, ArrayList liste);

}
