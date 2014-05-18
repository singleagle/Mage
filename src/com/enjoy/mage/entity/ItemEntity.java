package com.enjoy.mage.entity;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;

import com.enjoy.mage.config.SceneConfig;
import com.enjoy.mage.config.UIConfig;
import com.enjoy.mage.manager.MultipleManager;
import com.enjoy.mage.manager.RoleManager;

public class ItemEntity {
	private String mName="";
	private String mDesc="";//����
	
	private int mId=0; 
	private int mMinIconId=0;//�ڱ����е�ͼ��͵����ϵ�ͼ������
	private int mMaxIconId=0;//�ڵ�ͼ�ϵ�ID
	
	private boolean bCanStack=false;//��Ʒ�Ƿ��ܹ�������ʾ
	private int mAmount=1;//��Ʒ������
	
	private final int MaxStackSize = 99;//������Ʒ��������
	public int getMaxStackSize() {
		return MaxStackSize;
	}

	private Sprite mMinIcon=null,mMaxIcon=null;

	
	public ItemEntity(int pId,String pName,String pDesc,int pMinIconId,
			int pMaxIconId,boolean pBCanStack)
	{
	   mId=pId;
	   mName=pName;
	   mDesc=pDesc;
	   mMinIconId=pMinIconId;
	   mMaxIconId=pMaxIconId;
	   bCanStack=pBCanStack;
	}
	
    public void showInInventory(Sprite batch,int x,int y)
	{
		mMinIcon=MultipleManager.MINITEMS.getSprite(mMinIconId);	
		batch.attachChild(mMinIcon);
		mMinIcon.setPosition(UIConfig.INVENTORY_OFFSET_X+x,UIConfig.INVENTORY_OFFSET_Y+y);
		mMinIcon.setZIndex(UIConfig.ITEM_MIN_ICON);
	}
    
    public void removeInInventory()
    {
    	mMinIcon.setVisible(false);
    }
    
    public ItemEntity copy()
    {
    	return new ItemEntity(mId,mName,mDesc,mMinIconId,mMaxIconId,bCanStack);
    }
    
    Scene mScene=null;
    //��Ʒ�����ڵ���
	public void showOnGround(Scene scene,int x,int y)
	{
		mScene=scene;
		mMaxIcon=MultipleManager.MINITEMS.getSprite(mMinIconId);
	    scene.attachChild(mMaxIcon);
	    int posx=x-SceneConfig.GOODS_DROP_OFFSETX;
	    int posy=y-SceneConfig.GOODS_DORP_OFFSETY;
	    mMaxIcon.setPosition(posx,posy);
	    mMaxIcon.setZIndex(1);
	    mMaxIcon.registerUpdateHandler(new IUpdateHandler() {
			
			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onUpdate(float pSecondsElapsed) {
				// TODO Auto-generated method stub
				if(mMaxIcon.contains(RoleManager.GetPosX(),RoleManager.GetPosY()))
				{
					mMaxIcon.setAlpha(0.6f);
					mIsHeroOn=true;
				  //  RoleManager.getHero().setmStandOn(null);
				}
				else
				{					
					mMaxIcon.setAlpha(1.0f);
					mIsHeroOn=false;
					//RoleManager.getHero().setmStandOn(ItemEntity.this);
				}
			}
		});
	}
	
	private boolean mIsHeroOn=false; //�����Ƿ�վ������
	

	public boolean isbCanStack() {
		return bCanStack;
	}

	public int getmId() {
		return mId;
	}

	public int getAmount() {
		return mAmount;
	}

	public void setAmount(int mAmount) {
		if(mAmount<=MaxStackSize)
		  this.mAmount = mAmount;
		else
		   mAmount=0;
			
	}

	public String getmDesc() {
		return mDesc;
	}

	public String getmName() {
		return mName;
	}

	public int getmMaxIconId() {
		return mMaxIconId;
	}

	public boolean ismIsHeroOn() {
		return mIsHeroOn;
	}

	public void setmIsHeroOn(boolean mIsHeroOn) {
		this.mIsHeroOn = mIsHeroOn;
	}

	
	public void removeOnGround() {
		// TODO Auto-generated method stub
		mMaxIcon.setVisible(false);
		mMaxIcon.setIgnoreUpdate(true);
		
	}

}
