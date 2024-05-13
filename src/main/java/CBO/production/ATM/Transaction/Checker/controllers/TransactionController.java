package CBO.production.ATM.Transaction.Checker.controllers;
import CBO.production.ATM.Transaction.Checker.services.TransactionReconciliationService;
import CBO.production.ATM.Transaction.Checker.services.TransactionUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Transactions")

public class TransactionController {
    @Autowired
    private TransactionUploadService transactionUploadService;

    @Autowired
    private TransactionReconciliationService transactionReconciliationService;

    @PostMapping("/upload/cbs")
    public ResponseEntity<String> uploadCBSTransactions(@RequestParam("file") MultipartFile file) {
        try {
            transactionUploadService.uploadCBSTransactions(file);
            return ResponseEntity.status(HttpStatus.OK).body("CBS transactions uploaded successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload CBS transactions: " + e.getMessage());
        } catch (Exception e) {
            // Handle other potential exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/upload/bank-switch")
    public ResponseEntity<String> uploadBankSwitchTransactions(@RequestParam("file") MultipartFile file) {
        try {
            transactionUploadService.uploadBankSwitchTransactions(file);
            return ResponseEntity.status(HttpStatus.OK).body("BankSwitch transactions uploaded successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload BankSwitch transactions.");
        }
    }
    @PostMapping("/upload/et-switch")
    public ResponseEntity<String> uploadETSwitchTransactions(@RequestParam("file") MultipartFile file) {
        try {
            transactionUploadService.uploadETSwitchTransactions(file);
            return ResponseEntity.status(HttpStatus.OK).body("Et Switch transactions uploaded successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload BankSwitch transactions.");
        }
    }

    @GetMapping("/reconcile")
    public ResponseEntity<List<String>> reconcileTransactions() {
        try {
            List<String> discrepancies = transactionReconciliationService.reconcileTransactions();
            if (discrepancies.isEmpty()) {
                return ResponseEntity.ok().body(discrepancies);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(discrepancies);
            }
        } catch (Exception e) {
            // Handle any exceptions that might occur during reconciliation
            List<String> errorMessage = new ArrayList<>();
            errorMessage.add("Failed to reconcile transactions. Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
    @GetMapping("/reconcileThree")
    public ResponseEntity<List<String>> reconcileThreeTransactions() {
        try {
            List<String> discrepancies = transactionReconciliationService.reconcileThreeTransactions();
            if (discrepancies.isEmpty()) {
                return ResponseEntity.ok().body(discrepancies);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(discrepancies);
            }
        } catch (Exception e) {
            // Handle any exceptions that might occur during reconciliation
            List<String> errorMessage = new ArrayList<>();
            errorMessage.add("Failed to reconcile transactions. Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }


}
