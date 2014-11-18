package com.bgood.danny.hockeyliguevirtuelle;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.View.*;
import android.view.*;
import android.content.*;

public class Main extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		ImageButton menuPlayer = (ImageButton)findViewById(R.id.menuPlayer);
		
		menuPlayer.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v)
				{
					Intent intent = new Intent(v.getContext(), Players.class);
					startActivity(intent);
				}
		});
	}
}
