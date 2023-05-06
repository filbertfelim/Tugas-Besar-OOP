JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		  Main.java \
		  BahanMakanan.java \
		  Buyable.java \
		  Edible.java \
		  Inventory.java \
		  Item.java \
		  Masakan.java \
		  Matriks.java \
		  MusicPlayer.java \
		  NonMakanan.java \
		  Pekerjaan.java \
		  Point.java \
		  Ruangan.java \
		  Rumah.java \
		  Sim.java \
		  SingleCommand.java \
		  SingleHTP.java \
		  World.java \
		  json-simple-1.1.1.jar \

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
	
run:
	java Main