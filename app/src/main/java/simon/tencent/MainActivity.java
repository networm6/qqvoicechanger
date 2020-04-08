package simon.tencent;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends Activity 
{
	xfhelp xf;
	LinearLayout vp;
	Button but;
	EditText ed,ed2;
	String mpath,old;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		    setContentView(R.layout.main);
		xf=xfhelp.INTERFACE.incon(this);
			xf.in(R.layout.xf);
		vp=(LinearLayout) xf.getlayout();
			but=vp.findViewById(R.id.xfButton1);
		ed=findViewById(R.id.mainEditText1);
		ed2=findViewById(R.id.mainEditText2);
		but.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					Calendar calendar = Calendar.getInstance();
					int year = calendar.get(Calendar.YEAR);
					int month = calendar.get(Calendar.MONTH)+1;
					String nn;
					if(month<10)
						nn="0"+month;
						else
						nn=""+month;
					int data = calendar.get(Calendar.DATE);
					old="/storage/emulated/0/Android/data/com.tencent.mobileqq/Tencent/MobileQQ/"+ed2.getText().toString()+"/ptt/"+year+""+nn+"/"+data;
					//历遍路径
					mpath=ed.getText().toString();//新文件路径
					File path=new File(old);
					
					if (path.isFile()){
						Toast.makeText(MainActivity.this,old,10).show();
						return;
					}
						
					//Toast.makeText(MainActivity.this,path.getName(),10).show();
					File[] allfile= path.listFiles();
					
					if(allfile==null)
					Toast.makeText(MainActivity.this,"e",10).show();
					

					int[] arr=new int[allfile.length];
					for (int i=0;i < allfile.length;i++) {
						arr[i]=getModifiedTime_2(allfile[i]);
						// System.out.println(arr[i]+"e");
					}
					int max=arr[0];//将数组的第一个元素赋给max
					int min=arr[0];//将数组的第一个元素赋给min
					int minx = 0;
					for(int i=1;i<arr.length;i++){//从数组的第二个元素开始赋值，依次比较
						if(arr[i]>max){//如果arr[i]大于最大值，就将arr[i]赋给最大值
							max=arr[i];
						}
						if(arr[i]<min){//如果arr[i]小于最小值，就将arr[i]赋给最小值
							min=arr[i];
							minx=i;
						}
					}
					String newname= allfile[minx].getName();//拿到文件名
					String oldpath=allfile[minx].getAbsolutePath() ;//原文件路径
				   allfile[minx].delete();//删除原文件
				
					Toast.makeText(MainActivity.this,oldpath,10).show();
					
					//copyFile("","");
					
			    File a=new File(mpath);
					File b=new File(oldpath);
					try
					{
						copyFileUsingFileStreams(a, b);
					}
					catch (IOException e)
					{
						Toast.makeText(MainActivity.this,e.getMessage(),10).show();
					}
				}
			});
    }
	public void open(View v){
		if(xf.ishow)
		   xf.hide();
		else{
			   xf.show();
		   }
	}
	public static int  getModifiedTime_2(File in){             
		Calendar cal = Calendar.getInstance();  
        long time = in.lastModified();  
        SimpleDateFormat formatter = new SimpleDateFormat(" M d H m ss");         
		// SimpleDateFormat formatter = new SimpleDateFormat("yyyy M d H m ss");         
		cal.setTimeInMillis(time);    
		String filetime=formatter.format(cal.getTime()).replaceAll(" ","");
        System.out.println(filetime);
        Calendar calendar = Calendar.getInstance();
		//int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int data = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		String min;
		if(minute<10)
			min="0"+minute;
		else
			min=""+minute;
		int second = calendar.get(Calendar.SECOND);
		String all=""+month+""+data+""+hour+""+min+""+second;
		//String all=year+""+month+""+data+""+hour+""+min+""+second;
		System.out.println(all);
		int allint=Integer.parseInt(all);
		int fileint=Integer.parseInt(filetime);
		return allint-fileint;
    }  
	
	private static void copyFileUsingFileStreams(File source, File dest) 
	throws IOException { 
		InputStream input = null; 
		OutputStream output = null; 
		try { 
			input = new FileInputStream(source); 
			output = new FileOutputStream(dest); 
			byte[] buf = new byte[1024]; 
			int bytesRead; 
			while ((bytesRead = input.read(buf)) > 0) { 
				output.write(buf, 0, bytesRead); 
			} 
		} finally { 
			input.close(); 
			output.close(); 
		} 
	}
	
}
