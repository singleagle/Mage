package com.enjoy.mage.graphics;
import org.andengine.engine.Engine;
import org.andengine.entity.IEntity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

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
    Text mNameSprite,mContentSprite;
 
	public void onLoadResource()
	{
		TextureManager tm = Global.GetEngine().getTextureManager();
		this.mNameFontTexture = new BitmapTextureAtlas(tm, 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);	
		this.mContentFontTexture = new BitmapTextureAtlas(tm, 1024,1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);	
	    mNameFont = new Font(Global.GetEngine().getFontManager(), this.mNameFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD),mNameSize, true,UIConfig.TASK_NAME_COLOR);		 
		mContentFont=new Font(Global.GetEngine().getFontManager(), this.mContentFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD),mContentSize, true,UIConfig.TASK_CONTENT_COLOR);
		Tools.LoadAtlas(mNameFontTexture);
	    Tools.LoadAtlas(mContentFontTexture);
		Tools.LoadFont(mNameFont);
	    Tools.LoadFont(mContentFont);
	}
	
	public void initText(TextType type,String text,int x,int y,IEntity parent)	
	{	
		VertexBufferObjectManager vm = Global.GetEngine().getVertexBufferObjectManager();
		switch(type)
		{
			case TASK_NAME:
		         mNameSprite= new Text(x,y,mNameFont,text,20, vm);	
		         parent.attachChild(mNameSprite);
		         break;
			case TASK_CONTENT:
		         mContentSprite= new Text(x,y,mContentFont,text,200, vm);
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
