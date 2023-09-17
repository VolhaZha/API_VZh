import util.ReadWriteRequests;
import util.TokenManager;

public class Main {

    public static void main(String[] args) {
        ReadWriteRequests readWriteRequests = new ReadWriteRequests();

        String writeToken = TokenManager.getInstance().getWriteScopeToken();
        System.out.println("Write Token: " + writeToken);

        String readToken = TokenManager.getInstance().getReadScopeToken();
        System.out.println("Read Token: " + readToken);

        readWriteRequests.makeWriteRequest(writeToken);
        readWriteRequests.makeReadRequest(readToken);
    }
}




