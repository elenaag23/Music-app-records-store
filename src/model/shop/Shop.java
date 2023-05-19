package model.shop;

import service.Application;
import service.ApplicationService;

import java.util.*;

public class Shop {

    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Shop() {
    }

    public Shop(List<Product> products) {
        this.products = products;
    }


}
