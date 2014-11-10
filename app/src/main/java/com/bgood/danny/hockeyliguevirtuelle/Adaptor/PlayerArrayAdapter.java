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
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		
		TeamPlayer player = getItem(position);
		textView.setText(player.toString());
		
 		double con = 0;
		if (player.getHealth().length() > 0) {
			con = Double.valueOf(player.getHealth());
		}
		
		if (con == 100.0) {
			imageView.setImageResource(R.drawable.heart);
		} else if (con >= 90.0) {
			imageView.setImageResource(R.drawable.warning);
		} else {
			imageView.setImageResource(R.drawable.redcross);
		} 
 
		return rowView;
	}
}
