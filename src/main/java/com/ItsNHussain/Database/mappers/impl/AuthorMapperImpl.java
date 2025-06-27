package com.ItsNHussain.Database.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ItsNHussain.Database.domain.AuthorEntity;
import com.ItsNHussain.Database.domain.dto.AuthorDto;
import com.ItsNHussain.Database.mappers.Mapper;

@Component
public class AuthorMapperImpl implements Mapper<AuthorEntity,AuthorDto> {
	
	private ModelMapper modelMapper;
	
	public AuthorMapperImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public AuthorDto mapTo(AuthorEntity a) {
		return modelMapper.map(a, AuthorDto.class);
	}

	@Override
	public AuthorEntity mapFrom(AuthorDto b) {
		
		return modelMapper.map(b,AuthorEntity.class);
	}

}
