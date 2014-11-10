package com.bgood.danny.hockeyliguevirtuelle.DataModel;

public class TeamPlayer
{
	private String _name;
	private String _position;
	private String _overall;
	private String _health;
	
	public String getName() {
		return _name;
	}
	
	public String getPosition() {
		return _position;
	}
	
	public String getOverall() {
		return _overall;
	}
	
	public String getHealth() {
		return _health;
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
	
	public void setHealth(String value) {
		_health = value;
	}
	
	@Override
	public String toString()
	{
		return _name + " - " + _position + " - " + _health + "%" + "\nOv: " + getOverall();
	}
}
