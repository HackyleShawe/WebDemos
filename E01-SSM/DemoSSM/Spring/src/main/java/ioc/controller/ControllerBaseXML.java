package ioc.controller;

public class ControllerBaseXML {
    private String name;
    private String description;



    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void printSelfDescription() {
        System.out.println("ControllerBaseXML==> Name:"+this.name+"  Description:"+this.description);
    }
}
