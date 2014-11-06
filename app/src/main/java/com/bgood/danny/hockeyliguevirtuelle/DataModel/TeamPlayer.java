package com.bgood.danny.hockeyliguevirtuelle.DataModel;

public class TeamPlayer
{
	private String _name;
	private String _position;
	private String _overall;
	
	public String getName() {
		return _name;
	}
	
	public String getPosition() {
		return _position;
	}
	
	public String getOverall() {
		return _overall;
	}
	
	public void setName(String value) {
		_name = value;
	}
	
	public void setPosition(String value) {
		_position = value;
	}
	
	public void setOverall(String value) {
		_overall = value;
	}
	
	@Override
	public String toString()
	{
		return _name + " - " + _position + "\nOv: " + getOverall();
	}
}
