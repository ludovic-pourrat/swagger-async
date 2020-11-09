package io.swagger.v2.aas.integration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import io.swagger.v2.aas.integration.api.AsyncAPIConfiguration;
import io.swagger.v2.aas.integration.api.AsyncApiScanner;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericAsyncApiScanner implements AsyncApiScanner {

    static final Set<String> ignored = new HashSet<>();

    static {
        ignored.addAll(IgnoredPackages.ignored);
    }

    private static Logger LOGGER = LoggerFactory.getLogger(GenericAsyncApiScanner.class);

    AsyncAPIConfiguration asyncApiConfiguration;

    @Override
    public void setConfiguration(AsyncAPIConfiguration asyncApiConfiguration) {
        this.asyncApiConfiguration = asyncApiConfiguration;
    }

    @Override
    public Set<Class<?>> classes() {
        ClassGraph graph = new ClassGraph().enableAllInfo();

        Set<String> acceptablePackages = new HashSet<>();

        Set<Class<?>> output = new HashSet<>();

        boolean allowAllPackages = false;

        // if classes are passed, use them
        if (asyncApiConfiguration.getResourceClasses() != null && !asyncApiConfiguration.getResourceClasses().isEmpty()) {
            for (String className : asyncApiConfiguration.getResourceClasses()) {
                if (!isIgnored(className)) {
                    try {
                        output.add(Class.forName(className));
                    } catch (ClassNotFoundException e) {
                        LOGGER.warn("error loading class from resourceClasses: " + e.getMessage(), e);
                    }
                }
            }
            return output;
        }

        if (asyncApiConfiguration.getResourcePackages() != null && !asyncApiConfiguration.getResourcePackages().isEmpty()) {
            for (String pkg : asyncApiConfiguration.getResourcePackages()) {
                if (!isIgnored(pkg)) {
                    acceptablePackages.add(pkg);
                    graph.whitelistPackages(pkg);
                }
            }
        } else {
            allowAllPackages = true;
        }

        // this is generic, specific Jaxrs scanner will also look for @Path
        final Set<Class<?>> classes;
        try (ScanResult scanResult = graph.scan()) {
            classes = new HashSet<>(scanResult.getClassesWithAnnotation(AsyncAPIConfiguration.class.getName()).loadClasses());
        }


        for (Class<?> cls : classes) {
            if (allowAllPackages) {
                output.add(cls);
            } else {
                for (String pkg : acceptablePackages) {
                    if (cls.getPackage().getName().startsWith(pkg)) {
                        output.add(cls);
                    }
                }
            }
        }

        return output;
    }

    @Override
    public Map<String, Object> resources() {
        return new HashMap<>();
    }

    protected boolean isIgnored(String classOrPackageName) {
        if (StringUtils.isBlank(classOrPackageName)) {
            return true;
        }
        return ignored.stream().anyMatch(classOrPackageName::startsWith);
    }

}
