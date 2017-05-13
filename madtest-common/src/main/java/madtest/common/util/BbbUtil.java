package madtest.common.util;

import com.google.common.collect.Maps;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Created by qct on 2016/5/9.
 *
 * @author qct
 */
public class BbbUtil {

    static final String API_ROOT = "http://183.62.206.78:9000/bigbluebutton/api/";
    static final String SECRET = "26f13ccd40cd8ddde8dcaaae9653ec42";
    static String meetingName = null;
    static String meetingID = null;
    static String moderatorFullName = null;
    static String attendeeFullName = null;
    static String welcome = null;

    static {
        try {
            meetingName = URLEncoder.encode("屈陈涛的会议", "utf-8");
            meetingID = URLEncoder.encode("会议IDxxx1113", "utf-8");
            moderatorFullName = URLEncoder.encode("主持人1", "utf-8");
            attendeeFullName = URLEncoder.encode("参会人1", "utf-8");
            welcome = URLEncoder
                .encode("<br>欢迎进入 <b>%%CONFNAME%%</b>!<br><br>使用语音请点击左上角耳机图标。使用耳机可以避免噪音。<br>",
                    "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
        Map<String, String> pwdMap = createMeeting();
        String moderatorUrl = getJoinUrl(pwdMap.get("moderatorPW"), moderatorFullName);
        String attendeeUrl = getJoinUrl(pwdMap.get("attendeePW"), attendeeFullName);

        System.out.println("moderator join Url: " + moderatorUrl);
        System.out.println("attendee join Url: " + attendeeUrl);

        System.out.println("getMeetingsUrl: " + getMeetingsUrl());
        System.out.println(
            "getMeetingInfoUrl: " + getMeetingInfoUrl(meetingID, pwdMap.get("moderatorPW")));
        System.out.println("getEndUrl: " + getEndUrl(meetingID, pwdMap.get("moderatorPW")));

//        endAllMeetings();
    }

    private static void endAllMeetings() {
        Map<String, String> result = Maps.newHashMap();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(getMeetingsUrl());

            Element root = doc.getDocumentElement();
            NodeList childNodes = root.getElementsByTagName("meeting");
            for (int i = 0; i < childNodes.getLength(); i++) {
                NodeList meetingInfo = childNodes.item(i).getChildNodes();
                String meetingId = "";
                String moderatorPW = "";
                for (int j = 0; j < meetingInfo.getLength(); j++) {
                    if ("meetingID".equals(meetingInfo.item(j).getNodeName())) {
                        meetingId = meetingInfo.item(j).getTextContent();
                    }
                    if ("moderatorPW".equals(meetingInfo.item(j).getNodeName())) {
                        moderatorPW = meetingInfo.item(j).getTextContent();
                    }
                }
//                System.out.println(meetingId + ":" + moderatorPW);
//                result.put(meetingId, moderatorPW);
                DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(getEndUrl(URLEncoder.encode(meetingId, "utf-8"), moderatorPW));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getEndUrl(String meetingId, String password) {
        String url = API_ROOT + "end?meetingID=" + meetingId + "&password=" + password;
        String paramForChecksum = getParamForChecksum(url);
        String checksum = checksum(paramForChecksum);
        url += "&checksum=" + checksum;
        return url;
    }

    private static String getMeetingInfoUrl(String meetingId, String password) {
        String url = API_ROOT + "getMeetingInfo?meetingID=" + meetingId + "&password=" + password;
        String paramForChecksum = getParamForChecksum(url);
        String checksum = checksum(paramForChecksum);
        url += "&checksum=" + checksum;
        return url;
    }

    public static String getJoinUrl(Map<String, String> param) {
        String url = API_ROOT + "join?meetingID=" + param.get("meetingID") + "&password=" + param
            .get("password") + "&fullName=" +
            param.get("fullName");
        String paramForChecksum = getParamForChecksum(url);
        String checksum = checksum(paramForChecksum);
        url += "&checksum=" + checksum;
        return url;
    }

    private static String getJoinUrl(String pwd, String moderatorFullName) {
        String url = API_ROOT + "join?meetingID=" + meetingID + "&password=" + pwd + "&fullName=" +
            moderatorFullName;
        String paramForChecksum = getParamForChecksum(url);
        String checksum = checksum(paramForChecksum);
        url += "&checksum=" + checksum;
        System.out.println("final url: " + url);
        return url;
    }

    private static Map<String, String> createMeeting() {
        String url =
            API_ROOT + "create?name=" + meetingName + "&meetingID=" + meetingID + "&welcome="
                + welcome;
        String paramForChecksum = getParamForChecksum(url);
        String checksum = checksum(paramForChecksum);
        url += "&checksum=" + checksum;
        System.out.println("final url: " + url);
        return httpGet(url);
    }

    private static Map<String, String> httpGet(String url) {
//        HttpClient client = HttpClientBuilder.create().build();
//        HttpGet request = new HttpGet(url);
//        HttpResponse response = null;
//            response = client.execute(request);
//            System.out.println("Response Code: " + response.getStatusLine().getStatusCode());
//
//
//
//            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//            String line = "";
//            while((line = rd.readLine()) != null) {
//                System.out.println(line);
//            }
        Map<String, String> result = Maps.newHashMap();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(url);

            Element root = doc.getDocumentElement();
            NodeList childNodes = root.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                System.out.println(
                    childNodes.item(i).getNodeName() + ":" + childNodes.item(i).getTextContent());
                result.put(childNodes.item(i).getNodeName(), childNodes.item(i).getTextContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String getParamForChecksum(String url) {
        System.out.println(
            "paramForChecksum: " + url.replace(API_ROOT, "").replaceFirst("\\?", "") + SECRET);
        return url.replace(API_ROOT, "").replaceFirst("\\?", "") + SECRET;
    }

    private static void getCheckSum(String meetingName, String meetingID, String moderatorPwd,
        String
            attendeePwd, String secret) {
        System.out
            .println("create: " + checksum("createname=" + meetingName + "&meetingID=" + meetingID +
                "&moderatorPW=" + moderatorPwd + "&attendeePW=" + attendeePwd + secret));
        System.out.println(
            "end: " + checksum("endmeetingID=" + meetingID + "&password=" + moderatorPwd + secret));
        System.out.println("getMeetingInfo: " + checksum(
            "getMeetingInfomeetingID=" + meetingID + "&password=" + moderatorPwd +
                secret));
        System.out.println("getMeetings: " + checksum("getMeetings" + secret));
        System.out.println("join(moderator): " + checksum(
            "joinmeetingID=test01&password=" + moderatorPwd + "&fullName=moderator" + secret));
        System.out.println("join(attendee): " + checksum(
            "joinmeetingID=test01&password=" + attendeePwd + "&fullName=attendee" + secret));
    }

    public static String checksum(String s) {
        String checksum = "";
        try {
            checksum = org.apache.commons.codec.digest.DigestUtils.sha1Hex(s);
            System.out.println("checksum: " + checksum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checksum;
    }

    public static String getMeetingsUrl() {
        String url = API_ROOT + "getMeetings?";
        String paramForChecksum = getParamForChecksum(url);
        String checksum = checksum(paramForChecksum);
        url += "&checksum=" + checksum;
        return url;
    }
}