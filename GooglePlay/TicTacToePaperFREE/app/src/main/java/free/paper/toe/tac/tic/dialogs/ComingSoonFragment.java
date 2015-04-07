package free.paper.toe.tac.tic.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import free.paper.toe.tac.tic.tictactoepaperfree.R;

public class ComingSoonFragment extends DialogFragment implements View.OnClickListener {

    private TextView mTextView;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog);

        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mTextView = new TextView(getActivity());
        mTextView.setTextSize(32);
        mTextView.setPadding(21,21,21,21);
        mTextView.setBackgroundResource(R.drawable.dialog_background);
        mTextView.setText("COMING SOON!");


        return mTextView;
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }


}
