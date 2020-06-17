package async;

import model.Transaction;
import repositories.TransactionRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Future<List<Transaction>> getTransactions() {
        return CompletableFuture.supplyAsync(transactionRepository::getTransactions);
    }


}
