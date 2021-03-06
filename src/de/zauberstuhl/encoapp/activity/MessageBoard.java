package de.zauberstuhl.encoapp.activity;

/**
 * Copyright (C) 2013 Lukas Matt <lukas@zauberstuhl.de>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.LinkedHashMap;

import de.zauberstuhl.encoapp.R;
import de.zauberstuhl.encoapp.ThreadHelper;
import de.zauberstuhl.encoapp.adapter.MessageAdapter;
import de.zauberstuhl.encoapp.task.SendMessage;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MessageBoard extends Activity {
	
	private static ThreadHelper th = new ThreadHelper();
	private String TAG = getClass().getName();
	
    public Button sendButton;
	public EditText textField;
	public static ListView msgBoard;
	public static LinkedHashMap<String, String> listItems = new LinkedHashMap<String, String>();
    public static MessageAdapter msgAdapter;
	
    @Override
    public synchronized void onResume() {
    	super.onResume();
    	if (th.D) Log.e(TAG, "++ onResume ++");
    	setTitle(th.getActiveChatUser());
    	ThreadHelper.activityResumed();
    	th.updateDiscussion(this);
    }
    
    @Override
    public synchronized void onPause() {
        super.onPause();
        if (th.D) Log.e(TAG, "++ onPause ++");
        ThreadHelper.activityPaused();
    }
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (th.D) Log.e(TAG, "++ onCreate ++");
        setContentView(R.layout.message_board);
        
        // chat window plus textfield and send-button
        textField = (EditText) findViewById(R.id.msgTextField);
        sendButton = (Button) findViewById(R.id.sendButton);
        
        msgBoard = (ListView) findViewById(R.id.msgBoard);
        msgAdapter = new MessageAdapter(this, listItems);
        msgBoard.setAdapter(msgAdapter);
        msgBoard.setDivider(null);
        
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
        	if (extras.containsKey("activeChatUser"))
        		th.setActiveChatUser(extras.getString("activeChatUser"));
        }
	}
	
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (keyCode == KeyEvent.KEYCODE_BACK) {
            // reset the active user
            if (th.getActiveChatUser() != null) {
            	th.setActiveChatUser(null);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    
    public void send(View v) {
        final String msg = textField.getText().toString();
        textField.setText("");
        if (msg.length() > 0) {
        	new SendMessage(this).execute(msg);
        } else th.sendNotification(this, "Need message to send!");
    }

}
