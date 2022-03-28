package com.travel.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class DataProcessor {
    /**
     * in order to contract take any String and try parse to int
     * @param positiveSt any String
     * @return int representation of positiveSt;
     * -1 if exception during the cast or string is null or empty;
     */
    public int parsePositiveInt(String positiveSt) {
        if (positiveSt == null || positiveSt.isEmpty()) return -1;
        try {
            return Integer.parseInt(positiveSt);
        } catch (NumberFormatException e) {
            return -1;
        }
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

    /**
     * send 400 error and return true if param t is null; otherwise return false
     * @param errorMessage text on error page
     * @return true if t null; false if t not null
     */
    public <T> boolean isNullSendError(T t, HttpServletResponse resp, String errorMessage) throws IOException {
        if (t == null) {
            resp.sendError(400, errorMessage);
            return true;
        }
        return false;
    }

    /**
     * send 400 error and return true if param test is false; otherwise return false
     * @param errorMessage text on error page
     * @return true if test is false; false if test is true
     */
    public boolean isFalseSendError(boolean test, HttpServletResponse resp, String errorMessage) throws IOException {
        if (!test) {
            resp.sendError(400, errorMessage);
            return true;
        }
        return false;
    }

}
