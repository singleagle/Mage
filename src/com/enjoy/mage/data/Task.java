package com.enjoy.mage.data;
import java.util.ArrayList;

public class Task {
	private int mID=0;  
	private int mNPCID=0; 
	private int mRequiredTaskID=-1; 
	private int mType=0; 
	private String mDesc="";
	
	private ArrayList<Integer> mRequiredItemsID=new ArrayList<Integer>(); //��Ҫ��ƷID
	
	public ArrayList<Integer> getmRequiredItemsID() {
		return mRequiredItemsID;
	}
	public void setmRequiredItemsID(ArrayList<Integer> mRequiredItemsID) {
		this.mRequiredItemsID = mRequiredItemsID;
	}

	private ArrayList<String> mReplies=new ArrayList<String>();//�Ի�������Ĵ��б�
	
	private String mInProgerssDesc=""; 
	
	public int getmID() {
		return mID;
	}
	public void setmID(int mID) {
		this.mID = mID;
	}
	public int getmNPCID() {
		return mNPCID;
	}
	public void setmNPCID(int mNPCID) {
		this.mNPCID = mNPCID;
	}
	public int getmRequiredTaskID() {
		return mRequiredTaskID;
	}
	public void setmRequiredTaskID(int mRequiredTaskID) {
		this.mRequiredTaskID = mRequiredTaskID;
	}
	public int getmType() {
		return mType;
	}
	public void setmType(int mType) {
		this.mType = mType;
	}
	public String getmDesc() {
		return mDesc;
	}
	public void setmDesc(String mDesc) {
		this.mDesc = mDesc;
	}
	
	public void putReqItem(int id)
	{
		mRequiredItemsID.add(id);
	}
	public String getmInProgerssDesc() {
		return mInProgerssDesc;
	}
	
	public void setmInProgerssDesc(String mInProgerssDesc) {
		this.mInProgerssDesc = mInProgerssDesc;
	}
	
	public void putReply(String str)
	{
		mReplies.add(str);
	}
	
	public ArrayList<String> getReplies()
	{
		return mReplies;
	}
	
	
}
