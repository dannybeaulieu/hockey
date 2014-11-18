package com.bgood.danny.hockeyliguevirtuelle;
import android.app.*;
import android.os.*;
import com.bgood.danny.hockeyliguevirtuelle.DataModel.*;
import android.widget.*;
import com.bgood.danny.hockeyliguevirtuelle.Adaptor.*;
import android.view.View.*;
import android.view.*;
import android.content.*;

public class PlayerDetail extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.playerdetail);
		
		ImageButton menuPlayer = (ImageButton)findViewById(R.id.menuPlayerBack);

		menuPlayer.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v)
				{
					Intent intent = new Intent(v.getContext(), Players.class);
					startActivity(intent);
				}
			});
		
		Bundle bundle = getIntent().getExtras();
		TeamPlayer player = bundle.getParcelable("com.bgood.danny.hockeyliguevirtuelle");
		bindData(player);
	}
	
	private void bindData(TeamPlayer player) {
		TextView viewName = (TextView) findViewById(R.id.name);
		TextView viewCondition = (TextView) findViewById(R.id.condition);
		TextView viewInfo = (TextView) findViewById(R.id.info);
		TextView viewPosition = (TextView) findViewById(R.id.position);
		TextView viewOverall = (TextView) findViewById(R.id.overall);
		ListView attributes = (ListView) findViewById(R.id.lstAttrs);
		
		AttributeArrayMapAdapter dataAdapter = new AttributeArrayMapAdapter(player.getAttributes());
		attributes.setAdapter(dataAdapter);

        viewName.setText(player.getName());

		if (player.getFarm()) {
            viewName.setText(viewName.getText() + " (farm)");
		}

        viewCondition.setText(player.getHealth() + "%\n" + player.getInjury());
        viewInfo.setText(player.getInfo());
        viewPosition.setText(player.getPosition());
        viewOverall.setText(player.getOverall());
		
 		double con = 0;
		if (player.getHealth().length() > 0) {
			con = Double.valueOf(player.getHealth());
		}

		if (con == 100.0) {
            viewCondition.setBackgroundColor(0xFF99FFCC);
		} else if (con < 100.0 && player.getInjury().length() == 0) {
            viewCondition.setBackgroundColor(0xFFFDFD96);
		} else {
            viewCondition.setBackgroundColor(0xFFC23B22);
		} 
	}
}
