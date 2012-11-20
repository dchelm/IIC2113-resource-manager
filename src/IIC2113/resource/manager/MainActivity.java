package IIC2113.resource.manager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends Activity{
	
	ApplicationManager appManager;

	/** Called when the user clicks the Send button */
	public void endResource(View view) {
		appManager.endResources();
		EditText editText = (EditText) findViewById(R.id.editText1);
		editText.setText("Resource Ended");
	}
	
	public void cameraChange(View view)
	{
		CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
		ImageButton img = (ImageButton) findViewById(R.id.imageButton1);
		if(checkBox.isChecked())
		{
			img.setBackgroundColor(Color.GREEN);
		}
		else
		{
			img.setBackgroundColor(Color.BLACK);
			appManager.endResources();
		}
	}
	
	/** Called when the user clicks the Send button */
	public void takePicture(View view) {
		appManager.takePicture();
		EditText editText = (EditText) findViewById(R.id.editText1);
		CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
		if(checkBox.isChecked())
			editText.setText("Picture Taken");
		else
			editText.setText("Camera not available");
	}
	
	/** Called when the user clicks the Send button */
	public void stopVideo(View view) {
		appManager.stopRecording();
		EditText editText = (EditText) findViewById(R.id.editText1);
		CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
		if(checkBox.isChecked())
			editText.setText("Stop Video");
		else
			editText.setText("Camera not available");
	}
	
	/** Called when the user clicks the Send button */
	public void startVideo(View view) {
		appManager.startRecording();
		EditText editText = (EditText) findViewById(R.id.editText1);
		CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
		if(checkBox.isChecked())
			editText.setText("Start Video");
		else
			editText.setText("Camera not available");
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appManager = new ApplicationManager();
        appManager.init(this);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
