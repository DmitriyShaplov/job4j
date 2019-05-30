package ru.job4j.tasks.scripts;

import java.nio.Buffer;
import java.util.*;

/**
 * @author shaplov
 * @since 30.05.2019
 */
public class ScriptPlan {

    public List<VulnerabilityScript> plan(List<VulnerabilityScript> list) {
        Map<Integer, VulnerabilityScript> map = new HashMap<>();
        list.forEach(v -> map.put(v.getScriptId(), v));
        Queue<VulnerabilityScript> data = new LinkedList<>(list);
        Deque<VulnerabilityScript> buffer = new LinkedList<>();
        while (!data.isEmpty()) {
            VulnerabilityScript script = data.poll();
            buffer.offerFirst(script);
            for (Integer num : script.getDependencies()) {
                data.offer(map.get(num));
            }
        }
        Set<VulnerabilityScript> result = new LinkedHashSet<>(buffer);
        return new ArrayList<>(result);
    }

    public List<VulnerabilityScript> plan2(List<VulnerabilityScript> list) {
        Map<Integer, VulnerabilityScript> map = new HashMap<>();
        list.forEach(v -> map.put(v.getScriptId(), v));
        LinkedHashSet<VulnerabilityScript> result = new LinkedHashSet<>();
        list.forEach( v -> {
            Queue<VulnerabilityScript> data = new LinkedList<>();
            Deque<VulnerabilityScript> buffer = new LinkedList<>();
            data.offer(v);
            while (!data.isEmpty()) {
                VulnerabilityScript script = data.poll();
                if (result.contains(script)) {
                    continue;
                }
                buffer.offerFirst(script);
                for (Integer num : script.getDependencies()) {
                    data.offer(map.get(num));
                }
            }
            result.addAll(buffer);
        });
        return new ArrayList<>(result);
    }
}

class VulnerabilityScript {

    private final int scriptId;
    private final List<Integer> dependencies;

    public VulnerabilityScript(int scriptId, List<Integer> dependencies) {
        this.scriptId = scriptId;
        this.dependencies = dependencies;
    }

    public int getScriptId() {
        return scriptId;
    }

    public List<Integer> getDependencies() {
        return dependencies;
    }

    @Override
    public String toString() {
        return "Id=" + scriptId;
    }
}