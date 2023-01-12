package com.xm.designpattern.behavior.chain;

/**
 * 责任链模式: 每个接收者都包含对另一个接收者的引用。如果一个对象不能处理该请求，那么它会把相同的请求传给下一个接收者，依此类推。
 * 应用实例： 1、JS 中的事件冒泡。 2、JAVA WEB 中 Apache Tomcat 对 Encoding 的处理，Struts2 的拦截器，jsp servlet 的 Filter。
 * 优点：
 * 1、降低耦合度。它将请求的发送者和接收者解耦。
 * 2、简化了对象。使得对象不需要知道链的结构。
 * 3、增强给对象指派职责的灵活性。通过改变链内的成员或者调动它们的次序，允许动态地新增或者删除责任。
 * 4、增加新的请求处理类很方便。
 * 缺点：
 * 1、不能保证请求一定被接收。
 * 2、系统性能将受到一定影响，而且在进行代码调试时不太方便，可能会造成循环调用。
 * 3、可能不容易观察运行时的特征，有碍于除错。
 *
 * @author XM
 * @date 2023/1/12
 */
public class ChainPatternDemo {

    private static AbstractLogger getChainOfLoggers() {

        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return errorLogger;
    }

    public static void main(String[] args) {
        AbstractLogger loggerChain = getChainOfLoggers();

        loggerChain.logMessage(AbstractLogger.INFO, "This is an information.");
        System.out.println("---------------");

        loggerChain.logMessage(AbstractLogger.DEBUG, "This is a debug level information.");
        System.out.println("---------------");

        loggerChain.logMessage(AbstractLogger.ERROR, "This is an error information.");
    }
}