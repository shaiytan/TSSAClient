package shaiytan.tssaclient.view;


import android.os.*;
import android.support.v4.app.*;
import android.view.*;
import android.widget.*;

import shaiytan.tssaclient.R;
import shaiytan.tssaclient.model.*;


public class ReviewFormFragment extends Fragment {
    private static final String PRODUCT_ID = "product_id";

    private int id;
    private String token="";
    private EditText commentView;
    private RatingBar ratingView;
    private CommentPostModel commentLoader;
    private int rate;
    private String comment;

    public ReviewFormFragment() { }

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
        commentLoader = new CommentPostModel();
    }

    //установить видимость фрагмента при возврате из активити авторизации
    @Override
    public void onResume() {
        super.onResume();
        setToken(token);
    }

    // Скрыть если пользователь не авторизирован
    public void setToken(String token) {
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
        View view = inflater.inflate(R.layout.fragment_review_form, container, false);
        commentView = (EditText) view.findViewById(R.id.et_comment_text);
        ratingView = (RatingBar) view.findViewById(R.id.rating_bar);
        Button submit = (Button) view.findViewById(R.id.btn_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!commentView.getText().toString().isEmpty())submitComment();
            }
        });
        return view;
    }

    private void submitComment(){
        rate = ratingView.getProgress();
        comment = commentView.getText().toString();
        new AsyncTask<Void,Void,Integer>(){
            @Override
            protected Integer doInBackground(Void... params) {
                return commentLoader.postComment(id, new Comment(rate, comment), token);
            }
            @Override
            protected void onPostExecute(Integer res) {
                if(res<0) Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                ratingView.setProgress(4);
                commentView.setText("");
                ((MainActivity) getActivity()).updateReviewsList();
            }
        }.execute();
    }
}
