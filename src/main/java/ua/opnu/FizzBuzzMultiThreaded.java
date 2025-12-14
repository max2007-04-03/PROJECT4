package ua.opnu;

import java.util.function.IntConsumer;

public class FizzBuzzMultiThreaded {
    private int n;
    private int currentNumber = 1;

    public FizzBuzzMultiThreaded(int n) {
        this.n = n;
    }


    public synchronized void fizz(Runnable printFizz) throws InterruptedException {
        while (currentNumber <= n) {
            if (currentNumber % 3 == 0 && currentNumber % 5 != 0) {
                printFizz.run();
                currentNumber++;
                notifyAll();
            } else {
                wait();
            }
        }
    }


    public synchronized void buzz(Runnable printBuzz) throws InterruptedException {
        while (currentNumber <= n) {
            if (currentNumber % 5 == 0 && currentNumber % 3 != 0) {
                printBuzz.run();
                currentNumber++;
                notifyAll();
            } else {
                wait();
            }
        }
    }


    public synchronized void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (currentNumber <= n) {
            if (currentNumber % 15 == 0) {
                printFizzBuzz.run();
                currentNumber++;
                notifyAll();
            } else {
                wait();
            }
        }
    }


    public synchronized void number(IntConsumer printNumber) throws InterruptedException {
        while (currentNumber <= n) {
            if (currentNumber % 3 != 0 && currentNumber % 5 != 0) {
                printNumber.accept(currentNumber);
                currentNumber++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    public static void main(String[] args) {
        int n = 15;
        FizzBuzzMultiThreaded fizzBuzz = new FizzBuzzMultiThreaded(n);

        Thread threadA = new Thread(() -> {
            try {
                fizzBuzz.fizz(() -> System.out.print("fizz, "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                fizzBuzz.buzz(() -> System.out.print("buzz, "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadC = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(() -> System.out.print("fizzbuzz, "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadD = new Thread(() -> {
            try {
                fizzBuzz.number(number -> System.out.print(number + ", "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();


        try {
            threadA.join();
            threadB.join();
            threadC.join();
            threadD.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}