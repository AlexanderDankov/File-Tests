package com.simbirsoft;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;


public class TxtTest {

    @Test
    public void txtTest() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File txt = new File(classLoader.getResource("SampleText.txt").getFile());

        String fileText = FileUtils.readFileToString(txt, StandardCharsets.UTF_8);

        Assertions.assertAll(
                () -> assertThat(fileText).isNotNull(),
                () -> assertThat(fileText).contains("Александр Пушкин"),
                () -> assertThat(fileText).contains("Зимний вечер"),
                () -> assertThat(fileText).contains("1825 г.")
        );
    }
}
