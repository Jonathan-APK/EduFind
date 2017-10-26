package controller;

import android.content.Context;

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

        try {

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(checkBookmarkExist() == false){
            createBookmarkFile();
        }else {
            try {
                br = new BufferedReader(new FileReader(path));
                while ((line = br.readLine()) != null) {
                    Bookmark bookmark = new Bookmark();
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
        }
        return bookmarkList;
    }

    public boolean addBookmark(Bookmark bm){
        path=current.getFilesDir().getAbsolutePath()+"/bookmark/bookmark.csv";
        boolean rt = false;

        if(checkBookmarkExist() == false){
            createBookmarkFile();
        } else {
            if (bm == null)
                return rt;
            else {
                FileOutputStream outputStream;
                try {
                    outputStream = current.openFileOutput(path, Context.MODE_PRIVATE);
                    StringBuilder input = new StringBuilder();
                    input.append(bm.getInterest());
                    input.append("^");
                    input.append(bm.getSpecialization());
                    input.append("^");
                    input.append(String.valueOf(bm.getL1R4()));
                    input.append("^");
                    input.append(String.valueOf(bm.getPostalCode()));
                    input.append("^");
                    input.append(bm.getDate());
                    input.append("^");
                    input.append(bm.getTime());
                    input.append("\n");
                    outputStream.write(String.valueOf(input).getBytes());
                    outputStream.close();
                    rt = true;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return rt;
    }

    public boolean updateBookmark(ArrayList<Bookmark> newList) {
        path=current.getFilesDir().getAbsolutePath()+"/bookmark/bookmark.csv";
        BufferedWriter bw = null;
        boolean rt = false;
        if(checkBookmarkExist() == false){
            createBookmarkFile();
        } else {
            if (newList.size() <= 0)
                return rt;
            else {
                try {
                    bw = new BufferedWriter(new FileWriter(path));

                    for (int i = 0; i < newList.size(); i++) {
                        bw.append(newList.get(i).getInterest());
                        bw.append("^");
                        bw.append(newList.get(i).getSpecialization());
                        bw.append("^");
                        bw.append(String.valueOf(newList.get(i).getL1R4()));
                        bw.append("^");
                        bw.append(String.valueOf(newList.get(i).getPostalCode()));
                        bw.append("^");
                        bw.append(newList.get(i).getDate());
                        bw.append("^");
                        bw.append(newList.get(i).getTime());
                        bw.newLine();
                    }
                    rt = true;
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
            bw.append("");
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