package iutmetz.wmce.dmcd_store_dev;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import iutmetz.wmce.dmcd_store_dev.interfaces.ActiviteEnAttente;
import iutmetz.wmce.dmcd_store_dev.interfaces.DAO;


public class RequeteSQL extends AsyncTask<String, Void, String[]> {

    private ActiviteEnAttente activite;
    private DAO dao;


    public RequeteSQL(ActiviteEnAttente activite, DAO dao) {

        this.activite = activite;
        this.dao = dao;

    }



    @Override
    protected String[] doInBackground(String... codeEtUrl) {

        String urlRequete = codeEtUrl[1];
        StringBuilder resultat= new StringBuilder(1024);
        try{
            final HttpURLConnection conn = (HttpURLConnection) new URL(urlRequete).openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");

            conn.setDoInput(true);
            conn.connect();
            InputStream input = conn.getInputStream();

            BufferedReader in = new BufferedReader( new InputStreamReader(input));
            String line ="";
            while((line = in.readLine()) != null){
                resultat.append(line);
            }
            in.close();
        }catch(Exception e){
            Log.e("Requete SQL ", "Error : "+e.getMessage());


        }
        return new String[] {codeEtUrl[0], resultat.toString()};
    }

    @Override
    protected void onPostExecute(String[] result){
        this.dao.traiteResultatRequete(result);
    }
}
