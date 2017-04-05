package com.example.progressbarwidget; 

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.app.Activity; 
import android.app.Dialog;
import android.app.ProgressDialog; 
import android.graphics.drawable.Drawable;
import android.util.Log; 
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView; 

public class ProgressbarActivity extends Activity {
	int myProgress;
	ProgressBar progressBar;
	ProgressBar progressBar2;	
	TextView textview;
	Button button1, button2;
	private ProgressDialog pDialog;	
	ImageView my_image;
	
	// Progress dialog type (0 - for Horizontal progress bar)
	public static final int CUSTOM_PROGRESS_DIALOG = 0; 
	
	// File url to download
	private static String filedownload_url = "https://lh4.googleusercontent.com/-9z69eoAzRd8/UOjP_igcmtI/AAAAAAAAAsc/-yunBbz8eJI/s300/tour-Ha-Noi-Sword-Lake.jpg";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        setContentView(R.layout.activity_progressbar);
        textview = (TextView)findViewById(R.id.textView);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
		my_image = (ImageView) findViewById(R.id.my_image);
        progressBar2 = (ProgressBar)findViewById(R.id.progressBar2);        
        progressBar = (ProgressBar)findViewById(R.id.progressBar1);

    	progressBar2.setVisibility(View.INVISIBLE);        
        progressBar.setProgress(0);
        progressBar.setSecondaryProgress(0);
        
    }

    public void button_click(View view){
    	switch(view.getId()){
    	case R.id.button1:
    		new ShowCustomProgressBarAsyncTask().execute(); 
    		progressBar2.setVisibility(View.VISIBLE);
    		
    		break;
    	case R.id.button2:
    		new DownloadFileFromURL().execute(filedownload_url);
    		break;
    	}
    	
    }
    /**
	 * Background Async Task to show custom progressbar
	 * */
    
  public class ShowCustomProgressBarAsyncTask extends AsyncTask<Void, Integer, Void> {
  
  int myProgress;

  @Override
  protected void onPostExecute(Void result) { 
   textview.setText("Finish work with custom ProgressBar");
   button1.setEnabled(true);
   progressBar2.setVisibility(View.INVISIBLE);
  } 
  @Override
  protected void onPreExecute() { 
   button1.setEnabled(false);
   textview.setText("Start work with custom ProgressBar");
   myProgress = 0;
   progressBar.setSecondaryProgress(0);
  } 
  @Override
  protected Void doInBackground(Void... params) { 
   while(myProgress<100){
    myProgress++; 
    publishProgress(myProgress);
       SystemClock.sleep(100);
   }
   return null;
  } 
  @Override
  protected void onProgressUpdate(Integer... values) { 
   progressBar.setProgress(values[0]);
   progressBar.setSecondaryProgress(values[0] + 5);
  } 
 } 
  	/* Showing Dialog */
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case CUSTOM_PROGRESS_DIALOG:
			pDialog = new ProgressDialog(this);
			pDialog.setMessage("Downloading file. Please wait...");
			pDialog.setMax(100);
			pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			pDialog.setProgressDrawable(getResources().getDrawable(R.drawable.custom_progress_bar_horizontal));
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
			return pDialog;
		default:
			return null;
		}
	}

/* Background Async Task to download file */
class DownloadFileFromURL extends AsyncTask<String, String, String> {
		/*  Before starting background thread. Show Progress Bar Dialog */
		@SuppressWarnings("deprecation")
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showDialog(CUSTOM_PROGRESS_DIALOG);
		}
		/* Downloading file in background thread */
		@Override
		protected String doInBackground(String... f_url) {
			int count;
	        try {
	            URL url = new URL(f_url[0]);
	            URLConnection conection = url.openConnection();
	            conection.connect();
	            // getting file length
	            int lenghtOfFile = conection.getContentLength();
	            // input stream to read file - with 8k buffer
	            InputStream input = new BufferedInputStream(url.openStream(), 8192);
	            // Output stream to write file
	            OutputStream output = new FileOutputStream("/sdcard/filedownload.jpg"); 
	            byte data[] = new byte[1024];  
	            long total = 0; 
	            while ((count = input.read(data)) != -1) {
	                total += count;
	                // publishing the progress....
	                // After this onProgressUpdate will be called
	                publishProgress(""+(int)((total*100)/lenghtOfFile)); 
	                // writing data to file
	                output.write(data, 0, count);
	            }
	            // flushing output
	            output.flush();
	            // closing streams
	            output.close();
	            input.close();
	            
	        } catch (Exception e) {
	        	Log.e("Error: ", e.getMessage());
	        }
	        return null;
		}
		/* Updating progress bar */
		protected void onProgressUpdate(String... value) {
			// setting progress percentage
			pDialog.setProgress(Integer.parseInt(value[0]));
			pDialog.setSecondaryProgress(Integer.parseInt(value[0]) + 5);
     }
		/*  After completing background task. Dismiss the progress dialog */
		@SuppressWarnings("deprecation")
		@Override
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after the file was downloaded
			dismissDialog(CUSTOM_PROGRESS_DIALOG);
			// Displaying downloaded image into image view
			// Reading image path from sdcard
			String imagePath = Environment.getExternalStorageDirectory().toString() + "/filedownload.jpg";
			// setting downloaded into image view
			my_image.setImageDrawable(Drawable.createFromPath(imagePath));
		} 
	}
	
}
