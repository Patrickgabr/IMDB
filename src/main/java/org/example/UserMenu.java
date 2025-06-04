package org.example;

import java.util.ArrayList;
import java.util.List;

public class UserMenu {
    private List<UserMenuEntry> mList;
    private static final String[] stringEntries = {
            "View productions details",
            "View actors details",
            "View notifications",
            "Search for actor/movie/series",
            "Add/Delete actor/move/series to/from favorites",
            "Add/Delete a request",
            "Add/Delete actor/movie/series from system",
            "View/Solve requests",
            "Update Movie Details",
            "Update Actor Details",
            "Add/Delete a rating",
            "Add/Delete a user from system",
            "Logout"
    };

    private static final int[] maskEntries = {
            30, 30, 30, 30, 30, 6, 15, 15, 15, 15, 3, 5, 30
    };
    public UserMenu() {
        mList = new ArrayList<UserMenuEntry>();
        for (int i = 0; i < stringEntries.length; i++) {
            UserMenuEntry e = new UserMenuEntry(stringEntries[i], maskEntries[i]);
            mList.add(e);
        }
    }

    public void generateUserMenu(AccountType type) {
        //System.out.println("Intra lagenerateUSerMEnu " + type.toString());
        int index = 0;
        for (int i = 0; i < stringEntries.length; i++)
        {
            if (type == AccountType.REGULAR && mList.get(i).getMask() % 2 == 0) {
                index++;
                System.out.println(index + ") " + stringEntries[i]);
            }
            else if  (type == AccountType.CONTRIBUTOR && mList.get(i).getMask() % 3 == 0) {
                index++;
                System.out.println(index + ") " + stringEntries[i]);
            }
            else if (type == AccountType.ADMIN && mList.get(i).getMask() % 5 == 0) {
                index++;
                System.out.println(index + ") " + stringEntries[i]);
            }
        }
    }
}
