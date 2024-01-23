package com.example.scheduler.methods;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class SchedulerApp {
    QueueScheduler queueScheduler;
    StackScheduler stackScheduler;
    LinkedListScheduler linkedListScheduler;
    QueueSchedulerLL queueLLScheduler;
    long queueResTime, stackResTime, queueLLResTime, linkedListResTime;
    Task[] tasks;
    Task SortedTasks[];
    String[][] stackTasks, queueTasks, linkedListTasks, queueLLTasks;

    public SchedulerApp(){
        this.stackScheduler = new StackScheduler();
        this.linkedListScheduler = new LinkedListScheduler();
        this.queueScheduler = new QueueScheduler();
        this.queueLLScheduler = new QueueSchedulerLL<>();
        this.queueResTime = 0;
        this.stackResTime = 0;
        this.linkedListResTime = 0;
        this.queueLLResTime = 0;
        this.tasks = new Task[88];
        stackTasks = new String[tasks.length][3];
        queueTasks = new String[tasks.length][3];
        linkedListTasks = new String[tasks.length][3];
        queueLLTasks = new String[tasks.length][3];
    }

    public void resetApp(){
        this.stackScheduler = new StackScheduler();
        this.linkedListScheduler = new LinkedListScheduler();
        this.queueScheduler = new QueueScheduler();
        this.queueLLScheduler = new QueueSchedulerLL<>();
        this.queueResTime = 0;
        this.stackResTime = 0;
        this.linkedListResTime = 0;
        this.queueLLResTime = 0;
        this.tasks = new Task[88];
        stackTasks = new String[tasks.length][3];
        queueTasks = new String[tasks.length][3];
        linkedListTasks = new String[tasks.length][3];
        queueLLTasks = new String[tasks.length][3];
    }

    public void readInput(Context context) {
        tasks = parseTasks(context, "tasks.txt");
    }

    public void loadSchedulers() {
        long queueStart, queueEnd, stackStart, stackEnd;
        long linkedListStart, linkedListEnd, queueLLStart, queueLLEnd;
        int index = 0;
        for (Task task : tasks) {
            queueStart = System.nanoTime();
            queueScheduler.addTask(task);
            queueEnd = System.nanoTime();
            stackStart = System.nanoTime();
            stackScheduler.addTask(task);
            stackEnd = System.nanoTime();
            linkedListStart = System.nanoTime();
            linkedListScheduler.addTask(task);
            linkedListEnd = System.nanoTime();
            queueLLStart = System.nanoTime();
            queueLLScheduler.addTask(task);
            queueLLEnd = System.nanoTime();
            setStackResTime(stackResTime + (stackEnd - stackStart));
            task.setStackExecTime(stackEnd - stackStart);
            setLinkedListResTime(linkedListResTime + (linkedListEnd - linkedListStart));
            task.setLinkedListReponseTime(linkedListEnd - linkedListStart);
            setQueueResTime(queueResTime + (queueEnd - queueStart));
            task.setQueueReponseTime(queueEnd - queueStart);
            setQueueLLResTime(queueLLResTime + (queueLLEnd - queueLLStart));
            task.setQueueLLReponseTime(queueLLEnd - queueLLStart);
        }
    }

    public long executeQueue(){
        queueTasks = queueScheduler.executeTasks(queueTasks);
        return queueScheduler.getExecutionTime();
    }

    public long executeLinkedList(){
        linkedListTasks = linkedListScheduler.executeTasks(linkedListTasks);
        return linkedListScheduler.getExecutionTime();
    }

    public long executeStack(){
        stackTasks = stackScheduler.executeTasks(stackTasks);
        return stackScheduler.getExecutionTime();
    }

    public long executeQueueLL(){
        queueLLTasks = queueLLScheduler.executeTasks(queueLLTasks);
        return queueLLScheduler.getExecutionTime();
    }

    public Task[] parseTasks(Context context, String filename) {
        Task[] tasks = new Task[88];
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                tasks[index] = new Task(parts[0], parts[1], parts[2]);
                index++;
            }
            return tasks;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
//    public void sfjSort(){
//        Task[] tasks = this.tasks;
//        for (int i = 0; i < tasks.length - 1; i++) {
//            for (int j = 0; j < tasks.length - i - 1; j++) {
//                tasks[j].execute();
//                tasks[j+1].execute();
//                if (tasks[j].getPerformance() > tasks[j + 1].getPerformance()) {
//                    // Swap tasks[j+1] and tasks[j]
//                    Task temp = tasks[j];
//                    tasks[j] = tasks[j + 1];
//                    tasks[j + 1] = temp;
//                }
//            }
//        }
//        this.tasks = tasks;
//    }

    public void sfjSort() {
        Task[] tasks = this.tasks;
        mergeSort(tasks, 0, tasks.length - 1);
        this.tasks = tasks;
    }

    private void mergeSort(Task[] tasks, int left, int right) {
        if (left < right) {
            // Find the middle point
            int middle = left + (right - left) / 2;

            // Recursively sort both halves
            mergeSort(tasks, left, middle);
            mergeSort(tasks, middle + 1, right);

            // Merge the sorted halves
            merge(tasks, left, middle, right);
        }
    }

    private void merge(Task[] tasks, int left, int middle, int right) {
        // Calculate sizes of two subarrays to be merged
        int n1 = middle - left + 1;
        int n2 = right - middle;

        // Create temporary arrays
        Task[] leftArray = new Task[n1];
        Task[] rightArray = new Task[n2];

        // Copy data to temporary arrays
        System.arraycopy(tasks, left, leftArray, 0, n1);
        System.arraycopy(tasks, middle + 1, rightArray, 0, n2);

        // Merge the temporary arrays

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            leftArray[i].execute();
            rightArray[j].execute();

            if (leftArray[i].getPerformance() <= rightArray[j].getPerformance()) {
                tasks[k] = leftArray[i];
                i++;
            } else {
                tasks[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArray[], if any
        while (i < n1) {
            tasks[k] = leftArray[i];
            i++;
            k++;
        }

        // Copy remaining elements of rightArray[], if any
        while (j < n2) {
            tasks[k] = rightArray[j];
            j++;
            k++;
        }
    }



    public long getQueueResTime() {
        return queueResTime;
    }

    public void setQueueResTime(long queueResTime) {
        this.queueResTime = queueResTime;
    }

    public long getStackResTime() {
        return stackResTime;
    }

    public void setStackResTime(long stackResTime) {
        this.stackResTime = stackResTime;
    }

    public long getQueueLLResTime() {
        return queueLLResTime;
    }

    public void setQueueLLResTime(long queueLLResTime) {
        this.queueLLResTime = queueLLResTime;
    }

    public long getLinkedListResTime() {
        return linkedListResTime;
    }

    public void setLinkedListResTime(long linkedListResTime) {
        this.linkedListResTime = linkedListResTime;
    }

    public String[][] getStackTasks() {
        return stackTasks;
    }

    public String[][] getQueueTasks() {
        return queueTasks;
    }

    public String[][] getLinkedListTasks() {
        return linkedListTasks;
    }

    public String[][] getQueueLLTasks() {
        return queueLLTasks;
    }

    public double getAvgExecQ() {
        return queueScheduler.getAvgExec();
    }

    public double getAvgExecS() {
        return stackScheduler.getAvgExec();
    }

    public double getAvgExecLL() {
        return linkedListScheduler.getAvgExec();
    }

    public double getAvgExecQLL() {
        return queueLLScheduler.getAvgExec();
    }
}
