//package ru.nguira;

interface Handler{
    void setNext(Handler h);
    void handle();
}
class BaseHandler implements Handler{
    Handler next = null;
    @Override
    public void setNext(Handler h) {
        this.next = h;
    }

    @Override
    public void handle() {
        if(next != null)next.handle();
    }
}
class FileHandler extends BaseHandler{
    @Override
    public void handle() {
        System.out.println("Write to File");
        super.handle();
    }
}
class ConsoleHandler extends BaseHandler {
    @Override
    public void handle() {
        System.out.println("Write to Console");
        super.handle();
    }
}
class ServerHandler extends BaseHandler{
    @Override
    public void handle() {
        System.out.println("Send to Server");
        super.handle();
    }
}
class LoggerFramework{
    Handler fileHandler = new FileHandler(), consoleHandler = new ConsoleHandler(), serverHandler = new ServerHandler();
    public LoggerFramework(){
        fileHandler.setNext(consoleHandler);
        consoleHandler.setNext(serverHandler);
    }
    public void setWriteToOnlyFiles(boolean b){
        if(b)fileHandler.setNext(null);
        else fileHandler.setNext(consoleHandler);
    }
    public void writeLogs(){
        fileHandler.handle();
    }
}
public class Main {

    public static void main(String[] args) {
	    LoggerFramework loggerFramework = new LoggerFramework();
	    loggerFramework.writeLogs();
    }
}
