package model;

import java.util.List;

public class CombinationSale {
    private List<String> first;
    private Integer second;

    public CombinationSale(List<String> first, Integer second) {
        setFirst(first);
        setSecond(second);
    }

    public List<String> getFirst() {
        return first;
    }

    public void setFirst(List<String> first) {
        this.first = first;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }
}
