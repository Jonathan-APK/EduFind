package controller;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import entity.Bookmark;

public class BookmarkImplementation implements DataStoreInterface {

    private static String delimiter = "\\^";
    private Context context;

    public BookmarkImplementation(Context context){
        this.context = context;
    }

    @Override
    public ArrayList<Object> retrieveList() {
        ArrayList<Object> bookmarkList = new ArrayList<>();
        String path=context.getFilesDir().getAbsolutePath()+"/bookmark/bookmark.csv";
        String[] tempArray;
        String line;
        BufferedReader br = null;

        if(!checkBookmarkFileExists()){
            createBookmarkFile();
        }
        try {
            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                Bookmark bookmark = new Bookmark();
                tempArray = line.split(delimiter);
                bookmark.setInterest(tempArray[0]);
                bookmark.setspecialisation(tempArray[1]);
                bookmark.setL1R4(Integer.parseInt(tempArray[2]));
                bookmark.setPostalCode(Integer.parseInt(tempArray[3]));
                bookmark.setDate(tempArray[4]);
                bookmark.setTime(tempArray[5]);
                bookmarkList.add(bookmark);

            }
        } catch (Exception e) {
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

    public boolean addBookmark(Bookmark bm){
        String path=context.getFilesDir().getAbsolutePath()+"/bookmark/bookmark.csv";
        boolean rt = false;

        if(!checkBookmarkFileExists()){
            createBookmarkFile();
        }
        
        if (bm == null)
            return false;
        else {
            try {
                String bookmarkData = bm.getInterest() + "^" + bm.getspecialisation() + "^" + bm.getL1R4() +"^" + bm.getPostalCode() + "^" + bm.getDate() +"^" +bm.getTime();
                FileOutputStream outputStream;
                OutputStreamWriter osw;
                outputStream = new FileOutputStream(new File(path), true);
                outputStream.write(bookmarkData.getBytes());
                osw = new OutputStreamWriter(outputStream);
                osw.append("\n");
                osw.flush();
                osw.close();
                outputStream.flush();
                outputStream.close();
                rt = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rt;
    }

    public boolean updateBookmark(ArrayList<Bookmark> newList) {
        String path=context.getFilesDir().getAbsolutePath()+"/bookmark/bookmark.csv";
        FileOutputStream outputStream = null;
        BufferedWriter osw = null;
        boolean rt = false;
        String bookmarkData;
        boolean ss = false;

        if(!checkBookmarkFileExists()){
            createBookmarkFile();
        }
        if (newList.size() <= 0)
            try {
                bookmarkData = "";
                outputStream = new FileOutputStream(new File(path), false);
                outputStream.write(bookmarkData.getBytes());
                osw.flush();
                outputStream.flush();
                osw.close();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
                return rt;
            }
        else {
            try {
                for (int i = 0; i < newList.size(); i++) {
                    bookmarkData = newList.get(i).getInterest() + "^" + newList.get(i).getspecialisation() + "^" + newList.get(i).getL1R4() + "^" + newList.get(i).getPostalCode() + "^" + newList.get(i).getDate() + "^" + newList.get(i).getTime();
                    outputStream = new FileOutputStream(new File(path), ss);
                    outputStream.write(bookmarkData.getBytes());
                    FileWriter fw = new FileWriter(path, true);
                    osw = new BufferedWriter(fw);
                    osw.newLine();
                    ss = true;
                    osw.flush();
                    outputStream.flush();
                }
                osw.close();
                outputStream.close();
                rt = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rt;
    }

    private boolean checkBookmarkFileExists(){
        String path=context.getFilesDir().getAbsolutePath()+"/bookmark/bookmark.csv";
        boolean exist = false;
        File file = new File ( path );
        if (file.exists())
        {
            exist = true;
        }
        return exist;
    }

    private void createBookmarkFile() {
        String path = context.getFilesDir().getAbsolutePath() + "/bookmark";
        File file = new File(path);
        file.mkdirs();

        try {
            File yourFile = new File(path + "/bookmark.csv");
            yourFile.createNewFile(); // if file already exists will do nothing
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
