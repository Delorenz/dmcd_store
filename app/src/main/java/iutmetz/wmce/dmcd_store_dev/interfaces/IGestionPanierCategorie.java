package iutmetz.wmce.dmcd_store_dev.interfaces;

import iutmetz.wmce.dmcd_store_dev.VenteCatalogueFragment;
import iutmetz.wmce.dmcd_store_dev.modele.Client;

public interface IGestionPanierCategorie {

    void DisplayProductFragment(int id);

    double getPanier();

    void setPanier(double tarif);

    void onClickAjoutPanier(VenteCatalogueFragment venteCatalogueFragment, int noProduitCourant);

    void DisplayClientFragment();

    void DisplayNvClientFragment();

    void DisplayInfoClientFragment();

    void DisplayCategoriesFragment();

    Client getCl_connected();

    void setCl_connected(Client client);

    void DisplayMentionFragment();
}



