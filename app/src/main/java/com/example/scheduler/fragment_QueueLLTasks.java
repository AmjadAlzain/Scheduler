package com.example.scheduler;

import static com.example.scheduler.fragment_viewTasks.queueLLTasks;
import static com.example.scheduler.fragment_viewTasks.queueTasks;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class fragment_QueueLLTasks extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__queuell_tasks, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView responseTime = view.findViewById(R.id.output1TV);
        TextView executionTime = view.findViewById(R.id.output2TV);
        TextView avgExecutionTime = view.findViewById(R.id.output3TV);
        TableLayout queueLLTable = view.findViewById(R.id.queueLLTasksTable);
        String[][] tasks = queueLLTasks;

        TableRow headerRow = new TableRow(getActivity());
        TextView headerTask = createHeaderTextView("Task");
        TextView headerOutput = createHeaderTextView("Output");
        TextView headerExecutionTime = createHeaderTextView("Execution Time");

        headerRow.addView(headerTask);
        headerRow.addView(headerOutput);
        headerRow.addView(headerExecutionTime);

        queueLLTable.addView(headerRow);

        for (int i = 0; i < tasks.length; i++) {
            TableRow tableRow = new TableRow(getActivity());

            for (int j = 0; j < tasks[i].length; j++) {
                TextView textView = new TextView(getActivity());
                textView.setText(tasks[i][j]);
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(11.7F);
                textView.setTypeface(null, Typeface.BOLD);
                textView.setPadding(5, 8, 5, 6);

                // Add TextView to TableRow
                tableRow.addView(textView);
            }

            // Add TableRow to TableLayout
            queueLLTable.addView(tableRow);
        }

        responseTime.setText(String.valueOf(fragment_viewTasks.responseTimeQLL));
        executionTime.setText(String.valueOf(fragment_viewTasks.execTimeQLL));
        avgExecutionTime.setText(String.valueOf(fragment_viewTasks.avgExecQLL));
    }

    private TextView createHeaderTextView(String text) {
        TextView headerTextView = new TextView(getActivity());
        headerTextView.setText(text);
        headerTextView.setGravity(Gravity.CENTER);
        headerTextView.setTextSize(12);
        headerTextView.setTypeface(null, Typeface.BOLD);
        headerTextView.setPadding(6, 6, 6, 6);
        // Customize additional header styling if needed
        headerTextView.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        headerTextView.setTextColor(getResources().getColor(android.R.color.black));
        return headerTextView;
    }
}