package IIC2113.resource.manager;

public class ApplicationManager implements IAppObs{

	private ResourceManager resourceManager;
	private MainActivity mainActivity;
	
	public void init(MainActivity _mainActivity)
	{
		this.mainActivity = _mainActivity;
		this.resourceManager = new ResourceManager();
		this.resourceManager.setAppObserver((IAppObs)this);
		this.resourceManager.init(this.mainActivity);
	}

	public void takePicture()
	{
		resourceManager.resourceAction(0, 0, null); //foto,tomar,param
	}
	
	public void startRecording()
	{
		resourceManager.resourceAction(0, 1, null);//video,grabar,param
	}
	
	public void stopRecording()
	{
		resourceManager.resourceAction(0, 2, null);//video,parar_grabacion,param
	}
	
	public void endResources()
	{
		resourceManager.endResources();
	}
	
	public void resourceFinished(int resource_id, String path) {
		System.out.println("Application Manager - Resource:"+ResourceManager.RESOURCES[resource_id]+" FINISHED - (path:"+path+")");		
	}

	public void resourceFailed(int resource_id, String error) {
		System.out.println("Application Manager - Resource:"+ResourceManager.RESOURCES[resource_id]+" FAILED - (error:"+error+")");
	}

	public void resourceInterrupted(int resource_id, String error) {
		System.out.println("Application Manager - Resource:"+ResourceManager.RESOURCES[resource_id]+" INTERRUPTED - (error:"+error+")");
	}

}