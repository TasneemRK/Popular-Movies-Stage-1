package example.android.com.popularmoviesappstage.Models;

public class Movie {

    private int id;
    private String Original_title;
    private String Overview;
    private String image;
    private String rating;
    private String release_date;

    public Movie() {
    }

    public Movie(int id ,String original_title,String image) {
        this.id = id;
        this.Original_title = original_title;
        this.image = image;
    }


    public Movie(String original_title, String overview, String image, String rating, String release_date) {
        this.Original_title = original_title;
        this.Overview = overview;
        this.image = image;
        this.rating = rating;
        this.release_date = release_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return Original_title;
    }

    public void setOriginal_title(String original_title) {
        Original_title = original_title;
    }

    public String getOverview() {
        return Overview;
    }

    public void setOverview(String overview) {
        Overview = overview;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
