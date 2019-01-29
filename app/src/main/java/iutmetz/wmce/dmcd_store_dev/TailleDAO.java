package iutmetz.wmce.dmcd_store_dev;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import iutmetz.wmce.dmcd_store_dev.interfaces.ActiviteEnAttenteTaille;
import iutmetz.wmce.dmcd_store_dev.interfaces.DAO;
import iutmetz.wmce.dmcd_store_dev.modele.Taille;

public class TailleDAO implements DAO {

    public static final String TABLE="Taille";
    private final String URL_SERVEUR = "https://infodb.iutmetz.univ-lorraine.fr/~delorenz2u/android/";
    private ActiviteEnAttenteTaille activite;
    private static TailleDAO tailleDAO = null;

    public TailleDAO(ActiviteEnAttenteTaille activite) {
        this.activite = activite;
    }

    public static TailleDAO getInstance(ActiviteEnAttenteTaille activite) {
        if( tailleDAO == null){
            tailleDAO = new TailleDAO(activite);
        } else {
            tailleDAO.activite = activite;
        }
        return tailleDAO;

    }



    public void findTaille(int id_produit){
        RequeteSQL req = new RequeteSQL(activite, this);
        req.execute(TABLE, URL_SERVEUR + "produits.php?id_produit="+ id_produit);
        Log.e("requete", "req executed");

    }


    @Override
    public void traiteResultatRequete(String[] result) {

        ArrayList<Taille> liste = new ArrayList<>();

        try{
            JSONArray array = new JSONArray(result[1]);
            for(int i = 0; i<array.length(); i++){
                JSONObject row = array.getJSONObject(i);
                Taille t = new Taille(row.getInt("id_taille"),
                        row.getString("libelle"));
                liste.add(t);
            }
            this.activite.notifyRetourRequeteFindTaille(result[0], liste);
        }catch (JSONException je){
            Log.e("pb json", je.getMessage());
        }



    }
}
