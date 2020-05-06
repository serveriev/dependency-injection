package io.lenur.di.util;

import io.lenur.di.service.SmsNotification;
import io.lenur.di.service.Notification;
import io.lenur.di.service.Command;
import io.lenur.di.service.AddCommand;
import io.lenur.di.service.EmailNotification;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ProcessorTest {
    @Test
    public void processClasses() {
        FileScanner scanner = new FileScanner();
        List<Class<?>> classes = scanner.getClasses("io.lenur.di");

        Processor processor = new Processor();
        processor.process(classes);
        Map<Class<?>, Object> instances = processor.getInstances();

        Assert.assertEquals(instances.size(), 2);
        Assert.assertTrue(instances.get(Notification.class) instanceof SmsNotification);
        Assert.assertTrue(instances.get(Command.class) instanceof AddCommand);
    }

    @Test
    public void processEmptyConfig() {
        List<Class<?>> classes = Collections.singletonList(EmailNotification.class);

        Processor processor = new Processor();
        processor.process(classes);
        Map<Class<?>, Object> instances = processor.getInstances();

        Assert.assertEquals(instances.size(), 0);
    }
}
