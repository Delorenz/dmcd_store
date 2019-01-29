/**
 * Affichage de la boîte de dialogue d'alerte RESISTANTE au pivot
 *
 */
package iutmetz.wmce.dmcd_store_dev.tools;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import iutmetz.wmce.dmcd_store_dev.R;

public class AnnulerAlerte extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
/*
        Activity activite = getActivity();

        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(activite, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(activite);
        }

        builder.setMessage(R.string.confirm_phrase)
                .setTitle(R.string.confirm_titre);

        DialogInterface.OnClickListener ecouteur = (DialogInterface.OnClickListener) activite;
        builder.setPositiveButton(R.string.confirm_oui, ecouteur);
        builder.setNegativeButton(R.string.confirm_non, ecouteur);
*/
        Log.e("alert","Annuler alert not implemented!");
        return null;  //builder.create();
    }

}


