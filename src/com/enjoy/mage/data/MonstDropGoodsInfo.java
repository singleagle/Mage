package com.enjoy.mage.data;

public class MonstDropGoodsInfo {
	public int mID=-1;
	public int getmID() {
		return mID;
	}

	public void setmID(int pID) {
		this.mID = pID;
	}

	public int mMinDropNum=0;
	public int getmMinDropNum() {
		return mMinDropNum;
	}

	public void setmMinDropNum(int pMinDropNum) {
		this.mMinDropNum = pMinDropNum;
	}

	public int mMaxDropNum=0;
   
	public int getmMaxDropNum() {
		return mMaxDropNum;
	}

	public void setmMaxDropNum(int pMaxDropNum) {
		this.mMaxDropNum = pMaxDropNum;
	}

	public MonstDropGoodsInfo(int pID,int pMinDropNum,int pMaxDropNum) {
		mID=pID;
		mMinDropNum=pMinDropNum;
		mMaxDropNum=pMaxDropNum;
	}
}
