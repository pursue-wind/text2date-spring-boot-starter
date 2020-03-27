package cn.mirrorming.text2date.time;

import lombok.Data;

import java.util.Date;

@Data
public class TimeEntity {
    private String original;
    private Date value;
    private int offset;
    private Cycle cycle;
    private boolean isDateOnly;
    private boolean isStart;
    private boolean isEnd;

    public TimeEntity(String original, int offset) {
        this.original = original;
        this.offset = offset;
    }
}
