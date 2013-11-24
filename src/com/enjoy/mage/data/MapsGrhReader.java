package com.enjoy.mage.data;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.common.Tools;
//���������Ӧ��XML ���ǲ����SPRITE
public class MapsGrhReader {
	private static ArrayList<MapGrhInfo> mArray=new ArrayList<MapGrhInfo>();
	public static ArrayList<MapGrhInfo> getFiles() {
		return mArray;
	}
	public static void onLoad()
	{
		try
		{			
		AssetManager am=Global.GetContext().getAssets();
        InputStream input=am.open("map.xml");
        XmlPullParserFactory xppf=XmlPullParserFactory.newInstance();
        xppf.setNamespaceAware(true);
        XmlPullParser xrp=xppf.newPullParser();
        xrp.setInput(input,"utf-8");   
	
			while(xrp.getEventType()!=XmlResourceParser.END_DOCUMENT)
			{
				if(xrp.getEventType()==XmlResourceParser.START_TAG)
				{
					String tagName=xrp.getName();
					if(tagName.equals("file"))
					{
						int id=Tools.GetIntVal(xrp,"id");
						String descName=xrp.getAttributeValue(null,"descname");
						String fileName=xrp.getAttributeValue(null, "filename");
						MapGrhInfo info=new MapGrhInfo(id,descName,fileName);
						mArray.add(info);
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
