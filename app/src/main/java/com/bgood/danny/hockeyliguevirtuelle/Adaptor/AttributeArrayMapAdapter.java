package com.bgood.danny.hockeyliguevirtuelle.Adaptor;

import android.widget.*;
import com.bgood.danny.hockeyliguevirtuelle.DataModel.*;
import android.content.*;
import android.view.LayoutInflater;
import java.util.*;
import android.*;
import android.view.*;
import com.bgood.danny.hockeyliguevirtuelle.R;
import android.util.*;

public class AttributeArrayMapAdapter extends BaseAdapter
{
	private final ArrayList data;

	public AttributeArrayMapAdapter(LinkedHashMap<String, String> map) {
		data = new ArrayList();
		data.addAll(map.entrySet());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final View resultView;
		
		if (convertView == null) {
			resultView = LayoutInflater.from(parent.getContext()).inflate(R.layout.attribute_list, parent, false);
		}
		else {
			resultView = convertView;
		}
		
		Map.Entry<String, String> item = getItem(position);
		
		((TextView) resultView.findViewById(R.id.name)).setText(item.getKey());
		((TextView) resultView.findViewById(R.id.value)).setText(item.getValue());
		
		return resultView;
	}
	
	@Override
	public Map.Entry<String, String> getItem(int position) {
		return (Map.Entry)data.get(position);
	}
	
	@Override
	public int getCount()
	{
		return data.size();
	}

	@Override
	public long getItemId(int p1)
	{
		// TODO: Implement this method
		return 0;
	}	
}
