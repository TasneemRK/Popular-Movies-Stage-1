package example.android.com.popularmoviesappstage.Utils;

public interface Constant {

    final String BASE_URL = "https://api.themoviedb.org/3/";
    final String API_KEY = "?api_key=d87ff2eeb96afff48b1570500e022fa2";

    // ------------------ Sort APIs ---------------------------

    final String SORT_BY_POPULARITY_API = BASE_URL + "discover/movie"+API_KEY+"&sort_by=popularity.desc";
    final String SORT_BY_TOP_RATES_API = BASE_URL + "discover/movie"+API_KEY+"&sort_by=vote_average.desc";

    // ----------------------- Find Movie By Id -------------------

    // to get movie from its id , you should use the url as follows:
    // https://api.themoviedb.org/3/movie/ + movie id + ?api_key=d87ff2eeb96afff48b1570500e022fa2
    // OR ==>  BASE_URL + "movie/" + movie id + API_KEY

    final String GET_MOVIE = BASE_URL + "movie/";

    // ------------------------- all movies ---------------------

    final String ALL_MOIVES_API = BASE_URL + "discover/movie"+API_KEY;


    // ------------------------ image url -----------------------------

    final String IMAGE_URL = "https://image.tmdb.org/t/p/w500";


}
