package me.jimwayneyeh.ethereum.example;

import org.ethereum.config.SystemProperties;
import org.ethereum.facade.Ethereum;
import org.ethereum.facade.EthereumFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
  
  public static void main(String[] args) {
    System.out.println("Start application.");
    
    SystemProperties.getDefault().setSyncEnabled(false);
    SystemProperties.getDefault().setDiscoveryEnabled(true);
    
    Ethereum eth = EthereumFactory.createEthereum();
    eth.startPeerDiscovery();
    eth.switchToShortSync();
    
    System.out.println("End application.");
  }
}