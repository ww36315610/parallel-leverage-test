package services.api;

import app.page.PageObjectElement;
import app.page.PageObjectMethod;
import org.apiguardian.api.API;

import java.util.HashMap;

public class APIObjectModel {

    /**
     * 定义element接收
     */
    public HashMap<String, APIObjectElement> elements = new HashMap<>();
    /**
     * 定义method接收
     */
    public HashMap<String, APIObjectMethod> methods = new HashMap<>();

    public HashMap<String, APIObjectElement> getElements() {
        return elements;
    }

    public void setElements(HashMap<String, APIObjectElement> elements) {
        this.elements = elements;
    }

    public HashMap<String, APIObjectMethod> getMethods() {
        return methods;
    }

    public void setMethods(HashMap<String, APIObjectMethod> methods) {
        this.methods = methods;
    }
}
