package iutmetz.wmce.dmcd_store_dev.interfaces;

import iutmetz.wmce.dmcd_store_dev.modele.Client;

public interface ActiviteEnAttenteUpdateClient extends ActiviteEnAttente {

    void notifyRetourRequeteUpdateClient(String code, Client client);
}
