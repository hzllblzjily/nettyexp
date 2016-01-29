/**
 * 
 */
package com.hongbao.nettyexp;

import com.hongbao.nettyexp.codec.CustomDecoder;
import com.hongbao.nettyexp.codec.CustomEncoder;
import com.hongbao.nettyexp.codec.MarshallingCodeCFactory;
import com.hongbao.nettyexp.protobuf.SubscribeReqProto;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author hzllb
 *
 * 2016年1月26日
 */
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInitializer#initChannel(io.netty.channel.Channel)
	 */
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		// TODO Auto-generated method stub
		
		//以下是换行符分割的字符串序列
		//ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
		//ch.pipeline().addLast(new StringDecoder());
		
		//以下是java序列化的编解码器
		//ch.pipeline().addLast(new ObjectEncoder());
		//ch.pipeline().addLast(new ObjectDecoder(1024*1024, ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));

		//以下是protobuf编解码器
//		ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
//		ch.pipeline().addLast(new ProtobufDecoder(SubscribeReqProto.SubscribeReq.getDefaultInstance()));
//		ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
//		ch.pipeline().addLast(new ProtobufEncoder());
		
		//以下是自定义编解码器
		//ch.pipeline().addLast(new CustomEncoder());
		//ch.pipeline().addLast(new CustomDecoder());

		//以下是marshalling编解码器
		ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
		ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
		
		ch.pipeline().addLast(new TimeServerHandler());
		
	}

}
