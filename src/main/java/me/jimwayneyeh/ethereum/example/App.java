package me.jimwayneyeh.ethereum.example;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.ethereum.config.SystemProperties;
import org.ethereum.facade.Ethereum;
import org.ethereum.facade.EthereumFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.typesafe.config.ConfigFactory;

public class App {
  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
  
  public static void main(String[] args) throws Throwable {
    SystemProperties properties = SystemProperties.getDefault();
    
    try (InputStream is = App.class.getResourceAsStream("/ropsten.conf")) {
      String config = IOUtils.toString(is, StandardCharsets.UTF_8);
      properties.overrideParams(ConfigFactory.parseString(config));
    }
    properties.setSyncEnabled(true);
    properties.setDiscoveryEnabled(true);
    
    Ethereum eth = EthereumFactory.createEthereum();
    eth.startPeerDiscovery();
    eth.switchToShortSync();
    
    System.out.println("End application.");
  }
}