package com.bgood.danny.hockeyliguevirtuelle.DataModel;
import java.util.*;

public class ComparePlayerData
{
	private String attribut;
	private String leftValue;
	private String rightValue;

	public void setRightValue(String rightValue)
	{
		this.rightValue = rightValue;
	}

	public String getRightValue()
	{
		return rightValue;
	}

	public void setLeftValue(String leftValue)
	{
		this.leftValue = leftValue;
	}

	public String getLeftValue()
	{
		return leftValue;
	}

	public void setAttribut(String attribut)
	{
		this.attribut = attribut;
	}

	public String getAttribut()
	{
		return attribut;
	}
	
	public static ArrayList<ComparePlayerData> PopulateComparePlayerData(TeamPlayer left, TeamPlayer right) {
		ArrayList<ComparePlayerData> data = new ArrayList<ComparePlayerData>();
		
		ComparePlayerData cData = new ComparePlayerData();
		cData.setAttribut("Condition");
		cData.setLeftValue(left.getHealth() + "% - " + left.getInjiury());
		cData.setRightValue(right.getHealth() + "% - " + right.getInjiury());
		data.add(cData);
		
		cData = new ComparePlayerData();
		cData.setAttribut("Age");
		cData.setLeftValue(left.getAge() + " ans");
		cData.setRightValue(right.getAge() + " ans");
		data.add(cData);

		cData = new ComparePlayerData();
		cData.setAttribut("Salary");
		cData.setLeftValue(left.getSalary() + "$");
		cData.setRightValue(right.getSalary() + "$");
		data.add(cData);

		cData = new ComparePlayerData();
		cData.setAttribut("Contract");
		cData.setLeftValue(left.getContract() + " an(s)");
		cData.setRightValue(right.getContract() + " an(s)");
		data.add(cData);
		
		return data;
	}
}
