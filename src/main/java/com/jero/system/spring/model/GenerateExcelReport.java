/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jero.system.spring.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class GenerateExcelReport {

    public static ByteArrayInputStream usersToExcel(List<Afiliado> users) throws IOException {
            String[] COLUMNs = { "Nombres", "Número de identificación", "Email", "Celular", "Estado", "Fecha Creación" };
            try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
                    Sheet sheet = workbook.createSheet("Users");

                    Font headerFont = workbook.createFont();
                    headerFont.setBold(true);
                    headerFont.setColor(IndexedColors.BLUE.getIndex());

                    CellStyle headerCellStyle = workbook.createCellStyle();
                    headerCellStyle.setFont(headerFont);

                    // Header Row
                    Row headerRow = sheet.createRow(0);

                    // Table Header
                    for (int col = 0; col < COLUMNs.length; col++) {
                            Cell cell = headerRow.createCell(col);
                            cell.setCellValue(COLUMNs[col]);
                            cell.setCellStyle(headerCellStyle);
                    }

                    int rowIdx = 1;
                    for (Afiliado user : users) {
                            String conversionFecha = new SimpleDateFormat("dd/MM/yyyy").format(user.getFecha_adiciono());
                            Row row = sheet.createRow(rowIdx++);

                            row.createCell(0).setCellValue(user.getNombre_completo().toString());
                            row.createCell(1).setCellValue(user.getNumero_identicicacion());
                            row.createCell(2).setCellValue(user.getCorreo_electronico());
                            row.createCell(3).setCellValue(user.getCelular());
                            row.createCell(4).setCellValue(user.getDesc_estado());
                            row.createCell(5).setCellValue(conversionFecha);
                    }

                    //Auto-size all the above columns
                    sheet.autoSizeColumn(0);
                    sheet.autoSizeColumn(1);
                    sheet.autoSizeColumn(2);
                    sheet.autoSizeColumn(3);
                    sheet.autoSizeColumn(4);
                    sheet.autoSizeColumn(5);

                    workbook.write(out);
                    return new ByteArrayInputStream(out.toByteArray());
        }
    }
    
    public static ByteArrayInputStream aportesToExcel(List<Aportes> aportes) throws IOException {
            String[] COLUMNs = { "Folder", "Número Identificación Titular", "Nombres Titular" };
            try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
                    Sheet sheet = workbook.createSheet("Aportes");

                    Font headerFont = workbook.createFont();
                    headerFont.setBold(true);
                    headerFont.setColor(IndexedColors.BLUE.getIndex());

                    CellStyle headerCellStyle = workbook.createCellStyle();
                    headerCellStyle.setFont(headerFont);

                    // Header Row
                    Row headerRow = sheet.createRow(0);

                    // Table Header
                    for (int col = 0; col < COLUMNs.length; col++) {
                            Cell cell = headerRow.createCell(col);
                            cell.setCellValue(COLUMNs[col]);
                            cell.setCellStyle(headerCellStyle);
                    }

                    int rowIdx = 1;
                    for (Aportes aporte : aportes) {                            
                            Row row = sheet.createRow(rowIdx++);
                            row.createCell(0).setCellValue("FUN" + String.valueOf(aporte.getIdFolder()));                            
                            
                            if(aporte.getNumero_identicicacion() != null)
                                row.createCell(1).setCellValue(String.valueOf(aporte.getNumero_identicicacion()));
                            else
                                row.createCell(2).setCellValue("");                            
                            
                            if(aporte.getNombre_completo() != null)
                                row.createCell(2).setCellValue(aporte.getNombre_completo());
                            else
                                row.createCell(2).setCellValue("");
                    }

                    //Auto-size all the above columns
                    sheet.autoSizeColumn(0);
                    sheet.autoSizeColumn(1);
                    sheet.autoSizeColumn(2);

                    workbook.write(out);
                    return new ByteArrayInputStream(out.toByteArray());
        }
    }
    
    public static ByteArrayInputStream aportesMesToExcel(List<Aportes> aportes) throws IOException {
            String[] COLUMNs = { "Folder", "Número Identificación Titular", "Nombres Titular", "Fecha registro", "Valor", "usuario Adicionó" };
            try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
                    Sheet sheet = workbook.createSheet("Aportes");

                    Font headerFont = workbook.createFont();
                    headerFont.setBold(true);
                    headerFont.setColor(IndexedColors.BLUE.getIndex());

                    CellStyle headerCellStyle = workbook.createCellStyle();
                    headerCellStyle.setFont(headerFont);

                    // Header Row
                    Row headerRow = sheet.createRow(0);

                    // Table Header
                    for (int col = 0; col < COLUMNs.length; col++) {
                            Cell cell = headerRow.createCell(col);
                            cell.setCellValue(COLUMNs[col]);
                            cell.setCellStyle(headerCellStyle);
                    }

                    int rowIdx = 1;
                    for (Aportes aporte : aportes) {                            
                            Row row = sheet.createRow(rowIdx++);
                            row.createCell(0).setCellValue("FUN" + String.valueOf(aporte.getIdFolder()));                            
                            
                            if(aporte.getNumero_identicicacion() != null)
                                row.createCell(1).setCellValue(String.valueOf(aporte.getNumero_identicicacion()));
                            else
                                row.createCell(2).setCellValue("");                            
                            
                            if(aporte.getNombre_completo() != null)
                                row.createCell(2).setCellValue(aporte.getNombre_completo());
                            else
                                row.createCell(2).setCellValue("");
                            
                            row.createCell(3).setCellValue(String.valueOf(aporte.getFecha_registro_aporte()));
                            row.createCell(4).setCellValue(String.valueOf(aporte.getValor_aporte()));
                            row.createCell(5).setCellValue(String.valueOf(aporte.getUsuarioAdi()));
                    }

                    //Auto-size all the above columns
                    sheet.autoSizeColumn(0);
                    sheet.autoSizeColumn(1);
                    sheet.autoSizeColumn(2);
                    sheet.autoSizeColumn(3);
                    sheet.autoSizeColumn(4);
                    sheet.autoSizeColumn(5);

                    workbook.write(out);
                    return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
