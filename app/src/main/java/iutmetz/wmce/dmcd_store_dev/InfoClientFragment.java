package iutmetz.wmce.dmcd_store_dev;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import iutmetz.wmce.dmcd_store_dev.interfaces.IGestionPanierCategorie;
import iutmetz.wmce.dmcd_store_dev.modele.Client;

public class InfoClientFragment extends Fragment {

    public static final String TAG = "info_client_tag";


    private OnFragmentInteractionListener mListener;
    IGestionPanierCategorie ParentActivity;

    private TextView nom_info;
    private TextView prenom_info;
    private TextView identifiant_info;
    private TextView mdp_info;
    private TextView adr_numero_info;
    private TextView adr_voie_info;
    private TextView adr_cp_info;
    private TextView adr_ville_info;
    private TextView adr_pays_info;


    public InfoClientFragment() {

        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onStart() {
        super.onStart();
        ParentActivity = (IGestionPanierCategorie) this.getActivity();
        this.nom_info = this.getActivity().findViewById(R.id.nom_info);
        this.prenom_info = this.getActivity().findViewById(R.id.prenom_info);
        this.identifiant_info = this.getActivity().findViewById(R.id.identifiant_info);
        this.mdp_info = this.getActivity().findViewById(R.id.mdp_info);
        this.adr_numero_info = this.getActivity().findViewById(R.id.adr_numero_info);
        this.adr_voie_info = this.getActivity().findViewById(R.id.adr_voie_info);
        this.adr_cp_info = this.getActivity().findViewById(R.id.adr_cp_info);
        this.adr_ville_info = this.getActivity().findViewById(R.id.adr_ville_info);
        this.adr_pays_info = this.getActivity().findViewById(R.id.adr_pays_info);

        if (ParentActivity.getCl_connected() != null) {
            Client c = ParentActivity.getCl_connected();
            nom_info.setText(c.getNom());
            prenom_info.setText(c.getPrenom());
            identifiant_info.setText(c.getIdentifiant());
            mdp_info.setText(c.getMot_de_passe());
            adr_numero_info.setText(String.valueOf(c.getAdr_numero()));
            adr_cp_info.setText(String.valueOf(c.getAdr_code_postal()));
            adr_voie_info.setText(c.getAdr_voie());
            adr_ville_info.setText(c.getAdr_ville());
            adr_pays_info.setText(c.getAdr_pays());
        } else {
            Log.e("INFO CL", "Non connecté !");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_client, container, false);
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

    }
}
