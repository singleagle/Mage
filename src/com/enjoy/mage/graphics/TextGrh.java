package com.enjoy.mage.graphics;
import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.util.ColorUtils;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.common.Tools;
import com.enjoy.mage.config.UIConfig;
import com.enjoy.mage.entity.TextType;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;

public class TextGrh {
    private BitmapTextureAtlas mNameFontTexture,mContentFontTexture;
    private Font mNameFont,mContentFont;
    private int mNameSize=18,mContentSize=13;
    ChangeableText mNameSprite,mContentSprite;
 
	public void onLoadResource()
	{
		this.mNameFontTexture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);	
		this.mContentFontTexture = new BitmapTextureAtlas(1024,1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);	
	    mNameFont = new Font(this.mNameFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD),mNameSize, true,UIConfig.TASK_NAME_COLOR);		 
		mContentFont=new Font(this.mContentFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD),mContentSize, true,UIConfig.TASK_CONTENT_COLOR);
		Tools.LoadAtlas(mNameFontTexture);
	    Tools.LoadAtlas(mContentFontTexture);
		Tools.LoadFont(mNameFont);
	    Tools.LoadFont(mContentFont);
	}
	
	public void initText(TextType type,String text,int x,int y,IEntity parent)	
	{	
		switch(type)
		{
			case TASK_NAME:
		         mNameSprite= new ChangeableText(x,y,mNameFont,text,20);	
		         parent.attachChild(mNameSprite);
		         break;
			case TASK_CONTENT:
		         mContentSprite= new ChangeableText(x,y,mContentFont,text,200);
		         parent.attachChild(mContentSprite);
		         break;	
		}
	}	
	
	public void setNameText(String text)
	{
		mNameSprite.setText(text);
	}
	
	public void setContentText(String text)
	{
		mContentSprite.setText(text);
	}
}
