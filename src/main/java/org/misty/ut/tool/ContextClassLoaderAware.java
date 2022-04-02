package org.misty.ut.tool;

import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.util.concurrent.atomic.AtomicReference;

public class ContextClassLoaderAware {

    AtomicReference<ClassLoader> contextClassLoader = new AtomicReference<>();

    @SuppressWarnings("rawtypes")
    public <T> T aware(Answer answer, T mock) {
        return Mockito.doAnswer((invocationOnMock) -> {
            contextClassLoader.set(Thread.currentThread().getContextClassLoader());
            return answer.answer(invocationOnMock);
        }).when(mock);
    }

    public ClassLoader getContextClassLoader() {
        return contextClassLoader.get();
    }

}
