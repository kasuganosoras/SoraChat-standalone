/*
 * 版权所有(C)Niconico Craft 保留所有权利
 * 您不得在未经作者许可的情况下，擅自发布本软件的任何部分或全部内容
 * 否则将会追究二次发布者的法律责任
 */
package cn.kasuganosora.chat;

import cn.kasuganosora.chat.html.page;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.acraft.NicoHttp.Object.Http.Handler;
import org.acraft.NicoHttp.Event.Exchange;
import org.acraft.NicoHttp.Object.Http.Server;

/**
 *
 * @author jiang
 */
public class Main {

    private static int Port;
    private static String Password, Prefix;
    private static String Message = "";

    public static void main(String[] args) {

        Logger.log("SoraChat 10.0.1923.87-standalone");
        Logger.log("(C)2012-2017 KasuganoSora.cn");
        try {
            Port = Integer.parseInt(args[0]);
            Password = args[1];
        } catch (Exception ex) {
            Logger.log("Command: command [port] [password]");
            System.exit(1);
        }
        Logger.log("WebServer starting...");
        Logger.log("WebServer is running on port: " + Port);
        Logger.log("Password change to: " + Password);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Message = filereader.readTxtFile("logs/chat-" + sdf.format(d) + ".log");
        Server.create(Port).setHandler(new Handler() {
            @Override
            public void onRequest(Exchange event) throws IOException {
                String request = event.getURL().toString().substring(event.getURL().toString().lastIndexOf(":" + Port + "/") + 6);
                event.setHeader("X-Powered-By", "SoraOS/10.0");
                event.setHeader("Server", "SoraOS10");
                event.setHeader("Access-Control-Allow-Origin", "*");
                switch (request) {
                    case "":
                        event.setHeader("Content-Type", "text/html;charset=utf-8");
                        event.sendHeader(200, 0);
                        event.write(page.HomePage("200 OK WebServer", "10.0.1923.87"));
                        break;
                    case "msg":
                        event.setHeader("Content-Type", "text/plain;charset=gb2312");
                        event.sendHeader(200, 0);
                        event.writeln(Message);
                        //getLogger().info(Message);
                        break;
                    default:
                        if (!"".equals(event.getArgs().get("msg")) & !"".equals(event.getArgs().get("user")) & Password.equals(event.getArgs().get("pass"))) {
                            event.sendHeader(200, 0);
                            String message = java.net.URLDecoder.decode(event.getArgs().get("msg"), "UTF-8");
                            String user = java.net.URLDecoder.decode(event.getArgs().get("user"), "UTF-8");
                            Logger.log(user + ": " + message);
                            Message += user + ":" + message + "\n";
                            Date d = new Date();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            if (!new File("logs/").exists()) {
                                new File("logs/").mkdir();
                            }
                            if (!new File("logs/chat-" + sdf.format(d) + ".log").exists()) {
                                new File("logs/chat-" + sdf.format(d) + ".log").createNewFile();
                            }
                            //filewrite.append(, );
                            String file = "logs/chat-" + sdf.format(d) + ".log";
                            FileOutputStream writerStream = new FileOutputStream(file);
                            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8"));
                            writer.write(Message);
                            writer.close();
                            event.setHeader("Content-Type", "text/html;charset=utf-8");
                            event.write("Successful send message");
                        } else {
                            event.setHeader("Content-Type", "text/html;charset=utf-8");
                            event.sendHeader(403, 0);
                            event.write("403 Forbidden");
                        }
                }
            }
        });
    }
}

class Logger {

    public static void log(String str) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println("[" + sdf.format(d) + "] " + str);
    }

    public static void log(int str) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println("[" + sdf.format(d) + "] " + str);
    }

    public static void log(Exception str) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println("[" + sdf.format(d) + "] " + str);
    }

    public static void log(Object str) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println("[" + sdf.format(d) + "] " + str);
    }
}
