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
import shaiytan.tssaclient.model.SiteAPI;
import shaiytan.tssaclient.model.Review;
import shaiytan.tssaclient.model.ReviewModel;


/**
 * Фрагмент отображает выбранный товар и список его отзывов
 */
public class ReviewsFragment extends Fragment {
    private static final String PRODUCT = "product";

    private Product product;
    private ReviewModel reviews;
    private ListView reviewsView;
    private TextView revCount;

    public ReviewsFragment() {}

    public static ReviewsFragment newInstance(Product product) {
        ReviewsFragment fragment = new ReviewsFragment();
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
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        reviewsView = (ListView) view.findViewById(R.id.list);

        // Карточку с товаром запихнуть в заголовок списка, чтобы его можно было пролистать
        View header = inflater.inflate(R.layout.header_layout, null);
        ImageView image = (ImageView) header.findViewById(R.id.img);
        Picasso.with(getContext())
                .load(SiteAPI.IMAGE_URL+product.getImageID())
                .into(image);
        TextView title = (TextView) header.findViewById(R.id.tv_title);
        title.setText(product.getTitle());
        TextView desc = (TextView) header.findViewById(R.id.tv_desc);
        desc.setText(product.getText());
        revCount = (TextView) header.findViewById(R.id.tv_reviews_count);
        reviewsView.addHeaderView(header);
        loadData();
        return view;
    }

    public void loadData() {
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
    }
}
