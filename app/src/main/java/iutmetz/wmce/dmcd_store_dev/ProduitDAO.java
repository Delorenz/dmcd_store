package iutmetz.wmce.dmcd_store_dev;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import iutmetz.wmce.dmcd_store_dev.interfaces.ActiviteEnAttenteFindProduits;
import iutmetz.wmce.dmcd_store_dev.interfaces.DAO;
import iutmetz.wmce.dmcd_store_dev.modele.Produit;

public class ProduitDAO implements DAO {


    private ActiviteEnAttenteFindProduits activite;
    public static final String TABLE="Produits";
    private final String URL_SERVEUR = "https://infodb.iutmetz.univ-lorraine.fr/~delorenz2u/android/produits.php";
    private static ProduitDAO produitDAO = null;

    public ProduitDAO(ActiviteEnAttenteFindProduits activite) {
        this.activite = activite;
    }

    public static ProduitDAO getInstance(ActiviteEnAttenteFindProduits activite) {
        if( produitDAO == null){
            produitDAO = new ProduitDAO( activite);
        } else {
            produitDAO.activite = activite;
        }
        return produitDAO;

    }


    public void findProduitsByCateg(int id_categ){
      //  RequeteSQL req = new RequeteSQL(activite, this);
     //   req.execute(TABLE, URL_SERVEUR + "?id_categ="+id_categ);
     //   Log.e("requete", "req executed");

    }


    @Override
    public void traiteResultatRequete(String[] result) {

        ArrayList<Produit> liste = new ArrayList<>();
        try{
            JSONArray array = new JSONArray(result[1]);
            for(int i = 0; i<array.length(); i++){
                JSONObject row = array.getJSONObject(i);
                Produit p = new Produit(row.getInt("id_produit"),
                        row.getString("titre"),
                        row.getString("description"),
                        row.getString("visuel"),
                        row.getDouble("tarif"),

                        row.getInt("id_categorie"));
                liste.add(p);
            }
            this.activite.notifyRetourRequeteFindProduits(result[0], liste);
        }catch (JSONException je){
            Log.e("pb json", je.getMessage());
        }

    }
}
