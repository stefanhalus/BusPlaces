package ro.stefanhalus.java.models;

public class AboutApp {
    private String author;
    private String description;
    private String email;
    private String name;
    private String version;
    private String website;

    public AboutApp(){
        this.name = "Bus Places";
        this.version = "1.0";
        this.description = "Aplicație de gestiune a rezervărilor care le permite clienților să realizeze rezervări de pe site-ul companiei. Este un produs finalizat dar nu finisat, putând interveni oricând dificultăți de nuanță pentru fiecare caz în parte, lucru greu de imaginat din afara problemei. ";
        this.author = "Halus Cristian Ștefan";
        this.email = "stefanhalus@gmail.com";
        this.website = "http://www.stefanhalus.ro";
    }
    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getWebsite() {
        return website;
    }

}
