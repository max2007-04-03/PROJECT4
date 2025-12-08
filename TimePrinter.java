public class TimePrinter {
    public static void main(String[] args) {
        Thread secondsThread = new Thread(() -> {
            int elapsedSeconds = 0;
            while (true) {
                try {
                    System.out.println("Time since start: " + elapsedSeconds + " seconds");
                    Thread.sleep(1000); 
                    elapsedSeconds++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread fiveSecondsThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000); 
                    System.out.println("5 seconds have passed.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        secondsThread.start();
        fiveSecondsThread.start();
    }

}

