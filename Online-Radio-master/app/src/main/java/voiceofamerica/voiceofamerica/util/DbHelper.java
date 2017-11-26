package voiceofamerica.voiceofamerica.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by mwongela on 5/12/16.
 */
public class DbHelper {

    private DatabaseReference root;

    private static final String TAG = DbHelper.class.getSimpleName();
    private static  int DATABASE_VERSION = 28;
    private static final String DATABASE_NAME = "vamerica.voiceofamerica";

    //toukir.best.vamerica.voiceofamerica
    private static final String TABLE_WEBRADIO = "tblw";
    public static final String KEY_ROWID = "_id";
    public static final String KEY_RADIO_STATION_NAME = "radio_station_name";
    public static final String KEY_RADIO_URL = "radio_url";
    public static final String KEY_MUSIC_GENRE = "music_genre";
    public static final String KEY_FAV = "favourite";
    public static final String KEY_ORDER = "rorder";

    private DatabaseOpenHelper openHelper;
    private SQLiteDatabase database;

    public DbHelper(Context aContext) {
        openHelper = new DatabaseOpenHelper(aContext);
        database = openHelper.getWritableDatabase();
    }

    public Cursor getAllWebradios(){

        String buildSQL = "SELECT * FROM " + TABLE_WEBRADIO + " ORDER BY "+ KEY_ORDER+" desc , "+KEY_RADIO_STATION_NAME+ " ASC";
        Cursor systems =  database.rawQuery(buildSQL, null);
        systems.moveToFirst();
        return systems;
    }

    private void loadAlldata(final SQLiteDatabase ds ) {

        Log.d("firebasedata","called");

        root = FirebaseDatabase.getInstance().getReference().child("story");


    }

    String storyWritter, storyTitle, storyUrl;
    private void append_child(DataSnapshot dataSnapshot, SQLiteDatabase ds) {

        Iterator i = dataSnapshot.getChildren().iterator();

        while (i.hasNext()){
            storyTitle = (String) ((DataSnapshot)i.next()).getValue();
            storyWritter = (String) ((DataSnapshot)i.next()).getValue();
            storyUrl = (String) ((DataSnapshot)i.next()).getValue();

            Log.d("firebasevalue",storyTitle + "\n" + storyWritter + "\n" + storyUrl);


            String buildWebradioSQL ="create table " + TABLE_WEBRADIO + " ("
                    + KEY_ROWID + " integer primary key autoincrement, "
                    + KEY_RADIO_STATION_NAME + " text, "
                    + KEY_RADIO_URL + " text, "
                    + KEY_MUSIC_GENRE + " text, "
                    + KEY_FAV + " text DEFAULT 0,"
                    + KEY_ORDER+" int default 0);";
            Log.d(TAG, "onCreate SQL: " + buildWebradioSQL);
            ds.execSQL(buildWebradioSQL);

            //Insert webradios
            String insertWebradioSQL1 ="INSERT INTO " + TABLE_WEBRADIO + " ("
                    + KEY_RADIO_STATION_NAME + ", "
                    + KEY_RADIO_URL + ", "
                    + KEY_MUSIC_GENRE +") VALUES "+


                    //old radio stations

                    "(\""+storyTitle+"\", \"http://audio.scdn.arkena.com/11359/fbpaysdauvergne-midfi128.mp3\", \"Actualité, Information, News\"),"+
                    "(\""+storyUrl+"\", \"http://188.166.176.244:8919/index.html?sid=1\", \"ABC, ABC\"),"+
                    "(\""+storyWritter+"\", \"http://audio.scdn.arkena.com/11387/fbperigord-midfi128.mp3\", \"Actualité, Information, News\");";

            //break

            String insertWebradioSQL2 ="INSERT INTO " + TABLE_WEBRADIO + " ("
                    + KEY_RADIO_STATION_NAME + ", "
                    + KEY_RADIO_URL + ", "
                    + KEY_MUSIC_GENRE +") VALUES "+

                    "(\"MFM Radio 100% Enfoirés\", \"http://mfm-wr09.ice.infomaniak.ch/mfm-wr09.mp3\", \"Chansons françaises, French songs\"),"+
                    "(\"Voot\", \"http://dl.bhoot-fm.com/Bhoot-FM_2017-10-31_(Bhoot-FM.com).mp3\", \"ABC, ABC\"),"+
                    "(\"MFM Radio 100% Goldman\", \"http://dl.bhoot-fm.com/Bhoot-FM_2017-10-31_(Bhoot-FM.com).mp3\", \"Chansons françaises, French songs\");";

            String insertWebradioSQL3 ="INSERT INTO " + TABLE_WEBRADIO + " ("
                    + KEY_RADIO_STATION_NAME + ", "
                    + KEY_RADIO_URL + ", "
                    + KEY_MUSIC_GENRE +") VALUES "+
                    "(\"M2 Funk\", \"http://live.m2stream.fr/m2funk-128.mp3\", \"Disco, Funk\"),"+
                    "(\"ABC\", \"http://st2.zenorad.io:14424\", \"ABC, ABC\"),"+
                    "(\"NRJ Disney\", \"http://triton.scdn.arkena.com/13209/live.mp3\", \"Disney Channel\");";

            String insertWebradioSQL4 ="INSERT INTO " + TABLE_WEBRADIO + " ("
                    + KEY_RADIO_STATION_NAME + ", "
                    + KEY_RADIO_URL + ", "
                    + KEY_MUSIC_GENRE +") VALUES "+

                    "(\"NRJ Hits for Girls\", \"http://triton.scdn.arkena.com/13149/live.mp3\", \"Hits\"),"+
                    "(\"ABC\", \"http://st2.zenorad.io:14424\", \"ABC, ABC\"),"+
                    "(\"NRJ Hits of the Month\", \"http://triton.scdn.arkena.com/13037/live.mp3\", \"Hits\");";

            String insertWebradioSQL5 ="INSERT INTO " + TABLE_WEBRADIO + " ("
                    + KEY_RADIO_STATION_NAME + ", "
                    + KEY_RADIO_URL + ", "
                    + KEY_MUSIC_GENRE +") VALUES "+

                    "(\"Rire et Chansons Canulars\", \"http://adwzg3.tdf-cdn.com/8645/nrj_172621.mp3\", \"Humour\"),"+
                    "(\"ABC\", \"http://st2.zenorad.io:14424\", \"ABC, ABC\"),"+
                    "(\"Rire et Chansons Collectors\", \"http://adwzg3.tdf-cdn.com/8648/nrj_166789.mp3\", \"Humour\");";

            String insertWebradioSQL6 ="INSERT INTO " + TABLE_WEBRADIO + " ("
                    + KEY_RADIO_STATION_NAME + ", "
                    + KEY_RADIO_URL + ", "
                    + KEY_MUSIC_GENRE +") VALUES "+

                    "(\"Ado Love\", \"http://adolove.bcast.infomaniak.ch/adolove-128.mp3\", \"Love, Pop, RnB\"),"+
                    "(\"ABC\", \"http://st2.zenorad.io:14424\", \"ABC, ABC\"),"+
                    "(\"Blackbox Love\", \"http://blackboxlove.bcast.infomaniak.ch/blackboxlove-128.mp3\", \"Love, RnB\"),"+
                    "(\"HotmixRadio Hot\", \"http://streamingads.hotmixradio.fm/hotmixradio-hot-128.mp3\", \"Love, RnB, Soul\");";

            String insertWebradioSQL7 ="INSERT INTO " + TABLE_WEBRADIO + " ("
                    + KEY_RADIO_STATION_NAME + ", "
                    + KEY_RADIO_URL + ", "
                    + KEY_MUSIC_GENRE +") VALUES "+

                    "(\"Alpes 1 Gap\", \"http://alpes1gap.ice.infomaniak.ch/alpes1gap-high.mp3\", \"Radio locale, Local service\"),"+
                    "(\"ABC\", \"http://st2.zenorad.io:14424\", \"ABC, ABC\"),"+
                    "(\"Canal FM\", \"http://51.255.162.41:8000/canalfm.mp3\", \"Radio locale, Local service\");";


            String insertWebradioSQL8 ="INSERT INTO " + TABLE_WEBRADIO + " ("
                    + KEY_RADIO_STATION_NAME + ", "
                    + KEY_RADIO_URL + ", "
                    + KEY_MUSIC_GENRE +") VALUES "+

                    "(\"Urban Hit\", \"http://onlyrai.ice.infomaniak.ch/onlyrai-high.mp3\", \"Rap, RnB\"),"+
                    "(\"ABC\", \"http://st2.zenorad.io:14424\", \"ABC, ABC\"),"+
                    "(\"Radio Sun\", \"http://broadcast.infomaniak.net/radiosun-high.mp3\", \"Rap, RnB, Reggae\");";

            String insertWebradioSQL9 ="INSERT INTO " + TABLE_WEBRADIO + " ("
                    + KEY_RADIO_STATION_NAME + ", "
                    + KEY_RADIO_URL + ", "
                    + KEY_MUSIC_GENRE +") VALUES "+

                    "(\"NRJ Hits 2016\", \"http://triton.scdn.arkena.com/15242/live_aac.mp3\", \"Hits 2016\"),"+
                    "(\"ABC\", \"http://st2.zenorad.io:14424\", \"ABC, ABC\"),"+
                    "(\"NRJ Dubstep\", \"http://triton.scdn.arkena.com/15474/live_aac.mp3\", \"Dubstep\");";

            ds.execSQL(insertWebradioSQL1);
            //sqLiteDatabase.execSQL(insertWebradioSQL2);
            //sqLiteDatabase.execSQL(insertWebradioSQL3);
            //sqLiteDatabase.execSQL(insertWebradioSQL4);
            //sqLiteDatabase.execSQL(insertWebradioSQL5);
            //sqLiteDatabase.execSQL(insertWebradioSQL6);
            //sqLiteDatabase.execSQL(insertWebradioSQL7);
            //sqLiteDatabase.execSQL(insertWebradioSQL8);
            //sqLiteDatabase.execSQL(insertWebradioSQL9);

            updateOrder(ds);

        }

    }

    public Cursor getAllWebradios(String query){
        String buildSQL = "SELECT * FROM " + TABLE_WEBRADIO + " WHERE "+KEY_RADIO_STATION_NAME+" LIKE '%"+query+"%' OR "+KEY_MUSIC_GENRE+" LIKE '%"+query+"%' ORDER BY "+ KEY_ORDER+" desc , "+KEY_RADIO_STATION_NAME+ " ASC";
        Log.e(TAG, buildSQL);
        Cursor systems =  database.rawQuery(buildSQL, null);
        systems.moveToFirst();
        return systems;
    }

    public Cursor getFavoriteWebradios(){
        String buildSQL = "SELECT * FROM " + TABLE_WEBRADIO + " WHERE "+KEY_FAV+" = 1 ORDER BY "+ KEY_ORDER+" desc , "+KEY_RADIO_STATION_NAME+ " ASC";
        Cursor systems =  database.rawQuery(buildSQL, null);
        systems.moveToFirst();

        return systems;
    }

    public int getRadioFavStatus (String rowId) {
        String buildSQL = "SELECT * FROM " + TABLE_WEBRADIO +" WHERE " + KEY_ROWID + " = '"+rowId+"'";
        Cursor c =  database.rawQuery(buildSQL, null);
        c.moveToFirst();
        return c.getInt(c.getColumnIndex(c.getColumnName(4)));
    }

    public int getRadioId(String rowId) {
        String buildSQL = "SELECT * FROM " + TABLE_WEBRADIO +" WHERE " + KEY_RADIO_STATION_NAME + " = '"+rowId+"'";
        Cursor c =  database.rawQuery(buildSQL, null);
        c.moveToFirst();
        return c.getInt(c.getColumnIndex(c.getColumnName(0)));
    }

    public String getRadioURL (String RadioName) {
        String buildSQL = "SELECT * FROM " + TABLE_WEBRADIO +" WHERE " + KEY_RADIO_STATION_NAME+ " = '"+RadioName+"'";
        Cursor c =  database.rawQuery(buildSQL, null);
        c.moveToFirst();
        return c.getString(c.getColumnIndex(c.getColumnName(2)));
    }

    public String getRadioName (String rowId) {
        String buildSQL = "SELECT * FROM " + TABLE_WEBRADIO +" WHERE " + KEY_ROWID + " = '"+rowId+"'";
        Cursor c =  database.rawQuery(buildSQL, null);
        c.moveToFirst();
        return c.getString(c.getColumnIndex(c.getColumnName(1)));
    }

    public void updateRadioFavStatus(String rowId, String status) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_FAV, status);
        database.update(TABLE_WEBRADIO, contentValues, KEY_ROWID + " = " + rowId, null);
        Log.e(TAG, "Set "+getRadioName(rowId)+" to "+status);
    }

    private class DatabaseOpenHelper extends SQLiteOpenHelper {

        public DatabaseOpenHelper(Context aContext) {
            super(aContext, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {


            loadAlldata(sqLiteDatabase);

////            String buildWebradioSQL ="create table " + TABLE_WEBRADIO + " ("
////                    + KEY_ROWID + " integer primary key autoincrement, "
////                    + KEY_RADIO_STATION_NAME + " text, "
////                    + KEY_RADIO_URL + " text, "
////                    + KEY_MUSIC_GENRE + " text, "
////                    + KEY_FAV + " text DEFAULT 0,"
////                    + KEY_ORDER+" int default 0);";
////            Log.d(TAG, "onCreate SQL: " + buildWebradioSQL);
////            sqLiteDatabase.execSQL(buildWebradioSQL);
////
////            //Insert webradios
////            String insertWebradioSQL1 ="INSERT INTO " + TABLE_WEBRADIO + " ("
////                    + KEY_RADIO_STATION_NAME + ", "
////                    + KEY_RADIO_URL + ", "
////                    + KEY_MUSIC_GENRE +") VALUES "+
////
////
////                    //old radio stations
////
////                  "(\"France Bleu Pays d’Auvergne\", \"http://audio.scdn.arkena.com/11359/fbpaysdauvergne-midfi128.mp3\", \"Actualité, Information, News\"),"+
////                    "(\"SHEI\", \"http://188.166.176.244:8919/index.html?sid=1\", \"ABC, ABC\"),"+
////                    "(\"France Bleu Perigord\", \"http://audio.scdn.arkena.com/11387/fbperigord-midfi128.mp3\", \"Actualité, Information, News\");";
//
//            //break
//
//            String insertWebradioSQL2 ="INSERT INTO " + TABLE_WEBRADIO + " ("
//                    + KEY_RADIO_STATION_NAME + ", "
//                    + KEY_RADIO_URL + ", "
//                    + KEY_MUSIC_GENRE +") VALUES "+
//
//                   "(\"MFM Radio 100% Enfoirés\", \"http://mfm-wr09.ice.infomaniak.ch/mfm-wr09.mp3\", \"Chansons françaises, French songs\"),"+
//                    "(\"Voot\", \"http://dl.bhoot-fm.com/Bhoot-FM_2017-10-31_(Bhoot-FM.com).mp3\", \"ABC, ABC\"),"+
//                    "(\"MFM Radio 100% Goldman\", \"http://dl.bhoot-fm.com/Bhoot-FM_2017-10-31_(Bhoot-FM.com).mp3\", \"Chansons françaises, French songs\");";
//
//            String insertWebradioSQL3 ="INSERT INTO " + TABLE_WEBRADIO + " ("
//                    + KEY_RADIO_STATION_NAME + ", "
//                    + KEY_RADIO_URL + ", "
//                    + KEY_MUSIC_GENRE +") VALUES "+
//                   "(\"M2 Funk\", \"http://live.m2stream.fr/m2funk-128.mp3\", \"Disco, Funk\"),"+
//                    "(\"ABC\", \"http://st2.zenorad.io:14424\", \"ABC, ABC\"),"+
//                    "(\"NRJ Disney\", \"http://triton.scdn.arkena.com/13209/live.mp3\", \"Disney Channel\");";
//
//            String insertWebradioSQL4 ="INSERT INTO " + TABLE_WEBRADIO + " ("
//                    + KEY_RADIO_STATION_NAME + ", "
//                    + KEY_RADIO_URL + ", "
//                    + KEY_MUSIC_GENRE +") VALUES "+
//
//                   "(\"NRJ Hits for Girls\", \"http://triton.scdn.arkena.com/13149/live.mp3\", \"Hits\"),"+
//                    "(\"ABC\", \"http://st2.zenorad.io:14424\", \"ABC, ABC\"),"+
//                    "(\"NRJ Hits of the Month\", \"http://triton.scdn.arkena.com/13037/live.mp3\", \"Hits\");";
//
//            String insertWebradioSQL5 ="INSERT INTO " + TABLE_WEBRADIO + " ("
//                    + KEY_RADIO_STATION_NAME + ", "
//                    + KEY_RADIO_URL + ", "
//                    + KEY_MUSIC_GENRE +") VALUES "+
//
//                   "(\"Rire et Chansons Canulars\", \"http://adwzg3.tdf-cdn.com/8645/nrj_172621.mp3\", \"Humour\"),"+
//                    "(\"ABC\", \"http://st2.zenorad.io:14424\", \"ABC, ABC\"),"+
//                    "(\"Rire et Chansons Collectors\", \"http://adwzg3.tdf-cdn.com/8648/nrj_166789.mp3\", \"Humour\");";
//
//            String insertWebradioSQL6 ="INSERT INTO " + TABLE_WEBRADIO + " ("
//                    + KEY_RADIO_STATION_NAME + ", "
//                    + KEY_RADIO_URL + ", "
//                    + KEY_MUSIC_GENRE +") VALUES "+
//
//                  "(\"Ado Love\", \"http://adolove.bcast.infomaniak.ch/adolove-128.mp3\", \"Love, Pop, RnB\"),"+
//                    "(\"ABC\", \"http://st2.zenorad.io:14424\", \"ABC, ABC\"),"+
//                    "(\"Blackbox Love\", \"http://blackboxlove.bcast.infomaniak.ch/blackboxlove-128.mp3\", \"Love, RnB\"),"+
//                    "(\"HotmixRadio Hot\", \"http://streamingads.hotmixradio.fm/hotmixradio-hot-128.mp3\", \"Love, RnB, Soul\");";
//
//            String insertWebradioSQL7 ="INSERT INTO " + TABLE_WEBRADIO + " ("
//                    + KEY_RADIO_STATION_NAME + ", "
//                    + KEY_RADIO_URL + ", "
//                    + KEY_MUSIC_GENRE +") VALUES "+
//
//                   "(\"Alpes 1 Gap\", \"http://alpes1gap.ice.infomaniak.ch/alpes1gap-high.mp3\", \"Radio locale, Local service\"),"+
//                    "(\"ABC\", \"http://st2.zenorad.io:14424\", \"ABC, ABC\"),"+
//                    "(\"Canal FM\", \"http://51.255.162.41:8000/canalfm.mp3\", \"Radio locale, Local service\");";
//
//
//            String insertWebradioSQL8 ="INSERT INTO " + TABLE_WEBRADIO + " ("
//                    + KEY_RADIO_STATION_NAME + ", "
//                    + KEY_RADIO_URL + ", "
//                    + KEY_MUSIC_GENRE +") VALUES "+
//
//                   "(\"Urban Hit\", \"http://onlyrai.ice.infomaniak.ch/onlyrai-high.mp3\", \"Rap, RnB\"),"+
//                    "(\"ABC\", \"http://st2.zenorad.io:14424\", \"ABC, ABC\"),"+
//                    "(\"Radio Sun\", \"http://broadcast.infomaniak.net/radiosun-high.mp3\", \"Rap, RnB, Reggae\");";
//
//            String insertWebradioSQL9 ="INSERT INTO " + TABLE_WEBRADIO + " ("
//                    + KEY_RADIO_STATION_NAME + ", "
//                    + KEY_RADIO_URL + ", "
//                    + KEY_MUSIC_GENRE +") VALUES "+
//
//                  "(\"NRJ Hits 2016\", \"http://triton.scdn.arkena.com/15242/live_aac.mp3\", \"Hits 2016\"),"+
//                    "(\"ABC\", \"http://st2.zenorad.io:14424\", \"ABC, ABC\"),"+
//                    "(\"NRJ Dubstep\", \"http://triton.scdn.arkena.com/15474/live_aac.mp3\", \"Dubstep\");";
//
//            sqLiteDatabase.execSQL(insertWebradioSQL1);
//            //sqLiteDatabase.execSQL(insertWebradioSQL2);
//            //sqLiteDatabase.execSQL(insertWebradioSQL3);
//            //sqLiteDatabase.execSQL(insertWebradioSQL4);
//            //sqLiteDatabase.execSQL(insertWebradioSQL5);
//            //sqLiteDatabase.execSQL(insertWebradioSQL6);
//            //sqLiteDatabase.execSQL(insertWebradioSQL7);
//            //sqLiteDatabase.execSQL(insertWebradioSQL8);
//            //sqLiteDatabase.execSQL(insertWebradioSQL9);
//
//            updateOrder(sqLiteDatabase);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            String buildWebradioSQL = "DROP TABLE IF EXISTS " + TABLE_WEBRADIO;
            Log.d(TAG, "onUpgrade SQL: " + buildWebradioSQL);

            sqLiteDatabase.execSQL(buildWebradioSQL);

            onCreate(sqLiteDatabase);
        }
    }

    public DbHelper open() throws SQLException {
        database = openHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        openHelper.close();
    }

    private void updateOrder(SQLiteDatabase database){
        String where = KEY_RADIO_STATION_NAME+" in (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String[] whereVals = {
                "RTL", "NRJ", "RMC", "Fun Radio", "Skyrock", "Europe 1", "Radio Fg", "RTL2", "France Info", "Virgin Radio",
                "Chérie FM", "Le Mouv", "ADO FM", "RFM", "Latina", "Hit West", "France Inter", "Nova",
                "Voltage", "Radio Classique", "Alouette", "MFM", "Jazz Radio", "France Culture"
        };
        ContentValues vals = new ContentValues();
        vals.put(KEY_ORDER,1);
        database.update(TABLE_WEBRADIO, vals, where, whereVals);
    }
}