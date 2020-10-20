package util.timer_tasks;

import java.util.TimerTask;

public abstract class ModifiedTimerTask extends TimerTask {
    private long delayMillis;
    private long periodMillis;

    public ModifiedTimerTask(long delayMillis, long periodMillis) {
        setDelayMillis(delayMillis);
        setPeriodMillis(periodMillis);
    }

    public long getDelayMillis() {
        return delayMillis;
    }

    public void setDelayMillis(long delayMillis) {
        this.delayMillis = delayMillis;
    }

    public long getPeriodMillis() {
        return periodMillis;
    }

    public void setPeriodMillis(long periodMillis) {
        this.periodMillis = periodMillis;
    }
}
