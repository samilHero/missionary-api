package com.samill.missionary_backend.gateway.resolver;

import com.samill.missionary_backend.common.dto.MemberContext;
import com.samill.missionary_backend.member.MemberManagement;
import lombok.NonNull;
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
public class MemberContextHandlerResolver implements HandlerMethodArgumentResolver {

    private final MemberManagement memberManagement;

    @Override
    public boolean supportsParameter(@NonNull MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(MemberContext.class);
    }

    @Override
    public Object resolveArgument(
        @NonNull MethodParameter parameter,
        ModelAndViewContainer mavContainer,
        @NonNull NativeWebRequest webRequest,
        WebDataBinderFactory binderFactory
    ) throws Exception {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var getUserDto = memberManagement.getUserByMemberId(auth.getName());
        return MemberContext.builder()
            .memberId(auth.getName())
            .userId(getUserDto.id())
            .name(getUserDto.name())
            .build();
    }
}
