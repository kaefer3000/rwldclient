# rwldclient

## Prerequisite:
Have [NxParser](https://github.com/nxparser/nxparser) version 3.0.0-SNAPSHOT installed. If not, quickly:
```bash
cd /tmp/
git clone https://github.com/nxparser/nxparser
cd nxparser
mvn clean install -Dmaven.test.skip=true
```

## Build:
```bash
mvn package
```

## Run:
```bash
java -jar target/rwldclient-jar-with-dependencies.jar
```
