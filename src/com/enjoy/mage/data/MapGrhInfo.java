package com.enjoy.mage.data;
public class MapGrhInfo {
	private int mId=0;  //����ͼƬ��ID
	private String mDescName="";//�������
	private String mFileName="";
	public String getFileName() {
		return mFileName;
	}
	public void setFileName(String mFileName) {
		this.mFileName = mFileName;
	}
	public int getId() {
		return mId;
	}
	public void setId(int mId) {
		this.mId = mId;
	}
	
	public MapGrhInfo(int pId,String pDescName,String pFileName)
	{
		this.mId=pId;
		this.mDescName=pDescName;
		this.mFileName=pFileName;
	}
	public String getmDescName() {
		return mDescName;
	}
	public void setmDescName(String mDescName) {
		this.mDescName = mDescName;
	}
	
	
}
