package com.iconnectmed;

import com.parse.LogInCallback;
import com.parse.ParseException;
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
import android.widget.Toast;

public class LoginActivity extends Activity {


	 
	EditText editEmail;
	EditText editPassword;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		// Check if new user
		ParseUser user = ParseUser.getCurrentUser();
		
		if(user!=null){
	    	
	    	Intent intent = new Intent(LoginActivity.this, UserListActivity.class);
	    	startActivity(intent);	
		}
		
		Button buttonLogin = (Button) findViewById(R.id.buttonLogin);
		
		editEmail = (EditText)findViewById(R.id.editTextEmail);
		editPassword = (EditText)findViewById(R.id.editTextPassword);
		
		buttonLogin.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	
		    	
		    	ParseUser.logInInBackground(editEmail.getText().toString(), editPassword.getText().toString(), new LogInCallback() {
		    		  public void done(ParseUser user, ParseException e) {
		    		    if (user != null) {
		    		      // Hooray! The user is logged in.
		    		    	Intent intent = new Intent(LoginActivity.this, UserListActivity.class);
		    		    	startActivity(intent);
		    		    	
		    		    } else {
		    		      // Signup failed. Look at the ParseException to see what happened.
		    		    	Log.d("LoginActivity", "Sign in failed");
		    		    }
		    		  }
		    		});

		    	

		    }
		});
		
		Button buttonSignup = (Button) findViewById(R.id.buttonSignup);
		
		buttonSignup.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	
		    	Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
		    	startActivity(intent);

		    }
		});
		
		
		
		
		
	
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
