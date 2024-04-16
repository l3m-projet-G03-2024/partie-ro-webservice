**Thi Tran Van Anh and ABDRAMAN Abakar** 

## Rapport de compréhension du sujet 

Notre partie de projet consiste à optimiser les livraisons, c'est-à-dire diviser les livraisons à faire entre les différents livreurs et optimiser la tournée de chacun. 

Pour ça, on va implémenter cela sous forme d'un webservice : 

    * Prend en entrée : 
        - k (nombre de livreurs)
        - une matrice matrix de distance n*n supposée symétrique 
        - start le numéro du dépôt parmi les n lieux (les n-1 autres lieux) c'est le dépôt en quelque sorte 
    * Renvoies , un tableau contenant k listes, chacune d'elles correspondant à la tournée d'un livreur
        commançant au dépôt et indiquant dans l'ordre l'indice des lieux à livrer. Renvoies également un tableau longTournees de k double, 
        la case i contenant la longueur de la tournée du i-ème  livreur (y compris le retour au dépôt)


### Modélisation du problème : 

On va modéliser notre problème de livraison par un graphe. 
#### Etape 1 : Représentation du problème sous forme de graphe
- **Sommet du graphe** : Chaque lieu de livraison, ainsi que le dépôt, est représenté par un sommet dans le graphe. Si on a n lieux y compris le dépôt, alors notre graphe aura n sommets; 
- **Arête du graphe** : Chaque paire de sommets (lieux) u et v est reliée par une arête. Le poids de cette arête est la distance entre u et v
#### Etape 2 : Compréhension de la matrice des distances 
- La matrice de distance, appelée ici **matrix**, est une matrice n*n où matrix[u][v] représente la distance du lieu u au lieu v. matrix[u][v]=matrix[v][u] par symétrie ! 
- Le chemin direct entre u et v est toujours égal ou plus court que tout chemin par un 3ème point.
#### Etape 3 : Definition des tournées 
- **Tournée** : cycle élémentaire qui représente une tournée effectuée par un livreur 
- **Distribution des tournées** : Chaque sommet doit être visité par exactement un cycle. Cela signifie que chaque lieu est livré par un seul livreur, et chaque livreur part du dépôt et y revient à la fin de sa tournée. 
#### Etape 4 :  Application des algorithmes de graphe 
- Pour trouver les tournées optimales (les cycles élémentaires) qui minimisent les distances totales parcourues par tous les livreurs, on peut utiliser les algos spécifiques tels que **l'algorithme du voyage de commerce(TSP)** pour chaque livreur, adapté aux contraintes que chaque livreur doit partir et revenir au dépôt. 
- Etant donné que le problème est divisé en k tournées (k nb livreurs), il est essentiel de repartir equitablement les lieux de livraison entre les livreurs pour optimiser les routes globalement. 

## Le webservice planif 
On va utiliser le webservice planif ici 

## Partition : la repartition des livraisons entre livreurs 
La répartition des livraisons entre livreurs se fera grâce à une **Partition** (où on va définir une classe abstraite qui est donnée) 

et qui sera ensuite déclinée sous forme de :
#### Version 1 : **PartitionAlea** utilisera une stratégie aléatoire pour partitionner ; 
- elle n'est pas intelligente cette partition mais nous permettra d'avoir rapidement une implémentation de partition à articuler avec le reste de projet.
- On crée une classe ***PartitionAlea*** qui hérite de ***Partition*** et qui implémente la méthode *partitionne* 
##### Version 2 : **PartitionKCentre**  : se basera sur un algo de glouton de 2-approximation pour le problème k-centres. 
- pas optimale mais permettra d'obtenir de meilleur résultats que la partition aléatoire 
- basée sur le problème d'optimisation combinatoire appelé k-centres : 