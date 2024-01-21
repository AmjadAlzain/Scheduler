package com.example.scheduler.methods;

public class Task implements Comparable<Task> {
    private String name;
    private String inputType;
    private String inputValue;

    public Task(String name, String inputType, String inputValue) {
        this.name = name;
        this.inputType = inputType;
        this.inputValue = inputValue;
    }

    public void execute() {
        switch (name) {
            case "fib":
                System.out.println("Fibonacci result: " + StarterPack.fib(Integer.parseInt(inputValue)));
                break;
            case "isPrime":
                System.out.println("Is Prime: " + StarterPack.isPrime(Long.parseLong(inputValue)));
                break;
            case "longestPalSubstr":
                System.out.println("Longest Palindrome: " + StarterPack.longestPalSubstr(inputValue));
                break;
            case "sumOfDigitsFrom1ToN":
                System.out.println("Sum of Digits: " + StarterPack.sumOfDigitsFrom1ToN(Integer.parseInt(inputValue)));
                break;
            case "getNthUglyNo":
                System.out.println("Nth Ugly Number: " + StarterPack.getNthUglyNo(Integer.parseInt(inputValue)));
                break;
            // Add other cases if needed
        }
    }

    @Override
    public int compareTo(Task o) {
        return 0;
    }
}
