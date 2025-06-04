package org.example;

import java.util.ArrayList;
import java.util.List;

public class Contributor extends Staff implements RequestsManager {

    List<String> mProductionContributions;
    List<String> mActorContributions;

    List<String> mFavoriteProductions;
    List<String> mFavoriteActors;
    public Contributor(String username, int experience, User.Information information) {
        super(username, experience, information);
        mType = AccountType.CONTRIBUTOR;
    }

    public void setProductionContributions(List<String> productions) {
        mProductionContributions = productions;
    }

    public void setActorContributions(List<String> actors) {
        mActorContributions = actors;
    }

    public void setFavoriteProductions(List<String> favoritProductions) {
        mFavoriteProductions = favoritProductions;
    }

    public void setFavoriteActors(List<String> favoritActors) {
        mFavoriteActors = favoritActors;
    }
    @Override
    public void createRequest(Request r) {

    }

    @Override
    public void removeRequest(Request r) {

    }

    public void addReview(Production production, int rating, String comment) {
        // Implementation for adding a review
    }
}
