package com.roytrack.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * 字符串相关实用工具类
 * 
 */
public class StringUtils {

	/**
	 * 生成随即密码
	 * 
	 * @param pwd_len
	 *            生成的密码的总长度
	 * @return 密码的字符串
	 */
	public static String genRandomPwd(int pwd_len) {
		// 35是因为数组是从0开始的，26个字母+10个数字
		final int maxNum = 62;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
				'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
				'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6',
				'7', '8', '9' };
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();

		while (count < pwd_len) {
			// 生成随机数，取绝对值，防止生成负数，
			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}

		return pwd.toString();
	}

	/**
	 * 将字符串按指定次数重复后返回
	 * 
	 * @param str
	 *            需重复的字符串
	 * @param count
	 *            重复次数
	 * @return 重复后的字符串
	 */
	public static String repeatString(String str, int count) {
		StringBuffer sb = new StringBuffer(str.length() * count);

		for (int i = 0; i < count; i++) {
			sb.append(str);
		}

		return sb.toString();
	}

	/**
	 * 
	 * @param fromString
	 * @param splitCharacter
	 * @return
	 */
	public static List<String> splitString(String fromString, String separator) {
		if (fromString == null || fromString.trim().length() < 1)
			return null;

		if (separator == null || separator.trim().length() < 1)
			return null;

		fromString = fromString.trim();
		String[] tempString = fromString.split(separator);
		List<String> list = new ArrayList<String>();

		for (int i = 0; i < tempString.length; i++) {
			if (tempString[i] != null)
				list.add(tempString[i]);
		}

		return list;
	}

	/**
	 * 使用给定的replacement替换此字符串所有匹配给定的宏占位符的子字符串.
	 * 宏占位符"${aB汉123}"将首先转换为正则表达式"\\$\\{aB汉123\\}"。 注意，在替换字符串中使用反斜线 (\) 和美元符号
	 * ($) 可能导致与作为字面值替换字符串时所产生的结果不同。
	 * 
	 * @param str
	 *            待替换的字符串
	 * @param marco
	 *            用来匹配此字符串的宏占位符(格式为"${xyz}"，不区分大小写)
	 * @param replacement
	 *            用来替换每个匹配项的字符串
	 * @return 替换后的字符串
	 */
	public static String replaceString(String str, String macro, String replacement) {
		StringBuffer regex = new StringBuffer();

		for (int i = 0; i < macro.length(); i++) {
			char ch = macro.charAt(i);

			if (ch == '$' || ch == '{' || ch == '}' || ch == '\\') {
				regex.append("\\");
			}

			regex.append(ch);
		}

		Pattern pattern = Pattern.compile(regex.toString(), Pattern.CASE_INSENSITIVE);

		return pattern.matcher(str).replaceAll(replacement);
	}
	
	/**
	 * 返回字符串值，其中包含了连接到一起的数组的所有元素，元素由指定的分隔符分隔开来。
	 * 
	 * @param array
	 *            数组
	 * @param separator
	 *            分隔符
	 * @return String 返回类型
	 */
	public static String mergeArray(String[] array, String separator) {
		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < array.length; i++) {
			if (i > 0)
				buf.append(separator);

			buf.append(array[i]);
		}

		return buf.toString();
	}

	/**
	 * 合并Where条件
	 * 
	 * @param where1
	 *            Where条件1
	 * @param where2
	 *            Where条件2
	 * @return 合并后的Where条件
	 */
	public static String mergeWhere(String where1, String where2) {
		String w1, w2;
		w1 = where1 == null ? "" : where1.trim();
		w2 = where2 == null ? "" : where2.trim();

		return (!w1.equals("") && !w2.equals("")) ? w1 + " and " + w2 : w1 + w2;
	}

	/**
	 * 返回业务单据实体集的条件串 因为in（）中的最大字符数为1000 所以当数据量很大的时候，要把数据按500分组 t.mainid in
	 * (1,2,3) or t.mainid in (4,5,6)
	 * 
	 * @param mainList
	 *            业务单据实体集
	 * @param keyname
	 *            取值字段
	 * @param condiField
	 *            这段集
	 * @return 条件串
	 */
	public static String getKeysCondition(String keys, String condiField) {
		/** SQL条件中IN中的条件不能大于1000 */
		int GROUP_NUM = 999;
		StringBuffer keyscondition = new StringBuffer("");
		String[] idArray = keys.split(",");
		int loop = idArray.length;

		for (int group = 1; group <= (loop / GROUP_NUM + 1); group++) {
			StringBuffer oKeys = new StringBuffer("");

			for (int i = (group - 1) * GROUP_NUM; i < group * GROUP_NUM
					&& i < loop; i++) {
				String keyvalue = idArray[i];
				// 去除重复的主键取值
				if ((oKeys.toString()).indexOf("'" + keyvalue + "'") == -1) {
					if (oKeys.length() > 0)
						oKeys.append(",");

					oKeys.append("'").append(keyvalue).append("'");
				}
			}

			if (keyscondition.length() > 0)
				keyscondition.append(" or ");

			keyscondition.append(condiField + " in (");
			keyscondition.append(oKeys);
			keyscondition.append(")");
		}

		return "(" + keyscondition.toString() + ")";
	}
	
	public static String convertNull(String str) {
		if (str == null || "".equals(str.trim())) {
			return "0";
		} else {
			return str;
		}
	}
	
	public static String convertDouble(Double dou) {
		String str = String.format("%.2f", dou);
		str = str.replace(".00", "");
		
		return str;
	}
	
	/**
	 * 处理非空字符串
	 * @param str
	 * @return 返回保留两位小数str
	 */
	public static String convertString(String str) {
		Double dou = new Double(0);
		if(str!=null&&!"".equals(str)){
			dou = Double.valueOf(str);
			str = String.format("%.2f", dou);
			str = str.replace(".00", "");
		}
		return str;
	}
	
}