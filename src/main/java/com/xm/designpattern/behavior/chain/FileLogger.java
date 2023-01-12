package com.xm.designpattern.behavior.chain;

/**
 * @author XM
 * @date 2023/1/12
 */
public class FileLogger extends AbstractLogger {
 
   public FileLogger(int level){
      this.level = level;
   }
 
   @Override
   protected void write(String message) {    
      System.out.println("File::Logger: " + message);
   }
}