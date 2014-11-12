package com.bgood.danny.hockeyliguevirtuelle.DataModel;

public class TeamPlayer
{
	private String _name;
	private String _position;
	private String _overall;
	private String _health;
	private String _injury;
	private String _age;
	private String _contract;
	private String _salary;
	private Boolean _farm;
	
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
	
	public String getInjiury() {
		return _injury;
	}
	
	public String getAge() {
		return _age;
	}
	
	public String getContract() {
		return _contract;
	}
	
	public String getSalary() {
		return _salary;
	}
	
	public Boolean getFarm() {
		return _farm;
	}
	
	public String getInfo() {
		return _age + " ans | " + _salary + "$ | " + _contract + " an(s)";
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
	
	public void setInjury(String value) {
		_injury = value;
	}
	
	public void setAge(String value) {
		_age = value;
	}
	
	public void setContract(String value) {
		_contract = value;
	}
	
	public void setSalary(String value) {
		_salary = value;
	}
	
	public void setFarm(Boolean value) {
		_farm = value;
	}
	
	@Override
	public String toString()
	{
		return _name + " - " + _position + " - " + _health + "%" + " - " + _injury + "\nOv: " + getOverall();
	}
}
