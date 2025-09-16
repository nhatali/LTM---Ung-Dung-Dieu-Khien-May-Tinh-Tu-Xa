package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteService extends Remote {
    byte[] captureScreen() throws RemoteException;

    void mouseMove(int x, int y) throws RemoteException;
    void mouseClick() throws RemoteException;

    void keyPress(int keyCode) throws RemoteException;
    void keyRelease(int keyCode) throws RemoteException;
}
