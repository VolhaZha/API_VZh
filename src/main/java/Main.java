import com.coherentsolutions.training.aqa.java.api.zhavrid.util.ReadWriteRequests;
import com.coherentsolutions.training.aqa.java.api.zhavrid.util.TokenManager;

import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        String writeToken = TokenManager.getInstance().getWriteScopeToken();
        logger.info("Write Token: " + writeToken);
        ReadWriteRequests.writeRequest(writeToken);

        String readToken = TokenManager.getInstance().getReadScopeToken();
        logger.info("Read Token: " + readToken);
        ReadWriteRequests.readRequest(readToken);

    }
}




