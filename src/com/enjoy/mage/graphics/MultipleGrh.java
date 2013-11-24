package com.enjoy.mage.graphics;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.ITexture;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionLibrary;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.common.Tools;
import com.enjoy.mage.mytexturepacker.TexturePack;
import com.enjoy.mage.mytexturepacker.TexturePackLoader;


public class MultipleGrh {
	private ITexture mSpritesheetTexture;
	private TextureRegionLibrary mSpritesheetTextureRegionLibrary;
    public void onLoadResource(String path,String xmlPath)
	{
		try{
		   final TexturePack spritesheetTexturePack = new TexturePackLoader(Global.GetContext(),"textures/"+path).loadFromAsset(Global.GetContext(),xmlPath);
		   mSpritesheetTexture=spritesheetTexturePack.getTexture();
		   mSpritesheetTextureRegionLibrary=spritesheetTexturePack.getTextureRegionLibrary();		  
		   Tools.LoadAtlas(mSpritesheetTexture);
		}
		catch(Exception e)
		{
		   
		}	    
	}
	
    //��ȡRegion
    public TextureRegion getRegion(int id)
    {
    	TextureRegion localRegion=mSpritesheetTextureRegionLibrary.get(id).clone();
    	return localRegion;
    }
    
    //
	public Sprite getSprite(int id)
	{
		TextureRegion localRegion=mSpritesheetTextureRegionLibrary.get(id).clone();	
		return new Sprite(0,0,localRegion);
	}	
}
