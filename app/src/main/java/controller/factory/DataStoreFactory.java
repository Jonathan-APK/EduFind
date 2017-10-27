package controller.factory;

import android.content.Context;

import controller.BookmarkImplementation;
import controller.DataStoreInterface;
import controller.PolyImplementation;
import controller.UniImplementation;

public class DataStoreFactory {


    // static method to return appropriate DataStoreInterface object based on user choice
    public static DataStoreInterface getDatastore(String datastoreOption, Context context) {

        DataStoreInterface dataStore = null;

        if(datastoreOption.equalsIgnoreCase("bookmark")) {
            dataStore = new BookmarkImplementation(context);
        } else if(datastoreOption.equalsIgnoreCase("uni")) {
            dataStore = new UniImplementation(context);
        } else if(datastoreOption.equalsIgnoreCase("poly")) {
            dataStore = new PolyImplementation(context);
        }

        return dataStore;
    }

}