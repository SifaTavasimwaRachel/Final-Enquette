package isig.example.glodi.progetenquette;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {



    private static  final int DATABASE_VERSION=1;
    private  static final String CREATE_TABLE_ENQUETE="create table "+
            Dbenquette.TABLE_NAME_ENQUETTE+"(" +
            ""+Dbenquette.CODE_ENQUETTE+" integer primary key autoincrement,"+
            Dbenquette.NOM+" text,"+
            Dbenquette.ADRESSE+" text,"+
            Dbenquette.CONTACT+" text,"+
            Dbenquette.NIVEAU_ETUDE+" text,"+
            Dbenquette.REPONSE1+" text,"+
            Dbenquette.REPONSE2+" text,"+
            Dbenquette.REPONSE3+" text,"+
            Dbenquette.REPONSE4+" text,"+
            Dbenquette.REPONSE5+" text);";
    private  static final String DROP_TABLE_ENQUETE="drop table if exists "+Dbenquette.TABLE_NAME_ENQUETTE;


    private  static final String CREATE_TABLE_QUESTION="create table "+
            Dbenquette.TABLE_NAME_QUESTION+"(" +
            ""+Dbenquette.CODE_QUESTION+" integer primary key autoincrement,"+
            Dbenquette.DESIGNATION_QUESTION+" text,"+
            Dbenquette.REF_RUBRIQUE+" text);";
    private  static final String DROP_TABLE_QUESTION="drop table if exists "+Dbenquette.TABLE_NAME_QUESTION;


    private  static final String CREATE_TABLE_USER="create table "+
            Dbenquette.TABLE_NAME_UTILISATEUR+"(" +
            ""+Dbenquette.CODE_USER+" integer primary key autoincrement,"+
            Dbenquette.USERNAME+" text,"+
            Dbenquette.PASSWORD+" text);";
    private  static final String DROP_TABLE_USER="drop table if exists "+Dbenquette.TABLE_NAME_UTILISATEUR;


    public DbHelper(Context context)
    {
        super(context,Dbenquette.DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)    {

        db.execSQL(CREATE_TABLE_ENQUETE);
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_QUESTION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_ENQUETE);
        db.execSQL(DROP_TABLE_USER);
        db.execSQL(DROP_TABLE_QUESTION);
        onCreate(db);
    }

    public void saveTolocalDatabase_client(String name, String adresse, String contact, String niveauEtude,
                                           String reponse1,String reponse2,
                                           String reponse3,String reponse4,String reponse5,SQLiteDatabase database)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(Dbenquette.NOM,name);
        contentValues.put(Dbenquette.ADRESSE,adresse);
        contentValues.put(Dbenquette.CONTACT,contact);
        contentValues.put(Dbenquette.NIVEAU_ETUDE,niveauEtude);
        contentValues.put(Dbenquette.REPONSE1,reponse1);
        contentValues.put(Dbenquette.REPONSE2,reponse2);
        contentValues.put(Dbenquette.REPONSE3,reponse3);
        contentValues.put(Dbenquette.REPONSE4,reponse4);
        contentValues.put(Dbenquette.REPONSE5,reponse5);

        database.insert(Dbenquette.TABLE_NAME_ENQUETTE,null,contentValues);
    }

    public void saveTolocalDatabase_user(String username, String password,SQLiteDatabase database)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(Dbenquette.USERNAME,username);
        contentValues.put(Dbenquette.PASSWORD,password);
        database.insert(Dbenquette.TABLE_NAME_UTILISATEUR,null,contentValues);
    }

    public void saveTolocalDatabase_question(String designation, String refrubrique,SQLiteDatabase database)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(Dbenquette.USERNAME,designation);
        contentValues.put(Dbenquette.PASSWORD,refrubrique);
        database.insert(Dbenquette.TABLE_NAME_QUESTION,null,contentValues);
    }


    public int testeloginuser(String name,String password){

        int teste=0;
        SQLiteDatabase db=this.getReadableDatabase();
        db.beginTransaction();
        try{
            String selectQuery="SELECT * FROM "+Dbenquette.TABLE_NAME_UTILISATEUR+" WHERE "+Dbenquette.USERNAME+"= '"+name+"' AND "+Dbenquette.PASSWORD+"= '"+password+"' ";
            //String selectQuery="SELECT * FROM "+Dbpaiemobile.TABLE_NAME_CLIENT+" ";
            Cursor cursor=db.rawQuery(selectQuery,null);
            if(cursor.getCount()==1){
                teste=1;
                while(cursor.moveToNext()){
                    int code=cursor.getInt(cursor.getColumnIndex(Dbenquette.CODE_USER));
                }
            }
            else{
                teste=0;
            }
            db.setTransactionSuccessful();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
            db.close();
        }

        return  teste;
    }



}
