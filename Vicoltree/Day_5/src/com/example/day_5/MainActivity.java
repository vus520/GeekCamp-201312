package com.example.day_5;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ApplicationErrorReport.CrashInfo;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemLongClickListener;

public class MainActivity extends Activity implements OnItemLongClickListener {

	ListView myList;

	IceCreamInfo iceCreamInfo;

	ArrayList<IceCreamInfo> infolist;

	AlertDialog alertDialog;
	IceCreamAdapter adapter;

	private enum Select {
		MESSAGE, MODIFICATION, DELETE;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myList = (ListView) findViewById(R.id.myList);
		initIceCreamInfo();
		adapter = new IceCreamAdapter();
		myList.setAdapter(adapter);
		myList.setOnItemLongClickListener(this);
	}

	private void initIceCreamInfo() {
		String[] iceName = getResources().getStringArray(R.array.ice_names);
		String[] detailsInfo = getResources().getStringArray(R.array.details);
		int[] icePhoto = { R.drawable.icecream1, R.drawable.icecream2,
				R.drawable.icecream3, R.drawable.icecream4,
				R.drawable.icecream5, R.drawable.icecream6,
				R.drawable.icecream7, R.drawable.icecream8,
				R.drawable.icecream9, R.drawable.icecream10, };
		infolist = new ArrayList<IceCreamInfo>();
		for (int i = 0; i < icePhoto.length; i++) {
			infolist.add(new IceCreamInfo(iceName[i], icePhoto[i],
					detailsInfo[i]));
		}
	}

	private class IceCreamAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return infolist.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			View layout = View.inflate(MainActivity.this,
					R.layout.ice_item_layout, null);
			TextView iceNameTV = (TextView) layout.findViewById(R.id.iceNameTV);
			ImageView icePhoto = (ImageView) layout
					.findViewById(R.id.iceIconIV);
			IceCreamInfo creamInfo = infolist.get(arg0);
			iceNameTV.setText(creamInfo.getIceName());
			icePhoto.setImageResource(creamInfo.getIcePhotoid());
			return layout;
		}

	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
			final int arg2, long arg3) {
		AlertDialog.Builder builder = new Builder(MainActivity.this);
		builder.setItems(new CharSequence[] { "基本信息", "修改信息", "删除项目" },
				new OnClickListener() {
					Select select;

					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (select.values()[which]) {
						case MESSAGE:
							showIceCreamMessage(arg2);
							break;

						case MODIFICATION:
							showModificationMessage(arg2);
							break;
						case DELETE:
							deleteIceCreamItem(arg2);
							break;
						}

					}
				});
		alertDialog = builder.create();
		alertDialog.show();
		return true;
	}

	protected void showModificationMessage(final int arg2) {
		View layout = View.inflate(MainActivity.this,
				R.layout.details_info_layout, null);
		final IceCreamInfo creamInfo = infolist.get(arg2);
		final EditText editText = (EditText) layout
				.findViewById(R.id.detailsTV);
		ImageView imageView = (ImageView) layout.findViewById(R.id.detailsIcon);
		Button button = (Button) layout.findViewById(R.id.returnBtn);
		editText.setCursorVisible(true);
		editText.setText(creamInfo.getDetails());
		imageView.setImageResource(creamInfo.getIcePhotoid());
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String countStr = "";
				countStr = editText.getText().toString();
				IceCreamInfo info = new IceCreamInfo(creamInfo.getIceName(),
						creamInfo.getIcePhotoid(), countStr);
				infolist.set(arg2, info);
				adapter.notifyDataSetChanged();
				alertDialog.cancel();
			}
		});
		AlertDialog.Builder builder = new Builder(MainActivity.this);
		builder.setView(layout);
		alertDialog = builder.create();
		alertDialog.show();
	}

	protected void deleteIceCreamItem(final int arg2) {
		AlertDialog.Builder builder = new Builder(MainActivity.this);
		builder.setMessage("是否要删除此项目？");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				infolist.remove(arg2);
				adapter.notifyDataSetChanged();
			}
		});
		builder.setNegativeButton("取消", null);
		alertDialog = builder.create();
		alertDialog.show();
	}

	protected void showIceCreamMessage(int arg2) {
		View layout = View.inflate(MainActivity.this,
				R.layout.details_info_layout, null);
		EditText editText = (EditText) layout.findViewById(R.id.detailsTV);
		ImageView imageView = (ImageView) layout.findViewById(R.id.detailsIcon);
		Button button = (Button) layout.findViewById(R.id.returnBtn);
		IceCreamInfo creamInfo = infolist.get(arg2);
		editText.setText(creamInfo.getDetails());
		imageView.setImageResource(creamInfo.getIcePhotoid());
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				alertDialog.dismiss();
			}
		});
		AlertDialog.Builder builder = new Builder(MainActivity.this);
		builder.setView(layout);
		alertDialog = builder.create();
		alertDialog.show();
	}
}
