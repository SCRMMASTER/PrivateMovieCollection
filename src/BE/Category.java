package BE;

public class Category {

    int Id;

    String Genre;

    public Category(int Id, String Genre){

        this.Id = Id;
        this.Genre = Genre;

    }
    public int getId() {

        return Id;
    }

    public void setId(int id) {

        this.Id = Id;
    }

    public String getGenre() {

        return Genre;
    }

    public void setGenre(String Genre) {

        this.Genre = Genre;
    }

}
