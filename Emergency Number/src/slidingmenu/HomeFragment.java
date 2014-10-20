package slidingmenu;


import java.util.ArrayList;

import databasePackage.DBHelper;
import info.androidhive.slidingmenu.R;
import info.androidhive.slidingmenu.R.layout;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HomeFragment extends ListFragment {
	public static final String ARG_PLANET_NUMBER = "planet_number";
	private DBHelper mydb ;
   
	
	public HomeFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
		
		
                 mydb = new DBHelper(getActivity());
		
        View rootView = inflater.inflate(R.layout.getdata_fargment, container, false);
         
        int i = getArguments().getInt(ARG_PLANET_NUMBER);
    	 MainActivity.catagory= getResources().getStringArray(R.array.catagory)[i];
    	 
    	
    	// MainActivity.table = getResources().getStringArray(R.array.table_name)[i];
    	 
     String title= getResources().getStringArray(R.array.nav_drawer_items)[i];
     	
    	ArrayList array_list = mydb.getAllCotacts(MainActivity.catagory);

    	
    	ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.farment_listview,array_list); 
    	
    	
    	((ListView) rootView.findViewById(R.id.datalist)).setAdapter(adapter);
    	  
    	
    	 getActivity().setTitle(title +" Dhaka");
    	
        return rootView;
    }
	

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		
		
		int Value= position+1;
		super.onListItemClick(l, v, position, id);
		
		Bundle args1 = new Bundle();
        args1.putInt(ARG_PLANET_NUMBER, Value);
        
		Intent intent = new Intent(getActivity(),DialogActivity.class);
		
		
		intent.putExtras(args1);
		
		
		
		startActivity(intent); 
		

		
		
		
		
		
		//startActivity(new Intent(getActivity(), Division.class));
		

		
		
	}
    
	
	
}
