package org.misty.ut.tool;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.misty.expose.core.MistyExpose;
import org.misty.expose.core.MistyExposeDetector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

class MistyUtToolExposerTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    void spi() {
        List<MistyExpose> list = MistyExposeDetector.findBySPI();
        list.forEach(mistyExpose -> this.logger.info(mistyExpose.toString()));

        Assertions.assertThat(list).contains(new MistyUtToolExposer());
    }

}
