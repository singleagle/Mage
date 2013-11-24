package com.enjoy.mage.graphics;

import java.util.HashMap;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

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
		//Debug.w("�ļ�����:"+MapsGrhReader.getFiles().size());
		for(MapGrhInfo info:MapsGrhReader.getFiles())
		{
			BitmapTextureAtlas localMapAtlas=new BitmapTextureAtlas(1024,512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);;
			TextureRegion localRegion=Tools.CreateTextureRegion(localMapAtlas, info.getFileName(), 0, 0);
			mIdTextures.put(info.getId(),localRegion);
			Tools.LoadAtlas(localMapAtlas);
		}
	}
	
	public static Sprite getSprite(int id)
	{
		return new Sprite(0,0,mIdTextures.get(id));
	}
    
	
}
