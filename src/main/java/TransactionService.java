import repositories.TransactionRepository;

import java.util.List;

public class TransactionService {
    private final PersonsService personsService;
    private final TransactionRepository transactionRepository;

    public TransactionService(PersonsService personsService, TransactionRepository transactionRepository) {
        this.personsService = personsService;
        this.transactionRepository = transactionRepository;
    }

    List<String> getPersonRolesOfAllTransactions() {
        throw new UnsupportedOperationException();
    }
}
