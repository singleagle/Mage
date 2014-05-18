  package com.enjoy.mage.data;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.graphics.Point;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.common.Tools;
import com.enjoy.mage.entity.Monst;
import com.enjoy.mage.entity.NPC;
import com.enjoy.mage.entity.Switch;
import com.enjoy.mage.graphics.SceneTextGrh;


public class ScenesReader {
	static private ArrayList<String> mFileList=new ArrayList<String>();	
	static private ArrayList<SceneInfo> mSceneInfos=new ArrayList<SceneInfo>();
	public static ArrayList<SceneInfo> getSceneInfos() {
		return mSceneInfos;
	}

	static private void fillFileList()
	{		
		try
		{
			AssetManager am=Global.GetContext().getAssets();
	        InputStream input=am.open("scenes.xml");
	        XmlPullParserFactory xppf=XmlPullParserFactory.newInstance();
	        xppf.setNamespaceAware(true);
	        XmlPullParser xrp=xppf.newPullParser();
	        xrp.setInput(input,"utf-8");   
		
			while(xrp.getEventType()!=XmlResourceParser.END_DOCUMENT)
			{
				if(xrp.getEventType()==XmlResourceParser.START_TAG)
				{
					String tagName=xrp.getName();
			
					if(tagName.equals("scene"))
					{
						String fileName=xrp.getAttributeValue(null, "cfgfile");
						
                        mFileList.add(fileName);
					}
				}
				xrp.next();
			}			
		}
		catch(Exception e)
		{
			
		}
	}
	
	static private void onLoad(String pFileName)
	{
	    SceneInfo sceneInfo=new SceneInfo();
		try
		{  			
			AssetManager am=Global.GetContext().getAssets();
            InputStream input=am.open(pFileName);
            XmlPullParserFactory xppf=XmlPullParserFactory.newInstance();
            xppf.setNamespaceAware(true);
            XmlPullParser xrp=xppf.newPullParser();
            xrp.setInput(input,"utf-8");     
			while(xrp.getEventType()!=XmlPullParser.END_DOCUMENT)
			{
				if(xrp.getEventType()==XmlPullParser.START_TAG)
				{
				    String tagName=xrp.getName();
					if(tagName.equals("scene"))
					{			
						int id=Integer.parseInt(xrp.getAttributeValue(null, "id"));
						String name=xrp.getAttributeValue(null,"name");
						String desc=xrp.getAttributeValue(null,"desc");
						boolean bpk=Tools.StringToBool(xrp.getAttributeValue(null,"pk"));
						int type=Tools.GetIntVal(xrp, "type");
						sceneInfo.setId(id);
						sceneInfo.setmName(name);
						sceneInfo.setDesc(desc);
						sceneInfo.setCanPK(bpk);
						sceneInfo.setType(type);
					}
					else if(tagName.equals("map"))
					{
						int imageId=Integer.parseInt(xrp.getAttributeValue(null,"fileid"));
						sceneInfo.setMapImageId(imageId);
					}
					else if(tagName.equals("door"))
					{
						int x=Integer.parseInt(xrp.getAttributeValue(null, "x"));
						int y=Integer.parseInt(xrp.getAttributeValue(null, "y"));
						int toscene=Integer.parseInt(xrp.getAttributeValue(null, "toscene"));
						int tox=Integer.parseInt(xrp.getAttributeValue(null, "tox"));
						int toy=Integer.parseInt(xrp.getAttributeValue(null,"toy"));
						String title=xrp.getAttributeValue(null, "title");
						int imageid=Tools.GetIntVal(xrp,"imageid");
						
						SceneTextGrh text=new SceneTextGrh(20,Color.YELLOW);
						text.onLoadResource();
						Switch localDoor=new Switch(x,y,toscene,tox,toy,title,imageid);
						localDoor.setmTextGrh(text);
						sceneInfo.pushDoor(localDoor);				
					}
					else if(tagName.equals("layout"))
					{
						int type=Tools.GetIntVal(xrp, "type");
						int x=Tools.GetIntVal(xrp, "posx");
						int y=Tools.GetIntVal(xrp,"posy");
						LayoutInfo layout=new LayoutInfo(type,x,y);
						sceneInfo.setLayout(layout);
					}
					else if(tagName.equals("mark"))
					{
						
					}
					else if(tagName.equals("npc"))
					{
						int mapId=Tools.GetIntVal(xrp, "id");
						int mapImgId=Tools.GetIntVal(xrp, "imageid");
						int mapPosX=Tools.GetIntVal(xrp, "x");
						int mapPosY=Tools.GetIntVal(xrp, "y");
						String mapName=xrp.getAttributeValue(null,"name");
						
						int npcType=Tools.GetIntVal(xrp,"type");
						
						NPC localNpc=new NPC(new Point(mapPosX,mapPosY),mapId,mapImgId,mapName,npcType);
						sceneInfo.pushNpc(localNpc);
					}
					else if(tagName.equals("monst"))
					{
						int monstID=Tools.GetIntVal(xrp, "id");
						Monst localMonst=MonstReader.GetMonst(monstID); 
						sceneInfo.addMonst(localMonst);                  
					}
					else
					{
						
					}
				}
				xrp.next();
			}
			
			mSceneInfos.add(sceneInfo);
		}
		catch(Exception e)
		{
			//Debug.w("�����쳣"+e.getLocalizedMessage());
		}
	}
	
	
	static public void onLoad()
	{
		fillFileList();
		for(String file:mFileList)
		{
			onLoad(file);
		}
	}
}
