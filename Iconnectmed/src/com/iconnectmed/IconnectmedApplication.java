package com.iconnectmed;

import com.parse.Parse;

import android.app.Application;

public class IconnectmedApplication extends Application {
	
	private static final String PARSE_APPLICATION_ID = "byl4Gqpf4sjflIg46B13HLFTGwfa6iLhfx5JQhDa";
    private static final String PARSE_CLIENT_KEY = "7gfiAhmKPKmteyzPw9zPA8GeASdlmJRA748kOyVr";
    
	@Override
	public void onCreate() {
		super.onCreate();
		
		// initialize Parse 
		Parse.initialize(this, PARSE_APPLICATION_ID, PARSE_CLIENT_KEY);

		
	}
 

}
