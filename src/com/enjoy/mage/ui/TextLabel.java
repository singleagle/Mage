package com.enjoy.mage.ui;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.graphics.Color;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.config.UIConfig;
import com.enjoy.mage.graphics.SceneTextGrh;

public class TextLabel {
	

	protected SceneTextGrh mTextGrh;
	int mSize=16;//���ִ�С
	
	public int getSize() {
		return mSize;
	}

	int mColor=Color.WHITE;
	
	public TextLabel()
	{
		mTextGrh=new SceneTextGrh(mSize,mColor);	
	}
	
	public TextLabel(int pSize,int pColor)
	{
		mSize=pSize;
		mColor=pColor;
		mTextGrh=new SceneTextGrh(mSize,mColor);	
	}
	
	public void onLoadResource()
	{
		mTextGrh.onLoadResource();
	}
	
	
	public void onLoadSprite(Scene scene,Sprite parentSprite,String text,int x,int y,final IClickListener click)
	{   
		VertexBufferObjectManager vm = Global.GetEngine().getVertexBufferObjectManager();
		Rectangle mBackRect=new Rectangle(x-mSize,y,mSize*12,mSize, vm)
		{

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				click.onClick();
				return true;
			}
			
		};
		mBackRect.setAlpha(0.5f);
		mTextGrh.showText(text,14,0,UIConfig.UI_LEVEL+1,mBackRect);
				
		parentSprite.attachChild(mBackRect);
		scene.registerTouchArea(mBackRect);
	}
}
