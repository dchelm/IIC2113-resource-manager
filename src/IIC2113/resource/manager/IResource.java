package IIC2113.resource.manager;

public interface IResource {

	public boolean isAvailable();
	public void cancelConsumption();
	public void setObserver(IConsumptionObs observer);
	public boolean recieveAction(int action_id, String[] param);
	public int getStatus();
	public void setId(int id);
	
}
