.DEFAULT_GOAL := build-run
#compile: clean
#	mkdir -p ./target/classes
#	javac -d ./target/classes ./src/main/java/games/Slot.java

run:
	java -jar ./target/project-lvl1-s497-1.0-SNAPSHOT-jar-with-dependencies.jar

clean:
	rm -rf ./target

build-run: build run

build: 	clean
#	jar cfe ./target/casino.jar games.Slot -C ./target/classes .
	./mvnw package

update-libs:
	./mvnw versions:update-properties

update-plugins:
	./mvnw versions:display-plugin-updates

update:
	update-libs update-plugins



