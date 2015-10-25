package meuer.moroserver;

import com.caucho.hessian.server.HessianServlet;
import java.text.SimpleDateFormat;
import java.util.Date;
import meuer.morolibs.IMessageService;
import meuer.morolibs.Message;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("messageService")
public class MessageService extends HessianServlet implements IMessageService {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final Logger log = Logger.getLogger(MessageService.class);

    @Override
    public Message hello() {
        log.info("Zdravím klienta.");
        return new Message("SERVER", "Jsem připraven! RECEIVED(" + dateFormat.format(new Date()) + ")");
    }

    @Override
    public Message send(Message msg) {
        log.info("Odpovídám klientovi");
        return new Message("SERVER", "Obdržel jsem zprávu od: " + msg.getEmail() + " RECEIVED(" + dateFormat.format(new Date()) + ")");
    }

}
