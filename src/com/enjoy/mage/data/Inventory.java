package com.enjoy.mage.data;
import java.util.ArrayList;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.util.Debug;




import com.enjoy.mage.config.UIConfig;
import com.enjoy.mage.entity.ItemEntity;

public class Inventory {
	ArrayList<ItemEntity> mItems=new ArrayList<ItemEntity>();
	public static final int MAX_CAPACITY = 30;//30������
	private int mCurUseSlot=0;//��ǰʹ���˶��ٸ�����	
	private Sprite mSpriteBatch;
	
	public ArrayList<ItemEntity> getItems()
	{
		return mItems;
	}
	
	public void onLoadInDlg(Sprite sprite)
	{
		mSpriteBatch=sprite;
		for(int i=0;i<mItems.size();i++)
		{
			mItems.get(i).showInInventory(sprite,i%UIConfig.INVENTORY_COL*38,i/UIConfig.INVENTORY_COL*45);
		}
	}
	
	//�Ƿ����ָ��ID����Ʒ
	public boolean isExitsItem(int id)
	{
		for(ItemEntity item:mItems)
		{
			if(item.getmId()==id)
				return true;
		}
		return false;
	}
	
	//�Ƿ����һϵ����Ʒ
	public boolean isExitsItems(ArrayList<Integer> pListIDs)
	{
		boolean result=false;
		for(int i=0;i<pListIDs.size();i++)
		{
			int localID=pListIDs.get(i);
			result=isExitsItem(localID);
		}
		return result;
	}
	
	public void deleteItems(ArrayList<Integer> pListIDs)
	{
		for(int i=0;i<pListIDs.size();i++)
		{
			int localID=pListIDs.get(i);
			deleteItem(localID);
		}
	}
	
	//�����Ʒ
	public void deleteItem(int id)
	{
		//
		Debug.w("�����Ʒ:"+id);
		ItemEntity localItem=null;
		
		//����
		for(ItemEntity item:mItems) 
		{
			if(item.getmId()==id)
				localItem=item;
		}
		if(localItem!=null)
		{
			if(!localItem.isbCanStack()) //��Ʒ���ܹ�������
			{
			  mItems.remove(localItem);
			  localItem.removeInInventory();
			  mCurUseSlot--;
			}
		}
	}
	
	//�Ƿ�ɹ������Ʒ
	public boolean addItem(ItemEntity item)
	{
		return addItem(item,true);
	}
	
	public boolean addItem(ItemEntity item,boolean bShow)
	{
		boolean result=false;
	    if(item.isbCanStack())
	    {
		   int index=getItem(item.getmId());
		   if(index>-1)
		   {
			   ItemEntity localItem=mItems.get(index);  //��ȡ�Ѿ���ӵ������е�ITEM
			   int localAmount=localItem.getAmount()+item.getAmount();
			   if(localAmount<100)
			   {
				   localItem.setAmount(localAmount);
			   }
			   else
			   {
				   localItem.setAmount(item.getMaxStackSize()); //�Ѿ����ڵĴﵽ���ֵ
				   if(mCurUseSlot<Inventory.MAX_CAPACITY)  //���û����������
				   {
				     int localLeft=localAmount-item.getMaxStackSize();
				     ItemEntity localLeftItem=localItem.copy();
				     localLeftItem.setAmount(localLeft);
				     mItems.add(localLeftItem);			   
			         mCurUseSlot++;
			         result=true;
				   }
		    }
		   }
	   }
	   else
	   {
		   if(mCurUseSlot<Inventory.MAX_CAPACITY)
		   {
			   mItems.add(item);
			   
			   if(bShow)
			   {
			    item.showInInventory(mSpriteBatch,mCurUseSlot%UIConfig.INVENTORY_COL*38,mCurUseSlot/UIConfig.INVENTORY_COL*45);
			    mCurUseSlot++;
			   }
			   result=true;
		   }		   
	   }
	   return result;
	}
	
	public void addFromFile(ItemEntity item)
	{
		addItem(item,false);
	}
	
	//����ָ��ID�� û���ص������ֵ��ITEM
	private int getItem(int id)
	{
		int index=-1;
		for(int i=0;i<mItems.size();i++)
		{
			ItemEntity item=mItems.get(i);
			if((item.getmId()==id)&&(item.getAmount()<=item.getMaxStackSize()))
			{
			    index=i;
				break;
			}
		}
		return index;
	}
	
	public void clear()
	{
		mItems.clear();
	}
}
