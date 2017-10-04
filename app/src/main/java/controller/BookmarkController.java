package controller;

import java.io.*;
import java.util.ArrayList;
import entity.Bookmark;
import java.util.ArrayList;

/**
 * Created by boonleng94 on 27/9/2017.
 */

public class BookmarkController {

    private static String csvFile;
    private static BufferedReader br = null;
    private static BufferedWriter bw = null;
    private static String line;
    private static String delimiter = "^";
    private static String[] tempArray;
    private static Bookmark bookmark;
    private static ArrayList<Bookmark> bookmarkList;
    private static boolean rt = false;
    private static String bookmarkFile = "/Users/darks/Desktop/bookmark.csv";


    public static ArrayList<Bookmark> retrieveListOfBookmark() {
        bookmarkList = new ArrayList<Bookmark>();
        bookmark = new Bookmark();

        csvFile = bookmarkFile;

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                tempArray = line.split(delimiter);

                bookmark.setInterest(tempArray[0]);
                bookmark.setSpecialization(tempArray[1]);
                bookmark.setL1R4(Integer.parseInt(tempArray[2]));
                bookmark.setPostalCode(Integer.parseInt(tempArray[3]));

                bookmarkList.add(bookmark);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bookmarkList;
    }


    public static boolean updateBookmark(ArrayList<Bookmark> newList) {
        csvFile = bookmarkFile;

        if(newList.size() <=0)
            return rt;
        else{
            try{

                bw = new BufferedWriter(new FileWriter(csvFile));

                for(int i=0; i<newList.size(); i++) {

                    bw.write(newList.get(i).getInterest());
                    bw.write("^");
                    bw.write(newList.get(i).getSpecialization());
                    bw.write("^");
                    bw.write(newList.get(i).getL1R4());
                    bw.write("^");
                    bw.write(newList.get(i).getPostalCode());
                    bw.newLine();
                }
                rt = true;

            }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bw != null) {
                    try {
                        bw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return rt;
    }

}