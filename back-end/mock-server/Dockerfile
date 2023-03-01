# Stage 1: Build the application
FROM adoptopenjdk/openjdk16:ubi as build

# Creates a folder and sets the current working directory inside the image
WORKDIR /app

# Copy the maven executable to the image
COPY mvnw .
COPY .mvn .mvn

# Copy the pom.xml file
COPY pom.xml .

# Build all dependencies in preparation to go offline.
# This is a separate step so the dependencies will be cached unless the pom.xml files has changed.
RUN ./mvnw dependency:go-offline -B

# Copy the project source
COPY src src

# Package the application (with option to skip tests)
RUN ./mvnw package
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# Stage 2: A minimal docker image with command to run the app
FROM adoptopenjdk/openjdk16:ubi

ARG DEPENDENCY=/app/target/dependency

# Copy project dependencies from the build stage
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

# Use the java class file that has the Main method an entry point for the application within the container to run
# You can use `CMD` here instead, but `ENTRYPOINT` emphasizes that this is the last action for the container to take
ENTRYPOINT ["java", "-cp", "app:app/lib/*", "com.team1159ers.Main"]