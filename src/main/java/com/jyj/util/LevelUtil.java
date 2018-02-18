package com.jyj.util;

import org.apache.commons.lang3.StringUtils;

public class LevelUtil {

    private final static String SEPARATOR = ".";

    private final static String ROOT = "0";

    public static String calulateLevel(String parentLevel, int parentId) {
        if (StringUtils.isBlank(parentLevel)) {
            return ROOT;
        }
        return StringUtils.join(parentLevel, SEPARATOR, parentId);
    }
}
