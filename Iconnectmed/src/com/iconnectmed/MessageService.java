package com.iconnectmed;

import java.util.HashMap;
import java.util.Hashtable;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.pubnub.api.Pubnub;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.AsyncTask.Status;
import android.util.Log;

public class MessageService extends Service {
    Pubnub pubnub;
    MessageHandler mMessageHandler = new MessageHandler();
    MessageReceiver mMessageReceiver = new MessageReceiver();
    MessageListener mMessageListener = new MessageListener();
    
    static boolean flagMessageServiceRunning = false;
    
    public static boolean isMessageServiceRunning(){
    	return flagMessageServiceRunning;
    }
    
    private void broadcastMessage(JSONObject message)//this method sends broadcast messages
    {
        Intent intent = new Intent("com.iconnectmed.MESSAGE");
        intent.putExtra("message", message.toString());
        sendBroadcast(intent);
    }
    
    class MessageHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            try {
                String m = msg.getData().getString("message");
                
                JSONObject message = (JSONObject) new JSONTokener(m).nextValue();
                
                Log.d("MessageService", message.toString());
                
                broadcastMessage(message);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    };
    
    // Callback Interface when a Message is Received
    class MessageReceiver {
    	public boolean subscribeCallback(String channel, Object message) {
            try {
            	Log.d("MessageService", "Recieved something!");
                Message m = Message.obtain();
                Bundle b = new Bundle();
                b.putString("message", message.toString());
                m.setData(b);
                mMessageHandler.sendMessage(m);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }

		
		public void errorCallback(String channel, Object message) {
			 Log.e("ErrorCallback","Channel:" + channel + "-" + message.toString());
		}

		
		public void connectCallback(String channel) {
			 Log.e("ConnectCallback","Connected to channel :" + channel);
		}

		
		public void reconnectCallback(String channel) {
			 Log.e("ReconnectCallback","Reconnected to channel :" + channel);
		}

		
		public void disconnectCallback(String channel) {
			 Log.e("DisconnectCallback","Disconnected to channel :" + channel);
		}
    }
    
    class MessageListener extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            {
                try {
                	
                	
                	Hashtable<String, Object> args = new Hashtable<String, Object>(2); // Changed from HashMap - check later
                	
                    args.put("channel", params[0]);
                    args.put("callback", mMessageReceiver);
                    pubnub.subscribe(args);
                    
                    Log.d("MessageService", "Subscribing to channel - " + params[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return Boolean.TRUE;
        }
    }
    
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        flagMessageServiceRunning = true;
        
		Log.d("MessageService", "Message service started");

        
        pubnub = new Pubnub("pub-c-c2edd7e9-3cbe-4c38-a5f4-1f5ba16327b9", 
        		"sub-c-577af36a-7f07-11e3-89e2-02ee2ddab7fe");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        
        flagMessageServiceRunning = false;
    }
    
    @Override
    public void onStart(Intent intent, int startid) {
        super.onStart(intent, startid);
        if(mMessageListener.getStatus() != Status.RUNNING){
                mMessageListener.execute(intent.getStringExtra("channel"));
        }
            
        
    }
}
