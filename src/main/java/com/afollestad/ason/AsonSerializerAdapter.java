package com.afollestad.ason;

public abstract class AsonSerializerAdapter<T> {
  public abstract T fromJson(String json);

  public abstract String toJson(T value);
}
