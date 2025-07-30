Auteur: RAKOTONIRINA Daniella Nandrianina Natacha

API REST en Java (sans framework) pour gérer une file d’attente de tickets (FIFO).

-Dépôt GitHub : [masytana/Java-API-Exam](https://github.com/masytana/Java-API-Exam.git)  
-En ligne via Render : [https://daniella-java-api-exam.onrender.com](https://daniella-java-api-exam.onrender.com)

## Recompiler et redémarrer le serveur
Taper 'r' dans le terminal puis Entrer.

## Lancer le projet en local: dans bash
javac src/*.java -d .
java Main
Ensuite le Serveur accessible sur : http://127.0.0.1:8000

## Démarrer en local : dans bash
./build.sh


## Tester l’API avec `curl`
1-Ajouter un ticket : dans bash
curl -X POST http://localhost:8000/tickets

2-Retirer un ticket (dequeue) : dans bash
curl -X GET http://localhost:8000/tickets

3-Voir le ticket en tête (peek) : dans bash
curl -X GET http://localhost:8000/tickets/peek

4-Vérifier si la file est vide : dans bash
curl -X GET http://localhost:8000/tickets/empty

5-Connaître la taille de la file : dans bash
curl -X GET http://localhost:8000/tickets/size

## Structure du projet
Java-API-Exam/
├── .github/
│   └── workflows/
│       └── java-ci.yml          # Pipeline GitHub Actions
├── index.html                   # Page d'accueil simple
├── Dockerfile                   # Déploiement Render
├── .gitignore
├── build.sh
├── README.md
└── src/ticket/
    ├── HttpResponseHandler.java
    ├── Main.java
    ├── Ticket.java
    ├── TicketServive.java
    └── TicketController.java

NB: Je me suis permise de personnaliser le nom des fichiers ".java" pour que ce soit plus explicite.

