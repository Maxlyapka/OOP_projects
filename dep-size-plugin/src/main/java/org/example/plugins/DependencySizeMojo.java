package org.example.plugins;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.project.MavenProject;
import java.io.File;

/**
 * @goal estimate
 * @phase verify
 * @requiresDependencyResolution test
 */
public class DependencySizeMojo extends AbstractMojo {

    /**
     * @parameter default-value="${project}"
     * @readonly
     * @required
     */
    private MavenProject project;

    public void execute() {
        getLog().info("=========================================================");
        getLog().info(" РОЗРАХУНОК ВАГИ ЗАЛЕЖНОСТЕЙ ");
        getLog().info("=========================================================");

        long totalSizeBytes = 0;
        if (project.getArtifacts() != null) {
            for (Artifact artifact : project.getArtifacts()) {
                File file = artifact.getFile();
                if (file != null && file.exists()) {
                    long size = file.length();
                    totalSizeBytes += size;
                    getLog().info(" -> " + artifact.getArtifactId() + ": " + (size / 1024) + " KB");
                }
            }
        }

        double totalSizeMb = totalSizeBytes / (1024.0 * 1024.0);
        getLog().info("---------------------------------------------------------");
        getLog().info(String.format(" ЗАГАЛЬНА ВАГА .JAR ФАЙЛІВ: %.2f MB", totalSizeMb));
        getLog().info("=========================================================");
    }
}