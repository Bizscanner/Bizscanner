package store.bizscanner.global.config.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class AopConfig {
    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }
}
