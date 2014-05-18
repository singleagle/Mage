package com.enjoy.mage.graphics;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.common.Tools;


public class BubleGrh {
	private BitmapTextureAtlas mAtlas;
	private TiledTextureRegion  mRegion;
    private AnimatedSprite mAniSprite;
	
	public void onLoadResource()
	{
		Tools.setAssetsPath("textures/");
		TextureManager tm = Global.GetEngine().getTextureManager();
		mAtlas=new BitmapTextureAtlas(tm, 128,32,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mRegion=Tools.CreateTiledRegion(mAtlas,"buble.png",0,0,3,1); 
		Tools.LoadAtlas(mAtlas);
	}
	
	public void onLoadScene(Scene scene)
	{
		VertexBufferObjectManager vm = Global.GetEngine().getVertexBufferObjectManager();
		mAniSprite=new AnimatedSprite(0,0,34,32,mRegion,vm);
		mAniSprite.animate(100);
		if(!mAniSprite.hasParent())
		scene.attachChild(mAniSprite);
		mAniSprite.setVisible(false);
	}
	
	public void Show(int x,int y,int z)
	{
		mAniSprite.setVisible(true);
		mAniSprite.setPosition(x, y);
		mAniSprite.setZIndex(z);		
	}
	
	public void hide()
	{
		mAniSprite.setVisible(false);
	}
}
