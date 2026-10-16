import java.math.BigDecimal;
import java.time.LocalDate;


 record Transaction(BigDecimal belopp,LocalDate date, String kategori,  TransactionType typ) {

}
