package iutmetz.wmce.dmcd_store_dev.interfaces;


import iutmetz.wmce.dmcd_store_dev.modele.Client;

public interface ActiviteEnAttenteFindClient extends ActiviteEnAttente {

    void notifyRetourRequeteFindClient(String code, Client client);

    void notifyRetourRequetePOST(String code, Client client);
}
