package iutmetz.wmce.dmcd_store_dev;

import android.util.Log;

import java.util.ArrayList;

import iutmetz.wmce.dmcd_store_dev.interfaces.ActiviteEnAttenteFavoris;
import iutmetz.wmce.dmcd_store_dev.interfaces.DAO;

public class FavorisDAO implements DAO {


    public static final String TABLE = "Favoris";
    private static FavorisDAO FavorisDAO = null;
    private final String URL_SERVEUR = "https://storedmcd.000webhostapp.com/";
    private ArrayList ListeProduitsFav;
    private ActiviteEnAttenteFavoris activite;

    public FavorisDAO(ActiviteEnAttenteFavoris activite) {
        this.activite = activite;
    }

    public static FavorisDAO getInstance(ActiviteEnAttenteFavoris activite) {
        if (FavorisDAO == null) {
            FavorisDAO = new FavorisDAO(activite);
        }
        return FavorisDAO;

    }

    @Override
    public void traiteResultatRequete(String[] result) {


        Log.e("Res ADDFAV", result[0]);
        Log.e("Res ADDFAV 1", result[1]);

        this.activite.notifyRetourFavoris(result[0], result[1]);

    }


    public void AddFavoris(int id_cl, int id_pdt) {


        RequeteSQL req = new RequeteSQL(activite, this);
        req.execute(TABLE, URL_SERVEUR + "modfav.php?id_client=" + id_cl + "&id_produit=" + id_pdt);
        Log.e("requete FAV", "req ajout fav executed");

    }
}
