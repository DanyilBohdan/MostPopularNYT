package com.example.mostpopularapi;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder> {

    private int countItems;
    private Context parent;
    private Cursor cursor;

    public FavoritesAdapter( int count, Context parent, Cursor c){
        countItems = count;
        this.parent = parent;
        cursor = c;
    }

    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(layoutIdForListItem, viewGroup, false);
        FavoritesViewHolder viewHolder = new FavoritesViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder favoritesViewHolder, final int position) {
            cursor.moveToPosition(position);
            favoritesViewHolder.listItemFavView.setText(cursor.getString(cursor.getColumnIndex("title")));

            favoritesViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cursor.moveToPosition(position);

                    Import.id = Long.valueOf(cursor.getString(cursor.getColumnIndex("id")));

                    final Dialog dialogSetting = new Dialog(parent);
                    dialogSetting.setContentView(R.layout.setting_dialog);

                    Button btnDelete = (Button) dialogSetting.findViewById(R.id.btn_delete);
                    Button btnView = (Button) dialogSetting.findViewById(R.id.btn_view);

                    btnView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(parent, FavoriteArticle.class);
                            parent.startActivity(intent);

                            dialogSetting.dismiss();
                        }
                    });

                    btnDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogSetting.dismiss();
                            final Dialog dialogDelete = new Dialog(parent);
                            dialogDelete.setContentView(R.layout.dialog);

                            Button btnYes = (Button) dialogDelete.findViewById(R.id.btn_Yes);
                            Button btnNo = (Button) dialogDelete.findViewById(R.id.btn_No);

                            btnYes.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    DBHelper db = new DBHelper(parent, 1);
                                    db.delArticle(Import.id);
                                    dialogDelete.dismiss();
                                    dialogSetting.dismiss();
                                }
                            });

                            btnNo.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialogDelete.dismiss();
                                    dialogSetting.show();
                                }
                            });
                            dialogDelete.show();
                        }
                    });
                    dialogSetting.show();
                }
            });
        }

    @Override
    public int getItemCount() {
        return countItems;
    }

    class FavoritesViewHolder extends RecyclerView.ViewHolder {

        TextView listItemFavView;

        public FavoritesViewHolder(@NonNull View itemView) {
            super(itemView);
            listItemFavView = itemView.findViewById(R.id.text_title);
        }
    }
}
