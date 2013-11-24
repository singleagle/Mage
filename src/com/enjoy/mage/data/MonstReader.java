package com.enjoy.mage.data;
import java.io.InputStream;
import java.util.Hashtable;

import org.anddev.andengine.util.Debug;

import android.content.res.AssetManager;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.common.Tools;
import com.enjoy.mage.entity.Monst;

/**
 * @author shaoleibo
 * Monstʵ������� ��SWX�ļ�������
 */
public class MonstReader {
	
	private static Hashtable<Integer, Monst> mMonstTable=new Hashtable<Integer, Monst>();
	
	public static Monst GetMonst(int pID)
	{
		return mMonstTable.get(pID);
	}
	
	public static void onLoad()
	{
		try
		{
			MonstGrhReader.onLoad();//����ͼƬ��Դ
			AssetManager am=Global.GetContext().getAssets();
			InputStream input=am.open("monst.swx");

			int count=getValFromBytes(input);   
			getValFromBytes(input);
            for(int j=0;j<count;j++)
            {    	
            	int id=getValFromBytes(input);
            	int grh_id=getValFromBytes(input);
            	int level=getValFromBytes(input);
            	int hp=getValFromBytes(input);
            	int mp=getValFromBytes(input);
            	int min_attack=getValFromBytes(input);
            	int max_attack=getValFromBytes(input);
            	int defense=getValFromBytes(input);
            	int double_attack=getValFromBytes(input);
            	int dodge=getValFromBytes(input);
            	int prize_exp=getValFromBytes(input);
            	int goods_num=getValFromBytes(input);
            	Monst monst=new Monst(id,grh_id);
            	for(int i=0;i<goods_num;i++)
            	{
            		int localID=getValFromBytes(input);
            		int min_drop_num=getValFromBytes(input);
            		int max_drop_num=getValFromBytes(input);
            		MonstDropGoodsInfo info=new MonstDropGoodsInfo(localID, min_drop_num, max_drop_num);
            		monst.getmDropGoodsList().add(info);
            	}
            	
            	monst.setmLevel(level);
            	monst.setmMaxHp(hp);
            	monst.setmMaxMp(mp);
            	monst.setmMinAttack(min_attack);
            	monst.setmMaxAttack(max_attack);
            	monst.setmDefense(defense);
            	monst.setmDoubleAttackRate(double_attack);
            	monst.setmDodgeRate(dodge);
            	monst.setPrizeExp(prize_exp);
            	

            	mMonstTable.put(id, monst);         	
            }
            
		}
		catch(Exception e)
		{
			Debug.w(e.getMessage());
		}
	}
	
	private static int getValFromBytes(InputStream input)
	{
		try{
		  byte [] bVal=new byte[4];
		  int readCount=0;//�Ѿ��ɹ���ȡ���ֽ���		  
		  while(readCount<4)
		    readCount+=input.read(bVal,readCount,4-readCount);
		 
 		  return Tools.ConvertByteArrayToInt(bVal);
		}
		catch(Exception e)
		{
		  return 0;
		}		
	}
	
	
}
