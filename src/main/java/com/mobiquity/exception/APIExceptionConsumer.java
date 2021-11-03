package com.mobiquity.exception;

/**
 * This is a customized Consumer that throws a APIException. Used to rethrow an APIException through
 * lambda functions.
 */

@FunctionalInterface
public interface APIExceptionConsumer<T, E extends APIException> {
    void accept(T t) throws E;
}
