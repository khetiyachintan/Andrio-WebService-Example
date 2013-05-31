package chintan.khetiya.android.webservice;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Custom_alert_DialogClass extends Dialog {

    public Activity a;
    public String Msg;
    public Dialog d;
    public Button no;
    public Context context;
    public TextView alert_infromation_text;
    ProgressBar pb;
    LinearLayout exit_layout;

    public Custom_alert_DialogClass(Activity a, String Msg) {
	super(a);
	// TODO Auto-generated constructor stub
	this.a = a;
	this.Msg = Msg;

    }

    /**
     * Constructor for Custom_alert_DialogClass.java Called for initializing
     * object of Custom_alert_DialogClass.java.
     * 
     * @param function
     * @param alert_Msg
     */
    public Custom_alert_DialogClass(Context c, String alert_Msg) {
	// TODO Auto-generated constructor stub

	super(c);
	this.context = c;
	this.Msg = alert_Msg;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.custom_alert_dialog);
	Create_Alert_DialogView();

	no.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {
		// TODO Auto-generated method stub
		dismiss();

		a.finish();

	    }
	});

    }

    public void Create_Alert_DialogView() {

	no = (Button) findViewById(R.id.Dailog_Exit);
	exit_layout = (LinearLayout) findViewById(R.id.exit_layout);
	exit_layout.setVisibility(View.GONE);
	alert_infromation_text = (TextView) findViewById(R.id.alert_infromation_text);
	alert_infromation_text.setText(Msg);
	pb = (ProgressBar) findViewById(R.id.progressBar1);
	no.setVisibility(View.GONE);
    }

}