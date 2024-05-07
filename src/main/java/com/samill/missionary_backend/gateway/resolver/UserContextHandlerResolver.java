package com.samill.missionary_backend.gateway.resolver;

import com.samill.missionary_backend.common.dto.UserContext;
import com.samill.missionary_backend.member.MemberManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class UserContextHandlerResolver implements HandlerMethodArgumentResolver {

    private final MemberManagement memberManagement;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(UserContext.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
        ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest,
        WebDataBinderFactory binderFactory) throws Exception {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var getUserDto = memberManagement.getUserByMemberId(auth.getName());
        //TODO: hanbyul-member 정보 조회해서 맞는 값으로 변경
        return UserContext.builder()
            .memberId(auth.getName())
            .userId(getUserDto.id())
            .name(getUserDto.name())
            .build();
    }
}
