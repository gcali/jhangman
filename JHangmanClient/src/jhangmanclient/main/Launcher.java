package jhangmanclient.main;

import java.io.IOException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import jhangmanclient.client.ClientTask;
import rmi_interface.RMIServer;
import rmi_interface.UserAlreadyRegisteredException;

public class Launcher {
    

    public static void main(String[] args) {
        boolean done = false;
        
        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry(RMIServer.defaultPort);
        } catch (RemoteException e) {
            System.out.println("Connection error");
            throw new RuntimeException(e);
        }
        
        RMIServer server = null;
        try {
            server = (RMIServer) registry.lookup(RMIServer.name);
        } catch (RemoteException e) {
            System.err.println("Connection error");
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            System.err.println("No " + RMIServer.name + " found");
            throw new RuntimeException(e);
        }
        
        assert server != null;
        
        ClientTask task = new ClientTask(server);
        task.run();
        

    }

}