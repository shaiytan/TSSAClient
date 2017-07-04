package shaiytan.tssaclient;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;
import java.util.concurrent.ExecutionException;

import shaiytan.tssaclient.model.Product;
import shaiytan.tssaclient.model.ProductsModel;
import shaiytan.tssaclient.view.ProductsAdapter;

public class ProductsListActivity extends AppCompatActivity {
    private AsyncTask<Void,Void,List<Product>> loader =
            new AsyncTask<Void, Void, List<Product>>() {
        @Override
        protected List<Product> doInBackground(Void... params) {
            return products.getProducts();
        }
        @Override
        protected void onPostExecute(List<Product> products) {
            productsView.setAdapter(new ProductsAdapter(ProductsListActivity.this,products));
        }
    };
    private ProductsModel products;
    private RecyclerView productsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);
        products = new ProductsModel();
        productsView = (RecyclerView) findViewById(R.id.products);
        productsView.setLayoutManager(
                new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        productsView.setItemAnimator(new DefaultItemAnimator());
        try {
            loader.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
