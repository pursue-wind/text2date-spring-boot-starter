package cn.mirrorming.text2date.time;

import lombok.Data;

@Data
public class TimeDeltaEntity {
    private String original;
    private long value;
    private int offset;

    public TimeDeltaEntity(String original, int offset) {
        this.original = original;
        this.offset = offset;
    }
}
