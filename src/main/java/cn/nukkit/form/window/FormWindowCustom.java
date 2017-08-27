package cn.nukkit.form.window;

import cn.nukkit.form.element.*;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.response.FormResponseData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FormWindowCustom extends FormWindow {

    public String type = "custom_form";
    public String title = "";
    public ElementButtonImageData icon;
    public ArrayList<Element> content;

    private FormResponseCustom response;

    public FormWindowCustom(String title){
        this (title, new ArrayList<>());
    }
    public FormWindowCustom(String title, ArrayList<Element> contents){
        this (title, contents, "");
    }
    public FormWindowCustom(String title, ArrayList<Element> contents, String icon){
        this.title = title;
        this.content = contents;
        if (!icon.isEmpty()) this.icon = new ElementButtonImageData(ElementButtonImageData.IMAGE_DATA_TYPE_URL, icon);
    }

    public void addElement(Element element){
        content.add(element);
    }
    public void setIcon(String icon){
        if (!icon.isEmpty()) this.icon = new ElementButtonImageData(ElementButtonImageData.IMAGE_DATA_TYPE_URL, icon);
    }

    public String getJSONData(){
        String toModify = new Gson().toJson(this);
        //We need to replace this due to Java not supporting declaring class field 'default'
        return toModify.replace("defaultOptionIndex", "default")
                .replace("defaultText", "default")
                .replace("defaultValue", "default")
                .replace("defaultStepIndex", "default");
    }

    public FormResponseCustom getResponse(){
        return response;
    }
    public void setResponse(String data) {
        if (data.equals("null")){
            this.closed = true;
            return;
        }

        List<String> elementResponses = new Gson().fromJson(data, new TypeToken<List<String>>() {
        }.getType());
        elementResponses.remove(elementResponses.size() - 1); //submit button

        int i = 0;

        HashMap<Integer, FormResponseData> dropdownResponses = new HashMap<>();
        HashMap<Integer, String> inputResponses = new HashMap<>();
        HashMap<Integer, Float> sliderResponses = new HashMap<>();
        HashMap<Integer, FormResponseData> stepSliderResponses = new HashMap<>();
        HashMap<Integer, Boolean> toggleResponses = new HashMap<>();
        HashMap<Integer, Object> responses = new HashMap<>();

        for (String elementData : elementResponses){
            if (i >= content.size()) {
                break;
            }

            Element e = content.get(i);
            if (e == null) break;
            if (e instanceof ElementLabel) {
                i++;
                continue;
            }
            if (e instanceof ElementDropdown){
                String answer = ((ElementDropdown) e).options.get(Integer.parseInt(elementData));
                dropdownResponses.put(i, new FormResponseData(Integer.parseInt(elementData), answer));
                responses.put(i, answer);
            }
            else if (e instanceof ElementInput){
                inputResponses.put(i, elementData);
                responses.put(i, elementData);
            }
            else if (e instanceof ElementSlider){
                Float answer = Float.parseFloat(elementData);
                sliderResponses.put(i, answer);
                responses.put(i, answer);
            }
            else if (e instanceof ElementStepSlider){
                String answer = ((ElementStepSlider) e).steps.get(Integer.parseInt(elementData));
                stepSliderResponses.put(i, new FormResponseData(Integer.parseInt(elementData), answer));
                responses.put(i, answer);
            }
            else if (e instanceof ElementToggle){
                Boolean answer = Boolean.parseBoolean(elementData);
                toggleResponses.put(i, answer);
                responses.put(i, answer);
            }
            i++;
        }

        this.response = new FormResponseCustom(responses, dropdownResponses, inputResponses,
                sliderResponses, stepSliderResponses, toggleResponses);
    }

}
