/**
 * 
 */
package com.hongbao.nettyexp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author hzllb
 *
 * 2016年1月26日
 */
public class TimeServer {

	public static void main(String[] args) throws Exception{
		int port = 9654;
		new TimeServer().bind(port);
	}
	
	public void bind(int port)throws Exception{
		//配置服务端的nio线程组
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try{
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG, 1024).childHandler(new ChildChannelHandler());
			
			//绑定端口，同步等待成功
			ChannelFuture f = bootstrap.bind(port).sync();
			
			//等待服务端监听端口关闭
			f.channel().closeFuture().sync();
		}finally{
			//优雅退出，释放线程池资源
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}

		
	}
}
