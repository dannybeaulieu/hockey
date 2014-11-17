package com.bgood.danny.hockeyliguevirtuelle.DataModel;

public class PlayerAttribute
{
	private final String _name;
	private final int _start;
	private final int _end;
	
	public PlayerAttribute(String name, int start, int end) {
		_name = name;
		_start = start;
		_end = end;
	}

	public int getEnd()
	{
		return _end;
	}

	public int getStart()
	{
		return _start;
	}

	public String getName()
	{
		return _name;
	}
}
