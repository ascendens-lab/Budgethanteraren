import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.IO.readln;

public class Budgethanteraren {
  Scanner scanner = new Scanner(System.in);

 private final List<Transaction> transaktioner = new ArrayList<>();

  private int transaktionsIndex = 0;
  private BigDecimal belopp;
  private LocalDate datum;
  private String kategori;
  private TransactionType transaktionsTyp;
  private int i = 0;

  String menuInput;



  public void main(){
      testValues();





    do {
      IO.println("================");
      IO.println("Budgethanteraren");
      IO.println("================");
      IO.println("1. Lagg till transaktion");
      IO.println("2. Visa alla transaktioner");
      IO.println("3. Visa saldo och sammanstallning per kategori");
      IO.println("4. Filtrera transaktioner");
      IO.println("5. Spara till fil");
      IO.println("e. Avsluta");

      menuInput = readln();


      switch (menuInput) {

        case "1" -> addTransaction();
        case "2" -> showTransactions();
        case "3" -> showTransactionAndCategorySummary();
        case "4" -> filterTransAction();
        case "5" -> saveToFile();
        case "e", "E" -> System.exit(0);

        default -> IO.println("Ogiltigt val.");

      }

      }while (true);

  }



    private void addTransaction() {
      boolean correct = false;
      while (!correct)
        try {
          belopp = new BigDecimal(IO.readln("Belopp: "));
            if (belopp.compareTo(BigDecimal.ZERO) > 0) {
                 correct = true;
            } else {
                IO.println("Ange beloppet som ett positivt värde. Välj sedan Utgift eller Inkomst.");
            }


        }catch (NumberFormatException e){
          IO.println("Felaktigt format på beloppet.");
        }


   correct = false;
    while (!correct) {
      try {
        datum = LocalDate.parse(IO.readln("Datum för transaktionen (xxxx-xx-xx): "));
        correct = true;

      } catch (DateTimeParseException e) {
        IO.println("Felaktigt datum, försök igen.");
      }
    }

    //Använde Scaner då readln() inte vill läsa in å,ä,ö.

    IO.print("Kateogori: ");
    kategori = scanner.nextLine();


           correct = false;
     while (!correct){
       try{
         transaktionsTyp = TransactionType.valueOf(IO.readln("Typ av transaktion (INKOMST,UTGIFT: ").
                 toUpperCase());
         correct = true;
       } catch (IllegalArgumentException e) {
         IO.println("Ogiltig transaktionstyp. Ange INKOMST eller UTGIFT.");
       }

       transaktioner.add(transaktionsIndex, new Transaction(belopp, datum,kategori,transaktionsTyp));


     }


  }
  private void showTransactions() {

      if (transaktioner.size()>0) {

          for (Transaction transaktion: transaktioner )
              IO.println(transaktion);
      } else {
          IO.println("Det finns inga transaktioner registrerade.");
      }


  }
  private void showTransactionAndCategorySummary() {

      Map<String, List<Transaction>> grupperade =
              transaktioner.stream()
                      .collect(Collectors.groupingBy(transaction -> transaction.kategori().toLowerCase()));
                    //Grupperar transaktionerna efter kategori.

      IO.println(grupperade);

  }

  private void filterTransAction() {
  }
  private void saveToFile() {
  }

   private void testValues(){
       transaktioner.add(new Transaction(
               new BigDecimal("346"),
               LocalDate.of(2024, 3, 15),
               "Nöje",
               TransactionType.UTGIFT
       ));

       transaktioner.add(new Transaction(
               new BigDecimal("25000"),
               LocalDate.of(2024, 3, 25),
               "lön",
               TransactionType.INKOMST
       ));

       transaktioner.add(new Transaction(
               new BigDecimal("8500"),
               LocalDate.of(2024, 3, 28),
               "boende",
               TransactionType.UTGIFT
       ));

       transaktioner.add(new Transaction(
               new BigDecimal("1250"),
               LocalDate.of(2024, 4, 2),
               "mat",
               TransactionType.UTGIFT
       ));

       transaktioner.add(new Transaction(
               new BigDecimal("500"),
               LocalDate.of(2024, 4, 5),
               "nöje",
               TransactionType.UTGIFT
       ));

       transaktioner.add(new Transaction(
               new BigDecimal("1200"),
               LocalDate.of(2024, 4, 10),
               "övrigt",
               TransactionType.INKOMST
       ));

       transaktioner.add(new Transaction(
               new BigDecimal("650"),
               LocalDate.of(2024, 4, 12),
               "transport",
               TransactionType.UTGIFT
       ));

       transaktioner.add(new Transaction(
               new BigDecimal("25000"),
               LocalDate.of(2024, 4, 25),
               "lön",
               TransactionType.INKOMST
       ));

       transaktioner.add(new Transaction(
               new BigDecimal("1100"),
               LocalDate.of(2024, 4, 27),
               "mat",
               TransactionType.UTGIFT
       ));

       transaktioner.add(new Transaction(
               new BigDecimal("299"),
               LocalDate.of(2024, 4, 30),
               "nöje",
               TransactionType.UTGIFT
       ));
   }

}

