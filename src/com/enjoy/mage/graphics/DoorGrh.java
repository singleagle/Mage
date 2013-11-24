package com.enjoy.mage.graphics;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import com.enjoy.mage.common.Tools;

/**
 * @author shaoleibo
 * �������͵�ͼƬ
 */
public class DoorGrh {
	static private BitmapTextureAtlas mDoorAtlas;
	static private TiledTextureRegion mDoorTexture;
	
	public static void onLoadResource() {
		// TODO Auto-generated method stub
		Tools.setAssetsPath("textures/");
		mDoorAtlas=new BitmapTextureAtlas(1024,256,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mDoorTexture=Tools.CreateTiledRegion(mDoorAtlas,"door.png",0,0,8,1);
		Tools.LoadAtlas(mDoorAtlas);
	}
	
	public static AnimatedSprite CreateSprite()
	{
		AnimatedSprite doorSprite=new AnimatedSprite(0,0,mDoorTexture);
		return doorSprite;
	}
}
