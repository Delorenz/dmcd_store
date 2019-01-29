package iutmetz.wmce.dmcd_store_dev.interfaces;

import iutmetz.wmce.dmcd_store_dev.VenteCatalogueFragment;

public interface IGestionPanierCategorie {

    public void DisplayProductFragment(int id);
    public double getPanier();

    public void setPanier(double tarif);

    public void onClickAjoutPanier(VenteCatalogueFragment venteCatalogueFragment, int noProduitCourant);

    void DisplayClientFragment();
}



