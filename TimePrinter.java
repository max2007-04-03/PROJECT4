public class TimePrinter {
    public static void main(String[] args) {
        Thread secondsThread = new Thread(() -> {
            int elapsedSeconds = 0;
            while (true) {
                try {
                    System.out.println("Час від запуску: " + elapsedSeconds + " секунд");
                    Thread.sleep(1000); // Затримка в 1 секунду
                    elapsedSeconds++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread fiveSecondsThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000); // Затримка в 5 секунд
                    System.out.println("Минуло 5 секунд.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        secondsThread.start();
        fiveSecondsThread.start();
    }
}