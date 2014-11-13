package com.bgood.danny.hockeyliguevirtuelle.DataModel;

public class PlayerAttribute
{
	private String _name;
	private int _start;
	private int _end;
	
	public PlayerAttribute(String name, int start, int end) {
		_name = name;
		_start = start;
		_end = end;
	}

	public void setEnd(int end)
	{
		_end = end;
	}

	public int getEnd()
	{
		return _end;
	}

	public void setStart(int start)
	{
		_start = start;
	}

	public int getStart()
	{
		return _start;
	}

	public void setName(String name)
	{
		_name = name;
	}

	public String getName()
	{
		return _name;
	}
}
