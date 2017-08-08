package cn.nukkit.form.element;

import java.util.ArrayList;

public class ElementStepSlider extends Element {

    public String type = "step_slider";
    public String text = "";
    public ArrayList<String> steps;
    public int defaultStepIndex = 0;

    public ElementStepSlider(String text){
        this (text, new ArrayList<>());
    }
    public ElementStepSlider(String text, ArrayList<String> steps){
        this (text, steps, 0);
    }
    public ElementStepSlider(String text, ArrayList<String> steps, int defaultStep){
        this.text = text;
        this.steps = steps;
        this.defaultStepIndex = defaultStep;
    }

    public void setDefaultOptionIndex(int index){
        if (index >= steps.size()) return;
        this.defaultStepIndex = index;
    }
    public void addStep(String step){
        addStep(step, false);
    }
    public void addStep(String step, boolean isDefault){
        steps.add(step);
        if (isDefault) this.defaultStepIndex = steps.size()-1;
    }

}
