FROM azul/zulu-openjdk-alpine:18.0.2.1
WORKDIR /out/pm-system-sample
COPY . /target/pm-system.jar ./
WORKDIR /out/pm-system-sample/target
CMD java -jar pm-system.jar
#todo