package com.iconnectmed;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ProfileActivity extends Activity {

	EditText editFirstName;
	EditText editLastName;
	
	Spinner spinnerDepartments;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		editFirstName = (EditText)findViewById(R.id.editTextFirstName);
		editLastName = (EditText)findViewById(R.id.EditTextLastName);
		spinnerDepartments = (Spinner)findViewById(R.id.spinnerDepartments);
		
		Button buttonNext = (Button) findViewById(R.id.buttonNext);
		
		buttonNext.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	
		    	ParseUser user = ParseUser.getCurrentUser();
		    	
		    	
		    	// Make a new profile
		    	ParseObject profile = new ParseObject("UserProfile");
		    	profile.put("firstName", editFirstName.getText().toString());
		    	profile.put("lastName", editLastName.getText().toString());
		    	profile.put("department", spinnerDepartments.getSelectedItem().toString());
		    	profile.put("user", user);
		    	profile.saveInBackground();
		    	
		    	Log.d("ProfileActivity", "Profile created");
		    	
		    	Intent intent = new Intent(ProfileActivity.this, UserListActivity.class);
		    	startActivity(intent);
		    	
		    }
		});
	

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

}
