package com.qin.genesis.util;

import org.apache.commons.lang3.StringUtils;

public class StringUtil extends StringUtils {

    public static boolean trimEquals(final String v1, String v2){
        return equals(trim(v1),trim(v2));
    }

    public static boolean trimEqualsIgnoreCase(final String v1, String v2){
        return equalsIgnoreCase(trim(v1),trim(v2));
    }

    public static boolean trimStartsWith(String str, String prefix) {
        return startsWith(trim(str), trim(prefix));
    }
}
