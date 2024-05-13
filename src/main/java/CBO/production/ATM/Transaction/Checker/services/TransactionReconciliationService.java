package CBO.production.ATM.Transaction.Checker.services;

import CBO.production.ATM.Transaction.Checker.models.BankSwitchTransaction;
import CBO.production.ATM.Transaction.Checker.models.CBSTransaction;
import CBO.production.ATM.Transaction.Checker.models.ETSwitchTransaction;
import CBO.production.ATM.Transaction.Checker.repositories.BankSwitchTransactionRepository;
import CBO.production.ATM.Transaction.Checker.repositories.CBSTransactionRepository;
import CBO.production.ATM.Transaction.Checker.repositories.ETSwitchTransactionRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionReconciliationService {
    @Autowired
    private CBSTransactionRepository cbsTransactionRepository;

    @Autowired
    private ETSwitchTransactionRepository etSwitchTransactionRepository;

    @Autowired
    private BankSwitchTransactionRepository bankSwitchTransactionRepository;

        public List<String> reconcileTransactions() {
            List<CBSTransaction> cbsTransactions = cbsTransactionRepository.findAll();
            List<BankSwitchTransaction> bankSwitchTransactions = bankSwitchTransactionRepository.findAll();

            var cbsTransactionMap = cbsTransactions.stream()
                    .collect(Collectors.toMap(CBSTransaction::getRetrievalNumber, t -> t));
            var bankSwitchTransactionMap = bankSwitchTransactions.stream()
                    .collect(Collectors.toMap(BankSwitchTransaction::getRetrievalNumber, t -> t));

            List<String> discrepancies = new ArrayList<>();

            for (String retrievalNumber : cbsTransactionMap.keySet()) {
                CBSTransaction cbsTransaction = cbsTransactionMap.get(retrievalNumber);
                BankSwitchTransaction bankSwitchTransaction = bankSwitchTransactionMap.get(retrievalNumber);

                if (bankSwitchTransaction == null) {
                    discrepancies.add("Discrepancy: Retrieval number " + retrievalNumber + " found in CBS but not in BankSwitch.");
                } else {
                    if (!cbsTransaction.getAmount().equals(bankSwitchTransaction.getAmount())) {
                        discrepancies.add("Discrepancy: Amount mismatch for retrieval number " + retrievalNumber);
                    }
                    // Add more checks as needed
                }
            }
            return discrepancies;
        }
    public List<String> reconcileThreeTransactions() {
        List<CBSTransaction> cbsTransactions = cbsTransactionRepository.findAll();
        List<BankSwitchTransaction> bankSwitchTransactions = bankSwitchTransactionRepository.findAll();
        List<ETSwitchTransaction> etSwitchTransactions = etSwitchTransactionRepository.findAll();

        var cbsTransactionMap = cbsTransactions.stream()
                .collect(Collectors.toMap(CBSTransaction::getRetrievalNumber, t -> t));
        var bankSwitchTransactionMap = bankSwitchTransactions.stream()
                .collect(Collectors.toMap(BankSwitchTransaction::getRetrievalNumber, t -> t));
        var etSwitchTransactionMap = etSwitchTransactions.stream()
                .collect(Collectors.toMap(ETSwitchTransaction::getRetrievalNumber, t -> t));

        List<String> discrepancies = new ArrayList<>();

        for (String retrievalNumber : cbsTransactionMap.keySet()) {
            CBSTransaction cbsTransaction = cbsTransactionMap.get(retrievalNumber);
            BankSwitchTransaction bankSwitchTransaction = bankSwitchTransactionMap.get(retrievalNumber);
            ETSwitchTransaction etSwitchTransaction = etSwitchTransactionMap.get(retrievalNumber);

            if (bankSwitchTransaction == null) {
                discrepancies.add("Discrepancy: Retrieval number " + retrievalNumber + " found in CBS but not in BankSwitch.");
            } else {
                if (!cbsTransaction.getAmount().equals(bankSwitchTransaction.getAmount())) {
                    discrepancies.add("Discrepancy: Amount mismatch for retrieval number " + retrievalNumber);
                }
                // Add more checks as needed for CBS and BankSwitch
            }

            if (etSwitchTransaction == null) {
                discrepancies.add("Discrepancy: Retrieval number " + retrievalNumber + " found in CBS but not in ETSwitch.");
            } else {
                if (!cbsTransaction.getAmount().equals(etSwitchTransaction.getAmount())) {
                    discrepancies.add("Discrepancy: Amount mismatch for retrieval number " + retrievalNumber);
                }
                // Add more checks as needed for CBS and ETSwitch
            }
        }
        return discrepancies;
    }
}

