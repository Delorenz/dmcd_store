package iutmetz.wmce.dmcd_store_dev;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

import iutmetz.wmce.dmcd_store_dev.interfaces.ActiviteEnAttenteFindClient;
import iutmetz.wmce.dmcd_store_dev.interfaces.IGestionPanierCategorie;
import iutmetz.wmce.dmcd_store_dev.modele.Client;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClientFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ClientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class ClientFragment extends Fragment implements ActiviteEnAttenteFindClient, View.OnClickListener {
    public static final String TAG = "cl_tag";
    IGestionPanierCategorie ParentActivity;

    private Client client;
    private TextView text;
    private EditText login;
    private EditText mdp;
    private Button login_btn;
    private Button nv_compte;

    private OnFragmentInteractionListener mListener;

    public ClientFragment() {
        // Required empty public constructor
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

        if (savedInstanceState==null) {

            // ClientDAO.getInstance(this).findClient();


        }else{
            this.client = (Client) savedInstanceState.getSerializable("client");
            this.text.setText(client.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_client, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void notifyRetourRequeteFindClient(String code, Client client) {
        this.client = client;
        this.text.setText(client.toString());

    }

    @Override
    public void notifyRetourRequetePOST(String code, Client client) {
        this.client = client;
        this.text.setText(client.toString());
    }

    @Override
    public void onClick(View v) {
        if(v == this.getActivity().findViewById(R.id.login)){
            Log.e("Login pressed", "ok");
            if( login.getText().length()  != 0 && mdp.getText().length() != 0 ){
                Client cl = new Client();
                cl.setIdentifiant(String.valueOf(login.getText()));
                cl.setMot_de_passe(String.valueOf(mdp.getText()));

                ClientDAO.getInstance(this).postData(cl);
                //Envoie des données en POST, comparaison login/mdp et si OK redirection vers Infos Client
                //Si NOK message d'erreur + proposition creation nv compte
                //IGestionPanierCategorie activite = (IGestionPanierCategorie) this.getActivity();
                // activite.DisplayInfoClientFragment();

            }
        }

        if(v == nv_compte){
            //redirection page inscription
            Log.e("nv compte pressed", "ok");
            IGestionPanierCategorie activite = (IGestionPanierCategorie) this.getActivity();
            activite.DisplayNvClientFragment();
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */




    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    @Override
    public void onStart() {
        super.onStart();
        ParentActivity = (IGestionPanierCategorie) this.getActivity();
        this.text = this.getActivity().findViewById(R.id.info_client);
        this.login = this.getActivity().findViewById(R.id.login_co);
        this.mdp = this.getActivity().findViewById(R.id.mdp_co);
        this.login_btn = this.getActivity().findViewById(R.id.login);
        this.nv_compte = this.getActivity().findViewById(R.id.nvcompte);
        this.nv_compte.setOnClickListener(this);
        this.login_btn.setOnClickListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("info_cl", (Serializable) this.client);
    }

}
