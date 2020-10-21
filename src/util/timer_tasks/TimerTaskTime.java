package util.timer_tasks;
import static util.GlobalVar.timeWrapper;

public class TimerTaskTime extends ModifiedTimerTask {
    public TimerTaskTime(long period) {
        super(period);
    }

    @Override
    public void run() {
        timeWrapper.setTime(timeWrapper.getTime() + 1);
        System.out.println(">>>>> " + timeWrapper.getTime());
    }
}
