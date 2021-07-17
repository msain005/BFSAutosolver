JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        Node.java \
        BreadthFirstSearch.java \
        Puzzle.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
