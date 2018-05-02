package com.afollestad.ason;

import static junit.framework.TestCase.assertEquals;

import java.util.Objects;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;

public class AsonAdapterTest {

  private SerializationAdapterTest test;

  @Before
  public void setup() {
    test = new SerializationAdapterTest();
    test.uuid = UUID.randomUUID();
  }

  @Test
  public void test_adapter() {
    Ason serialized = Ason.serialize(test);
    assertEquals(serialized.get("uuid"), test.uuid.toString());
    SerializationAdapterTest deserialized =
        Ason.deserialize(serialized, SerializationAdapterTest.class);
    assertEquals(test, deserialized);
  }

  private static class SerializationAdapterTest {
    @AsonAdapter(UUIDAdapter.class)
    public UUID uuid;

    public String string = "Test";

    @Override
    public boolean equals(Object o) {
      if (o instanceof SerializationAdapterTest) {
        SerializationAdapterTest compare = (SerializationAdapterTest) o;
        return Objects.equals(compare.uuid, uuid) && Objects.equals(compare.string, string);
      }
      return false;
    }
  }

  public static class UUIDAdapter extends AsonSerializerAdapter<UUID> {
    @Override
    public UUID fromJson(String json) {
      return UUID.fromString(json);
    }

    @Override
    public String toJson(UUID value) {
      return value.toString();
    }
  }
}
