/*
 * 版权所有(C)Niconico Craft 保留所有权利
 * 您不得在未经作者许可的情况下，擅自发布本软件的任何部分或全部内容
 * 否则将会追究二次发布者的法律责任
 */
package cn.kasuganosora.chat.html;

/**
 *
 * @author jiang
 */
public class page {

    public String Header(String title, String content, String charset) {
        return "<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=" + charset + "\"><title>" + title + "</title>\n" + content + "</head>";
    }

    public String Html(String content) {
        return "<html>" + content + "</html>";
    }

    public String Css(String css) {
        return "<style type=\"text/css\">" + css + "</style>";
    }

    public String Js(String Js) {
        return "<script type=\"text/javascript\">" + Js + "</script>";
    }

    public String DefaultCSS() {
        return "body {background: #ffc107;color: #FFF;}.box {margin-top: 32px;margin-left: 64px;}.box face {font-size: 128px;}.box code {margin-top: -8px;display: inline-block;font-size: 15px;padding: 8px;background: rgba(0,0,0,0.2);border-radius: 8px;line-height: 20px;box-shadow: inset 0px 0px 16px rgba(0,0,0,0.3);}.box footer {margin-top: 64px;font-size: 16px;border-top: 3px solid #FFF;width: 512px;padding-top: 8px;}.box footer version {font-size: 8px;vertical-align: top;margin-left: 5px;margin-right: 8px;}";
    }
    
    public String DefaultBody(String faceText, String bigTitle, String smallTitle1, String code1, String smallTitle2, String code2) {
        return "<div class='box'><face>" + faceText + "</face><h1>" + bigTitle + "</h1><h3>" + smallTitle1 + "</h3><code>" + code1 + "</code><h3>" + smallTitle2 + "</h3><code>" + code2 + "</code><footer>SoraOS<version>10.3</version>Powerful Web Server</footer></div>";
    }
    
    public static String HomePage(String onLine, String version) {
        page page = new page();
        return page.Html(page.Header("KasuganoSora WebServer", page.Css(page.DefaultCSS()), "UTF-8") + page.DefaultBody(":)", "KasuganoSora WebServer", "Server Status", onLine, "Server Version", version));
    }
}
