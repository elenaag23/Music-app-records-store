package model.typeofmusic;

public abstract class TypeOfMusic {

    protected int id;
    protected boolean like;

    protected int noLikes;
    protected String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public TypeOfMusic(int id, String category) {
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public int getNoLikes() {
        return noLikes;
    }

    public void setNoLikes(int noLikes) {
        this.noLikes = noLikes;
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
