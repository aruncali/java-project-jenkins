public class Main {
    public static void main(String[] args) {

        String appName = System.getenv("APP_NAME");
        String environment = System.getenv("APP_ENV");
        String message = System.getenv("APP_MESSAGE");

        System.out.println("================================");
        System.out.println("Java Environment Application");
        System.out.println("================================");
        System.out.println("Application : " + appName);
        System.out.println("Environment : " + environment);
        System.out.println("Message     : " + message);
        System.out.println("================================");
    }
}