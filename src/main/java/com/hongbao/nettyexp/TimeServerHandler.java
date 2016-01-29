/**
 * 
 */
package com.hongbao.nettyexp;

import java.nio.ByteBuffer;

import org.w3c.dom.css.Counter;

import com.hongbao.nettyexp.protobuf.SubscribeReqProto;
import com.hongbao.nettyexp.protobuf.SubscribeRespProto;
import com.hongbao.nettyexp.protobuf.SubscribeReqProto.SubscribeReq;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author hzllb
 *
 * 2016年1月26日
 */
public class TimeServerHandler extends ChannelHandlerAdapter{

	private int counter = 0;
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)throws Exception{
		//String body = (String)msg;
		Request request = (Request)msg;
		System.out.println("request name = " + request.getRequestName());
		Response response = new Response();
		response.setId(1);
		response.setResponseData("123");
		response.setResponseName("hahaha");
		ctx.writeAndFlush(response);
		
		
		//System.out.println("The time server receive order :" + body + " counter = " + ++counter);
//		ByteBuf buf = (ByteBuf)msg;
//		byte[] req = new byte[buf.readableBytes()];
//		buf.readBytes(req);
//		System.out.println("The time server receive order :" + new String(req,"UTF-8"));
		//ByteBuf respBuf = Unpooled.copiedBuffer(new String("current" + System.getProperty("line.separator")).getBytes());
		//ctx.writeAndFlush(respBuf);
		
//		SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq)msg;
//		System.out.println("Server accept client request : " + req.toString());
//		
//		SubscribeRespProto.SubscribeResp.Builder builder = SubscribeRespProto.SubscribeResp.newBuilder();
//		builder.setSubReqID(req.getSubReqID());
//		builder.setRespCode(0);
//		builder.setDesc("Netty book order succeed, 3 days later");
//		SubscribeRespProto.SubscribeResp resp = builder.build();
//		
//		ctx.writeAndFlush(resp);
		
	}
	
//	@Override
//	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception{
//		ctx.flush();
//	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
		ctx.close();
	}
	
}
