package com.bgood.danny.hockeyliguevirtuelle;
import android.app.*;
import android.os.*;
import com.bgood.danny.hockeyliguevirtuelle.DataModel.*;
import android.widget.*;
import android.content.*;
import java.text.*;

public class PlayerDetail extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.playerdetail);
		
		Bundle bundle = getIntent().getExtras();
		TeamPlayer player = bundle.getParcelable("com.bgood.danny.hockeyliguevirtuelle");
		bindData(player);
	}
	
	private void bindData(TeamPlayer player) {
		TextView txname = (TextView) findViewById(R.id.name);
		TextView txcondition = (TextView) findViewById(R.id.condition);
		TextView txinfo = (TextView) findViewById(R.id.info);
		TextView txposition = (TextView) findViewById(R.id.position);
		TextView txoverall = (TextView) findViewById(R.id.overall);
		ListView attributes = (ListView) findViewById(R.id.lstAttrs);
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,);
		txname.setText(player.getName());

		if (player.getFarm()) {
			txname.setText(txname.getText() + " (farm)");
		}

		txcondition.setText(player.getHealth() + "%\n" + player.getInjiury());
		txinfo.setText(player.getInfo());
		txposition.setText(player.getPosition());
		txoverall.setText(player.getOverall());
		
 		double con = 0;
		if (player.getHealth().length() > 0) {
			con = Double.valueOf(player.getHealth());
		}

		if (con == 100.0) {
			txcondition.setBackgroundColor(0xFF99FFCC);
		} else if (con < 100.0 && player.getInjiury().length() == 0) {
			txcondition.setBackgroundColor(0xFFFFFF99);
		} else {
			txcondition.setBackgroundColor(0xFFFF3333);
		} 
	}
}
