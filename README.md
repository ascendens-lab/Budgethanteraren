# Budgethanteraren

Början med menyn och menyval med switch är samma som i föregående uppgift.
Tanken var att göra någon form av visualisering innan jag började den här gången, men
jag kom inte fram till något bra sätt att gör det på så jag gjorde som förra gången. 😊
Det innebar även den här gången en del merarbetet då jag först missade några av kraven, som att skapa
en generisk klass.

När jag kom fram till den systematiska testningen så insåg jag att det var nödvändigt att dela upp metoderna
i mindre metoder. Jag skapade klassen BudgetCalculator som sköter beräkningarna och lade den en egen fil.
Hanteringen av transaktionerna lade jag i det generiska klassen Repository i en annan fil.

## Felsökningen

Jag hittade inga fel vid den automatiska felsökningen men jag hittade två saker vid den manuella.

### Transaktion lades till trots felaktig transaktionstyp

Vid manuell testning under utveckling av metoden för att lägga till transaktioner angav jag en ogiltig transaktionstyp.
Trots att programmet skrev ut ett felmeddelande lades transaktionen till i listan för transaktioner.

När jag undersökte koden för inmatning och validering av transaktionstypen såg jag att det efter inte fanns något
som kontrollerade om det var en validerad transaktion som lades till. Eftersom att det redan fanns en boolean correct som
hade koll på det var det bara att lägga till en if-sats före add-koden. 

 ### Felhantering vid inläsning från fil

Vid manuell testning ändrade jag den befintliga datafilen och tog bort uppgifter för belopp, datum och transaktionstyp.
När programmet läste in filen upptäckte jag att det kunde krascha om någon av uppgifterna saknades.

Jag kompletterade felhanteringen med catch för de undantag som kunde uppstå vid felaktiga eller ofullständiga
uppgifter i filen så att programmet kunde hantera felen utan att krascha.




