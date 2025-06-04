package org.example;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

public abstract class User {
    private String mUsername;
    private Information mInformation;
    private List<String> mNotifications;
    private SortedSet<TSortable> mSorted;
    protected AccountType mType;
    private int mExperience;

    public User(String username, int experience, Information information) {
        mUsername = username;
        mExperience = experience;
        mInformation = information;
    }

    public Information getInformation() {
        return mInformation;
    }

    public static class Information {
        private Credentials mCredentials;
        private String mName;
        //private String mEmail;

        private int mAge;
        private String mCountry;
        private char mGender;

        private Information(Builder builder) {
            mCredentials = builder.credentials;
            mName = builder.name;
            mAge = builder.age;
            mCountry = builder.country;
            mGender = builder.gender;
        }

        public static class Builder {
            private Credentials credentials;
            private String name;
            private String email;
            private String password;
            private int age;

            private String birthday;
            private String country;
            private char gender;

            public Builder credential(Credentials credentials) {
                this.credentials = credentials;
                return this;
            }

            public Builder name(String name) {
                this.name = name;
                return this;
            }

            public Builder email(String email) {
                this.email = email;
                return this;
            }

            public Builder password(String pass) {
                this.password = pass;
                //System.out.println("Fac un user nou cu " + email + " si " + password);
                credentials = new Credentials(email, password);
                return this;
            }

            public Builder age(int age) {
                this.age = age;
                return this;
            }

            public Builder birthDate(String birthday) {
                this.birthday = birthday;
                return this;
            }

            public Builder country(String country) {
                this.country = country;
                return this;
            }

            public Builder gender(char gender) {
                this.gender = gender;
                return this;
            }
            public Information build() { // DE ARANJAT
                // daca trebuie neaparat sa aiba unele campuri
                Objects.requireNonNull(credentials, "Credentials cannot be null");
                Objects.requireNonNull(name, "Name cannot be null");

                return new Information(this);
            }
        } // class Builder

        public Credentials getCredentials() {
            return mCredentials;
        }
    } // class Information

    public void addToFavorites(TSortable obj) {
        mSorted.add(obj);
    }

    public void removeFavorite(TSortable obj) {
        mSorted.remove(obj);
    }

    public Credentials getCredentials() {
        return mInformation.getCredentials();
    }

    public AccountType getType() {
        return mType;
    }
}
