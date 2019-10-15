package com.example.mostpopularapi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public static String LOG_TAG = "MyLog";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ToDoCount";

    public DBHelper(Context context, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {

        Log.d(LOG_TAG, "create table Date");
        db.execSQL("CREATE TABLE Article " +
                "( " +
                "id LONG PRIMARY KEY, " +
                "title TEXT, " +
                "keywords TEXT, " +
                "email_count TEXT, " +
                "count_type TEXT, " +
                "colum TEXT, " +
                "section TEXT, " +
                "byline TEXT, " +
                "abstract TEXT, " +
                "published_date TEXT, " +
                "updated TEXT," +
                "url TEXT," +
                "url_image TEXT" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Date");
        onCreate(db);
    }

    public boolean insertArticle(Long id, String title, String keywords, String count_type,
                              String colum, String section, String byline, String abstr, String published_date, String updated, String url, String urlImage){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id", id);
        contentValues.put("title", title);
        contentValues.put("keywords", keywords);
        contentValues.put("count_type", count_type);
        contentValues.put("colum", colum);
        contentValues.put("section", section);
        contentValues.put("byline", byline);
        contentValues.put("abstract", abstr);
        contentValues.put("published_date", published_date);
        contentValues.put("updated", updated);
        contentValues.put("url", url);
        contentValues.put("url_image", urlImage);

        long result = db.insert("Article", null, contentValues);

        return result != 1;// if result = -1 data dosen`t insert
    }

    public Cursor viewArticle(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Article", null);
        return cursor;
    }

    public Cursor viewArticleId(Long id){
        String []str = new String[12];
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Article where id = '" + id +"'", null);
        return cursor;
    }

    public void delArticle(long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("Article", "id" + " = " + id, null);

    }

    public int viewKol(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Article", null);
        return cursor.getCount();
    }
}
