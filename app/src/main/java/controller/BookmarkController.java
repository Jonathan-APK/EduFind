package controller;

import android.content.Context;
import android.util.Log;

import java.io.*;
import java.util.ArrayList;
import entity.Bookmark;

public class BookmarkController {
    private static String delimiter = "\\^";
    private Context current;
    private String path;
    public BookmarkController(Context current){
        this.current = current;
    }

    public ArrayList<Bookmark> retrieveListOfBookmark() {
        ArrayList<Bookmark> bookmarkList = new ArrayList<Bookmark>();
        path=current.getFilesDir().getAbsolutePath()+"/bookmark/bookmark.csv";
        String[] tempArray;
        String line;
        BufferedReader br = null;

        if(checkBookmarkExist() == false){
            createBookmarkFile();
        }
        try {
                br = new BufferedReader(new FileReader(path));
                while ((line = br.readLine()) != null) {
                    Bookmark bookmark = new Bookmark();
                    tempArray = line.split(delimiter);
                    bookmark.setInterest(tempArray[0]);
                    bookmark.setSpecialization(tempArray[1]);
                    bookmark.setL1R4(Integer.parseInt(tempArray[2]));
                    bookmark.setPostalCode(Integer.parseInt(tempArray[3]));
                    bookmark.setDate(tempArray[4]);
                    bookmark.setTime(tempArray[5]);
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

    public boolean addBookmark(Bookmark bm){
        path=current.getFilesDir().getAbsolutePath()+"/bookmark/bookmark.csv";
        boolean rt = false;
        BufferedWriter bw = null;

        if(checkBookmarkExist() == false){
            createBookmarkFile();
        }
            if (bm == null)
                return rt;
            else {

                try {
                    String bookmarkData = bm.getInterest() + "^" + bm.getSpecialization() + "^" + bm.getL1R4() +"^" + bm.getPostalCode() + "^" + bm.getDate()
                            +"^" +bm.getTime();
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
        path=current.getFilesDir().getAbsolutePath()+"/bookmark/bookmark.csv";
        BufferedWriter bw = null;
        FileOutputStream outputStream = null;
        OutputStreamWriter osw = null;
        boolean rt = false;
        String bookmarkData;

        if(checkBookmarkExist() == false){
            createBookmarkFile();
        }
            if (newList.size() <= 0)
                return rt;
            else {

                try {

                    for (int i = 0; i < newList.size(); i++) {
                        bookmarkData = newList.get(i).getInterest() + "^" + newList.get(i).getSpecialization() + "^" + newList.get(i).getL1R4() + "^" + newList.get(i).getPostalCode() + "^" + newList.get(i).getDate()
                                + "^" + newList.get(i).getTime();

                        outputStream = new FileOutputStream(new File(path), false);
                        outputStream.write(bookmarkData.getBytes());
                        osw = new OutputStreamWriter(outputStream);
                        osw.write("\n");

                    }
                        osw.flush();
                        outputStream.flush();
                        osw.close();
                        outputStream.close();

                    rt = true;


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        return rt;
    }

    public boolean checkBookmarkExist(){
        path=current.getFilesDir().getAbsolutePath()+"/bookmark/bookmark.csv";
        boolean exist = false;
        File file = new File ( path );
        if (file.exists())
        {
            exist = true;
        }
        return exist;
    }

    public void createBookmarkFile() {
        BufferedWriter bw = null;
        path = current.getFilesDir().getAbsolutePath() + "/bookmark";
        File file = new File(path);
        file.mkdirs();

        try {
            bw = new BufferedWriter(new FileWriter(path + "/bookmark.csv"));
            bw.write("");
        } catch (FileNotFoundException e) {
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
}