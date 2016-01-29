/**
 * 
 */
package com.hongbao.nettyexp;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author hzllb
 *
 * 2016年1月25日
 */
public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, App>{

	/* (non-Javadoc)
	 * @see java.nio.channels.CompletionHandler#completed(java.lang.Object, java.lang.Object)
	 */
	public void completed(AsynchronousSocketChannel result, App attachment) {
		// TODO Auto-generated method stub
		Thread thread = Thread.currentThread();
		
		attachment.serverSocketChannel.accept(attachment, this);
		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		result.read(buffer, buffer, new ReadCompletionHandler(result));
		
	}

	/* (non-Javadoc)
	 * @see java.nio.channels.CompletionHandler#failed(java.lang.Throwable, java.lang.Object)
	 */
	public void failed(Throwable exc, App attachment) {
		// TODO Auto-generated method stub
		exc.printStackTrace();
		attachment.latch.countDown();
		
	}



}
