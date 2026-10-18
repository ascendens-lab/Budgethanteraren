import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Map;


public class BudgetCalculator {


    public Map<String, BigDecimal> calculateCategorySums(
            List<Transaction> transaktioner) {

        return transaktioner.stream()
                .collect(Collectors.groupingBy(
                        transaction -> transaction.kategori().toLowerCase(),
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                Transaction::belopp,
                                BigDecimal::add
                        )
                ));
    }
}
