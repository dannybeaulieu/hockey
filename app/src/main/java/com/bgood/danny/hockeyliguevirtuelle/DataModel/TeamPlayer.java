package com.bgood.danny.hockeyliguevirtuelle.DataModel;
import android.os.*;
import java.util.*;

public class TeamPlayer implements Parcelable
{
	public TeamPlayer() {
		_attributes = new LinkedHashMap<String, String>();
	}
	
	private TeamPlayer(Parcel in) {
		String[] data = new String[9];
		
		in.readStringArray(data);
		_attributes = (LinkedHashMap<String, String>)(in.readSerializable());
		_name = data[0];
		_position = data[1];
		_overall = data[2];
		_health = data[3];
		_injury = data[4];
		_age = data[5];
		_contract = data[6];
		_salary = data[7];
		_farm = Boolean.parseBoolean(data[8]);
	}
	
	public static final Parcelable.Creator<TeamPlayer> CREATOR = new Parcelable.Creator<TeamPlayer>() {
		public TeamPlayer createFromParcel(Parcel in) {
			return new TeamPlayer(in);
		}

		public TeamPlayer[] newArray(int size) {
			return new TeamPlayer[size];
		}
	};

	private String _name;
	private String _position;
	private String _overall;
	private String _health;
	private String _injury;
	private String _age;
	private String _contract;
	private String _salary;
	private Boolean _farm;
	private final LinkedHashMap<String, String> _attributes;
	
	public LinkedHashMap<String, String> getAttributes() {
		return _attributes;
	}
	
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
	
	public String getInjury() {
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
	
	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel destination, int flags)
	{
		destination.writeStringArray(new String[]{
                _name,
                _position,
                _overall,
                _health,
                _injury,
                _age,
                _contract,
                _salary,
                _farm.toString()});
		destination.writeSerializable(_attributes);
	}
}
