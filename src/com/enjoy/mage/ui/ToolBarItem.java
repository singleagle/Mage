package com.enjoy.mage.ui;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.config.UIConfig;
import com.enjoy.mage.graphics.DlgGrh;

import android.graphics.Point;

public class ToolBarItem extends Dialog{


	int mImageId=0;
	int mX=0,mY=0;//�����е����
	
	TextureRegion mRegion;
	IClickListener mClick;
	public int getImageId() {
		return mImageId;
	}
    
	//
	public void setImageId(int mImageId) {
		this.mImageId = mImageId;
	}

	public ToolBarItem(Point pPos,TextureRegion pRegion,IClickListener click) {
		super(pPos);
		mRegion=pRegion;
		mClick=click;
		VertexBufferObjectManager vm = Global.GetEngine().getVertexBufferObjectManager();
		lDlgSprite=new Sprite(mPos.x,mPos.y,DlgGrh.getmRingIconRegion(), vm);
	}
	
	public ToolBarItem(int x,int y,TextureRegion pRegion,IClickListener click)
	{
		this(new Point(x,y),pRegion,click);
	}
	int mCount=0;
	Sprite lDlgSprite;
    public void onLoadScene(Scene scene)
	{
    	mCount++;   	
		//Sprite lDlgSprite=new Sprite(mPos.x,mPos.y,DlgGrh.getmRingIconRegion());
    	if(!lDlgSprite.hasParent()) //�����´�����ʱ���ظ�����  ע��Ŀǰ��֪��Ϊʲô�ٴ�����ʱ�� �����������
    	{
    	scene.attachChild(lDlgSprite);	
    	VertexBufferObjectManager vm = Global.GetEngine().getVertexBufferObjectManager();
		Sprite iconSprite=new Sprite(10,10,mRegion, vm)
		{
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
			   if(pSceneTouchEvent.isActionDown())
			   {
				mClick.onClick();
			   return true;
			   }else
			   {
				   return false;
			   }
			}
		};
		
	
		lDlgSprite.attachChild(iconSprite);

		lDlgSprite.setZIndex(UIConfig.CONTROLLER_LEVEL);
		
		scene.registerTouchArea(iconSprite);
    	}
    	
	}
    

}
