package com.maxted.DEMO1; 
import com.developerlife.Utils.*; 
import android.os.Build;
import android.app.Activity; 
import android.app.ProgressDialog;
import android.content.Context; 
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.os.Bundle; 
import java.net.*; 
import java.util.*; 
import java.io.*; 
import android.widget.*; 

import java.text.*;
import android.view.*; 
import android.content.Intent; 
import android.widget.LinearLayout.LayoutParams; 
import android.graphics.Color; 
import android.graphics.PixelFormat;
import flashbox.ANDROID.*;
import android.graphics.drawable.Drawable;
import android.hardware.SensorManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.Class;
import android.database.*;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.protocol.HTTP;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import android.app.AlertDialog;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
public class MAIN extends  flashbox.ANDROID.DELEGATE
{
	public String plistUrl=null;
	public String entityUrl=null;
	public String xmlData=null;
	public String parameter=null;
	public String returnData="";
	public Hashtable customAction=null;
	public static final byte[] secureKey = {'m','o','b','i','l','e','t','k','l','i','g','a','d','m','i','n'}; 

	public String filterString(String option, String value, Hashtable record) {
		try{
			/*
			if(value!=null&&value.length()>6){
				value=MANAGER.replaceStr(value,"-","");
		           		return  value.substring(0, 6)+"-" +value.substring(6);
			}else{
				return value;
			}
			*/
			return value;
		}catch(Exception t){
			return value+":"+t.toString();
		}
	}
	@Override
	public String testSwitch(Hashtable param){
		return "sampleAction";
	}	




	@Override
	public Hashtable getCustomData(Hashtable dataProvider) throws Exception {
		String buffer="";
		buffer+="<plist>";
			buffer+="<dict>";
				buffer+="<key>subfolders</key>";
				buffer+="<array>";
					buffer+="<dict>";
						buffer+="<key>title</key><string>FOLDER1</string>";
						buffer+="<key>subfolders</key>";
						buffer+="<array>";
							buffer+="<dict>";
								buffer+="<key>title</key><string>Select 1</string>";
							buffer+="</dict>";
							buffer+="<dict>";
								buffer+="<key>title</key><string>Select 2</string>";
							buffer+="</dict>";
						buffer+="</array>";
					buffer+="</dict>";
					buffer+="<dict>";
						buffer+="<key>title</key><string>FOLDER2</string>";
						buffer+="<key>subfolders</key>";
						buffer+="<array>";
							buffer+="<dict>";
								buffer+="<key>title</key><string>Select 3</string>";
							buffer+="</dict>";
							buffer+="<dict>";
								buffer+="<key>title</key><string>Select 4</string>";
							buffer+="</dict>";
							buffer+="<dict>";
								buffer+="<key>title</key><string>Select 5</string>";
							buffer+="</dict>";
							
							buffer+="<dict>";
								buffer+="<key>title</key><string>more</string>";
								buffer+="<key>_action</key><string>moreAction</string>";
							buffer+="</dict>";
						buffer+="</array>";
					buffer+="</dict>";
				buffer+="</array>";
			buffer+="</dict>";
		buffer+="</plist>";
		XMLPROP xmlProp=new XMLPROP(new ByteArrayInputStream(buffer.getBytes()));
		Hashtable plist = xmlProp.getPlist();
		return plist;	
	}

	public String postToUrl(String addr,String data)
	{
		try{
			int i=0; 
			URL url = new URL(addr); 
			URLConnection conn = url.openConnection(); 
			conn.setDoOutput(true); 
			conn.setRequestProperty("Content-Type", "text/xml");
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream()); 
			wr.write(data); 
			wr.flush(); 
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
			String line; 
			StringBuffer buffer=new StringBuffer(2048); 
			while((line=rd.readLine())!=null){
				buffer.append(line); 
			}
			rd.close(); 
			wr.close(); 
			System.out.println("RECEIVE "+buffer.toString());
			return buffer.toString(); 
		}catch(Exception e){
			System.out.println("postToUrl error "+e.toString());
			return null; 
		}
	}

	public   class loadData implements Runnable{
		String dataUrl;
		String reqData;
		AlertDialog alert;
		Hashtable dataProvider;
		DELEGATE delegate;
		public Hashtable plist=null;

		public loadData(DELEGATE delegate,AlertDialog alert,String url,String reqData,Hashtable dataProvider){
			this.reqData=reqData;
			dataUrl=url;
			this.alert=alert;
			this.delegate=delegate;
			this.dataProvider=dataProvider;
		}
		public void run() {
		    try {
			long time=System.currentTimeMillis();
		            try {
			      String ret=postToUrl(dataUrl,reqData);
			      XMLPROP xmlProp=new XMLPROP(new ByteArrayInputStream(ret.getBytes()));
			      plist = xmlProp.getPlist();
			      Message msg=Message.obtain();
			      Hashtable param=new Hashtable();
			      param.put("plist",plist);		
			      param.put("dataProvider",dataProvider);		
			      msg.what=0;
			      msg.obj=param;
			      mHandler.sendMessage(msg);							
			}catch(Exception t){
				System.out.println("loadData run0 "+t.toString());
			}
		    } catch (Exception iex) {
			System.out.println("loadData run1 "+iex.toString());
		    }
		}
	}

	Handler mHandler=new Handler(){
		public void handleMessage(Message msg){
			if(msg.what==0){
			      try{
				      Hashtable param=(Hashtable)msg.obj;	
				      processXmlData((Hashtable)param.get("plist"),(Hashtable)param.get("dataProvider"));	
			      }catch(Exception t){
				      System.out.println("Handle Error :"+t.toString());  	
			      }
			}
		}
	};
	
	ProgressDialog alert;
	int alertCnt=0;

	public boolean requestWillStart(){
		alertCnt++;

		if(alert==null){
						alert=new ProgressDialog(this);
						alert.setTitle("Loading...");
						alert.setMessage("�����͸� �ҷ����� �ֽ��ϴ�.");
						alert.show();
						alert.setCancelable(false);
		}		
		return true;
	}

	@Override
	public boolean afterRequest(){
		alertCnt--;
		if (alertCnt <= 0) {
			if (alert != null){
				alert.dismiss();
			}
			alert = null;
		}
		return true;
	}

	@Override
     public void loadXmlData(String url,Hashtable params,Hashtable dataProvider)throws Exception
     {
     requestWillStart();
     String code=(String)params.get("code");
     if(code!=null&&code.equals("test")){
     }else if(code!=null&&code.equals("loadData")){
     String buffer="<plist><dict>";
     buffer+="<key>tabledata</key>";
     buffer+="<array>";
     buffer+="<dict>";
     buffer+="<key>title</key><string>title1</string>";
     buffer+="</dict>";
     buffer+="<dict>";
     buffer+="<key>title</key><string>title2</string>";
     buffer+="</dict>";
     buffer+="<dict>";
     buffer+="<key>title</key><string>title3</string>";
     buffer+="</dict>";
     buffer+="<dict>";
     buffer+="<key>title</key><string>title4</string>";
     buffer+="</dict>";
     buffer+="<dict>";
     buffer+="<key>title</key><string>title5</string>";
     buffer+="</dict>";
     buffer+="</array>";
     buffer+="</dict></plist>";
     XMLPROP xmlProp=new XMLPROP(new ByteArrayInputStream(buffer.getBytes()));
     Hashtable plist = xmlProp.getPlist();
     MANAGER.log("loadXmlData "+plist);
     processXmlData(plist,dataProvider);	
     }else{
     }
     }
     
     
     
     
     


	String makeParameter2(Hashtable nsdict)throws Exception
	{
		return makeParameter(nsdict);
	}

	/*
	public View getCustomView(Context act,Hashtable dict,Hashtable record,PROTO parent){
		try{
			String className=(String)dict.get("class");
			if(className!=null&&className.equals("flashbox.ANDROID.TouchSurface")){
				//TouchSurface surface=new TouchSurface(this);
				//surface.setBackgroundColor(MANAGER.getColor("1,0,0,0.3"));
				//surface.setEGLConfigChooser(8,8,8,8,16,0);map
				//surface.setZOrderOnTop(true);
				    
				return surface;
			}else if(className!=null&&className.equals("flashbox.ANDROID.SimulationView")){
				SimulationView surface=new SimulationView(this);
				surface.startSimulation();
				return surface;
			}else{
				return null;
			}
		}catch(Exception t){
			return null;
		}

	}
	*/
	

 	public void onWindowFocusChanged(boolean hasFocus)	{
 		MANAGER.log("FOCUS CHANGED");
 	}

	
	public void onCreate(Bundle savedInstanceState)
	{
        // Example of How to set global variables manually.
		
		setObjectValue("global.system.modelName",""+Build.MODEL);
		setObjectValue("global.system.deviceName",""+Build.DEVICE);
		setObjectValue("global.system.ProductName",Build.PRODUCT);
		setObjectValue("global.system.VersionRelase",""+Build.VERSION.RELEASE);
        
		unlockCode="0000000";	
		projectName="DUNAMIS_ANDROID";	
		//platformVersion="1.0.0.1";
		Display display = ((WindowManager) this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		
		//MANAGER.scaleLocation=0;
		MANAGER.deviceWidth=width;
		MANAGER.deviceHeight=height;		
		MANAGER.buttonLockTime=0.3f;
		MANAGER.useVirtualActivity=true;
		MANAGER.vWidth=320;
		MANAGER.vHeight=480;
		MANAGER.controllerCaching=false;
		MANAGER.listMemManager=false;	
		MANAGER.onDidLoadDelay=0;
		MANAGER.activityDuration=300;
		MANAGER.pushLeftIn=R.anim.push_left_in;
		MANAGER.pushLeftOut=R.anim.push_left_out;
		MANAGER.pushRightIn=R.anim.push_right_in;
		MANAGER.pushRightOut=R.anim.push_right_out;
		MANAGER.debug=10000;
		super.onCreate(savedInstanceState); 
		try{
			//MANAGER.mapActivity=this;
			setContentView(R.layout.main);
			Bundle extras = getIntent().getExtras(); 
			Hashtable plist=null;
			plistUrl="first.xml";
			System.gc();
			if(plistUrl!=null){
				View view=makeNavigator(plistUrl);
				if(view!=null){
					setContentView(view); 
				}else{
					addContentView(MANAGER._createInfo(this,"View not defined:"+plistUrl),new LayoutParams(
							LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)); 
				}
			}else{
				setContentView(MANAGER._createInfo(this,"ERROR:")); 
			}
		}catch(Exception t){
			setContentView(MANAGER._createInfo(this,"ERROR:"+t.toString())); 
		}
	}


	@Override
	public void onBackPressed() {
		if(rootNavigator.goBack()){
			System.out.println("BACK BUTTON PRESSED");
		}else{
			super.onBackPressed();
			MANAGER.exit();
		}
	}
}




