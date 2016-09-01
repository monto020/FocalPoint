package com.example.montoya.focalpoint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Montoya on 5/7/2016.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

//    protected static MySQLiteHelper sqliteInstance;
//    protected static SQLiteDatabase database;
//    protected static Context context;

    private static final String DATABASE_NAME = "focalPointApp.db";
    private static final int DATABASE_VERSION = 18;

    //users table info
    public static final String TABLE_USERS = "users";
    public static final String USER_ID = "id";
    public static final String USER_NAME = "userName";
    public static final String PASSWORD = "password";

    //focus_sessions table info
    public static final String TABLE_FOCUS_SESSION = "focusSession";
    //public static final String USER_ID = "id";
    public static final String SESSION_ID = "sessionId";
    public static final String SESSION_TIME = "sessionTime";
    public static final String SESSION_SUCCESS = "sessionSuccess";
    public static final String SESSION_MONTH = "sessionMonth";
    public static final String SESSION_YEAR = "sessionYear";

    //blocked_apps table info
    public static final String TABLE_BLOCKED_APPS = "blockedApps";
    //public static final String USER_ID = "id";
    public static final String BLOCKED_NAME = "blockedName";
    public static final String BLOCKED_STATUS = "blockedStatus";



    // Database creation sql statement
    private static final String CREATE_USERS_TABLE =
            "create table " + TABLE_USERS + "("
                    + USER_ID + " integer primary key autoincrement, "
                    + USER_NAME + " text unique not null, "
                    + PASSWORD + " text); ";

    private static final String CREATE_FOCUS_SESSION_TABLE =
            "create table " + TABLE_FOCUS_SESSION + "("
                    + USER_ID + " integer not null default 0, "
                    + SESSION_ID + " integer primary key autoincrement, "
                    + SESSION_TIME +" integer not null, "
                    +SESSION_SUCCESS+" integer not null, "
                    +SESSION_MONTH+" integer not null, "
                    +SESSION_YEAR+" integer not null); ";

    private static final String CREATE_BLOCKED_APPS_TABLE =
            "create table " + TABLE_BLOCKED_APPS+ "("
                    + USER_ID + " integer not null default 0, "
                    + BLOCKED_NAME + " text unique not null,"
                    +BLOCKED_STATUS + " integer not null);";

//    public static synchronized MySQLiteHelper getInstance(Context context) {
//
//        // Use the application context, which will ensure that you
//        // don't accidentally leak an Activity's context.
//        // See this article for more information: http://bit.ly/6LRzfx
//        if (sqliteInstance == null) {
//            sqliteInstance = new MySQLiteHelper(context.getApplicationContext());
//        }
//        return sqliteInstance;
//    }

//    public String[] columns() {
//        //SQLiteDatabase mDataBase;
//        SQLiteDatabase db = this.getReadableDatabase();
//        //(some code here...)
//       // mDataBase = getReadableDatabase();
//        Cursor dbCursor = db.query(TABLE_USERS, null, null, null, null, null, null);
//        String[] columnNames = dbCursor.getColumnNames();
//
//        return columnNames;
//    }//text function to see if tables are made correctly

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

//    private MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//        sqliteInstance.context = context;
//    }


    @Override
    public void onCreate(SQLiteDatabase database) {


        database.execSQL(CREATE_USERS_TABLE);
        database.execSQL(CREATE_FOCUS_SESSION_TABLE);
        database.execSQL(CREATE_BLOCKED_APPS_TABLE);
    }

    public Cursor getUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor userResult =  db.rawQuery( "select * from users where id="+id+"", null );
        userResult.moveToFirst();


        return userResult;
    }

    public Cursor getUserId(String name){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor personForId = db.rawQuery("select * from users where userName='" +name+"'", null);
        personForId.moveToFirst();

        return personForId;

    }


    public Cursor getFocusSession(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor userResult =  db.rawQuery( "select * from focusSession where id="+id+"", null );
        userResult.moveToFirst();


        return userResult;
    }

    public Cursor getBlockedApps(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor userResult =  db.rawQuery( "select * from blockedApps where id="+id+" and blockedStatus=1", null );
            userResult.moveToFirst();
       // return userResult.getString(1);
        //userResult.moveToFirst();

        return userResult;
    }

//    public static final String SESSION_TIME = "sessionTime";
//    public static final String SESSION_SUCCESS = "sessionSuccess";
//    public static final String SESSION_MONTH = "sessionMonth";
//    public static final String SESSION_YEAR = "sessionYear";

    public Cursor getGraphPoint(int month, int year){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor userResult =  db.rawQuery("select sessionTime from focusSession where sessionMonth="+month+" and sessionYear="+year ,null);
        userResult.moveToFirst();

        return userResult;
    }



    public boolean insertUser(String name, String password){
        SQLiteDatabase db = getWritableDatabase();

        //load a users values
        ContentValues values = new ContentValues();
       // values.put(USER_ID,);
        values.put(USER_NAME, name);
        values.put(PASSWORD, password);
        // Insert the new row, returning the primary key value of the new row
        db.insertWithOnConflict(TABLE_USERS, null, values, SQLiteDatabase.CONFLICT_IGNORE);
       // db.insert(TABLE_USERS,null,values);

        return true;
    }

    public boolean insertFocusSession(int userId, int sessionTime,int sessionSuccess, int sessionMonth, int sessionYear){
        SQLiteDatabase db = getWritableDatabase();

        //load a users values
        ContentValues values = new ContentValues();
        values.put(USER_ID, userId);
        //values.put(SESSION_ID, sessionId);
        values.put(SESSION_TIME, sessionTime);
        values.put(SESSION_SUCCESS, sessionSuccess);
        values.put(SESSION_MONTH,sessionMonth);
        values.put(SESSION_YEAR, sessionYear);
        db.insertWithOnConflict(TABLE_FOCUS_SESSION, null, values, SQLiteDatabase.CONFLICT_IGNORE);

        return true;
    }



    public boolean insertBlockedApp(int userId, String blockedName,int blockedStatus){
        SQLiteDatabase db = getWritableDatabase();
        //load a users values
        ContentValues values = new ContentValues();
        values.put(USER_ID, userId);
        values.put(BLOCKED_NAME, blockedName);
        values.put(BLOCKED_STATUS, blockedStatus);
        db.insertWithOnConflict(TABLE_BLOCKED_APPS, null, values, SQLiteDatabase.CONFLICT_IGNORE);

        return true;
    }

    public Cursor getBlockedAppsStatus(String name, int id){
        SQLiteDatabase db = this.getReadableDatabase();//blockedApps

        Cursor userResult =  db.rawQuery( "select blockedStatus from blockedApps where id="+id+" and blockedName='"+name+"'", null );
        userResult.moveToFirst();
        // return userResult.getString(1);
        //userResult.moveToFirst();

        return userResult;
    }
//
//    public static final String TABLE_BLOCKED_APPS = "blockedApps";
//    //public static final String USER_ID = "id";
//    public static final String BLOCKED_NAME = "blockedName";
//    public static final String BLOCKED_STATUS = "blockedStatus";

    public void changeBlocked(int status, String appName, int id){
        SQLiteDatabase db = getWritableDatabase();
      // String strSQL = "UPDATE blockedApps SET Column1 = someValue WHERE columnId = "+ someValue;
    String strSQL="update blockedApps SET blockedStatus="+status+" where blockedName='"+appName+"' and id="+id;
        db.execSQL(strSQL);
        //load a users values
        //ContentValues values = new ContentValues();
        //values.put(Profile_keysofweekly, keystemp);
        //values.put(BLOCKED_STATUS,status);
        //db.update(TABLE_BLOCKED_APPS,values,BLOCKED_NAME+"='"+appName+"' and id="+id,null);



    }

//    public int numberOfRows(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
//        return numRows;
//    }
//
    public int numberOfRowsUsers(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_USERS);

        return numRows;
    }
    //    public static final String SESSION_TIME = "sessionTime";
//    public static final String SESSION_SUCCESS = "sessionSuccess";
//    public static final String SESSION_MONTH = "sessionMonth";
//    public static final String SESSION_YEAR = "sessionYear";

    public int numberOfRowsFocusSession(int month, int year){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_FOCUS_SESSION+" where sessionMonth="+month+" and sessionYear="+year);

        return numRows;
    }

    public int numberOfRowsBlockedApps(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_BLOCKED_APPS+" where blockedStatus=1");

        return numRows;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOCUS_SESSION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BLOCKED_APPS);
        onCreate(db);
    }

}
