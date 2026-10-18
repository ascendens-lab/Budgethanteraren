import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;
import static java.lang.IO.readln;


public class Budgethanteraren {
  Scanner scanner = new Scanner(System.in);
    private Repository<Transaction> repository = new Repository<>();
    private BudgetCalculator budgetCalculator = new BudgetCalculator();


    private BigDecimal belopp;
  private LocalDate datum;
  private String kategori;
  private TransactionType transaktionsTyp;
  private int i = 0;

  String menuInput;

  public void main(){


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
                throw new InvalidTransactionException(
                        "Beloppet måste vara positivt."  //Felmeddelandet skickas till konstruktorn
                                                            // InvalidTransactionException() som parametern message.
                );
            }

        }catch (NumberFormatException e){
          IO.println("Felaktigt format på beloppet.");
        } catch (InvalidTransactionException e) {
            IO.println(e.getMessage()); //e.getMessage() hämtar felmeddelandet
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

        IO.print("Kateogori: ");
    kategori = scanner.nextLine();//Använde Scanner då readln() inte vill läsa in å,ä,ö.

           correct = false;
     while (!correct){
       try{
         transaktionsTyp = TransactionType.valueOf(IO.readln("Typ av transaktion (INKOMST,UTGIFT: ").
                 toUpperCase());
         correct = true;
       } catch (IllegalArgumentException e) {
         IO.println("Ogiltig transaktionstyp. Ange INKOMST eller UTGIFT.");
       }

       if(correct)
         repository.add(
                 new Transaction(belopp, datum, kategori, transaktionsTyp)
         );



     }


  }
  private void showTransactions() {

      if (repository.findAll().size()>0) {

          IO.println(repository.findAll());

      } else {
          IO.println("Det finns inga transaktioner registrerade.");
      }

  }
  private void showTransactionAndCategorySummary() {

      Map<String, BigDecimal> summorPerKategori = budgetCalculator.calculateCategorySums(repository.findAll());



      IO.println(summorPerKategori);

  }

  private void filterTransAction() {
             int filterVal;
      boolean correct = false;
        while (!correct)
            try {
                filterVal = Integer.parseInt(IO.readln("För utgifter tryck 1, för inkomster tryck 2: "));

                switch (filterVal) {

                    case 1 ->{
                        IO.println(repository.findWhere(
                                t -> t.typ() == TransactionType.UTGIFT
                        ));
                        correct= true;
                    }

                   case 2 ->{
                       IO.println(repository.findWhere(
                               t -> t.typ() == TransactionType.INKOMST
                       ));
                       correct = true;
                   }

                    default -> IO.println("Felaktigt val.");
                }

            } catch (NumberFormatException e) {
               IO.println("Felaktigt val.");

            }



  }
  private void saveToFile() {
      //Kommer senare.
  }


}

