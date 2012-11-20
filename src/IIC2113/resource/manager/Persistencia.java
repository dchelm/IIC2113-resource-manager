package IIC2113.resource.manager;

public class Persistencia implements IPersistencia{
	public void write(int usuario_id, int dispositivo, String mensaje) {
		System.out.println("User ["+usuario_id+"] - Device ["+dispositivo+"] - Message - "+mensaje);
	}
	
}
