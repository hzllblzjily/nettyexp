/**
 * 
 */
package com.hongbao.nettyexp;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.google.protobuf.InvalidProtocolBufferException;
import com.hongbao.nettyexp.protobuf.SubscribeReqProto;
import com.hongbao.nettyexp.protobuf.SubscribeReqProto.SubscribeReq;

/**
 * @author hzllb
 *
 * 2016年1月29日
 */
public class TestProtoBuf {

	private static byte[] encode(SubscribeReqProto.SubscribeReq req){
		return req.toByteArray();
	}
	
	private static SubscribeReqProto.SubscribeReq decode(byte[] body)throws InvalidProtocolBufferException{
		return SubscribeReqProto.SubscribeReq.parseFrom(body);
	}
	
	private static SubscribeReqProto.SubscribeReq createSubscribeReq(){
		SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
		builder.setSubReqID(1);
		builder.setUserName("hzl");
		//builder.setProductName(null);
		builder.setAddress("政治");
		return builder.build();
		
	}
	
	public static void main(String[] args) throws InvalidProtocolBufferException{
		Schema<Request> requestSchema = RuntimeSchema.getSchema(Request.class);
		LinkedBuffer buffer = LinkedBuffer.allocate(4096);
		byte[] protostuff = null;
		Request request = new Request();
		request.setId(1);
		request.setRequestData("123");
		request.setRequestName("胡子龙");
		protostuff =  ProtostuffIOUtil.toByteArray(request, requestSchema, buffer);
		
		request = new Request();

	    ProtostuffIOUtil.mergeFrom(protostuff,request,requestSchema);


		    
		
		SubscribeReqProto.SubscribeReq req = createSubscribeReq();
		System.out.println("Before encode : " + req.toString());
		SubscribeReqProto.SubscribeReq req2 = decode(encode(req));
		System.out.println("After decode : " + req.toString());
		System.out.println("Assert equal : " + req2.equals(req));
	}
}
