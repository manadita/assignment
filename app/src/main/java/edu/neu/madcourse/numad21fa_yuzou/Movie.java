package edu.neu.madcourse.numad21fa_yuzou;

public class Movie {
    private final String title;
    private final String status;
    private final String genres;
    private final String release_date;
    private final int runtime;
    private final double rating;


    public Movie(String title, String status, String genres, String release_date, int runtime, double rating) {
        this.title = title;
        this.status = status;
        this.genres = genres;
        this.release_date = release_date;
        this.runtime = runtime;
        this.rating = rating;
    }

    @Override
    public String toString(){
        String movieInfo = "";


        return movieInfo;
    }

}
