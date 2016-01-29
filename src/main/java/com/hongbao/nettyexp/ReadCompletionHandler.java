/**
 * 
 */
package com.hongbao.nettyexp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author hzllb
 *
 * 2016年1月25日
 */
public class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer>{

	private AsynchronousSocketChannel channel = null;
	/**
	 * @param result
	 */
	public ReadCompletionHandler(AsynchronousSocketChannel result) {
		// TODO Auto-generated constructor stub
		this.channel = result;
	}

	/* (non-Javadoc)
	 * @see java.nio.channels.CompletionHandler#completed(java.lang.Object, java.lang.Object)
	 */
	public void completed(Integer result, ByteBuffer attachment) {
		// TODO Auto-generated method stub
		attachment.flip();
		byte[] body = new byte[attachment.remaining()];
		attachment.get(body);
		try {
			String req = new String(body,"UTF-8");
			System.out.println("req = " + req);
			this.doWrite("hzl");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void doWrite(String current){
		byte[] bytes = current.getBytes();
		ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
		writeBuffer.put(bytes);
		writeBuffer.flip();
		channel.write(writeBuffer,writeBuffer,new CompletionHandler<Integer, ByteBuffer>() {

			public void completed(Integer result, ByteBuffer attachment) {
				// TODO Auto-generated method stub
				if(attachment.hasRemaining()){
					channel.write(attachment,attachment,this);
				}
			}

			public void failed(Throwable exc, ByteBuffer attachment) {
				// TODO Auto-generated method stub
				try {
					channel.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	/* (non-Javadoc)
	 * @see java.nio.channels.CompletionHandler#failed(java.lang.Throwable, java.lang.Object)
	 */
	public void failed(Throwable exc, ByteBuffer attachment) {
		// TODO Auto-generated method stub
		
	}

}
