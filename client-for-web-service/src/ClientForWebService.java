import java.rmi.RemoteException;

import ru.zubentsov.service.*;
public class ClientForWebService {

	public static void main(String[] args) throws RemoteException {
	
		MyService proxy = new MyServiceProxy();
		
		System.out.println(proxy.getTime());

	}

}
