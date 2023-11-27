package edu.sjsu.cs;

import org.graalvm.visualvm.core.VisualVM;
import org.netbeans.lib.profiler.heap.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    private final Heap heap;

    private static final String THREADS_FQDN = "java.lang.Thread";




    public Main(String heapDumpFilePath) throws IOException {
        heap = HeapFactory.createHeap(new File(heapDumpFilePath));
    }

    private void play() {

    }

    public void printThreads() {
        JavaClass threadsClass = heap.getJavaClassByName(THREADS_FQDN);
        List<Instance> instances = threadsClass.getInstances();
        for (Instance instance : instances) {
            System.out.println(instance.getJavaClass().getName());
            List<Object> fieldValues = instance.getFieldValues();
            for (Object i : fieldValues) {
                System.out.println(i.getClass().getName());
            }

//            Instance nameField = (Instance)instance.getValueOfField("name");
//            System.out.println(getValue(nameField));
        }
    }

    private String getValue(Instance instance) {
        System.out.println(instance);
        if (!(instance.getValueOfField("value") instanceof PrimitiveArrayInstance)) {
            System.out.println(instance + " h");
            return instance.getValueOfField("value").toString();
        }

        PrimitiveArrayInstance pai = (PrimitiveArrayInstance) instance.getValueOfField("value");
        if (pai == null) {
            System.out.println(instance + " n");
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        for (Object obj : pai.getValues()) {
            if (obj instanceof Character) {
                sb.append((char) obj);
            } else if (obj instanceof Integer) {
                int code = Integer.parseInt((String) obj);
                sb.append((char) code);
            } else {
                sb.append(obj.toString());
            }
        }
        System.out.println(instance + " sb");
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        String dumpFile = "/Users/sumane/troubles/dumps/ltx1-app73282.prod.linkedin.com-venice-server-20220723-06-26-30-heapdump.out";
        Main main = new Main(dumpFile);
        main.printThreads();
    }
}
