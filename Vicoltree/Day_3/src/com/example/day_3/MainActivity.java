package com.example.day_3;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private MyBroadCastReceiver mbcr = new MyBroadCastReceiver();
	private OnClickListener sendBroadcastreceiver = new OnClickListener() {

		@Override
		public void onClick(View arg0) {

			switch (arg0.getId()) {
			case R.id.regBtn:
				registerReceiver(mbcr, new IntentFilter(
						"com.example.day_3.intent.action.MyBroadCastReceiver"));
				break;
			case R.id.cancelBtn:
				unregisterReceiver(mbcr);
				break;
			case R.id.sendBtn:
				sendBroadcast(new Intent(
						"com.example.day_3.intent.action.MyBroadCastReceiver"));
				break;
			}

		}
	};
	private TextView showTime;
	private Timer timer = new Timer();
	private TimerTask task = null;
	private Button signup, cancel, sendreceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		showTime = (TextView) findViewById(R.id.showTime);
		signup = (Button) findViewById(R.id.regBtn);
		cancel = (Button) findViewById(R.id.cancelBtn);
		sendreceiver = (Button) findViewById(R.id.sendBtn);
		signup.setOnClickListener(sendBroadcastreceiver);
		cancel.setOnClickListener(sendBroadcastreceiver);
		sendreceiver.setOnClickListener(sendBroadcastreceiver);
	}

	@Override
	protected void onResume() {
		task = new TimerTask() {

			@Override
			public void run() {

				handler.sendEmptyMessage(0);
			}
		};
		timer.schedule(task, 100, 1000);

		super.onResume();
	}

	private void showTime() {

		Calendar calendar = Calendar.getInstance();
		String str = calendar.get(Calendar.HOUR) + ":"
				+ calendar.get(Calendar.MINUTE) + ":"
				+ calendar.get(Calendar.SECOND);

		showTime.setText(str);
	}

	private Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			showTime();
		};

	};

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
