package shaiytan.tssaclient.model;

import java.util.*;

import retrofit2.Response;
import shaiytan.tssaclient.TSSAClientApp;

/**
 * Created by Shaiytan on 04.07.2017.
 */

public class ProductsModel {
    private ProductsAPI api;
    public ProductsModel() {
        api = TSSAClientApp.getAPI();
    }
    public List<Product> getProducts() {
        List<Product> products;
        try {
            Response<Product[]> response = api.getProducts().execute();
            if (response.isSuccessful()) products = Arrays.asList(response.body());
            else products = Collections.emptyList();
        } catch (Exception e) {
            products = Collections.emptyList();
        }
        return products;
    }
}
