package CBO.production.ATM.Transaction.Checker.services;

import CBO.production.ATM.Transaction.Checker.models.BankSwitchTransaction;
import CBO.production.ATM.Transaction.Checker.models.CBSTransaction;
import CBO.production.ATM.Transaction.Checker.models.ETSwitchTransaction;
import CBO.production.ATM.Transaction.Checker.repositories.BankSwitchTransactionRepository;
import CBO.production.ATM.Transaction.Checker.repositories.CBSTransactionRepository;
import CBO.production.ATM.Transaction.Checker.repositories.ETSwitchTransactionRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Iterator;

@Service
public class TransactionUploadService {
    @Autowired
    private CBSTransactionRepository cbsTransactionRepository;
    @Autowired
    private ETSwitchTransactionRepository etSwitchTransactionRepository;

    @Autowired
    private BankSwitchTransactionRepository bankSwitchTransactionRepository;

    public void uploadCBSTransactions(MultipartFile file) throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0); // Assuming transaction data is in the first sheet
        Iterator<Row> rows = sheet.iterator();

        // Skip the header row
        if (rows.hasNext()) {
            rows.next(); // Move to the next row
        }

        while (rows.hasNext()) {
            Row currentRow = rows.next();
            Iterator<Cell> cellsInRow = currentRow.iterator();

            CBSTransaction transaction = new CBSTransaction();
            while (cellsInRow.hasNext()) {
                Cell currentCell = cellsInRow.next();
                int columnIndex = currentCell.getColumnIndex();

                switch (columnIndex) {
                    case 0: // Retrieval Number
                        if (currentCell.getCellType() == CellType.STRING) {
                            transaction.setRetrievalNumber(currentCell.getStringCellValue());
                        } else {
                            // Handle numeric value as retrieval number
                            transaction.setRetrievalNumber(String.valueOf((int) currentCell.getNumericCellValue()));
                        }
                        break;
                    case 1: // Amount
                        if (currentCell.getCellType() == CellType.NUMERIC) {
                            transaction.setAmount(BigDecimal.valueOf(currentCell.getNumericCellValue()));
                        } else {
                            // Handle non-numeric value as amount
                            // For example, you can set it to zero or throw an exception
                            transaction.setAmount(BigDecimal.ZERO);
                        }
                        break;
                    case 2: // Transaction Date Time
                        if (currentCell.getCellType() == CellType.STRING) {
                            String dateTimeString = currentCell.getStringCellValue();
                            try {
                                transaction.setTransactionDateTime(LocalDateTime.parse(dateTimeString));
                            } catch (DateTimeParseException e) {
                                // Log warning for invalid date-time string
                                // logger.warn("Invalid date-time string: {}", dateTimeString);
                                // Set transactionDateTime to null
                                transaction.setTransactionDateTime(null);
                            }
                        } else {
                            // Handle non-string value as transaction date time
                            // Set transactionDateTime to null
                            transaction.setTransactionDateTime(null);
                        }
                        break;

                    // Add more cases for other columns as needed
                }
            }
            cbsTransactionRepository.save(transaction);
        }
        workbook.close();
    }



    public void uploadBankSwitchTransactions(MultipartFile file) throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0); // Assuming transaction data is in the first sheet
        Iterator<Row> rows = sheet.iterator();

        // Skip the header row
        if (rows.hasNext()) {
            rows.next(); // Move to the next row
        }

        while (rows.hasNext()) {
            Row currentRow = rows.next();
            Iterator<Cell> cellsInRow = currentRow.iterator();

            BankSwitchTransaction transaction = new BankSwitchTransaction();
            while (cellsInRow.hasNext()) {
                Cell currentCell = cellsInRow.next();
                int columnIndex = currentCell.getColumnIndex();

                switch (columnIndex) {
                    case 0: // Retrieval Number
                        if (currentCell.getCellType() == CellType.STRING) {
                            transaction.setRetrievalNumber(currentCell.getStringCellValue());
                        } else {
                            // Handle numeric value as retrieval number
                            transaction.setRetrievalNumber(String.valueOf((int) currentCell.getNumericCellValue()));
                        }
                        break;
                    case 1: // Amount
                        if (currentCell.getCellType() == CellType.NUMERIC) {
                            transaction.setAmount(BigDecimal.valueOf(currentCell.getNumericCellValue()));
                        } else {
                            // Handle non-numeric value as amount
                            // For example, you can set it to zero or throw an exception
                            transaction.setAmount(BigDecimal.ZERO);
                        }
                        break;
                    case 2: // Transaction Date Time
                        if (currentCell.getCellType() == CellType.STRING) {
                            String dateTimeString = currentCell.getStringCellValue();
                            try {
                                transaction.setTransactionDateTime(LocalDateTime.parse(dateTimeString));
                            } catch (DateTimeParseException e) {
                                // Log warning for invalid date-time string
                                // Set transactionDateTime to null
                                transaction.setTransactionDateTime(null);
                            }
                        } else {
                            // Handle non-string value as transaction date time
                            // Set transactionDateTime to null
                            transaction.setTransactionDateTime(null);
                        }
                        break;

                    // Add more cases for other columns as needed
                }
            }
            bankSwitchTransactionRepository.save(transaction);
        }
        workbook.close();
    }
    public void uploadETSwitchTransactions(MultipartFile file) throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0); // Assuming transaction data is in the first sheet
        Iterator<Row> rows = sheet.iterator();

        // Skip the header row
        if (rows.hasNext()) {
            rows.next(); // Move to the next row
        }

        while (rows.hasNext()) {
            Row currentRow = rows.next();
            Iterator<Cell> cellsInRow = currentRow.iterator();

            ETSwitchTransaction transaction = new ETSwitchTransaction();
            while (cellsInRow.hasNext()) {
                Cell currentCell = cellsInRow.next();
                int columnIndex = currentCell.getColumnIndex();

                switch (columnIndex) {
                    case 0: // Retrieval Number
                        if (currentCell.getCellType() == CellType.STRING) {
                            transaction.setRetrievalNumber(currentCell.getStringCellValue());
                        } else {
                            // Handle numeric value as retrieval number
                            transaction.setRetrievalNumber(String.valueOf((int) currentCell.getNumericCellValue()));
                        }
                        break;
                    case 1: // Amount
                        if (currentCell.getCellType() == CellType.NUMERIC) {
                            transaction.setAmount(BigDecimal.valueOf(currentCell.getNumericCellValue()));
                        } else {
                            // Handle non-numeric value as amount
                            // For example, you can set it to zero or throw an exception
                            transaction.setAmount(BigDecimal.ZERO);
                        }
                        break;
                    case 2: // Transaction Date Time
                        if (currentCell.getCellType() == CellType.STRING) {
                            String dateTimeString = currentCell.getStringCellValue();
                            try {
                                transaction.setTransactionDateTime(LocalDateTime.parse(dateTimeString));
                            } catch (DateTimeParseException e) {
                                // Log warning for invalid date-time string
                                // Set transactionDateTime to null
                                transaction.setTransactionDateTime(null);
                            }
                        } else {
                            // Handle non-string value as transaction date time
                            // Set transactionDateTime to null
                            transaction.setTransactionDateTime(null);
                        }
                        break;

                }
            }
            etSwitchTransactionRepository.save(transaction);
        }
        workbook.close();
    }

}
