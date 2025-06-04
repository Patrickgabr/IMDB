package org.example;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Series extends Production implements TSortable{
    private int mYear;
    private int mSeasonNumber;
    private Map<String, List<Episode>> mSeasons;

    public Series(String title, String plot, List<String> directors, List<String> actors,
                  List<Genre> genres, List<Rating> ratings,
                  double averageRating,
                  int releaseYear, int numSeasons,
                  Map<String, List<Episode>> seasons)
    {
        super(title, plot, directors, actors, genres, ratings, averageRating);
        mYear = releaseYear;
        mSeasonNumber = numSeasons;
        mSeasons = seasons;
    }

    @Override
    public void displayInfo() {
        System.out.println("Series: " + getTitle() + " " + mYear);
        System.out.println("Number of seasons: " + mSeasonNumber);
    }

    public String getSortKey() {
        return getTitle();
    }
}
