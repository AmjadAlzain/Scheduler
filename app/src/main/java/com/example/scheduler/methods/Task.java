package com.example.scheduler.methods;

public class Task {
    private String name;
    private String inputType;
    private String inputValue;
    private String output;
    private long stackExecTime, queueExecTime, linkedListExecTime, queueLLExecTime;
    private long stackReponseTime, queueReponseTime, linkedListReponseTime, queueLLReponseTime;

    private String methodPerfName;
    private long performance;


    public Task(String name, String inputType, String inputValue) {
        this.name = name;
        this.inputType = inputType;
        this.inputValue = inputValue;
    }

    public void execute() {
        long start = System.nanoTime();
        switch (name) {
            case "fib":
                output = String.valueOf(StarterPack.fib(Integer.parseInt(inputValue)));

                break;
            case "isPrime":
                output = String.valueOf(StarterPack.isPrime(Long.parseLong(inputValue)));

                break;
            case "longestPalSubstr":
                output = StarterPack.longestPalSubstr(inputValue);

                break;
            case "sumOfDigitsFrom1ToN":
                output = String.valueOf(StarterPack.sumOfDigitsFrom1ToN(Integer.parseInt(inputValue)));

                break;
            case "getNthUglyNo":
                output = String.valueOf(StarterPack.getNthUglyNo(Integer.parseInt(inputValue)));
                break;

        }
        long end = System.nanoTime();
        setPerformance(end-start);

    }

    // Getters and Setters
    public String getMethodPerfName() {
        return methodPerfName;
    }

    public void setMethodPerfName(String methodPerfName) {
        this.methodPerfName = methodPerfName;
    }

    public long getPerformance() {
        return performance;
    }

    public void setPerformance(long performance) {
        this.performance = performance;
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
