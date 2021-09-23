package com.example.demo.core.configurationBeans;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.demo.domain.userDomain.Rol;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;

@Configuration
public class CustomConverters {
    @Bean
    public R2dbcCustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new UUIDToByteArrayConverter());
        converters.add(new ByteArrayToUUIDConverter());
        converters.add(new RolToIntegerConverter());
        converters.add(new IntegerToRolConverter());
        return new R2dbcCustomConversions(converters);
    }

    @WritingConverter
    private class UUIDToByteArrayConverter implements Converter<UUID, byte[]> {
        @Override
        public byte[] convert(UUID source) {
            ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
            bb.putLong(source.getMostSignificantBits());
            bb.putLong(source.getLeastSignificantBits());
            return bb.array();
        }
    }
    
    @ReadingConverter
    public class ByteArrayToUUIDConverter implements Converter<byte[], UUID> {
        @Override
        public UUID convert(byte[] source) {
            return UUID.nameUUIDFromBytes(source);
        }
    }

    @WritingConverter
    private class RolToIntegerConverter implements Converter<Rol, Integer> {
		@Override
		public Integer convert(Rol rol) {
			return rol.ordinal();
		}
	}

    @ReadingConverter
	public class IntegerToRolConverter implements Converter<Integer,Rol> {
		@Override
		public Rol convert(Integer value) {
			return Rol.values()[value];
		}
	}
}
