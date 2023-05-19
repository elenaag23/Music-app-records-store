package model.shop;

public abstract class Product implements Comparable<Product>{

    protected int id;
    protected float price;

    protected int noInStock;

    public int getNoInStock() {
        return noInStock;
    }

    public void setNoInStock(int noInStock) {
        this.noInStock = noInStock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public Product() {
    }

    public Product(int id, float price, int noInStock) {
        this.id = id;
        this.price = price;
        this.noInStock = noInStock;
    }

    @Override
    public int compareTo(Product p) {
        return Float.compare(this.price, p.price);
    }

    @Override
    abstract public String toString();
}
