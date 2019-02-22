Mitä olen tehnyt tällä viikolla?
1. > Tutkinut lisää RPS algoritmejä, ja toteuttanut muutamia niistä
1. > MarkovChain AI ensimmäinen toteutus - tuntuu toimivan melko hyvin - tätä voi vielä tosin parannella lukemani mukaan.
1. > Luonut muutamat testit, joilla testataan pelin pelaaminen itseänsä vastaan random valinnoilla (n. 50% winrate itseään vastaan random syötteillä), ja ennalta valitulla patternilla (alle 30% winrate)
1. > Parannellut verkkosivun näkymää ja tuntumaa
1. > NovaAI algoritmi, yhdistelmä joitakin algoritmejä mitä löysin netistä - tuntuu toimivan melko hyvin, mutta paranneltavaakin on vielä.
1. > tekoälyn syötteen valintaan tehty muokkauksia, priorisoi nyt kovemmin tekoälyjen valintoja, joilla on parempi voittohistoria lähiajalla ja pitkällä ajalla
1. > Määrittelydokumentit parantelua

Miten ohjelma on edistynyt?
1. > Testaus melko hyvällä tasolla
1. > Pelaaminen tuntuu miellyttävämmältä
1. > Tekoälyjen valintoja paranneltu, nyt harvoin pääsee enää yli 50% winrate jos pelaa pitkään.
	
Mitä opin tällä viikolla / tänään?
1. > Muutamia eri tapoja miten ihmiset pyrkivät pelaamaan RPS pelejä, ja niitten perusteella oppinut mitä voisi mahdollisesti tekoälylläkin tehdä
1. > Gradle configurointia, esim miten saa Travisille asennettua eri ajureita testejä varten
	
Mikä jäi epäselväksi tai tuottanut vaikeuksia? Vastaa tähän kohtaan rehellisesti, koska saat tarvittaessa apua tämän kohdan perusteella.
1. >  Onko pakko tehdä kattavaa testausta ja kommentointia myös luokille, jotka ei suoraan vaikuta tekoälyyn itseensä, eli esim Controllers luokkaan, joka lähettää HTML dataa verkkosivulle renderöitäväksi?
1. >  Riittääkö Cucumber tyylinen testaus, vai onko pakko tehdä myös erikseen JUnit testejä, jos Cucumber Selenium testit käytännössä kuitenkin testaa eri luokkien toiminnallisuutta oikeassa pelitilanteessa?
2. >  Lasketaanko String tietorakenteeksi, vai onko String esiintymät laitettava char[] muotoon?
2. >  Ja saako esim käyttää valmiita metodeita, yms muita String tai Array tai Collections luokan alimetodeja?

Mitä teen seuraavaksi?
1. > Tekoälyjen logiikan parantelua, ja tweakkailua
2. > Lisää uusia algoritmejä, yksi kiinnostava mutta kohtuu vaikeasti toteutettava mistä luin oli Iocaine Powder niminen algoritmi, joka oikein toteutettuna on pelannut ihmisiä vastaan jopa 75% voittoprosentilla..


Käytetyt tunnit ~ 25h
