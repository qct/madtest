package madtest.common.mongodb;

import com.google.common.collect.Lists;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import madtest.common.util.ClassLoaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by qct on 2015/6/11.
 */
public class MongoManager {

    private final static Logger logger = LoggerFactory.getLogger(MongoManager.class);

    private static MongoClient mongoClient = null;

    private static ArrayList<String> hostAndPort;

    private MongoManager() {
    }

    public static MongoClient getClient() {
        if (mongoClient == null) {
            init();
        }
        return mongoClient;
    }

    public static void init() {
        Properties properties = new Properties();
        InputStream inputStream = ClassLoaderUtil
            .getResourceAsStream("mongo.properties", MongoManager.class);
        try {
            properties.load(inputStream);
            hostAndPort = Lists.newArrayList();
            for (String str : properties.getProperty("mongoClients").split("#")) {
                hostAndPort.add(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("mongodb://");
        for (int i = 0; i < hostAndPort.size(); i++) {
            sb.append(hostAndPort.get(i));
            if (i != hostAndPort.size() - 1) {
                sb.append(",");
            }
        }
        logger.debug(sb.toString());

        MongoClientURI connectStr = new MongoClientURI(sb.toString());
        mongoClient = new MongoClient(connectStr);
    }

}