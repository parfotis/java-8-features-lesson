package async;

import model.Transaction;
import org.junit.Test;
import org.mockito.Mockito;
import repositories.TransactionRepository;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    private TransactionRepository transactionRepository = mock(TransactionRepository.class);
    private final TransactionService transactionService = new TransactionService(transactionRepository);

    @Test
    public void shouldGetTransactionsAsychronously() throws ExecutionException, InterruptedException {

        stub(transactionRepository.getTransactions())
                .toReturn(Collections.emptyList());

        Future<List<Transaction>> future = transactionService.getTransactions();

        assertEquals(Collections.emptyList(), future.get());
    }
}