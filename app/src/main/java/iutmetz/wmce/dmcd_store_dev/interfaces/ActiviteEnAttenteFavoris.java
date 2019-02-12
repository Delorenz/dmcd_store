package iutmetz.wmce.dmcd_store_dev.interfaces;

public interface ActiviteEnAttenteFavoris extends ActiviteEnAttente {

    void notifyRetourFavoris(String code, String msg);
}
