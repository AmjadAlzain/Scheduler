package com.example.scheduler.methods;

public class Task {
    private String name;
    private String inputType;
    private String inputValue;
    private String output;
    private long stackExecTime, queueExecTime, linkedListExecTime, queueLLExecTime;
    private long stackReponseTime, queueReponseTime, linkedListReponseTime, queueLLReponseTime;

    public Task(String name, String inputType, String inputValue) {
        this.name = name;
        this.inputType = inputType;
        this.inputValue = inputValue;
    }

    public void execute() {
        switch (name) {
            case "fib":
                output = "Fibonacci result: " + StarterPack.fib(Integer.parseInt(inputValue));
                break;
            case "isPrime":
                output = "Is Prime: " + StarterPack.isPrime(Long.parseLong(inputValue));
                break;
            case "longestPalSubstr":
                output = "Longest Palindrome: " + StarterPack.longestPalSubstr(inputValue);
                break;
            case "sumOfDigitsFrom1ToN":
                output = "Sum of Digits: " + StarterPack.sumOfDigitsFrom1ToN(Integer.parseInt(inputValue));
                break;
            case "getNthUglyNo":
                output = "Nth Ugly Number: " + StarterPack.getNthUglyNo(Integer.parseInt(inputValue));
                break;
            // Add other cases if needed
        }
    }

    public String getName() {
        return name;
    }

    public String getInputType() {
        return inputType;
    }

    public String getInputValue() {
        return inputValue;
    }

    public String getOutput() {
        return output;
    }

    public long getStackExecTime() {
        return stackExecTime;
    }

    public void setStackExecTime(long stackExecTime) {
        this.stackExecTime = stackExecTime;
    }

    public long getQueueExecTime() {
        return queueExecTime;
    }

    public void setQueueExecTime(long queueExecTime) {
        this.queueExecTime = queueExecTime;
    }

    public long getLinkedListExecTime() {
        return linkedListExecTime;
    }

    public void setLinkedListExecTime(long linkedListExecTime) {
        this.linkedListExecTime = linkedListExecTime;
    }

    public long getQueueLLExecTime() {
        return queueLLExecTime;
    }

    public void setQueueLLExecTime(long queueLLExecTime) {
        this.queueLLExecTime = queueLLExecTime;
    }

    public long getStackReponseTime() {
        return stackReponseTime;
    }

    public void setStackReponseTime(long stackReponseTime) {
        this.stackReponseTime = stackReponseTime;
    }

    public long getQueueReponseTime() {
        return queueReponseTime;
    }

    public void setQueueReponseTime(long queueReponseTime) {
        this.queueReponseTime = queueReponseTime;
    }

    public long getLinkedListReponseTime() {
        return linkedListReponseTime;
    }

    public void setLinkedListReponseTime(long linkedListReponseTime) {
        this.linkedListReponseTime = linkedListReponseTime;
    }

    public long getQueueLLReponseTime() {
        return queueLLReponseTime;
    }

    public void setQueueLLReponseTime(long queueLLReponseTime) {
        this.queueLLReponseTime = queueLLReponseTime;
    }
}
