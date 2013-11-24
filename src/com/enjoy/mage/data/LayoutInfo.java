package com.enjoy.mage.data;

public class LayoutInfo {
	private int mType=SceneLayout.FLOW;
	private int mPosX=0;
	private int mPosY=0;
	public LayoutInfo(int pType,int pPosX,int pPosY)
	{
		setType(pType);
		setPosX(pPosX);
		setPosY(pPosY);
	}
	public int getPosY() {
		return mPosY;
	}
	public void setPosY(int mPosY) {
		this.mPosY = mPosY;
	}
	public int getPosX() {
		return mPosX;
	}
	public void setPosX(int mPosX) {
		this.mPosX = mPosX;
	}
	public int getType() {
		return mType;
	}
	public void setType(int mType) {
		this.mType = mType;
	}
}
