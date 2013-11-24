package com.enjoy.mage.process;
import java.util.ArrayList;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.util.MathUtils;

import com.enjoy.mage.config.SceneConfig;
import com.enjoy.mage.data.ItemsReader;
import com.enjoy.mage.entity.ItemEntity;

import android.graphics.Point;

public class GoodsDropProcessor {
	
	//�������ָ��һ�����͵���Ʒ
	public static void monstDropGoods(Scene scene,Point pPos,int pID,int min,int max)
	{
		if(min>max)
			return;	
		int num=MathUtils.random(min, max);
		ItemEntity item=ItemsReader.mItemsTable.get(pID);
		mDropItemsList.clear();
		if(item!=null)
		{
		  for(int i=0;i<num;i++)
		  {
			int posx=MathUtils.random(pPos.x-SceneConfig.GOODS_DROP_RANGE,pPos.x+SceneConfig.GOODS_DROP_RANGE);
			int posy=MathUtils.random(pPos.y-SceneConfig.GOODS_DROP_RANGE,pPos.y+SceneConfig.GOODS_DROP_RANGE);
		    ItemEntity localItem=item.copy();
			localItem.showOnGround(scene,posx,posy);
			mDropItemsList.add(localItem);
		  }
		}
	}
	
	public static ArrayList<ItemEntity> mDropItemsList=new ArrayList<ItemEntity>();
	
	
	//��ȡ�ڽ�ɫ���µ���Ʒ
	public static ItemEntity getHeroOn()
	{
		for(ItemEntity item:mDropItemsList)
		{
			if(item.ismIsHeroOn())
				return item;
		}
		return null;
	}
	
	public static void removeFromList(ItemEntity pItem)
	{
		mDropItemsList.remove(pItem);
	}
}
