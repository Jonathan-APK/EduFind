package com.example.utsav.edufind;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import entity.Bookmark;

/**
 * This class sets the course information in their respective View elements and populates the card view
 * of each course dynamically based on the size of the Course objects.
 *
 * @author  Minions
 * @version 1.0
 * @since   2017-10-24
 */
public class RVAdapterBookmarks extends RecyclerView.Adapter<RVAdapterBookmarks.BookmarkViewHolder> {
    /**
     * This class references the respective View widgets inside the layout through its id.
     */
    public static class BookmarkViewHolder extends RecyclerView.ViewHolder {
        CardView cv2;
        TextView Date_createdTV;
        TextView Time_createdTV;
        TextView AoiTV;
        TextView SpecTV;
        TextView PostalTV;
        TextView L1R4TV;

        BookmarkViewHolder(View itemView) {
            super(itemView);
            cv2 = itemView.findViewById(R.id.cv2);
            Date_createdTV = itemView.findViewById(R.id.date_createdTV);
            Time_createdTV= itemView.findViewById(R.id.time_createdTV);
            AoiTV = itemView.findViewById(R.id.aoiTV);
            SpecTV = itemView.findViewById(R.id.specTV);
            PostalTV = itemView.findViewById(R.id.postalTV);
            L1R4TV = itemView.findViewById(R.id.L1R4TV);

            //GIVE SHADOW AROUND CARD
            cv2.setCardElevation(10);
            CardView.LayoutParams lp = new CardView.LayoutParams(CardView.LayoutParams.WRAP_CONTENT, CardView.LayoutParams.WRAP_CONTENT);
            DisplayMetrics dm = cv2.getResources().getDisplayMetrics();
            lp.setMargins(convertDpToPx(5, dm), convertDpToPx(5, dm), convertDpToPx(5, dm), convertDpToPx(5, dm));
            //lp.setMargins(5, 5, 5, 5);
            cv2.setLayoutParams(lp);
            cv2.setContentPadding(0, 0, 0, 0);
        }
    }

    List<Bookmark> bookmarks;
    String interest;
    String specialization;
    String date;
    String time;
    int L1R4;
    int postalCode;

    public RVAdapterBookmarks(List<Bookmark> bookmarks, String interest, String specialization, int L1R4, int postalCode, String date, String time){
        this.bookmarks = bookmarks;
        this.interest = interest;
        this.specialization = specialization;
        this.L1R4 = L1R4;
        this.postalCode = postalCode;
        this.date = date;
        this.time = time;
    }

    public RVAdapterBookmarks(List<Bookmark> bookmarks){
        this.bookmarks = bookmarks;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public BookmarkViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bookmark_item, viewGroup, false);
        BookmarkViewHolder pvh = new BookmarkViewHolder(v);
        return pvh;
    }

    /**
     * This method checks the name of the polytechnic or university and sets the respective institution image
     * to its CourseViewHolder widget.
     * This method also dynamically sets the external link(URL) on the clickable website icon which redirects the user
     * to an external browser when clicked on.
     * This method also dynamically sets the course details on the clickable card view which redirects the user
     * to the respective course information page.
     * @param BookmarkViewHolder Holds all the View widgets in the SearchResultsUI page
     * @param i Allows accessibility from it inner onClick method to retrieve the correct course URL
     */
    @Override
    public void onBindViewHolder(BookmarkViewHolder BookmarkViewHolder, final int i) {
        BookmarkViewHolder.Date_createdTV.setText(bookmarks.get(i).getDate());
        BookmarkViewHolder.Time_createdTV.setText(bookmarks.get(i).getTime());
        BookmarkViewHolder.AoiTV.setText(bookmarks.get(i).getInterest());
        BookmarkViewHolder.SpecTV.setText(bookmarks.get(i).getSpecialization());
        BookmarkViewHolder.PostalTV.setText(String.valueOf(bookmarks.get(i).getPostalCode()));
        BookmarkViewHolder.L1R4TV.setText(String.valueOf(bookmarks.get(i).getL1R4()));
    }

    @Override
    public int getItemCount() {
        return bookmarks.size();
    }

    private static int convertDpToPx(int dp, DisplayMetrics displayMetrics) {
        float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
        return Math.round(pixels);
    }
}