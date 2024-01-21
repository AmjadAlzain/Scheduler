package com.example.scheduler;

import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.scheduler.methods.LinkedListScheduler;
import com.example.scheduler.methods.QueueScheduler;
import com.example.scheduler.methods.QueueSchedulerLL;
import com.example.scheduler.methods.StackScheduler;
import com.example.scheduler.methods.Task;

import java.io.IOException;
import java.io.InputStream;


public class fragmentSchedulerHome extends Fragment {


    // Objects to store runtime and execution time to get turnaround time

    private long responseTimeLL, responseTimeQLL, responseTimeS, responseTimeQ;
    private long executionTimeLL, executionTimeQLL, executionTimeS, executionTimeQ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scheduler_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinkedListScheduler<Task> linkedList = new LinkedListScheduler<>();
        StackScheduler<Task> stack = new StackScheduler<>();
        QueueSchedulerLL<Task> queueLL = new QueueSchedulerLL<>();
        QueueScheduler<Task> queue= new QueueScheduler<>();

        Button executeBTN = view.findViewById(R.id.executeBTN);
        Button parseBTN = view.findViewById(R.id.parseBTN);
        Button executeSJFBTN = view.findViewById(R.id.executeSJFBTN);

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
                executionTimeS = stack.executeTasks();
                ET_S.setText(String.valueOf(executionTimeS));
                executionTimeQ = queue.executeTasks();
                ET_Q.setText(String.valueOf(executionTimeQ));
                executionTimeLL = linkedList.executeTasks();
                ET_LL.setText(String.valueOf(executionTimeLL));
                executionTimeQLL = queueLL.executeTasks();
                ET_QLL.setText(String.valueOf(executionTimeQLL));

                // Turnaround time
                TT_S.setText(String.valueOf(responseTimeS+executionTimeS));
                TT_Q.setText(String.valueOf(responseTimeQ+executionTimeQ));
                TT_LL.setText(String.valueOf(responseTimeLL+executionTimeLL));
                TT_QLL.setText(String.valueOf(responseTimeQLL+executionTimeQLL));
            }
        });

        parseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AssetManager assetManager = getContext().getAssets();
                    InputStream inputStream = assetManager.open("tasks.txt");
                    responseTimeS = stack.parseTasks(inputStream);
                    RT_S.setText(String.valueOf(responseTimeS));
                    responseTimeQ= queue.parseTasks(inputStream);
                    RT_Q.setText(String.valueOf(responseTimeQ));
                    responseTimeLL = linkedList.parseTasks(inputStream);
                    RT_LL.setText(String.valueOf(responseTimeLL));
                    responseTimeQLL = queueLL.parseTasks(inputStream);
                    RT_QLL.setText(String.valueOf(responseTimeQLL));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        executeSJFBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }
}