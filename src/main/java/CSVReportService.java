import model.Person;
import repositories.TransactionRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.AbstractMap;
import java.util.stream.Collectors;

public class CSVReportService {

    private final PersonsService personsService;
    private final TransactionRepository transactionRepository;

    public CSVReportService(PersonsService personsService, TransactionRepository transactionRepository) {
        this.personsService = personsService;
        this.transactionRepository = transactionRepository;
    }

    /**
     * Retrieve the average consumption (transaction amount) per @{@link Person}'s distinct roles during the last month
     *
     * Note that roles are just tags that each person is assigned. ie 'student', 'gamer', 'athlete', 'parent'
     * a Person may have multiple roles or none.
     *
     * @return data in csv file format,
     *         where the first line depict the roles
     *         and the second line the average consumption per role
     * ie: (formatted example -- the actual output should be just comma separated)
     * |student, gamer, parent|
     * |10.50  , 20.10, 0     |
     */
    public String getAverageConsumptionPerRoleDuringTheLastMonth() {
        String csvContents = transactionRepository.getTransactions().stream()
                .filter(t -> t.getDate().getMonth() == LocalDateTime.now().getMonth())
                .map(t -> { return new AbstractMap.SimpleEntry<>(personsService.getPersonByEmailAddress(t.getEmailAddress()), t.getAmount()); })
                .filter(p -> p.getKey().isPresent())
                .map(p -> { return new AbstractMap.SimpleEntry<>(p.getKey().get(), p.getValue()); })
                .map(p -> {return p.getKey().getRoles().stream().map(r -> {return new AbstractMap.SimpleEntry<>(r, p.getValue()); }).collect(Collectors.toList()); })
                .flatMap(r -> r.stream())
                .collect(Collectors.groupingBy(r -> r.getKey(), Collectors.averagingDouble(r -> r.getValue())))
                .entrySet().stream()
                .map(r -> { return new AbstractMap.SimpleEntry<>(r.getKey(), r.getValue().toString()); })
                .reduce((r1, r2) -> new AbstractMap.SimpleEntry<String, String>(r1.getKey() + "," + r2.getKey(), r1.getValue() + "," + r2.getValue()))
                .map(r -> {return r.getKey() + "\n" + r.getValue(); })
                .get();

        return csvContents;
    }
}
