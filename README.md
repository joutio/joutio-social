# joutio-social (staging local)

Structure cible en 2 sous-modules Maven :

- `joutio-social-domain`
- `joutio-social-infrastructure`

Seul `joutio-social-domain` est importable inter-services.

Le code applicatif reste organise sous `com.joutio.social.application.*` dans le module infrastructure.

Le module regroupe désormais :

- les demandes d'amitié
- les amitiés
- les challenges entre joueurs

