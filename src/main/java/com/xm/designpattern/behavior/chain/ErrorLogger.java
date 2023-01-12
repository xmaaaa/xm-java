package com.xm.designpattern.behavior.chain;

/**
 * @author XM
 * @date 2023/1/12
 */
public class ErrorLogger extends AbstractLogger {
 
   public ErrorLogger(int level){
      this.level = level;
   }
 
   @Override
   protected void write(String message) {    
      System.out.println("Error Console::Logger: " + message);
   }
}