package org.example;

import java.util.ArrayList;
import java.util.List;

public class RequestHolder {
    private static List<Request> mRequests = new ArrayList<Request>();

    private RequestHolder() {}

    public static void addRequest(Request request) {
        mRequests.add(request);
    }

    public static Request getNextRequest() {
        Request ret = null;
        if (!mRequests.isEmpty()) {
            // iau urmatorul request
            ret = mRequests.get(0);
            mRequests.remove(0);
        }
        return ret;
    }
}
