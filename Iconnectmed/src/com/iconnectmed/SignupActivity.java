package com.iconnectmed;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.parse.ParseFacebookUtils.Permissions.User;
import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;

import com.pubnub.api.PubnubError;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
 
public class SignupActivity extends Activity {
	
	EditText editEmail;
	EditText editPassword;
 
	// test commment - 2
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		
	
		Button buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
		
		editEmail = (EditText)findViewById(R.id.editTextEmail);
		editPassword = (EditText)findViewById(R.id.editTextPassword);

		
		buttonSubmit.setOnClickListener(new View.OnClickListener() {
			    @Override
			    public void onClick(View v) {
			    	
			    	ParseUser user = new ParseUser();
			    	user.setUsername(editEmail.getText().toString());
			    	user.setEmail(editEmail.getText().toString());
			    	user.setPassword(editPassword.getText().toString());
			    	 
			    	user.signUpInBackground(new SignUpCallback() {
			    		  public void done(ParseException e) {
			    			    if (e == null) {
			    			      // Hooray! Let them use the app now.
			    			    	
			    			    	Toast.makeText(getApplicationContext(), "User created!", 
			    			    			   Toast.LENGTH_LONG).show();
			    			    	
			    			    	Intent intent = new Intent(SignupActivity.this, ProfileActivity.class);
			    			    	startActivity(intent);
			    			    	
			    			    } else {
			    			      // Sign up didn't succeed. Look at the ParseException
			    			      // to figure out what went wrong
			    			    	
			    			    	Toast.makeText(getApplicationContext(), "error", 
			    			    			   Toast.LENGTH_LONG).show();
			    			    }
			    			  }

			    			});
			    	
			    }
			});
		

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
