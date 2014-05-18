package com.enjoy.mage.graphics;

import org.andengine.entity.IEntity;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.graphics.Typeface;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.common.Tools;
import com.enjoy.mage.config.UIConfig;
public class SceneTextGrh {
	private BitmapTextureAtlas mFontTexture;
    private Font mFont;
    protected int mSize=14;
    protected int mColor=UIConfig.SCENE_TEXT_COLOR;
    
    public SceneTextGrh()
    {
    	
    }
    public SceneTextGrh(int size,int color)
    {
    	mSize=size;
    	mColor=color;
    }
 
	public void onLoadResource()
	{
		TextureManager tm = Global.GetEngine().getTextureManager();
		this.mFontTexture = new BitmapTextureAtlas(tm, 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);	
	    mFont = new Font(Global.GetEngine().getFontManager(), this.mFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD),mSize, true,mColor);		 
        Tools.LoadAtlas(mFontTexture);
        Tools.LoadFont(mFont);
	}
	
	public void showText(String text,int x,int y,int z,IEntity parent)	
	{	
		VertexBufferObjectManager vm = Global.GetEngine().getVertexBufferObjectManager();
		Text localSprite= new Text( x,y,mFont,text, vm);	
		localSprite.setZIndex(z);
		parent.attachChild(localSprite);		         
	}	
	
	Text mAniText;
	public void showChangeableText(String text,int x,int y,int z,IEntity parent)
	{
		VertexBufferObjectManager vm = Global.GetEngine().getVertexBufferObjectManager();
		mAniText=new Text(x, y, mFont, text, vm);
		mAniText.setZIndex(z);		
		parent.attachChild(mAniText);
	}
	
	public void updateText(String text,int x,int y)
	{
		if(mAniText!=null)
		{
			mAniText.setPosition(x, y);
			mAniText.setText(text);
		}
	}
    
    
	public void updateText(String text)
	{
		if(mAniText!=null)
		{
			mAniText.setText(text);
		}
	}
	
	public void setVisible(boolean b)
	{
		mAniText.setVisible(b);
	}
	public Text getmAniText() {
		return mAniText;
	}
}
