package none.subhash.todo;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("none.subhash.todo");

        noClasses()
            .that()
            .resideInAnyPackage("none.subhash.todo.service..")
            .or()
            .resideInAnyPackage("none.subhash.todo.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..none.subhash.todo.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
