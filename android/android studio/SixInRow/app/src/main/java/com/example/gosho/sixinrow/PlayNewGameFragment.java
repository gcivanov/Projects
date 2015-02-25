package com.example.gosho.sixinrow;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by gosho on 7.2.2015 г..
 */
public abstract class PlayNewGameFragment extends DialogFragment{

    private TextView tv;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.fragment_dialog_play_new_game, null);
        tv = (TextView) view.findViewById(R.id.dialogTextView);
        tv.setText(textTextView());

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        onYesButtonClick();
                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        onNoButtonClick();
                    }
                });
        return builder.create();
    }

    public abstract void onYesButtonClick();
    public abstract void onNoButtonClick();
    public abstract String textTextView();

}