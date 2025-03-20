public interface INotificationChannel {
    void sendNotification(String recipient, String message);
}

public class EmailNotificationChannel implements INotificationChannel {
    @Override
    public void sendNotification(String recipient, String message) {
        // Implement email sending logic here
        System.out.println("Sending email to " + recipient + " with message: " + message);
    }
}

public class SMSNotificationChannel implements INotificationChannel {
    @Override
    public void sendNotification(String recipient, String message) {
        // Implement SMS sending logic here
        System.out.println("Sending SMS to " + recipient + " with message: " + message);
    }
}

public class PushNotificationChannel implements INotificationChannel {
    @Override
    public void sendNotification(String recipient, String message) {
        // Implement push notification sending logic here
        System.out.println("Sending push notification to " + recipient + " with message: " + message);
    }
}

import java.util.HashMap;
import java.util.Map;

public class NotificationService {
    private Map<String, INotificationChannel> channels = new HashMap<>();

    public void registerChannel(String channelName, INotificationChannel channel) {
        channels.put(channelName, channel);
    }

    public void sendNotification(String channelName, String recipient, String message) {
        INotificationChannel channel = channels.get(channelName);
        if (channel != null) {
            channel.sendNotification(recipient, message);
        } else {
            throw new IllegalArgumentException("Notification channel not found: " + channelName);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        NotificationService notificationService = new NotificationService();

        // Register notification channels
        notificationService.registerChannel("email", new EmailNotificationChannel());
        notificationService.registerChannel("sms", new SMSNotificationChannel());
        notificationService.registerChannel("push", new PushNotificationChannel());

        // Send notifications
        notificationService.sendNotification("email", "user@example.com", "Your appointment is confirmed.");
        notificationService.sendNotification("sms", "123-456-7890", "Your appointment is confirmed.");
        notificationService.sendNotification("push", "userDeviceToken", "Your appointment is confirmed.");
    }
}

