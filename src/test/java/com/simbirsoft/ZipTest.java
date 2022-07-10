package com.simbirsoft;

import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.io.inputstream.ZipInputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class ZipTest {

    @Test
    public void zipTestWithPassword() throws Exception {
        File zipSource = new File("src/test/resources/zip_5MB.zip");
        try(InputStream is = new FileInputStream(zipSource)) {
            ZipInputStream zis = new ZipInputStream(is, "123".toCharArray());
            LocalFileHeader entry;

            List<String> fileNames = new ArrayList<>();
            while ((entry = zis.getNextEntry()) != null) {
                assertThat(entry).isNotNull();
                fileNames.add(entry.getFileName());
            }

            Assertions.assertAll(
                    () -> assertThat(fileNames.size()).isEqualTo(5),
                    () -> assertThat(fileNames.get(0)).isEqualTo("file_example_PNG_2500kB.jpg"),
                    () -> assertThat(fileNames.get(1)).isEqualTo("file_example_PPT_1MB.ppt"),
                    () -> assertThat(fileNames.get(2)).isEqualTo("file-example_PDF_1MB.pdf"),
                    () -> assertThat(fileNames.get(3)).isEqualTo("file-sample_1MB.doc"),
                    () -> assertThat(fileNames.get(4)).isEqualTo("file_example_ODS_5000.ods")
            );
        }
    }
}
