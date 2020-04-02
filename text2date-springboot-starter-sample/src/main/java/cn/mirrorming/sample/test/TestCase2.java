package cn.mirrorming.sample.test;

import cn.mirrorming.text2date.config.DatetimeRecognizer;
import cn.mirrorming.text2date.time.TimeEntity;
import cn.mirrorming.text2date.time.TimeEntityRecognizer;
import lombok.Cleanup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class TestCase2 {
    public static void main(String[] args) {
        TimeEntityRecognizer timeEntityRecognizer = new TimeEntityRecognizer();
        List<TimeEntity> timeEntities = timeEntityRecognizer.parse("明天下午四点到五点去看电影");
        System.out.println(timeEntities);
    }
}
