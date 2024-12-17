# Descrizione

Lo scopo di questo progetto è la creazione di un'applicazione per creare e gestire liste della spesa condivise tra più utenti. Ogni utente può aggiungere o rimuovere elementi dalla lista.

---

## Funzioni

- Creazione di liste della spesa

- Aggiunta e rimozione di elementi dalle liste

- Condivisione delle liste con altri utenti

- Notifiche in tempo reale per modifiche alla lista

## Tecnologie

- Spring Boot 

- Spring WebFlux (per supporto reattivo)

- Thymeleaf

- MySQL

## Team

*Daniel Persi*

*Marco Iaquinandi*

*Pietro Molendini*

*Paolo Botta*

*Alessandro Magri*

---

## Step 1 : 

Come primo step del progetto abbiamo individuato le entità su cui lavorare le quali sono: 

- Liste: Entità che contiene tutti i prodotti che vengono aggiunti o già presenti.
- Prodotto: Entità che ha un nome e un prezzo e che viene poi inserito nella lista.
- Quantità: Attributo che porta con la quantità di prodotto acquistato.

## Step 2 : 

Come secondo step abbiamo realizzato il DB con le precedenti entità definite in questo modo:

- Liste(id_list, nome_lista)
- Prodotti(id_prodotti, nome_prod, prezzo_prod)
- Acquisti(id_acquisto, id_prodotti, id_liste)
