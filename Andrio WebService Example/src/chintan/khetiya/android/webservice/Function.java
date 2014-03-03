/**
 * Function.java
 * Web service Example
 * Created On Jan 1, 2013
 * Created By chintan Khetiya
 * Copyrights 2013. chintan khetiya
 * All Rights Reserved.
 */
package chintan.khetiya.android.webservice;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Function.java
 * 
 * @author User1
 */
public class Function {
    public ConnectivityManager cm;
    public NetworkInfo ni;
    public InputStream is;
    Context ctx;
    JSONObject jObjec = new JSONObject();
    String Buffer_String_Response = null;
    Custom_alert_DialogClass cad;
    public ArrayList<NameValuePair> URL_Params = new ArrayList<NameValuePair>();
    String Alert_Msg;
    long Dialog_Time_Limit = 3000;

    public Function(Context context) {
	this.ctx = context;
    }

    public Function() {

    }

    public void Send_JSON_Encode_Detail_To_Server(String URL, JSONObject jobj) {
	try {

	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost(URL);
	    httppost.setHeader("Content-type", "application/json");
	    StringEntity se = new StringEntity(jobj.toString());
	    se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
		    "application/json"));
	    httppost.setEntity(se);
	    HttpResponse response = httpclient.execute(httppost);
	    String temp = EntityUtils.toString(response.getEntity());
	    Log.i("tag", temp);

	} catch (ConnectTimeoutException e) {
	    Log.e("Timeout Exception: ", e.toString());

	} catch (SocketTimeoutException ste) {
	    Log.e("Timeout Exception: ", ste.toString());

	} catch (Exception e) {
	    Log.e("HTTP_Execption", "Error in http connection " + e.toString());

	}
    }

    public void Send_Simple_Detail_To_Server(String URL,
	    ArrayList<NameValuePair> nameValuePairs)

    {
	try {

	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost(URL);
	    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	    HttpResponse response = httpclient.execute(httppost);
	    HttpEntity entity = response.getEntity();

	    is = entity.getContent();

	} catch (ConnectTimeoutException e) {
	    Log.e("Timeout Exception: ", e.toString());

	} catch (SocketTimeoutException ste) {
	    Log.e("Timeout Exception: ", ste.toString());

	} catch (Exception e) {
	    Log.e("HTTP_Execption", "Error in http connection " + e.toString());

	}
    }

    public void Buffer_Response() {
	try {
	    Buffer_String_Response = null;
	    BufferedReader reader = new BufferedReader(new InputStreamReader(
		    is, "iso-8859-1"), 8);
	    StringBuilder sb = new StringBuilder();
	    String line = null;
	    while ((line = reader.readLine()) != null) {
		sb.append(line + "\n");
	    }
	    is.close();
	    Buffer_String_Response = sb.toString();

	} catch (Exception e) {
	    Log.e("Loading Runnable Error converting result :", e.toString());

	}

    }

    public void Show_Loader(Activity a, String Text_Message) {

	cad = new Custom_alert_DialogClass(a, Text_Message);
	cad.show();
    }

    public void Hide_Loader()

    {
	if (cad != null && cad.isShowing()) {
	    cad.dismiss();
	}

    }

}
