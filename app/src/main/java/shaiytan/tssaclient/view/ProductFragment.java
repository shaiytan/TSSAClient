package shaiytan.tssaclient.view;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import shaiytan.tssaclient.R;
import shaiytan.tssaclient.model.Product;
import shaiytan.tssaclient.model.Review;
import shaiytan.tssaclient.model.ReviewModel;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends Fragment {
    private static final String PRODUCT = "product";
    private Product product;
    private ReviewModel reviews;
    private ListView reviewsView;
    private ImageView image;
    private TextView title;
    private TextView desc;
    private TextView revCount;

    public ProductFragment() {
        // Required empty public constructor
    }

    public static ProductFragment newInstance(Product product) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putSerializable(PRODUCT, product);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            product = (Product) args.getSerializable(PRODUCT);
        }
        reviews = new ReviewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        image = (ImageView) view.findViewById(R.id.img);
        Picasso.with(getContext())
                .load("http://smktesting.herokuapp.com/static/"+product.getImageID())
                .into(image);
        title = (TextView) view.findViewById(R.id.product_title);
        title.setText(product.getTitle());
        desc = (TextView) view.findViewById(R.id.desc);
        desc.setText(product.getText());
        revCount = (TextView) view.findViewById(R.id.reviews);
        reviewsView = (ListView) view.findViewById(R.id.review_list);
        new AsyncTask<Object, Object, List<Review>>() {
            @Override
            protected List<Review> doInBackground(Object... params) {
                return reviews.getReviews(product.getId());
            }
            @Override
            protected void onPostExecute(List<Review> reviewsList) {
                reviewsView.setAdapter(new ReviewsAdapter(getActivity(), reviewsList));
                revCount.setText(String.format("Отзывы (%d):",reviewsList.size()));
            }
        }.execute();
        return view;
    }

}
