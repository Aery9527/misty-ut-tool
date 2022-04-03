package org.misty.ut.tool;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.misty.expose.core.MistyExpose;
import org.misty.expose.core.MistyExposeDetector;

import java.util.List;

class MistyUtToolExposerTest {

    @Test
    void spi() {
        List<MistyExpose> list = MistyExposeDetector.findBySPI();
        list.forEach(System.out::println);

        Assertions.assertThat(list).contains(new MistyUtToolExposer());
    }

}
