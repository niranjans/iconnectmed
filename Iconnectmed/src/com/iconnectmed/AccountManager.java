package com.iconnectmed;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class AccountManager {
	

	
	public static void signUp(String username, String email, String password, Context context){
		
    	ParseUser user = new ParseUser();
    	user.setUsername(username);
    	user.setEmail(email);
    	user.setPassword(password);
    	
//    	user.signUpInBackground(new SignUpCallback() {
//    		  public void done(ParseException e) {
//    			    if (e == null) {
//    			      // Hooray! Let them use the app now.
//    			    	
//    			    	Toast.makeText(context, "User created!", 
//    			    			   Toast.LENGTH_LONG).show();
//    			    	
//    			    	Intent intent = new Intent(SignupActivity.this, UserList.class);
//    			    	startActivity(intent);
//    			    	
//    			    } else {
//    			      // Sign up didn't succeed. Look at the ParseException
//    			      // to figure out what went wrong
//    			    	
//    			    	Toast.makeText(getApplicationContext(), "error", 
//    			    			   Toast.LENGTH_LONG).show();
//    			    }
//    			  }
//
//    			});
//    	
//    }
//});
		
		
	}

}
