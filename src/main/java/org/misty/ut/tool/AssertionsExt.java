package org.misty.ut.tool;

import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AssertionsExt extends Assertions {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssertionsExt.class);

    public static AbstractThrowableAssert<?, ? extends Throwable> assertThrown(ThrowableAssert.ThrowingCallable throwingCallable) {
        return Assertions.assertThatThrownBy(() -> {
            try {
                throwingCallable.call();
            } catch (Throwable t) {
                LOGGER.error("", t);
                throw t;
            }
        });
    }

}
