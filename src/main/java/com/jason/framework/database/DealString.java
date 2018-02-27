package com.jason.framework.database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class DealString {
	public static final boolean contains(String[] all, String sel) {
		boolean f = false;
		for (int i = 0; i < all.length; i++) {
			if (((all[i] == null) && (sel == null))
					|| ((all[i] != null) && (all[i].equals(sel)))) {
				f = true;
				break;
			}
		}
		return f;
	}

	public static final boolean isEmpty(String s) {
		if (s == null) {
			return true;
		}
		if (s.equals("")) {
			return true;
		}
		return false;
	}

	public static final boolean isNumber(String s) {
		try {
			if (s == null) {
				return false;
			}
			Double.parseDouble(s);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static final int getLength(String str) {
		int n = 0;
		String chinese = "[Α-￥]";
		String japanese = "[｡-ﾟ]";
		for (int i = 0; i < str.length(); i++) {
			String v = str.substring(i, i + 1);
			if (v.matches(chinese)) {
				n += 2;
			} else if (v.matches(japanese)) {
				n += 2;
			} else {
				n++;
			}
		}
		return n;
	}

	public static final String repeatString(String s, int count) {
		String str = "";
		for (int i = 0; i < count; i++) {
			str = str + s;
		}
		return str;
	}

	public static final String getRndString(String s, int count) {
		char[] cs = s.toCharArray();
		String v = "";
		for (int i = 0; i < count; i++) {
			int pos = DealNumber.getRnd(0, cs.length - 1);
			v = v + cs[pos];
		}
		return v;
	}

	public static final String getRndString(int count) {
		String s = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		String v = getRndString(s, count);
		return v;
	}

	public static final String getRndUEng(int count) {
		String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String v = getRndString(s, count);
		return v;
	}

	public static final String getRndLEng(int count) {
		String s = "abcdefghijklmnopqrstuvwxyz";
		String v = getRndString(s, count);
		return v;
	}

	public static final String getRndNumber(int count) {
		String s = "1234567890";
		String v = getRndString(s, count);
		return v;
	}

	public static final String getRndHanzi() {
		String str = null;

		Random random = new Random();
		int highPos = 176 + Math.abs(random.nextInt(71));
		int lowPos = 161 + Math.abs(random.nextInt(94));

		byte[] bArr = new byte[2];
		bArr[0] = new Integer(highPos).byteValue();
		bArr[1] = new Integer(lowPos).byteValue();
		try {
			str = new String(bArr, "GB2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static final String getRndName() {
		Random random = new Random(System.currentTimeMillis());

		String[] Surname = { "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈",
				"褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许", "何", "吕",
				"施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢",
				"邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范",
				"彭", "郎", "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任",
				"袁", "柳", "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺",
				"倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬", "安", "常", "乐", "于",
				"时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟",
				"平", "黄", "和", "穆", "萧", "尹", "姚", "邵", "湛", "汪", "祁", "毛",
				"禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋",
				"茅", "庞", "熊", "纪", "舒", "屈", "项", "祝", "董", "梁", "杜", "阮",
				"蓝", "闵", "席", "季", "麻", "强", "贾", "路", "娄", "危", "江", "童",
				"颜", "郭", "梅", "盛", "林", "刁", "钟", "徐", "邱", "骆", "高", "夏",
				"蔡", "田", "樊", "胡", "凌", "霍", "虞", "万", "支", "柯", "昝", "管",
				"卢", "莫", "经", "房", "裘", "缪", "干", "解", "应", "宗", "丁", "宣",
				"贲", "邓", "郁", "单", "杭", "洪", "包", "诸", "左", "石", "崔", "吉",
				"钮", "龚", "程", "嵇", "邢", "滑", "裴", "陆", "荣", "翁", "荀", "羊",
				"于", "惠", "甄", "曲", "家", "封", "芮", "羿", "储", "靳", "汲", "邴",
				"糜", "松", "井", "段", "富", "巫", "乌", "焦", "巴", "弓", "牧", "隗",
				"山", "谷", "车", "侯", "宓", "蓬", "全", "郗", "班", "仰", "秋", "仲",
				"伊", "宫", "宁", "仇", "栾", "暴", "甘", "钭", "厉", "戎", "祖", "武",
				"符", "刘", "景", "詹", "束", "龙", "叶", "幸", "司", "韶", "郜", "黎",
				"蓟", "溥", "印", "宿", "白", "怀", "蒲", "邰", "从", "鄂", "索", "咸",
				"籍", "赖", "卓", "蔺", "屠", "蒙", "池", "乔", "阴", "郁", "胥", "能",
				"苍", "双", "闻", "莘", "党", "翟", "谭", "贡", "劳", "逄", "姬", "申",
				"扶", "堵", "冉", "宰", "郦", "雍", "却", "璩", "桑", "桂", "濮", "牛",
				"寿", "通", "边", "扈", "燕", "冀", "浦", "尚", "农", "温", "别", "庄",
				"晏", "柴", "瞿", "阎", "充", "慕", "连", "茹", "习", "宦", "艾", "鱼",
				"容", "向", "古", "易", "慎", "戈", "廖", "庾", "终", "暨", "居", "衡",
				"步", "都", "耿", "满", "弘", "匡", "国", "文", "寇", "广", "禄", "阙",
				"东", "欧", "殳", "沃", "利", "蔚", "越", "夔", "隆", "师", "巩", "厍",
				"聂", "晁", "勾", "敖", "融", "冷", "訾", "辛", "阚", "那", "简", "饶",
				"空", "曾", "毋", "沙", "乜", "养", "鞠", "须", "丰", "巢", "关", "蒯",
				"相", "查", "后", "荆", "红", "游", "郏", "竺", "权", "逯", "盖", "益",
				"桓", "公", "仉", "督", "岳", "帅", "缑", "亢", "况", "郈", "有", "琴",
				"归", "海", "晋", "楚", "闫", "法", "汝", "鄢", "涂", "钦", "商", "牟",
				"佘", "佴", "伯", "赏", "墨", "哈", "谯", "篁", "年", "爱", "阳", "佟",
				"言", "福", "南", "火", "铁", "迟", "漆", "官", "冼", "真", "展", "繁",
				"檀", "祭", "密", "敬", "揭", "舜", "楼", "疏", "冒", "浑", "挚", "胶",
				"随", "高", "皋", "原", "种", "练", "弥", "仓", "眭", "蹇", "覃", "阿",
				"门", "恽", "来", "綦", "召", "仪", "风", "介", "巨", "木", "京", "狐",
				"郇", "虎", "枚", "抗", "达", "杞", "苌", "折", "麦", "庆", "过", "竹",
				"端", "鲜", "皇", "亓", "老", "是", "秘", "畅", "邝", "还", "宾", "闾",
				"辜", "纵", "侴", "万俟", "司马", "上官", "欧阳", "夏侯", "诸葛", "闻人", "东方",
				"赫连", "皇甫", "羊舌", "尉迟", "公羊", "澹台", "公冶", "宗正", "濮阳", "淳于",
				"单于", "太叔", "申屠", "公孙", "仲孙", "轩辕", "令狐", "钟离", "宇文", "长孙",
				"慕容", "鲜于", "闾丘", "司徒", "司空", "兀官", "司寇", "南门", "呼延", "子车",
				"颛孙", "端木", "巫马", "公西", "漆雕", "车正", "壤驷", "公良", "拓跋", "夹谷",
				"宰父", "谷梁", "段干", "百里", "东郭", "微生", "梁丘", "左丘", "东门", "西门",
				"南宫", "第五", "公仪", "公乘", "太史", "仲长", "叔孙", "屈突", "尔朱", "东乡",
				"相里", "胡母", "司城", "张廖", "雍门", "毋丘", "贺兰", "綦毋", "屋庐", "独孤",
				"南郭", "北宫", "王孙" };

		int index = random.nextInt(Surname.length - 1);
		String name = Surname[index];
		if (random.nextBoolean()) {
			name = name + getRndHanzi(2);
		} else {
			name = name + getRndHanzi(1);
		}
		return name;
	}

	public static final String getRndHanzi(int count) {
		String str = "";
		for (int i = 0; i < count; i++) {
			str = str + getRndHanzi();
		}
		return str;
	}

	public static final String get_cardno(String areaCode, String birthday,
			String sex, String idNo) {
		String cardno = areaCode + birthday + idNo + sex;
		String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4",
				"3", "2" };
		String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
				"9", "10", "5", "8", "4", "2" };
		int TotalmulAiWi = 0;
		for (int i = 0; i < 17; i++) {
			TotalmulAiWi += Integer.parseInt(String.valueOf(cardno.charAt(i)))
					* Integer.parseInt(Wi[i]);
		}
		int modValue = TotalmulAiWi % 11;
		String strVerifyCode = ValCodeArr[modValue];
		cardno = areaCode + birthday + idNo + sex + strVerifyCode;
		return cardno;
	}

	public static final String getUnikey() {
		String str1 = DealDate.getNowTimeNo();
		String str2 = intFormat(DealNumber.getRnd(1, 99999), 5);
		String str = str1 + str2;
		return str;
	}

	public static final byte[] codeBytes(byte[] obs, String ocode, String dcode) {
		try {
			String str = new String(obs, ocode);
			return str.getBytes(dcode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static final String codeString(String text, String ocode,
			String dcode) {
		try {
			byte[] b = text.getBytes(ocode);
			return new String(b, dcode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static final String specStr() {
		String str = "'";
		return str;
	}

	public static final String cvtSpecString(String text) {
		String str = text;
		for (int i = 0; i < specStr().length(); i++) {
			String spec = specStr().substring(i, i + 1);
			str = str.replace(spec, spec + spec);
		}
		return str;
	}

	public static final String cutLeftString(String str, int len) {
		String temp = "";
		if (str.length() >= len) {
			temp = str.substring(0, len);
		} else {
			temp = str;
		}
		return temp;
	}

	public static final String cutRightString(String str, int len) {
		String temp = "";
		if (str.length() >= len) {
			temp = str.substring(str.length() - len, str.length());
		}
		return temp;
	}

	public static final String zeroCount(int count) {
		String zero = "";
		for (int i = 0; i < count; i++) {
			zero = zero + "0";
		}
		return zero;
	}

	public static final String intFormat(int i, int count) {
		DecimalFormat number = new DecimalFormat(zeroCount(count));
		String str = number.format(i);
		return str;
	}

	public static final String lngFormat(long l, int count) {
		DecimalFormat number = new DecimalFormat(zeroCount(count));
		String str = number.format(l);
		return str;
	}

	public static final String decFormat(double d, String style) {
		DecimalFormat number = new DecimalFormat(style);
		String str = number.format(d);
		return str;
	}

	public static final String decFormat(double d, int count) {
		if (count == 0) {
			return decFormat(d, "##0");
		}
		String str = decFormat(d, "##0." + zeroCount(count));
		return str;
	}

	public static final String decToRate(double d, int count) {
		double p = 100.0D;
		double t = d * p;
		String str = decFormat(t, count) + "%";
		return str;
	}

	public static final String decToMoney(double d) {
		String str = decToMoney(d, 2);
		return str;
	}

	public static final String decToMoney(double d, int count) {
		if (count == 0) {
			return decFormat(d, "###,##0");
		}
		String str = decFormat(d, "###,##0." + zeroCount(count));
		return str;
	}

	public static final String getValue(Object obj) {
		String str = "";
		if (obj != null) {
			str = obj.toString();
		}
		return str;
	}

	public static final int indexof(String v, String[] s) {
		int pos = -1;
		for (int i = 0; i < s.length; i++) {
			if (v.equals(s[i])) {
				pos = i;
				break;
			}
		}
		return pos;
	}

	public static final String getArrayStr(String[] as, String split) {
		String v = "";
		for (int i = 0; i < as.length - 1; i++) {
			v = v + as[i] + split;
		}
		if (as.length > 0) {
			v = v + as[(as.length - 1)];
		}
		return v;
	}

	public static final void getParamList(ArrayList<String> list, String con,
			String left, String right) {
		if (con == null) {
			return;
		}
		String str = con;
		do {
			int x = str.indexOf(left);
			if (x == -1) {
				break;
			}
			String temp = str.substring(x + left.length());
			int y = temp.indexOf(right);
			if (y == -1) {
				break;
			}
			String v = temp.substring(0, y);
			list.add(v);
			str = temp.substring(y);
		} while (str.length() > 0);
	}

	public static final String[] getParams(String con, String left, String right) {
		ArrayList<String> list = new ArrayList<String>();
		getParamList(list, con, left, right);
		String[] param = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			param[i] = ((String) list.get(i)).toString();
		}
		return param;
	}

	public static final String getParam(String con, String left, String right,
			int pos) {
		String[] params = getParams(con, left, right);
		if (params.length < 1) {
			return null;
		}
		return params[pos];
	}

	public static final String getParamLeft(String str, String left) {
		int x = str.indexOf(left);
		if (x == -1) {
			return null;
		}
		String temp = str.substring(0, x);
		return temp;
	}

	public static final String getParamRight(String str, String right) {
		int x = str.lastIndexOf(right);
		if (x == -1) {
			return null;
		}
		String temp = str.substring(x + right.length());
		return temp;
	}

	public static final String concat(String... args) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < args.length; i++) {
			sb.append(args[i]);
		}
		return sb.toString();
	}

	public static final String[] concat(String[]... args) {
		int num = 0;
		for (int i = 0; i < args.length; i++) {
			if (args[i] != null) {
				num += args[i].length;
			}
		}
		String[] s = new String[num];
		int n = 0;
		for (int i = 0; i < args.length; i++) {
			String[] vs = args[i];
			if (vs != null) {
				for (int j = 0; j < vs.length; j++) {
					s[n] = vs[j];
					n++;
				}
			}
		}
		return s;
	}

	public static final String encode_url(String txt, String code) {
		try {
			return URLEncoder.encode(txt, code);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static final String getString(InputStream is, String code) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, code));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			return sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
