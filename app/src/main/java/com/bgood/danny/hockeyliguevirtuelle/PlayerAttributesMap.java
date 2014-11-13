package com.bgood.danny.hockeyliguevirtuelle;
import android.util.*;
import com.bgood.danny.hockeyliguevirtuelle.DataModel.*;
import java.util.*;

public class PlayerAttributesMap
{
	private static LinkedHashMap<String, PlayerAttribute> playerAttrs = new LinkedHashMap<String, PlayerAttribute>();
	private static LinkedHashMap<String, PlayerAttribute> goalerAttrs = new LinkedHashMap<String, PlayerAttribute>();

	public static LinkedHashMap<String, PlayerAttribute> getGoalerAttrs()
	{
		goalerAttrs.put("SK", new PlayerAttribute("Skating", 43, 46));
		goalerAttrs.put("DU", new PlayerAttribute("Durability", 46, 49));
		goalerAttrs.put("EN", new PlayerAttribute("Endurance", 49, 52));
		goalerAttrs.put("SZ", new PlayerAttribute("Size", 52, 55));
		goalerAttrs.put("AG", new PlayerAttribute("Agility", 55, 58));
		goalerAttrs.put("RB", new PlayerAttribute("Rebound Control", 58, 61));
		goalerAttrs.put("SC", new PlayerAttribute("Style Control", 61, 64));
		goalerAttrs.put("HS", new PlayerAttribute("Hand Speed", 64, 67));
		goalerAttrs.put("RT", new PlayerAttribute("Reaction Time", 67, 70));
		goalerAttrs.put("PH", new PlayerAttribute("Puck Handling", 70, 73));
		goalerAttrs.put("PS", new PlayerAttribute("Penalyty Shot", 73, 76));
		goalerAttrs.put("EX", new PlayerAttribute("Experience", 76, 79));
		goalerAttrs.put("LD", new PlayerAttribute("Leadership", 79, 82));
		goalerAttrs.put("MO", new PlayerAttribute("Morale", 82, 85));
		goalerAttrs.put("TA", new PlayerAttribute("Trade", 88, 91));
		goalerAttrs.put("SP", new PlayerAttribute("Star Power", 91, 94));
		
		return goalerAttrs;
	}

	public static LinkedHashMap<String, PlayerAttribute> getPlayerAttrs()
	{
		playerAttrs.put("CK", new PlayerAttribute("Checking", 50, 53));
		playerAttrs.put("FG", new PlayerAttribute("Fighting", 53, 56));
		playerAttrs.put("DI", new PlayerAttribute("Discipline", 56, 59));
		playerAttrs.put("SK", new PlayerAttribute("Skating", 59, 62));
		playerAttrs.put("ST", new PlayerAttribute("Strength", 62, 65));
		playerAttrs.put("EN", new PlayerAttribute("Endurance", 65, 68));
		playerAttrs.put("DU", new PlayerAttribute("Durability", 68, 71));
		playerAttrs.put("PH", new PlayerAttribute("Puck Handling", 71, 74));
		playerAttrs.put("FO", new PlayerAttribute("Face Off", 74, 77));
		playerAttrs.put("PA", new PlayerAttribute("Passing", 77, 80));
		playerAttrs.put("SC", new PlayerAttribute("Scoring", 80, 83));
		playerAttrs.put("DF", new PlayerAttribute("Defense", 83, 86));
		playerAttrs.put("PS", new PlayerAttribute("Penality Shot", 86, 89));
		playerAttrs.put("EX", new PlayerAttribute("Experience", 89, 92));
		playerAttrs.put("LD", new PlayerAttribute("Leadership", 92, 95));
		playerAttrs.put("MO", new PlayerAttribute("Morale", 95, 98));
		playerAttrs.put("TA", new PlayerAttribute("Trade", 101, 104));
		playerAttrs.put("SP", new PlayerAttribute("Star Power", 104, 107));
		
		return playerAttrs;
	}}
