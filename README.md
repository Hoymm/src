# Systemy czasu rzeczywistego

Aplikacja ukazuje rozwiązanie problemu <b>producent-konsument</b> z użyciem blokad.

Program to symulacja restauracji umożliwiającej składanie zamówień przez wiele urządzeń jednocześnie.
Zamówienia są umieszczane w buforze z ograniczoną pojemnością, następnie odbierane przez stanowiska do przetwarzania zamówień.  
Występuje tutaj problem producenta i konsumenta, ponieważ zależnie od:
<ul>
  <li>szybkości składania/przetwarzania zamówień</li>
  <li>ilości urządzeń składających/przetwarzających zamówienia</li>
</ul>
Bufor może przechodzić zarówno w stan "pusty" jak i "pełny".  
<br />
<br />

Problem można oczywiście rozwiązać znacznie prościej używając kolekcji 
<a href="https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ArrayBlockingQueue.html">ArrayBlockingQueue</a>, 
ale naszym celem była symulacja z zastosowaniem blokad.

<hr>

### Kompilacja oraz uruchomienie aplikacji
```bash
mvn clean package
java -jar target/systemu_czasu_rzeczywistego-1.0.jar
```
<hr>

### Twórcy
- Damian Muca
- Tomasz Widomski
- Jakub Piwowarczyk
- Angelika Okarmus
