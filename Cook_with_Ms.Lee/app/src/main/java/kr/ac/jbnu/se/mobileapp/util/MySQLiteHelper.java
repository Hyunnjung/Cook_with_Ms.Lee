//package kr.ac.jbnu.se.mobileapp.util;
///**
// * Copyright 2018 All rights reserved by WaySeekers.
// *
// * @author bongO moon
// * @since 2018. 04. 16.
// */
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.widget.Toast;
//
//public class MySQLiteHelper extends SQLiteOpenHelper{
//    private Context context;
//    //상수 클래스에 데이터베이스 버전 저장
//
//    public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
//        super(context, name, null, version);
//        this.context = context;
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db){
//        StringBuffer sb = new StringBuffer();
//        sb.append(" CREATE TABLE user ( ");
//        sb.append(" _id INTEGER PRIMARY KEY AUTOINCREMENT, ");
//        sb.append(" name TEXT, ");
//        sb.append(" email TEXT, ");
//        sb.append(" email_verified INTEGER ");
//        sb.append(" profile_photo TEXT");
//
//        db.execSQL(sb.toString());
//
//        Toast.makeText(context, "User Table 생성 완료", Toast.LENGTH_SHORT).show();
//
//    }
//
//    /**
//     * Application의 버전이 올라가서
//     * Table 구조가 변경되었을 때 실행된다.
//     * @param db
//     * @param oldVersion
//     * @param newVersion
//     */
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
//        Toast.makeText(context, "version upgrade", Toast.LENGTH_SHORT).show();
//    }
//
//    public void userDB(){
//        SQLiteDatabase db = getReadableDatabase();
//    }
//}
