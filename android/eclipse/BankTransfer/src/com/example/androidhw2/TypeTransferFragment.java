package com.example.androidhw2;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class TypeTransferFragment extends Fragment implements OnItemClickListener {
	
	
	
	private OnTypeTransferFragmentInteractionListener mListener;
	
	private ListView mTypeTransferListView;
	private ArrayAdapter<String> adapter;
	
	public TypeTransferFragment(){
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //бързо и лесно можеш да разширяваш транзак
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
        		new String[]{ TypeTransfersTags.BETWEEN_OWN_ACCOUNTS, TypeTransfersTags.TO_OTHER_ACCOUNTS });
        
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_typetransfer, container, false);

        mTypeTransferListView = (ListView) view.findViewById(R.id.typeTransferListView);
        mTypeTransferListView.setAdapter(adapter);
        
        mTypeTransferListView.setOnItemClickListener(this);
        
        return view;
    }
    

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		switch (position) {
		case 0:
			mListener.onTypeTransactionsClick(TypeTransfersTags.BETWEEN_OWN_ACCOUNTS);
			break;

		case 1:
			mListener.onTypeTransactionsClick(TypeTransfersTags.TO_OTHER_ACCOUNTS);
			break;
		}
		
	}
    

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnTypeTransferFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnTypeTransferFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    

    public interface OnTypeTransferFragmentInteractionListener {
    	public void onTypeTransactionsClick(String typeTag);
    	
    }

	
}
