package com.ItsNHussain.Database.mappers;

public interface Mapper<A,B> {
	
	B mapTo(A authorEn);
	
	A mapFrom(B b);

}
