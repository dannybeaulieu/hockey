package com.bgood.danny.hockeyliguevirtuelle.DataModel;
import java.util.*;

public class team
{
	private String _name = "";
	private String _key = "";
	private String _farmName = "";
	
	public team(String key, String name) {
		_key = key;
		_name = name;
	}
	
	public void setName(String value) {
		_name = value;
	}
	
	public String getName() {
		return _name;
	}
	
	public void setKey(String value) {
		_key = value;
	}
	
	public String getKey() {
		return _key;
	}
	
	public void setFarmName(String value) {
		_farmName = value;
	}

	public String getFarmName() {
		return _farmName;
	}

	@Override
	public String toString()
	{
		return _name;
	}
}
