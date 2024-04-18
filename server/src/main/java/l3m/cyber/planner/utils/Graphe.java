package l3m.cyber.planner.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Graphe implements Cloneable{


    // nombre de sommets
    private int nbSommets;

    // matrice d'adjacence du graphe remplie de 0 et de 1 symmétrique
    private int[][] adj;

    // matrice des poids des arêtes; NB : Une arête qui n'existe pas aura poids 0.0
    // poids = null si graphe sans pondération
    private Double[][] poidsA;

    // nom des sommets
    // voir le github pour + d'infos
    private ArrayList<Integer> nomSommets;


    /*différents constructeurs de Graphe, notamment Graphe(int n) qui crée un graphe avec n sommets,
    nommés 0 à n-1 et aucune arête;
     un constructeur qui crée un graphe (non-pondéré)
     avec la matrice d'adjacence et le nom des sommets,
     ou au contraire avec une matrice de poids
     et le nom des sommets mais sans matrice d'adjacence
      (il faut alors construire la matrice d'adjacence en fonction des poids
       : 0 = non-arête, poids strictement positif = arête).
     */

    public Graphe(int[][] adj,ArrayList<Integer> nomSommets) {
        this.adj = adj;
        this.nomSommets = nomSommets;
        this.nbSommets = nomSommets.size();
    }


    // crée un graphe avec une matrice de poids et le nom des sommets mais sans matrice d'adjacence
    public Graphe(Double[][] poidsA, ArrayList<Integer> nomSommets) {
        this.poidsA = poidsA;
        this.nbSommets = poidsA.length;
        this.nomSommets = nomSommets;
        this.adj = new int[nbSommets][nbSommets];
        for (int i = 0; i < nbSommets; i++) {
            for (int j = 0; j < nbSommets; j++) {
                if (poidsA[i][j] > 0) {
                    adj[i][j] = 1;
                }
                else {
                    adj[i][j] = 0;
                }
            }
        }

    }

    // crée un graphe non pondéré avec la matrice et le nom des sommets
    public Graphe(ArrayList<Integer> nomSommets) {
        this.nomSommets = nomSommets;
        this.nbSommets = nomSommets.size();
        this.adj = new int[nbSommets][nbSommets];
    }

    // crée un graphe avec n sommets, nommés 0 à n-1 et aucune arête
    public Graphe(int n) {
        this(Auxiliere.integerList(n));
    }

    // crée un graphe non pondéré
    public Graphe(Double[][] poidsA,int n) {
        this(poidsA,Auxiliere.integerList(n));

    }

    public void pondereAretest() {
        // on transforme un graphe non-pondéré (poidsA==null)en un graphe pondéré, avec poinds 1 par défaut sur toutes les aretes axistantes
        if (poidsA == null) {
            poidsA = new Double[nbSommets][nbSommets];
        }
        for (int i = 0; i < nbSommets; i++) {
            for (int j = 0; j < nbSommets; j++) {
                if (adj[i][j] == 1) {
                    poidsA[i][j] = 1.0;
                } else {
                    poidsA[i][j] = 0.0;
                }
            }
        }
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < nbSommets; i++) {
            res += "Sommet " + nomSommets.get(i) + " : ";
            for (int j = 0; j < nbSommets; j++) {
                res += adj[i][j] + " ";
            }
            res += "\n";
        }
        return res;
    }

    // ajouter une arete entre les sommets i et j
    public void ajouterArete(int i, int j) {
        adj[i][j] = 1;
        adj[j][i] = 1;
    }

    // ajouter une arete entre les sommets i et j avec un poids
    public void ajouterArete(int i, int j, double poids) {
        ajouterArete(i,j);
        poidsA[i][j] = poids;
        poidsA[j][i] = poids;
    }

    //ajuster poids à une arete entre les sommets i et j ??? à revoir !!!
    public void ajusterPoids(int i, int j) {
        poidsA[i][j] = 1.0;
        poidsA[j][i] = 1.0;
    }

    // supprimer une arete entre les sommets i et j
    public void retirerArete(int i, int j) {
        adj[i][j] = 0;
        adj[j][i] = 0;
        poidsA[i][j] = 0.0;
        poidsA[j][i] = 0.0;
    }

    public boolean voisins (int i, int j) {
        return adj[i][j] == 1;
    }

    //d'une méthode estConnexe, qui s'obtient facilement à partir d'un parcours classique de graphe (en largeur ou en profondeur).
    public boolean estConnexe() {
        boolean[] visite = new boolean[nbSommets];
        for (int i = 0; i < nbSommets; i++) {
            visite[i] = false;
        }
        visite[0] = true;
        ArrayList<Integer> file = new ArrayList<Integer>();
        file.add(0);
        while (!file.isEmpty()) {
            int sommet = file.removeFirst();
            for (int i = 0; i < nbSommets; i++) {
                if (adj[sommet][i] == 1 && !visite[i]) {
                    visite[i] = true;
                    file.add(i);
                }
            }
        }
        int i = file.size();
        while (i < nbSommets && visite[i]) {
            i++;
        }
        return i == nbSommets;
    }

    // List<Triplet> listeAretes renvoie la liste des arêtes du graphe,
    // sous forme de triplets (i, j, poids) où i et j sont les sommets de l'arête et poids est le poids de l'arête.
    public List<Triplet> listeAretes() {
        List<Triplet> aretes = new LinkedList<Triplet>();
        for (int i = 0; i < nbSommets; i++) {
            for (int j = i + 1; j < nbSommets; j++) {
                if (adj[i][j] == 1) {
                    aretes.add(new Triplet(i, j, poidsA[i][j]));
                }
            }
        }
        return aretes;
    }


    /*
    aretesTriees(boolean croissant) qui renvoie
    la liste des arêtes du graphe sous la forme d'une List<Triplet>
     dans laquelle les arêtes seront triées par ordre croissant de poids
     (si croissant est vrai),
     et par ordre décroissant de poids sinon.
     Vous pouvez utiliser les méthodes Collections.sort et/ou
     Collections.reverseOrder() après en avoir consulté
     la documentation sur javadoc. */
    public List<Triplet> aretesTriees(boolean croissant) {
        List<Triplet> aretes = listeAretes();
        if (croissant) {
            Collections.sort(aretes);
        } else {
            Collections.sort(aretes, Collections.reverseOrder());
        }
        return aretes;
    }

    /* On commence par calculer T un arbre couvrant de poids minimum du graphe. Remarquons que le poids de cet arbre est plus petit que la longueur du plus petit cycle hamiltonien, car on peut obtenir un arbre couvrant T' en partant d'un cycle hamiltonien H et en enlevant une arête (n'importe laquelle). Le poids de T' est plus petit que celui de H, mais nécéssairement plus grand (ou égal) à celui de T, par minimisation de T.
Ensuite, on double toutes les arêtes de T, de façon à ce que le graphe contenant uniquement les arêtes de T en double soit un multigraphe eulérien : tous les degrés des sommes sont pairs, puisqu'on vient de doubler toutes les arêtes de T (donc tous les degrés); il existe donc C un cycle eulérien utilisant chaque arête de T exactement 2 fois.
Pour transformer le cycle eulérien C en cycle hamiltonien H, on prend des raccourcis : par exemple si C = 0, 1, 4, 6, 4, 2, 4, 1, 5, 3, 5, 1, 0, alors on ne garde que la première apparition de chaque sommet : H = 0, 1, 4, 6, 2, 5, 3. Cela revient à dire que, après avoir parcouru 0, 1, 4, 6, plutôt que de repasser par 4 que l'on a déjà vu, on va directement à 2; et ainsi de suite, on va directement au prochain sommet que l'on n'a pas encore visité. On a la garantie que H est bien un cycle dans le graphe car ce dernier est complet : il y a donc une arête de 6 à 2 sans repasser par 4; de plus, le poids du cycle H n'est pas plus grand que celui du cycle C car la pondération des arêtes vérifie l'inégalité triangulaire : me rendre directement du sommet 6 au sommet 2 sans passer par 4 ne rallonge pas le chemin par rapport au trajet 6-> 4 -> 2.
On obtient ainsi un cycle hamiltonien de poids au plus deux fois celui de T, et donc au plus deux fois le poids du cycle hamiltonien optimal.
Il se trouve que les étapes 2. et 3. peuvent être remplacées de manière équivalente par un simple parcours en profondeur de l'arbre.*/


    // ArrayList<Integer> parcoursProfondeur(int debut) qui renvoie la liste des sommets visités lors d'un parcours en profondeur du graphe en partant du sommet debut.
    public ArrayList<Integer> parcoursProfondeur(int debut) {
        boolean[] visite = new boolean[nbSommets];
        for (int i = 0; i < nbSommets; i++) {
            visite[i] = false;
        }
        ArrayList<Integer> res = new ArrayList<Integer>(); // liste des sommets visités
        parcoursProfondeur(debut, visite, res);
        return res;
    }

    // void parcoursProfondeur(int sommet, boolean[] visite, ArrayList<Integer> res) qui remplit res avec le parcours en profondeur du graphe en partant du sommet sommet,
//en utilisant le tableau visite pour marquer les sommets déjà visités.
    private void parcoursProfondeur(int sommet, boolean[] visite, ArrayList<Integer> res) {
        visite[sommet] = true;
        res.add(sommet);
        for (int i = 0; i < nbSommets; i++) {
            if (adj[sommet][i] == 1 && !visite[i]) {
                parcoursProfondeur(i, visite, res);
                res.add(sommet);
            }
        }
    }

    // Graphe KruskalInverse() qui renvoie un arbre couvrant de poids minimum du graphe,
//obtenu par l'algorithme de Kruskal, mais en retirant les arêtes qui ne sont pas dans l'arbre couvrant de poids minimum.
    public Graphe KruskalInverse() throws CloneNotSupportedException {
        Graphe T = (Graphe) this.clone(); // prendre une copie de notre graphe
        List<Triplet> aretes = T.aretesTriees(false);
        for (Triplet arete : aretes) {
            int i = arete.getC1();
            int j = arete.getC2();
            double poids = arete.getPoids();
            T.retirerArete(i, j);
            if (!T.estConnexe()) {
                T.ajouterArete(i, j, poids);
            }
        }
        return T;
    }

    /*une méthode ArrayList<Integer> tsp(int debut), qui calcule et renvoie un cycle hamiltonien du graphe courant (liste de tous les sommets, dans un certain ordre, commençant par debut). */
    public ArrayList<Integer> tsp(int debut) throws CloneNotSupportedException {
        Graphe T = KruskalInverse();
        ArrayList<Integer> parcours = T.parcoursProfondeur(debut);
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < parcours.size(); i++) {
            if (!res.contains(parcours.get(i))) {
                res.add(parcours.get(i));
            }
        }
        return res;
    }



}
