package com.xm.designpattern.behavior.chain;

/**
 * @author XM
 * @date 2023/1/12
 */
public class ConsoleLogger extends AbstractLogger {
 
   public ConsoleLogger(int level){
      this.level = level;
   }
 
   @Override
   protected void write(String message) {    
      System.out.println("Standard Console::Logger: " + message);
   }
}