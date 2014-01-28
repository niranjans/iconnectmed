package com.iconnectmed;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubError;
import com.pubnub.api.PubnubException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChatActivity extends Activity {
	
	TextView textViewChat;
	EditText editChat;
	Callback callback;
	
	Pubnub pubnub;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		
		Button buttonSend = (Button) findViewById(R.id.buttonSend);
		
		editChat = (EditText)findViewById(R.id.editTextChat);

		textViewChat = (TextView) findViewById(R.id.textViewChat);
		
		// 
		ParseUser user = ParseUser.getCurrentUser();
		
		
		
		pubnub = new Pubnub("pub-c-c2edd7e9-3cbe-4c38-a5f4-1f5ba16327b9", "sub-c-577af36a-7f07-11e3-89e2-02ee2ddab7fe");
		
		try {
			 //pubnub.subscribe(user.getEmail(), new Callback() {
				  pubnub.subscribe("testsk@test.com", new Callback() {

			      @Override
			      public void connectCallback(String channel, Object message) {
			          Log.d("PUBNUB","SUBSCRIBE : CONNECT on channel:" + channel
			                     + " : " + message.getClass() + " : "
			                     + message.toString());
			      }

			      @Override
			      public void disconnectCallback(String channel, Object message) {
			          Log.d("PUBNUB","SUBSCRIBE : DISCONNECT on channel:" + channel
			                     + " : " + message.getClass() + " : "
			                     + message.toString());
			      }

			      public void reconnectCallback(String channel, Object message) {
			          Log.d("PUBNUB","SUBSCRIBE : RECONNECT on channel:" + channel
			                     + " : " + message.getClass() + " : "
			                     + message.toString());
			      }

			      @Override
			      public void successCallback(String channel, Object message) {
			          Log.d("PUBNUB","SUBSCRIBE : " + channel + " : "
			                     + message.getClass() + " : " + message.toString());
			          
			          final Object msg = message;
			          
			          
			          
			         // textViewChat.setText(message.toString());
  			    	
			          ChatActivity.this.runOnUiThread(new Runnable() {
		                    public void run() {
		                    	
		                    	textViewChat.setText(msg.toString());
		                    	
		      			    	Toast.makeText(getApplicationContext(), msg.getClass() + " : " + msg.toString(), 
		   			    			   Toast.LENGTH_LONG).show();	
		                        Log.i("Received msg : ", msg.toString());
		                    }
		                });
			          

			      }

			      @Override
			      public void errorCallback(String channel, PubnubError error) {
			          Log.d("PUBNUB","SUBSCRIBE : ERROR on channel " + channel
			                     + " : " + error.toString());
			      }
			    }
			  );
			} catch (PubnubException e) {
			  Log.d("PUBNUB",e.toString());
			}
		
		// Callback to publish 
		callback = new Callback() {
			  public void successCallback(String channel, Object response) {
			    Log.d("PUBNUB",response.toString());
			  }
			  public void errorCallback(String channel, PubnubError error) {
				Log.d("PUBNUB",error.toString());
			  }
			};
		

		
		buttonSend.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	
		    	pubnub.publish(getIntent().getStringExtra("chatUserEmail"), 
		    			editChat.getText().toString() , callback);
		    	

		    	
		    }
		});
	
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chat, menu);
		return true;
	}

}
