/**
 * 
 */
package com.hongbao.nettyexp.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.hongbao.nettyexp.Request;

/**
 * @author hzllb
 *
 * 2016年1月29日
 */
public class CustomEncoder extends MessageToByteEncoder<Serializable>{

	/* (non-Javadoc)
	 * @see io.netty.handler.codec.MessageToByteEncoder#encode(io.netty.channel.ChannelHandlerContext, java.lang.Object, io.netty.buffer.ByteBuf)
	 */
	@Override
	protected void encode(ChannelHandlerContext ctx, Serializable msg, ByteBuf out)
			throws Exception {
		// TODO Auto-generated method stub
		
		//序列化
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
		objectOutputStream.writeObject(msg);
		byte[] byteArray = byteArrayOutputStream.toByteArray();
		

		out.writeInt(byteArray.length);
		out.writeBytes(byteArray);
	}

}
