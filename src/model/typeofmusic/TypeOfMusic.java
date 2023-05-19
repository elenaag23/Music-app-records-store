package model.typeofmusic;

public abstract class TypeOfMusic {

    protected int id;
    protected String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public TypeOfMusic(int id, String category) {
        this.id = id;
        this.category = category;
    }

    public TypeOfMusic(String category) {
        this.category = category;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeOfMusic() {
    }

    public TypeOfMusic(int id) {
        this.id = id;
    }

    @Override
    abstract public String toString();
}
