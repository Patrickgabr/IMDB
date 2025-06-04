# IMDb Java – Aplicație de tip bază de date pentru filme

Acest proiect reprezintă o aplicație Java care simulează o parte din funcționalitatea site-ului IMDb, incluzând gestiunea de utilizatori, filme, actori și evaluări. Aplicația procesează fișiere JSON de intrare și generează un răspuns bazat pe cererile primite.

## Structură generală

Aplicația este organizată în pachete Java și fișiere de configurare Gradle, urmând principiile programării orientate pe obiect. Sunt incluse și teste automate care verifică corectitudinea implementării.

## Funcționalități principale

- Citirea datelor din fișiere JSON:
  - `accounts.json`
  - `actors.json`
  - `production.json`
  - `requests.json`

- Tratarea cererilor de tip:
  - login, logout
  - add/remove movie/actor
  - create/delete user
  - rating & updates pentru filme
  - căutare și filtrare filme

- Scrierea rezultatelor într-un fișier de ieșire JSON

- Testare automată cu fișiere de intrare presetate

## Organizare fișiere

