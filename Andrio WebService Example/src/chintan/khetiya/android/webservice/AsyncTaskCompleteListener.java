package chintan.khetiya.android.webservice;

public interface AsyncTaskCompleteListener {
    // it will call when background process finish
    public void onTaskComplete(String result);
}