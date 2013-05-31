package chintan.khetiya.android.webservice;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Android_WebService extends Activity implements
	AsyncTaskCompleteListener {

    Function fun;
    Background_Task bTask;
    Button call_ws;
    String User_Name = null;
    String TAG = "Android_WebService_Error";
    ArrayList<String> User_Id = new ArrayList<String>();
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	Set_View();

	call_ws.setOnClickListener(new View.OnClickListener() {

	    @Override
	    public void onClick(View v) {
		try {
		    // check for internet connection
		    if (isNetworkAvailable() == true) {

			// clear older parms value
			fun.URL_Params.clear();

			// add your params here

			// fun.URL_Params.add(new
			// BasicNameValuePair("key_value",""));

			// pass your parms to constructor
			bTask = new Background_Task(fun.URL_Params,
				Android_WebService.this);

			// show a message while loader is loading
			fun.Alert_Msg = "Please Wait...\nWe are Authenticating your details";
			fun.Show_Loader(Android_WebService.this, fun.Alert_Msg);

			// call web service in asyn class
			bTask.execute("http://chintankhetiya.wordpress.com/");

		    } else {
			fun.Alert_Msg = "Sorry..\n Try to connect Internet first.";
			fun.Show_Loader(Android_WebService.this, fun.Alert_Msg);
		    }
		} catch (Exception e) {
		    // TODO: handle exception
		    fun.Alert_Msg = "Sorry..\n Try to connect internet first.";
		    fun.Show_Loader(Android_WebService.this, fun.Alert_Msg);
		}
	    }
	});

    }

    public void Call_My_Blog(View v) {
	Intent intent = new Intent(Android_WebService.this, My_Blog.class);
	startActivity(intent);

    }

    @Override
    public void onTaskComplete(String result) {
	// TODO Auto-generated method stub
	fun.Hide_Loader();
	if (result != null) {

	    // call and edit below function as per your needs
	    /*
	     * Response_JSON_Object(result); Response_JSON_Array(result);
	     */

	    fun.Alert_Msg = "congrats ..\n You have create web-service example successfully.";
	    fun.Show_Loader(Android_WebService.this, fun.Alert_Msg);

	    final Timer t = new Timer();
	    t.schedule(new TimerTask() {
		public void run() {
		    fun.Hide_Loader(); // when the task active then close the
				       // dialog
		    t.cancel(); // also just top the timer thread,
				// otherwise, you may receive a crash report
		}
	    }, fun.Dialog_Time_Limit); // after 2 second (or 2000
				       // miliseconds), the task will
	    // be active.

	}

	else {
	    fun.Alert_Msg = "Sorry..\n We are not able connect with Chintan Khetiya's Blog due to slow Internet Connction.\n Reset WI-FI or Try Later";
	    fun.Show_Loader(Android_WebService.this, fun.Alert_Msg);

	    final Timer t = new Timer();
	    t.schedule(new TimerTask() {
		public void run() {
		    fun.Hide_Loader(); // when the task active then close the
				       // dialog
		    t.cancel(); // also just top the timer thread,
				// otherwise, you may receive a crash report
		}
	    }, fun.Dialog_Time_Limit); // after 2 second (or 2000
				       // miliseconds), the task will
	    // be active.

	}

    }

    public String Response_JSON_Object(String Buffer_Response) {
	try {
	    JSONObject json_data = new JSONObject(Buffer_Response);
	    User_Name = json_data.getString("User_Name").toString();
	    return User_Name;
	} catch (Exception e) {
	    Log.e(TAG, "Json Object issue" + e);
	}
	return User_Name;

    }

    public ArrayList<String> Response_JSON_Array(String Buffer_Response) {
	try {
	    JSONArray jArray = new JSONArray(Buffer_Response);
	    for (int i = 0; i < jArray.length(); i++) {
		JSONObject json_data = jArray.getJSONObject(i);
		// add to list
		User_Id.add(json_data.getString("user_id"));
	    }

	} catch (Exception e) {
	    // TODO: handle exception
	    Log.e(TAG, "Json array issue" + e);
	}
	return User_Id;
    }

    public void Set_View() {
	fun = new Function(getApplicationContext());
	call_ws = (Button) findViewById(R.id.call);

    }

    public boolean isNetworkAvailable() {
	ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	NetworkInfo networkInfo = cm.getActiveNetworkInfo();
	// if no network is available networkInfo will be null
	// otherwise check if we are connected
	if (networkInfo != null && networkInfo.isConnected()) {
	    return true;
	}
	return false;
    }
}
