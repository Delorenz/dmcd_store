package iutmetz.wmce.dmcd_store_dev;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import iutmetz.wmce.dmcd_store_dev.interfaces.ActiviteEnAttenteFindAll;
import iutmetz.wmce.dmcd_store_dev.interfaces.DAO;
import iutmetz.wmce.dmcd_store_dev.modele.Categorie;


public class CategorieDAO implements DAO {

    public static final String TABLE="Categorie";
    private final String URL_SERVEUR = "https://infodb.iutmetz.univ-lorraine.fr/~delorenz2u/android/";
    private ActiviteEnAttenteFindAll activite;
    private static CategorieDAO categorieDAO = null;

    public CategorieDAO(ActiviteEnAttenteFindAll activite) {
        this.activite = activite;
    }

    public static CategorieDAO getInstance(ActiviteEnAttenteFindAll activite) {
        if( categorieDAO == null){
            categorieDAO = new CategorieDAO(activite);
        }
        return categorieDAO;

    }


    public void findAll(){
      //  RequeteSQL req = new RequeteSQL(activite, this);
       // req.execute(TABLE, URL_SERVEUR + "categories.php");
        //Log.e("requete", "req executed");

    }

    @Override
    public void traiteResultatRequete(String[] result) {
        ArrayList<Categorie> liste = new ArrayList<>();
        try{
            JSONArray array = new JSONArray(result[1]);
            for(int i = 0; i<array.length(); i++){
                JSONObject row = array.getJSONObject(i);
                Categorie c = new Categorie(row.getInt("id_categorie"),
                        row.getString("titre"),
                        row.getString("visuel"));
                liste.add(c);
            }
            this.activite.notifyRetourRequeteFindAll(result[0], liste);
        }catch (JSONException je){
            Log.e("pb json", je.getMessage());
        }
    }
}
