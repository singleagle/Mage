package com.enjoy.mage.graphics;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;




import android.graphics.Typeface;

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
		this.mFontTexture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);	
	    mFont = new Font(this.mFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD),mSize, true,mColor);		 
        Tools.LoadAtlas(mFontTexture);
        Tools.LoadFont(mFont);
	}
	
	public void showText(String text,int x,int y,int z,IEntity parent)	
	{	
		Text localSprite= new Text(x,y,mFont,text);	
		localSprite.setZIndex(z);
		parent.attachChild(localSprite);		         
	}	
	
	ChangeableText mAniText;
	public void showChangeableText(String text,int x,int y,int z,IEntity parent)
	{
		mAniText=new ChangeableText(x, y, mFont, text);
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
	public ChangeableText getmAniText() {
		return mAniText;
	}
}
