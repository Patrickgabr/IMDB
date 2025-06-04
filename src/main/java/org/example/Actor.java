package org.example;

import java.util.List;

public class Actor {
    private String mName;
    private List<Pair<String, String>> roles; // List of roles with movie/series name and type
    private String mBiography;
    private List<Performance> mPerformances;

    public Actor(String name, String biography,
                 List<Performance> performances) {
        mName = name;
        mBiography = biography;
        mPerformances = performances;
    }
}
