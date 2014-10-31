package com.bgood.danny.hockeyliguevirtuelle.DataModel;

public class team
{
	private String _name = "";
	private String _key = "";
	
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

	@Override
	public String toString()
	{
		return _name;
	}
}
