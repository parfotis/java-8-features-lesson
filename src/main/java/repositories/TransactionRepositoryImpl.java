package repositories;

import daos.TransactionDAO;
import model.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public class TransactionRepositoryImpl implements TransactionRepository {

    private final TransactionDAO transactionDAO;

    public TransactionRepositoryImpl(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    @Override
    public List<Transaction> getTransactions() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Transaction> getTransactions(LocalDateTime from) {
        throw new UnsupportedOperationException();
    }
}
