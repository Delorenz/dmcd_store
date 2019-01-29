package iutmetz.wmce.dmcd_store_dev;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import iutmetz.wmce.dmcd_store_dev.interfaces.ActiviteEnAttenteFindProduits;
import iutmetz.wmce.dmcd_store_dev.interfaces.ActiviteEnAttenteImage;
import iutmetz.wmce.dmcd_store_dev.interfaces.ActiviteEnAttenteTaille;
import iutmetz.wmce.dmcd_store_dev.interfaces.IGestionPanierCategorie;
import iutmetz.wmce.dmcd_store_dev.modele.Produit;
import iutmetz.wmce.dmcd_store_dev.modele.Taille;


public class VenteCatalogueFragment extends Fragment implements View.OnClickListener, ActiviteEnAttenteFindProduits, ActiviteEnAttenteImage, ActiviteEnAttenteTaille {

    private int noProduitCourant = 0;
    private ArrayList<Produit> liste;
    private ImageView imgV;
    private Button btn_prev;
    private Button btn_next;
    private TextView titre;
    private TextView desc;
    private TextView prix;
    private Spinner tailles;
    private ImageButton Cart;
    ArrayAdapter<Taille> adapter;
    ArrayList<Taille> spinnerArray;
    private ImageView imgProduitZoom;
    IGestionPanierCategorie ParentActivity;
    public static final String TAG = "CatalogueDisplay_tag";
    private ArrayList<Bitmap> listeImages;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("on createView","Not implemented !");
        return null; //return inflater.inflate(R.layout.fragment_vente_catalogue, container, false);
    }


    public void onSaveInstanceState(Bundle outState) {

        Log.e("onSavedInstance","saving");
        super.onSaveInstanceState(outState);

        outState.putInt("noPullCourant", this.noProduitCourant);
        outState.putSerializable("liste", this.liste);
        outState.putSerializable("listeimg", this.listeImages);


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.e("onCreate","start");


        super.onActivityCreated(savedInstanceState);

        ((AppCompatActivity)this.getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ParentActivity = (IGestionPanierCategorie) this.getActivity();
        liste = new ArrayList<Produit>();
        spinnerArray = new ArrayList<Taille>();
        this.listeImages = new ArrayList<>();
        if(savedInstanceState==null) {

            Log.e("onCreate","noSave");
            ProduitDAO.getInstance(this).findProduitsByCateg(getArguments().getInt("id_categ"));



        }else{
            Log.e("onCreate","loading save");
            this.noProduitCourant = savedInstanceState.getInt("noPullCourant");
            this.liste = (ArrayList <Produit>)  savedInstanceState.getSerializable("liste");
            this.listeImages = (ArrayList<Bitmap>) savedInstanceState.getSerializable("listeimg");
            ProduitDAO.getInstance(this).findProduitsByCateg(getArguments().getInt("id_categ"));


        }


        Log.e("onCreate","end");

    }

    @SuppressLint("CutPasteId")
    @Override
    public void onStart() {
        super.onStart();

        Log.e("onStart","Not implemented !");

        /*
        this.imgV = (ImageView) this.getActivity().findViewById(R.id.imageView);
        this.btn_prev = (Button) this.getActivity().findViewById(R.id.button_prev);
        this.btn_next = (Button) this.getActivity().findViewById(R.id.button_next);
        this.titre = this.getActivity().findViewById(R.id.textView2);
        this.desc = this.getActivity().findViewById(R.id.desc);
        this.prix = this.getActivity().findViewById(R.id.prix);
        this.tailles= this.getActivity().findViewById(R.id.sp_taille);
        this.Cart = this.getActivity().findViewById(R.id.btn_bskt);
        this.imgProduitZoom = this.getActivity().findViewById(R.id.img_produit_zoom);
        btn_next.setOnClickListener(this);
        btn_prev.setOnClickListener(this);
        Cart.setOnClickListener(this);
        Log.e("onStart"," init end");

        adapter = new ArrayAdapter<Taille>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        tailles.setAdapter(adapter);
        Log.e("onStart"," adapter init end");

*/
        Log.e("onStart","end");

    }



    public void updateLayout(){

 Log.e("update","start");
 Log.e("nopdt", String.valueOf(noProduitCourant));
        if(this.noProduitCourant >=this.liste.size()-1){
            this.btn_next.setVisibility(View.GONE);
        }else{
            this.btn_next.setVisibility(View.VISIBLE);
        }
        if(this.noProduitCourant==0){
            btn_prev.setVisibility(View.GONE);
        }else{
            btn_prev.setVisibility(View.VISIBLE);
        }

        Log.e("liste", liste.toString());


        this.titre.setText(this.liste.get(this.noProduitCourant).getTitre());
        this.desc.setText(this.liste.get(this.noProduitCourant).getDescription());
        String px = new Double(this.liste.get(this.noProduitCourant).getTarif()).toString();
        this.prix.setText( px+"€");
        this.imgV.setImageBitmap(this.listeImages.get(this.noProduitCourant));
        TailleDAO.getInstance(this).findTaille(liste.get(noProduitCourant).getId());

        Log.e("update","end");
    }


    @Override
    public void onClick(View v) {
        /*
        if(v == getActivity().findViewById(R.id.button_next) && noProduitCourant<this.liste.size()){
            this.noProduitCourant++;
            TailleDAO.getInstance(this).findTaille(liste.get(noProduitCourant).getId());

            updateLayout();

        }

        if(v == getActivity().findViewById(R.id.button_prev)){
            if(this.noProduitCourant>=0){
                this.noProduitCourant--;

            }

            updateLayout();

        }

        if(v == getActivity().findViewById(R.id.btn_bskt)){
            this.ParentActivity.setPanier(this.liste.get(noProduitCourant).getTarif());
            this.ParentActivity.onClickAjoutPanier(this, this.liste.get(noProduitCourant).getId());


        }

        //ZOOM IMG
        if(v == getActivity().findViewById(R.id.imageView)){
            onClickImage(this.getActivity().getCurrentFocus());
        }
        if( v == getActivity().findViewById(R.id.img_produit_zoom)){
            onClickImageZoomed(getActivity().getCurrentFocus());
        }
        */
    }



    @Override
    public void notifyRetourRequeteFindProduits(String code, ArrayList liste) {

        Log.e("Produits","retour");

        this.liste.clear();
        this.liste.addAll(liste);


        this.chargeImages();


        updateLayout();

    }

    
    public void chargeImages(){
        Log.e("img","chargement");

        for(int i=0; i<=this.liste.size()-1;i++){
            this.listeImages.add(null);
            ImageFromURL chargement = new ImageFromURL(this);
            chargement.execute("https://infodb.iutmetz.univ-lorraine.fr/~delorenz2u/android/"+this.liste.get(i).getVisuel(),String.valueOf(i));
        }
    }

    @Override
    public void receptionneImage(Object[] resultats) {
        Log.e("img","retour");

        if(resultats[0] != null){
            int idx = Integer.parseInt(resultats[1].toString());
            Bitmap img = (Bitmap) resultats[0];
            this.listeImages.set(idx ,img);
            updateLayout();
        }

    }

    @Override
    public void notifyRetourRequeteFindTaille(String code, ArrayList liste) {
        Log.e("Tailles","retour");
        this.spinnerArray.clear();
        this.spinnerArray.addAll(liste);
        Log.e("retour taille data",liste.toString());

        adapter.notifyDataSetChanged();



    }

    public void onClickImage(View view) {
        this.btn_next.setVisibility(View.GONE);
        this.btn_prev.setVisibility(View.GONE);
        ImageView source = (ImageView) view;
        this.imgProduitZoom.setImageDrawable(source.getDrawable());
        this.imgProduitZoom.setVisibility(View.VISIBLE);
        this.imgProduitZoom.bringToFront();
        this.imgProduitZoom.invalidate();

    }

    public void onClickImageZoomed(View v) {

        this.btn_prev.setVisibility(View.VISIBLE);
        this.btn_next.setVisibility(View.VISIBLE);
        this.imgProduitZoom.setVisibility(View.GONE);
    }

}
