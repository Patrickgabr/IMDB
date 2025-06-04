package org.example;

public class Credentials {
    private String mEmail;
    private String mPassword;

    public Credentials(String email, String password) {
        mEmail = email;
        mPassword = password;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            System.out.println("E ala null");
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Credentials other = (Credentials) obj;
        // System.out.println("Compara " + mEmail + " si " + mPassword
        // + " cu " + other.getEmail() + " si " + other.getPassword());
        return mEmail.equals(other.getEmail()) &&
                mPassword.equals(other.getPassword());
    }
}
