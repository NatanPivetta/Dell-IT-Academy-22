plugins {
    id("java")
}

group = "natanpivetta.itacademy"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // JPA API
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")

    // Hibernate (implementação do JPA)
    implementation("org.hibernate.orm:hibernate-core:6.3.1.Final")

    // MySQL Driver
    implementation("com.mysql:mysql-connector-j:8.0.33")

}

tasks.test {
    useJUnitPlatform()
}