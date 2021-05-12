package utils;

/**
 * Created by schinnag on 5/12/2021.
 */

import cucumber.api.junit.Cucumber;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import utils.CustomHooks.AfterSuite;
import utils.CustomHooks.BeforeSuite;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class CucumberCustomRunner extends Runner {
    private Class<Cucumber> classValue;
    private Cucumber cucumber;

    public CucumberCustomRunner(Class<Cucumber> classValue) throws Exception {
        this.classValue = classValue;
        this.cucumber = new Cucumber(classValue);
    }

    public Description getDescription() {
        return this.cucumber.getDescription();
    }

    private void runAnnotatedMethods(Class<?> annotation) throws Exception {
        if(annotation.isAnnotation()) {
            Method[] methods = this.classValue.getMethods();
            Method[] var3 = methods;
            int var4 = methods.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Method method = var3[var5];
                Annotation[] annotations = method.getAnnotations();
                Annotation[] var8 = annotations;
                int var9 = annotations.length;

                for(int var10 = 0; var10 < var9; ++var10) {
                    Annotation item = var8[var10];
                    if(item.annotationType().equals(annotation)) {
                        method.invoke((Object)null, new Object[0]);
                        break;
                    }
                }
            }
        }
    }

    public void run(RunNotifier notifier) {
        try {
            this.runAnnotatedMethods(BeforeSuite.class);
            this.cucumber = new Cucumber(this.classValue);
        } catch (Exception var3) {
            //logger.debug("An exception has occurred - " + var3);
        }

        this.cucumber.run(notifier);

        try {
            this.runAnnotatedMethods(AfterSuite.class);
        } catch (Exception var3) {
            //logger.debug("An exception has occurred - " + var3);
        }
    }
}