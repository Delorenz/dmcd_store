package iutmetz.wmce.dmcd_store_dev.interfaces;

import java.util.ArrayList;

public interface ActiviteEnAttenteFindProduits extends ActiviteEnAttente {

    public void notifyRetourRequeteFindProduits(String code, ArrayList liste);
}
