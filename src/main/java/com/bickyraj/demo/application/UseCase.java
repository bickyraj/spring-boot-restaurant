package com.bickyraj.demo.application;

public interface UseCase<T, K> {
    public K execute(T t);
}
