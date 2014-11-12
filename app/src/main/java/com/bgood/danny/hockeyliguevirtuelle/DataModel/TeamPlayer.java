package com.bgood.danny.hockeyliguevirtuelle.DataModel;
import android.os.*;

public class TeamPlayer implements Parcelable
{

	public void setSp(String sp)
	{
		_sp = sp;
	}

	public String getSp()
	{
		return _sp;
	}

	public void setTa(String ta)
	{
		_ta = ta;
	}

	public String getTa()
	{
		return _ta;
	}

	public void setMo(String mo)
	{
		_mo = mo;
	}

	public String getMo()
	{
		return _mo;
	}

	public void setLd(String ld)
	{
		_ld = ld;
	}

	public String getLd()
	{
		return _ld;
	}

	public void setEx(String ex)
	{
		_ex = ex;
	}

	public String getEx()
	{
		return _ex;
	}

	public void setPs(String ps)
	{
		_ps = ps;
	}

	public String getPs()
	{
		return _ps;
	}

	public void setDf(String df)
	{
		_df = df;
	}

	public String getDf()
	{
		return _df;
	}

	public void setSc(String sc)
	{
		_sc = sc;
	}

	public String getSc()
	{
		return _sc;
	}

	public void setPa(String pa)
	{
		_pa = pa;
	}

	public String getPa()
	{
		return _pa;
	}

	public void setFo(String fo)
	{
		_fo = fo;
	}

	public String getFo()
	{
		return _fo;
	}

	public void setPh(String ph)
	{
		_ph = ph;
	}

	public String getPh()
	{
		return _ph;
	}

	public void setDu(String du)
	{
		_du = du;
	}

	public String getDu()
	{
		return _du;
	}

	public void setEn(String en)
	{
		_en = en;
	}

	public String getEn()
	{
		return _en;
	}

	public void setSt(String st)
	{
		_st = st;
	}

	public String getSt()
	{
		return _st;
	}

	public void setSk(String sk)
	{
		_sk = sk;
	}

	public String getSk()
	{
		return _sk;
	}

	public void setDi(String di)
	{
		_di = di;
	}

	public String getDi()
	{
		return _di;
	}

	public void setFg(String fg)
	{
		_fg = fg;
	}

	public String getFg()
	{
		return _fg;
	}
	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeStringArray(new String[] {
											_name,
											_position,
											_overall,
											_health,
											_injury,
											_age,
											_contract,
											_salary,
											_farm.toString(),
											_ck});
	}
	
	public TeamPlayer() {}
	
	public TeamPlayer(Parcel in) {
		String[] data = new String[27];
		
		in.readStringArray(data);
		_name = data[0];
		_position = data[1];
		_overall = data[2];
		_health = data[3];
		_injury = data[4];
		_age = data[5];
		_contract = data[6];
		_salary = data[7];
		_farm = Boolean.parseBoolean(data[8]);
		_ck = data[9];
		_fg = data[10];
		_di = data[11];
		_sk = data[12];
		_st = data[13];
		_en = data[14];
		_du = data[15];
		_ph = data[16];
		_fo = data[17];
		_pa = data[18];
		_sc = data[19];
		_df = data[20];
		_ps = data[21];
		_ex = data[22];
		_ld = data[23];
		_mo = data[24];
		_ta = data[25];
		_sp = data[26];
	}
	
	public static final Parcelable.Creator<TeamPlayer> CREATOR
	= new Parcelable.Creator<TeamPlayer>() {
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
	private String _ck;
	private String _fg;
	private String _di;
	private String _sk;
	private String _st;
	private String _en;
	private String _du;
	private String _ph;
	private String _fo;
	private String _pa;
	private String _sc;
	private String _df;
	private String _ps;
	private String _ex;
	private String _ld;
	private String _mo;
	private String _ta;
	private String _sp;
	
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
	
	public String getCk() {
		return _ck;
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
	
	public void setCk(String value) {
		_ck = value;
	}
	
	@Override
	public String toString()
	{
		return _name + " - " + _position + " - " + _health + "%" + " - " + _injury + "\nOv: " + getOverall();
	}
}
