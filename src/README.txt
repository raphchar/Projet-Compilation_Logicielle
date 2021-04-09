Pour lancer l'application : 
	- Côté client, il suffit de lancer src/GUI/ConnectionGUI.java
	- Côté serveur il faut exécuter src/servPattern/ServeurTCP.java

Le retour de fonctionnement se fait sur les commandes dnas l'IDE utilisé : 
	- Le fichier qui est à l'origine du message affiché s'indique entre []
		ex : [TraitementContext] attente client
	- Les messages côté serveur (resp. côté client) s'affiche dans la cmd de 
	  l'exécution de serveurTCP (resp. ConnectionGUI)
	
	- l'IHM évolue selon le bouton sur lequel on clique, à savoir que le 
	  bouton NouvelleConversation est à l'origine de bugs


L'IHM fonctionne de la telle manière : 
	- Au début, un client peut se connecter si son compte existe, ou créer son compte sinon
	- Une fois connecté, une liste des nom de conversations déjà existantes s'affiche,
	  l'utilisateur y à accès en cliquant dessus
	- L'utilisateur peut aussi choisir de créer une nouvelle conversation en cliquant sur
	  nouvelle Conversation, pour cela il doit indiquer : 
		-- le nom de la conversation
		-- la liste des nom d'utilisateurs qu'il souhaite contacter, séparés par des ";", sans " "
	- Une fois dans la conversation souhaité, les logs des messages s'affichent, et le client peut
	  envoyer un message en tapant son message et en cliquant sur le bouton avec la flèche.
	- pour visualiser les messages reçus, il faut actualiser la fenêtre, pour cela il est nécessaire de
	  revenir sur la liste des conversations (bouton retour) puis de cliquer à nouveau sur la conversation.
	- La déconnexion se fait en fermant la fenêtre.

Beaucoup de points seraient à améliorer, mais les erreurs de choix d'architecture en début de projet
nous à amener à cette impasse, où il est difficle de rajouter une fonctionnalité sans créer de nombreux bugs
ou sans avoir à coder 1000 lignes pour une petite fonctionnalité.