package org.example;

public class Rating {
    private String mUser;
    private int mRating;
    private String mComments;
    public Rating(String user, int mark, String comment) {
        mUser = user;
        mRating = mark;
        mComments = comment;
    }
}
