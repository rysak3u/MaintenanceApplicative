# BUT 3A : Maintenance Applicative

Dans ce cours, vous apprendrez à détecter les code smells et à effectuer un refactoring approprié en utilisant de bons design patterns.
Vous verrez également pourquoi il est crucial d'effectuer des tests intensifs **avant** d'entamer le refactoring.
Pour cela, nous travaillerons avec des kata de refactoring célèbres, rassemblés dans ce dépôt.

## Resources

### Design pattern

[Refactoring guru](https://refactoring.guru/design-patterns) propose une excellente liste de *design patterns* avec des pseudo-codes, des exemples d'utilisation, ainsi que leurs avantages et inconvénients.  
C'est une très bonne ressource pour débuter !

Si vous souhaitez aller plus loin, il existe la bible des *design patterns* : [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.oreilly.com/library/view/design-patterns-elements/0201633612/) écrit par le célèbre **Gang of Four** (Erich Gamma, Richard Helm, Ralph Johnson et John Vlissides).  
Un autre excellent livre, plus accessible pour les débutants, est : [Head First - Design Patterns](https://www.oreilly.com/library/view/design-patterns-elements/0201633612/).

### Code Smells

[Luzkan's catalog](https://luzkan.github.io/smells/) est une très bonne ressource pour apprendre à reconnaître les code smells.  
[Refactoring guru](https://refactoring.guru/) propose aussi une liste détaillée de [code smells](https://refactoring.guru/refactoring/smells) avec leurs explications et méthodes de traitement.

Si vous souhaitez approfondir, je vous recommande vivement la lecture du livre suivant : [Refactoring: Improving the Design of Existing Code](https://martinfowler.com/books/refactoring.html) de Martin Fowler.


### Kata \#1 GildedRose - par Terry Hughes et adapté par [Emily Bache](https://github.com/emilybache)

Dans ce Kata, vous allez:  
✅ Comprendre l'importance des **tests unitaires** pour le *refactoring*  
✅ Découvrir les [**ApprovalTests**](https://github.com/approvals/ApprovalTests.Java)  
✅ Rédiger des tests unitaire avec une **couverture de code de 100%**  
✅ Utiliser la méthode **mutation testing** pour garantir l'exhaustivité des tests unitaires  
✅ Appliquer la méthode de refactoring "**lift up conditional**"  
✅ Utiliser le **polymorphisme** pour un *refactoring* encore plus efficace  