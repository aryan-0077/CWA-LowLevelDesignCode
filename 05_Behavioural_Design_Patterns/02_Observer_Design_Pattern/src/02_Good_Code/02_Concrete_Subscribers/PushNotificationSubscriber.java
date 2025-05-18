package Good_Code.Concrete_Subscribers;

import Good_Code.Interfaces.Subscriber;

public class PushNotificationSubscriber implements Subscriber {
    private String userDevice;
    public PushNotificationSubscriber(String userDevice) {
        this.userDevice = userDevice;
    }

    @Override
    public void update(String video) {
        System.out.println("Sending push notification to " + userDevice
                + ": New video uploaded: " + video);
    }
}
