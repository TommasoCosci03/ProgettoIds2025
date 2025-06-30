# Progetto IDS 24/25 - Piattaforma di Digitalizzazione e Valorizzazione della Filiera Agricola Locale

## 📌 Descrizione del Progetto

La piattaforma consente la **gestione, valorizzazione e tracciabilità dei prodotti agricoli locali**, includendo dati su produzione, trasformazione e vendita, con l'obiettivo di promuovere il territorio e facilitare la trasparenza della filiera.

## 🚀 Tecnologie Utilizzate

* Java 21
* Spring Boot 3
* Maven
* H2 Database
* Spring Security
* OpenStreetMap (OSM)

## 🛠️ Design Pattern Utilizzati

* Factory Pattern
* MVC Pattern (Model-View-Controller)
* DTO Pattern

## ⚙️ Setup del Progetto

```bash
git clone <repository_url>
cd <nome_cartella_progetto>
mvn clean install
mvn spring-boot:run
```

Accesso al database H2:

* URL: `http://localhost:8080/h2-console`
* JDBC URL: `jdbc:h2:mem:testdb`
* User: `sa`
* Password: (vuoto)

## 🛣️ Endpoint Principali

* `/api/auth/` - Registrazione e login attori
* `/api/prodotti/` - CRUD prodotti e pacchetti
* `/api/utenti/` - Gestione utenti
* `/api/osm/` - Visualizzazione mappa e inserimento punti
* `/api/eventi/` - Gestione eventi e fiere

## 👥 Attori previsti

* Produttore, Trasformatore, Distributore, Curatore, Animatore, Acquirente, Gestore Piattaforma

## 🔮 Possibili Estensioni Future

* Sistema notifiche eventi e approvazioni
* Sistema recensioni prodotti e aziende
* Dashboard grafica marketplace
* Integrazione sistemi di pagamento

## 📚 Crediti

Progetto sviluppato per il corso di **Ingegneria del Software (IDS)** - Università degli Studi di Camerino.

**Team:**

* Elia Cavalieri
* Riccardo Ciccarelli
* Tommaso Cosci

---

Per qualsiasi dubbio o demo live, il progetto è testabile via **Postman e console H2** per illustrare tutte le funzionalità richieste dalla traccia.
