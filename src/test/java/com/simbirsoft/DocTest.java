package com.simbirsoft;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DocTest {

    @Test
    public void docTest() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream stream = classLoader.getResourceAsStream("Sample.docx")) {
            XWPFDocument docxFile = new XWPFDocument(stream);
            XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(docxFile);

            XWPFHeader docHeader = headerFooterPolicy.getDefaultHeader();

            List<XWPFParagraph> paragraphs = docxFile.getParagraphs();
            List<String> paragraphsAsText = new ArrayList<>();

            for (XWPFParagraph paragraph : paragraphs) {
                paragraphsAsText.add(paragraph.getText());
            }

            XWPFFooter docFooter = headerFooterPolicy.getDefaultFooter();

            Assertions.assertAll(
                    () -> assertThat(docxFile).isNotNull(),
                    () -> assertThat(docHeader.getText()).contains("Александр Пушкин: Зимний вечер"),
                    () -> assertThat(paragraphsAsText).contains("Буря мглою небо кроет,", "Вихри снежные крутя;", "То, как зверь, она завоет,", "То заплачет, как дитя,"),
                    () -> assertThat(docFooter.getText()).contains("1825 г.")
            );
        }
    }
}
