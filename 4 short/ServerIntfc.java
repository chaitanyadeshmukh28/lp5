import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalTime;

public interface ServerIntfc extends Remote{
    
    LocalTime getTime() throws RemoteException;

    void adjustTime(LocalTime serverTime, long diff) throws RemoteException;
}
