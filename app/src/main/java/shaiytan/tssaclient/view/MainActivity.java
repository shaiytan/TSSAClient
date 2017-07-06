package shaiytan.tssaclient.view;

import android.content.*;
import android.os.Bundle;
import android.support.v4.app.*;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.AdapterView;

import shaiytan.tssaclient.R;
import shaiytan.tssaclient.model.Product;

//попробуем использовать фрагменты, посмотрим что из этого выйдет

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {
    public static final String USERNAME_PREF = "username";
    public static final String TOKEN_PREF = "token";
    public static final int AUTH_REQUEST = 1;
    private FragmentManager fragmentManager;
    private String token;
    private String username;
    private SharedPreferences pref;
    private MenuItem menuItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //чтобы не авторизироваться при кождом запуске приложения
        pref = getPreferences(MODE_PRIVATE);
        username = pref.getString(USERNAME_PREF,"Гость");
        token = pref.getString(TOKEN_PREF,"");

        //Список товаров
        fragmentManager = getSupportFragmentManager();
        ProductsFragment productsFragment = new ProductsFragment();
        fragmentManager.beginTransaction()
                .add(R.id.list_fragment_container, productsFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sign_in_menu,menu);
        menuItem = menu.findItem(R.id.sign_in_action);
        updateAuthUIState();
        return true;
    }

    // заголовки, имя пользователя, вход-выход...
    private void updateAuthUIState(){
        setTitle(username);
        if(token.isEmpty()) menuItem.setTitle("ВХОД");
        else menuItem.setTitle("ВЫХОД");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        toggleAuth();
        return true;
    }

    // вход-выход
    private void toggleAuth() {
        if(token.isEmpty()) {
            Intent intent = new Intent(this, AuthActivity.class);
            startActivityForResult(intent, AUTH_REQUEST);
        } else {
            username="Гость";
            token="";
            writeAuthData();
            updateAuthUIState();
            setFragmentToken();
        }
    }

    private void setFragmentToken(){
        // Передать токен авторизации в форму добавления комментария
        // и скрыть форму если пользователь не авторизирован
        ReviewFormFragment form = (ReviewFormFragment) fragmentManager.findFragmentById(R.id.review_form_container);
        if(form!=null)
            form.setToken(token);
    }

    // сохранить данные авторизации
    private void writeAuthData(){
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(USERNAME_PREF,username);
        edit.putString(TOKEN_PREF,token);
        edit.apply();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Заменить фрагмент на отображение одного товара и списка отзывов
        // Добавить форму отправки отзыва
        ReviewsFragment fragment = ReviewsFragment.newInstance((Product) parent.getAdapter().getItem(position));
        ReviewFormFragment form = ReviewFormFragment.newInstance((int)id);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.list_fragment_container,fragment);
        transaction.add(R.id.review_form_container,form);
        transaction.commit();
        form.setToken(token);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK)
        { //успешная регистрация/авторизация
            token = data.getStringExtra("token");
            username = data.getStringExtra("username");
            writeAuthData();
            updateAuthUIState();
            setFragmentToken();
        }
    }

    // перезагрузка списка отзывов после добавления нового отзыва
    public void updateReviewsList() {
        try {
            ReviewsFragment product = (ReviewsFragment) fragmentManager.findFragmentById(R.id.list_fragment_container);
            product.loadData();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }
}
