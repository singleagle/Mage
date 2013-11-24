package com.enjoy.mage.manager;

import org.anddev.andengine.entity.scene.Scene;

import com.enjoy.mage.entity.Hero;
import com.enjoy.mage.graphics.PlayerGrh;

public class RoleManager {
	static Hero mHero=new Hero();
	public static Hero getHero() {
		return mHero;
	}

	public static void setHero(Hero mHero) {
		RoleManager.mHero = mHero;
	}

	static public void onLoadResources()
	{
		PlayerGrh mRoleGrh=new PlayerGrh();
		mRoleGrh.onLoadResource();	
		mHero.setGrh(mRoleGrh);
	}
	
	static public void onLoadScene(Scene scene)
	{
	   mHero.onLoadScene(scene);
    }
	
	static public int GetPosX()
	{
		return mHero.getPos().x;
	}
	
	static public int GetPosY()
	{
		return mHero.getPos().y;
	}
	
}
