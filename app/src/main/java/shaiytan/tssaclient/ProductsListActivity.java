package shaiytan.tssaclient;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import shaiytan.tssaclient.model.Product;
import shaiytan.tssaclient.view.ProductFragment;
import shaiytan.tssaclient.view.ProductsListFragment;

public class ProductsListActivity
        extends AppCompatActivity
        implements AdapterView.OnItemClickListener {
    private FragmentManager fragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);
        fragmentManager = getSupportFragmentManager();
        ProductsListFragment productsFragment = new ProductsListFragment();
        fragmentManager.beginTransaction()
                .add(R.id.products_fragment, productsFragment)
                .commit();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ProductFragment fragment = ProductFragment.newInstance((Product) parent.getAdapter().getItem(position));
        fragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.products_fragment,fragment)
                .commit();
    }
}
