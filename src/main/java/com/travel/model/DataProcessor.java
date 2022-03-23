package com.travel.model;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class DataProcessor {
    /**
     * in order to contract take String and try parse to int
     * @param positiveSt String representation of positive int in range [1; Integer.MAX_VALUE]
     * @return int in range [-Integer.MIN_VALUE; + Integer.MAX_VALUE] if pageSt is an int representation;
     * -1 as an exception during the cast
     */
    public int parsePositiveInt(String positiveSt) {
        int i;
        try {
            i = Integer.parseInt(positiveSt);
        } catch (NumberFormatException e) {
            return -1;
        }
        return i;
    }

    /**
     * @param exceptParam parameter that exclude in uri
     * @return uri with all parameters except exceptParam
     */
    public String prepareURI(HttpServletRequest req, String exceptParam) {
        StringBuilder sb = new StringBuilder();
        Map<String, String[]> map = req.getParameterMap();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            String key = entry.getKey();
            if (!exceptParam.equals(key)) {
                sb.append(key)
                        .append("=")
                        .append(entry.getValue()[0])
                        .append("&");
            }
        }
        return "ShowFound?" + sb.toString();
    }

}
