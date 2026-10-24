insert into link (short_code, title, url, ue_id, canal_id, publication_date, active) values
                                                                                         (
                                                                                             '24f21415-5c73-49be-873e-6f4074b5a8c2',
                                                                                             'f(x+y) = f(x) + f(y) : exercice corrigé — Analyse réelle MSP 1 | Partie 1',
                                                                                             'https://youtu.be/Lo80LUJCpqk',
                                                                                             (select id from ue where name = 'Analyse Réelle 1'),
                                                                                             (select id from canal where name = 'Chaîne WhatsApp'),
                                                                                             current_timestamp,
                                                                                             true
                                                                                         ),
                                                                                         (
                                                                                             '4aa89939-d658-4c6a-8bf3-3456b0e2cbd3',
                                                                                             'Tables de vérité : exercice corrigé — Algèbre générale MSP 1 | Partie 1',
                                                                                             'https://youtu.be/RY7UffsqLc0',
                                                                                             (select id from ue where name = 'Algèbre Générale'),
                                                                                             (select id from canal where name = 'Chaîne WhatsApp'),
                                                                                             current_timestamp,
                                                                                             true
                                                                                         );