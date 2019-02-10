package iutmetz.wmce.dmcd_store_dev;

import android.util.Log;

import iutmetz.wmce.dmcd_store_dev.interfaces.ActiviteEnAttenteUpdateClient;
import iutmetz.wmce.dmcd_store_dev.interfaces.DAO;
import iutmetz.wmce.dmcd_store_dev.modele.Client;

public class ClientUpdateDAO implements DAO {

    public static final String TABLE = "Client";
    private static ClientUpdateDAO clientUpdateDAO = null;
    private final String URL_SERVEUR = "https://storedmcd.000webhostapp.com/";
    private ActiviteEnAttenteUpdateClient activite;

    public ClientUpdateDAO(ActiviteEnAttenteUpdateClient activite) {
        this.activite = activite;
    }

    public static ClientUpdateDAO getInstance(ActiviteEnAttenteUpdateClient activite) {
        if (clientUpdateDAO == null) {
            clientUpdateDAO = new ClientUpdateDAO(activite);
        }
        return clientUpdateDAO;

    }

    @Override
    public void traiteResultatRequete(String[] result) {
        Log.e("Resultat requete client", "debut retour requete update");
        Log.e("Resultat dump", result.toString());

/*
            JSONArray array = new JSONArray(result[1]);
            JSONObject row = array.getJSONObject(0);

            Client c = new Client(row.getInt("id_client"),
                    row.getString("nom"),
                    row.getString("prenom"),
                    row.getString("identifiant"),
                    row.getString("mot_de_passe"),
                    row.getInt("adr_numero"),
                    row.getString("adr_voie"),
                    row.getInt("adr_code_postal"),
                    row.getString("adr_ville"),
                    row.getString("adr_pays")


            );
*/
        //Log.e("Resultat obj dump", c.toString());

        this.activite.notifyRetourRequeteUpdateClient(result[0], null);

        //Log.e("pb json cl", je.getMessage());

    }

    public void postDataUpdate(Client params) {

        //https://storedmcd.000webhostapp.com/Client.php?update_client=1&nom=Update6&prenom=PrenomTest&identifiant=idTest&mot_de_passe=mdpTest&adr_numero=55&adr_voie=VoieTest&adr_code_postal=55555&adr_ville=VilleTest&adr_pays=PaysTest&id_client=4
        RequeteSQL req = new RequeteSQL(activite, this);
        req.execute(TABLE, URL_SERVEUR + "Client.php?update_client=1&id_client=" + params.getId_client() + "&nom=" + params.getNom() + "&prenom=" + params.getPrenom() + "&identifiant=" + params.getIdentifiant() + "&mot_de_passe=" + params.getMot_de_passe() + "&adr_numero=" + params.getAdr_numero() + "&adr_voie=" + params.getAdr_voie() + "&adr_code_postal=" + params.getAdr_code_postal() + "&adr_ville=" + params.getAdr_ville() + "&adr_pays=" + params.getAdr_pays());
        Log.e("requete Update cl", "req update client launched");
    }
}
