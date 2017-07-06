package shaiytan.tssaclient.view;


import android.os.*;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.*;

import java.util.List;

import shaiytan.tssaclient.*;
import shaiytan.tssaclient.model.*;

/**
 * Фрагмент списка товаров
 */
public class ProductsFragment extends Fragment {

    public ProductsFragment() { }

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
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        productsView = (ListView) view.findViewById(R.id.list);
        // Обработка выбора элемента в активити
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
