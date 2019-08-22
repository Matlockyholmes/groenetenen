package be.vdab.groenetenen.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
@Order(2)
public class Auditing {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Before("be.vdab.groenetenen.aop.PointcutExpressions.services()")
    void schrijfAudit(JoinPoint joinPoint) {
        StringBuilder builder =
                new StringBuilder("Tijdstip\t").append(LocalDateTime.now());
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            builder.append("\nGebruiker\t").append(authentication.getName());
        }
        builder.append("\nMethod\t\t").append(joinPoint.getSignature().toLongString());
        Arrays.stream(joinPoint.getArgs())
                .forEach(object -> builder.append("\nParameter\t").append(object));
        logger.info(builder.toString());
    }
}
