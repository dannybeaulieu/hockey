package com.bgood.danny.hockeyliguevirtuelle.Adaptor;
 
import com.bgood.danny.hockeyliguevirtuelle.R;
 
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bgood.danny.hockeyliguevirtuelle.DataModel.*;
import java.util.*;
 
public class PlayerArrayAdapter extends ArrayAdapter<TeamPlayer> {
	private final Context context;
 
	public PlayerArrayAdapter(Context context, ArrayList<TeamPlayer> players) {
		super(context, R.layout.list_player, players);
		this.context = context;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.list_player, parent, false);
		TextView txname = (TextView) rowView.findViewById(R.id.name);
		TextView txcondition = (TextView) rowView.findViewById(R.id.condition);
		TextView txinfo = (TextView) rowView.findViewById(R.id.info);
		TextView txposition = (TextView) rowView.findViewById(R.id.position);
		TextView txoverall = (TextView) rowView.findViewById(R.id.overall);
		
		TeamPlayer player = getItem(position);
		
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
 
		return rowView;
	}
}
