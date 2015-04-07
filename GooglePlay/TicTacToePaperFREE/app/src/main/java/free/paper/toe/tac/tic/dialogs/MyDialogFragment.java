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


public abstract class MyDialogFragment extends DialogFragment implements View.OnClickListener {

    private TextView mTextView;
    private Button mYesButton;
    private Button mNoButton;


//    public static MyDialogFragment newInstance( OnDialogClickListenr listenr ){
//        MyDialogFragment f = new MyDialogFragment();
//
//        return new MyDialogFragment();
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog);

        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialogfragment_my, container, false);

        mTextView = (TextView) v.findViewById(R.id.editTextView);
        if(setText() != null) {
            mTextView.setText(setText());
        }
        mYesButton = (Button) v.findViewById(R.id.yesButton);
        mNoButton = (Button) v.findViewById(R.id.noButton);

        mYesButton.setOnClickListener(this);
        mNoButton.setOnClickListener(this);

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! change - in MainActivity
//        Typeface typeface = TypeFaces.getTypeFace(getActivity(), "fonts/tempussansitc.ttf");
//        mTextView.setTypeface(typeface, Typeface.NORMAL);
//        mYesButton.setTypeface(typeface, Typeface.NORMAL);
//        mNoButton.setTypeface(typeface, Typeface.NORMAL);

        return v;
    }

    public void setButtonsVisibility(boolean is) {
        if(!is && mNoButton != null && mYesButton != null) {
            mNoButton.setVisibility(View.INVISIBLE);
            mYesButton.setVisibility(View.INVISIBLE);
        }
    }

    public abstract String setText();

    @Override
    public void onClick(View v) {
        if(v.getId() == mYesButton.getId()) {
            dismiss();
            yesButtonClicked();
        } else if(v.getId() == mNoButton.getId()) {
            dismiss();
            noButtonClicked();
        } else {
            dismiss();
        }
    }



    public abstract void yesButtonClicked();
    public abstract void noButtonClicked();

}
