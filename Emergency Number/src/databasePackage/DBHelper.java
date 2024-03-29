package databasePackage; 
import java.util.ArrayList;
import java.util.HashMap; 
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;


public class DBHelper extends SQLiteOpenHelper { 
	
	
	public static final String DATABASE_NAME = "MyDBName.db"; 
	public static final String CONTACTS_TABLE_NAME = "contacts1";
	public static final String CONTACTS_COLUMN_ID = "id"; 
	public static final String CONTACTS_COLUMN_NAME = "name";
	public static final String CONTACTS_COLUMN_EMAIL = "email";
	public static final String CONTACTS_COLUMN_STREET = "street";
	public static final String CONTACTS_COLUMN_CITY = "place"; 
	public static final String CONTACTS_COLUMN_PHONE = "phone";
	private HashMap hp; 
	
	public DBHelper(Context context) { 
		super(context, DATABASE_NAME , null, 1);
		
		
	}
	@Override
	public void onCreate(SQLiteDatabase db)//Here My Database object db  is created
	{
		// TODO Auto-generated method stub
		db.execSQL( "create table contacts " + "(id integer primary key, name text,phone text,email text, street text,place text)" );
		
		//db.execSQL( "create table contacts1 " + "(id integer primary key, name text,phone text,email text, street text,place text)" );
		
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub 
		db.execSQL("DROP TABLE IF EXISTS contacts");
		db.execSQL("DROP TABLE IF EXISTS contacts1");
		onCreate(db); 
		}
	public boolean insertContact (String name, String phone, String email, String street,String place) {
		SQLiteDatabase db = this.getWritableDatabase(); 
		ContentValues contentValues = new ContentValues(); 
		contentValues.put("name", name); 
		contentValues.put("phone", phone); 
		contentValues.put("email", email);
		contentValues.put("street", street); 
		contentValues.put("place", place); 
		db.insert("contacts1", null, contentValues); 
		return true;
		}
	public Cursor getData(int id, String table){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery( "select * from "+table+" where id="+id+"", null ); 
		return res; 
		}
	public int numberOfRows(){
		SQLiteDatabase db = this.getReadableDatabase();
		int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME); 
		return numRows;
		}
	
	public boolean updateContact (Integer id, String name, String phone, String email, String street,String place) { 
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("name", name); 
		contentValues.put("phone", phone); 
		contentValues.put("email", email);
		contentValues.put("street", street);
		contentValues.put("place", place);
		db.update("contacts1", contentValues, "id = ? ", new String[] { Integer.toString(id) } ); 
		return true;
		}
	public Integer deleteContact (Integer id) { 
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete("contacts1", "id = ? ", new String[] { Integer.toString(id) }); 
		}
	public ArrayList getAllCotacts(String table) { 
		
		ArrayList array_list = new ArrayList();
		//hp = new HashMap();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery( "select * from "+table, null );
		res.moveToFirst(); 
		while(res.isAfterLast() == false){
			array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME))); 
			res.moveToNext();
	    }
		
		
		return array_list;
		
		
		
		}
	

	}
	