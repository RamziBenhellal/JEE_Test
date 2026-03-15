# JEE Student & Course Management System

Ein robustes Web-Informationssystem zur Verwaltung von Studenten,
Lehrern, Kursmodulen und Noten. Die Anwendung wurde mit **Java
Enterprise Edition (JEE)** entwickelt und folgt dem
**MVC-Architekturmuster (Model-View-Controller)**, um Geschäftslogik und
Präsentationsschicht sauber zu trennen.

------------------------------------------------------------------------

## 🚀 Funktionen

-   **Benutzerverwaltung**\
    Sicheres Login- und Registrierungssystem für verschiedene
    Benutzerrollen.

-   **Studentenverwaltung**\
    Vollständige CRUD-Operationen (Create, Read, Update, Delete) für
    studentische Datensätze.

-   **Lehrermanagement**\
    Verwaltung von Lehrkräften und deren Zuordnung zu Klassen oder
    Modulen.

-   **Kurs- & Modulverwaltung**\
    Organisation von Studienmodulen und Klassenstrukturen.

-   **Notenerfassung**\
    System zur Dokumentation und Verwaltung akademischer Leistungen.

-   **Dashboard-Ansichten**\
    Spezifische Benutzeroberflächen für Lehrer (z. B. „Meine Studenten")
    und Administratoren.

------------------------------------------------------------------------

## 🛠️ Technologien

  -----------------------------------------------------------------------
  Kategorie                           Technologie
  ----------------------------------- -----------------------------------
  **Backend**                         Java (JEE) mit Servlets

  **Frontend**                        JSP (JavaServer Pages), JSTL,
                                      HTML5, CSS3 (Bootstrap 4)

  **Datenbank**                       MySQL / MariaDB

  **Architektur**                     MVC Pattern

  **Design Pattern**                  DAO (Data Access Object)

  **Server**                          Apache Tomcat 8.5+ oder kompatible
                                      JEE-Container
  -----------------------------------------------------------------------

------------------------------------------------------------------------

## 📂 Projektstruktur

    JEE_Test/
    ├── src/
    │   ├── beans/          # Datenmodelle (Student, Teacher, Module, etc.)
    │   ├── dao/            # Data Access Objects & Datenbanklogik
    │   ├── forms/          # Validierungslogik für Formulare
    │   ├── servlets/       # Controller zur Steuerung der Anwendungslogik
    │   └── database/       # Datenbankverbindungskonfiguration
    ├── WebContent/
    │   ├── WEB-INF/        # Konfigurationsdateien & JSP-Views
    │   ├── css/            # Stylesheets (Bootstrap)
    │   └── js/             # JavaScript-Bibliotheken
    └── database_script.sql # SQL-Skript zur Datenbankinitialisierung

------------------------------------------------------------------------

## ⚙️ Installation & Einrichtung

### 1. Voraussetzungen

-   Java JDK 8 oder höher
-   Apache Tomcat (v8.5 oder v9.0 empfohlen)
-   MySQL Server
-   Eine IDE wie Eclipse for Enterprise Java oder IntelliJ IDEA

------------------------------------------------------------------------

### 2. Datenbank vorbereiten

Importiere die bereitgestellte SQL-Datei in deinen MySQL-Server:

``` sql
SOURCE path/to/database_script.sql;
```

------------------------------------------------------------------------

### 3. Datenbankverbindung konfigurieren

Passe die Verbindungsdaten in folgender Datei an:

    src/dao/DaoFactory.java

Dort musst du konfigurieren:

-   Datenbank-URL (z. B. `jdbc:mysql://localhost:3306/deine_db`)
-   Benutzername
-   Passwort

------------------------------------------------------------------------

### 4. Deployment

1.  Importiere das Projekt in deine IDE als:

    -   **Existing Maven Project** oder
    -   **Dynamic Web Project**

2.  Füge einen **Apache Tomcat Server** hinzu.

3.  Ordne das Projekt dem Server zu.

4.  Starte den Server und öffne im Browser:

```{=html}
<!-- -->
```
    http://localhost:8080/test/

------------------------------------------------------------------------

## 🔐 Sicherheit & Validierung

Die Anwendung verwendet dedizierte **Form-Klassen** (z. B.
`StudentForms.java`), um:

-   Benutzereingaben serverseitig zu validieren
-   Validierungsfehler strukturiert zu sammeln
-   Fehlermeldungen an die JSP-Seiten zurückzugeben

Dies sorgt für eine klare Trennung zwischen **Validierungslogik**,
**Controller** und **View**.

------------------------------------------------------------------------

## 📌 Zweck des Projekts

Dieses Projekt wurde als **Test- und Lernumgebung für
JEE-Entwicklungspraktiken** erstellt und demonstriert typische
Architekturprinzipien wie:

-   MVC Pattern
-   DAO Pattern
-   Servlet-basierte Controller
-   JSP-basierte Views
-   Serverseitige Formularvalidierung

------------------------------------------------------------------------

## 📄 Lizenz

Dieses Projekt dient ausschließlich zu **Lern- und
Demonstrationszwecken**.
