package org.example;

public class UserMenuEntry {
    private String mText;
    private int mMask;
    public UserMenuEntry(String text, int mask) {
        mText = text;
        mMask = mask;
    }

    public String getTest() {
        return mText;
    }

    public int getMask() {
        return mMask;
    }
}
