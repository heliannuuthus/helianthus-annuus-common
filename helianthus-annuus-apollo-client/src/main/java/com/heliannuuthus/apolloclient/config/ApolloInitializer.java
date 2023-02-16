package com.heliannuuthus.apolloclient.config;

import com.ctrip.framework.apollo.core.ApolloClientSystemConsts;
import com.ctrip.framework.apollo.spring.config.PropertySourcesConstants;
import com.google.common.base.Strings;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

public class ApolloInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>, Ordered {


    private static final String ENV = "env";
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

        /*
         * 根据环境进行初始化 id 内容
         * spring.application.name
         * vcap.application.name
         * spring.config.name
         * spring.application.index
         * vcap.application.instance_index
         */

        if (Strings.isNullOrEmpty(System.getProperty(ApolloClientSystemConsts.APP_ID))) {
            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            System.setProperty(ApolloClientSystemConsts.APP_ID, environment.getProperty("spring.application.name", "UNKNOWN_APP_ID"));
        }

        if (Strings.isNullOrEmpty(System.getProperty(ENV))) {
            String activeProfile = "dev";
            String[] activeProfiles = applicationContext.getEnvironment().getActiveProfiles();
            if (activeProfiles.length > 0) {
                activeProfile = activeProfiles[0];
            }
            System.setProperty(ENV, activeProfile);
        }

        if (Strings.isNullOrEmpty(System.getProperty(ApolloClientSystemConsts.APOLLO_META))) {
            System.setProperty(ApolloClientSystemConsts.APOLLO_META, "http://192.168.3.100:28080");
        }

        System.setProperty(PropertySourcesConstants.APOLLO_BOOTSTRAP_ENABLED, "true");
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
