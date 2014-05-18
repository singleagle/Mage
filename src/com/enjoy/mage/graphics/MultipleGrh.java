package com.enjoy.mage.graphics;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackLoader;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackTextureRegionLibrary;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TextureRegionLibrary;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.common.Tools;


public class MultipleGrh {
	private ITexture mSpritesheetTexture;
	private TexturePackTextureRegionLibrary  mSpritesheetTextureRegionLibrary;
    public void onLoadResource(String path,String xmlPath)
	{
		try{
			TextureManager tm = Global.GetEngine().getTextureManager();
		   final TexturePack spritesheetTexturePack = new TexturePackLoader(tm,"textures/"+path).loadFromAsset(Global.GetContext().getAssets(),
				   xmlPath);
		   mSpritesheetTexture=spritesheetTexturePack.getTexture();
		   mSpritesheetTextureRegionLibrary=spritesheetTexturePack.getTexturePackTextureRegionLibrary();		  
		   Tools.LoadAtlas(mSpritesheetTexture);
		}
		catch(Exception e)
		{
		   
		}	    
	}
	
    //��ȡRegion
    public TextureRegion getRegion(int id)
    {
    	TextureRegion localRegion=mSpritesheetTextureRegionLibrary.get(id).deepCopy();
    	return localRegion;
    }
    
    //
	public Sprite getSprite(int id)
	{
		TextureRegion localRegion=mSpritesheetTextureRegionLibrary.get(id).deepCopy();	
		VertexBufferObjectManager vm = Global.GetEngine().getVertexBufferObjectManager();
		return new Sprite(0,0,localRegion, vm);
	}	
}
