package shaiytan.tssaclient.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import shaiytan.tssaclient.R;


public class ReviewFormFragment extends Fragment {
    private static final String PRODUCT_ID = "product_id";

    private int id;
    private String token="";


    public ReviewFormFragment() {
        // Required empty public constructor
    }

    public static ReviewFormFragment newInstance(int id) {
        ReviewFormFragment fragment = new ReviewFormFragment();
        Bundle args = new Bundle();
        args.putInt(PRODUCT_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(PRODUCT_ID);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setToken(token);
    }

    public void setToken(String token)
    {
        this.token=token;
        FragmentManager fragmentManager = getFragmentManager();
        if(token.isEmpty()){
            fragmentManager.beginTransaction().hide(this).commit();
        } else if(isHidden()&&isResumed()){
            fragmentManager.beginTransaction().show(this).commit();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review_form, container, false);
    }

}
