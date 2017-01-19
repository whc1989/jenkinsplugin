package com.emqc.jenkins.emqcplugin;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class HttpPostUploadUtil {
	   public static void main(String[] args){
		   JSONObject sr=HttpPostUploadUtil.initClass("http://localhost:8080/emqc-platform/ios_uploadapk.action","23165A8C-20F7-49A7-963B-95FA55FF1C34","F:/dfcft_inhouse_23063-resigned.ipa");
		   System.out.println(sr.get("appName"));
	   }

	    public static JSONObject initClass(String url, String param,String path){
	        //1:创建一个httpclient对象
	        HttpClient httpclient = new DefaultHttpClient();
	        Charset charset = Charset.forName("UTF-8");//设置编码
	        try {
	            //2：创建http的发送方式对象，是GET还是post
	            HttpPost httppost = new HttpPost(url);

	            //3：创建要发送的实体，就是key-value的这种结构，借助于这个类，可以实现文件和参数同时上传，很简单的。
	            MultipartEntity reqEntity = new MultipartEntity();

	            FileBody bin = new FileBody(new File(path));
	            //FileBody bin1 = new FileBody(new File("C:/Users/kin.liufu.2GOTECH/Desktop/资料/Go.XML Message Protocol Specification (V2.88h).doc"));
	            StringBody comment = new StringBody(param,charset);
	            ArrayList<FileBody> fileBodys = new ArrayList<>();
	            fileBodys.add(bin);
	            //fileBodys.add(bin1);

	            addFileBodyPart("myFile", fileBodys, reqEntity);
	            reqEntity.addPart("uuid", comment);
	            httppost.setEntity(reqEntity);

	            //4：执行httppost对象，从而获得信息
	            HttpResponse response = httpclient.execute(httppost);
	            HttpEntity resEntity = response.getEntity();

	            //获得返回来的信息，转化为字符串string
	            String resString = EntityUtils.toString(resEntity);
	            System.out.println(resString);
	            return new JSONObject(resString);

	        } catch (UnsupportedEncodingException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            return null;
	        } catch (IllegalStateException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            return null;
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            return null;
	        } finally {
	            try { httpclient.getConnectionManager().shutdown(); } catch (Exception ignore) {}
	        }
	    }

	    //当多个文件需要上传时，对文件进行组装
	    public static void addFileBodyPart(String paramName, ArrayList<FileBody> fileBodys, MultipartEntity reqEntity){
	        if(fileBodys == null || fileBodys.size() < 1 || reqEntity == null || paramName == null){
	            return;
	        }
	        for(FileBody iteam : fileBodys){
	            reqEntity.addPart(paramName,iteam);
	        }
	    }
}
