package util.timer_tasks;

public class Task implements Runnable {
    private int launchTime;
    private transient TaskController controlledBy;

    public Task(int launchTime, TaskController controlledBy) {
        setLaunchTime(launchTime);
        setControlledBy(controlledBy);
    }

    public int getLaunchTime() {
        return launchTime;
    }

    public void setLaunchTime(int launchTime) {
        this.launchTime = launchTime;
    }

    public TaskController getControlledBy() {
        return controlledBy;
    }

    public void setControlledBy(TaskController controlledBy) {
        this.controlledBy = controlledBy;
    }

    @Override
    public void run() {
        try {
            throw new Exception("run() is not implemented.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
