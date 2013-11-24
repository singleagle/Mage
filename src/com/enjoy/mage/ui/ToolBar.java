package com.enjoy.mage.ui;
import java.util.ArrayList;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
public class ToolBar {
	
    Sprite mRoleIconSprite,mBagIconSprite,mTaskIconSprite;
	private ArrayList<ToolBarItem> mToolBars=new ArrayList<ToolBarItem>();
	
	public ToolBar()
	{
		
	}
		
	public void onLoadScene(Scene scene)
	{
		for(ToolBarItem item:mToolBars)
		{
			item.onLoadScene(scene);
		}
	}
	
	public void add(ToolBarItem item)
	{
		mToolBars.add(item);
	}
	
	public ToolBarItem get(int index)
	{
		if(index<mToolBars.size())
		{
			return mToolBars.get(index);
		}
		else
		{
			return null;
		}
	}
	
}
