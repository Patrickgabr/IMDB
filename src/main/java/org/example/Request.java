package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Request {
    private RequestType mType;
    private LocalDateTime mCreationTime;
    private String mTitle;
    private String mDescription;
    private String mCreator; // doar numele user-ului care a creat
    private String mSolver;

    public Request(RequestType type, String title,
                   String description, String creator, String solver) {
        mType = type;
        mCreationTime = LocalDateTime.now();
        mTitle = title;
        mDescription = description;
        mCreator = creator;
        mSolver = solver;

        // la solver - va fi ADMIN pentru anumite tipuri
        // daca nu trebuie sa caut userul care a introdus
        // productia sau actorul in sistem
        if (mType == RequestType.DELETE_ACCOUNT || mType == RequestType.OTHERS)
            mSolver = "ADMIN";
    }

    public String getCreationTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return mCreationTime.format(formatter);
    }
}
