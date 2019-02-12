package iutmetz.wmce.dmcd_store_dev;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import iutmetz.wmce.dmcd_store_dev.interfaces.ActiviteEnAttenteMention;
import iutmetz.wmce.dmcd_store_dev.interfaces.DAO;

public class MentionDAO implements DAO {

    public static final String TABLE = "Mention";
    private static MentionDAO MentionDAO = null;
    private final String URL_SERVEUR = "https://storedmcd.000webhostapp.com/";
    private ArrayList ListeProduitsFav;
    private ActiviteEnAttenteMention activite;

    public MentionDAO(ActiviteEnAttenteMention activite) {
        this.activite = activite;
    }

    public static MentionDAO getInstance(ActiviteEnAttenteMention activite) {
        if (MentionDAO == null) {
            MentionDAO = new MentionDAO(activite);
        }
        return MentionDAO;

    }

    @Override
    public void traiteResultatRequete(String[] result) {

        JSONArray array = null;
        try {
            array = new JSONArray(result[1]);
            JSONObject row = array.getJSONObject(0);
            String res = String.valueOf(row.get("mentions"));
            this.activite.notifyRetourRequeteMention(res);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.e("ResMENTION", result[0]);
        Log.e("Res MENTION1", result[1]);


    }

    public void DisplayMention() {


        RequeteSQL req = new RequeteSQL(activite, this);
        req.execute(TABLE, URL_SERVEUR + "mentions.php");
        Log.e("requete FAV", "req ajout fav executed");

    }
}
