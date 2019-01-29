package iutmetz.wmce.dmcd_store_dev.interfaces;


import iutmetz.wmce.dmcd_store_dev.modele.Client;

public interface ActiviteEnAttenteFindClient extends ActiviteEnAttente {

    public void notifyRetourRequeteFindClient(String code, Client client);

}
