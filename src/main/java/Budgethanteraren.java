public class Budgethanteraren {

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

      menuInput = IO.readln();


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

  }
  private void showTransactions() {
  }
  private void showTransacitonAndCategorySummary() {
  }

  private void filterTransAction() {
  }
  private void saveToFile() {
  }



}

