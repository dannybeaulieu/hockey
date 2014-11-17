package com.bgood.danny.hockeyliguevirtuelle.DataModel;
import java.util.*;

public class ComparePlayerData
{
	private String attribute;
	private String leftValue;
	private String rightValue;

	void setRightValue(String rightValue)
	{
		this.rightValue = rightValue;
	}

	public String getRightValue()
	{
		return rightValue;
	}

	void setLeftValue(String leftValue)
	{
		this.leftValue = leftValue;
	}

	public String getLeftValue()
	{
		return leftValue;
	}

	void setAttribute(String attribute)
	{
		this.attribute = attribute;
	}

	public String getAttribute()
	{
		return attribute;
	}
	
	public static ArrayList<ComparePlayerData> PopulateComparePlayerData(TeamPlayer left, TeamPlayer right) {
		ArrayList<ComparePlayerData> data = new ArrayList<ComparePlayerData>();
		
		ComparePlayerData cData = new ComparePlayerData();
		cData.setAttribute("Condition");
		cData.setLeftValue(left.getHealth() + (left.getInjury().length() == 0 ? "" : "% - " + left.getInjury()));
		cData.setRightValue(left.getHealth() + (right.getInjury().length() == 0 ? "" : "% - " + right.getInjury()));
		data.add(cData);
		
		cData = new ComparePlayerData();
		cData.setAttribute("Position");
		cData.setLeftValue(left.getPosition());
		cData.setRightValue(right.getPosition());
		data.add(cData);
		
		cData = new ComparePlayerData();
		cData.setAttribute("Age");
		cData.setLeftValue(left.getAge() + " ans");
		cData.setRightValue(right.getAge() + " ans");
		data.add(cData);

		cData = new ComparePlayerData();
		cData.setAttribute("Salary");
		cData.setLeftValue(left.getSalary() + "$");
		cData.setRightValue(right.getSalary() + "$");
		data.add(cData);

		cData = new ComparePlayerData();
		cData.setAttribute("Contract");
		cData.setLeftValue(left.getContract() + " an(s)");
		cData.setRightValue(right.getContract() + " an(s)");
		data.add(cData);
		
		cData = new ComparePlayerData();
		cData.setAttribute("Overall");
		cData.setLeftValue(left.getOverall());
		cData.setRightValue(right.getOverall());
		data.add(cData);
		
		for (String key : left.getAttributes().keySet()) {
			cData = new ComparePlayerData();
			cData.setAttribute(key);
			cData.setLeftValue(left.getAttributes().get(key));
			cData.setRightValue(right.getAttributes().get(key));
			data.add(cData);
		}
		
		return data;
	}
}
