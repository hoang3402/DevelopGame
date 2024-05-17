public class Main {
    public static void main(String[] args) {
        try {
            GameLoop.main(args);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}