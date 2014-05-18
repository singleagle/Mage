package com.enjoy.mage.ui;
import org.andengine.entity.sprite.Sprite;

import android.graphics.Color;

import com.enjoy.mage.config.UIConfig;

public class ChangeableTextLabel extends TextLabel {
	public ChangeableTextLabel()
	{
		super(12,Color.YELLOW);
	}
	
	public ChangeableTextLabel(int size,int color)
	{
		super(size,color);
	}
	
	public void onLoadResource()
	{
		super.onLoadResource();
	}
	
	public void onLoadSprite(Sprite parentSprite,String text,int x,int y)
	{
		if(mTextGrh!=null)
		mTextGrh.showChangeableText(text, x, y,UIConfig.UI_LEVEL+1,parentSprite);
	}
	
	public void setText(String text)
	{
		mTextGrh.updateText(text);
	}
}
