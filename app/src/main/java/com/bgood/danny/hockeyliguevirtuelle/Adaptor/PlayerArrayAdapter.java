package com.bgood.danny.hockeyliguevirtuelle.Adaptor;
 
import com.bgood.danny.hockeyliguevirtuelle.R;
 
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.bgood.danny.hockeyliguevirtuelle.DataModel.*;
import java.util.*;
import android.graphics.*;
 
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
		TextView viewName = (TextView) rowView.findViewById(R.id.name);
		TextView viewCondition = (TextView) rowView.findViewById(R.id.condition);
		TextView viewInfo = (TextView) rowView.findViewById(R.id.info);
		TextView viewPosition = (TextView) rowView.findViewById(R.id.position);
		TextView viewOverall = (TextView) rowView.findViewById(R.id.overall);
		
		TeamPlayer player = getItem(position);
		
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
		
		if (position % 2 == 0) {
			rowView.setBackgroundColor(Color.WHITE);
		}
		else
		{
			rowView.setBackgroundColor(0xFFB9D3EE);
		}
 
		return rowView;
	}
}
