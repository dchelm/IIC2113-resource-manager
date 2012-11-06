package IIC2113.resource.manager;

public class ResourceManager implements IConsumptionObs{

	private IAppObs observer;
	private Resource[] resources;
	private IPersistencia persistencia;
	private int user_id,device_id;
	public static final String[] RESOURCES = {"CAMERA","QR"};
	public static final String[] R1_ACTIONS = {"TAKE_PICTURE","START_RECORDING","STOP_RECORDING"};
	private MainActivity mainActivity;
	private IUserManager userManager;
	
	public String[] getResourcesList()//para User manager
	{
		String[] resources2 = new String[resources.length];
		for(int i =0;i<resources.length;i++)
		{
			resources2[i] = resources[i].toString();
		}
		return resources2;
	}
	
	public ResourceManager(MainActivity _mainActivity, IUserManager _userManager)
	{
		this.userManager = _userManager;
		this.mainActivity = _mainActivity;
		init();
	}
	
	public void setDeviceId(int _device_id)
	{
		this.device_id = _device_id;
	}
	
	public void init()
	{
		this.user_id = 1;
		resources = new Resource[RESOURCES.length];
		for(int i = 0;i<RESOURCES.length;i++)
		{
			Resource aux = new Resource();
			aux.setId(i);
			aux.setMainActivity(this.mainActivity);
			aux.setObserver((IConsumptionObs)this);
			resources[i] = aux;
		}
	}
	
	public void setPersistencia(IPersistencia _persistencia)
	{
		this.persistencia = _persistencia;
	}
	
	public void setAppObserver(IAppObs _observer)
	{
		this.observer = _observer;
	}
	
	public boolean sendResourceAvailability(int resource_id)
	{
		boolean available = resources[resource_id].isAvailable(); 
		persistencia.write(user_id,device_id,"resource:"+RESOURCES[resource_id]+", availability:"+available);
		return available;
	}
	
	public int sendResourceStatus(int id)
	{
		int status = resources[id].getStatus();
		persistencia.write(user_id,device_id, "resource:"+RESOURCES[id]+", status:"+Resource.RESOURCES[status]);
		return status;
	}
	
	public boolean resourceAction(int resource_id,int action_id,String[] param)
	{
		boolean response = resources[resource_id].recieveAction(action_id, param);
		persistencia.write(user_id,device_id,"resource:"+RESOURCES[resource_id]+" , action:"+ResourceManager.R1_ACTIONS[action_id]);
		persistencia.write(user_id,device_id, "resource:"+RESOURCES[resource_id]+", status:"+Resource.RESOURCES[resources[resource_id].getStatus()]);
		return response;
	}
	
	public void endResource(int resource_id)
	{
		resources[resource_id].cancelConsumption();
		persistencia.write(user_id,device_id,"resource:"+RESOURCES[resource_id]+", RESOURCE ENDED");
	}
	
	public void endResources()
	{
		for(int i = 0;i<resources.length;i++)
		{
			resources[i].cancelConsumption();	
			persistencia.write(user_id,device_id,"resource:"+RESOURCES[i]+", RESOURCES ENDED");
		}
	}
	
	public void consumptionFinished(int resource_id, Object object) {
		observer.resourceFinished(resource_id, object);
		persistencia.write(user_id,device_id,"resource:"+RESOURCES[resource_id]+" FINISHED CONSUMPTION (object - "+object.toString()+")");
	}

	public void consumptionFailed(int resource_id, String error) {
		observer.resourceFailed(resource_id, error);
		persistencia.write(user_id,device_id,"resource:"+RESOURCES[resource_id]+" FAILED CONSUMPTION error-"+error);
	}

	public void consumptionInterrupted(int resource_id, String error) {
		observer.resourceInterrupted(resource_id, error);
		persistencia.write(user_id,device_id,"resource:"+RESOURCES[resource_id]+" INTERUPTED CONSUMPTION error-"+error);
	}
}
