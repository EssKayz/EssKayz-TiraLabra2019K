Mitä algoritmeja ja tietorakenteita toteutat työssäsi
> MarkovChain algoritmi, Queue, Erinäiset ihmisen psykologiaan perustuvat algoritmit
	
Mitä ongelmaa ratkaiset ja miksi valitsit kyseiset algoritmit/tietorakenteet
1. > KSP Tekoäly, verkkosivulla pelattava versio, joka tukee monen pelaajan sessioneita, ja kertoo mikä on tämän hetkinen paras voittoprosentti
2. > Valitsen algoritmini sen mukaan, millaista dataa saan ihmisiltä jotka pelaavat peliä, ja kertovat miten he saivat tekoälyn voitettua. Tämän tiedon perusteella sitten mietin, miten kyseisen strategian voisi päihittää.
3. > Tietorakenteet sen mukaan mitä erinäisten ideoiden toteutukset vaativat. 
	
Mitä syötteitä ohjelma saa ja miten näitä käytetään
> Ohjelma saa verkkosivun kautta nappien kautta käyttäjän valinnan, jotka kerrotaan tekoälyn moottorille joka kierroksen JÄLKEEN - mahdollistaen tekoälyn oppimisen viime kierrosten perusteella.
	
Tavoitteena olevat aika- ja tilavaativuudet (m.m. O-analyysit)
1. > Tavoitteena on, että tekoäly tekee oman siirtonsa 2.5s aikana pelaajan siirrosta. 
1. > Toisena tavoitteena on myös se, että mitä enemmän kierroksia pelaaja pelaa, sitä enemmän tekoälyn pitäisi oppia voittamaan kierroksia. 
	
Lähteet
1. > https://daniel.lawrence.lu/programming/rps/
2. > http://www.rpscontest.com/
3. > http://setosa.io/ev/markov-chains/
