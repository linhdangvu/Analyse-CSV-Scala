
Projet d'analyser les données un fichier CSV donnée en Scala
Groupe AK: NGUYEN Van Phong et DANG Vu My Linh
Lien video: https://www.youtube.com/watch?v=EecYwV_wH2Y

A. Pour compiler, il y a 2 moyennes d'écrire sur Terminale:

	- Moyenne 1:
		$ scalac CsvProcessor.scala
		$ scala CsvProcessor

	- Moyenne 2:
		$ scala CsvProcessor.scala

Après cela, nous pouvons écrire le nom de fichier que nous voudrons analyser.

Maintenant, il y a un Menu qu'il affiche:

1. Load FILE :
	- Pour télécharger un nouveau fichier par écrire le nom de fichier que nous voudrons analyser.

2. Number of Columns :
	- Nombre total de colonnes

3. Total Number of Records :
	- Nombre total d'instances

4. Column Summary:
	- Afficher le récapitulatif de chaque colonne.

	- Si colonne de type numérique :
		- Il va afficher Somme(Total), Moyenne(Average), Min, Max.

	- Si colonne de type chaîne :
		- Il va afficher des éléments dans ce colonne avec la value de probabilité.

5. All Columns Summary :
	- Afficher le résumé de toutes les colonnes.

6. Fill Missing values :
	- Cela remplacera la valeur nulle en fonction de la logique suivante.

		- Si la colonne est numérique :
			- Remplacer la cellule NULL par la valeur moyenne.

		- Si la colonne est String : 
			- Remplacer NULL par l'élément de probabilité la plus élevée.

x. Exit :
	- Pour quitter ce program.

B. Des fonctions :

1. def loadFile : Unit = {}
	- Cette méthode charge le fichier dans le tableau ci-dessous, son tableau à 2 dimensions.

	var content = Array.ofDim[Any](100,50000)
		- donc le tableau ci-dessus contient des tableaux pour chaque colonne.
		- il charge également les étiquettes de colonne dans la carte ci-dessous.
		
		var map: HashMap[Int, String] = HashMap()
			- il contient l'index de la colonne et le nom de l'en-tête de la colonne.

2. def printMenu : Unit = {}
	- Il affiche le menu

3. def process( col : Int, max : Int ) : Unit = {}
	- Cela prend l'index de la colonne et le nombre d'enregistrements comme entrée, et il détecte le type de colonne, que ce soit une chaîne ou numérique.
	- Il compte également le nombre de valeurs nulles.
	- Nous utilisons une boucle for, il parcourt toutes les données et essaie de convertir une valeur donnée et si tout est converti en double, considérez la colonne comme numérique sinon de type chaîne
	- Il est utilisé pour afficher le résumé (aide à l'option 5 dans le menu)

4. def printAll : Unit = {}
	- Cette méthode utilise la méthode ci-dessus (processus) pour imprimer le résumé de toutes les colonnes.
	- Il utilise var map: HashMap [Int, String] = HashMap () et l'itère tout en appelant la méthode process.
	- L'option 5 du menu appelle cette méthode

5. def nomSummary( col : Int, max : Int ) : Unit = {}
	- Cette méthode permet de dériver des informations supplémentaires sur les colonnes de type chaîne, ces informations supplémentaires sont utilisées lors de l'appel de l'option de menu 4.
	- Il parcourt la colonne sélectionnée et insère des données dans la carte, cette carte stocke le nombre d'occurrences de chaque mot. donc finalement il renvoie la probabilité de chaque mot.

6. def numSummary( col : Int, max : Int ) : Unit = {}
	- Cette méthode permet de dériver des informations supplémentaires sur les colonnes de type numérique, ces informations supplémentaires sont utilisées lors de l'appel de l'option de menu 4.
	- Il imprime les valeurs totales, moyennes, min et max de la colonne numérique sélectionnée.

7. def processCol( col : Int, max : Int ) : Unit = {}
	- cette méthode est appelée par l'option 4.
	- Elle est identique à la méthode de processus décrite, mais elle fournit des informations supplémentaires en utilisant la méthode nomSummary lorsque le type est string et numSummary dans le cas de numerical.

8. def numFill( col : Int, max : Int ) : Int = {}
	- Cette méthode est utilisée pour obtenir la valeur moyenne d'une colonne numérique et elle est utilisée pour remplacer les valeurs nulles d'une colonne numérique.

9. def nomFill( col : Int, max : Int ) : String = {}
	- Cette méthode est utilisée pour obtenir la chaîne (mot) la plus courante de la colonne et elle est utilisée pour remplacer les valeurs nulles d'une colonne chaîne.

10. def fillNumCol( col : Int, max : Int ) : Unit = {}
	- Cette méthode remplace les valeurs nulles de la colonne numérique, obtenez de l'aide de la méthode numFill pour obtenir la valeur moyenne.

11. def fillNomCol( col : Int, max : Int ) : Unit = {}
	- Cette méthode remplace les valeurs nulles de la colonne chaîne, obtenez de l'aide de la méthode nomFill pour obtenir la valeur moyenne.

12. def fill( col : Int, max : Int ) : Unit = {}
	- Cette méthode est appelée par l'option 6. En fonction du type de colonne, elle appelle la méthode fillNumCol ou fillNomCol.





















