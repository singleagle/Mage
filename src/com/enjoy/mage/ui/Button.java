package com.enjoy.mage.ui;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import android.graphics.Color;

import com.enjoy.mage.config.MultipleGrhCfg;
import com.enjoy.mage.config.UIConfig;
import com.enjoy.mage.graphics.SceneTextGrh;
import com.enjoy.mage.manager.MultipleManager;


//
public class Button{
	SceneTextGrh mTextGrh=new SceneTextGrh(12,Color.YELLOW);
	public void onLoadResource()
	{
		mTextGrh.onLoadResource();
	}
	
	//��ʾ����
	Sprite mSprite;
	public void onLoadSprite(Scene scene,Sprite parentSprite,String text,final IClickListener click)
	{
		
		TextureRegion localRegion=MultipleManager.FACE.getRegion(MultipleGrhCfg.BTN_ID);
		mSprite=new Sprite(0,0,localRegion)
		{

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
			  if(pSceneTouchEvent.isActionDown())
			  {
			    click.onClick();
			    return true;
			  }
			  else
			  {
				return false;
			  }
			}
			
		};
		
		mTextGrh.showText(text,11,7,UIConfig.UI_LEVEL+1,mSprite);
		
		parentSprite.attachChild(mSprite);
		
		scene.registerTouchArea(mSprite);
		setPostion(134,295);
	}
	
	public void setPostion(int x,int y)
	{
		if(mSprite!=null)
		{
			mSprite.setPosition(x, y);
		}
	}
	
	public void setVisble(boolean b)
	{
		mSprite.setVisible(b);
	}

	public Sprite getSprite() {
		return mSprite;
	}
}
