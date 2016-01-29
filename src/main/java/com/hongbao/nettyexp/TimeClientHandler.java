/**
 * 
 */
package com.hongbao.nettyexp;

import com.hongbao.nettyexp.protobuf.SubscribeReqProto;
import com.hongbao.nettyexp.protobuf.SubscribeRespProto;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author hzllb
 *
 * 2016年1月26日
 */
public class TimeClientHandler extends ChannelHandlerAdapter{

	//private final ByteBuf firstMessage;
	private final byte[] req;
	public TimeClientHandler(){
		//String tmp = "Query time order";
		String tmp = "Query time order" + System.getProperty("line.separator");
		req = tmp.getBytes();
		//firstMessage = Unpooled.buffer(req.length);
		//firstMessage.writeBytes(req);
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx){
		ByteBuf messageBuf = null;
		for (int i = 0; i < 10; i++) {
			//essageBuf = Unpooled.buffer(req.length);
			//messageBuf.writeBytes(req);
			
			Request request = new Request();
			request.setId(i);
			request.setRequestName("hzl" + i);
			ctx.writeAndFlush(request);
			
//			SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
//			builder.setSubReqID(1);
//			builder.setUserName("hzl");
//			builder.setProductName("ppp");
//			builder.setAddress("政治");
//			SubscribeReqProto.SubscribeReq req = builder.build();
//			ctx.write(req);
		}
		//ctx.flush();
	
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx,Object msg)throws Exception{
//		ByteBuf buf = (ByteBuf)msg;
//		byte[] req = new byte[buf.readableBytes()];
//		buf.readBytes(req);
		Response response = (Response)msg;
		System.out.println("now is :" + response.getResponseName());
		
		//SubscribeRespProto.SubscribeResp resp = (SubscribeRespProto.SubscribeResp)msg;
		//System.out.println("now is :" + resp);
		
	}
}
