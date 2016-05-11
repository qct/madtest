package madtest.common.util;

import com.google.common.collect.Maps;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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

    static {
        try {
            meetingName = URLEncoder.encode("屈陈涛的会议", "utf-8");
            meetingID = URLEncoder.encode("会议ID123", "utf-8");
            moderatorFullName = URLEncoder.encode("主持人1", "utf-8");
            attendeeFullName = URLEncoder.encode("参会人1", "utf-8");
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

//        getCheckSum(meetingName, meetingID, moderatorPwd, attendeePwd, SECRET);
//        String moderatorJoinUrl = "http://218.17.169" +
//                ".173:9000/bigbluebutton/api/join?meetingID=test01&password=mp&fullName" +
//                "=moderator&checksum=f21ecefbde1ff22fd0a81a0148d1c34074b31b7e";
//        String attendeeJoinUrl= "http://218.17.169" +
//                ".173:9000/bigbluebutton/api/join?meetingID=test01&password=ap&fullName" +
//                "=attendee&checksum=1528db90a72cd03fe3990028067ed804313345f8";
//        System.out.println("moderatorJoinUrl: " + moderatorJoinUrl);
//        System.out.println("attendeeJoinUrl: " + attendeeJoinUrl);
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
        String url = API_ROOT + "create?name=" + meetingName + "&meetingID=" + meetingID;
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
                System.out.println(childNodes.item(i).getNodeName() + ":" + childNodes.item(i).getTextContent());
                result.put(childNodes.item(i).getNodeName(), childNodes.item(i).getTextContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String getParamForChecksum(String url) {
        System.out.println("paramForChecksum: " + url.replace(API_ROOT, "").replaceFirst("\\?", "") + SECRET);
        return url.replace(API_ROOT, "").replaceFirst("\\?", "") + SECRET;
    }

    private static void getCheckSum(String meetingName, String meetingID, String moderatorPwd, String
            attendeePwd, String secret) {
        System.out.println("create: " + checksum("createname=" + meetingName + "&meetingID=" + meetingID +
                "&moderatorPW=" + moderatorPwd + "&attendeePW=" + attendeePwd + secret));
        System.out.println("end: " + checksum("endmeetingID=" + meetingID + "&password=" + moderatorPwd + secret));
        System.out.println("getMeetingInfo: " + checksum("getMeetingInfomeetingID=" + meetingID + "&password=" + moderatorPwd +
                secret));
        System.out.println("getMeetings: " + checksum("getMeetings" + secret));
        System.out.println("join(moderator): " + checksum("joinmeetingID=test01&password=" + moderatorPwd + "&fullName=moderator" + secret));
        System.out.println("join(attendee): " + checksum("joinmeetingID=test01&password=" + attendeePwd + "&fullName=attendee" + secret));
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
}
