package remote_rmi;

import remote.RemoteService;
import remote.RemoteServiceImpl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RemoteServer {
    public static void main(String[] args) {
        try {
            RemoteService service = new RemoteServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099); // port mặc định RMI
            registry.rebind("RemoteService", service);
            System.out.println("RemoteServer đã sẵn sàng...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
