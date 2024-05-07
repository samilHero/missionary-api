package com.samill.missionary_backend.common.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.util.Optional;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@UtilityClass
public class RequestContextUtil {

    public void setAttribute(String key, Object value) {
        Optional.ofNullable(RequestContextHolder.getRequestAttributes())
            .ifPresent((var requestAttributes) -> requestAttributes.setAttribute(key, value, RequestAttributes.SCOPE_REQUEST));
    }

    public String getAttribute(String key) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (null != requestAttributes) {
            return Optional.ofNullable(requestAttributes.getAttribute(key, RequestAttributes.SCOPE_REQUEST)).orElseGet(String::new).toString();
        } else {
            return StringUtils.EMPTY;
        }
    }

    public String getHeader(String key) {
        HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        if (key.equals(HttpHeaders.AUTHORIZATION)) {
            try {
                return Optional.ofNullable(servletRequest.getHeader(key)).orElse(StringUtils.EMPTY);
            } catch (Exception e) {
                return StringUtils.EMPTY;
            }
        } else {
            return Optional.ofNullable(servletRequest.getHeader(key)).orElse(StringUtils.EMPTY);
        }
    }

    public Pair<HttpServletRequest, HttpServletResponse> getCurrentHttpReqRes() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();
            return Pair.of(request, response);
        }
        return null;
    }

    public HttpServletRequest getCurrentHttpReq() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            return request;
        }
        return null;
    }

    public String getClientIp() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

            String[] headers = {
                "X-Forwarded-For",
                "Proxy-Client-IP",
                "WL-Proxy-Client-IP",
                "HTTP_CLIENT_IP",
                "HTTP_X_FORWARDED_FOR",
                "X-Real-IP",
                "X-RealIP",
                "REMOTE_ADDR"
            };

            for (String header : headers) {
                String value = request.getHeader(header);
                if (StringUtils.isNotEmpty(value) && !"unknown".equalsIgnoreCase(value)) {
                    return value;
                }
            }
            return request.getRemoteAddr();
        }
        return StringUtils.EMPTY;
    }

    public String getServerIp() {

        InetAddress local = null;
        try {
            local = InetAddress.getLocalHost();
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }

        if (local == null) {
            return StringUtils.EMPTY;
        } else {
            return local.getHostAddress();
        }

    }

}
