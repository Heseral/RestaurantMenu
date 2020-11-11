package util.timer_tasks;

import java.util.TimerTask;

public abstract class ModifiedTimerTask extends TimerTask implements Cloneable {
    // на момент сериализации было время в миллисекундах...
    private long currentTimeMillis;
    // на момент сериализации таск должен был быть запущен во время в миллисекундах...
    private long launchTimeMillis;
    // период запуска таска, момент сериализации роли не играет
    private long periodMillis;

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            throw new Exception("Unsupported clone()");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ModifiedTimerTask(long periodMillis) {
        setPeriodMillis(periodMillis);
    }

    public long getLaunchTimeMillis() {
        return launchTimeMillis;
    }

    public void setLaunchTimeMillis(long launchTimeMillis) {
        this.launchTimeMillis = launchTimeMillis;
    }

    public long getPeriodMillis() {
        return periodMillis;
    }

    public void setPeriodMillis(long periodMillis) {
        this.periodMillis = periodMillis;
    }

    public long getCurrentTimeMillis() {
        return currentTimeMillis;
    }

    public void setCurrentTimeMillis(long currentTimeMillis) {
        this.currentTimeMillis = currentTimeMillis;
    }

    @Override
    public void run() {
        try {
            throw new Exception("Unsupported TimerTask.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
