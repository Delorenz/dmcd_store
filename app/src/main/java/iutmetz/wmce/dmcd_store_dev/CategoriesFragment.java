package iutmetz.wmce.dmcd_store_dev;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import iutmetz.wmce.dmcd_store_dev.interfaces.ActiviteEnAttenteFindAll;
import iutmetz.wmce.dmcd_store_dev.interfaces.ActiviteEnAttenteImage;
import iutmetz.wmce.dmcd_store_dev.interfaces.IGestionPanierCategorie;
import iutmetz.wmce.dmcd_store_dev.modele.Categorie;



public class CategoriesFragment extends Fragment implements AdapterView.OnItemClickListener, ActiviteEnAttenteImage, ActiviteEnAttenteFindAll, View.OnClickListener {

    public static final String TAG = "cat_tag";

    private ArrayList<Categorie> listeCategories;
    //private double panier;
    IGestionPanierCategorie ParentActivity;
    private CategoriesAdapter adaptateur;
    private ArrayList <Bitmap> listeImagesCategories;
    private Button btn_cl;
    private TextView txtPanier;
    private Button btn_mention;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.listeCategories = new ArrayList<>();
        this.listeImagesCategories = new ArrayList<>();
         adaptateur = new CategoriesAdapter(
                this.getContext(), this.listeCategories, this.listeImagesCategories
        );

        if (savedInstanceState==null) {

            CategorieDAO.getInstance(this).findAll();


        } else {

            this.listeCategories.clear();
            this.listeCategories.addAll((ArrayList<Categorie>)savedInstanceState.getSerializable("modele"));
            this.chargeImages();
            this.adaptateur.notifyDataSetChanged();
        }


    }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_categories, container, false);
        }

        @Override
        public void onStart() {
            super.onStart();
            ParentActivity = (IGestionPanierCategorie) this.getActivity();
            this.txtPanier = this.getActivity().findViewById(R.id.txt_panier);
            ListView lvCategories = this.getActivity().findViewById(R.id.lv_liste);
            lvCategories.setAdapter(adaptateur);
            lvCategories.setOnItemClickListener(this);
            this.updatePanier();
            this.btn_cl = this.getActivity().findViewById(R.id.button_cl);
            this.btn_cl.setOnClickListener(this);
            this.btn_mention = this.getActivity().findViewById(R.id.btn_mention);
            this.btn_mention.setOnClickListener(this);
        }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("modele", this.listeCategories);
    }

    /**
     * clic sur un item de liste : lancement de l'activité VenteCatalgue
     * en spécifiant le requestCode : vc_vente
     */




    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        IGestionPanierCategorie activite = (IGestionPanierCategorie) this.getActivity();
        activite.DisplayProductFragment(this.listeCategories.get(position).getId());
    }


    /**
     * Dans le cadre de cet exemple, le panier ne sert à rien
     * il a néanmoins été conservé ici car il servira par la suite
     */



    private void updatePanier() {
        this.txtPanier.setText(String.format(getString(R.string.panier), ParentActivity.getPanier()));
    }

    @Override
    public void notifyRetourRequeteFindAll(String code, ArrayList liste) {
        this.listeCategories.clear();
        this.listeCategories.addAll(liste);
        Log.e("listeCat" , liste+"");
        this.chargeImages();
        this.adaptateur.notifyDataSetChanged();
    }


    public void chargeImages(){

        for(int i=0; i<this.listeCategories.size();i++){
            this.listeImagesCategories.add(null);
            ImageFromURL chargement = new ImageFromURL(this);
            chargement.execute("https://storedmcd.000webhostapp.com/"+this.listeCategories.get(i).getVisuel(),String.valueOf(i));
        }
    }

    @Override
    public void receptionneImage(Object[] resultats) {

        if(resultats[0] != null){
            int idx = Integer.parseInt(resultats[1].toString());
            Bitmap img = (Bitmap) resultats[0];
            this.listeImagesCategories.set(idx ,img);
            this.adaptateur.notifyDataSetChanged();
        }

    }

    @Override
    public void onClick(View v) {
        if(v == btn_cl){
            IGestionPanierCategorie activite = (IGestionPanierCategorie) this.getActivity();
            activite.DisplayClientFragment();
        }
        if (v == btn_mention) {
            IGestionPanierCategorie activite = (IGestionPanierCategorie) this.getActivity();
            activite.DisplayMentionFragment();
        }
    }
}
