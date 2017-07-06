package shaiytan.tssaclient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import shaiytan.tssaclient.model.Product;
import shaiytan.tssaclient.view.AuthActivity;
import shaiytan.tssaclient.view.ProductFragment;
import shaiytan.tssaclient.view.ProductsListFragment;
import shaiytan.tssaclient.view.ReviewFormFragment;

public class ProductsListActivity
        extends AppCompatActivity
        implements AdapterView.OnItemClickListener {
    public static final String USERNAME_PREF = "username";
    public static final String TOKEN_PREF = "token";
    private FragmentManager fragmentManager;
    private String token;
    private String username;
    private SharedPreferences pref;
    private MenuItem menuItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);
        pref = getPreferences(MODE_PRIVATE);
        username = pref.getString(USERNAME_PREF,"Гость");
        token = pref.getString(TOKEN_PREF,"");
        fragmentManager = getSupportFragmentManager();
        ProductsListFragment productsFragment = new ProductsListFragment();
        fragmentManager.beginTransaction()
                .add(R.id.products_fragment, productsFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sign_in_menu,menu);
        menuItem = menu.findItem(R.id.sign_in_action);
        updateAuthState();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        toggleAuth();
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ProductFragment fragment = ProductFragment.newInstance((Product) parent.getAdapter().getItem(position));
        ReviewFormFragment form = ReviewFormFragment.newInstance((int)id);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.products_fragment,fragment);
        transaction.add(R.id.review_form,form);
        transaction.commit();
        form.setToken(token);
    }
    private void toggleAuth()
    {
        if(token.isEmpty()) {
            Intent intent = new Intent(this, AuthActivity.class);
            startActivityForResult(intent, 1);
        }
        else{
            username="Гость";
            token="";
            writeAuthData();
            updateAuthState();
            setFragmentToken();
        }
    }
    private void setFragmentToken(){
        ReviewFormFragment form = (ReviewFormFragment) fragmentManager.findFragmentById(R.id.review_form);
        if(form!=null)
            form.setToken(token);
    }
    private void writeAuthData(){
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(USERNAME_PREF,username);
        edit.putString(TOKEN_PREF,token);
        edit.apply();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK)
        {
            token = data.getStringExtra("token");
            username = data.getStringExtra("username");
            writeAuthData();
            updateAuthState();
            setFragmentToken();
        }
    }
    private void updateAuthState(){
        setTitle(username);
        if(token.isEmpty()) menuItem.setTitle("ВХОД");
        else menuItem.setTitle("ВЫХОД");
    }
    public void updateList(){
        try {
            ProductFragment product = (ProductFragment) fragmentManager.findFragmentById(R.id.products_fragment);
            product.loadData();
        }catch (ClassCastException e){
            e.printStackTrace();
        }
    }
}
