package com.enjoy.mage.graphics;

import java.util.HashMap;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.common.Tools;
import com.enjoy.mage.data.MapGrhInfo;
import com.enjoy.mage.data.MapsGrhReader;


////
public class MapsGrh{
    static private HashMap<Integer,TextureRegion> mIdTextures=new HashMap<Integer,TextureRegion>();
    static public void onLoadResource() {	
		MapsGrhReader.onLoad();
		Tools.setAssetsPath("textures/maps/");		
		createRegion();
		Tools.setAssetsPath("textures/");
	}
	
	static private void createRegion()
	{
		for(MapGrhInfo info:MapsGrhReader.getFiles())
		{
			TextureManager tm = Global.GetEngine().getTextureManager();
			BitmapTextureAtlas localMapAtlas=new BitmapTextureAtlas(tm, 1024,512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);;
			TextureRegion localRegion=Tools.CreateTextureRegion(localMapAtlas, info.getFileName(), 0, 0);
			mIdTextures.put(info.getId(),localRegion);
			Tools.LoadAtlas(localMapAtlas);
		}
	}
	
	public static Sprite getSprite(int id)
	{
		VertexBufferObjectManager vm = Global.GetEngine().getVertexBufferObjectManager();
		return new Sprite(0,0,mIdTextures.get(id), vm);
	}
    
	
}
