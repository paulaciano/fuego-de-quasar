package org.rebellion.finder.split.add.processor;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.rebellion.finder.split.add.model.SplitAddRequest;
import org.rebellion.finder.split.add.utils.SatellitesInfoManager;

import java.util.Arrays;

import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SplitAddProcessorTest {

    @Mock
    SatellitesInfoManager manager;

    @InjectMocks
    SplitAddProcessor processor;

    @Test
    public void process_whenOk() {
        when(manager.persistSatelliteData(any())).thenReturn(null);

        Object response = processor.process(getSatelliteInputOk());
        assertNull(response);
    }

    @Test(expected = RuntimeException.class)
    public void process_whenPersistFails() {
        when(manager.persistSatelliteData(any())).thenThrow(RuntimeException.class);
        processor.process(getSatelliteInputOk());
    }

    private SplitAddRequest getSatelliteInputOk() {
        return new SplitAddRequest().withName("Kenobi")
                    .withDistance(100.)
                    .withMessage(Arrays.asList("Este", "", "", "mensaje", ""));
    }

}
