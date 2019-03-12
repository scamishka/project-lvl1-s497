.DEFAULT_GOAL := build-run

run:
	java -jar ./target/project-lvl1-s497-1.0-SNAPSHOT-jar-with-dependencies.jar

clean:
	rm -rf ./target

build-run: build run

build: 
	./mvnw clean package

update-libs:
	./mvnw versions:update-properties

update-plugins:
	./mvnw versions:display-plugin-updates

update:	update-plugins update-libs

