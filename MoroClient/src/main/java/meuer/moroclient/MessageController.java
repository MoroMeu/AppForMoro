package meuer.moroclient;

import meuer.morolibs.Message;
import com.caucho.hessian.client.HessianProxyFactory;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;
import meuer.morolibs.IMessageService;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@Controller
public class MessageController {

    private static final Logger log = Logger.getLogger(MessageController.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:DD HH:mm:ss");

    private MessageHistory msgHistory = new MessageHistory(); //lokalni historie zprav od uzivatele
    private MessageHistory msgFromServer = new MessageHistory(); //lokalni historie zprav od serveru

    private HessianProxyFactory factory = new HessianProxyFactory();
    private IMessageService server;

    private static final int interval = 30000;

    public MessageController() {
        try {
            this.server = (IMessageService) factory.create(IMessageService.class, "http://localhost:8081/messageService/"); //na konci URL musí být "/" jinak hlásí HTTP 405
        } catch (MalformedURLException ex) {
            log.error(ex);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String messageForm(Message message) {
        return "msgform";
    }

    //validace pomoci HTML5 (required) i Springu pres @Valid
    @RequestMapping(value = "/msgform", method = RequestMethod.POST)
    public String messageSubmit(@Valid Message message, BindingResult result, Model model) {
        log.info("Odesílám klientovu zprávu serveru.");
        if (result.hasErrors()) {
            log.info("Nevyplněná položka: " + result.getFieldError());
            return "msgform";
        }
        msgHistory.addMessage(message);
        msgFromServer.addMessage(server.send(message));
        model.addAttribute("messages", msgHistory.getMessages());
        model.addAttribute("responses", msgFromServer.getMessages());
        return "msgform";
    }

    @Scheduled(fixedRate = interval) //1min = 60000
    public void checkServer() {
        log.info("Testuji server.");
        if (server != null) {
            Message msg;
            msg = server.hello();
            log.info(msg.getText());
            msgFromServer.addMessage(msg);
        }
    }
}
