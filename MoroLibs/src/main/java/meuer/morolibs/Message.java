package meuer.morolibs;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Message implements Serializable {

    @NotNull //sám NotNull nic nedělá (nespustí se validátor), proto je potřeba přidat další validační podmínky
    @Size(min = 1, message = "Mail musí být vyplněn.")
    private String email;
    @NotNull
    @Size(min = 1, message = "Zpráva musí být vyplněna.")
    private String text;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    private final String date;

    public Message() {
        date = dateFormat.format(new Date());
    }

    public Message(String email, String text) {
        this.email = email;
        this.text = text;
        date = dateFormat.format(new Date());
    }
    
    public String getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
