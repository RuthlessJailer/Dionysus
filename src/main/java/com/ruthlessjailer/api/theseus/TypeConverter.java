package com.ruthlessjailer.api.theseus;

/**
 * Interface for converting types.
 *
 * @author RuthlessJailer
 * @see Common#convert(Iterable, TypeConverter)
 * @see Common#convert(Object[], Object[], TypeConverter)
 */
@FunctionalInterface
public interface TypeConverter<O, N> {

	N convert(O value);

}
