import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

// 这里用 SimpleChannelInboundHandler，而不是 ChannelInboundHandlerAdapter 的原因是：
// 1) 在客户端，当 channelRead0 方法返回时，SimpleChannelInboundHandler 负责释放保存该消息的 ByteBuf。
// 2) 在 EchoServerHandler 中，仍然需要将传入的消息返回给发送方，而 write() 操作是异步的，
// 直到 channelRead() 方法返回后可能仍然没有完成。EchoServerHandler 要保证在这个时间点上不会释放消息。
// 消息会在 channelReadComplete() 中被释放。
@Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 到服务器的连接建立之后被调用
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        // 当从服务器接收到一条消息时被调用
        System.out.println("Client received: " + msg.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
