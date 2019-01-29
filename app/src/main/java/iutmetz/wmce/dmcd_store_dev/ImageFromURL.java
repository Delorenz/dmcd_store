package iutmetz.wmce.dmcd_store_dev;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;

import iutmetz.wmce.dmcd_store_dev.interfaces.ActiviteEnAttenteImage;

public class ImageFromURL extends AsyncTask<String,Void,Object[]> {
    private ActiviteEnAttenteImage activite;

    public ImageFromURL(ActiviteEnAttenteImage activite) {
        this.activite = activite;
    }

    @Override
    protected Object[] doInBackground(String... urlEtIndice) {
        String urlImage = urlEtIndice[0];
        Bitmap image = null;
        Log.e("url", urlEtIndice[0]);
        try{
            InputStream in =new URL(urlImage).openStream();
            image=BitmapFactory.decodeStream(in);
            in.close();
        }catch (Exception e){
            Log.e("Pas d'image", "image générique à utiliser à la place !");
        }
        return new Object[] {image, urlEtIndice[1]};

    }


    protected  void onPostExecute(Object[] result){
        this.activite.receptionneImage(result);
    }
}
