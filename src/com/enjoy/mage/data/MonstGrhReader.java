package com.enjoy.mage.data;
import java.io.InputStream;
import java.util.HashMap;

import org.anddev.andengine.util.Debug;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.common.Tools;
import com.enjoy.mage.graphics.MonstGrh;
import com.enjoy.mage.graphics.MonstPara;

/**
 * @author shaoleibo
 * ����ͼƬ��Դ����
 */
public class MonstGrhReader {
	
	//ͼ��ID�Լ�ͼ����Դ����
	public static HashMap<Integer,MonstGrh> mMonstGrh=new HashMap<Integer,MonstGrh>();
	
	public static MonstGrh getGrh(int id)
	{
		return mMonstGrh.get(id);
	}
	
	//���ع�����Դ�ĸ���
	public static int getCount()
	{
		return mMonstGrh.size();
	}
	
	public static void onLoad()
	{
		try
		{
			AssetManager am=Global.GetContext().getAssets();
	        InputStream input=am.open("monstgrh.xml");
	        XmlPullParserFactory xppf=XmlPullParserFactory.newInstance();
	        xppf.setNamespaceAware(true);
	        XmlPullParser xrp=xppf.newPullParser();
	        xrp.setInput(input,"utf-8");   
		
			while(xrp.getEventType()!=XmlResourceParser.END_DOCUMENT)
			{
					if(xrp.getEventType()==XmlResourceParser.START_TAG)
					{
						String tagName=xrp.getName();
						if(tagName.equals("monst"))
						{
							int id=Tools.GetIntVal(xrp,"id"); //ͼ����ԴID
							int stand_id=Tools.GetIntVal(xrp,"stand_id");
							String run_file=xrp.getAttributeValue(null, "runfile");
							String attack_file=xrp.getAttributeValue(null,"attackfile");
							int run_row=Tools.GetIntVal(xrp,"runrow");
							int run_cow=Tools.GetIntVal(xrp,"runcol");
							int attack_row=Tools.GetIntVal(xrp,"attackrow");
							int attack_col=Tools.GetIntVal(xrp, "attackcol");
							int run_tile_w=Tools.GetIntVal(xrp,"run_tile_w");
							int run_tile_h=Tools.GetIntVal(xrp, "run_tile_h");
							int attack_tile_w=Tools.GetIntVal(xrp,"attack_tile_w");
							int attack_tile_h=Tools.GetIntVal(xrp, "attack_tile_h");
							int stand_offset_x=Tools.GetIntVal(xrp, "stand_offset_x");
							int stand_offset_y=Tools.GetIntVal(xrp, "stand_offset_y");
							int run_offset_x=Tools.GetIntVal(xrp, "run_offset_x");
							int run_offset_y=Tools.GetIntVal(xrp, "run_offset_y");
							int attack_offset_x=Tools.GetIntVal(xrp, "attack_offset_x");
							int attack_offset_y=Tools.GetIntVal(xrp,"attack_offset_y");
					        MonstPara mp=new MonstPara();
							mp.mAttackCol=attack_col;
							mp.mAttackRow=attack_row;

							mp.mAttackTileHeight=attack_tile_h;
							mp.mAttackTileWidth=attack_tile_w;
	
							mp.mRunCol=run_cow;
							mp.mRunRow=run_row;

							mp.mRunTileHeight=run_tile_h;
							mp.mRunTileWidth=run_tile_w;
							
							mp.mStandOffsetX=stand_offset_x;
							mp.mStandOffsetY=stand_offset_y;
							mp.mRunOffsetX=run_offset_x;
							mp.mRunOffsetY=run_offset_y;
							mp.mAttackOffsetX=attack_offset_x;
							mp.mAttackOffsetY=attack_offset_y;
														
							MonstGrh grh=new MonstGrh(stand_id,run_file,attack_file,mp);
							grh.onLoadResources();
							mMonstGrh.put(id,grh);							
						}						
					}
				    xrp.next();
			}
		}
		catch(Exception e)
		{
			Debug.w("monst�����쳣:"+e.toString());
		}
	}
}
