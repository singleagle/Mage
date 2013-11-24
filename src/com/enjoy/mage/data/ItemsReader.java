package com.enjoy.mage.data;
import java.io.InputStream;
import java.util.Hashtable;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.common.Tools;
import com.enjoy.mage.entity.ItemEntity;

import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;

//���������Ӧ����ƷͼƬ�б�
public class ItemsReader {	
	public static Hashtable<Integer,ItemEntity> mItemsTable=new Hashtable<Integer, ItemEntity>();
		
	public static void onLoad()
	{
		try
		{
			AssetManager am=Global.GetContext().getAssets();
			InputStream input=am.open("items.xml");
			XmlPullParserFactory xppf=XmlPullParserFactory.newInstance();
			xppf.setNamespaceAware(true);
			XmlPullParser xrp=xppf.newPullParser();
			xrp.setInput(input,"utf-8");  
			while(xrp.getEventType()!=XmlResourceParser.END_DOCUMENT)
			{
				if(xrp.getEventType()==XmlResourceParser.START_TAG)
				{
					String tagName=xrp.getName();
					if(tagName.equals("item"))
					{
						int id=Tools.GetIntVal(xrp, "id");
						String name=xrp.getAttributeValue(null,"name");
						int minIcon=Tools.GetIntVal(xrp, "minicon");
						int maxIcon=Tools.GetIntVal(xrp, "maxicon");
						String desc=xrp.getAttributeValue(null,"desc");
						
						ItemEntity item=new ItemEntity(id, name, desc, minIcon, maxIcon,false);
						mItemsTable.put(id, item);
					}
				}
				xrp.next();
			}			
		}
		catch(Exception e)
		{
			
		}
	}
}
