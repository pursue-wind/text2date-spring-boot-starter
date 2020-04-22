package cn.mirrorming.text2date.time;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 公历农历时间 转换器
 */
public final class LunarGregorianConverter {
    /**
     * 转换的结果集.year .month .day .isLeap .yearCyl .dayCyl .monCyl
     */
    private int[] result;
    private Calendar calendar;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy.M.d EEEEE");

    public Calendar getCalendar() {
        return calendar;
    }

    /**
     * http://blog.csdn.net/onlyonecoder/article/details/8484118
     */
    private static int[] lunarInfo = {0x04bd8, 0x04ae0, 0x0a570, 0x054d5,
            0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0, 0x055d2, 0x04ae0,
            0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2,
            0x095b0, 0x14977, 0x04970, 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40,
            0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970, 0x06566, 0x0d4a0,
            0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0, 0x1c8d7,
            0x0c950, 0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0, 0x1a5b4, 0x025d0,
            0x092d0, 0x0d2b2, 0x0a950, 0x0b557, 0x06ca0, 0x0b550, 0x15355,
            0x04da0, 0x0a5d0, 0x14573, 0x052d0, 0x0a9a8, 0x0e950, 0x06aa0,
            0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263,
            0x0d950, 0x05b57, 0x056a0, 0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0,
            0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0, 0x195a6, 0x095b0,
            0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40, 0x0af46,
            0x0ab60, 0x09570, 0x04af5, 0x04970, 0x064b0, 0x074a3, 0x0ea50,
            0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0, 0x0c960, 0x0d954,
            0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0,
            0x0cab5, 0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9, 0x04ba0,
            0x0a5b0, 0x15176, 0x052b0, 0x0a930, 0x07954, 0x06aa0, 0x0ad50,
            0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260, 0x0ea65, 0x0d530,
            0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0, 0x1d0b6,
            0x0d250, 0x0d520, 0x0dd45, 0x0b5a0, 0x056d0, 0x055b2, 0x049b0,
            0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0};
    /**
     * 节气
     */
    private final static long[] S_TERM_INFO = new long[]{0, 21208, 42467,
            63836, 85337, 107014, 128867, 150921, 173149, 195551, 218072,
            240693, 263343, 285989, 308563, 331033, 353350, 375494, 397447,
            419210, 440795, 462224, 483532, 504758};

    private static final String[] SOLAR_TERM = new String[]{"小寒", "大寒", "立春", "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至", "小暑", "大暑", "立秋", "处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪", "冬至"};

    /**
     * 键值：0-7位表示日期，8-11位表示月份，5-7位与12-15位恒为0
     */
    private final static Map<Integer, String> LUNAR_HOLIDAY_MAP = new HashMap<>(11);

    /**
     * 键值：0-7位表示日期，8-11位表示月份，5-7位与12-15位恒为0
     */
    private final static Map<Integer, String> HOLIDAY_MAP = new HashMap<>(23);

    static {
        // 农历节日
        LUNAR_HOLIDAY_MAP.put(0x0101, "春节");
        LUNAR_HOLIDAY_MAP.put(0x010f, "元宵");
        LUNAR_HOLIDAY_MAP.put(0x0505, "端午");
        LUNAR_HOLIDAY_MAP.put(0x0707, "七夕");
        LUNAR_HOLIDAY_MAP.put(0x070f, "中元");
        LUNAR_HOLIDAY_MAP.put(0x080f, "中秋");
        LUNAR_HOLIDAY_MAP.put(0x0909, "重阳");
        LUNAR_HOLIDAY_MAP.put(0x0c08, "腊八");
        LUNAR_HOLIDAY_MAP.put(0x0c18, "小年");
        LUNAR_HOLIDAY_MAP.put(0x0c1e, "除夕");

        // 公历节日
        HOLIDAY_MAP.put(0x0101, "元旦");
        HOLIDAY_MAP.put(0x020e, "情人节");
        HOLIDAY_MAP.put(0x0308, "妇女节");
        HOLIDAY_MAP.put(0x030c, "植树节");
        HOLIDAY_MAP.put(0x030f, "消费者权益日");
        HOLIDAY_MAP.put(0x0401, "愚人节");
        HOLIDAY_MAP.put(0x0501, "劳动节");
        HOLIDAY_MAP.put(0x0504, "青年节");
        HOLIDAY_MAP.put(0x050c, "护士节");
        HOLIDAY_MAP.put(0x0601, "儿童节");
        HOLIDAY_MAP.put(0x0701, "建党节");
        HOLIDAY_MAP.put(0x0801, "建军节");
        HOLIDAY_MAP.put(0x0808, "爸爸节");
        HOLIDAY_MAP.put(0x090a, "教师节");
        HOLIDAY_MAP.put(0x091c, "孔子诞辰");
        HOLIDAY_MAP.put(0x0a01, "国庆节");
        HOLIDAY_MAP.put(0x0a06, "老人节");
        HOLIDAY_MAP.put(0x0a18, "联合国日");
        HOLIDAY_MAP.put(0x0b0c, "孙中山诞辰纪念");
        HOLIDAY_MAP.put(0x0c14, "澳门回归纪念");
        HOLIDAY_MAP.put(0x0c19, "圣诞");
        HOLIDAY_MAP.put(0x0c18, "平安夜");
        HOLIDAY_MAP.put(0x0b01, "万圣节");
    }


    private static String[] Gan = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    private static String[] Zhi = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
    private static String[] Animals = {"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};
    private static String[] nStr1 = {"日", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十"};
    private static String[] nStr2 = {"初", "十", "廿", "卅", "　"};
    private static String[] monthNong = {"正", "正", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"};
    private static String[] yearName = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};

    public LunarGregorianConverter() {
        this.calendar = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);
        convert();
    }

    public LunarGregorianConverter(int year, int month, int day) {
        calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        convert();// 转换日期
    }

    /**
     * @param year
     * @param month
     * @param day
     * @param hourOfDay 24小时制(0-23)
     * @param minute
     */
    public LunarGregorianConverter(int year, int month, int day, int hourOfDay, int minute) {
        calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, hourOfDay, minute);
        convert();// 转换日期
    }

    public LunarGregorianConverter(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        convert();// 转换日期
    }

    public LunarGregorianConverter(Calendar calendar) {
        this.calendar = calendar;
        convert();// 转换日期
    }

    /**
     * 农历年份的总天数
     *
     * @param year 农历年份
     * @return res
     */
    private static int totalDaysOfYear(int year) {
        // 29*12
        int sum = 348;
        for (int i = 0x8000; i > 0x8; i >>= 1) {
            // 大月 +1 天
            sum += (lunarInfo[year - 1900] & i) == 0 ? 0 : 1;
        }
        // +闰月的天数
        return (sum + leapDays(year));
    }

    /**
     * 农历 year年闰月的天数
     *
     * @param year 年
     * @return year年闰月的天数
     */
    private static int leapDays(int year) {
        int result = 0;
        if (leapMonth(year) != 0) {
            result = (lunarInfo[year - 1900] & 0x10000) == 0 ? 29 : 30;
        }
        return result;
    }

    /**
     * 传农历 year年闰哪个月 1-12 , 没闰传回 0
     *
     * @param year 农历年份
     * @return year年闰哪个月 1-12 , 没闰传回 0
     */
    private static int leapMonth(int year) {
        return (lunarInfo[year - 1900] & 0xf);
    }

    /**
     * 农历 y 年 m 月的总天数
     *
     * @param y y 年
     * @param m m 月
     * @return 总天数
     */
    private static int monthDays(int y, int m) {
        return ((lunarInfo[y - 1900] & (0x10000 >> m)) == 0 ? 29 : 30);
    }

    /**
     * 将传入的日期转换为农历
     */
    private void convert() {
        // 基准时间 1900-01-31是农历1900年正月初一
        Calendar baseCalendar = Calendar.getInstance();
        // 1900-01-31是农历1900年正月初一
        baseCalendar.set(1900, 0, 31, 0, 0, 0);
        Date baseDate = baseCalendar.getTime();
        // 偏移量（天）// 天数(86400000=24*60*60*1000)
        int offset = (int) ((calendar.getTimeInMillis() - baseDate.getTime()) / 86400000);
        // 基准时间在天干地支纪年法中的位置
        // 1898-10-01是农历甲子月
        int monCyl = 14;
        // 1899-12-21是农历1899年腊月甲子日
        int dayCyl = offset + 40;

        // 得到年数
        int i;
        int temp = 0;
        for (i = 1900; i < 2050 && offset > 0; i++) {
            // 农历每年天数
            temp = totalDaysOfYear(i);
            offset -= temp;
            monCyl += 12;
        }
        if (offset < 0) {
            offset += temp;
            i--;
            monCyl -= 12;
        }
        // 农历年份
        int year = i;
        // 1864年是甲子年
        int yearCyl = i - 1864;
        // 闰哪个月
        int leap = leapMonth(i);
        boolean isLeap = false;
        int j;
        for (j = 1; j < 13 && offset > 0; j++) {
            // 闰月
            if (leap > 0 && j == (leap + 1) && !isLeap) {
                --j;
                isLeap = true;
                temp = leapDays(year);
            } else {
                temp = monthDays(year, j);
            }
            // 解除闰月
            if (isLeap && j == (leap + 1)) {
                isLeap = false;
            }
            offset -= temp;
            if (!isLeap) {
                monCyl++;
            }
        }
        if (offset == 0 && leap > 0 && j == leap + 1) {
            if (isLeap) {
                isLeap = false;
            } else {
                isLeap = true;
                --j;
                --monCyl;
            }
        }
        if (offset < 0) {
            offset += temp;
            --j;
            --monCyl;
        }
        // 农历月份
        int month = j;
        // 农历天
        int day = offset + 1;
        result = new int[]{year, month, day, isLeap ? 1 : 0, yearCyl, monCyl, dayCyl};
    }

    /**
     * 获取偏移量对应的干支, 0=甲子
     *
     * @param num 偏移量（年or月or日）
     * @return 偏移量对应的干支
     */
    private static String cyclical(int num) {
        return (Gan[num % 10] + Zhi[num % 12]);
    }

    /**
     * 中文日期
     *
     * @param day 天数
     * @return 中文日期
     */
    private static String chineseDay(int day) {
        String result;
        switch (day) {
            case 10:
                result = "初十";
                break;
            case 20:
                result = "二十";
                break;
            case 30:
                result = "三十";
                break;
            default:
                // 取商
                result = nStr2[(int) (day / 10)];
                // 取余
                result += nStr1[day % 10];
        }
        return (result);
    }

    /**
     * 大写年份
     *
     * @param y 年
     * @return 大写年份
     */
    public static String chineseYear(int y) {
        String s = " ";
        int d;
        while (y > 0) {
            d = y % 10;
            y = (y - d) / 10;
            s = yearName[d] + s;
        }
        return (s);
    }

    /**
     * 输出格式：2015.07.04 周六 乙未[羊]年 壬午月 辛巳日
     *
     * @return s
     */
    public String getLunarDate() {
        String s = sdf.format(calendar.getTime()) + " ";
        s += cyclical(result[4]) + "[" + Animals[(result[0] - 4) % 12] + "]年 ";
        s += cyclical(result[5]) + "月 ";
        s += cyclical(result[6]) + "日";
        return s;
    }

    /**
     * 输出格式：五月十九
     *
     * @return s
     */
    public String getLunarDay() {
        return (result[3] == 1 ? "闰" : "") + monthNong[result[1]] + "月" + chineseDay(result[2]);
    }

    public String getLunarMonth() {
        return (result[3] == 1 ? "闰" : "") + monthNong[result[1]] + "月";
    }

    public String getLunarDayOfMonth() {
        return chineseDay(result[2]);
    }

    /**
     * 获取时辰，输出格式：戊子时
     *
     * @return s
     */
    public String getLunarTime() {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int timeOffset = (result[6] % 10) * 24 + hour;
        return Gan[((timeOffset + 1) / 2) % 10] + Zhi[((hour + 1) / 2) % 12] + "时";
    }

    /**
     * 核心方法 根据日期(y年m月d日)得到节气
     *
     * @return s
     */
    public String getSoralTerm() {
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        String solarTerms;
        if (d == sTerm(y, m * 2)) {
            solarTerms = SOLAR_TERM[m * 2];
        } else if (d == sTerm(y, m * 2 + 1)) {
            solarTerms = SOLAR_TERM[m * 2 + 1];
        } else {
            // 到这里说明非节气时间
            solarTerms = null;
        }
        return solarTerms;
    }

    /**
     * y年的第n个节气为几日(从0小寒起算)
     *
     * @return i
     */
    public int sTerm(int y, int n) {
        Calendar cal = Calendar.getInstance();
        cal.set(1900, 0, 6, 2, 5, 0);
        long temp = cal.getTime().getTime();
        cal.setTimeInMillis((long) ((31556925974.7 * (y - 1900) + S_TERM_INFO[n] * 60000L) + temp));
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 农历节日
     *
     * @return s
     */
    public String getLunarHoliday() {
        int temp;
        if (result[2] == 29 && !isBigMonth(result[0], result[1])) {
            // 如果是小月，29日为最后一天，将其加为30日算
            temp = (result[1] << 8) + 30;
        } else {
            temp = (result[1] << 8) + result[2];
        }
        return LUNAR_HOLIDAY_MAP.get(temp);
    }

    /**
     * 公历节日
     *
     * @return s
     */
    public String getHoliday() {
        int m = calendar.get(Calendar.MONTH) + 1;
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        int temp = (m << 8) + d;
        return HOLIDAY_MAP.get(temp);
    }

    /**
     * 判断 y年m月 是大月还是小月
     *
     * @param y y年
     * @param m m月
     * @return true:大月，false:小月
     */
    private boolean isBigMonth(int y, int m) {
        return ((lunarInfo[y - 1900] & (0x10000 >> m)) != 0);
    }

    /**
     * 后一天
     */
    public void nextDay() {
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        convert();
    }


    public static void main(String[] args) {
        LunarGregorianConverter lunarGregorianConverter = new LunarGregorianConverter();
        boolean bigMonth = lunarGregorianConverter.isBigMonth(2019, 2);
        String lunarDate = lunarGregorianConverter.getLunarDate();
        String s2 = lunarGregorianConverter.getHoliday();
        String s3 = lunarGregorianConverter.getHoliday();
        String s4 = lunarGregorianConverter.getLunarMonth();
        String s5 = LunarGregorianConverter.chineseYear(2019);
        System.out.println(bigMonth);
    }
}
