package com.example.day_4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.View.OnCreateContextMenuListener;

public class AutoBoot extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		Log.i("boot", "Boot Completed");
	
	}

}
