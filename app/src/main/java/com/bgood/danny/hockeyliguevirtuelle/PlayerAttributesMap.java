package com.bgood.danny.hockeyliguevirtuelle;
import android.util.*;
import com.bgood.danny.hockeyliguevirtuelle.DataModel.*;

public class PlayerAttributesMap
{
	private static ArrayMap<String, PlayerAttribute> playerAttrs = new ArrayMap<String, PlayerAttribute>();
	private static ArrayMap<String, PlayerAttribute> goalerAttrs = new ArrayMap<String, PlayerAttribute>();

	public static ArrayMap<String, PlayerAttribute> getGoalerAttrs()
	{
		return goalerAttrs;
	}

	public static ArrayMap<String, PlayerAttribute> getPlayerAttrs()
	{
		playerAttrs.put("CK", new PlayerAttribute("Checking", 50, 53));
		return playerAttrs;
	}}
