package cn.mirrorming.text2date.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

/**
 * 节假日转换器
 *
 * @author Mireal
 */
public class Holiday2StringConverter {
    /**
     * HOLIDAY
     */
    public final static String[] HOLIDAY = {
            "小寒", "大寒", "立春", "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至", "小暑", "大暑", "立秋", "处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪", "冬至",

            "春节", "元宵", "端午", "七夕", "中元", "中秋", "重阳", "腊八", "小年", "除夕", "元旦", "情人节", "妇女节", "植树节", "消费者权益日",
            "愚人节", "劳动节", "青年节", "护士节", "儿童节", "建党节", "建军节", "爸爸节", "教师节", "孔子诞辰", "国庆节", "老人节", "联合国日",
            "孙中山诞辰纪念", "澳门回归纪念", "平安夜", "万圣节", "圣诞"};

    private static HashMap<String, String> holidayMap = new HashMap<>();
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

    static {
        for (String e : HOLIDAY) {
            holidayMap.put(e, "");
        }
        int year = Calendar.getInstance().get(Calendar.YEAR);
        LunarGregorianConverter time = new LunarGregorianConverter(year, 1, 1);
        int size = holidayMap.size();
        while (size > 0) {
            // 农历节日
            String lunarHoliday = time.getLunarHoliday();
            // 公历节日
            String holiday = time.getHoliday();
            // 节气
            String solarTerms = time.getSoralTerm();

            if (holidayMap.containsKey(lunarHoliday)) {
                String value = sdf.format(time.getCalendar().getTime());
                holidayMap.put(lunarHoliday, value);
                size--;
            }

            if (holidayMap.containsKey(holiday)) {
                String value = sdf.format(time.getCalendar().getTime());
                holidayMap.put(holiday, value);
                size--;
            }

            if (holidayMap.containsKey(solarTerms)) {
                String value = sdf.format(time.getCalendar().getTime());
                holidayMap.put(solarTerms, value);
                size--;
            }
            time.nextDay();
        }


//        holidayMap.put("黑人日", "01月03日");
//        holidayMap.put("腊八节", "01月05日");
//        holidayMap.put("国际海关日", "01月26日");
//        holidayMap.put("国际麻风节", "01月31日");
//        holidayMap.put("二月节日世界湿地日", "02月02日");
//        holidayMap.put("世界抗癌症日", "02月04日");
//        holidayMap.put("国际声援南非日", "02月7日");
//        holidayMap.put("国际气象节", "02月10日");
//        holidayMap.put("国际母语日", "02月21日");
//        holidayMap.put("第三世界青年日", "02月24日");
//        holidayMap.put("龙抬头", "02月27日");
//        holidayMap.put("国际罕见病日", "02月29日");
//        holidayMap.put("三月节日国际海豹日", "03月01日");
//        holidayMap.put("国际爱耳日", "03月03日");
//        holidayMap.put("学雷锋日", "03月05日");
//        holidayMap.put("三八妇女节", "03月08日");
//        holidayMap.put("白色情人节", "03月14日");
//        holidayMap.put("国际航海日", "03月17日");
//        holidayMap.put("世界无肉日", "03月20日");
//        holidayMap.put("春分", "03月20日");
//        holidayMap.put("世界睡眠日", "03月21日");
//        holidayMap.put("世界水日", "03月22日");
//        holidayMap.put("世界气象日", "03月23日");
//        holidayMap.put("四月节日愚人节", "04月01日");
//        holidayMap.put("国际儿童图书日", "04月02日");
//        holidayMap.put("寒食节", "04月03日");
//        holidayMap.put("清明节", "04月04日");
//        holidayMap.put("世界卫生日", "04月07日");
//        holidayMap.put("世界帕金森日", "04月11日");
//        holidayMap.put("谷雨", "04月20日");
//        holidayMap.put("世界地球日", "04月22日");
//        holidayMap.put("世界读书日", "04月23日");
//        holidayMap.put("世界儿童日", "04月24日");
//        holidayMap.put("知识产权日", "04月26日");
//        holidayMap.put("佛诞", "04月29日");
//        holidayMap.put("全国交通安全反思日", "04月30日");
//        holidayMap.put("五月节日国际劳动节", "05月01日");
//        holidayMap.put("五四青年节", "05月04日");
//        holidayMap.put("立夏", "05月05日");
//        holidayMap.put("世界红十字日", "05月08日");
//        holidayMap.put("世界微笑日", "05月08日");
//        holidayMap.put("母亲节", "05月14日");
//        holidayMap.put("国际护士节", "05月12日");
//        holidayMap.put("国际家庭日", "05月15日");
//        holidayMap.put("全国助残日", "05月15日");
//        holidayMap.put("世界电信日", "05月17日");
//        holidayMap.put("国际博物馆日", "05月18日");
//        holidayMap.put("小满", "05月21日");
//        holidayMap.put("五卅运动纪念", "05月30日");
//        holidayMap.put("端午节", "05月30日");
//        holidayMap.put("世界无烟日", "05月31日");
//        holidayMap.put("六月节日六一儿童节", "06月01日");
//        holidayMap.put("世界环境日", "06月05日");
//        holidayMap.put("芒种", "06月05日");
//        holidayMap.put("全国爱眼日", "06月06日");
//        holidayMap.put("中国人口日", "06月11日");
//        holidayMap.put("父亲节", "06月18日");
//        holidayMap.put("世界难民日", "06月20日");
//        holidayMap.put("夏至", "06月21日");
//        holidayMap.put("中国儿童慈善活动日", "06月22日");
//        holidayMap.put("国际奥林匹克日", "06月23日");
//        holidayMap.put("全国土地日", "06月25日");
//        holidayMap.put("国际禁毒日", "06月26日");
//        holidayMap.put("世界青年联欢节", "06月30日");
//        holidayMap.put("七月节日中国共产党诞生日", "07月01日");
//        holidayMap.put("香港回归日", "07月01日");
//        holidayMap.put("国际体育记者日", "07月02日");
//        holidayMap.put("国际接吻日", "07月06日");
//        holidayMap.put("小暑", "07月07日");
//        holidayMap.put("世界人口日", "07月11日");
//        holidayMap.put("大暑", "07月22日");
//        holidayMap.put("非洲妇女日", "07月30日");
//        holidayMap.put("八月节日八一建军节", "08月01日");
//        holidayMap.put("日本投降日", "08月15日");
//        holidayMap.put("处暑", "08月23日");
//        holidayMap.put("九月节日抗日战争胜利纪念日", "09月03日");
//        holidayMap.put("中元节", "09月05日");
//        holidayMap.put("白露", "09月07日");
//        holidayMap.put("国际扫盲日", "09月08日");
//        holidayMap.put("教师节", "09月10日");
//        holidayMap.put("国际臭氧层保护日", "09月16日");
//        holidayMap.put("九一八事变纪念日", "09月18日");
//        holidayMap.put("国际和平日", "09月20日");
//        holidayMap.put("国际爱牙日", "09月21日");
//        holidayMap.put("世界无车日", "09月22日");
//        holidayMap.put("秋分", "09月23日");
//        holidayMap.put("国际聋人节", "09月24日");
//        holidayMap.put("世界旅游日", "09月27日");
//        holidayMap.put("十月节日国庆节", "10月01日");
//        holidayMap.put("世界动物日", "10月04日");
//        holidayMap.put("中秋节", "10月04日");
//        holidayMap.put("寒露", "10月08日");
//        holidayMap.put("世界邮政日", "10月09日");
//        holidayMap.put("辛亥革命纪念日", "10月10日");
//        holidayMap.put("中国少年先锋队诞辰日", "10月13日");
//        holidayMap.put("世界标准日", "10月14日");
//        holidayMap.put("国际盲人节", "10月15日");
//        holidayMap.put("世界粮食日", "10月16日");
//        holidayMap.put("国际消除贫困日", "10月17日");
//        holidayMap.put("世界传统医药日", "10月22日");
//        holidayMap.put("霜降", "10月23日");
//        holidayMap.put("联合国日", "10月24日");
//        holidayMap.put("重阳", "10月28日");
//        holidayMap.put("世界勤俭日", "10月31日");
//        holidayMap.put("万圣节", "10月31日");
//        holidayMap.put("十一月节日立冬", "11月07日");
//        holidayMap.put("中国记者日", "11月08日");
//        holidayMap.put("世界青年节", "11月10日");
//        holidayMap.put("光棍节", "11月11日");
//        holidayMap.put("世界糖尿病日", "11月14日");
//        holidayMap.put("国际大学生节", "11月17日");
//        holidayMap.put("世界问候日", "11月21日");
//        holidayMap.put("小雪", "11月22日");
//        holidayMap.put("感恩节", "11月23日");
//        holidayMap.put("十二月节日世界艾滋病日", "12月01日");
//        holidayMap.put("国际残疾人日", "12月03日");
//        holidayMap.put("大雪", "12月07日");
//        holidayMap.put("一二九运动纪念日", "12月09日");
//        holidayMap.put("世界足球日", "12月09日");
//        holidayMap.put("世界人权日", "12月10日");
//        holidayMap.put("西安事变纪念日", "12月12日");
//        holidayMap.put("南京大屠杀", "12月13日");
//        holidayMap.put("澳门回归日", "12月20日");
//        holidayMap.put("国际篮球日", "12月21日");
//        holidayMap.put("冬至", "12月22日");
//        holidayMap.put("平安夜", "12月24日");
//        holidayMap.put("圣诞节", "12月25日");
    }


    /**
     * 节日转化为时间
     *
     * @param holiday 文本
     * @return String yyyy年MM月dd日 or null
     */
    public static String holidayDate(String holiday) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        // 处理 感恩节、父亲节、母亲节等这类的节日
        if (holiday.trim().contains("节")) {
            String[] s = {"感恩节", "父亲节", "母亲节"};
            Calendar cal = Calendar.getInstance();
            if (holiday.equals(s[0])) {
                cal.set(Calendar.MONTH, 10);
                cal.set(Calendar.WEEK_OF_MONTH, 4);
                cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
                return simpleDateFormat.format(cal.getTime());
            } else if (holiday.equals(s[1])) {
                cal.set(Calendar.MONTH, 5);
                cal.set(Calendar.WEEK_OF_MONTH, 3);
                cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                return simpleDateFormat.format(cal.getTime());
            } else if (holiday.equals(s[2])) {
                cal.set(Calendar.MONTH, 4);
                cal.set(Calendar.WEEK_OF_MONTH, 2);
                cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                return simpleDateFormat.format(cal.getTime());
            }
        }

        if (holidayMap.containsKey(holiday)) {
            return holidayMap.get(holiday);
        } else if (!holidayMap.containsKey(holiday) && holiday.contains("节")) {
            holiday = holiday.replace("节", "");
            return holidayMap.get(holiday);
        } else {
            return holidayMap.getOrDefault(holiday.concat("节"), null);
        }
    }
}
