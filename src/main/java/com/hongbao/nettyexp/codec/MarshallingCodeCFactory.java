/**
 * 
 */
package com.hongbao.nettyexp.codec;

import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.MarshallingEncoder;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;

/**
 * @author hzllb
 *
 * 2016年1月29日
 */
public final class MarshallingCodeCFactory {

	public static MarshallingDecoder buildMarshallingDecoder(){
		final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
		final MarshallingConfiguration configuration = new MarshallingConfiguration();
		configuration.setVersion(5);
		UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory, configuration);
		MarshallingDecoder decoder = new MarshallingDecoder(provider,1024);
		return decoder;
	}
	public static MarshallingEncoder buildMarshallingEncoder(){
		final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
		final MarshallingConfiguration configuration = new MarshallingConfiguration();
		configuration.setVersion(5);
		MarshallerProvider provider = new DefaultMarshallerProvider(marshallerFactory, configuration);
		MarshallingEncoder encoder = new MarshallingEncoder(provider);
		
		return encoder;
	}
}
