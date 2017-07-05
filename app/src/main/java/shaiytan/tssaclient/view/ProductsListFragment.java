package shaiytan.tssaclient.view;


import android.os.*;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import shaiytan.tssaclient.*;
import shaiytan.tssaclient.model.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsListFragment extends Fragment {


    public ProductsListFragment() {
        // Required empty public constructor
    }

    private ProductsModel products;
    private ListView productsView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        products = new ProductsModel();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products_list, container, false);
        productsView = (ListView) view.findViewById(R.id.products);
        productsView.setOnItemClickListener((AdapterView.OnItemClickListener) getActivity());
        new AsyncTask<Void, Void, List<Product>>() {
            @Override
            protected List<Product> doInBackground(Void... params) {
                return products.getProducts();
            }
            @Override
            protected void onPostExecute(List<Product> products1) {
                productsView.setAdapter(new ProductsAdapter(getActivity(), products1));
            }
        }.execute();
        return view;
    }
}
