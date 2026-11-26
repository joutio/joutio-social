package com.joutio.social.domain.port.out;

/**
 * Port sortant - version des compteurs d'abonnements surveillés par les caches du BFF : un compteur par joueur,
 * incrémenté atomiquement à chaque écriture, donc monotone même si plusieurs groupes ou répliques écrivent pour le même
 * joueur.
 */
public interface LiveVersionPort {

    /** Incrémente la version du joueur et renvoie la nouvelle valeur (1 à la première écriture). */
    long increment(String userId);
}
