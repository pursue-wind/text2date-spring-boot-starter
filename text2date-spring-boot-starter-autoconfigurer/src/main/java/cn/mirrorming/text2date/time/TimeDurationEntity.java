package cn.mirrorming.text2date.time;

import lombok.Data;

@Data
public class TimeDurationEntity {
    private String original;
    private long value;
    private int offset;

    public TimeDurationEntity(String original, int offset) {
        this.original = original;
        this.offset = offset;
    }
}
