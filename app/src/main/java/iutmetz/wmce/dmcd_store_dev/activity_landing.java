package iutmetz.wmce.dmcd_store_dev;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import iutmetz.wmce.dmcd_store_dev.interfaces.IGestionPanierCategorie;
import iutmetz.wmce.dmcd_store_dev.modele.Client;


public class activity_landing extends AppCompatActivity implements IGestionPanierCategorie ,ClientFragment.OnFragmentInteractionListener, NvClientFragment.OnFragmentInteractionListener, InfoClientFragment.OnFragmentInteractionListener  {

    private CategoriesFragment categorieFragment;
    private VenteCatalogueFragment venteCatalogueFragment;
    private ClientFragment clientFragment;
    private InfoClientFragment infoClientFragment;

    public Client getCl_connected() {
        return cl_connected;
    }

    public void setCl_connected(Client cl_connected) {
        this.cl_connected = cl_connected;
    }

    private Client cl_connected = null;


    // Le panier ne sert à rien dans cet exemple
    // il a été laissé car servira par la suite...
    private double panier;
    private NvClientFragment NvClientFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        if (savedInstanceState == null) {
            FragmentManager fm = getSupportFragmentManager();
            this.categorieFragment = (CategoriesFragment) fm.findFragmentByTag(CategoriesFragment.TAG);
            if (this.categorieFragment == null) {
                this.categorieFragment = new CategoriesFragment();
            }
            fm.beginTransaction().add(R.id.fragment1, this.categorieFragment, CategoriesFragment.TAG).commit();

            this.panier = 0;
        } else {
            this.panier = savedInstanceState.getDouble("panier");
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putDouble("panier", this.panier);
    }

    /**
     * clic sur le bouton retour de la barre de menu (en haut !)
     *
     * @param item l'item de menu cliqué
     * @return true si tout s'est bien passé
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.onClickRetour();
        }

        return true;
    }

    /**
     * clic sur le bouton back du téléphone (celui du bas)
     */
    @Override
    public void onBackPressed() {
        this.onClickRetour();
    }

    /**
     * Gestion du retour vers l'activité CategoriesActivity
     * Permet de factoriser le code pour les deux méthodes précédentes
     */

    public void onClickRetour() {

        FragmentManager fm = this.getSupportFragmentManager();
        this.venteCatalogueFragment = (VenteCatalogueFragment) fm.findFragmentByTag(VenteCatalogueFragment.TAG);
        if (this.venteCatalogueFragment != null && this.venteCatalogueFragment.isAdded()) {
            fm.popBackStack();
            this.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void DisplayProductFragment(int id) {

        FragmentManager fm = this.getSupportFragmentManager();
        this.venteCatalogueFragment = (VenteCatalogueFragment) fm.findFragmentByTag(VenteCatalogueFragment.TAG);
        if (this.venteCatalogueFragment == null) {
            this.venteCatalogueFragment = new VenteCatalogueFragment();
        }

        Bundle bundle = new Bundle();
        bundle.putInt("id_categ", id);
        this.venteCatalogueFragment.setArguments(bundle);

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment1, this.venteCatalogueFragment, VenteCatalogueFragment.TAG);
        ft.addToBackStack(null);
        ft.commit();
    }

    public double getPanier(){
        return this.panier;
    }

    @Override
    public void setPanier(double tarif) {
        this.panier = panier + tarif;
    }

    @Override
    public void onClickAjoutPanier(VenteCatalogueFragment venteCatalogueFragment, int noProduitCourant) {
    /*    Toast toast = new Toast(getApplicationContext());
        toast = Toast.makeText(this, this.getString(R.string.AjoutPanier, noProduitCourant+1), LENGTH_LONG);
        toast.show();*/
    }

    @Override
    public void DisplayClientFragment() {


        FragmentManager fm = this.getSupportFragmentManager();
        this.clientFragment = (ClientFragment) fm.findFragmentByTag(ClientFragment.TAG);
        if (this.clientFragment == null) {
            this.clientFragment = new ClientFragment();
        }

        //  Bundle bundle = new Bundle();
        //  bundle.putInt("id_categ", id);
        //  this.venteCatalogueFragment.setArguments(bundle);

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment1, this.clientFragment, ClientFragment.TAG);
        ft.addToBackStack(null);
        ft.commit();

    }

    @Override
    public void DisplayNvClientFragment() {
        FragmentManager fm = this.getSupportFragmentManager();
        this.NvClientFragment = (NvClientFragment) fm.findFragmentByTag(iutmetz.wmce.dmcd_store_dev.NvClientFragment.TAG);
        if (this.NvClientFragment == null) {
            this.NvClientFragment = new NvClientFragment();
        }

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment1, this.NvClientFragment, iutmetz.wmce.dmcd_store_dev.NvClientFragment.TAG);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void DisplayInfoClientFragment() {
        FragmentManager fm = this.getSupportFragmentManager();
        this.infoClientFragment = (InfoClientFragment) fm.findFragmentByTag(InfoClientFragment.TAG);
        if (this.infoClientFragment == null) {
            this.infoClientFragment= new InfoClientFragment();
        }

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment1, this.infoClientFragment, InfoClientFragment.TAG);
        ft.addToBackStack(null);
        ft.commit();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
