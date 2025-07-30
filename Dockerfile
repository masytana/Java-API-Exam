FROM openjdk:17-slim
WORKDIR /app
COPY ./src ./src
COPY ./index.html ./index.html
RUN javac src/*.java -d .
EXPOSE 8000
CMD ["java", "Main"]
