package com.bgood.danny.hockeyliguevirtuelle;
import android.app.*;
import android.os.*;
import android.widget.*;
import com.bgood.danny.hockeyliguevirtuelle.DataModel.*;
import android.graphics.*;
import android.widget.TableRow.*;
import android.view.*;
import android.content.*;

public class ComparePlayers extends Activity
{
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
		setContentView(R.layout.compare_players);
		Global global = ((Global)getApplicationContext());
		
		ImageButton menuPlayer = (ImageButton)findViewById(R.id.menuPlayerBack);

		menuPlayer.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v)
				{
					Intent intent = new Intent(v.getContext(), Players.class);
					startActivity(intent);
				}
			});
		
		TeamPlayer leftPlayer = global.getLeftPlayer();
		TeamPlayer rightPlayer = global.getRightPlayer();
		
		((TextView)findViewById(R.id.leftValue)).setText(leftPlayer.getName().replace(" ", "\n"));
		((TextView)findViewById(R.id.rightValue)).setText(rightPlayer.getName().replace(" ", "\n"));
		
		TableLayout tl = (TableLayout)findViewById(R.id.compareTable);
		int index = 0;
		
		for (ComparePlayerData data : ComparePlayerData.PopulateComparePlayerData(leftPlayer, rightPlayer)) {
			TableRow row = new TableRow(this);
			
		    row.setId(10);
			row.setBackgroundColor(index % 2 == 0 ? Color.WHITE : 0xFFB9D3EE);
			row.setLayoutParams(new LayoutParams(
			
										LayoutParams.MATCH_PARENT,
										LayoutParams.WRAP_CONTENT));
										
			TextView attribute = new TextView(this);
			attribute.setId(20);
			LayoutParams params = new LayoutParams();
			params.weight = (float) 3.0;
			params.setMargins(5,0,0,0);
			attribute.setLayoutParams(params);
			attribute.setText(data.getAttribute());
			attribute.setTextSize(16);
			
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
			
			row.addView(attribute);
			row.addView(leftV);
			row.addView(rightV);
			
			index++;
			
			tl.addView(row);
		}
	}
}
