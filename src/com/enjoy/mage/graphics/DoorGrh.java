package com.enjoy.mage.graphics;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.enjoy.mage.common.Global;
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
		TextureManager tm = Global.GetEngine().getTextureManager();
		mDoorAtlas=new BitmapTextureAtlas(tm, 1024,256,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mDoorTexture=Tools.CreateTiledRegion(mDoorAtlas,"door.png",0,0,8,1);
		Tools.LoadAtlas(mDoorAtlas);
	}
	
	public static AnimatedSprite CreateSprite()
	{ 
		VertexBufferObjectManager vm = Global.GetEngine().getVertexBufferObjectManager();
		AnimatedSprite doorSprite=new AnimatedSprite(0,0,mDoorTexture, vm);
		return doorSprite;
	}
}
