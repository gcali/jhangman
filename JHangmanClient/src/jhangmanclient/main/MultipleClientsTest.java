package jhangmanclient.main;

import java.rmi.RemoteException;

import jhangmanclient.controller.AuthController;

public class MultipleClientsTest {

    public static void main(String[] args) throws RemoteException {
        
        GUIMain.User[] users = {new GUIMain.User("Gio", "test"),
                                new GUIMain.User("Mike", "test"),
                                new GUIMain.User("Phil", "test")};
        for (int i=0; i<users.length; i++) {
            AuthController controller = GUIMain.initConnection();
            GUIMain frame = new GUIMain(controller);
            frame.startLogged(users[i].getNick(), users[i].getPass());
        }
    }

}
