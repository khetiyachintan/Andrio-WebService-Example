package chintan.khetiya.android.webservice;

import java.util.ArrayList;

import org.apache.http.NameValuePair;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class Background_Task extends AsyncTask<String, String, String> {

    public Activity activity;
    Context context;
    public AsyncTaskCompleteListener callback;
    ArrayList<NameValuePair> URL_Params = new ArrayList<NameValuePair>();
    Function fun = new Function();
    ProgressDialog Loader_Dialog;
    Custom_alert_DialogClass cad;

    public Background_Task(ArrayList<NameValuePair> URL_Params, Activity act) {
	this.URL_Params = URL_Params;

	this.activity = act;
	this.callback = (AsyncTaskCompleteListener) act;
    }

    @Override
    protected void onPreExecute() {
	super.onPreExecute();
	try {

	    Log.i("onPre", "call");
	} catch (Exception e) {
	    // TODO: handle exception
	    Log.e("onPre", "" + e);
	}

    }

    @Override
    protected String doInBackground(String... web_url) {
	try {
	    // this will send req in post
	    // here [0] mean URL & passing params
	    Log.i("onDO", "call");

	    fun.Send_Simple_Detail_To_Server(web_url[0], URL_Params);

	    Log.i("onDO", "SEND");

	    // this will get stream response
	    fun.Buffer_Response();
	    Log.i("onDO", "GET RES");

	} catch (Exception e) {
	    // TODO: handle exception
	    Log.e("onDo", "" + e);
	}

	return fun.Buffer_String_Response;
    }

    @Override
    protected void onPostExecute(String result) {
	super.onPostExecute(result);
	// check is dialog open ? THEN HIDE DIALOG
	try {
	    Log.i("onPOST", "DONE");
	    Log.i("onPOST", "" + result);

	    callback.onTaskComplete(result);
	} catch (Exception e) {
	    Log.e("onPost", "" + e);
	    // TODO: handle exception
	}

    }

}
