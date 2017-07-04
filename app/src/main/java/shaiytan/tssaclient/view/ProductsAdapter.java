package shaiytan.tssaclient.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import shaiytan.tssaclient.R;
import shaiytan.tssaclient.model.Product;

/**
 * Created by Shaiytan on 04.07.2017.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    private Context context;
    private List<Product> products;

    public ProductsAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = products.get(position);
        Picasso.with(context)
                .load("http://smktesting.herokuapp.com/static/"+product.getImageID())
                .into(holder.img);
        holder.title.setText(product.getTitle());
        holder.description.setText(product.getText());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public long getItemId(int position) {
        return products.get(position).getId();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title;
        TextView description;
        ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            title = (TextView) itemView.findViewById(R.id.profuct_title);
            description = (TextView) itemView.findViewById(R.id.desc);
        }
    }
}
