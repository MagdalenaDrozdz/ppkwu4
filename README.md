# Mobilny vCard PŁ
API, które dla wpisanego rodzaju fachowca wyszukuje wszystkich fachowców na podstawie wyszukiwarki ze strony z https://panoramafirm.pl/szukaj.

# Działanie
Podajemy adres:
```
localhost:8080/
```
Pojawia nam się wyszukiwarka, w której możemy podać nazwę fachowca jakiego chcemy wyszukać. Po naciśnieciu przycisku szukaj przechodzimy do strony:

```
localhost:8080/users
```
Na stronie dostajemy wyniki wyszukiwania - wszystkich znalezionych fachowców i ich dane: nazwę, adres, telefon, mail, stronę internetową, link do mapy z lokalizacją firmy i link do generowania wizytówki dla danej firmy.
Jeśli wybierzemy generowanie wizytówki vcard to przejdziemy pod adres:

```
localhost:8080/card/{idFirmy}
```
idFirmy - to identyfikator przypisany wyszukanym na danej stronie firmom, określa dla której firmy chcemy wygenerować wizytówkę.
Pod danym adresem zostanie wygenerowana wizytówka vcard w postaci pliku vcf. 
Przykładowy wygenerowany plik: https://github.com/MagdalenaDrozdz/ppkwu4/blob/main/cardUser0.vcf
Na stronie mamy możliwość przechodzenia do kolejnych stron poprzez przycisk next na końcu strony, zostaniemy przekierowani do kolejnej strony z wyszukanymi firmami, pod adres: 
```
localhost:8080/users/next
```



