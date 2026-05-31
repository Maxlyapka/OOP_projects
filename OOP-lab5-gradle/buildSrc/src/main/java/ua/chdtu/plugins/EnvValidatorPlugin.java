package ua.chdtu.plugins;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.GradleException;

public class EnvValidatorPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        EnvExtension ext = project.getExtensions().create("envRequirements", EnvExtension.class);

        project.getTasks().register("validateEnv", task -> {
            task.doLast(t -> {
                project.getLogger().lifecycle("=======================================");
                project.getLogger().lifecycle(" ПЕРЕВІРКА ІНФРАСТРУКТУРИ ");
                project.getLogger().lifecycle("=======================================");

                String currentOS = System.getProperty("os.name");
                if (!currentOS.contains(ext.requiredOS)) {
                    throw new GradleException("ЗБІРКА ЗУПИНЕНА: Потрібна ОС " + ext.requiredOS + ", знайдено " + currentOS);
                }
                project.getLogger().lifecycle(" [OK] Операційна система: " + currentOS);

                String currentJava = System.getProperty("java.version");
                if (!currentJava.startsWith(ext.minJavaVersion)) {
                    throw new GradleException("ЗБІРКА ЗУПИНЕНА: Потрібна Java " + ext.minJavaVersion + ", знайдено " + currentJava);
                }
                project.getLogger().lifecycle(" [OK] Версія Java: " + currentJava);

                long maxMemoryMb = Runtime.getRuntime().maxMemory() / (1024 * 1024);
                if (maxMemoryMb < ext.minMemoryMb) {
                    throw new GradleException("ЗБІРКА ЗУПИНЕНА: Недостатньо пам'яті! Мінімум " + ext.minMemoryMb + " MB");
                }
                project.getLogger().lifecycle(" [OK] Пам'ять: " + maxMemoryMb + " MB");
                project.getLogger().lifecycle("=======================================");
            });
        });

        project.getTasks().named("compileJava").configure(task -> {
            task.dependsOn("validateEnv");
        });
    }
}