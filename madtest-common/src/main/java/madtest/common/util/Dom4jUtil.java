package madtest.common.util;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

/**
 * Created by qct on 2015/7/13.
 */
public class Dom4jUtil {

    public static Document readXML(String fileName) {
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            URL xmlPath = Dom4jUtil.class.getClassLoader().getResource(fileName);
            document = saxReader.read(xmlPath);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

    public static Document readXML(InputStream in) {
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(in);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

    public static Document readXML(Reader reader) {
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(reader);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

    public static Document readXML(File file) {
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }
}
