package org.misty.ut.tool.core;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.misty.ut.tool.core.ContextClassLoaderAware;
import org.mockito.Mockito;

import java.net.URL;
import java.net.URLClassLoader;

class ContextClassLoaderAwareTest {

    public static class Mocker {
        public String kerker() {
            return "kerker";
        }
    }

    @Test
    void test() {
        ContextClassLoaderAware aware = new ContextClassLoaderAware();

        URLClassLoader targetClassLoader = new URLClassLoader(new URL[0], ClassLoader.getSystemClassLoader());
        Thread.currentThread().setContextClassLoader(targetClassLoader);

        Mocker mocker = Mockito.mock(Mocker.class);
        aware.aware((invocationOnMock) -> null, mocker).kerker();
        String mockResult = mocker.kerker();

        Assertions.assertThat(mockResult).isNotEqualTo(new Mocker().kerker());
        Assertions.assertThat(targetClassLoader == aware.getContextClassLoader()).isTrue();
    }

}
