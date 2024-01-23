package com.example.scheduler;

import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.scheduler.methods.SchedulerApp;

import java.io.IOException;
import java.io.InputStream;

public class fragmentSchedulerHome extends Fragment {
    private long responseTimeLL, responseTimeQLL, responseTimeS, responseTimeQ;
    private long executionTimeLL, executionTimeQLL, executionTimeS, executionTimeQ;
    private long avgExecLL, avgExecQLL, avgExecS, avgExecQ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scheduler_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button executeBTN = view.findViewById(R.id.executeBTN);
        Button parseBTN = view.findViewById(R.id.parseBTN);
        Button executeSJFBTN = view.findViewById(R.id.executeSJFBTN);
        Button viewTasksBTN = view.findViewById(R.id.viewTASK);

        SchedulerApp schedulerApp = new SchedulerApp();


        // text views for Response time
        TextView RT_LL = view.findViewById(R.id.RT_LL);
        TextView RT_QLL = view.findViewById(R.id.RT_QLL);
        TextView RT_S = view.findViewById(R.id.RT_S);
        TextView RT_Q = view.findViewById(R.id.RT_Q);

        // text view for Execution time
        TextView ET_LL = view.findViewById(R.id.ET_LL);
        TextView ET_QLL = view.findViewById(R.id.ET_QLL);
        TextView ET_S = view.findViewById(R.id.ET_S);
        TextView ET_Q = view.findViewById(R.id.ET_Q);

        // Text Views for Turnaround Time
        TextView TT_LL = view.findViewById(R.id.TT_LL);
        TextView TT_QLL = view.findViewById(R.id.TT_QLL);
        TextView TT_S = view.findViewById(R.id.TT_S);
        TextView TT_Q = view.findViewById(R.id.TT_Q);

        executeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executionTimeQLL = schedulerApp.executeQueueLL();
                ET_QLL.setText(String.valueOf(executionTimeQLL));
                executionTimeS = schedulerApp.executeStack();
                ET_S.setText(String.valueOf(executionTimeS));
                executionTimeQ = schedulerApp.executeQueue();
                ET_Q.setText(String.valueOf(executionTimeQ));
                executionTimeLL = schedulerApp.executeLinkedList();
                ET_LL.setText(String.valueOf(executionTimeLL));

                // Turnaround time
                TT_S.setText(String.valueOf(responseTimeS + executionTimeS));
                TT_Q.setText(String.valueOf(responseTimeQ + executionTimeQ));
                TT_LL.setText(String.valueOf(responseTimeLL + executionTimeLL));
                TT_QLL.setText(String.valueOf(responseTimeQLL + executionTimeQLL));

                // Find the minimum execution time
                long minTurnaroundTime = Math.min(Math.min(responseTimeQLL + executionTimeQLL,
                        responseTimeS + executionTimeS), Math.min(responseTimeQ + executionTimeQ,
                        responseTimeLL + executionTimeLL));

                // Highlight the TextView with the minimum execution time in green
                if (minTurnaroundTime == (responseTimeQLL + executionTimeQLL)) {
                    TT_QLL.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                    TT_S.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                    TT_Q.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                    TT_LL.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                } else if (minTurnaroundTime == (responseTimeS + executionTimeS)) {
                    TT_QLL.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                    TT_S.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                    TT_Q.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                    TT_LL.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                } else if (minTurnaroundTime == (responseTimeQ + executionTimeQ)) {
                    TT_QLL.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                    TT_S.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                    TT_Q.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                    TT_LL.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                } else {
                    TT_QLL.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                    TT_S.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                    TT_Q.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                    TT_LL.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                }

                // Find the minimum execution time
                long minExecutionTime = Math.min(Math.min(executionTimeQLL, executionTimeS),
                        Math.min(executionTimeQ, executionTimeLL));

                // Highlight the TextView with the minimum execution time in green
                if (minExecutionTime == executionTimeQLL) {
                    ET_QLL.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                    ET_S.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                    ET_Q.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                    ET_LL.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                } else if (minExecutionTime == executionTimeS) {
                    ET_QLL.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                    ET_S.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                    ET_Q.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                    ET_LL.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                } else if (minExecutionTime == executionTimeQ) {
                    ET_QLL.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                    ET_S.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                    ET_Q.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                    ET_LL.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                } else {
                    ET_QLL.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                    ET_S.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                    ET_Q.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                    ET_LL.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                }
            }
        });
        parseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                schedulerApp.resetApp();
                schedulerApp.readInput(getActivity());
                schedulerApp.loadSchedulers();
                responseTimeQLL = schedulerApp.getQueueLLResTime();
                RT_QLL.setText(String.valueOf(responseTimeQLL));
                responseTimeS = schedulerApp.getStackResTime();
                RT_S.setText(String.valueOf(responseTimeS));
                responseTimeQ= schedulerApp.getQueueResTime();
                RT_Q.setText(String.valueOf(responseTimeQ));
                responseTimeLL = schedulerApp.getLinkedListResTime();
                RT_LL.setText(String.valueOf(responseTimeLL));

            }
        });

        executeSJFBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                schedulerApp.resetApp();
                schedulerApp.readInput(getActivity());
                schedulerApp.sfjSort();
                schedulerApp.loadSchedulers();
                responseTimeQLL = schedulerApp.getQueueLLResTime();
                RT_QLL.setText(String.valueOf(responseTimeQLL));
                responseTimeS = schedulerApp.getStackResTime();
                RT_S.setText(String.valueOf(responseTimeS));
                responseTimeQ= schedulerApp.getQueueResTime();
                RT_Q.setText(String.valueOf(responseTimeQ));
                responseTimeLL = schedulerApp.getLinkedListResTime();
                RT_LL.setText(String.valueOf(responseTimeLL));
                executionTimeQLL = schedulerApp.executeQueueLL();
                ET_QLL.setText(String.valueOf(executionTimeQLL));
                executionTimeS = schedulerApp.executeStack();
                ET_S.setText(String.valueOf(executionTimeS));
                executionTimeQ = schedulerApp.executeQueue();
                ET_Q.setText(String.valueOf(executionTimeQ));
                executionTimeLL = schedulerApp.executeLinkedList();
                ET_LL.setText(String.valueOf(executionTimeLL));

                // Turnaround time
                TT_S.setText(String.valueOf(responseTimeS+executionTimeS));
                TT_Q.setText(String.valueOf(responseTimeQ+executionTimeQ));
                TT_LL.setText(String.valueOf(responseTimeLL+executionTimeLL));
                TT_QLL.setText(String.valueOf(responseTimeQLL+executionTimeQLL));
            }
        });

        viewTasksBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateArrays(schedulerApp);
                Navigation.findNavController(view).navigate(R.id.Dest_ViewTasks);
            }
        });

//        stackBTN.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                long stackexecTime = schedulerApp.executeStack();
//                execTimeTV.setText(String.valueOf(stackexecTime));
//            }
//        });
//
//        queueBTN.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                long queueexecTime = schedulerApp.executeQueue();
//                execTimeTV.setText(String.valueOf(queueexecTime));
//            }
//        });
//
//        linkedListBTN.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                long linkedListExecTime = schedulerApp.executeLinkedList();
//                execTimeTV.setText(String.valueOf(linkedListExecTime));
//            }
//        });

    }

    private void generateArrays(SchedulerApp schedulerApp) {
        fragment_viewTasks.execTimeS = schedulerApp.executeStack();
        fragment_viewTasks.execTimeQ = schedulerApp.executeQueue();
        fragment_viewTasks.execTimeLL = schedulerApp.executeLinkedList();
        fragment_viewTasks.execTimeQLL = schedulerApp.executeQueueLL();
        fragment_viewTasks.stackTasks = schedulerApp.getStackTasks();
        fragment_viewTasks.queueTasks = schedulerApp.getQueueTasks();
        fragment_viewTasks.queueLLTasks = schedulerApp.getQueueLLTasks();
        fragment_viewTasks.linkedListTasks = schedulerApp.getLinkedListTasks();
        fragment_viewTasks.responseTimeS = schedulerApp.getStackResTime();
        fragment_viewTasks.responseTimeQ = schedulerApp.getQueueResTime();
        fragment_viewTasks.responseTimeLL = schedulerApp.getLinkedListResTime();
        fragment_viewTasks.responseTimeQLL = schedulerApp.getQueueLLResTime();
        fragment_viewTasks.avgExecS = (long) schedulerApp.getAvgExecS();
        fragment_viewTasks.avgExecQ = (long) schedulerApp.getAvgExecQ();
        fragment_viewTasks.avgExecLL = (long) schedulerApp.getAvgExecLL();
        fragment_viewTasks.avgExecQLL = (long) schedulerApp.getAvgExecQLL();
    }
}