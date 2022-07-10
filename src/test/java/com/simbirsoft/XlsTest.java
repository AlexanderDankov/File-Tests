package com.simbirsoft;

import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;

public class XlsTest {

    @Test
    public void xlsTest() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream stream = classLoader.getResourceAsStream("Sample.xlsx")) {
            XLS xlsFile = new XLS(stream);

            Assertions.assertAll(
                    () -> assertThat(xlsFile).isNotNull(),
                    () -> assertThat(xlsFile.excel.getNumberOfSheets()).isEqualTo(1),
                    () -> assertThat(xlsFile.excel.getSheetAt(0).getPhysicalNumberOfRows()).isEqualTo(701),
                    () -> assertThat(xlsFile.excel.getSheetAt(0).getRow(0).getPhysicalNumberOfCells()).isEqualTo(16),
                    () -> assertThat(xlsFile.excel.getSheetAt(0).getSheetName()).isEqualTo("Sheet1"),
                    () -> assertThat(xlsFile.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue()).isEqualTo("Segment")
            );
        }
    }
}
