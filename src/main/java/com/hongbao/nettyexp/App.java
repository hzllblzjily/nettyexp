package com.hongbao.nettyexp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.Selector;
import java.util.concurrent.CountDownLatch;

/**
 * Hello world!
 *
 */
public class App 
{
	AsynchronousServerSocketChannel serverSocketChannel = null;
	CountDownLatch latch = null;
    public static void main( String[] args ) throws IOException, InterruptedException
    {
    	App app = new App();
        AsynchronousServerSocketChannel asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
        asynchronousServerSocketChannel.bind(new InetSocketAddress(9920));
        
        app.serverSocketChannel = asynchronousServerSocketChannel;
        app.latch = new CountDownLatch(1);
        Thread thread = Thread.currentThread();
        asynchronousServerSocketChannel.accept(app, new AcceptCompletionHandler());
        app.latch.await();
    }
}
