package IIC2113.resource.manager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends Activity{
	
	ApplicationManager appManager;

	/** Called when the user clicks the Send button */
	public void endResource(View view) {
		appManager.endResources();
		EditText editText = (EditText) findViewById(R.id.editText1);
		editText.setText("Resource Ended");
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
