package com.samill.missionary_backend.authentication.security;

import com.samill.missionary_backend.authentication.dto.GetMemberServiceTypeDto;
import com.samill.missionary_backend.authentication.mapper.AuthenticationMapper;
import com.samill.missionary_backend.common.util.RequestContextUtil;
import com.samill.missionary_backend.member.MemberExternalService;
import com.samill.missionary_backend.member.exception.MemberException;
import com.samill.missionary_backend.token.provider.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


@WebFilter(urlPatterns = "*")
@Order(0)
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    public static final String API_ROOT = "/api";
    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    private final TokenProvider tokenProvider;
    private final MemberExternalService memberManagement;

    // 실제 필터릴 로직
    // 토큰의 인증정보를 SecurityContext에 저장하는 역할 test수행
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
        throws ServletException, IOException {

        String jwt = resolveToken();
        String requestURI = request.getRequestURI();

        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // request uri가 /api/{serviceType} 이 아니면 unauthorized error 발생
            if (isUnauthorizedServiceType(requestURI, authentication)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            logger.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
        } else {
            logger.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
        }
        filterChain.doFilter(request, response);
    }

    private Boolean isUnauthorizedServiceType(
        @NonNull String requestURI,
        @NonNull Authentication authentication
    ) {

        GetMemberServiceTypeDto serviceTypeDto = null;
        try {
            serviceTypeDto = AuthenticationMapper.INSTANCE.memberToGetMemberServiceTypeDto(
                memberManagement.getMemberServiceType(authentication.getName())
            );
        } catch (MemberException e) {
            return Boolean.TRUE;
        }

        return !requestURI.startsWith(String.join("/", API_ROOT, serviceTypeDto.serviceType().getRoot()));
    }

    // Request Header 에서 토큰 정보를 꺼내오기 위한 메소드
    private String resolveToken() {
        String bearerToken = RequestContextUtil.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }
}