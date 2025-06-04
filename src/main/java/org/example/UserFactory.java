package org.example;

public class UserFactory {
    public static User createUser(AccountType accountType, String username, int experience, User.Information information) {
        switch (accountType) {
            case REGULAR:
                return new Regular(username, experience, information);
            case CONTRIBUTOR:
                return new Contributor(username, experience, information);
            case ADMIN:
                return new Admin(username, information);
            default:
                throw new IllegalArgumentException("Invalid account type");
        }
    }
}