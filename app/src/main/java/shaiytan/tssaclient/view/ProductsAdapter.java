package shaiytan.tssaclient.view;

import android.content.Context;
import android.view.*;
import android.widget.*;

import com.squareup.picasso.Picasso;

import java.util.List;

import shaiytan.tssaclient.R;
import shaiytan.tssaclient.model.*;

/**
 * Created by Shaiytan on 04.07.2017.
 * Адаптер для списка товаров
 */

class ProductsAdapter extends BaseAdapter {
    private Context context;
    private List<Product> products;

    ProductsAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return products.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if(v==null){
            v = LayoutInflater.from(context)
                    .inflate(R.layout.product_layout, parent, false);
            holder=new ViewHolder(v);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        Product product = products.get(position);
        Picasso.with(context)
                .load(SiteAPI.IMAGE_URL +product.getImageID())
                .into(holder.img);
        holder.title.setText(product.getTitle());
        holder.description.setText(product.getText());
        return v;
    }

    private class ViewHolder {
        ImageView img;
        TextView title;
        TextView description;
        ViewHolder(View itemView) {
            img = (ImageView) itemView.findViewById(R.id.img);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            description = (TextView) itemView.findViewById(R.id.tv_desc);
        }
    }
}
