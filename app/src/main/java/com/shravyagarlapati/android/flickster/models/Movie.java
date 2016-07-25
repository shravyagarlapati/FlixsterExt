package com.shravyagarlapati.android.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by shravyagarlapati on 7/19/16.
 */
public class Movie {

    String posterPath;
    String originalTitle;
    String overview;
    String backdropPath;
    double averageRating;

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",posterPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdropPath() { return String.format("https://image.tmdb.org/t/p/w342/%s",backdropPath); }

    public double getAverageRating() { return averageRating; }


    public Movie(JSONObject jsonObject) throws JSONException {
        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");
        this.backdropPath = jsonObject.getString("backdrop_path");
        this.averageRating = jsonObject.getDouble("vote_average");
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray array) {
        ArrayList<Movie> listOfMovies = new ArrayList<>();

        for(int i = 0; i < array.length(); i++){
            try {
                JSONObject eachMovie = array.getJSONObject(i);
                listOfMovies.add(new Movie(eachMovie));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return listOfMovies;
    }

    public enum ImageType{
        POSTER, BACKDROP
    }

    public ImageType imageType;

}