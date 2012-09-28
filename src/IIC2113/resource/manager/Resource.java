package IIC2113.resource.manager;

import android.widget.CheckBox;

public class Resource implements IResource {
											//0				1		2				3			4			5
	public static final String[] RESOURCES = {"AVAILABLE","IN USE","NOT PRESENT","RECORDING","STREAMING","NOT AVAILABLE"};
	private int id;
	private MainActivity mainActivity;
	private int status = 0;
	private IConsumptionObs observer;
	private int pic_counter = 0;
	private int vid_counter = 0;

	public boolean isAvailable() {
		CheckBox checkBox = (CheckBox) mainActivity.findViewById(R.id.checkBox1);
		if(!checkBox.isChecked())
		{
			status = 5;
			return false;
		}
		else
		{
			if(status == 5)
				status = 0;
			else if(status!=0)
				return false;
		}
		return true;
	}

	public void cancelConsumption() {
		status = 0;
	}

	public void setObserver(IConsumptionObs _observer) {
		this.observer = _observer;
	}

	public boolean recieveAction(int action_id,String[] param) {
		switch(action_id){
			case 0://TAKE A PICTURE
				if(!this.isAvailable())
					return false;
				status = 1;
				observer.consumptionFinished(this.id, "bin/temp/temp"+pic_counter+".jpg");
				pic_counter++;
				status = 0;
				break;
			case 1://START RECORDING VIDEO
				if(!this.isAvailable())
					return false;
				status = 3;
				break;
			case 2://STOP RECORDING VIDEO
				CheckBox checkBox = (CheckBox) mainActivity.findViewById(R.id.checkBox1);
				if(!checkBox.isChecked()){
					observer.consumptionFailed(this.id, "DISPONIBILIDAD DE CAMARA CAMBIO DURANTE EJECUCION");
					return false;
				}
				if(status == 3){
					status = 0;
					observer.consumptionFinished(this.id, "bin/temp/temp"+vid_counter+".mpeg");
					vid_counter++;
				}
				else
					return false;
				break;
		}
		return true;
	}

	public int getStatus() {
		return status;
	}

	public void setId(int _id) {
		this.id = _id;
	}

	public void setMainActivity(MainActivity _mainActivity) {
		this.mainActivity = _mainActivity;
	}
}