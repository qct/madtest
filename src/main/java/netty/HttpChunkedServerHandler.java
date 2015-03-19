package netty;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;
import org.jboss.netty.handler.codec.http.*;
import org.jboss.netty.util.CharsetUtil;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * Created by qct on 2015/3/18.
 */
public class HttpChunkedServerHandler extends SimpleChannelUpstreamHandler {
    private static final AtomicInteger count = new AtomicInteger(0);

    private void increment() {
        System.out.format("online user %d\n", count.incrementAndGet());
    }

    private void decrement() {
        if (count.get() <= 0) {
            System.out.format("~online user %d\n", 0);
        } else {
            System.out.format("~online user %d\n", count.decrementAndGet());
        }
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        HttpRequest request = (HttpRequest) e.getMessage();
        if (request.getMethod() != HttpMethod.GET) {
            sendError(ctx, HttpResponseStatus.METHOD_NOT_ALLOWED);
            return;
        }
        sendPrepare(ctx);
        increment();
    }


    private void sendPrepare(ChannelHandlerContext ctx) {
        HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        response.setChunked(true);
        response.setHeader(HttpHeaders.Names.CONTENT_TYPE,
                "text/html; charset=UTF-8");
        response.addHeader(HttpHeaders.Names.CONNECTION,
                HttpHeaders.Values.KEEP_ALIVE);
        response.setHeader(HttpHeaders.Names.TRANSFER_ENCODING,
                HttpHeaders.Values.CHUNKED);

        Channel chan = ctx.getChannel();
        chan.write(response);

        // 缓冲必须凑够256字节，浏览器端才能够正常接收 ...
        StringBuilder builder = new StringBuilder();
        builder.append("<html><body><script>var _ = function (msg) { parent.s._(msg, document); };</script>");
        int leftChars = 256 - builder.length();
        for (int i = 0; i < leftChars; i++) {
            builder.append("a");
        }

        writeStringChunk(chan, builder.toString());
    }

    private void writeStringChunk(Channel channel, String data) {
        ChannelBuffer chunkContent = ChannelBuffers.dynamicBuffer(channel
                .getConfig().getBufferFactory());
        chunkContent.writeBytes(data.getBytes());
        HttpChunk chunk = new DefaultHttpChunk(chunkContent);

        channel.write(chunk);
    }

    private void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
        HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, status);
        response.setHeader(HttpHeaders.Names.CONTENT_TYPE, "text/plain; charset=UTF-8");
        response.setContent(ChannelBuffers.copiedBuffer(
                "Failure: " + status.toString() + "\r\n", CharsetUtil.UTF_8));

        // Close the connection as soon as the error message is sent.
        ctx.getChannel().write(response)
                .addListener(ChannelFutureListener.CLOSE);
    }
}
