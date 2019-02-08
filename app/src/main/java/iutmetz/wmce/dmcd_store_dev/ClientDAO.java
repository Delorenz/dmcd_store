package iutmetz.wmce.dmcd_store_dev;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import iutmetz.wmce.dmcd_store_dev.interfaces.ActiviteEnAttenteFindClient;
import iutmetz.wmce.dmcd_store_dev.interfaces.DAO;
import iutmetz.wmce.dmcd_store_dev.modele.Client;

public class ClientDAO implements DAO {
    //BdD : Client(id_client, nom, prenom, identifiant, mot_de_passe, adr_numero, adr_voie, adr_code_postal, adr_ville, adr_pays)

    public static final String TABLE="Client";
    private final String URL_SERVEUR = "https://storedmcd.000webhostapp.com/";
    private ActiviteEnAttenteFindClient activite;
    private static ClientDAO clientDAO = null;

    public ClientDAO(ActiviteEnAttenteFindClient activite) {
        this.activite = activite;
    }

    public static ClientDAO getInstance(ActiviteEnAttenteFindClient activite) {
        if( clientDAO == null){
            clientDAO = new ClientDAO(activite);
        }
        return clientDAO;

    }


    public void findClient(){
        RequeteSQL req = new RequeteSQL(activite, this);
        req.execute(TABLE, URL_SERVEUR + "Client.php?id_client=1");
        Log.e("requete cl", "req client executed");

    }


    @Override
    public void traiteResultatRequete(String[] result) {
        Log.e("Resultat requete client", "debut retour requete");
        Log.e("Resultat dump", result.toString());
        try{

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

            Log.e("Resultat obj dump", c.toString());

            this.activite.notifyRetourRequeteFindClient(result[0], c);
        }catch (JSONException je){
            Log.e("pb json cl", je.getMessage());
        }
    }

    public void postDataConnexion(Client params) {
        RequeteSQL req = new RequeteSQL(activite, this);
        req.execute(TABLE, URL_SERVEUR + "Client.php?login=" + params.getIdentifiant() + "&password=" + params.getMot_de_passe());
        Log.e("requete POST cl", "req client launched");
    }

    public void postDataCreationCompte(Client params) {
        RequeteSQL req = new RequeteSQL(activite, this);
        req.execute(TABLE, URL_SERVEUR + "Client.php?nom=" + params.getNom() + "&prenom=" + params.getPrenom() + "&identifiant=" + params.getIdentifiant() + "&password=" + params.getMot_de_passe() + "" +
                "&adr_numero=" + params.getAdr_numero() + "&adr_voie=" + params.getAdr_voie() + "&adr_code_postal=" + params.getAdr_code_postal() +
                "&adr_ville=" + params.getAdr_ville() + "&adr_pays=" + params.getAdr_pays() + "&nv-client=1");
        Log.e("requete POST create cl", "req create client launched");


    }


}

