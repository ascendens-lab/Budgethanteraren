import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

import static java.lang.IO.readln;

public class Budgethanteraren {
  Scanner scanner = new Scanner(System.in);

 private List<Transaction> transaktioner = new ArrayList<>();

  private int transaktionsIndex = 0;
  private BigDecimal belopp;
  private LocalDate datum;
  private String kategori;
  private TransactionType transaktionsTyp;
  private int i = 0;

  String menuInput;
  public void main(){
    IO.println(System.getProperty("sun.stdin.encoding"));


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
        case "3" -> showTransacitonAndCategorySummary();
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
          correct = true;
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
    Scanner scanner = new Scanner(System.in);
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

          for (i = 0; i < transaktioner.size(); i++)
              IO.println(transaktioner.get(i));
      } else {
          IO.println("Det finns inga transaktioner registrerade.");
      }


  }
  private void showTransacitonAndCategorySummary() {
  }

  private void filterTransAction() {
  }
  private void saveToFile() {
  }



}

