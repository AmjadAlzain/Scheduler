package com.example.scheduler;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.scheduler.methods.Task;
import com.google.android.material.tabs.TabLayout;

public class fragment_viewTasks extends Fragment {
    private TabLayout tasksTL;
    private ViewPager tasksVP;
    public static String[][] queueTasks, stackTasks, linkedListTasks, queueLLTasks;
    public static long avgExecQ, avgExecS, avgExecLL, avgExecQLL;
    public static long execTimeQ, execTimeS, execTimeLL, execTimeQLL;
    public static long responseTimeQ, responseTimeS, responseTimeLL, responseTimeQLL;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_tasks, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tasksTL = view.findViewById(R.id.tasksTabLayout);
        tasksVP = view.findViewById(R.id.tasksPager);

        tasksTL.setupWithViewPager(tasksVP);

        ViewPagerAdapter menstruationAdapter = new ViewPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        menstruationAdapter.addFragment(new fragment_StackTasks(), "Stack");
        menstruationAdapter.addFragment(new fragment_QueueTasks(), "Queue");
        menstruationAdapter.addFragment(new fragment_LinkedListTasks(), "LinkedList");
        menstruationAdapter.addFragment(new fragment_QueueLLTasks(), "QueueLL");

        tasksVP.setAdapter(menstruationAdapter);
    }
}