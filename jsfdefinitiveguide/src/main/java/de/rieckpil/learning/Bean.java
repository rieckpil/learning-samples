package de.rieckpil.learning;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class Bean {

    private String text;
    private String password;
    private String message;
    private String hidden;
    private Part file;

    private boolean checked;
    private String oneMenu;
    private String oneRadio;
    private List<String> manyListbox;
    private List<String> manyCheckbox;
    private List<String> availableItems;
    private Map<String, Boolean> manyCheckboxMap = new LinkedHashMap<>();

    private List<SelectItem> availableSelectItems;

    private String selectedItem;

    @PostConstruct
    public void init() {
        availableItems = Arrays.asList("one", "two", "three");

        SelectItemGroup group1 = new SelectItemGroup("Group 1");
        group1.setSelectItems(new SelectItem[] {
                new SelectItem("Group 1 Value 1", "Group 1 Label 1"),
                new SelectItem("Group 1 Value 2", "Group 1 Label 2"),
                new SelectItem("Group 1 Value 3", "Group 1 Label 3")
        });

        SelectItemGroup group2 = new SelectItemGroup("Group 2");
        group2.setSelectItems(new SelectItem[] {
                new SelectItem("Group 2 Value 1", "Group 2 Label 1"),
                new SelectItem("Group 2 Value 2", "Group 2 Label 2"),
                new SelectItem("Group 2 Value 3", "Group 2 Label 3")
        });

        availableSelectItems = Arrays.asList(group1, group2);
    }

    public void collectCheckedValues() {
        manyCheckbox = manyCheckboxMap.entrySet().stream()
                .filter(e -> e.getValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public void submit() throws Exception {
        System.out.println("Form has been submitted!");
        System.out.println("text: " + text);
        System.out.println("password: " + password);
        System.out.println("message: " + message);
        System.out.println("hidden: " + hidden);
        System.out.println("file: " + file);
        if (file != null) {
            System.out.println("name: " + file.getSubmittedFileName());
            System.out.println("type: " + file.getContentType());
            System.out.println("size: " + file.getSize());
            InputStream content = file.getInputStream();
        }
    }

    public List<SelectItem> getAvailableSelectItems() {
        return availableSelectItems;
    }

    public void setAvailableSelectItems(List<SelectItem> availableSelectItems) {
        this.availableSelectItems = availableSelectItems;
    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public Map<String, Boolean> getManyCheckboxMap() {
        return manyCheckboxMap;
    }

    public void setManyCheckboxMap(Map<String, Boolean> manyCheckboxMap) {
        this.manyCheckboxMap = manyCheckboxMap;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getOneMenu() {
        return oneMenu;
    }

    public void setOneMenu(String oneMenu) {
        this.oneMenu = oneMenu;
    }

    public String getOneRadio() {
        return oneRadio;
    }

    public void setOneRadio(String oneRadio) {
        this.oneRadio = oneRadio;
    }

    public List<String> getManyListbox() {
        return manyListbox;
    }

    public void setManyListbox(List<String> manyListbox) {
        this.manyListbox = manyListbox;
    }

    public List<String> getManyCheckbox() {
        return manyCheckbox;
    }

    public void setManyCheckbox(List<String> manyCheckbox) {
        this.manyCheckbox = manyCheckbox;
    }

    public List<String> getAvailableItems() {
        return availableItems;
    }

    public void setAvailableItems(List<String> availableItems) {
        this.availableItems = availableItems;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHidden() {
        return hidden;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }
}