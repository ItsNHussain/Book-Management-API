package com.ItsNHussain.Database.mappers.impl;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ItsNHussain.Database.domain.BookEntity;
import com.ItsNHussain.Database.domain.dto.BookDto;
import com.ItsNHussain.Database.mappers.Mapper;

@Component
public class BookMapper implements Mapper<BookEntity,BookDto> {
	
	private ModelMapper modelMapper;
	
	public BookMapper (ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public BookDto mapTo(BookEntity authorEn) {
		return modelMapper.map(authorEn,BookDto.class);
	}

	@Override
	public BookEntity mapFrom(BookDto b) {
		return modelMapper.map(b, BookEntity.class);
	}

}
