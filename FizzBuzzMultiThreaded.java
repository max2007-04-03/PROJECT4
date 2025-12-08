import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FizzBuzzMultiThreaded {
    private final int n;
    private final BlockingQueue<String> outputQueue = new LinkedBlockingQueue<>();

    public FizzBuzzMultiThreaded(int n) {
        this.n = n;
    }

    public void fizz() {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                outputQueue.add("fizz");
            }
        }
    }

    public void buzz() {
        for (int i = 1; i <= n; i++) {
            if (i % 5 == 0 && i % 3 != 0) {
                outputQueue.add("buzz");
            }
        }
    }

    public void fizzbuzz() {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                outputQueue.add("fizzbuzz");
            }
        }
    }

    public void number() {
        for (int i = 1; i <= n; i++) {
            if (i % 3 != 0 && i % 5 != 0) {
                outputQueue.add(String.valueOf(i));
            }
        }
    }

    public void printOutput() {
        while (!outputQueue.isEmpty()) {
            try {
                System.out.print(outputQueue.take());
                if (!outputQueue.isEmpty()) {
                    System.out.print(", ");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int n = 15; // Задайте значення n
        FizzBuzzMultiThreaded fizzBuzz = new FizzBuzzMultiThreaded(n);

        Thread threadA = new Thread(fizzBuzz::fizz);
        Thread threadB = new Thread(fizzBuzz::buzz);
        Thread threadC = new Thread(fizzBuzz::fizzbuzz);
        Thread threadD = new Thread(() -> {
            fizzBuzz.number();
            fizzBuzz.printOutput();
        });

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
    }
}