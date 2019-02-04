/**
 * Classe utilisée par la ListView pour afficher les items de catégories
 *
 */
package iutmetz.wmce.dmcd_store_dev;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import iutmetz.wmce.dmcd_store_dev.modele.Categorie;

public class CategoriesAdapter extends ArrayAdapter<Categorie> {

    private  ArrayList<Bitmap> listeImagesCategories;
    private ArrayList<Categorie> listeCategories;

    public CategoriesAdapter(Context context, ArrayList<Categorie> liste, ArrayList<Bitmap> listeImagesCategories) {
        super(context, 0, liste);
        this.listeCategories = liste;
        this.listeImagesCategories = listeImagesCategories;
    }

    /**
     * Méthde chargée d'afficher une ligne(=un item) de la liste
     * @param position numéro de la ligne à afficher
     * @param convertView la vue d'affichage de la ligne  (permet de ne pas tout recréer si c'est inutile)
     * @param parent la vue parente
     * @return la vue d'affichage de la ligne
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView==null) {
            // chargement du XML qui contient le layout d'affichage de la ligne
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_liste_categories, parent, false);
        }
            // affichage du titre de la catégorie
            TextView tv = convertView.findViewById(R.id.ilc_titre);
            tv.setText(this.listeCategories.get(position).getTitre());

            // affichage de l'image (conversion d'une chaîne en drawable)
            ImageView img = convertView.findViewById(R.id.ilc_visuel);


            int id = getContext().getResources().getIdentifier(
                    this.listeCategories.get(position).getVisuel(),
                    "drawable",
                    getContext().getPackageName());
            img.setImageResource(id);



        if (listeImagesCategories.get(position) == null){
            int id_img = getContext().getResources().getIdentifier(this.listeCategories.get(position).getVisuel(),"drawable",getContext().getPackageName());
            img.setImageResource(id_img);
        }else{

            Bitmap bmp  = listeImagesCategories.get(position);
            if(bmp != null){
                img.setImageBitmap(bmp);
            }

        }

        return convertView;


    }
}
