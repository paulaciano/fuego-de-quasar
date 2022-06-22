package org.rebellion.finder.split.add.handler;

import com.amazonaws.services.lambda.runtime.Context;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.rebellion.finder.split.add.model.SplitAddRequest;
import org.rebellion.finder.split.add.processor.SplitAddProcessor;

import java.util.Arrays;

import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class FinderSplitAddHandlerTest {

    @Mock
    private Context context;

    @Mock
    private SplitAddProcessor processor;

    @InjectMocks
    private FinderSplitAddHandler handler;

    @Before
    public void setup() {
        doNothing().when(processor).process(any());
    }

    @Test
    public void handleRequest_whenOk() {
        Object response = handler.handleRequest(getSatelliteInputOk(), context);
        assertNull(response);
    }

    @Test(expected = RuntimeException.class)
    public void handleRequest_whenMissingField() {
        handler.handleRequest(getSatelliteInputMissingField(), context);
    }

    @Test(expected = RuntimeException.class)
    public void handleRequest_whenEmptyField() {
        handler.handleRequest(getSatelliteInputEmptyField(), context);
    }

    private SplitAddRequest getSatelliteInputOk() {
         return new SplitAddRequest().withName("Kenobi")
                    .withDistance(100.)
                    .withMessage(Arrays.asList("This", "", "", "secret", ""));
    }

    private SplitAddRequest getSatelliteInputMissingField() {
        return new SplitAddRequest().withName("Kenobi")
                .withMessage(Arrays.asList("This", "", "", "secret", ""));
    }

    private SplitAddRequest getSatelliteInputEmptyField() {
        return new SplitAddRequest().withName("")
                .withDistance(100.)
                .withMessage(Arrays.asList("This", "", "", "secret", ""));
    }
}
