package com.jeebase.system.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang.StringUtils;

public class ChineseAndEngLishUtil {
	public static boolean isChinese(String src) {
		String regEx = "[\\u4e00-\\u9fa5]+";
		Pattern compile = Pattern.compile(regEx);
		Matcher matcher = compile.matcher(src);
		if(matcher.find()) {
			return true;
		}
		return false;
	}
	
	public static boolean isEnglish(String src) {
		return src.matches("^[a-zA-Z]*");
	}
	
	public static String converterToFirstSpell(String chines) {
		String pinyinName = "";
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();
		hanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i]>128) {
				try {
					pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], 
							hanyuPinyinOutputFormat)[0].charAt(0);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				pinyinName += nameChar[i];
			}
		}
		return pinyinName;
	}
	/**
	 * 将汉字转换为全拼
	 *
	 * @param text 文本
	 * @param separator 分隔符
	 * @return {@link String}
	 */
	public static String getPinyin(String text, String separator) {
		char[] chars = text.toCharArray();
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		// 设置大小写
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		// 设置声调表示方法
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		// 设置字母u表示方法
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
		String[] s;
		String rs = StringUtils.EMPTY;
		try {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < chars.length; i++) {
				// 判断是否为汉字字符
				if (String.valueOf(chars[i]).matches("[\\u4E00-\\u9FA5]+")) {
					s = PinyinHelper.toHanyuPinyinStringArray(chars[i], format);
					if (s != null) {
						sb.append(s[0]).append(separator);
						continue;
					}
				}
				sb.append(String.valueOf(chars[i]));
				if ((i + 1 >= chars.length) || String.valueOf(chars[i + 1]).matches("[\\u4E00-\\u9FA5]+")) {
					sb.append(separator);
				}
			}
			rs = sb.substring(0, sb.length());
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return rs.toUpperCase();
	}
	public static void main(String[] args) {
		String a = ChineseAndEngLishUtil.getPinyin("范有益","`");
		System.out.println(a);
	}
}
