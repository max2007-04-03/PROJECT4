package ua.opnu;

public class TimePrinter {
    public static void main(String[] args) {
        Thread secondsThread = new Thread(() -> {
            int elapsedSeconds = 0;
            while (true) {
                try {
                    Thread.sleep(1000);
                    elapsedSeconds++;
                    System.out.println("Time since start: " + elapsedSeconds + " seconds");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        Thread fiveSecondsThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                    System.out.println("5 seconds have passed.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        secondsThread.start();
        fiveSecondsThread.start();

        // ВИПРАВЛЕННЯ: Додаємо join(), щоб main потік не завершувався раніше за інші
        try {
            secondsThread.join();
            fiveSecondsThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}