package jhangmanserver.remote;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class ConcurrentTCPServer implements Runnable {

    private LoggedInChecker loggedInChecker;
    private ServerSocket mainSocket;
    private ThreadPoolExecutor threadPool;
    private final static int port = 10005;
    private boolean done = false;

    public ConcurrentTCPServer(LoggedInChecker loggedInChecker) throws IOException {
        this.loggedInChecker = loggedInChecker;
        this.mainSocket = new ServerSocket(ConcurrentTCPServer.port);
        this.threadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    }

    @Override
    public void run() {
        while (!done) {
            try {
                Socket socket = this.mainSocket.accept();
                ServerTask task = new ServerTask(socket);
                this.threadPool.execute(task);
            } catch (IOException e) {
                System.err.println("Error during socket acceptance; ignoring");
            }
        }
    } 
}