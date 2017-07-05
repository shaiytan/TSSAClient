package shaiytan.tssaclient.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import shaiytan.tssaclient.R;


public class ReviewFormFragment extends Fragment {
    private static final String PRODUCT_ID = "product_id";
    private static final String AUTH_TOKEN = "auth_token";

    private int id;
    private String token;


    public ReviewFormFragment() {
        // Required empty public constructor
    }

    public static ReviewFormFragment newInstance(int id, String token) {
        ReviewFormFragment fragment = new ReviewFormFragment();
        Bundle args = new Bundle();
        args.putInt(PRODUCT_ID, id);
        args.putString(AUTH_TOKEN, token);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(PRODUCT_ID);
            token = getArguments().getString(AUTH_TOKEN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review_form, container, false);
    }
    
}
