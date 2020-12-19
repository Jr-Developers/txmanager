package io.github.jr.developers.txmanager.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ChainedTransactionManagerConfig {

    @Bean
    @Primary // 기본존의 @Primary는 해당 PlatformTransactionManager로 변경
    public PlatformTransactionManager chainedTransactionManager(
            @Qualifier("clubTransactionManager") PlatformTransactionManager clubTransactionManager,
            @Qualifier("userTransactionManager") PlatformTransactionManager userTransactionManager) {
        return new ChainedTransactionManager(clubTransactionManager, userTransactionManager);
    }
}
