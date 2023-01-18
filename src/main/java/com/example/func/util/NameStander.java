package com.example.func.util;

import org.apache.commons.lang3.StringUtils;


public class NameStander {

    /**
     * 全角转换为半角
     */
    public static String toDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '\u3000') {
                c[i] = ' ';
            } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
                c[i] = (char) (c[i] - 65248);
            }
        }
        String returnString = new String(c);
        return returnString;
    }

    /**
     * 半角转全角
     */
    public static String toSBC(String input) {
        if (StringUtils.isBlank(input)) {
            return input;
        }
        char[] charArray = input.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == ' ') {
                charArray[i] = '\u3000';
            } else if (charArray[i] < '\177') {
                charArray[i] = (char) (charArray[i] + 65248);
            }
        }
        return new String(charArray);
    }

    /**
     * 半角括号转全角括号
     */
    public static String bracketToSBC(String input) {
        if (StringUtils.isBlank(input)) {
            return input;
        }
        char[] charArray = input.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            // 全角括号不处理
            if (charArray[i] == '\uFF08' || charArray[i] == '\uFF09') {
                // 半角括号转全角
            } else if (charArray[i] == '\u0028' || charArray[i] == '\u0029') {
                charArray[i] = (char) (charArray[i] + 65248);
                // 中文下输入空格单独转
            } else if (charArray[i] == '\u3000') {
                charArray[i] = ' ';
                // 其余字符全角转半角
            } else if (charArray[i] > '\uFF00' && charArray[i] < '\uFF5F') {
                charArray[i] = (char) (charArray[i] - 65248);
            }
        }
        return new String(charArray);
    }

    /**
     * 是否存在中文汉字
     */
    public static boolean containsHanScript(String s) {
        return s.codePoints().anyMatch(
                codepoint ->
                        Character.UnicodeScript.of(codepoint) == Character.UnicodeScript.HAN);
    }

}
