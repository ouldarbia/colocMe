package com.apitich.colocme;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.apitich.colocme");

        noClasses()
            .that()
                .resideInAnyPackage("com.apitich.colocme.service..")
            .or()
                .resideInAnyPackage("com.apitich.colocme.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.apitich.colocme.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
