package controller;

import android.content.Context;

/**
 * Created by darks on 26-Oct-17.
 */
public class DataStoreFactory {


    // static method to return appropriate DataStoreInterface object based on user choice
    public static DataStoreInterface getDatastore(String datastoreOption, Context context) {

        DataStoreInterface dataStore = null;

        if(datastoreOption.equalsIgnoreCase("bookmark")) {
            dataStore = new BookMarkImplementation(context);
        } else if(datastoreOption.equalsIgnoreCase("uni")) {
            dataStore = new UniImplementation(context);
        } else if(datastoreOption.equalsIgnoreCase("poly")) {
            dataStore = new PolyImplementation(context);
        }

        return dataStore;
    }

}