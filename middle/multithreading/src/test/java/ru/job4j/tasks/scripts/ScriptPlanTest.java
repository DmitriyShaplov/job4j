package ru.job4j.tasks.scripts;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ScriptPlanTest {

    @Test
    public void whenUsePlan() {
        ScriptPlan plan = new ScriptPlan();
        List<VulnerabilityScript> list = new ArrayList<>();
        list.add(new VulnerabilityScript(1, List.of(4, 5)));
        list.add(new VulnerabilityScript(2, List.of(1, 3)));
        list.add(new VulnerabilityScript(3, List.of(7)));
        list.add(new VulnerabilityScript(4, List.of(6)));
        list.add(new VulnerabilityScript(5, List.of()));
        list.add(new VulnerabilityScript(6, List.of()));
        list.add(new VulnerabilityScript(7, List.of()));
        list.add(new VulnerabilityScript(8, List.of()));
        var result = plan.plan(list).stream().map(VulnerabilityScript::getScriptId).collect(Collectors.toList());
        assertThat(result, is(List.of(6, 7, 5, 4, 3, 1, 8, 2)));
    }

    @Test
    public void whenUsePlan2() {
        ScriptPlan plan = new ScriptPlan();
        List<VulnerabilityScript> list = new ArrayList<>();
        list.add(new VulnerabilityScript(1, List.of(4, 5)));
        list.add(new VulnerabilityScript(2, List.of(1, 3)));
        list.add(new VulnerabilityScript(3, List.of(7)));
        list.add(new VulnerabilityScript(4, List.of(6)));
        list.add(new VulnerabilityScript(5, List.of()));
        list.add(new VulnerabilityScript(6, List.of()));
        list.add(new VulnerabilityScript(7, List.of()));
        list.add(new VulnerabilityScript(8, List.of()));
        var result = plan.plan2(list).stream().map(VulnerabilityScript::getScriptId).collect(Collectors.toList());
        assertThat(result, is(List.of(6, 5, 4, 1, 7, 3, 2, 8)));
    }
}