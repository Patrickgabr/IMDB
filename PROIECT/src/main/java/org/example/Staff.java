package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public abstract class Staff extends User implements StaffInterface {
    private List<Request> mRequests;
    private SortedSet<TSortable> mAddedEntries;

    public Staff(String username, int experience, User.Information information) {
        super(username, experience, information);
        mRequests = new ArrayList<Request>();
        mAddedEntries = new TreeSet<TSortable>();
    }

    @Override
    public void addProductionSystem(Production p) {


    }

    @Override
    public void addActorSystem(Actor a) {





    }

    @Override
    public void removeProductionSystem(String name) {

    }

    @Override // ASTA DE SCOS?
    public void removeActorSystem(String name) {

    }

    @Override
    public void updateProduction(Production p) {

    }

    @Override
    public void updateActor(Actor a) {

    }

    @Override
    public void resolveRequest(Request request) {

    }
}