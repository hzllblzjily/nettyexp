/**
 * 
 */
package com.hongbao.nettyexp;

import com.hongbao.nettyexp.codec.CustomDecoder;
import com.hongbao.nettyexp.codec.CustomEncoder;
import com.hongbao.nettyexp.codec.MarshallingCodeCFactory;
import com.hongbao.nettyexp.protobuf.SubscribeReqProto;
import com.hongbao.nettyexp.protobuf.SubscribeRespProto;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author hzllb
 *
 * 2016年1月26日
 */
public class TimeClient {

	public static void main(String[] args) throws Exception{
		int port = 9654;
		new TimeClient().connect(port, "127.0.0.1");
	}
	public void connect(int port, String host) throws Exception{
		//配置客户端nio线程组
		EventLoopGroup group = new NioEventLoopGroup();
		try{
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
			.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					// TODO Auto-generated method stub
					//字符串分割
					//ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
					//ch.pipeline().addLast(new StringDecoder());
					
					//java序列化
					//ch.pipeline().addLast(new ObjectEncoder());
					//ch.pipeline().addLast(new ObjectDecoder(1024*1024, ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));

					
					//以下是protobuf编解码器
//					ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
//					ch.pipeline().addLast(new ProtobufDecoder(SubscribeRespProto.SubscribeResp.getDefaultInstance()));
//					ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
//					ch.pipeline().addLast(new ProtobufEncoder());
					
					//以下是自定义编解码器
//					ch.pipeline().addLast(new CustomEncoder());
//					ch.pipeline().addLast(new CustomDecoder());
					
					
					//以下是marshalling编解码器
					ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
					ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
					
					ch.pipeline().addLast(new TimeClientHandler());
					
				}
				
			});
			//发起异步连接操作
			ChannelFuture future = bootstrap.connect(host,port).sync();
			
			//等待客户端链路关闭
			future.channel().closeFuture().sync();
		}finally{
			//优雅退出，释放nio线程组
			group.shutdownGracefully();
		}
	}
}
