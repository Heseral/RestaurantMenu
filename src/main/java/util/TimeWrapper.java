package util;

/**
 * Да, это класс который содержит только одну переменную и особо ничего и не делает! Нужно для сериализации,
 * так как джейсон сериализует time как просто цифорку не понятно откуда взявшуюся. Вероятно, без этого класса, я
 * не десеарелизую time.
 */
public class TimeWrapper {
    // используется для тестов и симуляции работы ресторана
    private int time = 0;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
