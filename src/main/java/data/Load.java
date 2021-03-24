package data;

public class Load {
    private String[] categories;
    private String occupation;

    public Load(String args1, String args2) {
        this.setCategories(args1.toLowerCase());
        this.setOccupation(args2.toLowerCase());
    }

    public String[] parseCategory(String input) {
        return input.split("\\|");
    }

    public void setCategories(String categories) {
        this.categories = this.parseCategory(categories);
    }

    public String[] getCategories() {
        return this.categories;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getOccupation() {
        return this.occupation;
    }
}