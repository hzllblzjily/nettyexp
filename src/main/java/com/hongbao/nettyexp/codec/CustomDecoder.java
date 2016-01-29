/**
 * 
 */
package com.hongbao.nettyexp.codec;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * @author hzllb
 *
 * 2016年1月29日
 */
public class CustomDecoder extends ByteToMessageDecoder {

	/* (non-Javadoc)
	 * @see io.netty.handler.codec.ByteToMessageDecoder#decode(io.netty.channel.ChannelHandlerContext, io.netty.buffer.ByteBuf, java.util.List)
	 */
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		// TODO Auto-generated method stub
		if(in.readableBytes() < 4){
			return;
		}
		in.markReaderIndex();
		int dataLength = in.readInt();
		if(dataLength < 0){
			ctx.close();
		}
		if(in.readableBytes() < dataLength){
			in.resetReaderIndex();
			return;
		}
		byte[] data = new byte[dataLength];
		in.readBytes(data);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
		ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
		Object object = objectInputStream.readObject();
		
		out.add(object);
		
	}

}
