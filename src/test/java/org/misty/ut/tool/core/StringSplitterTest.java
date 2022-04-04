package org.misty.ut.tool.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.converter.ArgumentConversionException;

import java.util.*;
import java.util.function.BiConsumer;

class StringSplitterTest {

    @SuppressWarnings("rawtypes")
    @Test
    void modify_converter() {
        Class<String> targetType = String.class;

        // putConverter
        String testTarget = "9527";
        StringSplitter.putConverter(targetType, (source) -> testTarget);
        AssertionsExt.assertThat(new StringSplitter().convert("kerker", targetType)).isEqualTo(testTarget);
        AssertionsExt.assertThat(StringSplitter.getConverterHandleTypes())
                .contains(String[].class, List.class, Set.class, String.class);

        // removeConverter
        StringSplitter.removeConverter(targetType);
        AssertionsExt.assertThrown(() -> new StringSplitter().convert("kerker", targetType))
                .isInstanceOf(ArgumentConversionException.class);
        AssertionsExt.assertThat(StringSplitter.getConverterHandleTypes())
                .contains(String[].class, List.class, Set.class);

        // reset
        Class<Date> targetType1 = Date.class;
        Class<Map> targetType2 = Map.class;
        StringSplitter.putConverter(targetType1, (source) -> null);
        StringSplitter.putConverter(targetType2, (source) -> null);
        AssertionsExt.assertThat(StringSplitter.getConverterHandleTypes())
                .contains(String[].class, List.class, Set.class, targetType1, targetType2);

        StringSplitter.resetConverter();

        AssertionsExt.assertThat(StringSplitter.getConverterHandleTypes())
                .contains(String[].class, List.class, Set.class);
    }

    @Test
    void splitWith() {
        char splitter = ',';

        BiConsumer<String, List<String>> test = (source, tester) -> {
            List<String> result = new ArrayList<>();
            StringSplitter.splitWith(source, splitter, result::add);
            AssertionsExt.assertThat(result).containsAll(tester);
        };

        test.accept("", Collections.singletonList(""));
        test.accept("a", Collections.singletonList("a"));
        test.accept("a,b", Arrays.asList("a", "b"));
        test.accept("a,b,, ", Arrays.asList("a", "b", "", " "));
        test.accept(",a,b,     ", Arrays.asList("", "a", "b", "     "));
    }

    @Test
    void convertToArray() {
        Object result = new StringSplitter().convert("a,null,#null", String[].class);
        AssertionsExt.assertThat(result).isInstanceOf(String[].class);
        AssertionsExt.assertThat((String[]) result).contains("a", "null", null);
    }

    @SuppressWarnings("unchecked")
    @Test
    void convertToList() {
        Object result = new StringSplitter().convert("a,null,#null", List.class);
        AssertionsExt.assertThat(result).isInstanceOf(List.class);
        AssertionsExt.assertThat((List<String>) result).contains("a", "null", null);
    }

    @SuppressWarnings("unchecked")
    @Test
    void convertToSet() {
        Object result = new StringSplitter().convert("a,null,#null", Set.class);
        AssertionsExt.assertThat(result).isInstanceOf(Set.class);
        AssertionsExt.assertThat((Set<String>) result).contains("a", "null", null);
    }

}
