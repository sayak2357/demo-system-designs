package com.taskScheduler;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

class ScheduledTask implements Comparable<ScheduledTask> {
    private final Runnable task;
    private final long scheduledTime;

    public ScheduledTask(Runnable task, long scheduledTime) {
        this.task = task;
        this.scheduledTime = scheduledTime;
    }

    public Runnable getTask() {
        return task;
    }

    public long getScheduledTime() {
        return scheduledTime;
    }

    @Override
    public int compareTo(ScheduledTask other) {
        return Long.compare(this.scheduledTime, other.scheduledTime);
    }
}

class TaskScheduler {

    private final PriorityQueue<ScheduledTask> queue = new PriorityQueue<>();
    private final Lock lock = new ReentrantLock();
    private final Condition newTaskArrived = lock.newCondition();
    private final ExecutorService workerPool = Executors.newFixedThreadPool(4);

    public TaskScheduler() {
        Thread schedulerThread = new Thread(this::runScheduler);
        schedulerThread.setDaemon(true);
        schedulerThread.start();
    }

    public void schedule(Runnable task, long delayMillis) {
        long executionTime = System.currentTimeMillis() + delayMillis;

        lock.lock();
        try {
            queue.offer(new ScheduledTask(task, executionTime));
            newTaskArrived.signal();
        } finally {
            lock.unlock();
        }
    }

    private void runScheduler() {
        while (true) {
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    newTaskArrived.await();
                }

                ScheduledTask nextTask = queue.peek();
                long now = System.currentTimeMillis();
                long waitTime = nextTask.getScheduledTime() - now;

                if (waitTime > 0) {
                    newTaskArrived.await(waitTime, TimeUnit.MILLISECONDS);
                } else {
                    queue.poll();
                    workerPool.submit(nextTask.getTask());
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }
}