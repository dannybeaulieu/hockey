package com.bgood.danny.hockeyliguevirtuelle;
import android.app.*;
import android.os.*;
import android.widget.*;
import com.bgood.danny.hockeyliguevirtuelle.DataModel.*;
import android.graphics.*;
import android.widget.TableRow.*;

public class ComparePlayers extends Activity
{
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.compare_players);
		Global global = ((Global)getApplicationContext());
		
		TeamPlayer lplayer = global.getLeftPlayer();	
		TeamPlayer rplayer = global.getRightPlayer();
		
		((TextView)findViewById(R.id.leftValue)).setText(lplayer.getName().replace(" ", "\n"));
		((TextView)findViewById(R.id.rightValue)).setText(rplayer.getName().replace(" ", "\n"));
		
		TableLayout tl = (TableLayout)findViewById(R.id.compareTable);
		int index = 0;
		
		for (ComparePlayerData data : ComparePlayerData.PopulateComparePlayerData(lplayer, rplayer)) {
			TableRow row = new TableRow(this);
			
		    row.setId(10);
			row.setBackgroundColor(index % 2 == 0 ? Color.WHITE : 0xFFB9D3EE);
			row.setLayoutParams(new LayoutParams(
			
										LayoutParams.FILL_PARENT,
										LayoutParams.WRAP_CONTENT));
										
			TextView attribut = new TextView(this);
			attribut.setId(20);
			LayoutParams params = new LayoutParams();
			params.weight = (float) 3.0;
			attribut.setLayoutParams(params);
			attribut.setText(data.getAttribut());
			attribut.setTextSize(16);
			
			TextView leftV = new TextView(this);
			leftV.setId(30);
			params = new LayoutParams();
			params.weight = (float) 3.0;
			leftV.setLayoutParams(params);
			leftV.setText(data.getLeftValue());
			leftV.setTextSize(16);
			
			TextView rightV = new TextView(this);
			rightV.setId(20);
			params = new LayoutParams();
			params.weight = (float) 3.0;
			rightV.setLayoutParams(params);
			rightV.setText(data.getRightValue());
			rightV.setTextSize(16);
			
			row.addView(attribut);
			row.addView(leftV);
			row.addView(rightV);
			
			index++;
			
			tl.addView(row);
		}
	}
}
