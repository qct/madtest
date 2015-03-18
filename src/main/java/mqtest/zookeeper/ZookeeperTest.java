package mqtest.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by qct on 2015/1/7.
 */
public class ZookeeperTest {
    /**
     * server列表, 以逗号分割
     */
    protected String hosts = "zkmaster111:3888,zkslave112:3888,zkslave122:3888";
    /**
     * 连接的超时时间, 毫秒
     */
    private static final int SESSION_TIMEOUT = 5000;
    private static CountDownLatch connectedSignal = new CountDownLatch(1);

    public ZooKeeper getZk() {
        return zk;
    }

    protected ZooKeeper zk;

    /**
     * 连接zookeeper server
     */
    public void connect() throws Exception {
        zk = new ZooKeeper(hosts, SESSION_TIMEOUT, new ConnWatcher());
        // 等待连接完成
        connectedSignal.await();
    }

    public static class ConnWatcher implements Watcher {
        public void process(WatchedEvent event) {
            // 连接建立, 回调process接口时, 其event.getState()为KeeperState.SyncConnected
            if (event.getState() == Event.KeeperState.SyncConnected) {
                // 放开闸门, wait在connect方法上的线程将被唤醒
                connectedSignal.countDown();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ZookeeperTest zt = new ZookeeperTest();
        zt.connect();
        ConnWatcher cw = new ConnWatcher();
        List<String> children = zt.getZk().getChildren("/",cw);
        System.out.println(children.size());
    }
}
