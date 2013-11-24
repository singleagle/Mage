package com.enjoy.mage.data;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.res.AssetManager;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.common.Tools;



/**
 * @author shaoleibo
 * ��ȡ��TASK.XML
 */
public class TasksReader {

	static private ArrayList<Task> mTaskList=new ArrayList<Task>(); //NPC ID ������Ĺ�����
	
	static public void onLoad()
	{
		try
		{  	
			Task localTask = null;
			AssetManager am=Global.GetContext().getAssets();
            InputStream input=am.open("task.xml");
            XmlPullParserFactory xppf=XmlPullParserFactory.newInstance();
            xppf.setNamespaceAware(true);
            XmlPullParser xrp=xppf.newPullParser();
            xrp.setInput(input,"utf-8");     
			while(xrp.getEventType()!=XmlPullParser.END_DOCUMENT)
			{
				if(xrp.getEventType()==XmlPullParser.START_TAG)
				{
				    String tagName=xrp.getName();
				  
				    if(tagName.equals("task"))
				    {
					    localTask=new Task();
					    int id=Tools.GetIntVal(xrp, "id");
					    int npcid=Tools.GetIntVal(xrp, "npcid");
					    int reqtask=Tools.GetIntVal(xrp, "reqtask");
					    int type=Tools.GetIntVal(xrp, "type");
					    
					    localTask.setmID(id);
					    localTask.setmNPCID(npcid);
					    localTask.setmRequiredTaskID(reqtask);
					    localTask.setmType(type);
					    mTaskList.add(localTask);
					    
				    }
				    else if(localTask!=null)
				    {
				    	if(tagName.equals("desc"))
				    	{
				    	  String desc=xrp.nextText();
				    	  localTask.setmDesc(desc);	//setһ��Ҫע��
				    	}
				    	else if(tagName.equals("inprogressdesc"))
				    	{
				    	   String inProgerssDesc=xrp.nextText();
				    	   localTask.setmInProgerssDesc(inProgerssDesc);
				    	}
				    	else if(tagName.equals("reqitem"))
				    	{
				    		int reqitem=Integer.parseInt(xrp.nextText());
				    		localTask.putReqItem(reqitem);
				    	}
				    	else if(tagName.equals("reply"))
				    	{
				    		String reply=xrp.nextText();
				    		localTask.putReply(reply);
				    	}
				    }
				}
				xrp.next();
			}
		}
		catch(Exception e)
		{
			
		}
	}

	public static ArrayList<Task> getGlobalTaskList() {
		return mTaskList;
	}


    public static Task getInitTask(int npcid)
    {   	
    	Task localTask = null;
    	for(Task t:mTaskList)
    	{
    		//Debug.w("task:"+t.getmNPCID());
    		if(t.getmNPCID()==npcid)
    		{
    			localTask=t;
    			break;
    		}
    	}
    	return localTask;
    }
}
