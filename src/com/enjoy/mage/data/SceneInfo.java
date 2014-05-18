package com.enjoy.mage.data;
import java.util.ArrayList;

import org.andengine.entity.sprite.Sprite;

import com.enjoy.mage.config.MapConfig;
import com.enjoy.mage.entity.Door;
import com.enjoy.mage.entity.Monst;
import com.enjoy.mage.entity.NPC;
import com.enjoy.mage.graphics.MapsGrh;
/**
 * @author shaoleibo
 * ��������XML��Ϣ
 */
public class SceneInfo {
	private int mId=0;
	private int mMapImageId=0;
	
	private ArrayList<Door> mDoors=new ArrayList<Door>();
	private ArrayList<NPC> mNpcs=new ArrayList<NPC>();
	private ArrayList<Monst> mMonsts=new ArrayList<Monst>(); //�����еĹ���
	
	private int[] mMark=new int[MapConfig.MAP_WIDTH/MapConfig.GRID_SIZE*(MapConfig.MAP_HEIGHT/MapConfig.GRID_SIZE)];
	private String mDesc="";
	private boolean mCanPK=false;//�Ƿ�ΪPK����
	private int mType=0;//��������
	private String mName="";//�������
	private LayoutInfo mLayout=null;
	
	
	public SceneInfo()
	{
		mLayout=new LayoutInfo(1, 0, 0);
	}
	
	public int[] getMark() {
		return mMark;
	}
	public void setMark(int[] mMark) {
		this.mMark = mMark;
	}
	public ArrayList<Door> getDoors() {
		return mDoors;
	}
	public void setDoors(ArrayList<Door> mDoors) {
		this.mDoors = mDoors;
	}
	public int getMapImageId() {
		return mMapImageId;
	}
	public void setMapImageId(int pMapImageId) {
		this.mMapImageId = pMapImageId;
	}
	public int getId() {
		return mId;
	}
	public void setId(int pId) {
		this.mId = pId;
	}

	public String getDesc() {
		return mDesc;
	}

	public void setDesc(String mDesc) {
		this.mDesc = mDesc;
	}
	
	public void addMonst(Monst pMonst)
	{
		mMonsts.add(pMonst);
	}
	
	//��ȡ�����б�
	public ArrayList<Monst> getMonsts()
	{
		return mMonsts;
	}
	
	//
	public void pushDoor(Door pDoor)
	{
		mDoors.add(pDoor);
	}
	
	//
	public void pushNpc(NPC pNpc)
	{
		mNpcs.add(pNpc);
	}
	
	public Sprite getMapImage()
	{
		return MapsGrh.getSprite(mMapImageId);
	}

	public ArrayList<NPC> getNpcs() {
		return mNpcs;
	}

	public void setNpcs(ArrayList<NPC> mNpcs) {
		this.mNpcs = mNpcs;
	}

	public boolean isCanPK() {
		return mCanPK;
	}

	public void setCanPK(boolean mCanPK) {
		this.mCanPK = mCanPK;
	}

	public int getType() {
		return mType;
	}

	public void setType(int mType) {
		this.mType = mType;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public LayoutInfo getLayout() {
		return mLayout;
	}

	public void setLayout(LayoutInfo mLayout) {
		this.mLayout = mLayout;
	}


	
}
