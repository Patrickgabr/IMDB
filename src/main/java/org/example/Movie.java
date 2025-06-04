package org.example;

import java.util.List;

public class Movie  extends Production implements TSortable{
    private String mDuration;
    private int mYear;

    public Movie(String title, String plot, List<String> directors, List<String> actors, List<Genre> genres,
                 List<Rating> ratings, double averageRating,
                 String duratation, int year) {
        super(title, plot, directors, actors, genres, ratings, averageRating);
        mDuration = duratation;
        mYear = year;
    }

    @Override
    public void displayInfo() {
        System.out.println("Movie: " + getTitle() + ", Duration: " + mDuration + ", Release Year: " + mYear);
    }

    public String getSortKey() {
        return getTitle();
    }

}
