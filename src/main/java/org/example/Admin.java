package org.example;

import java.util.List;

public class Admin extends Staff {
    public Admin(String username, Information info) {
        super(username, Integer.MAX_VALUE, info);
        mType = AccountType.ADMIN;
    }

    @Override
    public void addProductionSystem(Production p) {
        // Implementation for adding a production to the system


    }

    @Override
    public void addActorSystem(Actor a) {
        // Implementation for adding an actor to the system
    }

    @Override
    public void removeProductionSystem(String name) {
        // Implementation for removing a production from the system
    }

    @Override
    public void removeActorSystem(String name) {
        // Implementation for removing an actor from the system
    }

    @Override
    public void updateProduction(Production p) {
        // Implementation for updating production details
    }

    @Override
    public void updateActor(Actor a) {
        // Implementation for updating actor details
    }


    public void resolveRequests(List<Request> requests) {
        // Implementation for resolving requests


    }
}