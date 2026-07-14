package application;

import java.awt.Desktop;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Set;

// Builds a mailto: link with the shopping list prefilled as the email body,
// and opens it in the user's default mail client via Desktop.mail().
public class Email {

    public URI buildMailto(Application app) {
        String subject = encode("My Shopping List");
        String body = encode(buildBody(app));
        return URI.create("mailto:?subject=" + subject + "&body=" + body);
    }

    public void send(Application app) {
        URI mailto = buildMailto(app);
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.MAIL)) {
            try {
                Desktop.getDesktop().mail(mailto);
                return;
            } catch (Exception e) {
                System.out.println("Could not open mail client, here is the link instead:");
            }
        }
        System.out.println(mailto);
    }

    private String buildBody(Application app) {
        ArrayList<Grocery> items = app.getGroceryList();
        Set<Grocery> crossed = app.getCrossedList();
        StringBuilder body = new StringBuilder("Shopping List:\n");
        for (Grocery item : items) {
            String mark = crossed.contains(item) ? "[x] " : "[ ] ";
            body.append(mark).append(item.getName()).append(" - ").append(item.getPrice()).append("\n");
        }
        body.append("\nTotal: ").append(app.getCurrent()).append(" / Limit: ").append(app.getLimit());
        return body.toString();
    }

    private String encode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.name()).replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("UTF-8 not supported", e);
        }
    }
}
