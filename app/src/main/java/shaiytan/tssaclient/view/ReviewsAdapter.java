package shaiytan.tssaclient.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;


import java.util.List;

import shaiytan.tssaclient.R;
import shaiytan.tssaclient.model.Review;

/**
 * Created by Shaiytan on 05.07.2017.
 */

class ReviewsAdapter extends BaseAdapter {
    private Context context;
    private List<Review> reviews;

    ReviewsAdapter(Context context, List<Review> reviews) {
        this.context = context;
        this.reviews = reviews;
    }

    @Override
    public int getCount() {
        return reviews.size();
    }

    @Override
    public Object getItem(int position) {
        return reviews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return reviews.get(position).getID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if(v==null){
            v = LayoutInflater.from(context)
                    .inflate(R.layout.review_layout, parent, false);
            holder=new ViewHolder(v);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        Review review = reviews.get(position);
        holder.rating.setRating(review.getRate());
        holder.username.setText(review.getUser());
        holder.reviewText.setText(review.getText());
        return v;
    }

    private class ViewHolder {
        private RatingBar rating;
        private TextView username;
        private TextView reviewText;
        ViewHolder(View itemView) {
            rating = (RatingBar) itemView.findViewById(R.id.rating);
            username = (TextView) itemView.findViewById(R.id.et_username);
            reviewText = (TextView) itemView.findViewById(R.id.review_text);
        }
    }
}
