package util.timer_tasks;
import static util.GlobalVar.timeWrapper;

public class TimerTaskTime extends ModifiedTimerTask {
    public TimerTaskTime(long delay, long period) {
        super(delay, period);
    }

    @Override
    public void run() {
        System.out.println(">>>>> " + timeWrapper.getTime());
        timeWrapper.setTime(timeWrapper.getTime() + 1);
    }
}
