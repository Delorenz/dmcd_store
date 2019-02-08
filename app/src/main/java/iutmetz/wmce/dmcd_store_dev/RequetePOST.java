/*
package iutmetz.wmce.dmcd_store_dev;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import iutmetz.wmce.dmcd_store_dev.interfaces.ActiviteEnAttente;
import iutmetz.wmce.dmcd_store_dev.modele.Client;


public class RequetePOST extends AsyncTask<String, Void, String[]> {

    private ActiviteEnAttente activite;
    private ClientDAO dao;
    private JSONObject jsonParam;


    public RequetePOST(ActiviteEnAttente activite, ClientDAO dao, Client params) {

        this.activite = activite;
        this.dao = dao;
        jsonParam = new JSONObject();
        if (params.getIdentifiant() != null) {
            try {
                this.jsonParam.put("identifiant", params.getIdentifiant());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (params.getMot_de_passe() != null) {
            try {
                this.jsonParam.put("mot_de_passe", params.getMot_de_passe());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }


    @Override
    protected String[] doInBackground(String... codeEtUrl) {

        String urlRequete = codeEtUrl[1];
        StringBuilder resultat = new StringBuilder(1024);
        try {
            final HttpsURLConnection conn = (HttpsURLConnection) new URL(urlRequete).openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, StandardCharsets.UTF_8));
            writer.write(getPostDataString(jsonParam));

            writer.flush();
            writer.close();
            os.close();
            Log.e("STATUS", String.valueOf(conn.getResponseCode()));
            Log.e("MSG", conn.getResponseMessage());


            // conn.connect();
            InputStream input = conn.getInputStream();

            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            String line = "";
            while ((line = in.readLine()) != null) {
                resultat.append(line);
            }
            in.close();
        } catch (Exception e) {
            Log.e("Requete POST ", "Error : " + e.getMessage());


        }
        Log.e("Res doInBackground", resultat.toString());
        return new String[]{codeEtUrl[0], resultat.toString()};
    }


    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while (itr.hasNext()) {

            String key = itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }

        return result.toString();
    }

    @Override
    protected void onPostExecute(String[] result) {
        this.dao.traiteResultatRequetePOST(result);
    }
}
*/