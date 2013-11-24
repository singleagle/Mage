package com.enjoy.mage.entity;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;

import com.enjoy.mage.common.Tools;
import com.enjoy.mage.config.SceneConfig;
import com.enjoy.mage.graphics.DoorGrh;
import com.enjoy.mage.graphics.SceneTextGrh;
import com.enjoy.mage.manager.RoleManager;

import android.graphics.Point;
import android.graphics.Rect;

public class Door{
    protected Point mPos;
	protected Rect mRect;
	protected String mTitle="";  //���� 
	protected int mToSceneId=-1; //���͵��ĳ���
    protected int mToPosX=0,mToPosY=0;
    protected int mImgId=-1; //Ĭ��Ϊ���Ͷ�����ͼƬ
    protected SceneTextGrh mTextGrh=null;
    

    public SceneTextGrh getmTextGrh() {
		return mTextGrh;
	}

	public void setmTextGrh(SceneTextGrh mTextGrh) {
		this.mTextGrh = mTextGrh;
	}

	public int getImgId() {
		return mImgId;
	}

	public void setImgId(int mImgId) {
		this.mImgId = mImgId;
	}

	public Door(Point pPos)
	{	
		mRect=new Rect(pPos.x,pPos.y,pPos.x+SceneConfig.DOOR_WIDTH,pPos.y+SceneConfig.DOOR_HEIGHT);
		setPos(pPos);
	}
    
    public Door(int x,int y,int pToScene,int pToSceneX,int pToSceneY,String pDesc,int pImageId)
    {
    	setPos(new Point(x,y));
    	mToSceneId=pToScene;
    	mToPosX=pToSceneX;
    	mToPosY=pToSceneY;
    	mTitle=pDesc;
    	mImgId=pImageId;
    }
	
    public Point getPos() {
		return mPos;
	}

	public void setPos(Point mPos) {
		this.mPos = mPos;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String mDesc) {
		this.mTitle = mDesc;
	}

	public int getToSceneId() {
		return mToSceneId;
	}

	public void setToSceneId(int mToSceneId) {
		this.mToSceneId = mToSceneId;
	}

	public int getToPosX() {
		return mToPosX;
	}

	public void setToPosX(int mToPosX) {
		this.mToPosX = mToPosX;
	}

	public int getToPosY() {
		return mToPosY;
	}

	public void setToPosY(int mToPosY) {
		this.mToPosY = mToPosY;
	}
	
    long start=0;
	int end=0;
	public void loadOnScene(final Scene scene)
	{
		final AnimatedSprite sprite=DoorGrh.CreateSprite();
        
		sprite.animate(200);
		sprite.setPosition(mPos.x,mPos.y);
		sprite.setZIndex(mPos.y+SceneConfig.DOOR_HEIGHT);
		sprite.registerUpdateHandler(new IUpdateHandler() {					
			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onUpdate(float pSecondsElapsed) {
				// TODO Auto-generated method stub
				
				if(Tools.BInRect(RoleManager.getHero().getPos(),mPos.x,mPos.y,116,135))
				{
					//RoleManager.getHero().setPos(mToPosX, mToPosY);
					//Global.GetEngine().setScene(SceneManager.getScene(1000));
					//RoleManager.getHero().setPos(10,10);
					//sprite.unregisterUpdateHandler(this);					
					RoleManager.getHero().setInDoor(true);
				}
				else
				{
					RoleManager.getHero().setInDoor(false);
				}
			}
		});
				
		scene.attachChild(sprite);		
	}
}
