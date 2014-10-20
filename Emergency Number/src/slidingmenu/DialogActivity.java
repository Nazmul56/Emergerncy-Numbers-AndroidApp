package slidingmenu;

/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import info.androidhive.slidingmenu.R;

import java.util.ArrayList;



import databasePackage.DBHelper;





import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DialogActivity extends Activity {
   

	String[] phonelist ;
	public static final String ARG_PLANET_NUMBER = "planet_number";
	private DBHelper mydb ;
	public String phone = new String();
	public String phon = new String();
	public String phon1 = new String();
	public String phon2 = new String();
	
	public ArrayList array_list = new ArrayList();
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog);
       
        mydb = new DBHelper(this);
        
          Bundle extras = getIntent().getExtras();
      
        	int Value = extras.getInt(ARG_PLANET_NUMBER);
        	
        	if(Value>0){
    			//means this is the view part not the add contact part.
    			
    				Cursor rs = mydb.getData(Value, MainActivity.catagory);
    			
    			//	id_To_Update = Value; 
    			
    				rs.moveToFirst(); 
    				
    				String nam = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_NAME));
    				
    				
    			     phon = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_PHONE));
    				 phon1 = rs.getString(rs.getColumnIndex("phone1"));
    				 phon2 = rs.getString(rs.getColumnIndex("phone2"));
    				//String emai = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_EMAIL)); 
    				//String stree = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_STREET));
    				//String plac = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_CITY));
    				TextView msgTextView = (TextView) findViewById(R.id.tv1); 
    				msgTextView.setText(nam);
    				
    				if(phon!=null)
    					array_list.add(rs.getString(rs.getColumnIndex("phone")));	
    				
    				if(phon1!=null)
    					array_list.add(rs.getString(rs.getColumnIndex("phone1")));
    				
    				if(phon2!=null)
    				    array_list.add(rs.getString(rs.getColumnIndex("phone2"))); 
    				
    				
    				phone = phon;
    				
    				if (!rs.isClosed())
    				{ 
    					rs.close();
    				
    				} 
        	}
        
       
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview,array_list); 
		ListView listView = (ListView) findViewById(R.id.listView1); 
		listView.setAdapter(adapter);
		
	
		listView.setOnItemClickListener(new OnItemClickListener(){ 
		@Override 
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) { 
			// TODO Auto-generated method stub 
			//Toast.makeText(DialogActivity.this, "Call faild, please try again later.", Toast.LENGTH_SHORT).show();
			
			Intent phoneIntent = new Intent(Intent.ACTION_CALL);
			
			if(arg2 == 0)
			phoneIntent.setData(Uri.parse("tel:"+phon));
			if(arg2 == 1)
				phoneIntent.setData(Uri.parse("tel:"+phon1));
			if(arg2 == 2)
				phoneIntent.setData(Uri.parse("tel:"+phon2));
			
			
			try { 
				startActivity(phoneIntent);
				finish(); 
				Log.i("Finished making a call...", "");
				} 
			catch (android.content.ActivityNotFoundException ex) {
				Toast.makeText(DialogActivity.this, "Call faild, please try again later.", Toast.LENGTH_SHORT).show();
				
			}
			
			} });
	
	
	
	
	
	}
		
	
        
    

    /**
     * Callback method defined by the View
     * @param v
     */
    public void finishDialog(View v) {
        DialogActivity.this.finish();
    }
}
