package com.simbirsoft;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;

public class PdfTest {

    @Test
    public void pdfTest() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream stream = classLoader.getResourceAsStream("sample.pdf")) {
            PDF pdf = new PDF(stream);

            Assertions.assertAll(
                    () -> assertThat(pdf).isNotNull(),
                    () -> assertThat(pdf.numberOfPages).isEqualTo(4),
                    () -> assertThat(pdf.author).isEqualTo("Accelio Corporation"),
                    () -> assertThat(pdf.title).isEqualTo("PDF Bookmark Sample"),
                    () -> assertThat(pdf.signed).isFalse()
            );
        }
    }
}
