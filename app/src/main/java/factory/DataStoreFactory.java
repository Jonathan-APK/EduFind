package factory;

import android.content.Context;

import strategy.BookmarkImplementation;
import strategy.DataStoreInterface;
import strategy.PolyImplementation;
import strategy.UniImplementation;

/**
 * This class is use to instantiate BookmarkImplementation/UniImplementation/PolyImplementation objects on behalf of the caller
 *
 * @author  Minions
 * @version 1.0
 * @since   2017-10-29
 */
public class DataStoreFactory {


    /**
     * Instantiate BookmarkImplementation/UniImplementation/PolyImplementation objects based on parameter
     * @param datastoreOption type of datastore to instantiate
     * @param context current state of the application/object
     * @return DataStoreInterface implementation object being returned based on parameter
     */    public static DataStoreInterface createDatastore(String datastoreOption, Context context) {

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