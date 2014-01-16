package com.iconnectmed;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
 
public class MainActivity extends Activity {
	
	EditText editEmail;
	EditText editPassword;
 
	// test commment - 2
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Parse.initialize(this, "byl4Gqpf4sjflIg46B13HLFTGwfa6iLhfx5JQhDa", "7gfiAhmKPKmteyzPw9zPA8GeASdlmJRA748kOyVr");
	
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
			    			    	
			    			    	
			    			    	
			    			    } else {
			    			      // Sign up didn't succeed. Look at the ParseException
			    			      // to figure out what went wrong
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
