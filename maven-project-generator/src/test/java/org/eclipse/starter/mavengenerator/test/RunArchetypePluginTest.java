package org.eclipse.starter.mavengenerator.test;

import java.io.File;
import org.eclipse.starter.mavengenerator.test.commons.UnitTestCommons;
import java.nio.file.Path;
import org.eclipse.starter.mavengenerator.ArchetypeGenerateParameters;
import org.eclipse.starter.mavengenerator.CliMavenContext;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class RunArchetypePluginTest implements UnitTestCommons {

    @Test
    void canRunCliArchetypePluginFromParams(TestInfo testInfo) {
        String newArtifactName = getTestMethodName(testInfo);
        Path testDir = getTestDir(testInfo);

        new ArchetypeGenerateParameters()
                .archetypeGroupId("org.eclipse.starter")
                .archetypeArtifactId("jakartaee10-minimal")
                .archetypeVersion("1.1.0")
                .groupId("org.eclipse.starter.test")
                .artifactId(newArtifactName)
                .version("0.1-SNAPSHOT")
                .interactiveMode(false)
                .addGoal("org.apache.maven.plugins:maven-archetype-plugin:3.2.1:generate")
                .updateMavenContext(new CliMavenContext()
                        .workingDirectory(testDir)
                        .addOptions("-X")
                )
                .run();

        assertThat(new File(testDir.toFile(), newArtifactName + File.separator + "pom.xml"))
                .as("Generated pom.xml file")
                .exists();
    }
}
