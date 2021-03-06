package com.bgood.danny.hockeyliguevirtuelle.Adaptor;
 
import com.bgood.danny.hockeyliguevirtuelle.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bgood.danny.hockeyliguevirtuelle.DataModel.*;
import java.util.*;
import android.graphics.*;
import android.widget.*;
 
public class PlayerArrayAdapter extends ArrayAdapter<TeamPlayer> implements Filterable
{
    private final ArrayList<TeamPlayer> originalPlayers;
	private ArrayList<TeamPlayer> filteredPlayers;
	private PlayerFilter filter;
    private final LayoutInflater inflater;

	public PlayerArrayAdapter(Context context, ArrayList<TeamPlayer> players) {
		super(context, R.layout.list_player, players);
        inflater = LayoutInflater.from(context);
		originalPlayers = new ArrayList<TeamPlayer>();
		originalPlayers.addAll(players);
		filteredPlayers = new ArrayList<TeamPlayer>();
		filteredPlayers.addAll(players);
	}
	
	@Override
	public int getCount()
	{
		return filteredPlayers.size();
	}

	@Override
	public TeamPlayer getItem(int position)
	{
		return filteredPlayers.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public Filter getFilter()
	{
		if (filter == null) {
			filter = new PlayerFilter();
		}
		return filter;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;

        if (rowView == null) {
            rowView = inflater.inflate(R.layout.list_player, parent, false);
        }

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
	
	private class PlayerFilter extends Filter
	{
		@Override
		protected Filter.FilterResults performFiltering(CharSequence constraint)
		{
			FilterResults results = new FilterResults();
			constraint = constraint.toString().toLowerCase();
			
			if (constraint.toString().length() > 0 && !constraint.toString().toLowerCase().equals("all")) {
				ArrayList<TeamPlayer> players = new ArrayList<TeamPlayer>();

                for (TeamPlayer p : originalPlayers) {
                    if (p.getPosition().toLowerCase().contains(constraint.toString())) {
                        players.add(p);
                    }
                }
				results.values = players;
				
				results.count = players.size();
			}
			else {
				synchronized(this)
				{
					results.values = originalPlayers;
					results.count = originalPlayers.size();
				}
			}
			return results;
		}

		@Override
		protected void publishResults(CharSequence constraint, Filter.FilterResults results)
		{
            //noinspection unchecked
            filteredPlayers = (ArrayList<TeamPlayer>)results.values;
			notifyDataSetChanged();
			clear();
            for (TeamPlayer filteredPlayer : filteredPlayers) add(filteredPlayer);
			notifyDataSetInvalidated();
		}
	}
}
