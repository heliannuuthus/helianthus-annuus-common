package com.heliannuuthus.apolloclient.spi;

import com.ctrip.framework.apollo.core.enums.Env;
import com.ctrip.framework.apollo.core.spi.MetaServerProvider;
import com.ctrip.framework.apollo.core.utils.DeferredLoggerFactory;
import org.slf4j.Logger;

public class ApolloMetaServerProvider implements MetaServerProvider {
  private static final Logger logger =
      DeferredLoggerFactory.getLogger(ApolloMetaServerProvider.class);

  private static final String DEFAULT_APOLLO_META_SERVER = "http://192.168.3.100:28070";
  private final String metaServerAddress;

  public ApolloMetaServerProvider() {
    metaServerAddress = initMetaServerAddress();
  }

  private String initMetaServerAddress() {
    logger.info("initial apollo meta from " + DEFAULT_APOLLO_META_SERVER);
    return DEFAULT_APOLLO_META_SERVER;
  }

  @Override
  public String getMetaServerAddress(Env targetEnv) {
    return metaServerAddress;
  }

  @Override
  public int getOrder() {
    return 0;
  }
}
