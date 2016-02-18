package texus.task;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.texus.mohanlalquotes.AppConstance;

public class DoOperationTask extends  AsyncTask<Void, Void, String>
					implements AppConstance {
    public static final int OPERATION_TYPE_SHARE_APP = 1;
    public static final int OPERATION_TYPE_FEEDBACK = 2;
    public static final int OPERATION_TYPE_RATE_APP = 3;
    Context context;
    int type;

    public DoOperationTask(Context context, int type) {
    	this.context = context;
        this.type = type;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(Void... params) {
		try {
            Thread.sleep(TIME_TO_WAIT_IN_MS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
        if(type == OPERATION_TYPE_SHARE_APP) {
            doShareApp();return;
        }
        if(type == OPERATION_TYPE_RATE_APP) {
            doRateApp();return;
        }
        if(type == OPERATION_TYPE_FEEDBACK) {
            doFeedback();return;
        }
	}

    public void doRateApp() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=com.texus.mohanlalquotes"));
        context.startActivity(intent);
    }

    public void doShareApp() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "Download Mohanlal Quotes from google play, " +
                "it is an awesome app https://play.google.com/store/apps/details?id=com.texus.mohanlalquotes");
        context.startActivity(intent);
    }

    public void doFeedback() {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        String []emails = {"texusapps@gmail.com"};
        emailIntent .setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emails);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Mohanlal Quotes: Feedback");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
        context.startActivity(Intent.createChooser(emailIntent, "Send Feedback e-mail"));
    }

}
