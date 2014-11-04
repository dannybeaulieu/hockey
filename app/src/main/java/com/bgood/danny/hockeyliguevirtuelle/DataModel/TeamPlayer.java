package com.bgood.danny.hockeyliguevirtuelle.DataModel;

public class TeamPlayer
{
	private String _name;
	
	public String getName() {
		return _name;
	}
	
	public void setName(String value) {
		_name = value;
	}
	
	@Override
	public String toString()
	{
		return _name;
	}
}
