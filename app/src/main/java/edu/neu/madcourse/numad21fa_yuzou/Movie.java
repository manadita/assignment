package edu.neu.madcourse.numad21fa_yuzou;

public class Movie {
    private final String title;
    private final String releasedate;
    private final String rating;


    public Movie(String title, String release_date, String rating) {
        this.title = title;
        this.releasedate = release_date;
        this.rating = rating;
    }

    String getTitle(){
        return title;
    }

    String getReleaseDte(){
        return releasedate;
    }

    String getRating(){
        return rating;
    }

}
