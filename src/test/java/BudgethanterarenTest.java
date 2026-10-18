import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BudgethanterarenTest {

    Repository<Transaction> repository = new Repository<>();

    @Test
    void testAddTransaction() {

        Repository<String> testRepository = new Repository<>();
        testRepository.add("Hej");
        assertEquals(List.of("Hej"), testRepository.findAll());

    }
    @Test
    void testFilterTransAction(){
        Repository<Transaction> repository = new Repository<>();
         Transaction lon = new Transaction(
                new BigDecimal("100"),
                LocalDate.of(2026, 10, 17),
                "Lön",
                TransactionType.INKOMST
        );

        Transaction noje = new Transaction(
                new BigDecimal("50"),
                LocalDate.of(2026, 10, 17),
                "Nöje",
                TransactionType.UTGIFT
        );
        repository.add(lon);
        repository.add(noje);
//        ************************************

        List<Transaction> resultat =
                repository.findWhere(t -> t.typ() == TransactionType.UTGIFT);
//        ************************************

        assertEquals(List.of(noje), resultat);

    }

    @Test
    void testCalculateCategorySums(){
        BudgetCalculator budgetCalculator = new BudgetCalculator();
            repository.add(new Transaction(
                    new BigDecimal("346"),
                    LocalDate.of(2024, 3, 15),
                    "Nöje",
                    TransactionType.UTGIFT
            ));

            repository.add(new Transaction(
                    new BigDecimal("25000"),
                    LocalDate.of(2024, 3, 25),
                    "lön",
                    TransactionType.INKOMST
            ));

            repository.add(new Transaction(
                    new BigDecimal("8500"),
                    LocalDate.of(2024, 3, 28),
                    "boende",
                    TransactionType.UTGIFT
            ));

            repository.add(new Transaction(
                    new BigDecimal("1250"),
                    LocalDate.of(2024, 4, 2),
                    "nöje",
                    TransactionType.UTGIFT
            ));

//        *************************************************

        Map<String, BigDecimal> resultat =
                budgetCalculator.calculateCategorySums(repository.findAll());

//        **************************************************


        assertEquals(new BigDecimal("25001"), resultat.get("lön"));





    }
//       private void testValues(){
//           repository.add(new Transaction(
//               new BigDecimal("346"),
//               LocalDate.of(2024, 3, 15),
//               "Nöje",
//               TransactionType.UTGIFT
//       ));
//
//           repository.add(new Transaction(
//               new BigDecimal("25000"),
//               LocalDate.of(2024, 3, 25),
//               "lön",
//               TransactionType.INKOMST
//       ));
//
//           repository.add(new Transaction(
//               new BigDecimal("8500"),
//               LocalDate.of(2024, 3, 28),
//               "boende",
//               TransactionType.UTGIFT
//       ));
//
//           repository.add(new Transaction(
//               new BigDecimal("1250"),
//               LocalDate.of(2024, 4, 2),
//               "mat",
//               TransactionType.UTGIFT
//       ));
//
//           repository.add(new Transaction(
//               new BigDecimal("500"),
//               LocalDate.of(2024, 4, 5),
//               "nöje",
//               TransactionType.UTGIFT
//       ));
//
//           repository.add(new Transaction(
//               new BigDecimal("1200"),
//               LocalDate.of(2024, 4, 10),
//               "övrigt",
//               TransactionType.INKOMST
//       ));
//
//           repository.add(new Transaction(
//               new BigDecimal("650"),
//               LocalDate.of(2024, 4, 12),
//               "transport",
//               TransactionType.UTGIFT
//       ));
//
//           repository.add(new Transaction(
//               new BigDecimal("25000"),
//               LocalDate.of(2024, 4, 25),
//               "lön",
//               TransactionType.INKOMST
//       ));
//
//           repository.add(new Transaction(
//               new BigDecimal("1100"),
//               LocalDate.of(2024, 4, 27),
//               "mat",
//               TransactionType.UTGIFT
//       ));
//
//           repository.add(new Transaction(
//               new BigDecimal("299"),
//               LocalDate.of(2024, 4, 30),
//               "nöje",
//               TransactionType.UTGIFT
//       ));
//   }

}
