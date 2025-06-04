package org.example;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Regular extends User implements RequestsManager{
    private SortedSet<Rating> mReviews;
    List<String> mFavoriteProductions;
    List<String> mFavoriteActors;

    public Regular(String username, int experience, Information information) {
        super(username, experience, information);
        mReviews = new TreeSet<Rating>();
        mType = AccountType.REGULAR;
    }

    @Override
    public void createRequest(Request r) {

    }

    @Override
    public void removeRequest(Request r) {

    }

    public void addReview(Rating rating) {
        mReviews.add(rating);
    }

    public void setFavoriteProductions(List<String> favoritProductions) {
        mFavoriteProductions = favoritProductions;
    }

    public void setFavoriteActors(List<String> favoritActors) {
        mFavoriteActors = favoritActors;
    }
}
