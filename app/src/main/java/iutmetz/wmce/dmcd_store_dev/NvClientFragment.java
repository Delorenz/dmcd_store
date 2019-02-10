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

import iutmetz.wmce.dmcd_store_dev.interfaces.ActiviteEnAttenteFindClient;
import iutmetz.wmce.dmcd_store_dev.interfaces.ActiviteEnAttenteUpdateClient;
import iutmetz.wmce.dmcd_store_dev.interfaces.IGestionPanierCategorie;
import iutmetz.wmce.dmcd_store_dev.modele.Client;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NvClientFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NvClientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NvClientFragment extends Fragment implements View.OnClickListener, ActiviteEnAttenteFindClient, ActiviteEnAttenteUpdateClient {

    public static final String TAG ="nv_client_tag";
    IGestionPanierCategorie ParentActivity;
    private EditText nom_form;
    private EditText prenom_form;
    private EditText identifiant_form;
    private EditText mdp_form;
    private EditText adr_numero_form;
    private EditText adr_voie_form;
    private EditText adr_cp_form;
    private EditText adr_ville_form;
    private EditText adr_pays_form;
    private Client cl = new Client();
    private Button validate;

    private OnFragmentInteractionListener mListener;

    public NvClientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NvClientFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NvClientFragment newInstance(String param1, String param2) {
        NvClientFragment fragment = new NvClientFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }

        if (savedInstanceState == null) {

            // ClientDAO.getInstance(this).findClient();


        } else {
            //this.client = (Client) savedInstanceState.getSerializable("client");
            //this.text.setText(client.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nv_client, container, false);
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
    public void onClick(View v) {
        if (v == validate) {
            if (ParentActivity.getCl_connected() == null) {


                cl.setNom(String.valueOf(nom_form.getText()));
                cl.setPrenom(String.valueOf(prenom_form.getText()));
                cl.setIdentifiant(String.valueOf(identifiant_form.getText()));
                cl.setMot_de_passe(String.valueOf(mdp_form.getText()));
                cl.setAdr_numero(Integer.parseInt(String.valueOf(adr_numero_form.getText())));
                cl.setAdr_voie(String.valueOf(adr_voie_form.getText()));
                cl.setAdr_code_postal(Integer.parseInt(String.valueOf(adr_cp_form.getText())));
                cl.setAdr_ville(String.valueOf(adr_ville_form.getText()));
                cl.setAdr_pays(String.valueOf(adr_pays_form.getText()));
                Log.e("Recup champs", cl.toString());

                ClientDAO.getInstance(this).postDataCreationCompte(cl);
                Log.e("NVCL", "request for client creation launched");

                ClientDAO.getInstance(this).postDataConnexion(cl);
                Log.e("NVCL", "check if successfully persisted");
            } else {


                cl.setNom(String.valueOf(nom_form.getText()));
                cl.setPrenom(String.valueOf(prenom_form.getText()));
                cl.setIdentifiant(String.valueOf(identifiant_form.getText()));
                cl.setMot_de_passe(String.valueOf(mdp_form.getText()));
                cl.setAdr_numero(Integer.parseInt(String.valueOf(adr_numero_form.getText())));
                cl.setAdr_voie(String.valueOf(adr_voie_form.getText()));
                cl.setAdr_code_postal(Integer.parseInt(String.valueOf(adr_cp_form.getText())));
                cl.setAdr_ville(String.valueOf(adr_ville_form.getText()));
                cl.setAdr_pays(String.valueOf(adr_pays_form.getText()));
                cl.setId_client(ParentActivity.getCl_connected().getId_client());


                //Envoi requete update;
                try {
                    Log.e("dump cl", cl.toString());
                    ClientUpdateDAO.getInstance(this).postDataUpdate(cl);
                    //ParentActivity.setCl_connected(cl);
                } catch (Exception e) {
                    Log.e("Error update", e.getMessage());
                }


            }

        }

    }

    @Override
    public void notifyRetourRequeteFindClient(String code, Client client) {
        Log.e("NVCL  save/update cl", client.toString());
        if (client != null) {
            ParentActivity.setCl_connected(client);
            IGestionPanierCategorie activite = (IGestionPanierCategorie) this.getActivity();
            activite.DisplayInfoClientFragment();
        }
    }

    @Override
    public void notifyRetourRequeteUpdateClient(String code, Client client) {
        Log.e("UPCL", "retour requete update client");
        // ParentActivity.setCl_connected(client);
        //ClientDAO.getInstance(this).postDataConnexion(cl);
        //ParentActivity.setCl_connected(cl);

        IGestionPanierCategorie activite = (IGestionPanierCategorie) this.getActivity();
        activite.DisplayInfoClientFragment();


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
        this.nom_form = this.getActivity().findViewById(R.id.nom_form);
        this.prenom_form = this.getActivity().findViewById(R.id.prenom_form);
        this.identifiant_form = this.getActivity().findViewById(R.id.identifiant_form);
        this.mdp_form = this.getActivity().findViewById(R.id.mdp_form);
        this.adr_numero_form = this.getActivity().findViewById(R.id.adr_num_form);
        this.adr_voie_form = this.getActivity().findViewById(R.id.adr_voie_form);
        this.adr_cp_form = this.getActivity().findViewById(R.id.adr_cp_form);
        this.adr_ville_form = this.getActivity().findViewById(R.id.adr_ville_form);
        this.adr_pays_form = this.getActivity().findViewById(R.id.adr_pays_form);
        this.validate = this.getActivity().findViewById(R.id.button_validate_nvcl);
        this.validate.setOnClickListener(this);

        if (ParentActivity.getCl_connected() != null) {
            Client c = ParentActivity.getCl_connected();

            nom_form.setText(c.getNom());
            prenom_form.setText(c.getPrenom());
            identifiant_form.setText(c.getIdentifiant());
            mdp_form.setText(c.getMot_de_passe());
            adr_numero_form.setText(String.valueOf(c.getAdr_numero()));
            adr_voie_form.setText(c.getAdr_voie());
            adr_cp_form.setText(String.valueOf(c.getAdr_code_postal()));
            adr_ville_form.setText(c.getAdr_ville());
            adr_pays_form.setText(c.getAdr_pays());
        }
    }
}
