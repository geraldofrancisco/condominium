package com.p2p.condominium.util;

import org.apache.commons.lang3.StringUtils;

public class CpfCnpjUtil {
    public static String removeSpecialCharacters(String identification) {
        if(StringUtils.isBlank(identification))
            return null;

        return identification
                .replace(".","")
                .replace("-", "")
                .replace("/", "");
    }
}
