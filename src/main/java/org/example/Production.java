package org.example;

import java.util.List;

public abstract class Production implements Comparable {
    private String mTitle;
    private String mPlot;
    private List<String> mDirectors;
    private List<String> mActors;
    private List<Genre> mGenres;
    private List<Rating> mRatings;
    //private String mDescrition;
    private Double mTotalRatings;

    // ? UN CONSTRUCTOR PTR A INITIALIZA TOATE ALEA
    Production(String title, String plot, List<String> directors, List<String> actors,
               List<Genre> genres, List<Rating> ratings, Double totalRatings) {
        mTitle = title;
        mPlot = plot;
        mDirectors = directors;
        mActors = actors;
        mGenres = genres;
        mRatings = ratings;
        mTotalRatings = totalRatings;
    }
    public abstract void displayInfo();
    public String getTitle() {
        return mTitle;
    }

    public int compareTo(Object o) {
        if (o instanceof Production) {
            Production other = (Production)o;
            return mTitle.compareTo(other.getTitle());
        }
        return 0;
    }
}
