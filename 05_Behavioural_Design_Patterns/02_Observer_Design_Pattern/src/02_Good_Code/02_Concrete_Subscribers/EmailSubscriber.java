package Good_Code.Concrete_Subscribers;

import Good_Code.Interfaces.Subscriber;

public class EmailSubscriber implements Subscriber {
    private String email;
    public EmailSubscriber(String email) {
        this.email = email;
    }

    @Override
    public void update(String video) {
        System.out.println(
                "Sending email to " + email + ": New video uploaded: " + video);
    }
}