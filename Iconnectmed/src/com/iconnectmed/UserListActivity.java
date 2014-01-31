package com.iconnectmed;

import java.util.ArrayList;
import java.util.List;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubException;
import com.pubnub.api.PubnubError;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class UserListActivity extends Activity {
	
	// Declare Variables
    ListView listview;
    List<ParseUser> ob;
    ProgressDialog mProgressDialog;
    ListViewAdapter adapter;
    private List<UserProfile> userProfileList = null;
    
    ParseUser currentUser;
 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_list);
				
		currentUser = ParseUser.getCurrentUser();
		
		// ---------------------------------------------------------------------- 
		// Check if service is running.
		
		if(!MessageService.isMessageServiceRunning()){
			
			// Start the service
	        // registerReceiver(messageReceiver, messageFilter);
			
			Log.d("UserListActivity", "Starting message service");
			
	        Intent messageservice = new Intent(this, MessageService.class);
	        messageservice.putExtra("channel", currentUser.getUsername());
	        startService(messageservice);
			
		}
		
		// ----------------------------------------------------------------------
		
		 new RemoteDataTask().execute();
		
		
	}
	
	   // RemoteDataTask AsyncTask
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(UserListActivity.this);
            // Set progressdialog title
            mProgressDialog.setTitle("User List");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }
 
        @Override
        protected Void doInBackground(Void... params) {
            // Create the array
        	userProfileList = new ArrayList<UserProfile>();
            try {
            	
            	
            	
                // Locate the class table named "Country" in Parse.com
               ParseQuery<ParseUser> query = ParseUser.getQuery();
               
               Log.d("UserListActivity", "In asynctask.. username - " + currentUser.getUsername());
               
               query.whereNotEqualTo("username", currentUser.getUsername());
               
                // by ascending LastName
                query.orderByAscending("lastName");
                ob = query.find();
                for (ParseObject userProfile : ob) {
 
                    UserProfile map = new UserProfile();
                    map.setFirstName((String) userProfile.get("firstName"));
                    map.setLastName((String) userProfile.get("lastName"));
                    map.setDepartment((String) userProfile.get("department"));
                    map.setUsername((String) userProfile.get("username"));
                    
                    userProfileList.add(map);

                }
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }
 
        @Override
        protected void onPostExecute(Void result) {
            // Locate the listview in listview_main.xml
            listview = (ListView) findViewById(R.id.listViewUser);
            // Pass the results into ListViewAdapter.java
            adapter = new ListViewAdapter(UserListActivity.this,
            		userProfileList);
            // Binds the Adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss(); 
        }
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		menu.add("Logout");
		menu.add("Chat");

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_list, menu);
		return true;
	}
	
	 @Override
     public boolean onOptionsItemSelected(MenuItem item)
     {
         switch(item.getItemId())
         {
             case 0:
            	 
            	// Logout the user
          	 	ParseUser.logOut();
          	 	
          	 	Intent intent2 = new Intent(UserListActivity.this, ChatActivity.class);
          	 	
          	 	// For testing. fix later
          	 	intent2.putExtra("chatUserEmail", "nir7@gmail.com");
          	 	
  		    	startActivity(intent2);
          	 	

            	 	
                   return true;
             case 1:
            	 
   		    	
          		// Logout the user
         	 	ParseUser.logOut();
         	 	
         	 	Intent intent = new Intent(UserListActivity.this, LoginActivity.class);
 		    	startActivity(intent);
          		
                return true;
             default:
                   return super.onOptionsItemSelected(item);
         }
     }

}
