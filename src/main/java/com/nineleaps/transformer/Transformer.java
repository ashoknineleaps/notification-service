package com.nineleaps.transformer;

public interface Transformer<S1, S2> {

	public S2 transformer(S1 s);
}
